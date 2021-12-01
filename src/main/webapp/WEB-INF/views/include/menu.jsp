<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<a href="/main">메인</a>
<a href="/mypage">마이페이지</a>
<a href="/exchange">교환</a>
<c:if test="${loginUser ne null && loginUser.name eq 'admin' }">
	<a href="/admin/exchange">교환_관리자</a>
</c:if>

<c:if test="${loginUser ne null }">
	user:${loginUser.name }&nbsp;
	<button onclick="location.href='/logOut'">logout</button><br>
</c:if>

<c:if test="${loginUser eq null }">
	<button onclick="location.href='/login'">login</button>
</c:if>
<br>
<button onclick="send()">반응보내</button>
<hr>
<br>
<script>
<c:if test="${loginUser ne null }">
   const websocket = new WebSocket("ws://<%=request.getServerName()%>:<%=request.getServerPort()%>/ws/chat");

   websocket.onmessage = onMessage;
   websocket.onopen = onOpen;
   websocket.onclose = onClose;

   function send(str){

       websocket.send(str);
   }
   
   //채팅창에서 나갔을 때
   function onClose(evt) {
       websocket.send("나갔누");
   }
   
   //채팅창에 들어왔을 때
   function onOpen(evt) {
       websocket.send("입장했누");
   }

   function onMessage(msg) {
	   //alert(msg.data);
	   if(JSON.parse(msg.data).to === '${loginUser._id }'){
		   alert("매칭요청이 왔어요");
	   }
   }
</c:if>
</script>