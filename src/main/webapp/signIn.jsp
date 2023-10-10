<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 06.10.2023
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean res = UserService.singIn(login, password);
        if(!res){
            response.sendRedirect(request.getContextPath());
        }
        else{
            response.sendRedirect("/DigitalLibrary_war_exploded/home");
        }
    %>
</body>
</html>
