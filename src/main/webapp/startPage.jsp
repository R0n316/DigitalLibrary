<%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 19.10.2023
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Title</title>
</head>
<body>
<div class = "container">
    <%@include file="header.jsp"%>
    <div class = "registration">
        <div class = "registrationColor"></div>
        <img src="style/pngwing.com.png" class = "icon" alt="">
        <h1 class = "header">D i g i t a l &nbsp;&nbsp;L i b r a r y</h1>
        <h3 class = "registrationHeader" ><fmt:message key="page.startPage.welcome"/></h3>
        <form class = "startPageForm">
            <button class = startPageButton><a
                    href="${pageContext.request.contextPath}/registrationPage"><fmt:message key="page.startPage.registrationButton"/>
            </a></button>
            <button class = startPageButton><a
                    href="${pageContext.request.contextPath}/signInPage"><fmt:message key="page.startPage.signInButton"/>
            </a></button>
        </form>
    </div>
</div>
</body>
</html>

