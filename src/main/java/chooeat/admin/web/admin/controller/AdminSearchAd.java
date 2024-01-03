package chooeat.admin.web.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.ad.pojo.AdminAdVO;
import chooeat.admin.web.ad.service.AdminAdService;

@RestController
@RequestMapping("/adminSearchAd")
public class AdminSearchAd {

	@Autowired
	private AdminAdService SERVICE;
	
	@GetMapping("/selectAll")
	public List<AdminAdVO> findAll(Integer searchType, String search){
		
		if(searchType == 0) {
			return SERVICE.selectAll();			
		} else if (searchType == 1 || searchType == 2) {
			try {
				int id = Integer.parseInt(search);
				return SERVICE.searchById(searchType, id);
			} catch (NumberFormatException e) {
				return Collections.emptyList();
			}
		} else if (searchType == 3) {
			return SERVICE.searchByResName(search);
		} else {
			return Collections.emptyList();
		}
	}
	
	@GetMapping("/findAd")
	public AdminAdVO findAd(Integer adId) {
		return SERVICE.findByAdId(adId);
	}
}
