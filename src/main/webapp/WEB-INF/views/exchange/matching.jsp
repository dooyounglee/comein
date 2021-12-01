<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<body>
	_id:${exchange._id }
	<br> type:${exchange.type eq 'R' ? '랜덤' : '지정' }
	<br> myR:${exchange.myR }-exW:${exchange.exW }
	<br> myW:${exchange.myW }-exR:${exchange.exR }
	<br> 기간:${exchange.fromDt }-${exchange.toDt }
	<br> 전체/일부:${exchange.fullYn }
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>type</th>
				<th>교환</th>
				<th>fullYn</th>
				<th>useYn</th>
				<th>기간</th>
				<th>매칭요청</th>
				<th>매칭상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${matching }">
				<tr>
					<td>${row._id }</td>
					<td>${row.type }</td>
					<td><c:if test="${row.myR ne 0 }">Red ${row.myR }개 -> White ${row.exW }개</c:if>
						<c:if test="${row.myW ne 0 }">White ${row.myW }개 -> Red ${row.exR }개</c:if>
					</td>
					<td>${row.fullYn eq 'Y' ? '일괄' : '부분'}</td>
					<td>${row.useYn }</td>
					<td>${row.fromDt }~ ${row.toDt }</td>
					<td>
						<c:choose>
						<c:when test="${row.matchingStatus eq 'R' }">
							<button onclick="acceptMatching('${row._id }')">수락</button>
						</c:when>
						<c:when test="${row.matchingStatus eq 'W' }">
							수락대기
						</c:when>
						<c:when test="${row.matchingStatus eq 'S' }">
							수락
						</c:when>
						<c:otherwise>
							<button onclick="requestMatching('${row._id }')">매칭요청</button>
						</c:otherwise>
						</c:choose>
					</td>
					<td>${row.matchingStatus }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		function requestMatching(toMatchingId) {
			let fromMatchingId = '${exchange._id }';

			$.ajax({
			    url: "/exchange/requestMatching", // 호출할 주소
			    type: "POST",
			    data: { fromMatchingId: fromMatchingId,
			    		toMatchingId : toMatchingId }, // 넘길 데이터
			    dataType: "json", // 데이터 타입 json으로 설정 <- 이걸 안하면 밑에 처럼 JSON.parse를 해야함
			    success: function(data) { // 결과 받기
			        //data = JSON.parse(data); // JSON 형태로 파싱
			        console.log(data);
			        opener.send(JSON.stringify(data));
			        //opener.location.reload();
			        //window.close();
			    }
			});
		}
		
		function acceptMatching(toMatchingId) {
			let fromMatchingId = '${exchange._id }';

			$.ajax({
			    url: "/exchange/acceptMatching", // 호출할 주소
			    type: "POST",
			    data: { fromMatchingId: fromMatchingId,
		    		toMatchingId : toMatchingId }, // 넘길 데이터
			    dataType: "json", // 데이터 타입 json으로 설정 <- 이걸 안하면 밑에 처럼 JSON.parse를 해야함
			    success: function(data) { // 결과 받기
			        //data = JSON.parse(data); // JSON 형태로 파싱
			        console.log(data);
			        opener.send(JSON.stringify(data));
			        //opener.location.reload();
			        //window.close();
			    }
			});
		}
	</script>
</body>
</html>