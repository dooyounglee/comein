<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
_id:${exchange._id }<br>
type:${exchange.type eq 'R' ? '랜덤' : '지정' }<br>
myR:${exchange.myR }-exW:${exchange.exW }<br>
myW:${exchange.myW }-exR:${exchange.exR }<br>
기간:${exchange.fromDt }-${exchange.toDt }<br>
전체/일부:${exchange.fullYn }<br>
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
	<c:forEach var="row" items="${matching }">
		<tr>
			<td>${row._id }</td>
			<td>${row.type }</td>
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