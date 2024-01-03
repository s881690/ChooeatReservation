package chooeat.admin.web.acc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.acc.dao.AccountDAO;
import chooeat.admin.web.acc.pojo.AdminAccountVO;
import chooeat.admin.web.acc.service.AccService;

@Service
public class AdminAccServiceImpl implements AccService{
	
	@Autowired
	private AccountDAO dao;

	@Override
	public AdminAccountVO findAcc(Integer accId) {
		return dao.selectById(accId);
	}

	@Override
	public AdminAccountVO edit() {
		return null;
	}

	@Override
	public List<AdminAccountVO> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<AdminAccountVO> searchBySomething(String searchType, String search) {
		String searchCondition = "%" + search + "%";
		if("1".equals(searchType)) {
			return dao.selectAllByAccId(searchCondition);
		} else if ("2".equals(searchType)){
			return dao.selectAllByAccAcc(searchCondition);			
		} else {
			return dao.selectAllByAccName(searchCondition);
		}
	}
}
