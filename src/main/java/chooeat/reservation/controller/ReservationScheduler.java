package chooeat.reservation.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import chooeat.reservation.dao.ReservationDao;
import chooeat.reservation.model.EmailDetails;
import chooeat.reservation.model.EmailInfo;

@Component
public class ReservationScheduler {
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	EmailDetails details;
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;
	


//	@Scheduled(cron = "0 0 * * * *") // 每小时的整点触发一次
	@Scheduled(cron = "0 * * * * *") // 每分钟触发一次
	public void invokeMethodForExpiredReservations() {
	    java.util.Date currentDateTime = new java.util.Date();
	    long twentyFourHoursInMillis = 24 * 60 * 60 * 1000;
	    Timestamp twentyFourHoursLater = new Timestamp(currentDateTime.getTime() + twentyFourHoursInMillis);

	    //抓出所有的預約紀錄
	    List<EmailInfo> list = reservationDao.selectAllReservationForSchedule();
	    for (EmailInfo emailInfo : list) {
	    	
	    	//以下判斷預約時間是否在未來24小時內
	        String reservationTimeStr = emailInfo.getReservationTime();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	        	java.util.Date reservationTime = dateFormat.parse(reservationTimeStr);
	            if (reservationTime.after(currentDateTime) && reservationTime.before(twentyFourHoursLater) && emailInfo.getIsNotify() == 0) {
	  
	            	//寄送mail
	            	// 收件人email
	            	//為了方便demo，先寫自己的信箱
	            	details.setRecipient("s88169039@gmail.com");
	            	//details.setRecipient(emailInfo.getRecipient());
	            	// 寄件主旨
	            	details.setSubject("訂位提醒 - 訂位編號" + emailInfo.getReservationId().toString());
	            	// 信件內容
	        		details.setMsgBody("親愛的會員 " + emailInfo.getRecipientName() + "您好：\n" 
	        						 + "提醒您，您在 CHOOEAT! 的未來24小時的預約紀錄。以下是您的訂位詳細信息：\n " 
	        						 + "時間：" + emailInfo.getReservationTime() + "\n" 
	        						 + "餐廳名稱： " + emailInfo.getRestaurantName() + "\n"
	        						 + "地點：" + emailInfo.getRestaurantAddress() + "\n" 
	        						 + "電話： " + emailInfo.getRestaurantPhone() + "\n"
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
	        			
	        			
	        		
	        			String status = "notifyMail Sent Successfully...";
	        			
	        			
	        			System.out.println(status);
	        			reservationDao.updateResNotify(emailInfo.getReservationId());
	        			
	        		}

	        		// Catch block to handle the exceptions
	        		catch (Exception e) {
	        			String status = "Error while Sending Mail";
	        			System.out.println(status);
	        		}
	        		
	        	
	        	
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
}
