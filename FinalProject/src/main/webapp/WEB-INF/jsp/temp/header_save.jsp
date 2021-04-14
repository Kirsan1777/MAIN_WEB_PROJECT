<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 21.03.2021
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <style>
        <%@include file="/WEB-INF/css/header.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<header>

<nav>

    <a href="Controller?command=gotomainindexpage"><img src= "picture/HeadPicture.jpg" width=40" height="40" /></a>
    <a class = "button1" href="#">Books</a>
    <a class = "button2" href="#">My library</a>
    <a href="Controller?command=gotologinpage">Login</a>
    </form> <a href="Controller?command=gotobookpage">Add book</a>
    <a href="Controller?command=gotohomepage">Homepage</a>

    <form action="Controller" method="post">
        <input class="finder" type="text" name="Name"/>
        <input class="buttonFind" type="submit" name="sumbit" value="Найти"/>
    </form>

    <a href="Controller?command=gotologinpage">Login</a>


</nav>
</header>
</html>
