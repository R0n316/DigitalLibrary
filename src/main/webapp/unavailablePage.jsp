<%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 21.10.2023
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Oops... The page seems to be unavailable</h1>
    <form class="unavailablePageForm" action="${pageContext.servletContext.contextPath}/${param.page}" method="post">
        <c:if test="${sessionScope.prevPage!=null}">
            <button type="submit" name="page"
            ><a href="${pageContext.servletContext.contextPath}${sessionScope.prevPage}">prevPage</a></button>
        </c:if>
        <button type="submit" name="page"
        ><a href="${pageContext.servletContext.contextPath}${param.startPage}">startPage</a></button>
    </form>
</body>
</html>
