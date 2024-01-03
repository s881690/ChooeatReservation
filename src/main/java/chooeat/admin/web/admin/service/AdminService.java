package chooeat.admin.web.admin.service;

import java.util.List;

import chooeat.admin.web.admin.pojo.AdminVO;

public interface AdminService {
	
	AdminVO register(AdminVO adminVO);
	
	AdminVO editAdmin(AdminVO admin);
	
	AdminVO login(AdminVO adminvo);
	
	AdminVO edit(Integer adminId, Integer adminPermission);
	
	List<AdminVO> findAll();
		
	List<AdminVO> searchBySomething(String searchType, String searchCondition);
	
	boolean remove(Integer adminId);

}
