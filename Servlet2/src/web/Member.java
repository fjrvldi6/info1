package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/parse")
public class Member extends HttpServlet {
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3307/databasetest?serverTimezone=UTC";		
		
		int flag = 0;
		String div = req.getParameter("div");
		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		String[] depts = req.getParameterValues("dept");
		String gender = req.getParameter("gender");
		String birth = req.getParameter("birth");
		String intro = req.getParameter("introduction");

		List<String> db_id = new ArrayList<>();
		List<String> db_name = new ArrayList<>();
		List<String> db_tel = new ArrayList<>();
		List<String> db_email = new ArrayList<>();
		List<String> db_school = new ArrayList<>();
		List<String> db_gender = new ArrayList<>();
		List<String> db_season = new ArrayList<>();
		List<String> db_introduce = new ArrayList<>();

		
		try {
			
			Class.forName(jdbc_driver).newInstance();		// DB 로드
			Connection con = DriverManager.getConnection(jdbc_url, "root", "비밀번호");		// DB와 연결
			
			Statement st = con.createStatement();

			
			
			//	테이블 생성 코드 (테이블 여부에 따라서 주석 처리)
			
			String db = "CREATE TABLE memberform("
					+ "id VARCHAR(45) NOT NULL PRIMARY KEY, password VARCHAR(45) NOT NULL, name VARCHAR(45) NOT NULL, tel VARCHAR(45) NOT NULL, "
					+ "email VARCHAR(45) NOT NULL, school VARCHAR(45) NOT NULL, gender VARCHAR(60) NOT NULL, season VARCHAR(60) NOT NULL, introduce VARCHAR(60) NOT NULL)";
			
			st.executeUpdate(db);
			
			//	여기까지 주석처리 하시면	
			
			PreparedStatement st1;
			
			
			String sql = "SELECT * FROM databasetest.memberform";
			ResultSet rs = st.executeQuery(sql);
			
			
			
			if( div.equals("전송") ) {	// 전송 버튼 눌렀을 때
				
				while( rs.next() ) {	// 데이터베이스에 저장된 값 가져와서 같은 값이 있는지 확인
					String con_id = rs.getString("id");
					String con_name = rs.getString("name");
					String con_pass = rs.getString("password");
					
					if( con_id.equals(id) && con_name.equals(name) ) {
						if( con_pass.equals(password) ) {	//아이디와 이름이 같으며, 비밀번호도 같을 때 데이터를 업데이트

							flag = 1;
							
							st1 = con.prepareStatement("UPDATE databasetest.memberform SET id=?, password=?, name=?, tel=?, email=?, "
							+" school=?, gender=?, season=?, introduce=? WHERE id=? AND password=? AND name=? ");
							
							st1.setString(1, id);
							st1.setString(2, password);
							st1.setString(3, name);
							st1.setString(4, tel);
							st1.setString(5, email);
							st1.setString(6, depts[0]);
							st1.setString(7, gender);
							st1.setString(8, birth);
							st1.setString(9, intro);
							st1.setString(10, id);
							st1.setString(11, password);
							st1.setString(12, name);
							st1.executeUpdate();
							
							System.out.println("flag가 1의 업데이트가 들어감.");
							
							break;
						}
						else {	// 아이디와 이름이 같지만, 비밀번호가 틀렸을 때
							flag = 2;
							break;
						}
					}

				}
				
				
				if( flag == 0 ) {	// 아이디와 이름이 처음 등록된 값일 때
					
					st1 = con.prepareStatement("INSERT INTO databasetest.memberform VALUES(?,?,?,?,?,?,?,?,?)");

					st1.setString(1, id);
					st1.setString(2, password);
					st1.setString(3, name);
					st1.setString(4, tel);
					st1.setString(5, email);
					st1.setString(6, depts[0]);
					st1.setString(7, gender);
					st1.setString(8, birth);
					st1.setString(9, intro);
					st1.executeUpdate();
				}
				
			}
			else if( div.equals("DB 보기") ) {	// DB 보기 버튼을 눌렀을 때
				flag = 3;
			
				while( rs.next() ) {
					
					db_id.add(rs.getString("id"));
					db_name.add(rs.getString("name"));
					db_tel.add(rs.getString("tel"));
					db_email.add(rs.getString("email"));
					db_school.add(rs.getString("school"));
					db_gender.add(rs.getString("gender"));
					db_season.add(rs.getString("season"));
					db_introduce.add(rs.getString("introduce"));

				}
				
			}
			else if( div.equals("DB 삭제") ) {	// DB 삭제 버튼을 눌렀을 때
				flag = 4;
				st1 = con.prepareStatement("DELETE FROM databasetest.memberform");
				st1.executeUpdate();
				
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}

		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>get</title></head>");
		out.print("<body>");
		out.print("<h1>GET 방식으로 요청되었습니다</h1>");
		
		if( flag == 2 ) {
			out.print("비밀번호가 틀렸습니다.");
		}
		else if( flag == 4 ) {
			out.print("DB 정보가 모두 삭제되었습니다.");
		}
		else if( flag == 3 ) {			
			
			for( int i = 0; i < db_id.size(); i++) {
				out.print("< " + (i+1) + " 번째 >     아이디 : " + db_id.get(i) + "  /   이름 : " + db_name.get(i) + "  /   핸드폰번호 : " + db_tel.get(i) + "  /   이메일 : " + db_email.get(i) 
				+ "  /   학부 : " + db_school.get(i) + "  /   성별 : " + db_gender.get(i) + "  /   생일 : " + db_season.get(i) + "  /   소개 : " + db_introduce.get(i) + " <br><br>");
			}
			
		}
		else {
			out.print("아이디 : " + id + "<br/>");
			out.print("이름 : " + name + "<br/>");
			out.print("핸드폰번호 : " + tel + "<br/>");
			out.print("이메일 : " + email + "<br/>");
			out.print("학부 : ");
			for (int i = 0; i < depts.length; i++) {
				out.print(depts[i] + " ");
			}
			out.print("<br/>");
			out.print("성별 : " + gender + "<br/>");
			out.print("생일 : " + birth + "<br/>");
			out.print("소개 : " + intro + "<br/>");
			
			if( flag == 0 ) {
				out.print("<br/><br/> 저장에 성공했습니다. <br/><br/>");
			}
			else if( flag == 1 ) {
				out.print("<br/><br/> 업데이트에 성공했습니다. <br/><br/>");
			}
		}

		out.println("</body></html>");
		out.close();
		
	}
}
