package chooeat.prod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.prod.dao.impl.ProdDaoImpl;
import chooeat.prod.model.vo.CartItem;
import chooeat.prod.model.vo.Prod;
import redis.clients.jedis.Jedis;

@WebServlet("/prod/get-cart")
public class AddToCartContronller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ProdDaoImpl prodDaoImpl;

	private Jedis jedis;

	@Override
	public void init() throws ServletException {
		jedis = new Jedis("localhost", 6379);
		jedis.select(1);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設置接收格式
		req.setCharacterEncoding("utf-8");
		// 設置跨域
		res.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		res.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		res.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		res.setContentType("application/json; charset=utf-8");
		// 判斷請求中的操作類型
		String operation = req.getParameter("operation");
		System.out.println(operation);
		String productId = req.getParameter("productId");
		System.out.println(productId);
		String productName = req.getParameter("productName");
		System.out.println(productName);
		int price = Integer.parseInt(req.getParameter("price"));
		System.out.println(price);
		int qty = Integer.parseInt(req.getParameter("qty"));
		System.out.println(qty);
		int accId = Integer.parseInt(req.getParameter("accId"));
		System.out.println(accId);
		String cartKey = "cart:user" + accId; // 先假設使用者ID為user1，實際要再多增加使用者這個參數
		switch (operation) {
		case "deleteSelected":
			// 刪除
			jedis.select(1);
			jedis.hdel(cartKey, "productId:" + productId);
			res.getWriter().println("{\"status\": \"success\"}");
			break;
		case "updateQuantity":
			// 更新數量
			String cartItemJson = "name:" + productName + ", price:" + price + ", qty:" + qty;
			System.out.println(cartItemJson);
			jedis.select(1);
			jedis.hset(cartKey, "productId:" + productId, cartItemJson);
			res.getWriter().println("{\"status\": \"success\"}");
			break;
		case "addcart":
			jedis.select(1);
			jedis.hset(cartKey, "productId:" + productId, "name:" + productName + ", price:" + price + ", qty:" + qty);
			res.getWriter().println("{\"status\": \"success\"}");
			break;
		default:
			System.out.println("又壞了哀");
			break;
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設置接收格式
		req.setCharacterEncoding("utf-8");
		// 設置跨域
		res.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		res.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		res.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		res.setContentType("application/json; charset=utf-8");
		int accId = Integer.parseInt(req.getParameter("accId"));
		String cartKey = "cart:user" + accId;

		jedis.select(1);
		Map<String, String> cartData = jedis.hgetAll(cartKey);
		if (accId != 0) {
	        String oldCartKey = "cart:user0";
	        Map<String, String> oldCartData = jedis.hgetAll(oldCartKey);
	        cartData.putAll(oldCartData);
	        jedis.del(oldCartKey);
	    }
		Map<String, CartItem> cartItems = new HashMap<>();

		for (Map.Entry<String, String> entry : cartData.entrySet()) {
			String productId = entry.getKey();
			String cartItemJson = entry.getValue();
			String redisValue = productId + ", " + cartItemJson;

			// 使用 selectByProdId 方法根據 prodId 取得商品資訊
			String[] parts = productId.split(":");
			String numberPart = parts[1].trim();
			Integer prodId = Integer.parseInt(numberPart);
			Prod product = prodDaoImpl.selectByProdId(prodId);

			// 取得圖片的 byte[] 數組
			byte[] imageBytes = product.getProdPic();

			CartItem cartItem = convertToCartItem(redisValue);
			cartItem.setProdPic(imageBytes); // 將商品圖片路徑設定到 CartItem 中
			cartItems.put(extractProductId(productId), cartItem);
		}

		String json = new Gson().toJson(cartItems);
		res.getWriter().write(json);
	}

	private static String extractProductId(String productId) {
		Pattern pattern = Pattern.compile("productId:(\\d+)");
		Matcher matcher = pattern.matcher(productId);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null; // 無法找到productId，返回null或其他適當的預設值
		}
	}

	public static CartItem convertToCartItem(String keyValueString) {
		CartItem cartItem = new CartItem();
		String[] keyValuePairs = keyValueString.split(", ");
		for (String keyValuePair : keyValuePairs) {
			String[] keyValue = keyValuePair.split(":");
			String key = keyValue[0].trim();
			String value = keyValue[1].trim();
			if (key.equals("productId")) {
				cartItem.setProductId(value);
			} else if (key.equals("name")) {
				cartItem.setProductName(value);
			} else if (key.equals("price")) {
				cartItem.setPrice(Double.parseDouble(value));
			} else if (key.equals("qty")) {
				cartItem.setQty(Integer.parseInt(value));
			}
		}
		return cartItem;
	}

	public void destroy() {
		jedis.close();
	}
}
