package chooeat.prod.dao;

import java.util.List;

import chooeat.prod.model.vo.Order;

public interface OrderDao {
	int insert(Order order);
	
	Order selectByaccId(Integer accId);
	
	List<Order> selectAll();
}
