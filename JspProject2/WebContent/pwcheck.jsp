<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
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
<h3>본인 여부 확인</h3>
<hr>
<form name="fo" method="get">

<%

String jdbc_driver = "com.mysql.cj.jdbc.Driver";
String jdbc_url = "jdbc:mysql://localhost:3307/databasetest?serverTimezone=UTC";

String id = request.getParameter("id");
String pwd ="";
System.out.println(id);

try {
	
	Class.forName(jdbc_driver).newInstance();		// DB 로드
	Connection con = DriverManager.getConnection(jdbc_url, "root", "비밀번호");		// DB와 연결
	
	Statement st = con.createStatement();


	st.close();
	con.close();
}
catch (Exception e){
	e.printStackTrace();
}

%>
사용자 ID : <%=id%>	<br>
비밀 번호 : <input type="password" name="" value=""><br>
        <input type="submit" value="수정">
           

</form>
</body>
</html>