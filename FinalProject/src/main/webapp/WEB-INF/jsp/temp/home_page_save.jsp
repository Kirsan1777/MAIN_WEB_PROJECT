<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 02.03.2021
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <style>
        <%@include file="/WEB-INF/css/header.css" %>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<jsp:include page="header.jsp" />

<img src= "picture/${users.picture}" width="150" height="150"/>

<form action="FileUploadingServlet" enctype="multipart/form-data" method="post">
    <input type="file" name="Picture" accept="image/*"/>
    <input type="hidden" name="page" value="user"/>
    <input type="submit" value="<fmt:message key="update_photo"/>"/>
</form>


<form action="Controller" method="post">
    <input type="hidden" name="command" value="updateuserinfocommand">
    <fmt:message key="name"/>
    <input type="text" name="name" value="${users.name}" required="required" />
    <fmt:message key="email"/>
    <input type="email" name="email" value="${users.email}" required="required" />
    <fmt:message key="password"/>
    <input type="password" name="password" value="${users.password}"  />
    <input type="submit" name="sumbit" value="<fmt:message key="accept"/>"/>
</form>
<fmt:message key="current_balance"/> - ${balance}
<fmt:message key="put_on_the_bank_account"/>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="updatebalancecommand">
    <input type="number" name="balance" min = "0" >
    <input type="submit" name="sumbit" value="<fmt:message key="accept"/>"/>
</form>
<c:if test="${message == 'CORRECT UPDATE'}">
<p>
    <span style="color: green; ">Updated!</span>
<p>
    </c:if>

<a href="Controller?command=gotobookpage"><fmt:message key="add_book"/></a>
<a href="Controller?command=logout"><fmt:message key="logout"/></a>


<div class="author">
    <div class="descriptionAuthor">
        <div>
            <img src="picture/${users.picture}" width="100" height="140"/>
            <form action="FileUploadingServlet" enctype="multipart/form-data" method="post">
                <input type="file" name="Picture" accept="image/*"/>
                <input type="hidden" name="page" value="user"/>
                <input type="submit" value="<fmt:message key="update_photo"/>"/>
            </form>
        </div>
        <div class="textForm2">
            <div class="text">
                    ${users.name}
            </div>
            <div class="text">
                <fmt:message key="date_registration"/> : ${users.dateRegistration}
            </div>
            <div class="text">
                <fmt:message key="rating"/>: ${users.rating}
            </div>
        </div>
        <a href="Controller?command=gotobookpage"><fmt:message key="add_book"/></a>
        <a href="Controller?command=logout"><fmt:message key="logout"/></a>
    </div>
</div>



</body>
</fmt:bundle>
</html>
