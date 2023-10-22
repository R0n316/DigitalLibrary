<%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 21.10.2023
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Books:</h1>
<ul>
    <c:forEach var="book" items="${sessionScope.userBooks}">
        <li>${book}</li>
    </c:forEach>
</ul>
</body>
</html>
