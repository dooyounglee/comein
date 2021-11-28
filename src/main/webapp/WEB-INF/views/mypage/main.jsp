<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/views/include/menu.jsp" %>
<h1>마이페이지</h1>

<h2>나의 교환</h2>
<table border="1">
	<thead>
		<tr>
			<th>순번</th>
			<th>type</th>
			<th>교환</th>
			<th>fullYn</th>
			<th>useYn</th>
			<th>기간</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${exchangeList }">
	<c:set var="row" value="${item.get() }"/>
		<tr>
			<td>${row._id }</td>
			<td>${row.type eq 'R' ? '랜덤' : '지정' }</td>
			<td>
				<c:if test="${row.myR ne 0 }">Red ${row.myR }개 -> White ${row.exW }개</c:if>
				<c:if test="${row.myW ne 0 }">White ${row.myW }개 -> Red ${row.exR }개</c:if>
			</td>
			<td>${row.fullYn eq 'Y' ? '일괄' : '부분'}</td>
			<td>${row.useYn }</td>
			<td>${row.fromDt } ~ ${row.toDt }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>