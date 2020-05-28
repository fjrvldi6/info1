package web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cal4")
public class Calculator4 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 전달된 POST parameter 파싱
		String value = req.getParameter("value");
		String operator = req.getParameter("operator");
		
		// Cookie 파싱 
		String exp = "";
		Cookie[] cookies = req.getCookies();
		
		if(cookies!=null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}			
			}
		}
		
		if(operator!=null && operator.equals("=")) {
			// "=" 연산자일 때	-> 계산
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} else {
			// 문자열에 add
			exp += (value==null)?"":value;
			exp += (operator==null)?"":operator;			
		}
		
		// Cookie 생성 및 추가
		Cookie cookie = new Cookie("exp", exp);
		
		if(operator!=null && operator.equals("C")) {
			cookie.setMaxAge(0);
		}
		
		resp.addCookie(cookie);
		
		// /calpage로 redirect
		resp.sendRedirect("calpage");
			
	}	

}
