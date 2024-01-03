package chooeat.restaurant.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.restaurant.service.RestaurantService;

@WebServlet("/restaurant/restaurantforgetpassword")
public class RestaurantForgetPassword extends HttpServlet {
	 @Autowired
	    private RestaurantService RestaurantService;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 設置跨域
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		response.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		// 取得請求參數

		String account = request.getParameter("account");
		String mail = request.getParameter("mail");

		int ddd = RestaurantService.restaurantforgetpassword(account, mail);
		if (ddd == 1) {
			String to = request.getParameter("mail");
			String subject = "Chooeat註冊忘記密碼驗證信件";
			String messageText = "您的新密碼為5487,請前去登入修改";
			RestaurantService.sendMail(to, subject, messageText);
		} else {
			System.out.println("驗證信件發送失敗");
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(ddd);
		response.getWriter().write(jsonStr);
	}
}
