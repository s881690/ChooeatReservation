package chooeat.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.prod.dao.impl.OrderDetailDaoImpl;
import chooeat.prod.model.vo.OrderDetail;
import chooeat.prod.service.impl.OrderDetailServiceImpl;

@WebServlet("/prod/prod/commentSort")
public class ProdCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderDetailDaoImpl orderDetailDaoImpl;
	@Autowired
	private OrderDetailServiceImpl orderDetailServiceImpl;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
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
		String sortParam = req.getParameter("sort");
		int prodId = Integer.parseInt(req.getParameter("id"));
		List<OrderDetail> list = null;
		if ("all".equals(sortParam)) {
			list = orderDetailServiceImpl.selectByProdId(prodId);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(list);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.flush();
		} else {
			list = orderDetailServiceImpl.selectByProdId(prodId);
			// 根據評分篩選評論
			int filterScore = Integer.parseInt(sortParam); // 將 sortParam 轉換為整數
			List<OrderDetail> filteredProducts = new ArrayList();
			for (OrderDetail orderDetail : list) {
				if (orderDetail.getProdCommentScore() == filterScore) {
					filteredProducts.add(orderDetail);
				}
			}

			Gson gson = new Gson();
			String jsonStr = gson.toJson(filteredProducts);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.flush();
		}

	}
}
