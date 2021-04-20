<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 23.02.2021
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        <%@include file="/WEB-INF/css/basic-style.css" %>
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="bg-image">
<jsp:include page="header.jsp"></jsp:include>
<div class="boxColumn author">
    <div class="descriptionAuthor">
        <form action="Controller" method="get">
            <input type="hidden" name="command" value="createnewuser" />
            <div>
                <p class="rowText"><fmt:message key="login"/></p>
                <input class="inputField" type="login" name="login" required="required"/>
            </div>
            <div>
                <p class="rowText"><fmt:message key="password"/></p>
                <input class="inputField" type="password" name="password" required="required"/>
            </div>
            <div>
                <p class="rowText"><fmt:message key="email"/></p>
                <input class="inputField" type="email" name="email" required="required"/>
            </div>
            <div>
                <p class="rowText"><fmt:message key="name"/></p>
                <input class="inputField" type="text" name="name" required="required"/>
            </div>
            <div>
                <p class="textColorGray"><fmt:message key="fill_in_all_the_fields_for_registration"/>!</p>
            </div>
            <p class="rowText">
                <input class="auth" type="submit" name="submit" value="<fmt:message key="registration"/>"/>
            <div>
                <a class="linkOption" href="Controller?command=gotomainindexpage"><fmt:message key="back_main"/></a>
            </div>
            <div>
                <c:if test="${message == 'THIS_LOGIN_IS_ALREADY_USED'}">
                <p>
                    <span class="textSizeMin" style="color: red; "><fmt:message key="this_login_is_already_used"/></span>
                <p>
                    </c:if>
                        <c:if test="${message == 'INCORRECT_INPUT'}">
                    <p>
                        <span class="textSizeMin" style="color: red; "><fmt:message key="incorrect_input_reg"/></span>
                    <p>
                        </c:if>
            </div>
        </form>

    </div>
</div>
</body>
</fmt:bundle>
</html>