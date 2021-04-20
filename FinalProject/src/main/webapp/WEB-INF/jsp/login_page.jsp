<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 31.03.2021
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login</title>
    <style>
        <%@include file="/WEB-INF/css/basic-style.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body class="bg-image">
<jsp:include page="header.jsp" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>


<div class="login brd" id = "fon">
    <form action="Controller" method="get" class="login">
        <input type="hidden" name="command" value="logination" />
        <div>
            <h3><fmt:message key="login"/></h3>
            <input class="inputField" type="login" name="login"/>
        </div>
        <div>
            <h3><fmt:message key="password"/></h3>
            <input class="inputField" type="password" name="password"/>
        </div>
        <div>
            <c:if test="${message == 'INCORRECT_INPUT'}">
            <p>
                <span style="color: red; ">Invalid login or password</span>
            <p>
                </c:if>
        </div>
        <div>
            <input class="auth" type="submit" name="sumbit" value="Войти"/>
        </div>
    </form>
    <div>
    <nav class="navbar" id = colorHidden>
        <div><fmt:message key="no_account"/>?    </div>
        <div><a class="text" href="Controller?command=registration"><fmt:message key="registration"/></a></div>
    </nav>
    </div>
</div>

</body>
</fmt:bundle>
</html>
