<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 09.04.2021
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Author page</title>
    <style>
        <%@include file="/WEB-INF/css/basic-style.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="bg-color">
<jsp:include page="header.jsp" />

<div class="author">
    <div class="descriptionAuthor">
            <div>
                <img src="picture/${users.picture}" width="100" height="140"/>
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

    </div>
<div>
    <h1><fmt:message key="books"/>:</h1>>

<c:if test="${role!='ADMIN'}">
<table border="1">
    <c:forEach var="n" items="${books}">
    <c:if test="${n.access == 0}">
    <tr>
        <td>
                        <span style="color: black; ">
                            <form action="Controller" method="get">
                                <img src= "picture/${n.photoReference}" width="50" height="50"/>
                                    <c:out value="${n.nameBook}" />
                                    <c:out value="${n.id}" />
                                    <c:out value="${n.authorId}" />
                                    <c:out value="${n.description}" />
                                    <c:out value="${n.genre}" />
                                    <c:out value="${n.cost}" />
                                    <c:out value="${n.dateAdd}" />

                                <input type="hidden" name="command" value="gotomainbookpage" />
                                <input type="hidden" name="idbook" value="${n.id}" />
                                <input type="submit" name="sumbit" value="<fmt:message key="watch"/>"/>
                            </form>

            <c:if test="${role != 'GUEST'}">
                            <form action="Controller" method="post">
                            <input type="hidden" name="command" value="buybookuser" />
                            <input type="hidden" name="idbook" value="${n.id}" />
                            <input type="hidden" name="idauthor" value="${n.authorId}" />
                            <input type="hidden" name="cost" value="${n.cost}" />
                            <input type="submit" name="sumbit" value="<fmt:message key="buy"/>"/>
                            </form>
            </c:if>

            <c:if test="${role == 'GUEST'}">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="gotologinpage" />
                    <input type="submit" name="sumbit" value="<fmt:message key="buy"/>"/>
                    </form>
            </c:if>
                        </span>

        </td>
    </tr>
    </c:if>
    </c:forEach>
    </c:if>

</>
</body>
</fmt:bundle>
</html>
