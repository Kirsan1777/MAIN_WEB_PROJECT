<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.04.2021
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 31.03.2021
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

Role = ${role}
Id = ${id}
Message = ${message}
<form action="Controller" method="post">
    <input type="hidden" name="command" value="logination" />
    <h2>Логин СЮДА</h2>
    <input type="login" name="login"/>

    <h2>Пароль СЮДА</h2>
    <input type="password" name="password"/>

    <h1></h1>
    <input type="submit" name="sumbit" value="Войти"/>
    <h1></h1>

</form>

<c:if test="${message == 'INCORRECT_INPUT'}">
<p>
    <span style="color: red; ">Error</span>
<p>
    </c:if>

    <a href="Controller?command=registration">Перейти на страницу регистрации</a>
</body>
</html>
