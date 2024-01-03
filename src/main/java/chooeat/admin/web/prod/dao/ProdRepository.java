package chooeat.admin.web.prod.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chooeat.admin.web.prod.pojo.ProdVO;

public interface ProdRepository extends JpaRepository<ProdVO, Integer>{
	
	ProdVO findByProdId(Integer prodId);
	
	@Query(value = "SELECT * FROM prod p WHERE p.prod_id LIKE :prodId", nativeQuery = true)
	List<ProdVO> findByProdIdLike(@Param(value = "prodId") String prodId);
	
	@Query("SELECT p FROM ProdVO p WHERE p.prodName LIKE %:prodName%")
	List<ProdVO> findByProdNameLike(@Param(value = "prodName") String prodName);

	@Query(value = "SELECT * FROM prod p WHERE p.restaurant_id LIKE :restaurantId", nativeQuery = true)
	List<ProdVO> findByRestaurantIdLike(@Param(value = "restaurantId") String restaurantId);
	
	@Query("SELECT p FROM ProdVO p JOIN AdminRestaurantVO res ON p.restaurantId = res.restaurantId WHERE res.resName LIKE %:resName%")
	List<ProdVO> findByRestaurantNameLike(@Param(value = "resName") String resName);
}
