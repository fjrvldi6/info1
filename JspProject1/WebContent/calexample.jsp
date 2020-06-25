<%@page import="javax.script.ScriptException"%>
<%@page import="javax.script.ScriptEngineManager"%>
<%@page import="javax.script.ScriptEngine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	// 전달된 POST parameter 파싱
			String value = request.getParameter("value");
			System.out.print(value);
			String operator = request.getParameter("operator");
			
			// Cookie 파싱 
			String exp = "";
			Cookie[] cookies = request.getCookies();
			
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
				System.out.print(exp);
			}
			
			// Cookie 생성 및 추가
			Cookie cookie = new Cookie("exp", exp);
			
			if(operator!=null && operator.equals("C")) {
				cookie.setMaxAge(0);
			}
			
			response.addCookie(cookie);
			
			// /jspcalpage.jsp로 redirect
			response.sendRedirect("myjspexample1.jsp");
	%>

</body>
</html>