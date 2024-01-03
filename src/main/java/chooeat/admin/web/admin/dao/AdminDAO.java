package chooeat.admin.web.admin.dao;

import java.util.List;

import chooeat.admin.core.dao.CoreDao;
import chooeat.admin.web.admin.pojo.AdminVO;

public interface AdminDAO extends CoreDao<AdminVO, Integer>{
	
	AdminVO selectByAdminName(String adminName);
	
	AdminVO selectByAdminAcc(String adminAcc);

	List<AdminVO> selectAllByAdminName(String searchCondition);
	
	List<AdminVO> selectAllByAdminAcc(String searchCondition);
	
	AdminVO selectForLogin(String adminAcc, String adminPass);
}
