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
<h1>교환</h1>
<a href="/exchange/add">Add</a>
<table border="1">
	<thead>
		<tr>
			<th>순번</th>
			<th>type</th>
			<th>userId</th>
			<th>myR</th>
			<th>myW</th>
			<th>exR</th>
			<th>exW</th>
			<th>fullYn</th>
			<th>useYn</th>
			<th>fromDt</th>
			<th>toDt</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="row" items="${exchangeList }">
		<tr>
			<td><a href="/exchange/view?_id=${row.get()._id }">${row.get()._id }</a></td>
			<td>${row.get().type }</td>
			<td>${row.get().userId }</td>
			<td>${row.get().myR }</td>
			<td>${row.get().myW }</td>
			<td>${row.get().exR }</td>
			<td>${row.get().exW }</td>
			<td>${row.get().fullYn }</td>
			<td>${row.get().useYn }</td>
			<td>${row.get().fromDt }</td>
			<td>${row.get().toDt }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>