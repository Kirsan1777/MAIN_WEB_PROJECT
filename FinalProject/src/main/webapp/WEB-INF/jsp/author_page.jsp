<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value='${sessionScope.language}' scope="session"/>
<fmt:bundle basename="property.text">
    <%--
      Created by IntelliJ IDEA.
      User: Asus
      Date: 09.04.2021
      Time: 0:40
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <title>Author page</title>
        <style>
            <%@include file="/WEB-INF/css/basic-style.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body class="bg-image">
    <jsp:include page="header.jsp"/>

    <div class="author">
        <div class="descriptionAuthor">
            <div>
                <img src="picture/${users.picture}" width="100" height="140"/>
            </div>
            <div class="textForm2">
                <div class="textSizeMin"><fmt:message key="author"/></div>
                <div class="text">
                        ${users.name}
                </div>
                <div class="text">
                    <fmt:message key="date_registration"/> : ${users.dateRegistration}
                </div>
                <div class="text">
                    <fmt:message key="rating"/>: ${users.rating}
                </div>
            </div>

        </div>
        <div class="boxColumn">

            <div class="boxColumn">
                <h1><fmt:message key="books_author"/> :</h1>

                <c:forEach var="n" items="${books}">
                    <c:if test="${n.access == 0}">
                        <div class="formControllerColumn">
                            <div>
                                <img src="picture/${n.photoReference}" width="155" height="215"/>
                                <div class="rowButton">
                                    <form action="Controller" method="get">
                                        <input type="hidden" name="command" value="gotomainbookpage"/>
                                        <input type="hidden" name="idbook" value="${n.id}"/>
                                        <input class="twoButton hameleonColor" type="submit" name="sumbit"
                                               value="<fmt:message key="watch"/>"/>
                                    </form>

                                    <c:if test="${role != 'GUEST'}">
                                        <form action="Controller" method="post">
                                            <input type="hidden" name="command" value="buybookuser"/>
                                            <input type="hidden" name="idbook" value="${n.id}"/>
                                            <input type="hidden" name="idauthor" value="${n.authorId}"/>
                                            <input type="hidden" name="cost" value="${n.cost}"/>
                                            <input class="twoButton hameleonColor" type="submit" name="sumbit"
                                                   value="<fmt:message key="buy"/>"/>
                                        </form>
                                    </c:if>

                                    <c:if test="${role == 'GUEST'}">
                                        <form action="Controller" method="post">
                                            <input type="hidden" name="command" value="gotologinpage"/>
                                            <input class="twoButton hameleonColor" type="submit" name="sumbit"
                                                   value="<fmt:message key="buy"/>"/>
                                        </form>
                                    </c:if>

                                </div>
                            </div>
                            <div>
                                <p class="rowText"><c:out value="${n.nameBook}"/></p>
                                <p class="rowText"><fmt:message key="genres"/>: <c:out value="${n.genre}"/></p>
                                <p class="descriptionBook textSize12"><c:out value="${n.description}"/></p>
                                <p class="textSize12"><fmt:message key="date_add"/>: <c:out value="${n.dateAdd}"/></p>
                                <p class="textSize12"><fmt:message key="cost"/>: <c:out value="${n.cost}"/>р</p>
                            </div>
                        </div>

                    </c:if>

                </c:forEach>
            </div>

        </div>
    </div>
    </body>
</fmt:bundle>
</html>
