<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${loginUser ne null }">
user:${loginUser.name }<br>
	<c:if test="${loginFrequency ne null }">
		frequency: ${loginFrequency }<br>
	</c:if>
	<c:if test="${loginFrequency eq null }">
		frequency를 입력하세요<br>
	</c:if>
<button onclick="location.href='/logOut'">logout</button><br>
</c:if>
<c:if test="${loginUser eq null }">
<button onclick="location.href='/login'">login</button>
</c:if>
<br>이유현
<br>User(_id=607c335dc49e252c88301c77, name=Steve Jennings, email=tiwekacud@ussuwep.lu)
<br>User(_id=607c335ec49e252c88301c78, name=Beulah Huff, email=meb@kijlago.dz)
<br>User(_id=607c335ec49e252c88301c79, name=Harriet Doyle, email=ban@feg.bf)
<br>User(_id=607c335ec49e252c88301c7a, name=Miguel Cain, email=luowo@siggevu.ky)
<br>User(_id=607c335ec49e252c88301c7b, name=Carrie Elliott, email=karezej@copi.li)
<br>User(_id=607c335ec49e252c88301c7c, name=Alan Roberson, email=tuuw@dozwa.sa)
<br>User(_id=607c335fc49e252c88301c7d, name=Mabel Alexander, email=to@wukmep.sn)
<br>User(_id=607c335fc49e252c88301c7e, name=Eugenia Carpenter, email=rewizda@fowapaese.mo)
<br>User(_id=607c335fc49e252c88301c7f, name=Martha Maldonado, email=norvondij@ka.vu)
<br>User(_id=607c335fc49e252c88301c80, name=Allie Daniels, email=ze@ro.md)
<br>User(_id=607c335fc49e252c88301c81, name=Jim Smith, email=zoapoju@tatiw.tw)
<br>User(_id=607c3360c49e252c88301c82, name=Elva Guerrero, email=ikni@novuk.za)
<br>User(_id=607c3360c49e252c88301c83, name=Tillie Moore, email=mubudote@kas.mz)
<br>User(_id=607c3360c49e252c88301c84, name=Craig Duncan, email=riduw@sune.gs)
<br>User(_id=607c3360c49e252c88301c85, name=Ricky Arnold, email=sulsa@jibazuf.fk)
<br>User(_id=607c3360c49e252c88301c86, name=Estelle Lane, email=mi@emaanju.cv)
<br>User(_id=607c3361c49e252c88301c87, name=Rhoda Neal, email=ziasu@fovo.org)
<br>User(_id=607c3361c49e252c88301c88, name=Lucille Tucker, email=jocbek@itoahuboh.na)
<br>User(_id=607c3361c49e252c88301c89, name=Isaac Miles, email=va@isomigu.gn)
<br>User(_id=607c3361c49e252c88301c8a, name=Adam Garza, email=oba@timow.vc)
<br>User(_id=607c3362c49e252c88301c8b, name=Nancy Maxwell, email=dup@be.vn)
<br>User(_id=607c3362c49e252c88301c8c, name=Etta Sanders, email=baomus@pa.lv)
<br>User(_id=607c3362c49e252c88301c8d, name=Ellen Gibson, email=zud@mufu.nu)
<br>User(_id=607c3362c49e252c88301c8e, name=Sean Cortez, email=eb@wocih.bj)
<br>User(_id=607c3362c49e252c88301c8f, name=Susan Wagner, email=ziweg@atgezle.va)
<br>User(_id=607c3363c49e252c88301c90, name=Joseph Stevens, email=ko@jabjet.org)
<br>User(_id=607c3363c49e252c88301c91, name=Warren Pratt, email=zetokmiw@del.ps)
</body>
</html>