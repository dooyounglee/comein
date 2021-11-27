<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="/main">메인</a>
<a href="/mypage">마이페이지</a>
<a href="/exchange">교환</a>

<c:if test="${loginUser ne null }">
	user:${loginUser.name }&nbsp;
	<button onclick="location.href='/logOut'">logout</button><br>
</c:if>

<c:if test="${loginUser eq null }">
	<button onclick="location.href='/login'">login</button>
</c:if>

<hr>
<br>
