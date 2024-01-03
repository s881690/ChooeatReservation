package chooeat.account.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.account.model.vo.AccountVo;
import chooeat.account.service.AccountService;

@WebServlet("/account/changpass")
public class ChangpassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ----------------傳到下一層---------------------------------------------------------
	@Autowired
	private AccountService accountService;



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設置跨域
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		// 設置返回格式
		res.setContentType("application/json; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("utf-8");
		// 取得請求參數

//		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//		String json = "";
//		String line;
//		while ((line = br.readLine()) != null) {
//			json += line;
//		}
//		
//		JSONObject actObj = new JSONObject(json);
//		String password = actObj.get("password").toString();
//		String password2 = actObj.get("password2").toString();
//		String password3 = actObj.get("password3").toString();
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String password3 = req.getParameter("password3");
//		System.out.println("1"+password);
//		System.out.println("2"+password2);
//		System.out.println("3"+password3);
		AccountVo member = (AccountVo) req.getSession().getAttribute("member");
		String sqlpassword = member.getAcc_pass();
		Integer id = member.getAcc_id();
		System.out.println(sqlpassword);
//		System.out.println(password2 !="");
//		System.out.println(password2.equals(password3));
		
		if ((password2 != ""  && password2.equals(password3))){
//				System.out.println("aa");
		String a=	accountService.changpass( id, password2 ,sqlpassword,password);
			
			if(a=="1") {
			Gson gson = new Gson();
			String jsonStr = gson.toJson(member);
			res.getWriter().write(jsonStr);
			}
			
			
		}else{
			System.out.println("輸入錯誤");
			
		}

		


		
	}

}
