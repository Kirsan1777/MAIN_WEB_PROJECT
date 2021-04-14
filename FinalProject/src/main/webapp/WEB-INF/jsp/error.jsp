<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 21.03.2021
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
    <body>
    <p>
        We have problem with site!
        Please wait
    </p>
    <a href="Controller?command=gotomainindexpage">Ok</a>
    </body>
</fmt:bundle>
</html>
