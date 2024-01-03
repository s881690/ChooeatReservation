package chooeat.admin.web.resType.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.resType.dao.ResTypeDAO;
import chooeat.admin.web.resType.pojo.ResTypeVO;
import chooeat.admin.web.resType.service.ResTypeService;

@Service
public class AdminResTypeServiceImpl implements ResTypeService{
	
	@Autowired
	private ResTypeDAO dao;

	@Override
	public ResTypeVO createResType(ResTypeVO resType) {
		if(resType.getResTypeName() == null || resType.getResTypeName().length() == 0) {
			resType.setMessage("請輸入類別名稱");
			resType.setSuccessful(false);
			return resType;
		}
		
		if(dao.selectByResTypeName(resType.getResTypeName()) != null) {
			resType.setMessage("類別已存在");
			resType.setSuccessful(false);
			return resType;
		}
		
		final int resultCount = dao.insert(resType);
		
		if(resultCount < 1) {
			resType.setMessage("新增失敗，請聯絡管理員!");
			resType.setSuccessful(false);
			return resType;
		}
		
		resType.setMessage("新增成功!");
		resType.setSuccessful(true);
		return resType;
	}

	@Override
	public boolean deleteResType(Integer resTypeId) {
		return dao.deleteById(resTypeId) > 0;
	}

	@Override
	public List<ResTypeVO> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<ResTypeVO> searchBySomething(String searchType, String search) {
		String searchCondition = "%" + search + "%";
		if("1".equals(searchType)) {
			return dao.selectAllByResTypeId(searchCondition);
		} else {
			return dao.selectAllByResTypeName(searchCondition);			
		}
	}

}
