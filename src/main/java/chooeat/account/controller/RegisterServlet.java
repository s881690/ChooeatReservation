package chooeat.account.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import chooeat.account.model.vo.AccountVo;
import chooeat.account.service.AccountService;
@WebServlet("/account/register")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID =1L; 
	
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
		//-------------------------------- 拿到 json字串--------------------------------------------------		
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
				String json = "";
				if (br != null) {
					json = br.readLine();
				}
				System.out.println(json); 
				String regusername = null;
				String regpassword = null;
				String regname = null;
				String regnickname = null;
				String regmail = null;
				String regbirth = null;
				String regphone = null;
				String regadd = null;
				String regadd2 = null;
				String regadd3 = null;
				String reggender = null;
		//--------------------拿到帳密資料-----------------------------------------------------
				JSONObject regObj = new JSONObject(json);
				regusername = regObj.get("regusername").toString();
				regpassword = regObj.get("regpassword").toString();
				regname = regObj.get("regname").toString();
				regnickname = regObj.get("regnickname").toString();
				regmail = regObj.get("regmail").toString();
				regbirth = regObj.get("regbirth").toString();
				regphone = regObj.get("regphone").toString();
				regadd = regObj.get("regadd").toString();
				regadd2 = regObj.get("regadd2").toString();
				regadd3 = regObj.get("regadd3").toString();
				reggender = regObj.get("reggender").toString();
//		//---------------------獲取輸入的帳號和密碼------------------------------------------------------
//				System.out.println(regusername);
//				System.out.println(regpassword);
//				System.out.println(regname);
//				System.out.println(regnickname);
//				System.out.println(regmail);
//				System.out.println(regbirth);
//				System.out.println(regphone);
//				System.out.println(regadd);
//				System.out.println(regadd2);
//				System.out.println(regadd3);
//				System.out.println(reggender);
				List<AccountVo> accountList = new ArrayList<>();
				AccountVo vo = new AccountVo();
				vo.setAcc_acc(regusername);
				vo.setAcc_pass(regpassword);
				vo.setAcc_name(regname);
				vo.setAcc_nickname(regnickname);
				vo.setAcc_mail(regmail);
				vo.setAcc_birth(regbirth);
				vo.setAcc_phone(regphone);
				vo.setAcc_add1(regadd);
				vo.setAcc_add2(regadd2);
				vo.setAcc_add3(regadd3);
				vo.setAcc_gender(reggender);
				accountList.add(vo);

				
				
				if (accountList.isEmpty()) {
				    res.getWriter().write("資料為空");
				    
				} else {
				    int a = accountService.register(accountList);
				    if (a == 1) {
				        res.getWriter().write("資料重複");
				    } else {
				        res.getWriter().write("註冊成功");
				    }
				}

			
		//----------------------GetSession---------------------------------------------------		
		//	req.getSession().setAttribute("member", member);
		//---------------------回傳資料給前端-----------------------------------------------------		
//
//			Gson gson = new Gson();
//			String jsonStr = gson.toJson(member);
//			res.getWriter().write(jsonStr);	
//		

				
	
	
	}



}
