<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 23.02.2021
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="createnewuser" />
    <h3><fmt:message key="login"/></h3>
    <input type="login" name="LoginUser" required="required"/>
    <h3><fmt:message key="password"/></h3>
    <input type="password" name="PasswordUser" required="required"/>
    <h3><fmt:message key="email"/></h3>
    <input type="email" name="EmailUser" required="required"/>
    <h1></h1>
    <h3><fmt:message key="name"/></h3>
    <input type="text" name="Name" required="required"/>
    <h1></h1>
    <c:if test="${message == 'THIS_LOGIN_IS_ALREADY_USED'}">
    <p>
        <span style="color: red; ">This login is already used</span>
    <p>
        </c:if>
    <input type="submit" name="sumbit" value="<fmt:message key="registration"/>"/>
</form>
<a href="Controller?command=gotomainindexpage"><fmt:message key="back_main"/></a>
</body>
</fmt:bundle>
</html>