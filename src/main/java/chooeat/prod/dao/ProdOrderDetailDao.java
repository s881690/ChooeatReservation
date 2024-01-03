package chooeat.prod.dao;

import java.util.List;

import chooeat.prod.model.vo.ProdOrderDetail;


public interface ProdOrderDetailDao {
	
	int insert(ProdOrderDetail prodOrderDetail);
	
	List<ProdOrderDetail> selectAll();
}
