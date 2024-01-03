package chooeat.admin.web.resType.service;

import java.util.List;

import chooeat.admin.web.resType.pojo.ResTypeVO;

public interface ResTypeService {
	
	ResTypeVO createResType(ResTypeVO resType);
	
	boolean deleteResType(Integer resTypeId);

	List<ResTypeVO> findAll();
	
	List<ResTypeVO> searchBySomething(String searchType, String searchCondition);
	
}
