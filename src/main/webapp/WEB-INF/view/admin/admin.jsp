<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="app.admin.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>

<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <div align="center">
                    <br><h2 class='welcome'><spring:message code="app.admin.welcome"/>, ${name}</h2>
                    <c:forEach items="${listBirthday}" var="item" >
                    <h3 class='welcome2' style="color: aqua" ><em>${item.birthdayDate.getDayOfMonth()}.
                            ${item.birthdayDate.getMonthValue()} <spring:message code="app.admin.birthday"/>
                            ${item.name} ${item.surname}</em></h3>
                    </c:forEach>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <form name="test" method="post" action="/admin/search">
                        <spring:message code="app.admin.findBySurname" var="find"></spring:message>
                        <input class='input-line full-width'  type="search" name="findChild" placeholder="${find}">
                        <button class='ghost-round full-width' type="submit" name="button">
                            <spring:message code="app.admin.find"/></button>
                    </form>
                    <form name="test" method="get" action="/admin/addChild">
                        <button class='ghost-round full-width' type="submit">
                            <spring:message code="app.admin.addChild"/></button>
                    </form>
                    <form name="test" method="get" action="/admin/addPayment">
                        <button class='ghost-round full-width' type="submit">
                            <spring:message code="app.admin.addPayments"/></button>
                    </form>
                    <form name="test" method="get" action="/admin/addGroup">
                        <button class='ghost-round full-width' type="submit">
                            <spring:message code="app.admin.addGroup"/></button>
                    </form>
                    <h3 class='welcome'><spring:message code="app.admin.listGroups"/></h3>
                    <c:forEach items="${listGroups}" var="item"  >
                    <form style="display:inline-block" name="test" method="post" action="/admin/${item.id}">
                        <button class='ghost-round full-width' type="submit" name="button"
                                value="${item.id}">${item.name}</button>
                    </form>
                    </c:forEach>
                    <br><br>
                    <form name="test" method="POST" action="/logout">
                        <button class='ghost-round full-width' type="submit" name="button">
                            <spring:message code="app.exit"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div align="top" >
        <a href="?lang=en"><img src="/img/eng.png"  width="50" height="30" border="1"></a><br>
        <a href="?lang=ru"><img src="/img/rus.png"  width="50" height="30" border="1"></a>
    </div>
</div>
</body>
</html>