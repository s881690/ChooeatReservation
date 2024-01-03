package chooeat.admin.web.admin.controller.servletVersion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.admin.core.pojo.Core;
import chooeat.admin.core.util.CommonUtil;
import chooeat.admin.web.admin.pojo.AdminVO;
import chooeat.admin.web.admin.service.AdminService;

//@WebServlet("/admin/searchAdmin")
public class AdminSearchAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdminService SERVICE;
	
	@Autowired
	private CommonUtil commonUtil;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");

		res.setContentType("application/json; charset=utf-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String searchType = req.getParameter("searchType");
		String search = req.getParameter("search");
		
		List<AdminVO> adminList = new ArrayList<>();
		
		if("0".equals(searchType) && "".equals(search)) {
			adminList = SERVICE.findAll();
		} else {
			adminList = SERVICE.searchBySomething(searchType, search);
		}
		
		if(adminList.size() == 0) {
			Core core = new Core();
			core.setMessage("查無資料");
			core.setSuccessful(false);
			commonUtil.writePojo2Json(res, core);
			return;
		}
				
		commonUtil.writePojo2Json(res, adminList);
	}
	
}
