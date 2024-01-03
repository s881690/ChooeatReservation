package chooeat.admin.web.activity.service;

import java.util.List;

import chooeat.admin.web.activity.pojo.AdminActivityVO;

public interface AdminActivityService {

	public List<AdminActivityVO> selectAll();
	
	public AdminActivityVO findByActivityId(Integer activityId);
	
	public List<AdminActivityVO> searchBySomethingId(Integer searchType, Integer id);
	
	public List<AdminActivityVO> searchByNameOrAcc(Integer searchType, String search);
}
