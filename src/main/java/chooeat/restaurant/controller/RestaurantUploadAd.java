package chooeat.restaurant.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import chooeat.restaurant.service.RestaurantService;

@WebServlet("/restaurant/restaurantuploadad")
public class RestaurantUploadAd extends HttpServlet {
	 @Autowired
	    private RestaurantService RestaurantService;

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        String adcheck = "0";
        String restaurantId = request.getParameter("restaurantId");
        String adplan = request.getParameter("adplan");
        String adprice = request.getParameter("adprice");
        String adstarttime = request.getParameter("adstarttime");
        String adendtime = request.getParameter("adendtime");     
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strTimestamp = now.format(formatter);
      
       int a= RestaurantService.restaurantuploadad(restaurantId,adplan,adprice,adstarttime,adendtime,strTimestamp,adcheck);
              
        Gson gson = new Gson();
		String jsonStr = gson.toJson(a);
		response.getWriter().write(jsonStr);

		
   }
}
