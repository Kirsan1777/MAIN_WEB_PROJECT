<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--
      Created by IntelliJ IDEA.
      User: Asus
      Date: 01.04.2021
      Time: 23:43
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Title</title>
        <style>
            <%@include file="/WEB-INF/css/header.css" %>
            <%@include file="/WEB-INF/css/basic-style.css" %>
        </style>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
              integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body class="bg-image">
    <jsp:include page="header.jsp"/>
    <c:if test="${role != 'ADMIN'}">
        <c:choose>
            <c:when test="${books.access == 1}">
                <jsp:forward page="blocked_book.jsp">
                    <jsp:param name="command" value="gotoblockedbookpage"/>
                </jsp:forward>
            </c:when>
        </c:choose>
    </c:if>

    <div class="author">
        <div class="descriptionAuthor">
            <div class="formControllerColumn">
                <div>
                    <img src="picture/${books.photoReference}" width="155" height="215"/>
                    <div class="rowButton">
                        <div style="margin-right: 5px">
                            <c:if test="${role != 'GUEST'}">
                                <form action="Controller" method="get">
                                    <input type="hidden" name="command" value="readbookcommand"/>
                                    <input type="hidden" name="idbook" value="${books.id}"/>
                                    <input class="btn-outline-success  acceptButton" type="submit" name="sumbit"
                                           value="<fmt:message key="read"/>"/>
                                </form>
                            </c:if>
                            <c:if test="${role == 'GUEST'}">
                                <form action="Controller" method="get">
                                    <input type="hidden" name="command" value="gotologinpage"/>
                                    <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                                           value="<fmt:message key="read"/>"/>
                                </form>
                            </c:if>
                        </div>
                        <div style="margin-left: 5px">
                            <c:if test="${role != 'GUEST'}">
                                <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="buybookuser"/>
                                    <input type="hidden" name="idbook" value="${books.id}"/>
                                    <input type="hidden" name="idauthor" value="${books.authorId}"/>
                                    <input type="hidden" name="cost" value="${books.cost}"/>
                                    <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                                           value="<fmt:message key="buy"/>"/>
                                </form>
                            </c:if>
                            <c:if test="${role == 'GUEST'}">
                                <form action="Controller" method="get">
                                    <input type="hidden" name="command" value="gotologinpage"/>
                                    <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                                           value="<fmt:message key="buy"/>"/>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="textForm2">
                <div class="textSizeMin">
                        ${books.nameBook}
                </div>
                <div class="text">
                    <fmt:message key="date_add"/> : ${books.dateAdd}
                </div>
                <div class="text">
                    <p class="descriptionBook textSize12"><c:out value="${books.description}"/></p>
                </div>
                <div class="text">
                    <fmt:message key="cost"/>: ${books.cost}
                </div>
                <div class="rowButton" style="display: flex; justify-content: space-between">
                    <div>
                        <c:if test="${role == 'ADMIN'}">
                            <form action="Controller" method="get">
                                <c:if test="${books.access == 0}">
                                    <input type="hidden" name="command" value="blockbookcommand"/>
                                    <input type="hidden" name="idbook" value=${books.id}/>
                                    <input class="acceptButton btn-outline-danger" type="submit" name="sumbit"
                                           value="<fmt:message key="block"/>"/>
                                </c:if>
                                <c:if test="${books.access == 1}">
                                    <input type="hidden" name="command" value="unblockbookcommand"/>
                                    <input type="hidden" name="idbook" value=${books.id}/>
                                    <input class="acceptButton btn-outline-success" type="submit" name="sumbit"
                                           value="<fmt:message key="unblock"/>"/>
                                </c:if>
                            </form>
                        </c:if>
                    </div>
                    <div>
                        <c:if test="${role == 'ADMIN'}">
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="deletebookcommand"/>
                                <input type="hidden" name="idbook" value=${books.id}/>
                                <input class="acceptButton btn-outline-danger" type="submit" name="sumbit"
                                       value="<fmt:message key="delete_book"/>"/>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${role != 'GUEST'}">
        <div>
            <div class="commentBox">
                <h1 class="bodyBack"><fmt:message key="send_comment"/>: </h1>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="createnewcomment"/>
                    <input type="hidden" name="idbook" value="${books.id}"/>
                    <div>
                        <textarea style="height: 78px; width: 550px; resize: none" name="text"
                                  class="textRedactor"> </textarea>
                    </div>
                    <div>
                        <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                               value="<fmt:message key="send_comment"/>"/>
                    </div>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${role == 'GUEST'}">
        <div>
            <div class="commentBox">
                <h1 class="bodyBack"><fmt:message key="send_comment"/>: </h1>
                <form action="Controller" method="get">
                    <input type="hidden" name="command" value="gotologinpage"/>
                    <div>
                        <textarea style="height: 78px; width: 550px; resize: none" name="text"
                                  class="textRedactor"> </textarea>
                    </div>
                    <div class="rowText">
                        <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                               value="<fmt:message key="send_comment"/>"/>
                        <div class="textSize12" style="margin-left: 10px"> *<fmt:message
                                key="for_registered_user_only"/></div>
                    </div>
                </form>
            </div>
        </div>
    </c:if>

    <c:if test="${role != 'ADMIN'}">
        <div class="author">
            <c:forEach var="n" items="${comments}">
                <c:if test="${n.status == 0}">
                    <c:forEach var="user" items="${usersmain}">
                        <c:if test="${user.id == n.idUser}">
                            <div class="descriptionAuthor" style="margin: 10px">
                                <div class="formControllerColumn textForm1" style="width: 800px; margin-bottom: 10px">
                                    <div>
                                        <img src="picture/${user.picture}" width="45" height="60"/>
                                    </div>
                                    <div class="textBox">
                                        <div class="rowText">
                                            <div class="text">
                                                    ${user.name}
                                            </div>
                                            <div class="text" style="font-size: 9px">
                                                    ${n.dateAdd}
                                            </div>
                                        </div>
                                        <div class="text">
                                                ${n.comment}
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </div>
    </c:if>


    <c:if test="${role == 'ADMIN'}">
        <div class="author">
            <c:forEach var="n" items="${comments}">
                <c:forEach var="user" items="${usersmain}">
                    <c:if test="${user.id == n.idUser}">
                        <div class="descriptionAuthor" style="margin: 10px">
                            <div class="formControllerColumn textForm1" style="width: 800px; margin-bottom: 10px">
                                <div>
                                    <img src="picture/${user.picture}" width="45" height="60"/>
                                </div>
                                <div class="textBox">
                                    <div class="rowText">
                                        <div class="text">
                                                ${user.name}
                                        </div>
                                        <div class="text" style="font-size: 9px">
                                                ${n.dateAdd}
                                        </div>
                                    </div>
                                    <div class="text">
                                            ${n.comment}
                                    </div>

                                </div>
                                <c:if test="${n.status == 0 }">
                                    <form action="Controller" method="get">
                                        <input type="hidden" name="command" value="BlockCommentCommand"/>
                                        <input type="hidden" name="idbook" value="${books.id}"/>
                                        <input type="hidden" name="idcomment" value="${n.id}"/>
                                        <input type="hidden" name="iduser" value="${n.idUser}"/>
                                        <input class="acceptButton btn-outline-danger" type="submit" name="sumbit"
                                               value="<fmt:message key="block_comment"/>"/>
                                            <%--Может заменить на ссылку--%>
                                    </form>
                                </c:if>
                                <c:if test="${n.status == 1}">
                                    <form action="Controller" method="get">
                                        <input type="hidden" name="command" value="UnblockCommentCommand"/>
                                        <input type="hidden" name="idbook" value="${books.id}"/>
                                        <input type="hidden" name="idcomment" value="${n.id}"/>
                                        <input type="hidden" name="iduser" value="${n.idUser}"/>
                                        <input class="btn-outline-success acceptButton" type="submit" name="sumbit"
                                               value="<fmt:message key="unblock_comment"/>"/>
                                            <%--Может заменить на ссылку--%>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
    </c:if>
    </body>
</fmt:bundle>
</html>
