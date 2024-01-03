package chooeat.prod.dao;

import java.util.List;

import chooeat.prod.model.vo.Prod;


public interface ProdDao {
	int insert(Prod prod);
	int deleteByprodId(Integer prodId);
	int updateByprodId(Prod prod);
	List<Prod> getByCompositeQuery(String searchText);
	Prod selectByProdId(Integer prodId);
	List<Prod> selectAll();
	long getTotal();
}
