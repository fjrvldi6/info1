<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <div id="body">
		<div class="content-container clearfix">
		<main class="main">
			<h2 class="main title">DBP 학생 정보</h2>
			
			<div class="notice margin-top">
				<%
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String school = request.getParameter("school");
				String gender = request.getParameter("gender");
				%>
				아이디: <%=id%> <br>
				이름: <%=name%> <br>
				학과: <%=school%> <br>
				성별: <%=gender%> <br>
			</div>
			
			<div class="search-form margin-top first align-right">
			<input type="submit" onclick="location.href='list.jsp'" value="목록" />
			<input type="submit" onclick="location.href='pwcheck.jsp?id=<%=id%>'" value="수정" />

			</div>

		</main>
		</div>
	</div>

    </body>
    
    </html>