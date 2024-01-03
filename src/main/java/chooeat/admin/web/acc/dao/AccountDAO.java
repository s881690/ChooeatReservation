package chooeat.admin.web.acc.dao;

import java.util.List;

import chooeat.admin.core.dao.CoreDao;
import chooeat.admin.web.acc.pojo.AdminAccountVO;

public interface AccountDAO extends CoreDao<AdminAccountVO, Integer>{
	
	List<AdminAccountVO> selectAllByAccId (String accId);
	
	List<AdminAccountVO> selectAllByAccAcc (String accAcc);
	
	List<AdminAccountVO> selectAllByAccName (String accName);
	
}
