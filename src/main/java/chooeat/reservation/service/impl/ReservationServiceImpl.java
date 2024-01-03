package chooeat.reservation.service.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import chooeat.reservation.dao.AccountRepository;
import chooeat.reservation.dao.ReservationDao;
import chooeat.reservation.dao.ReservationRepository;
import chooeat.reservation.dao.RestaurantDayoffRepository;
import chooeat.reservation.dao.RestaurantRepository;
import chooeat.reservation.dao.impl.ReservationLock;
import chooeat.reservation.model.AccountVo;
import chooeat.reservation.model.EmailDetails;
import chooeat.reservation.model.EmailInfo;
import chooeat.reservation.model.HourlySeat;
import chooeat.reservation.model.ReservationVO;
import chooeat.reservation.model.RestaurantVO;
import chooeat.reservation.model.Result;
import chooeat.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private RestaurantDayoffRepository restaurantDayoffRepository;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	EmailInfo emailInfo;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private JavaMailSender javaMailSender;
//	@Autowired
//	EmailDetails details;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountVo accountVo;
	@Autowired
	ReservationLock reservationLock;
	@Autowired
	RestaurantVO restaurantVO;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public Boolean isDayOff(Integer restaurant_id, String date) {
		// 把前端傳來的日期字串轉換成sqlDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Date selecteDate = Date.valueOf(localDate);

		// 資料庫查詢公休日資料表是否有那一天
		if (restaurantDayoffRepository.dayOffDate(restaurant_id, selecteDate) != null) {
			Date sqlDate = restaurantDayoffRepository.dayOffDate(restaurant_id, selecteDate).getDayoff();
			if (selecteDate.equals(sqlDate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Optional<RestaurantVO> selectResInfo(int restaurantId) {

		return restaurantRepository.findById(restaurantId);
	}

	@Override
	public List<HourlySeat> findHourlySeats(int restaurantId, String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Date selecteDate = Date.valueOf(localDate);
		return reservationDao.selectall(restaurantId, selecteDate);

	}

	@Override
	public List<Integer> reservedData(int memberId, int restaurantId, String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Date selecteDate = Date.valueOf(localDate);
		return reservationDao.reservedList(memberId, restaurantId, selecteDate);

	}

	@Override
	public Integer reservation(ReservationVO reservationVO) {

		// 抓出日期，尋找當天的剩餘座位數的list
		Date sqlDate = new Date(reservationVO.getReservationDateStartTime().getTime());
		List<HourlySeat> list = reservationDao.selectall(reservationVO.getRestaurantId(), sqlDate);

		// list長度=0，表示當天沒有人預約，直接insert
		if (list.size() == 0) {
			return reservationDao.insertReservation(reservationVO);
		}
		// 長度不等於0，尋找是否有預約紀錄
		// 抓出要存進去的當前時段
		int hour = reservationVO.getReservationDateStartTime().getHours();
		// 用餐廳id去找餐廳的座位數上限

		int remainSeat = restaurantRepository.findById(reservationVO.getRestaurantId()).get().getResMaxNum();
		// 從HourlySeat裡面找出該時段的剩餘數量
		for (HourlySeat hourlySeat : list) {
			if (hourlySeat.getHour() == hour) {
				remainSeat = hourlySeat.getRemainSeat();
				System.out.println("當前時段有" + hourlySeat.getRemainSeat() + "個座位");
				break;
			}
		}

		if ((remainSeat - reservationVO.getReservationNumber()) >= 0) {
			return reservationDao.insertReservation(reservationVO);
		}

		return 0;

	}

	@Override
	public void sendMail(int memberId, int reservationId) {
		
		EmailDetails details = new EmailDetails();

		// 取得信件內容必要資訊
		// 這裡輸入會員編號跟預約編號
		emailInfo = reservationDao.getEmailInfos(memberId, reservationId).get(0);
		// 收件人email
		// 為了方便demo，先寫自己的信箱
		details.setRecipient("s88169039@gmail.com");
//		details.setRecipient(emailInfo.getRecipient());
		System.out.println(details.getRecipient().toString());

		// 寄件主旨，要+訂位編號
		details.setSubject("訂位成功通知 - 訂位編號" + emailInfo.getReservationId().toString());
		System.out.println(details.getSubject().toString());

		// 信件內容
		details.setMsgBody("親愛的會員 " + emailInfo.getRecipientName() + "您好：\n" + "我們很高興通知您，您的訂位已成功完成。以下是您的訂位詳細信息：\n "
				+ "時間：" + emailInfo.getReservationTime() + "\n" + "餐廳名稱： " + emailInfo.getRestaurantName() + "\n"
				+ "地點：" + emailInfo.getRestaurantAddress() + "\n" + "電話： " + emailInfo.getRestaurantPhone() + "\n"
				+ "預訂人數：" + emailInfo.getReservationNumber());
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// 上面的value綁定application.properties綁定的寄件人
			mailMessage.setFrom(sender);
			// 收件人email
			mailMessage.setTo(details.getRecipient());
			// 信件內容
			mailMessage.setText(details.getMsgBody());
			// 信件主旨
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			String status = "Mail Sent Successfully...";
			System.out.println(status);
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			String status = "Error while Sending Mail";
			System.out.println(status);
		}

	}

	@Override
	public Boolean reservationUpdate(ReservationVO reservationVO, int reservationId) {
		return reservationDao.updateReservation(reservationVO, reservationId);
	}

	@Override
	public Boolean reservationDelete(int reservationId) {

		return reservationDao.deleteReservation(reservationId);
	}

	@Override
	public List<Result> reservationInfo(int reservationId) {

		return reservationDao.reservationData(reservationId);
	}

	@Override
	public String memberName(int memberId) {

		return accountRepository.findById(memberId).get().getAccName();
	}

	@Override
	public List<EmailInfo> getAllreservation(int memberId) {

		return reservationDao.selectAllReservationForMember(memberId);
	}

	@Override
	public String getRestaurantNameByReservation(int reservationId) {

		return reservationDao.getRestaurantNameByReservation(reservationId).get(0);
	}

	@Override
	public String bookReservation(String dateTime, Integer reservationNumber, Integer restaurantId) {
		 
		  try {
			  //判斷redis裡面有沒有當天該小時的資料，如果有，就是有人在結帳中
	            if (reservationLock.haskey(dateTime)) { 
	            	System.out.println("有其他人在結帳喔!!wait！");	
	            	return "wait";
	            } 
	            //判斷redis裡面有沒有當天該小時的資料，以下是沒有的情況
	            else {
	            	System.out.println("只有我在結帳~");
	            	
	        		//先把前端傳進來的預約日期字串轉換日期時間格式
	                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                java.util.Date utildate = dateFormat.parse(dateTime);
	                //抓出選擇的日期
	                Date sqlDate = new Date(utildate.getTime());
	                //用前端資料抓出選擇的小時
	                int paraHour = utildate.getHours();
	                System.out.println("當前小時是" + paraHour);
	                
	                //如果沒資料，剩餘座位數預設就是餐廳座位數
	                Integer remainSeats = restaurantRepository.findById(restaurantId).get().getResMaxNum();
	            	System.out.println("預設的餐廳座位數是" + restaurantRepository.findById(restaurantId).get().getResMaxNum());
	            
	            	
	                //用剩餘座位數的方法去跑，如果有對應到的，就更新數字
	                List<HourlySeat> list = reservationDao.selectall(restaurantId, sqlDate);
	                for(HourlySeat hourlySeat : list) {
	                	if(paraHour == hourlySeat.getHour()) {
	                		remainSeats = hourlySeat.getRemainSeat();
	                		System.out.println("更新之後是" + remainSeats);
	                	}
	                }
	                
	                //把剩餘座位數扣掉預約人數
	                remainSeats = remainSeats - reservationNumber;
	            	System.out.println("扣掉之後是" + remainSeats);
	                //扣掉預約人數後>=0，表示有位置可以接這筆訂單，所以存進redis
	                if(remainSeats >= 0) {
	                	
	                	if(reservationLock.acquireLock(dateTime,remainSeats)) {
	                		return "success";
	                	}else {
	                		System.out.println("沒有成功存進去QAQ");
	                		return "error";
	                	}
	  
	            	
	            	}else {
	            		System.out.println("剩餘座位數<0，不接你的單~");
	            		return "error";
	            	}
	  
	            	
	            }
	        } catch (ParseException e) {
				System.err.println("時間轉換出4惹~");
				e.printStackTrace();
			} finally {
	            

	        }
		  
		  return "";
		
	}

}
