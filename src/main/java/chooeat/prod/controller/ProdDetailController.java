package chooeat.prod.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import chooeat.prod.dao.impl.ProdCommentReportDaoImpl;
import chooeat.prod.dao.impl.ProdDaoImpl;
import chooeat.prod.model.vo.OrderDetail;
import chooeat.prod.model.vo.Prod;
import chooeat.prod.model.vo.ProdCommentReport;
import chooeat.prod.service.impl.OrderDetailServiceImpl;
import chooeat.prod.service.impl.ProdServiceImpl;

@WebServlet("/prod/prod/details")
public class ProdDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProdDaoImpl prodDaoImpl;
	@Autowired
	private OrderDetailServiceImpl orderDetailServiceImpl;
	@Autowired
	private ProdCommentReportDaoImpl prodCommentReportDaoImpl;

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
		int prodId = Integer.parseInt(req.getParameter("id"));

		List<OrderDetail> orderDetails = orderDetailServiceImpl.selectByProdId(prodId);
		Prod prod1 = prodDaoImpl.selectByProdId(prodId);
		Gson gson = new Gson();
		String prodJsonStr = gson.toJson(prod1);
		String orderDetailsJsonStr = gson.toJson(orderDetails);

		PrintWriter out = res.getWriter();
		out.print("{\"prod\": " + prodJsonStr + ", \"orderDetails\": " + orderDetailsJsonStr + "}");
		out.flush();
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

		// 讀取請求
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = req.getReader()) {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}
		// 解析 JSON 字串
		JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
		String reason = json.get("reason").getAsString();
		int orderDetailId = Integer.parseInt(json.get("orderDetailId").getAsString());
		System.out.println(reason);
		System.out.println(orderDetailId);
		ProdCommentReport prodCommentReport = new ProdCommentReport();
		prodCommentReport.setAccId(1);
		prodCommentReport.setOrderDetailId(orderDetailId);
		prodCommentReport.setProdCommentReportReason(reason);

		int prodCommentReportId = prodCommentReportDaoImpl.insert(prodCommentReport);
		if (prodCommentReportId != -1) {
			System.out.println("評論檢舉成功！PROD_COMMENT_REPORT_ID為：" + prodCommentReportId);
		} else {
			System.out.println("無法建立評論檢舉。");
		}

		JsonObject responseJson = new JsonObject();
		responseJson.addProperty("message", "檢舉成功");
		String jsonResponse = responseJson.toString();
		res.getWriter().write(jsonResponse);
	}

}
