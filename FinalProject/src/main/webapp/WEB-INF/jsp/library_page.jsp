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
            <%@include file="/WEB-INF/css/basic-style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body class="bg-image" style="color: white">
    <jsp:include page="header.jsp"/>

    <c:if test="${role != 'GUEST'}">
        <div>
            <div>
                <h1 style="margin-left: 153px; margin-top: 30px"><fmt:message key="my_books"/> :</h1>
                <div class="tableInformation1_wrapper">
                    <div class="tableInformation1">
                        <c:forEach var="n" items="${mybooks}">
                            <div class="TextForm1">
                                <div class="descriptionForm">
                                    <img src="picture/${n.photoReference}" width="80" height="120"/>
                                    <p class="textSizeMin"><c:out value="${n.nameBook}"/></p>
                                    <p class="textSizeMin"><fmt:message key="cost"/>: <c:out value="${n.cost}"/>р</p>
                                </div>
                                <div class="formController">
                                    <form action="Controller" method="get" class="Form1">
                                        <input type="hidden" name="command" value="gotomainbookpage"/>
                                        <input type="hidden" name="idbook" value="${n.id}"/>
                                        <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                                               value="<fmt:message key="watch"/>"/>
                                    </form>
                                    <form action="Controller" method="get">
                                        <input type="hidden" name="command" value="readbookcommand"/>
                                        <input type="hidden" name="idbook" value="${n.id}"/>
                                        <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                                               value="<fmt:message key="read"/>"/>
                                    </form>

                                    <form action="Controller" method="get">
                                        <input type="hidden" name="command" value="gotoredactbookpage"/>
                                        <input type="hidden" name="idbook" value="${n.id}"/>
                                        <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                                               value="<fmt:message key="edit"/>"/>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${role != 'GUEST'}">
    <div>
        <h1 style="margin-left: 153px; margin-top: 30px"><fmt:message key="readable"/> :</h1>
        <div class="tableInformation1_wrapper">
            <div class="tableInformation1">
                <c:forEach var="n" items="${booksnew}">
                    <div class="TextForm1">
                        <div class="descriptionForm">
                            <img src="picture/${n.photoReference}" width="80" height="120"/>
                            <p class="textSizeMin"><c:out value="${n.nameBook}"/></p>
                            <p class="textSizeMin"><fmt:message key="cost"/>: <c:out value="${n.cost}"/>р</p>
                        </div>
                        <div class="formController">
                            <form action="Controller" method="get" class="Form1">
                                <input type="hidden" name="command" value="gotomainbookpage"/>
                                <input type="hidden" name="idbook" value="${n.id}"/>
                                <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                                       value="<fmt:message key="watch"/>"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="readbookcommand"/>
                                <input type="hidden" name="idbook" value="${n.id}"/>
                                <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                                       value="<fmt:message key="read"/>"/>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    </c:if>
    <div class="blockMessage">
        <c:if test="${role == 'GUEST'}">
            <p><fmt:message key="guest_accept"/></p>
        </c:if>
    </div>
    </body>
</fmt:bundle>
</html>
