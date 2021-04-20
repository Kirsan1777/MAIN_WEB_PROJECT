<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19.03.2021
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book_page</title>
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
<c:choose>
    <c:when test="${role == 'GUEST'}">
        <jsp:forward page="login_page.jsp">
            <jsp:param name="command" value="gotologinpage"/>
        </jsp:forward>
    </c:when>
</c:choose>

<c:if test="${message == 'INCORRECT_INPUT'}">
<p>
    <span style="color: green; "><fmt:message key="please_check_accuracy_of_input_information"/></span>
<p>
    </c:if>
    <c:if test="${pageContext.request.getParameter('message') == 'CREATE_BOOK'}">
<p>
    <span style="color: green; "><fmt:message key="your_book_was_added"/></span>
<p>
    </c:if>




<div class="descriptionAuthor">
    <div class="formControllerColumn">
        <div>
            <img src="picture/${picture}" width="300" height="420"/>
            <form action="FileUploadingServlet" enctype="multipart/form-data" method="post">
                <label class="btn-outline-success acceptButton" style="margin-bottom: 0px" >
                    <input  style="display: none" class="btn-outline-success acceptButton" type="file" name="Picture" accept="image/*"/>
                    <fmt:message key="add_photo"/>
                </label>
                <input type="hidden" name="page" value=""/>
                <input class="btn-outline-success acceptButton" type="submit" value="<fmt:message key="update_photo"/>"/>
            </form>
        </div>
        <div class="position">
            <div>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="createnewbook" />
                    <input type="hidden" name="picture" value= ${picture} />
                    <input type="hidden" name="idbook" value = "${books.id}" required="required" />
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="name_book"/>
                        </div>
                        <div>
                            <input class="textRedactor" type="text" name="NameBook"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="genres"/>
                        </div>
                        <div>
                            <select name="genre">
                                <option disabled><fmt:message key="choose_genre"/></option>
                                <option value="1"><fmt:message key="horror"/></option>
                                <option value="2"><fmt:message key="fantasy"/></option>
                                <option value="3"><fmt:message key="roman"/></option>
                                <option value="4"><fmt:message key="litrpg"/></option>
                                <option value="5"><fmt:message key="love"/></option>
                                <option value="6"><fmt:message key="adventure"/></option>
                                <option value="0"><fmt:message key="manga"/></option>
                            </select>
                        </div>
                    </div>
                    <div class="textRedactor">
                        <div class="twoButton">
                            <fmt:message key="description"/>
                        </div>
                        <div>
                            <textarea style="height: 48px" class="textRedactor" type="text" name="description"
                                      required="required"></textarea>
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
                                   value="<fmt:message key="add_book"/>"/>
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
