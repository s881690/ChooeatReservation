package chooeat.account.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.account.model.vo.AccountVo;
import chooeat.account.service.AccountService;

@WebServlet("/account/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//----------------傳到下一層---------------------------------------------------------	
	@Autowired
	private AccountService accountService;


//--------------------------------------------------------------------------
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 設置跨域
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		// 設置返回格式
		res.setContentType("application/json; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("utf-8");
		// 取得請求參數
//-------------------------------- 拿到 json字串--------------------------------------------------		
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if (br != null) {                          //br 的值不為空的時候 開始讀取第一行
			json = br.readLine();
		}
		System.out.println(json); 
		String username = null;
		String password = null;
		String customCheck = null;
		
		
		
//--------------------拿到帳密資料-----------------------------------------------------
		JSONObject actObj = new JSONObject(json);
		username = actObj.get("username").toString();
		password = actObj.get("password").toString();
		customCheck = actObj.get("checkbox").toString();
//---------------------獲取輸入的帳號和密碼------------------------------------------------------
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(customCheck);
		
		
		if ("1".equals(customCheck)) {
		    System.out.println("success");
		    // 創建 Cookie
		    Cookie cookie = new Cookie("cookieName", username);
		    Cookie cookie1 = new Cookie("cookieName1", password);
		    cookie.setMaxAge(30 * 24 * 60 * 60); // 設定有效期為30天 (單位為秒)
		    cookie.setPath("/"); // 設定可用於整個網站
		    cookie1.setMaxAge(30 * 24 * 60 * 60); // 設定有效期為30天 (單位為秒)
		    cookie1.setPath("/"); // 設定可用於整個網站

		    // 將 Cookie 添加到回應中
		    res.addCookie(cookie);
		    res.addCookie(cookie1);
		} else {
		    System.out.println("successfull");
		    // 清除 Cookie
		    Cookie cookie = new Cookie("cookieName", "");
		    Cookie cookie1 = new Cookie("cookieName1", "");
		    cookie.setMaxAge(0); // 設定有效期為0，即立即過期
		    cookie.setPath("/"); // 設定可用於整個網站
		    cookie1.setMaxAge(0); // 設定有效期為0，即立即過期
		    cookie1.setPath("/"); // 設定可用於整個網站

		    // 將空白的 Cookie 添加到回應中，即清除原有的 Cookie
		    res.addCookie(cookie);
		    res.addCookie(cookie1);
		}

	

		AccountVo member = accountService.login(username, password);
//----------------------GetSession---------------------------------------------------		
		req.getSession().setAttribute("member", member);
//		System.out.println(member);

//---------------------回傳資料給前端-----------------------------------------------------		

		Gson gson = new Gson();
		String jsonStr = gson.toJson(member);
		//System.out.println(jsonStr.length());
		res.getWriter().write(jsonStr);
//		System.out.println(jsonStr);
	}
}


//		a = actObj.getString("username");
//		b = actObj.getString("password");
//		boolean isValidUser = actObj.getBoolean("username");
//
//		if (isValidUser) {
//
//			HttpSession session = req.getSession();
//			session.setAttribute("username", username);
//			res.sendRedirect("usercenter.html");
//		} else {
//			PrintWriter out = res.getWriter();
//			out.println("<html>");
//			out.println("<body>");
//			out.println("<p>Invalid username or password</p>");
//			out.println("</body>");
//			out.println("</html>");
//
//		}

//		System.out.println(req.getInputStream());
//		String a = req.getParameter("username");
//		String b = req.getParameter("password");
//		 System.out.println(req);
//		
//                System.out.println(a);
//                System.out.println(b);
//                

//		Gson gson = new Gson();
//		AccountVo vo = gson.fromJson(req.getReader(), AccountVo.class);
//		req.getSession().setAttribute("member", member.get(0));

//              System.out.println(member);
//            	Gson gson = new  Gson();
//        		System.out.println(member);
//            	String jsonStr = gson.toJson(member);
//        		
//            	System.out.println(jsonStr);
//        		res.getWriter().write(jsonStr);

//	 private boolean isValidUser(String username, String password) {
//	        // TODO: 驗證帳號和密碼是否正確
//	        return true;
//	    }
