<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 07.04.2021
  Time: 13:30
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
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="bg-image">
<jsp:include page="header.jsp"></jsp:include>
    <c:if test="${message == 'UPDATED'}">
<p>
    <span style="color: green; ">Your book was updated</span>
<p>
    </c:if>



<div class="descriptionAuthor">
    <div class="formControllerColumn">
        <div>
            <img src="picture/${books.photoReference}" width="300" height="420"/>
            <form action="FileUploadingServlet" enctype="multipart/form-data" method="post">
                <label class="btn-outline-success acceptButton" style="margin-bottom: 0px" >
                    <input  style="display: none" class="btn-outline-success acceptButton" type="file" name="picture" accept="image/*"/>
                    <fmt:message key="add_photo"/>
                </label>
                <input type="hidden" name="page" value=""/>
                <input type="hidden" name="idbook" value="${books.id}"/>
                <input class="btn-outline-success acceptButton" type="submit" value="<fmt:message key="update_photo"/>"/>
            </form>
        </div>
        <div class="position">
            <div>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="updatebookinfo" />
                    <input type="hidden" name="picture" value= ${picture} />
                    <input type="hidden" name="idbook" value = "${books.id}" required="required" />
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="name_book"/>
                        </div>
                        <div>
                            <input class="textRedactor" type="text" name="name" value="${books.nameBook}"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="description"/>
                        </div>
                        <div>
                            <textarea style="height: 48px" class="textRedactor" type="text" name="description"
                                      required="required">${books.description}</textarea>
                        </div>
                    </div>
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="cost"/>
                        </div>
                        <div>
                            <input class="textRedactor" type="number" name="cost" min="0" value="${books.cost}"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="text"/>
                        </div>
                        <div>
                            <textarea style="height: 120px;" class="textRedactor"  name="text"  >${books.text}</textarea>
                        </div>
                        <div>
                            <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                                   value="<fmt:message key="accept"/>"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</fmt:bundle>
</html>
