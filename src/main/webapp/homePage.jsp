<%@ page import="service.UserService" %>
<%@ page import="service.BookService" %><%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 05.10.2023
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style/homePageStyle.css">
    <title>Title</title>
</head>
<body>
    <div class = "registration">
        <h3>Your books:</h3>
        <h4><%=UserService.findUserBooks()%></h4>
        <h3>Available books:</h3>
        <h4><%=BookService.findAllAvailableBooks()%></h4>
        <%String bookId = request.getParameter("book_id");
            if(bookId!=null){%>
        <h3>User take new book:)</h3>
        <h4><%=UserService.findUserBooks()%></h4>
        <h3>Available books:</h3>
        <h4><%=BookService.findAllAvailableBooks()%></h4>
        <%}%>
        <%
            String returnBook = request.getParameter("returnBook");
            if(returnBook!=null){
                UserService.returnBook(Integer.parseInt(returnBook));
            }%>
    </div>
</body>
</html>
