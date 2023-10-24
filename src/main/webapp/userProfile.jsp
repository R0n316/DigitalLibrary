<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 22.10.2023
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style/profileStyle.css">
</head>
<body>
    <h1>Profile picture:</h1>
    <div class = "profilePicture">

    </div>
    <div></div>
    <h1>Your name: ${UserService.getUser().getUserName()}</h1>
    <form action="${pageContext.servletContext.contextPath}/change" method="post">
        <label>
            Change name:
            <input name="value">
            <input type="hidden" name="attribute" value="user_name">
            <button type="submit">submit</button>
        </label>
    </form>

    <h1>your password: ${UserService.getUser().getPassword()}</h1>
    <form action="${pageContext.servletContext.contextPath}/change" method="post">
        <label>
            Change name:
            <input name="value">
            <input type="hidden" name="attribute" value="pass">
            <button type="submit">submit</button>
        </label>
    </form>
</body>
</html>
