<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<body>
<%@include file="/WEB-INF/views/include/menu.jsp" %>
<h1>교환</h1>
<form id="viewForm" action="/exchange/del" method="post">
	<input type="hidden" name="_id" value="${exchange._id }">
	type:${exchange.type }<br>
	myR:${exchange.myR }-exW:${exchange.exW }<br>
	myW:${exchange.myW }-exR:${exchange.exR }<br>
	기간:${exchange.fromDt }-toDt:${exchange.toDt }<br>
	전체/일부:${exchange.fullYn }<br>
</form>
<button onclick="list()">목록</button>
<button onclick="edit()">수정</button>
<button onclick="del()">삭제</button>
</body>
<script>
function list(){
	location.href="/exchange";
}
function edit(){
	alert("개발중");return false;
	location.href="/exchange/edit";
}
function del(){
	$("#viewForm").prop("action", "/exchange/del");
	$("#viewForm").submit();
}
</script>
</html>