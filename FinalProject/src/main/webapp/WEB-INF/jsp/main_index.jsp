<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <meta charset="UTF-8">
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
    <c:choose>
        <c:when test="${role == 'BLOCKED'}">
            <jsp:forward page="blocked_page.jsp">
                <jsp:param name="command" value="gotoblockpage"/>
            </jsp:forward>
        </c:when>
    </c:choose>
    <jsp:include page="header.jsp"></jsp:include>
   <%--${property.text_en_US.properties=login}--%>
    <div>
    <div>
    <c:if test="${pageContext.request.getParameter('message') == 'INCORRECT_INPUT'}">
        <p>
            <span style="color: green; "><fmt:message key="your_book_was_added"/>Your book was created</span>
        <p>
    </c:if>
    <c:if test="${pageContext.request.getParameter('message') == 'BUY' }">
        <p>
            <span style="color: green; "><fmt:message key="your_book_was_bought"/></span>
        <p>
    </c:if>
    <c:if test="${pageContext.request.getParameter('message') == 'NOT_MONEY' }">
        <p>
            <span style="color: red; "><fmt:message key="not_enough_money"/></span>
        <p>
    </c:if>

    <c:if test="${pageContext.request.getParameter('message') == 'BOOK_BOUGHT'}">
        <p>
            <span style="color: red; "><fmt:message key="book_has_already_bought"/></span>
        <p>
    </c:if>
        <c:if test="${pageContext.request.getParameter('message') == 'YOURBOOK'}">
        <p>
            <span style="color: red; "><fmt:message key="this_book_has_already_your"/></span>
        <p>
        </c:if>

        <h3 id="marginTop" style="margin-bottom: 25px"><fmt:message key="new_books"/></h3>
    <div class="tableInformation1_wrapper">
    <div class="tableInformation1">
    <c:forEach var="n" items="${booksnew}">
        <c:if test="${n.access == 0}">
            <div class="TextForm1">
            <div class="descriptionForm">
                <img src="picture/${n.photoReference}" width="80" height="120"/>
                <p class="textSizeMin"><c:out value="${n.nameBook}"/></p>
                <p class="textSizeMin"> <fmt:message key="cost"/>: <c:out value="${n.cost}"/>р</p>
            </div>
            <div class="formController">
                <form action="Controller" method="get" class="Form1">
                    <input type="hidden" name="command" value="gotomainbookpage"/>
                    <input type="hidden" name="idbook" value="${n.id}"/>
                    <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                           value="<fmt:message key="watch"/>"/>
                </form>

                <c:if test="${role != 'GUEST'}">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="buybookuser"/>
                        <input type="hidden" name="idbook" value="${n.id}"/>
                        <input type="hidden" name="idauthor" value="${n.authorId}"/>
                        <input type="hidden" name="cost" value="${n.cost}"/>
                        <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                               value="<fmt:message key="buy"/>"/>
                    </form>
                </c:if>
                <c:if test="${role == 'GUEST'}">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="gotologinpage"/>
                        <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                               value="<fmt:message key="buy"/>"/>
                    </form>
                </c:if>

            </div>
        </c:if>
        </div>
    </c:forEach>
    </div>
    </div>
        <h3 id="marginTop"><fmt:message key="all_books"/></h3>
    <div class="tableInformation1_wrapper">
    <div class="tableInformation1">
    <c:forEach var="k" items="${booksname}">
        <c:if test="${k.access == 0}">
            <div class="TextForm1">
            <div class="descriptionForm">
                <img src="picture/${k.photoReference}" width="80" height="120"/>
                <p class="textSizeMin"><c:out value="${k.nameBook}"/></p>
                <p class="textSizeMin"><fmt:message key="cost"/>: <c:out value="${k.cost}"/>р</p>
            </div>
            <div class="formController">
                <form action="Controller" method="post" class="Form1">
                    <input type="hidden" name="command" value="gotomainbookpage"/>
                    <input type="hidden" name="idbook" value="${k.id}"/>
                    <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                           value="<fmt:message key="watch"/>"/>
                </form>

                <c:if test="${role != 'GUEST'}">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="buybookuser"/>
                        <input type="hidden" name="idbook" value="${k.id}"/>
                        <input type="hidden" name="idauthor" value="${k.authorId}"/>
                        <input type="hidden" name="cost" value="${k.cost}"/>
                        <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                               value="<fmt:message key="buy"/>"/>
                    </form>
                </c:if>
                <c:if test="${role == 'GUEST'}">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="gotologinpage"/>
                        <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                               value="<fmt:message key="buy"/>"/>
                    </form>
                </c:if>

            </div>
        </c:if>

        </div>
    </c:forEach>
    </div>
    </div>
    </div>
    </div>
    </body>
    </fmt:bundle>
    </html>
