package chooeat.restaurant.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import chooeat.restaurant.service.RestaurantService;
@WebServlet("/restaurant/getregisterform")
public class GetRegisterForm extends HttpServlet {
	 @Autowired
	    private RestaurantService RestaurantService;
	  @Autowired
	    private ResourceLoader resourceLoader;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		response.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		request.setCharacterEncoding("utf-8");
	    Resource resource = resourceLoader.getResource("classpath:static/restaurant/Restaurantregisterform.html");
	    InputStream inputStream = resource.getInputStream();
        byte[] fileContent = inputStream.readAllBytes();

        response.setContentType("text/html;charset=UTF-8");
        response.setContentLength(fileContent.length);
        response.getOutputStream().write(fileContent);
    }
}
