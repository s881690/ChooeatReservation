package chooeat.admin.web.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.activity.dao.AdminActivityRepository;
import chooeat.admin.web.activity.pojo.AdminActivityVO;
import chooeat.admin.web.activity.service.AdminActivityService;

@Service
public class AdminActivityServiceImpl implements AdminActivityService{

	@Autowired
	private AdminActivityRepository activityRepository;

	@Override
	public List<AdminActivityVO> selectAll() {
		List<AdminActivityVO> activityList = activityRepository.findAll();
		return activityList;
	}

	@Override
	public AdminActivityVO findByActivityId(Integer activityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminActivityVO> searchBySomethingId(Integer searchType, Integer id) {
		String stringId = "%" + String.valueOf(id) + "%";
		
		if(searchType == 2) {
			return activityRepository.findByActivityIdLike(stringId);
		} else if (searchType == 5){
			return activityRepository.findByAccIdLike(stringId);
		} else if (searchType == 7){
			return activityRepository.findByResIdLike(stringId);
		} else {
			return null;
		}
	}

	@Override
	public List<AdminActivityVO> searchByNameOrAcc(Integer searchType, String search) {
		
		if(searchType == 1) {
			return activityRepository.findByActivityNameLike(search);
		} else if (searchType == 3) {
			return activityRepository.findByAccNameLike(search);
		} else if (searchType == 4) {
			return activityRepository.findByAccAccLike(search);
		} else if (searchType == 6) {
			return activityRepository.findByResNameLike(search);
		} else {
			return null;
		}
	}
	
}
