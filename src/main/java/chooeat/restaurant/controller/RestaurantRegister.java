package chooeat.restaurant.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.restaurant.service.RestaurantService;

@WebServlet("/restaurant/restaurantregister")
public class RestaurantRegister extends HttpServlet {
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
        
        List<String> values = new ArrayList<>();
    
        values.add(request.getParameter("account"));  
        values.add(request.getParameter("password")); 
        values.add(request.getParameter("status"));  
        values.add(request.getParameter("name")); 
        values.add(request.getParameter("address"));  
        values.add(request.getParameter("phone"));          
        values.add(request.getParameter("email"));                
        values.add(request.getParameter("starttime"));  
        values.add(request.getParameter("endtime"));  
        values.add(request.getParameter("seat"));  
        values.add(request.getParameter("guestOption"));  
        values.add(request.getParameter("seatmax"));  
                
        int b= RestaurantService.restaurantregister(values);
                
        if (b==1) {        	
		String to=request.getParameter("email");
        String subject="Chooeat註冊驗證信件";
        String messageText = "請點擊以下連結進行登入: http://35.194.170.154:8080/restaurant/Restaurantlogin.html";
        RestaurantService.sendMail(to, subject, messageText); 
		}else {System.out.println("註冊失敗");}
                     
        Gson gson = new Gson();
		String jsonStr = gson.toJson(b);		
		response.getWriter().write(jsonStr);                       

    }
}
