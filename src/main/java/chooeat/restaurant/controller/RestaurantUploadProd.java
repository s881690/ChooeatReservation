package chooeat.restaurant.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.restaurant.service.RestaurantService;
@MultipartConfig
@WebServlet("/restaurant/restaurantuploadprod")
public class RestaurantUploadProd extends HttpServlet {
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
              
        String key = request.getParameter("key");
        String prodname = request.getParameter("prodname");
        String prodprice = request.getParameter("prodprice");
        String prodnumber = request.getParameter("prodnumber");
        String prodruler = request.getParameter("prodruler");
        String proddiscribe = request.getParameter("proddiscribe");
        String prodstatus = request.getParameter("prodstatus");
        Part prodimagePart = request.getPart("prodimage");
        InputStream prodimageStream = prodimagePart.getInputStream();
        
        // 呼叫Service層的方法，並將參數逐個傳遞
       int aaa= RestaurantService.restaurantuploadprod(key, prodname, prodprice, prodnumber, prodruler, proddiscribe, prodstatus,
                prodimageStream);
                     
        Gson gson = new Gson();
		String jsonStr = gson.toJson(aaa);		
		response.getWriter().write(jsonStr);
		
   }
}
