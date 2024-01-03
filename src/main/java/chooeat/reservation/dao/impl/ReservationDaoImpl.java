package chooeat.reservation.dao.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import chooeat.reservation.dao.ReservationDao;
import chooeat.reservation.model.EmailInfo;
import chooeat.reservation.model.EmailInfoRowMapper;
import chooeat.reservation.model.GetNameRowMapper;
import chooeat.reservation.model.HourlySeat;
import chooeat.reservation.model.HourlySeatRowMapper;
import chooeat.reservation.model.ReservationVO;
import chooeat.reservation.model.Result;
import chooeat.reservation.model.ResultRowMapper;
import chooeat.reservation.model.reservedListRowMapper;

@Component
public class ReservationDaoImpl implements ReservationDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	//新增一筆訂位資料
	@Override
	public Integer insertReservation(ReservationVO reservationVO) {
		String sql = " insert into reservation(acc_id, restaurant_id, reservation_number, reservation_date_starttime, reservation_date_endtime, reservation_note, reservation_state, isComment, isNotify) "
				+ "values(:acc_id, :restaurant_id, :reservation_number, :reservation_date_starttime, :reservation_date_endtime,:reservation_note, 0, 0, 0) ";
		Map<String, Object> map = new HashMap<>();

		// 開始訂位時間+1小時，等於結束訂位時間（時段）
		Calendar cal = Calendar.getInstance();
		cal.setTime(reservationVO.getReservationDateStartTime());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		reservationVO.setReservationDateEndTime(new java.sql.Timestamp(cal.getTimeInMillis()));

		map.put("acc_id", reservationVO.getAccId());
		map.put("restaurant_id", reservationVO.getRestaurantId());
		map.put("reservation_number", reservationVO.getReservationNumber());
		map.put("reservation_date_starttime", reservationVO.getReservationDateStartTime());
		map.put("reservation_date_endtime", reservationVO.getReservationDateEndTime());

		if (reservationVO.getReservationNote() != null) {
			map.put("reservation_note", reservationVO.getReservationNote());
		} else {
			map.put("reservation_note", "");
		}

		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

		int reservationId = keyHolder.getKey().intValue();

		return reservationId;
	}

	//修改一筆訂位資料
	@Override
	public Boolean updateReservation(ReservationVO reservationVO, int reservationId) {

		try {
			String sql = "update reservation " + "set reservation_number = :reservation_number, "
					+ "reservation_date_starttime = :reservation_date_starttime, "
					+ "reservation_date_endtime = :reservation_date_endtime, " 
					+ "reservation_note = :reservation_note ,"
					+ "isNotify = :isNotify "
					+ "where reservation_id = :reservation_id ; ";
			Map<String, Object> map = new HashMap<>();

			// 開始訂位時間+1小時，等於結束訂位時間（時段）
			Calendar cal = Calendar.getInstance();
			cal.setTime(reservationVO.getReservationDateStartTime());
			cal.add(Calendar.HOUR_OF_DAY, 1);
			reservationVO.setReservationDateEndTime(new java.sql.Timestamp(cal.getTimeInMillis()));

			map.put("reservation_number", reservationVO.getReservationNumber());
			map.put("reservation_date_starttime", reservationVO.getReservationDateStartTime());
			map.put("reservation_date_endtime", reservationVO.getReservationDateEndTime());
			map.put("reservation_id", reservationId);
			map.put("isNotify", 0);
			if (reservationVO.getReservationNote() != null) {
				map.put("reservation_note", reservationVO.getReservationNote());
			} else {
				map.put("reservation_note", "");
			}

			namedParameterJdbcTemplate.update(sql, map);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Boolean deleteReservation(int reservationId) {
		try {
			String sql = "delete from reservation where reservation_id = :reservation_id; ";
			Map<String, Object> map = new HashMap<>();
			map.put("reservation_id", reservationId);
			namedParameterJdbcTemplate.update(sql, map);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	//	查詢該會員在該餐廳當天所有預約時段
	@Override
	public List<Integer> reservedList(int memberId, int restaurantId, Date date) {
		String sql = "SELECT HOUR(reservation_date_starttime) AS reservation_hour " + "FROM reservation "
				+ "WHERE acc_id = :memberId " + "AND DATE(reservation_date_starttime) = :date "
				+ "AND restaurant_id = :restaurantId; ";

		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("date", date);
		map.put("restaurantId", restaurantId);
		List<Integer> list = namedParameterJdbcTemplate.query(sql, map, new reservedListRowMapper());

		return list;

	}
	//查詢該餐廳當天每一個時段的剩餘座位數
	@Override
	public List<HourlySeat> selectall(Integer restaurant_id, Date date) {
		String sql = "SELECT HOUR(reservation_date_starttime) AS hour, "
				+ "(res_max_num - SUM(reservation_number)) AS remaining_seats " + "FROM restaurant r " + "LEFT JOIN "
				+ "reservation rs ON r.restaurant_id = rs.restaurant_id " + "WHERE r.restaurant_id = :restaurant_id "
				+ "AND DATE(reservation_date_starttime) = :date "
				+ "AND TIME(reservation_date_starttime) >= res_start_time "
				+ "AND TIME(reservation_date_starttime) < ADDTIME(res_end_time, '-01:00:00') " + "GROUP BY hour ";
		Map<String, Object> map = new HashMap<>();
		map.put("restaurant_id", restaurant_id);
		map.put("date", date);
		List<HourlySeat> list = namedParameterJdbcTemplate.query(sql, map, new HourlySeatRowMapper());

		return list;
	}
//查詢寄出訂位成功通知信的必要資訊
	@Override
	public List<EmailInfo> getEmailInfos(int memberId, Integer reservation_id) {
		String sql = "SELECT a.acc_name, a.acc_mail, r1.reservation_id,r1.reservation_number,r1.reservation_date_starttime, r1.isNotify, "
				+ "r2.res_name, r2.res_add ,r2.res_tel "
				+ "FROM `account` a "
				+ "JOIN reservation r1 ON a.acc_id = r1.acc_id "
				+ "JOIN restaurant r2 ON r1.restaurant_id = r2.restaurant_id "
				+ "WHERE a.acc_id = :acc_id AND reservation_id = :reservation_id ";
		Map<String, Object> map = new HashMap<>();
		map.put("acc_id", memberId);
		map.put("reservation_id", reservation_id);
		List<EmailInfo> list = namedParameterJdbcTemplate.query(sql, map, new EmailInfoRowMapper());
		return list;
	}

	
	//查詢訂位成功後在訂位成功畫面顯示的資料
	@Override
	public List<Result> reservationData(int reservationId) {
		String sql = "select a.acc_name, r.reservation_date_starttime, r.reservation_number "
				   + "from reservation r "
				   + "join `account` a on r.acc_id = a.acc_id "
				   + "where reservation_id = :reservation_id; ";
		 Map<String, Object> map = new HashMap<>();		   
		 map.put("reservation_id", reservationId);
		 List<Result> list = namedParameterJdbcTemplate.query(sql, map, new ResultRowMapper());
		return list;
	}

	@Override
	
	//排程用查詢(24小時內通知信)
	public List<EmailInfo> selectAllReservationForSchedule() {
		String sql = "select a.acc_name, r2.res_name, r2.res_add, r2.res_tel, r1.reservation_date_starttime, "
				+ " r1.reservation_number, r1.isNotify, r1.reservation_id, a.acc_mail "
				+ " from reservation r1 "
				+ " join `account` a on r1.acc_id = a.acc_id "
				+ " join restaurant r2 on r1.restaurant_id = r2.restaurant_id ;";
		Map<String, Object> map = new HashMap<>();
		List<EmailInfo> list = namedParameterJdbcTemplate.query(sql, map, new EmailInfoRowMapper());
		return list;
	}

	@Override
	public Boolean updateResNotify(int reservationId) {
		String sql = "UPDATE reservation "
				+ "SET isNotify = 1 "
				+ "WHERE reservation_id = :reservation_id; ";
		Map<String, Object> map = new HashMap<>();
		map.put("reservation_id", reservationId);
		namedParameterJdbcTemplate.update(sql, map);
		return null;
	}

	@Override
	//把查詢資料弄到會員頁面
	public List<EmailInfo> selectAllReservationForMember(int memberId) {
		String sql = "select a.acc_name, r2.res_name, r2.res_add, r2.res_tel, r1.reservation_date_starttime, "
				+ " r1.reservation_number, r1.isNotify, r1.reservation_id, a.acc_mail "
				+ " from reservation r1 "
				+ " join `account` a on r1.acc_id = a.acc_id "
				+ " join restaurant r2 on r1.restaurant_id = r2.restaurant_id "
				+ " where a.acc_id = :acc_id ";
		Map<String, Object> map = new HashMap<>();
		map.put("acc_id", memberId);
		List<EmailInfo> list = namedParameterJdbcTemplate.query(sql, map, new EmailInfoRowMapper());
		return list;
	}

	@Override
	public List<String> getRestaurantNameByReservation(int reservationId) {
		String sql = "SELECT r2.res_name "
				+ "FROM reservation r1 "
				+ "join restaurant r2 "
				+ "on r1.restaurant_id = r2.restaurant_id "
				+ "where r1.reservation_id = :reservation_id; ";
		Map<String, Object> map = new HashMap<>();
		map.put("reservation_id", reservationId);
		List<String> list = namedParameterJdbcTemplate.query(sql, map, new GetNameRowMapper());
		return list;
		
	}

	
	
	

}
