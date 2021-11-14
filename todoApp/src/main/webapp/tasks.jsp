<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>HELLO FROM</h1>
	<ul>

	<c:forEach var="tempTasks" items="${tasks}">
		<li>${tempTasks}</li>
	</c:forEach>
	</ul>

	${pepe}

</body>
</html>