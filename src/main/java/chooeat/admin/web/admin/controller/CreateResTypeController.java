package chooeat.admin.web.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.admin.core.util.CommonUtil;
import chooeat.admin.web.resType.pojo.ResTypeVO;
import chooeat.admin.web.resType.service.ResTypeService;

@WebServlet("/admin/adminCreateResType")
public class CreateResTypeController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ResTypeService SERVICE;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");

		res.setContentType("application/json; charset=utf-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		ResTypeVO resType = commonUtil.json2Pojo(req, ResTypeVO.class);
		if (resType == null) {
			resType = new ResTypeVO();
			resType.setMessage("無此類別資訊");
			resType.setSuccessful(false);
			commonUtil.writePojo2Json(res, resType);
			return;
		}
		
		resType = SERVICE.createResType(resType);
		commonUtil.writePojo2Json(res, resType);
		
	}

}
