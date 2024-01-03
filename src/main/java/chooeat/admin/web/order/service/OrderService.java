package chooeat.admin.web.order.service;

import java.util.List;

import chooeat.admin.web.order.pojo.AdminOrderDetailVO;
import chooeat.admin.web.order.pojo.AdminOrderVO;

public interface OrderService {

	public List<AdminOrderVO> selectAll();

	public AdminOrderVO findById(Integer orderId);
	
	public List<AdminOrderVO> searchBySomethingId(Integer searchType, Integer id);
	
	public List<AdminOrderVO> searchByAcc(Integer searchType, String search);
	
	public List<AdminOrderDetailVO> searchByOrderId(Integer id);
	
	public boolean deleteById(Integer id);

}
