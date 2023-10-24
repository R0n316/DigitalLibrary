<%--
  Created by IntelliJ IDEA.
  User: manya
  Date: 20.10.2023
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="util.LocaleLanguages" %>
<header>
    <div class = "headerColor"></div>
    <c:choose>
        <c:when test="${sessionScope.lang==null}">
            <fmt:setLocale value="en_EN"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="${sessionScope.lang}"/>
        </c:otherwise>
    </c:choose>
    <fmt:setBundle basename="translations"/>
    <div>
        <a href="${pageContext.request.contextPath}/startPage">
            <img src="style/pngwing.com.png" class = "headerIcon" alt="">
        </a>
        <h3>D i g i t a l &nbsp;&nbsp;L i b r a r y</h3>
    </div>
    <form action="${pageContext.request.contextPath}/locale" id="selectLanguage" method="post">
        <button type="submit" name="lang" value="ru_RU">RU</button>
        <button type="submit" name="lang" value="en_EN">EN</button>
    </form>
    <c:if test="${sessionScope.user!=null}">
        <form action="${pageContext.request.contextPath}/signOut" method="post">
            <button type="submit">Sign Out</button>
        </form>
        <button type="button"><a href="${pageContext.servletContext.contextPath}/profile">profile</a></button>
    </c:if>
</header>