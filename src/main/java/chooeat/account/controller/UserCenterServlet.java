package chooeat.account.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.Utils.GsonUtils;
import chooeat.account.model.vo.AccountVo;
import chooeat.account.service.AccountService;
@MultipartConfig
@WebServlet("/account/userCenter")
public class UserCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ----------------傳到下一層---------------------------------------------------------
	@Autowired
	private AccountService accountService;

	

	// ------------------------------------------------------------------------------------------------------------

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		switch (action) {
		case "update":
			update(req, resp);
			
			break;

		case "printdata":
			printdata(req, resp);
			break;

		}
	}
	// -------------------------------------------------更新-----------------------------------------------------------------
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		{	
			AccountVo member = (AccountVo) req.getSession().getAttribute("member");
			System.out.println(member);
			resp.setContentType("application/json; charset=utf-8");
			
			String photoPart1 = req.getParameter("imageBase64");
			byte[] photo = Base64.getDecoder().decode(photoPart1);
			

			System.out.println(photoPart1);
		    
			List<AccountVo> accountList = new ArrayList<>();
			AccountVo vo = new AccountVo();
			vo.setAcc_id(member.getAcc_id());
			vo.setAcc_pic(photo);
			vo.setAcc_name(req.getParameter("userinputname"));
			vo.setAcc_nickname(req.getParameter("userinputnick"));
			vo.setAcc_mail(req.getParameter("userinputmail"));
			vo.setAcc_birth(req.getParameter("userinputbirth"));
			vo.setAcc_phone(req.getParameter("userinputphone"));
			vo.setAcc_add1(req.getParameter("userinputadd"));
			vo.setAcc_gender(req.getParameter("userinputgender"));
			vo.setAcc_text(req.getParameter("usertextarea"));
			accountList.add(vo);

			accountService.update(accountList);

			resp.getWriter().write(GsonUtils.toJson(member)); // 將會員資訊轉換為 JSON 格式回傳給前端
		}
	};


	// ----------------------------------------------------印資料--------------------------------------------------------------------
	private void printdata(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		{
			AccountVo member = (AccountVo) req.getSession().getAttribute("member");
			System.out.println(member);
			resp.setContentType("application/json; charset=utf-8");
			
			
			  if (member.getAcc_pic() != null) {
			        member.setAcc_pic1(Base64.getEncoder().encodeToString(member.getAcc_pic()));
			    } else {
			    	 member.setAcc_pic1("./images/header/logo2.png"); // 或者设置为其他默认的空字符串，根据需求而定
			    }
			
			
			
			  
			
			
			
//			List<AccountVo> accountList = new ArrayList<>();
//			
//			AccountVo vo = new AccountVo();
//			
//			vo.setAcc_id(member.getAcc_id());
//			
//			accountList.add(vo);
			
//			member.setAcc_pic1(Base64.getEncoder().encodeToString(member.getAcc_pic())) ; 
		//	 accountService.printdata(accountList);

			resp.getWriter().write(GsonUtils.toJson(member)); // 將會員資訊轉換為 JSON 格式回傳給前端
			
		}
	}
};

//--------------------------------------------------------上傳團片--------------------------------------------------------------------
















