package chooeat.admin.web.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chooeat.admin.web.order.pojo.AdminOrderVO;

public interface OrderRepository extends JpaRepository<AdminOrderVO, Integer>{

	AdminOrderVO findByOrderId (Integer orderId);
	
	@Query(value = "SELECT * FROM `order` o WHERE o.order_id LIKE :orderId", nativeQuery = true)
	List<AdminOrderVO> findByOrderIdLike(@Param(value = "orderId") String orderId);
	
	@Query(value = "SELECT * FROM `order` o WHERE o.acc_id LIKE :accId", nativeQuery = true)
	List<AdminOrderVO> findByAccIdLike(@Param(value = "accId") String accId);
	
	@Query(value = "SELECT o FROM AdminOrderVO o JOIN AdminAccountVO acc ON o.accId = acc.accId WHERE acc.accAcc LIKE %:accAcc%")
	List<AdminOrderVO> findByAccAccLike(@Param(value = "accAcc") String accAcc);
	
	@Query(value = "SELECT o FROM AdminOrderVO o JOIN AdminAccountVO acc ON o.accId = acc.accId WHERE acc.accName LIKE %:accName%")
	List<AdminOrderVO> findByAccNameLike(@Param(value = "accName") String accName);
}
