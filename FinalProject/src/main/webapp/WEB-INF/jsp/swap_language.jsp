<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.04.2021
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:bundle basename="property.text">
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dropdown_menu.css"/>
</head>
<body>
<form action="Controller" name="select language" method="post">
    <input type="hidden" value="change_language" name="command">
        ${requestScope.fullUrl}
    <select class="dark-navbar" onchange="this.form.submit()" name="language">
        <option value="en_US"><fmt:message key="en"/></option>
        <option value="ru_RU"><fmt:message key="ru"/></option>
    </select>
</form>
</body>
</fmt:bundle>
</html>
