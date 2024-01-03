package chooeat.admin.web.resType.dao;

import java.util.List;

import chooeat.admin.core.dao.CoreDao;
import chooeat.admin.web.resType.pojo.ResTypeVO;

public interface ResTypeDAO extends CoreDao<ResTypeVO, Integer>{
	
	ResTypeVO selectByResTypeName (String resTypeName);
	
	List<ResTypeVO> selectAllByResTypeId (String resTypeId);
	
	List<ResTypeVO> selectAllByResTypeName (String resTypeName);
}
