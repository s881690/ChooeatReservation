package chooeat.admin.web.prod.service;

import java.util.List;

import chooeat.admin.web.prod.pojo.ProdVO;

public interface ProdService {

	public List<ProdVO> selectAll();
	
	public ProdVO findByProdId(Integer prodId);
	
	public List<ProdVO> searchBySomethingId(Integer searchType, Integer id);
	
	public List<ProdVO> searchBySomethingName(Integer searchType, String name);
	
}
