<%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 14.10.2023
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="style/style.css">
  <title>Title</title>
</head>
<body>
<div class = "registration">
  <div class = "registrationColor"></div>
  <img src="style/pngwing.com.png" class = "icon" alt="">
  <h1 class = "header">D i g i t a l &nbsp;&nbsp;L i b r a r y</h1>
  <h3 class = "registrationHeader" >welcome</h3>
  <form class = "registrationPageForm" action="${pageContext.request.contextPath}/registration" method="POST">
    <h2 class = "inputName">Name:</h2>
    <input type="text" name = "name">
    <h2 class = "inputName">Login:</h2>
    <input type = "text" name = "login">
    <h2 class = "inputName">Password:</h2>
    <input type="text" name = "password">
    <div class = "buttonDiv">
      <button class = "registerButton"> register</button>
    </div>
  </form>
</div>
</body>
</html>