<%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 14.10.2023
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <link rel="stylesheet" href="style/style.css">
  <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class = "container">
  <div class = "registration">
    <div class = "registrationColor"></div>
    <img src="style/pngwing.com.png" class = "icon" alt="">
    <h1 class = "header">D i g i t a l &nbsp;&nbsp;L i b r a r y</h1>
    <h3 class = "registrationHeader" ><fmt:message key="page.startPage.welcome"/></h3>
    <form class = "registrationPageForm" action="${pageContext.request.contextPath}/registration" method="POST">
      <h2 class = "inputName"><fmt:message key="page.registration.name"/>:</h2>
      <input type="text" name = "name">
      <h2 class = "inputName"><fmt:message key="page.registration.login"/>:</h2>
      <input type = "text" name = "login">
      <h2 class = "inputName"><fmt:message key="page.registration.password"/>:</h2>
      <input type="text" name = "password">
      <div class = "buttonDiv">
        <button type="submit" class = "registerButton"><fmt:message key="page.registration.sendButton"/></button>
      </div>
    </form>
  </div>
</div>
</body>
</html>