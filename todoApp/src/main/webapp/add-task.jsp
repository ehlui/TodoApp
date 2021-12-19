<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a task</title>
</head>
<body>
	<h1>Adding a task</h1>
	<form method="POST" action="AddTask">
      <label for="task-name">Task:</label></br>
      <input type="text" id="task-name" name="task-name"></br>
      <label for="task-description">Description:</label></br>
      <input type="text" id="task-description" name="task-description"></br>
      <input type="submit" value="Submit">
    </form>
</body>
</html>