package chooeat.admin.web.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.activity.pojo.AdminActivityVO;
import chooeat.admin.web.activity.service.AdminActivityService;

@RestController
@RequestMapping("/adminSearchActivity")
public class AdminSearchActivity {

	@Autowired
	private AdminActivityService SERVICE;
	
	@GetMapping("/selectAll")
	public List<AdminActivityVO> findAll(Integer searchType, String search){
		
		if(searchType == 0) {
			return SERVICE.selectAll();			
		} else if (searchType == 2 || searchType == 5 || searchType == 7) {
			try {
				int id = Integer.parseInt(search);
				return SERVICE.searchBySomethingId(searchType, id);
			} catch (NumberFormatException e) {
				return Collections.emptyList();
			}
		} else if (searchType == 1 || searchType == 3 || searchType == 4 || searchType == 6) {
			return SERVICE.searchByNameOrAcc(searchType, search);
		} else {
			return Collections.emptyList();
		}
	}
}
