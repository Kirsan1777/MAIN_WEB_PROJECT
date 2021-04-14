<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
    <%--
      Created by IntelliJ IDEA.
      User: Asus
      Date: 05.04.2021
      Time: 13:15
      To change this template use File | Settings | File Templates.
    --%>

    <%@ taglib prefix="tag" uri="/WEB-INF/jsp/tag/MyTagTime.tld" %>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <style>
            <%@include file="/WEB-INF/css/header.css" %>
        </style>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
              integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
              crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>

    <body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>


    <nav class="navbar  navbar-expand-lg p-2 mb-2 bg-color text-white">

        <div class="col-sm-1 textSizeMin"><tag:MyTagTime/></div>
        <div class="collapse navbar-collapse col-sm-10" id="navbarSupportedContent">
            <a href="Controller?command=gotomainindexpage"><img src="picture/logo.svg" width=40"
                                                                height="40"/></a>
            <div class="navbar-nav mr-auto">
                <a class="button1" href="Controller?command=gotomainindexpage"><fmt:message key="main"/></a>
                <a class="button1" href="Controller?command=sortbookcommand&sort=name_book"><fmt:message
                        key="library"/></a>
                <c:if test="${role == 'ADMIN'}">
                    <a class="button1" href="Controller?command=viewalluserscommand&sort=id_user"><fmt:message
                            key="users"/></a>
                </c:if>
                <div class="button1 dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="genres"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="Controller?command=findbygenrecommand&genre=1"><fmt:message key="horror"/></a>
                        <a class="dropdown-item" href="Controller?command=findbygenrecommand&genre=2"><fmt:message key="fantasy"/></a>
                        <a class="dropdown-item" href="Controller?command=findbygenrecommand&genre=3"><fmt:message key="roman"/></a>
                        <a class="dropdown-item" href="Controller?command=findbygenrecommand&genre=4"><fmt:message key="litrpg"/></a>
                        <a class="dropdown-item" href="Controller?command=findbygenrecommand&genre=5"><fmt:message key="love"/></a>
                        <a class="dropdown-item" href="Controller?command=findbygenrecommand&genre=6"><fmt:message key="adventure"/></a>
                        <a class="dropdown-item" href="Controller?command=findbygenrecommand&genre=0"><fmt:message key="manga"/></a>
                        <div class="dropdown-divider"></div>
                    </div>
                </div>
            </div>
            <form class="form-inline my-2 my-lg-0" action="Controller" method="get">
                <input type="hidden" name="command" value="findbynamecommand"/>
                <input class="form-control mr-sm-2 " type="search" placeholder="<fmt:message key="search"/>"
                       aria-label="Search" name="name" required="required">
                <button class="btn btn-outline-success  my-2 my-sm-0" type="submit"><fmt:message key="search"/></button>
            </form>
            <c:if test="${role != 'GUEST'}">
                <a class="button1" href="Controller?command=gotohomepage"><fmt:message key="profile"/></a>
            </c:if>
            <c:if test="${role == 'GUEST'}">
                <a class="button1" href="Controller?command=gotologinpage"><fmt:message key="login"/></a>
            </c:if>
            <c:if test="${role != 'GUEST'}">
                <a class="button1" href="Controller?command=logout"><fmt:message key="logout"/></a>
            </c:if>



        </div>

        <div class="col-sm-1">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="ChooseLocaleCommand">
                <input type="hidden" name="url" value="${pageContext.findAttribute("url")}">
                <input class="button2 bg-color" type="submit" name="sumbit" value=<fmt:message key="language" /> />
            </form>
        </div>

    </nav>


    </body>
</fmt:bundle>
</html>
