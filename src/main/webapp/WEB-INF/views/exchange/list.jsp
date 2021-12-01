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
			<th>교환</th>
			<th>fullYn</th>
			<th>useYn</th>
			<th>기간</th>
			<th>매칭</th>
			<th>매칭상태</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="row" items="${exchangeList }">
		<tr>
			<td><a href="/exchange/view?_id=${row._id }">${row._id }</a></td>
			<td>${row.type }</td>
			<td>
				<c:if test="${row.myR ne 0 }">Red ${row.myR }개 -> White ${row.exW }개</c:if>
				<c:if test="${row.myW ne 0 }">White ${row.myW }개 -> Red ${row.exR }개</c:if>
			</td>
			<td>${row.fullYn eq 'Y' ? '일괄' : '부분'}</td>
			<td>${row.useYn }</td>
			<td>${row.fromDt } ~ ${row.toDt }</td>
			<td>
				<c:if test="${row.matchingStatus eq 'S' || row.matchingStatus eq 'RS' }">
					매칭성공
				</c:if>
				<c:if test="${row.matchingStatus ne 'S' && row.matchingStatus ne 'RS' }">
					<button onclick="matching('${row._id }')">매칭</button>
				</c:if>
			</td>
			<td>${row.matchingStatus }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
<script>
function matching(_id){
	var popup = window.open("/exchange/matching?_id="+_id, "matching", "width=900,height=400");
}
</script>
</html>