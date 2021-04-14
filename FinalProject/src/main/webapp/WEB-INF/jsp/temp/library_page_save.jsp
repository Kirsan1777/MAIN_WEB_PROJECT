<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 31.03.2021
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TITLE</title>
    <style>
        <%@include file="/WEB-INF/css/header.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="bg-image">
<jsp:include page="header.jsp"/>

<c:if test="${role != 'GUEST'}">
    <fmt:message key="my_books"/>
    <table border="3">
        <c:forEach var="n" items="${mybooks}">
            <c:if test="${n.access == 0}">
                <tr>
                    <td>
                        <span style="color: black; ">
                                <img src= "picture/${n.photoReference}" width="50" height="50"/>
                                    <c:out value="${n.nameBook}" />
                                    <c:out value="${n.id}" />
                                    <c:out value="${n.authorId}" />
                                    <c:out value="${n.description}" />
                                    <c:out value="${n.genre}" />
                                    <c:out value="${n.cost}" />
                                    <c:out value="${n.dateAdd}" />
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="gotomainbookpage" />
                                <input type="hidden" name="idbook" value="${n.id}" />
                                <input type="submit" name="sumbit" value="<fmt:message key="watch"/>"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="readbookcommand" />
                                <input type="hidden" name="idbook" value="${n.id}" />
                                <input type="submit" name="sumbit" value="<fmt:message key="read"/>"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="gotoredactbookpage" />
                                <input type="hidden" name="idbook" value="${n.id}" />
                                <input type="submit" name="sumbit" value="<fmt:message key="edit"/>"/>
                            </form>
                        </span>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</c:if>

<c:if test="${role != 'GUEST'}">
    Моя библиотека
    <table border="2">
    <c:forEach var="n" items="${booksnew}">
        <c:if test="${n.access == 0}">
            <tr>
                <td>
                        <span style="color: black; ">
                                <img src= "picture/${n.photoReference}" width="50" height="50"/>
                                    <c:out value="${n.nameBook}" />
                                    <c:out value="${n.id}" />
                                    <c:out value="${n.authorId}" />
                                    <c:out value="${n.description}" />
                                    <c:out value="${n.genre}" />
                                    <c:out value="${n.cost}" />
                                    <c:out value="${n.dateAdd}" />
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="gotomainbookpage" />
                                <input type="hidden" name="idbook" value="${n.id}" />
                                <input type="submit" name="sumbit" value="<fmt:message key="watch"/>"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="readbookcommand" />
                                <input type="hidden" name="idbook" value="${n.id}" />
                                <input type="submit" name="sumbit" value="<fmt:message key="read"/>"/>
                            </form>
                        </span>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</c:if>

Найденные книги
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
</table>
</body>
</fmt:bundle>
</html>
