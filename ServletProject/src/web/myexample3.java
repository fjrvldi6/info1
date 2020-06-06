package web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/my3")
public class myexample3 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String v_ = req.getParameter("value");
		int v = 0;
		v = Integer.parseInt(v_);
		String op = req.getParameter("operator");

		ServletContext application = req.getServletContext();
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		// 계산
		if(op.equals("=")) {
			int result = 0;
			//int x = (int) application.getAttribute("value");
			//String operator = (String)application.getAttribute("op");
			//int x = (int) session.getAttribute("value");
			//String operator = (String)session.getAttribute("op");
			
			int x = 0;
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());					
				}	
				if(c.getName().equals("op")) {
					operator = c.getValue();					
				}	
			}
			
			int y = v;
			if(operator.equals("+"))
				result = x + y;
			else
				result = x - y;
					
				resp.getWriter().printf("result is %d\n", result);
			}			
		else{
			// 값을 저장
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			// cookie는 문자열만 저장됨 (JSON, XML 사용 가능)
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setMaxAge(60*60);
			resp.addCookie(valueCookie);
			resp.addCookie(opCookie);
			
			resp.sendRedirect("Calculator2.html");
			
		}	
		
	}	

}