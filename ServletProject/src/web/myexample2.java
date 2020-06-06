package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my2")
public class myexample2 extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>get</title></head>");
		out.print("<body>");
		out.print("<h1>GET 방식으로 요청되었습니다</h1>");

		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		String name = req.getParameter("name");
		String[] depts = req.getParameterValues("dept");
		String gender = req.getParameter("gender");
		String birth = req.getParameter("birth");
		String intro = req.getParameter("introduction");

		out.print("ID : " + id + "<br/>");
		out.print("비밀번호 : " + password + "<br/>");
		out.print("이름 : " + name + "<br/>");
		out.print("학부 : ");
		for (int i = 0; i < depts.length; i++) {
			out.print(depts[i] + " ");
		}
		out.print("<br/>");
		out.print("성별 : " + gender + "<br/>");
		out.print("birth : " + birth + "<br/>");
		out.print("소개 : " + intro + "<br/>");
		out.print("전체 Query 문자열 : " + req.getQueryString());

		out.println("</body></html>");
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>Query 문자열 테스트</title></head>");
		out.print("<body>");
		out.print("<h1>POST 방식으로 요청되었습니다</h1>");

		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		String name = req.getParameter("name");
		String[] depts = req.getParameterValues("dept");
		String gender = req.getParameter("gender");
		String birth = req.getParameter("birth");
		String intro = req.getParameter("introduction");

		out.print("ID : " + id + "<br/>");
		out.print("비밀번호 : " + password + "<br/>");
		out.print("이름 : " + name + "<br/>");
		out.print("취미 : ");
		for (int i = 0; i < depts.length; i++) {
			out.print(depts[i] + " ");
		}
		out.print("<br/>");
		out.print("성별 : " + gender + "<br/>");
		out.print("생일 : " + birth + "<br/>");
		out.print("소개 : " + intro + "<br/>");
		out.print("전체 Query 문자열 : " + req.getQueryString());
		
		out.println("</body></html>");
		out.close();
	}
}