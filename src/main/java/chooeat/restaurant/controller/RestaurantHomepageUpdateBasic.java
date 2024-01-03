package chooeat.restaurant.controller;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import chooeat.Utils.CustomTimeSerializer;
import chooeat.restaurant.service.RestaurantService;

@WebServlet("/restaurant/restauranthomepageupdatebasic")
public class RestaurantHomepageUpdateBasic extends HttpServlet {
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
		
		String a = request.getParameter("resAcc");
		String b = request.getParameter("resPass");
		String c = request.getParameter("resName");
		String d = request.getParameter("resAdd");
		String e = request.getParameter("resTel");
		String f = request.getParameter("resEmail");
		String g = request.getParameter("resSeatNumber");
		String h = request.getParameter("resStartTime");
		String i = request.getParameter("resEndTime");	
			
		int ccc=RestaurantService.restauranthomepageupdatebasic(a,b,c,d,e,f,g,h,i);			
				
		Gson gson = new GsonBuilder().registerTypeAdapter(Time.class, new CustomTimeSerializer()).create();
		String jsonStr = gson.toJson(ccc);
		response.getWriter().write(jsonStr);
	}
}
