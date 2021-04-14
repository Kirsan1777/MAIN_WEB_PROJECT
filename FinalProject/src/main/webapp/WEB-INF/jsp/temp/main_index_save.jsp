<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 23.02.2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main Index</title>
    <style>
        <%@include file="/WEB-INF/css/header.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div>
<div>
<h1>Основная страница при входе</h1>
<c:if test="${message == 'INCORRECT_INPUT'}">
<p>
    <span style="color: green; ">Your book was created</span>
<p>
    </c:if>
        <c:choose>
        <c:when test="${role == 'BLOCKED'}">
        <jsp:forward page="blocked_page.jsp">
            <jsp:param name="command" value="gotoblockedpage"/>
        </jsp:forward>
        </c:when>
        </c:choose>

${role}
<div class="container">
Новинки
    <div class="tableInformation1">
    <table border="1">
        <c:forEach var="n" items="${booksnew}">
            <c:if test="${n.access == 0}">

            <tr>

                <td>
                    <div class="TextForm1">
                        <span style="color: black; ">
                            <form action="Controller" method="post">
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
                                <input type="submit" name="sumbit" value="Посмотреть"/>
                            </form>

            <c:if test="${role != 'GUEST'}">
                            <form action="Controller" method="post">
                            <input type="hidden" name="command" value="buybookuser" />
                            <input type="hidden" name="idbook" value="${n.id}" />
                            <input type="hidden" name="idauthor" value="${n.authorId}" />
                            <input type="hidden" name="cost" value="${n.cost}" />
                            <input type="submit" name="sumbit" value="Купить"/>
                            </form>
            </c:if>
            <c:if test="${role == 'GUEST'}">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="gotologinpage" />
                    <input type="submit" name="sumbit" value="Купить"/>
                    </form>
            </c:if>
                        </span>
                    </div>
                </td>

            </tr>

            </c:if>
        </c:forEach>
    </table>
    </div>
По названию
<div class="tableInformation2">
<table border="2">
    <c:forEach var="n" items="${booksname}" >
        <c:if test="${n.access == 0}">
            <tr>
                <td>
                        <span style="color: black; ">
                            <form action="Controller" method="post">
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
                                <input type="submit" name="sumbit" value="Посмотреть"/>
                            </form>

                            <form action="Controller" method="post">
                            <input type="hidden" name="command" value="buybookuser" />
                            <input type="hidden" name="idbook" value="${n.id}" />
                            <input type="hidden" name="idauthor" value="${n.authorId}" />
                            <input type="hidden" name="cost" value="${n.cost}" />
                            <input type="submit" name="sumbit" value="Купить"/>
                            </form>

                        </span>

                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</div>
</div>
</div>
</div>
</body>
</html>
