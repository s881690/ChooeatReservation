package chooeat.admin.web.reservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chooeat.admin.web.reservation.pojo.AdminReservationVO;

public interface AdminReservationRepository extends JpaRepository<AdminReservationVO, Integer>{
	
	AdminReservationVO findByReservationId(Integer reservationId);
	
	@Query(value = "SELECT reser FROM AdminReservationVO reser JOIN AdminRestaurantVO res ON reser.restaurantId = res.restaurantId WHERE res.resName LIKE %:resName%")
	public List<AdminReservationVO> findByResNameLike(@Param(value = "resName") String resName);
	
	@Query(value = "SELECT * FROM reservation WHERE restaurant_id LIKE :restaurantId", nativeQuery = true)
	public List<AdminReservationVO> findByResId(@Param(value = "restaurantId") String restaurantId);
	
	@Query(value = "SELECT reser FROM AdminReservationVO reser JOIN AdminAccountVO acc ON reser.accId = acc.accId WHERE acc.accName LIKE %:accName%")
	public List<AdminReservationVO> findByAccName(@Param(value = "accName") String accName);
	
	@Query(value = "SELECT *FROM reservation WHERE acc_id LIKE :accId", nativeQuery = true)
	public List<AdminReservationVO> findByAccId(@Param(value = "accId") String accId);
}
