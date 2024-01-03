package chooeat.admin.web.admin.controller.servletVersion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.admin.core.util.CommonUtil;
import chooeat.admin.web.admin.pojo.AdminVO;
import chooeat.admin.web.admin.service.AdminService;

//@WebServlet("/admin/adminRegister")
public class AdminRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdminService SERVICE;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");

		res.setContentType("application/json; charset=utf-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		AdminVO admin = commonUtil.json2Pojo(req, AdminVO.class);
		
	
		if (admin == null) {
			admin = new AdminVO();
			admin.setMessage("無會員資訊");
			admin.setSuccessful(false);
			commonUtil.writePojo2Json(res, admin);
			return;
		}
		
		admin = SERVICE.register(admin);
		commonUtil.writePojo2Json(res, admin);
		
	}

}
