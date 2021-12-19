<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tasks List</title>
</head>
<body>
	<h1>Todo's</h1>
	<c:choose>
            <c:when test="${empty taskList}">
                <p>No tasks...</p>
            </c:when>
            <c:otherwise>
                <jsp:include page="includes/iterateTasksList.jsp" />
            </c:otherwise>
        </c:choose>
</body>
</html>