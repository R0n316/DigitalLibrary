<%@ page import="service.UserService" %>
<%@ page import="service.BookService" %><%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 05.10.2023
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="style/homePageStyle.css">
    <title>Title</title>
</head>
<body>
<div>
    <h3>Your books:</h3>
    <c:forEach var="book" items="${BookService.findUserBooks()}">
        <h4>${book.getBookName()}</h4>
    </c:forEach>
    <h4>Available books:</h4>
    <c:forEach var="book" items="${BookService.findAllAvailableBooks()}">
    <h4>${book.getBookName()}</h4>
    </c:forEach>
    <c:if test="${param.book_id!=null}">
        <h3>User take new book:)</h3>
        ${BookService.takeBook(param.book_id)}
        <h3>Available books:</h3>
        <c:forEach var="book" items="${BookService.findAllAvailableBooks()}">
            <h4>${book.getBookName()}</h4>
        </c:forEach>
    </c:if>
    <c:if test="${param.return_book!=null}">
        ${BookService.returnBook(param.return_book)}
    </c:if>
    <form class = "usersPageForm" action="${pageContext.request.contextPath}/users" method="post">
        <button type="submit">User info</button>
    </form>
    <form class = "userProfileForm" action="${pageContext.request.contextPath}/profile" method="post">
        <button type="submit">profile</button>
    </form>
</div>
</body>
</html>
