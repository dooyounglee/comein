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
<form id="addForm" action="/exchange/add" method="post">
	<input type="hidden" name="userId" value="${loginUser._id }">
	type:<select name="type">
			<option value="A">랜덤</option>
			<option value="B">지정</option>
		</select><br>
	myR:<select name="myR">
			<option value="-1">선택</option>
			<c:forEach var="i" begin="0" end="14">
			<option value=${i }>${i }개</option>
			</c:forEach>
		</select>-
	exW:<select name="exW">
			<option value="-1">선택</option>
			<c:forEach var="i" begin="0" end="14">
			<option value=${i }>${i }개</option>
			</c:forEach>
		</select><br>
	myW:<select name="myW">
			<option value="-1">선택</option>
			<c:forEach var="i" begin="0" end="14">
			<option value=${i }>${i }개</option>
			</c:forEach>
		</select>-
	exR:<select name="exR">
			<option value="-1">선택</option>
			<c:forEach var="i" begin="0" end="14">
			<option value=${i }>${i }개</option>
			</c:forEach>
		</select><br>
	fromDt:<input type="text" name="fromDt" placeholder="20200101"><br>
	toDt:<input type="text" name="toDt" placeholder="20200101"><br>
	전체/일부:<select name="fullYn">
			<option value="Y">전체</option>
			<option value="N">일부</option>
		</select><br>
	<input type="hidden" name="useYn" value="Y">
</form>
<button onclick="add()">add</button>
</body>
<script>
function add(){
	if(!validate())return;
	$("#addForm").submit();
}
function validate(){
	if($("select[name=myR]").val() != "-1" && $("select[name=exW]").val() != "-1"){
		$("select[name=myW]").val(0);$("select[name=exR]").val(0);
		return true;
	}
	if($("select[name=myW]").val() != "-1" && $("select[name=exR]").val() != "-1"){
		$("select[name=myR]").val(0);$("select[name=exW]").val(0);
		return true;
	}
	alert("수량을 체크하세요");
	return false;
}
</script>
</html>