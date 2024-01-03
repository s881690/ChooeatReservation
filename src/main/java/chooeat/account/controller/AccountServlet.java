package chooeat.account.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import chooeat.account.dao.AccountDao;
import chooeat.account.daoimpl.AccountDaoImpl;
import chooeat.account.model.vo.AccountVo;

@WebServlet("/account/friends")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         
                res.setHeader("Access-Control-Allow-Origin", "*"); // ���\�Ӧ۩Ҧ����쪺�ШD
                res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // ���\�� HTTP ��k
                res.setHeader("Access-Control-Allow-Headers", "Content-Type"); // ���\���ШD�Y
                res.setHeader("Access-Control-Allow-Credentials", "true"); // �O�_���\�a�����Ҫ��ШD
             
                res.setContentType("application/json; charset=utf-8");
                
        AccountDao reser = new AccountDaoImpl();
        List<AccountVo> list = null;
        try {
			list = reser.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);
        res.getWriter().write(jsonStr);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    }

}