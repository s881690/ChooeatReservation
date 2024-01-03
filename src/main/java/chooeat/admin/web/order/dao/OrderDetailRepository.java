package chooeat.admin.web.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chooeat.admin.web.order.pojo.AdminOrderDetailVO;

public interface OrderDetailRepository extends JpaRepository<AdminOrderDetailVO, Integer>{

	@Query(value = "SELECT * FROM order_detail WHERE order_id = :orderId", nativeQuery = true)
	List<AdminOrderDetailVO> findByOrderId(@Param(value = "orderId") String orderId);
}
