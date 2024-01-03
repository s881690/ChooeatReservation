package chooeat.prod.dao;

import java.util.List;

import chooeat.prod.model.vo.OrderDetail;


public interface OrderDetailDao {
	int insert(OrderDetail orderDetail);
	
	int deleteByorderDetailId(Integer orderDetailId);
	
	int updateByorderDetailId(OrderDetail orderDetail);
	
	OrderDetail selectByorderDetailId(Integer orderDetailId);
	
	List<OrderDetail> selectAll();
}
