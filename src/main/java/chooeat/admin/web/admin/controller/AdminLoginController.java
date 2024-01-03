package chooeat.admin.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.admin.pojo.AdminVO;
import chooeat.admin.web.admin.service.AdminService;

@RestController
@RequestMapping("/adminLogin")
public class AdminLoginController {

	@Autowired
	private AdminService SERVICE;
	
	@PostMapping("/login")
	public AdminVO login(HttpServletRequest req, @RequestBody AdminVO admin) {

		if (admin == null) {
			admin = new AdminVO();
			admin.setMessage("無管理員資訊");
			admin.setSuccessful(false);
			return admin;
		}

		admin = SERVICE.login(admin);

		if (admin.isSuccessful()) {
			if (req.getSession(false) != null) {
				req.changeSessionId();
			}
			final HttpSession session = req.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("admin", admin);
		}

		return admin;
	}
}
