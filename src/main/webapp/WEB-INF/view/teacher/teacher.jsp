<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="app.teacher.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <br><h2 class='welcome'><spring:message code="app.teacher.welcome"/>, ${name}</h2>
                <h2 class='welcome2' style="color: aqua"><em>${msg}</em></h2>
                <h3 class='welcome'><spring:message code="app.teacher.listGroups"/></h3>
                    <c:forEach items="${listGroups}" var="item"  >
                        <form style="display:inline-block" name="test" method="post" action="/teacher/${item.id}">
                            <button class='ghost-round full-width' type="submit" name="button" value="${item.id}">
                                    ${item.name}</button>
                        </form>
                    </c:forEach><br><br>
                <form name="test" method="POST" action="/logout">
                    <button class='ghost-round full-width' type="submit" name="button"><spring:message code="app.exit"/>
                    </button>
                </form>
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
