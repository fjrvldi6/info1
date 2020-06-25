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
	import="java.sql.Statement"
	import="java.util.ArrayList"
	import="java.util.List"	%>
	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>    
        #visual .content-container{	
            height:inherit;
            display:flex; 
            align-items: center;
        }
    </style>
</head>

<body>
    <!-- --------------------------- <body> --------------------------------------- -->
    
<%

String jdbc_driver = "com.mysql.cj.jdbc.Driver";
String jdbc_url = "jdbc:mysql://localhost:3307/databasetest?serverTimezone=UTC";

List<String> db_id = new ArrayList<>();
List<String> db_name = new ArrayList<>();
List<String> db_school = new ArrayList<>();
List<String> db_gender = new ArrayList<>();

try {
	
	Class.forName(jdbc_driver).newInstance();		// DB 로드
	Connection con = DriverManager.getConnection(jdbc_url, "root", "비밀번호");		// DB와 연결
	
	Statement st = con.createStatement();
	
	String sql = "SELECT * FROM databasetest.memberform";
	ResultSet rs = st.executeQuery(sql);
	
	while( rs.next() ) {	// 데이터베이스에 저장된 id와 name 가져와서 리스트에 저장
		db_id.add(rs.getString("id"));
		db_name.add(rs.getString("name"));
		db_school.add(rs.getString("school"));
		db_gender.add(rs.getString("gender"));
	}

	rs.close();
	st.close();
	con.close();
	
}
catch (Exception e){
	e.printStackTrace();
}

%>

	<div id="body">
		<div class="content-container clearfix">
		<main class="main">
			<h2 class="main title">DBP 학생 목록</h2>
			
			<div class="notice margin-top">				
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>ID</th>
							<th>&nbsp;이름</th>
							<th>&nbsp;학과</th>
						</tr>
					</thead>
					<tbody>
						
					<%
					for( int i=0; i<db_id.size(); i++){
					%>	
					<tr>
						<td><%=i+1%></td>
						<td><a href="detail.jsp?id=<%=db_id.get(i)%>&name=<%=db_name.get(i)%>&school=<%=db_school.get(i)%>&gender=<%=db_gender.get(i)%>">
						<%=db_id.get(i)%></a></td>
						<td>&nbsp;&nbsp; <%=db_name.get(i)%></td>
						<td>&nbsp;&nbsp; <%=db_school.get(i)%></td>												
					</tr>
					<%}%>

					</tbody>
					
				</table>
			</div>

			<div class="search-form margin-top first align-right">
			<br>	
				<form action="yourname" method=get>
					<fieldset>
						<legend class="hidden">학생 분류</legend>
						<label class="hidden">학과</label>
						<select name="f">
							<option name="school" value="전체">전체</option>
							<option name="school" value="컴퓨터공학부">컴퓨터공학부</option>
							<option name="school" value="정보통신학부">정보통신학부</option>
							<option name="school" value="IT콘텐츠학과">IT콘텐츠학과</option>
							<option name="school" value="국어국문학과">국어국문학과</option>		
						</select>
						<input type="submit" name="button" value="검색" />
					</fieldset>		
				</form>
			</div>
		</main>		
		</div>
	</div>

    </body>
    
    </html>