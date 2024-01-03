package chooeat.admin.web.ad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chooeat.admin.web.ad.pojo.AdminAdVO;

public interface AdminAdRepository extends JpaRepository<AdminAdVO, Integer>{
	
	AdminAdVO findByAdId(Integer adId);

	@Query(value = "SELECT * FROM ad WHERE ad.ad_id LIKE :adId", nativeQuery = true)
	public List<AdminAdVO> findByAdId(@Param(value = "adId") String adId);
	
	@Query(value = "SELECT * FROM ad WHERE ad.restaurant_id LIKE :restaurantId", nativeQuery = true)
	public List<AdminAdVO> findByResId(@Param(value = "restaurantId") String restaurantId);
	
	@Query(value = "SELECT ad FROM AdminAdVO ad JOIN AdminRestaurantVO res ON ad.restaurantId = res.restaurantId WHERE res.resName LIKE %:resName%")
	public List<AdminAdVO> findByResName(@Param(value = "resName") String resName);
	
}
