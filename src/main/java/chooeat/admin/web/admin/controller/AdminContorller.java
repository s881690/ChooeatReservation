package chooeat.admin.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.core.pojo.Core;
import chooeat.admin.web.admin.pojo.AdminVO;
import chooeat.admin.web.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminContorller {

	@Autowired
	private AdminService SERVICE;

	@PostMapping("/register")
	public AdminVO register(@RequestBody AdminVO admin) {

		if (admin == null) {
			admin = new AdminVO();
			admin.setMessage("無會員資訊");
			admin.setSuccessful(false);
			return admin;
		}

		admin = SERVICE.register(admin);

		return admin;
	}

	@GetMapping("/editPermission")
	public AdminVO editPermission(Integer adminId, Integer permissionValue) {
		return SERVICE.edit(adminId, permissionValue);
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}

	@GetMapping("/searchAdmin")
	public List<AdminVO> searchAdmin(String searchType, String search) {
		List<AdminVO> adminList = new ArrayList<>();

		if ("0".equals(searchType) && "".equals(search)) {
			adminList = SERVICE.findAll();
		} else {
			adminList = SERVICE.searchBySomething(searchType, search);
		}

		return adminList;

	}
	
	@GetMapping("/deleteAdmin")
	public Core deleteAdmin(String adminId) {
		
		Core core = new Core();
		
		if(adminId == null) {
			core.setSuccessful(false);
			core.setMessage("刪除失敗，請確認此管理員是否存在");
			return core;
		}
		
		int admin = Integer.parseInt(adminId);
		
		if(SERVICE.remove(admin) == false) {
			core.setSuccessful(false);
			core.setMessage("刪除失敗，請確認Repository運作是否正常");
			return core;
		} else {
			core.setSuccessful(true);
			core.setMessage("刪除成功");
			return core;
		}
		
	}
	
	@PostMapping("/editAdmin")
	public AdminVO editAdmin(@RequestBody AdminVO admin, HttpServletRequest req) {
		
		if (admin == null) {
			admin = new AdminVO();
			admin.setMessage("無會員資訊");
			admin.setSuccessful(false);
			return admin;
		}

		admin = SERVICE.editAdmin(admin);
		
		req.getSession().invalidate();
		return admin;
		
	}
}
