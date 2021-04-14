<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.04.2021
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/WEB-INF/css/basic-style.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="bg-image">
<jsp:include page="header.jsp" />

<h3><fmt:message key="users"/></h3>
<div class="tableInformation1_wrapper">
    <div class="tableInformation1">
        <c:forEach var="n" items="${users}">
                <div class="TextForm1">
                <div class="descriptionForm">
                    <img src="picture/${n.picture}" width="80" height="120"/>
                    <p class="textSizeMin"><c:out value="${n.name}"/></p>
                    <p class="textSizeMin"> <fmt:message key="cost"/>: <c:out value="${n.dateRegistration}"/>р</p>
                </div>
                <div class="formControllerCentre">
                    <a class="acceptButton btn-outline-success" href="Controller?command=gotoauthorpage&idauthor=${n.id}"><fmt:message key="watch"/></a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="boxColumn">

    <div class="boxColumn">
        <h1><fmt:message key="books"/> :</h1>

        <c:forEach var="n" items="${books}">
                <div class="formControllerColumn">
                    <div>
                        <img src="picture/${n.photoReference}" width="155" height="215"/>
                        <div class="rowButton">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="gotomainbookpage"/>
                                <input type="hidden" name="idbook" value="${n.id}"/>
                                <input class="twoButton acceptButton btn-outline-success" type="submit" name="sumbit"
                                       value="<fmt:message key="watch"/>"/>
                            </form>

                            <c:if test="${role != 'GUEST'}">
                                <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="buybookuser"/>
                                    <input type="hidden" name="idbook" value="${n.id}"/>
                                    <input type="hidden" name="idauthor" value="${n.authorId}"/>
                                    <input type="hidden" name="cost" value="${n.cost}"/>
                                    <input class="twoButton acceptButton btn-outline-success" type="submit" name="sumbit"
                                           value="<fmt:message key="buy"/>"/>
                                </form>
                            </c:if>

                            <c:if test="${role == 'GUEST'}">
                                <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="gotologinpage"/>
                                    <input class="twoButton acceptButton btn-outline-success" type="submit" name="sumbit"
                                           value="<fmt:message key="buy"/>"/>
                                </form>
                            </c:if>

                        </div>
                    </div>
                    <div>
                        <p class="rowText"><c:out value="${n.nameBook}"/></p>
                        <p class="rowText"><fmt:message key="genres"/>: <c:out value="${n.genre}"/></p>
                        <p class="descriptionBook textSize12"><c:out value="${n.description}"/></p>
                        <p class="textSize12"><fmt:message key="date_add"/>: <c:out value="${n.dateAdd}"/></p>
                        <p class="textSize12"><fmt:message key="cost"/>: <c:out value="${n.cost}"/>р</p>
                    </div>
                </div>

        </c:forEach>
    </div>

</div>

</body>
</fmt:bundle>
</html>
