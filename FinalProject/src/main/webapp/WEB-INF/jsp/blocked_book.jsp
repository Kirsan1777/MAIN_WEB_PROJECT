<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 13.04.2021
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/WEB-INF/css/header.css" %>
        <%@include file="/WEB-INF/css/basic-style.css" %>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<c:if test="${sessionScope.language == 'ru_RU'}">
    <body class="bg-imageRU">
    <jsp:include page="header.jsp"></jsp:include>
        ${sessionScope.language}
    </body>
</c:if>
<c:if test="${sessionScope.language == 'en_US'}">
    <body class="bg-imageEU">
    <jsp:include page="header.jsp"></jsp:include>

    </body>
</c:if>
</fmt:bundle>
</html>
