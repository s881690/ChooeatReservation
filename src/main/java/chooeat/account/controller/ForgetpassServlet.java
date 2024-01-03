package chooeat.account.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.account.service.AccountService;

@WebServlet("/account/forget")
public class ForgetpassServlet extends HttpServlet {
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

		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		String line;
		while ((line = br.readLine()) != null) {
			json += line;
		}

		JSONObject actObj = new JSONObject(json);
		String forgetemail = actObj.get("forgetemail").toString();
		System.out.println(forgetemail);
		int[] aaa = accountService.updatepass(forgetemail);
		
		
		if(aaa[0]==1) {
			String to = actObj.get("forgetemail").toString();

			String subject = "密碼通知";

			String ch_name = "使用者你好";
			int passRandom = aaa[1];
			String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";

			mail mailService = new mail();
			mailService.sendMail(to, subject, messageText);
		}
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(aaa);
		res.getWriter().write(jsonStr);
		
	}

}
