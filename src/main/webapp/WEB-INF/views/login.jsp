<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/views/include/menu.jsp" %>
<form action="/login" method="post">
	id:<input type="text" name="id">
	<button>login</button>
</form>
</body>
</html>