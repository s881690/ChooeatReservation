package chooeat.admin.web.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import chooeat.admin.web.admin.pojo.AdminVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
//@WebFilter(filterName = "", urlPatterns = { "/admin" })
//@Order(value = 1)
public class AdminIsLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null) {            
            AdminVO admin = (AdminVO) session.getAttribute("admin");
//            System.out.println(admin.getAdminId());
            
            if(admin == null) {
            	res.sendRedirect("/admin_login.html");
            } else {
            	chain.doFilter(request, response);
            }
        } 

        chain.doFilter(request, response);

	}

}
