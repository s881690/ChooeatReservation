package chooeat.admin.web.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chooeat.admin.web.activity.pojo.AdminActivityVO;

public interface AdminActivityRepository extends JpaRepository<AdminActivityVO, Integer>{

	//活動
	@Query(value = "SELECT act FROM AdminActivityVO act WHERE act.activityName LIKE %:activityName%")
	public List<AdminActivityVO> findByActivityNameLike(@Param(value = "activityName") String activityName);
	
	@Query(value = "SELECT * FROM activity a WHERE a.activity_id LIKE :activityId", nativeQuery = true)
	public List<AdminActivityVO> findByActivityIdLike(@Param(value = "activityId") String activityName);
	
	//主辦人
	@Query(value = "SELECT act FROM AdminActivityVO act JOIN AdminAccountVO acc ON act.accId = acc.accId WHERE acc.accName LIKE %:accName%")
	public List<AdminActivityVO> findByAccNameLike(@Param(value = "accName") String accName);
	
	@Query(value = "SELECT act FROM AdminActivityVO act JOIN AdminAccountVO acc ON act.accId = acc.accId WHERE acc.accAcc LIKE %:accAcc%")
	public List<AdminActivityVO> findByAccAccLike(@Param(value = "accAcc") String accAcc);
	
	@Query(value = "SELECT * FROM activity a WHERE a.acc_id LIKE :accId", nativeQuery = true)
	public List<AdminActivityVO> findByAccIdLike(@Param(value = "accId") String accId);
	
	//餐廳
	@Query(value = "SELECT act FROM AdminActivityVO act JOIN AdminRestaurantVO res ON act.restaurantId = res.restaurantId WHERE res.resName LIKE %:resName%")
	public List<AdminActivityVO> findByResNameLike(@Param(value = "resName") String resName);
	
	@Query(value = "SELECT * FROM activity a WHERE a.restaurant_id LIKE :resId", nativeQuery = true)
	public List<AdminActivityVO> findByResIdLike(@Param(value = "resId") String resId);
	
}
