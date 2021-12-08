<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
<c:forEach var="task" items="${taskList}">
    <li>${task.name}</li>
</c:forEach>
</ul>