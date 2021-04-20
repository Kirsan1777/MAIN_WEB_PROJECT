<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--
      Created by IntelliJ IDEA.
      User: Asus
      Date: 05.04.2021
      Time: 23:46
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
    <jsp:include page="header.jsp"/>
    <div>
        <div class="buttonInRow ">
            <div>
                <fmt:message key="sort_by"/>:
                <a class="textSizeMin" href="Controller?command=viewalluserscommand&sort=rating DESC"><fmt:message
                        key="by_rating"/></a>
            </div>
            <div>
                <a class="textSizeMin" href="Controller?command=viewalluserscommand&sort=rating DESC"><fmt:message
                        key="by_rating"/></a>
            </div>
            <div>
                <a class="textSizeMin" href="Controller?command=viewalluserscommand&sort=name"><fmt:message
                        key="by_name"/></a>
            </div>
            <div>
                <a class="textSizeMin" href="Controller?command=viewalluserscommand&sort=date_registration"><fmt:message
                        key="by_date_registration"/></a>
            </div>
        </div>
    </div>


    <c:if test="${message == 'ALREADY_UNBLOCK'}">
    <p>
        <span style="color: red; ">User already unblock</span>
    <p>
        </c:if>
        <c:if test="${message == 'UNBLOCK'}">
    <p>
        <span style="color: green; ">user was unblocked</span>
    <p>
        </c:if>
        <c:if test="${message == 'ERROR_BLOCKED'}">
    <p>
        <span style="color: red; ">user already blocked</span>
    <p>
        </c:if>
        <c:if test="${message == 'ERROR_ADMIN'}">
    <p>
        <span style="color: red; ">Can't block administrator</span>
    <p>
        </c:if>
        <c:if test="${message == 'UPDATED'}">
    <p>
        <span style="color: red; ">User was blocked</span>
    <p>
        </c:if>


    <div class="tableInformation1_wrapper">
        <div class="tableInformation1">
            <c:forEach var="n" items="${users}">
                <div class="TextForm1">
                    <div class="descriptionForm">
                        <img src="picture/${n.picture}" width="80" height="120"/>
                        <p class="textSizeMin"><c:out value="${n.name}"/></p>
                        <p class="textSizeMin"><fmt:message key="date_registration"/>: <c:out
                                value="${n.dateRegistration}"/></p>
                        <p class="textSizeMin"><fmt:message key="rating"/>: <c:out value="${n.rating}"/></p>
                    </div>
                    <div class="formController">
                        <form action="Controller" method="get" class="Form1">
                            <input type="hidden" name="command" value="unblockusercommand"/>
                            <input type="hidden" name="iduser" value="${n.id}"/>
                            <input class="btn btn-outline-success  my-2 my-sm-0" type="submit" name="sumbit"
                                   value="<fmt:message key="unblock"/>"/>
                        </form>
                        <form action="Controller" method="get">
                            <input type="hidden" name="command" value="blockusercommand"/>
                            <input type="hidden" name="iduser" value="${n.id}"/>
                            <input class="btn btn-outline-danger  my-2 my-sm-0" type="submit" name="sumbit"
                                   value="<fmt:message key="block"/>"/>
                        </form>


                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </body>
</fmt:bundle>
</html>
