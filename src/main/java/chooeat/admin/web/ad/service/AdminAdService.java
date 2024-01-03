package chooeat.admin.web.ad.service;

import java.util.List;

import chooeat.admin.web.ad.pojo.AdminAdVO;

public interface AdminAdService {
	
	public List<AdminAdVO> selectAll();
	
	public AdminAdVO findByAdId(Integer adId);
	
	public List<AdminAdVO> searchById(Integer searchType, Integer id);
	
	public List<AdminAdVO> searchByResName(String resName);

}
