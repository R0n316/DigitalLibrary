<%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 21.10.2023
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
<c:forEach var="user" items="${requestScope.users}">
    <li><a href="${pageContext.request.contextPath}/userBooksServlet?userId=${user.getUserId()}"> ID:
            ${user.getUserId()},
        Name:
            ${user.getUserName()}</a></li>
</c:forEach>
</ul>
</body>
</html>
