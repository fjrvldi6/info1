<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.io.IOException"
    import="java.io.PrintWriter"
    import="javax.servlet.ServletException"
    import="javax.servlet.annotation.WebServlet"
	import="javax.servlet.http.HttpServlet"
	import="javax.servlet.http.HttpServletRequest"
	import="javax.servlet.http.HttpServletResponse"
	import="java.sql.Connection"
	import="java.sql.DriverManager"
	import="java.sql.PreparedStatement"
	import="java.sql.ResultSet"
	import="java.sql.Statement"	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>DBP 학생관리</h3>
<hr>
<form name="parse" method="get">
사용자 ID : <input type="text" name="id" size="15" value=""><br>
비밀 번호 : <input type="password" name="pwd" size="15" value=""><br>
           <input type="submit" name="div" value="로그인">
           <input type="submit" name="div" value="가입">
</form>

<%

String jdbc_driver = "com.mysql.cj.jdbc.Driver";
String jdbc_url = "jdbc:mysql://localhost:3307/databasetest?serverTimezone=UTC";

try {
	
	Class.forName(jdbc_driver).newInstance();		// DB 로드
	Connection con = DriverManager.getConnection(jdbc_url, "root", "비밀번호");		// DB와 연결
	
	Statement st = con.createStatement();
	String div = request.getParameter("div");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	//	테이블 생성 코드 (테이블 여부에 따라서 주석 처리)
	
	/*
	String db = "CREATE TABLE memberform("
			+ "id VARCHAR(45) NOT NULL, password VARCHAR(45) NOT NULL, name VARCHAR(45) NOT NULL, "
			+ "school VARCHAR(45) NOT NULL, gender VARCHAR(60) NOT NULL)";
	
	st.executeUpdate(db);
	
	*/
	
	String sql = "SELECT * FROM databasetest.memberform";
	ResultSet rs = st.executeQuery(sql);
	
	
	if( div.equals("로그인") ) {	// 로그인 버튼을 눌렀을 때
		
		while( rs.next() ) {	// 데이터베이스에 저장된 ID와 PWD 가져와서 확인
			String db_id = rs.getString("id");
			String db_pwd = rs.getString("password");

			if( id.equals(db_id) && pwd.equals(db_pwd) ) {	// 로그인 성공
				response.sendRedirect("list.jsp");
			}
		}%>
	<jsp:forward page="output.jsp">
		<jsp:param name="flag" value="fail"/>
	</jsp:forward>
	
	<%}

	if( div.equals("가입") ) {	// 가입 버튼을 눌렀을 때
		while( rs.next() ) {	// 데이터베이스에 저장된 ID와 PWD 가져와서 확인
			String db_id = rs.getString("id");

			if( id.equals(db_id) ) {	// 아이디가 존재
			%>
				<jsp:forward page="output.jsp">
					<jsp:param name="flag" value="exist"/>
				</jsp:forward>
				
			<%}
		}
	// 아이디 생성 성공
	
		System.out.println(id);
		System.out.println(pwd);
		
		PreparedStatement st1;
		
		st1 = con.prepareStatement("INSERT INTO databasetest.memberform VALUES (?, ?, ?, ?, ?)");
				
		st1.setString(1, id);
		st1.setString(2, pwd);
		st1.setString(3, "");
		st1.setString(4, "");
		st1.setString(5, "");
		
		st1.executeUpdate();
		
		%>
		
		<jsp:forward page="output.jsp">
			<jsp:param name="flag" value="join"/>
		</jsp:forward>
		
	<%
	
	rs.close();
	st.close();
	st1.close();
	con.close();
	
	}

}
catch (Exception e){
	e.printStackTrace();
}

%>
</body>
</html>