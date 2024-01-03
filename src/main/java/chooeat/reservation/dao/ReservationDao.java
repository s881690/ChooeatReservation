package chooeat.reservation.dao;

import java.sql.Date;
import java.util.List;

import chooeat.reservation.model.EmailInfo;
import chooeat.reservation.model.HourlySeat;
import chooeat.reservation.model.ReservationVO;
import chooeat.reservation.model.Result;


public interface ReservationDao {

	Integer insertReservation(ReservationVO reservationVO);

	Boolean updateReservation(ReservationVO reservationVO,int reservationId);

	Boolean deleteReservation(int reservationId);

	List<HourlySeat> selectall(Integer restaurant_id, Date date);

	List<Integer> reservedList(int memberId, int restaurantId, Date date);

	List<EmailInfo> getEmailInfos(int memberId, Integer reservationId);
	
	List<Result> reservationData(int reservationId);
	
	List<EmailInfo> selectAllReservationForSchedule();
	
	Boolean updateResNotify(int reservationId);
	
	List<EmailInfo> selectAllReservationForMember(int memberId);
	
	List<String> getRestaurantNameByReservation(int reservationId);
	

	

}
