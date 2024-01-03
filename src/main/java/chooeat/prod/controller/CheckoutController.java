package chooeat.prod.controller;

import java.io.BufferedReader;
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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import chooeat.prod.dao.ProdDao;
import chooeat.prod.dao.impl.ProdDaoImpl;
import chooeat.prod.model.vo.CartItem;
import chooeat.prod.model.vo.Prod;
import redis.clients.jedis.Jedis;

@WebServlet("/prod/checkout")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Jedis jedis;
	@Autowired
	private ProdDaoImpl prodDaoImpl;

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

		BufferedReader reader = req.getReader();
		StringBuilder jsonBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			jsonBuilder.append(line);
		}

		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonBuilder.toString(), JsonObject.class);
		String[] selectedProducts = gson.fromJson(jsonObject.get("selectedProducts"), String[].class);
		boolean isDirectBuy = jsonObject.get("isDirectBuy").getAsBoolean();

		HttpSession session = req.getSession();
		session.setAttribute("selectedProducts", selectedProducts);
		session.setAttribute("isDirectBuy", isDirectBuy);

	}

	private Map<String, String> parseProductData(String productData) {
		Map<String, String> productMap = new HashMap<>();
		String[] fields = productData.split(", ");

		for (String field : fields) {
			String[] keyValue = field.split(":");
			String key = keyValue[0].trim();
			String value = keyValue[1].trim();
			productMap.put(key, value);
		}

		return productMap;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json; charset=utf-8");
		
		String accId = req.getParameter("accId");
		String cartKey = "cart:user" + accId; 
		HttpSession session = req.getSession();
		String[] selectedProducts = (String[]) session.getAttribute("selectedProducts");
		BufferedReader reader = req.getReader();
		StringBuilder jsonBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			jsonBuilder.append(line);
		}
		Gson gson = new Gson();
		String[] arrayData = gson.fromJson(jsonBuilder.toString(), String[].class);
		String[] keyArray = new String[selectedProducts.length];

		for (int i = 0; i < selectedProducts.length; i++) {
			keyArray[i] = selectedProducts[i];
		}
		boolean isDirectBuy = (boolean) session.getAttribute("isDirectBuy");
		if (isDirectBuy) {
			int prodId = Integer.parseInt(keyArray[0]);
			Prod prod1 = prodDaoImpl.selectByProdId(prodId);
			String backendData = gson.toJson(prod1);
//			System.out.println(backendData);
			JsonObject data = gson.fromJson(backendData, JsonObject.class);
			byte[] prodPic = gson.fromJson(data.get("prodPic"), byte[].class); // 將 prodPic 轉換為 byte[]
			JsonArray picArray = new JsonArray();
			for (byte b : prodPic) {
				picArray.add(b);
			}

			JsonObject productObject = new JsonObject();
			productObject.addProperty("productId", data.get("prodId").getAsString());
			String productName = data.get("resName").getAsString() + "｜" + data.get("prodName").getAsString();
			productObject.addProperty("productName", productName);
			productObject.addProperty("price", data.get("prodPrice").getAsInt());
			productObject.addProperty("qty", 1);
			productObject.add("prodPic", picArray);
			System.out.println(productObject);
			JsonObject newData = new JsonObject();
			String productId = productObject.get("productId").getAsString();
			newData.add(productId, productObject);
			
			res.getWriter().write(newData.toString());

		} else {
			for (int i = 0; i < selectedProducts.length; i++) {
				keyArray[i] = "productId:" + selectedProducts[i];
			}

			Map<String, CartItem> cartItems = new HashMap<>();
			for (String productKey : keyArray) {
				jedis.select(1);
				String productData = jedis.hget(cartKey, productKey);

				if (productData != null) {
					String productId = productKey;
					String cartItemJson = productData;
					String redisValue = productId + ", " + cartItemJson;
					CartItem cartItem = convertToCartItem(redisValue);
					cartItems.put(extractProductId(productId), cartItem);
					String json = new Gson().toJson(cartItem);
					String[] parts = productId.split(":");
					String numberPart = parts[1].trim();
					Integer prodId = Integer.parseInt(numberPart);
					Prod product = prodDaoImpl.selectByProdId(prodId);

					// 取得圖片的 byte[] 數組
					byte[] imageBytes = product.getProdPic();
					cartItem.setProdPic(imageBytes);

				} else {
					System.out.println("未找到商品：" + productKey);
				}
			}
			String json = new Gson().toJson(cartItems);
			res.getWriter().write(json);
		}
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
}
