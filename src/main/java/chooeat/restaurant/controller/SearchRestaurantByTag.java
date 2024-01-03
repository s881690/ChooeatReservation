package chooeat.restaurant.controller;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import chooeat.Utils.CustomTimeSerializer;
import chooeat.restaurant.model.vo.RestaurantVO;
import chooeat.restaurant.service.RestaurantService;

@WebServlet("/restaurant/searchrestaurantbytag")
public class SearchRestaurantByTag extends HttpServlet {
	 @Autowired
	    private RestaurantService RestaurantService;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 設置跨域
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		response.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		List<RestaurantVO> restaurantList = RestaurantService.searchRestaurantsbytag(request.getParameter("tag"));

		Gson gson = new GsonBuilder().registerTypeAdapter(Time.class, new CustomTimeSerializer()).create();
		String jsonStr = gson.toJson(restaurantList);
		response.getWriter().write(jsonStr);

	}
}