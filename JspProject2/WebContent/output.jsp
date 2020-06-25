<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>
<%

String flag = request.getParameter("flag");

switch(flag){
	case "fail":
		out.write("ID, PWD 불일치 오류");
		break;
	case "exist":
		out.write("아이디 존재");
		break;
	case "join":
		out.write("회원가입 성공");
		break;

}
%>
</h3>
<hr>
<%

switch(flag){
	case "fail":
		out.write("비밀번호가 일치하지 않거나 존재하지 않는 ID입니다.");
		break;
	case "exist":
		out.write("아이디가 존재합니다. 다시 시도해주십시오.");
		break;
	case "join":
		out.write("회원가입을 축하드립니다.");
		break;
}
%>
</body>
</html>