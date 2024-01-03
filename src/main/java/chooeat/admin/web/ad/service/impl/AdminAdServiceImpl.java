package chooeat.admin.web.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.ad.dao.AdminAdRepository;
import chooeat.admin.web.ad.pojo.AdminAdVO;
import chooeat.admin.web.ad.service.AdminAdService;

@Service
public class AdminAdServiceImpl implements AdminAdService{

	@Autowired
	private AdminAdRepository adRepository;
	
	@Override
	public List<AdminAdVO> selectAll() {
		List<AdminAdVO> adList = adRepository.findAll();
		return adList;
	}

	@Override
	public AdminAdVO findByAdId(Integer adId) {
		return adRepository.findByAdId(adId);
	}

	@Override
	public List<AdminAdVO> searchById(Integer searchType, Integer id) {
		String stringId = "%" + String.valueOf(id) + "%";
		
		if(searchType == 1) {
			return adRepository.findByAdId(stringId);
		} else if (searchType == 2) {
			return adRepository.findByResId(stringId);
		} else {
			return null;
		}
	}

	@Override
	public List<AdminAdVO> searchByResName(String resName) {
		
		if(resName != null) {
			return adRepository.findByResName(resName);
		} else {
			return null;
		}
	}

}
