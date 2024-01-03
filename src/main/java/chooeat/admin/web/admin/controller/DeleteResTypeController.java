package chooeat.admin.web.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.admin.core.pojo.Core;
import chooeat.admin.core.util.CommonUtil;
import chooeat.admin.web.resType.service.ResTypeService;

@WebServlet("/admin/adminDeleteResType")
public class DeleteResTypeController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ResTypeService SERVICE;
	
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
		
		int resTypeId = Integer.parseInt(req.getParameter("resTypeId"));
		
		final Core core = new Core();		
		
		if(req.getParameter("resTypeId") == null) {
			core.setMessage("無此類別");
			core.setSuccessful(false);
		} else {
			core.setMessage("刪除成功!");
			core.setSuccessful(SERVICE.deleteResType(resTypeId));
		}
		commonUtil.writePojo2Json(res, core);
	}
}
