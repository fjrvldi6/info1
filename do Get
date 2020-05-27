package database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.SetContextPropertiesRule;

@WebServlet("/first2")
public class Base extends HttpServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service1 실행");
		super.service(req, res);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 실행");
	
		resp.setCharacterEncoding("UTF-8");		// 2바이트씩 보내도록 인코딩 (한글은 1바이트로 불가능)
		resp.setContentType("text/html; charset=UTF-8");		// 인코딩한 것을 디코딩 해줘라.
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>Test</title></head>");
		out.print("<body><h1>doGet 안녕하세요</h1></body>");
		out.print("</html>");
		out.close();
	}
	
}
