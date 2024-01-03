package chooeat.admin.web.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.acc.pojo.AdminAccountVO;
import chooeat.admin.web.acc.service.AccService;

@RestController
@RequestMapping("/accResult")
public class AccSearchResult {

	@Autowired
	private AccService SERVICE;
	
	@GetMapping("/findAcc")
	public AdminAccountVO findAcc(Integer accId) {
		return SERVICE.findAcc(accId);
	}
}
