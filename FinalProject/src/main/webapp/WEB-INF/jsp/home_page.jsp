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
        <%@include file="/WEB-INF/css/basic-style.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
    <body class="bg-image">

    <jsp:include page="header.jsp"/>

    <div class="descriptionAuthor">
        <div class="formControllerColumn">
            <div>
                <img src="picture/${users.picture}" width="300" height="420"/>
                <form action="FileUploadingServlet" enctype="multipart/form-data" method="post">
                    <label class="btn-outline-success acceptButton" style="margin-bottom: 0px" >
                        <input  style="display: none" class="btn-outline-success acceptButton" type="file" name="Picture" accept="image/*"/>
                        <fmt:message key="add_photo"/>
                    </label>
                    <input type="hidden" name="page" value="user"/>
                    <input class="btn-outline-success acceptButton" type="submit" value="<fmt:message key="update_photo"/>"/>
                </form>
            </div>
            <div class="position">
            <div>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="updateuserinfocommand">
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="name"/>
                        </div>
                        <div>
                            <input class="textRedactor" type="text" name="name" value="${users.name}"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="email"/>
                        </div>
                        <div>
                            <input class="textRedactor" type="email" name="email" value="${users.email}"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="twoButton">
                        <div>
                            <fmt:message key="password"/>
                        </div>
                        <div>
                            <input class="textRedactor" type="password" name="password" value="${users.password}"/>
                        </div>
                        <div>
                            <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                                   value="<fmt:message key="accept"/>"/>
                        </div>
                    </div>
                </form>
            </div>
                <div class="textForm3">
                    <div class="twoButton">
                    <fmt:message key="current_balance"/> - ${balance}
                    <fmt:message key="put_on_the_bank_account"/>
                    </div>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="updatebalancecommand">
                        <input type="number" name="balance" min="0">
                        <div>
                        <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                               value="<fmt:message key="accept"/>"/>
                        </div>
                    </form>
            </div>
                <div class="twoButton">
                    <a class="btn-outline-success acceptButton" href="Controller?command=gotobookpage"><fmt:message key="add_book"/></a>
                    <a class="acceptButton btn-outline-danger" href="Controller?command=logout"><fmt:message key="logout"/></a>
                </div>
            </div>

        </div>
        </div>

    </body>
</fmt:bundle>
</html>
