<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="app.teacher.child.group.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'><br><br>
                    <select style="background: #8CA6DB" class='ghost-round full-width' >
                        <c:forEach items="${listThemePast}" var="item" >
                            <option>${item.date} <spring:message code="app.teacher.child.group.lesson.was"/>:
                                    ${item.theme.name}</option>
                        </c:forEach>
                    </select>
                <h3 class='welcome2'><spring:message code="app.teacher.child.group.theme.last"/>:</h3>
                    <form name="test" method="post" action="/teacher">
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="themeName">
                            <c:forEach items="${listTheme}" var="item" >
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                <h3 class='welcome2'><spring:message code="app.teacher.child.group.theme.now"/></h3><br>
                <h3 class='welcome2'><spring:message code="app.teacher.child.group.check"/>:</h3>
                    <div align="center">
                        <table border="0" width="350">
                            <c:forEach items="${listChild}" var="item" >
                                <tr><td><h2 class='input-lineLeft full-width'>${item.name} ${item.surname}</h2></td>
                                <td><input class='welcome' type="checkbox" name="child" value="${item.id}"></td>
                            </c:forEach>
                        </table><br><br>
                    <button class='ghost-round full-width' type="submit" name="button"><spring:message code="app.save"/>
                    </button>
                    </form>
                     </div>
                <form name="test" method="get" action="/teacher">
                    <button class='ghost-round full-width' type="submit" ><spring:message code="app.to.main"/></button>
                </form>
        </div>
    </div>

</div>
<div align="top" >
    <a href="?lang=en"><img src="/img/eng.png"  width="50" height="30" border="1"></a><br>
    <a href="?lang=ru"><img src="/img/rus.png"  width="50" height="30" border="1"></a>
</div>
</body>
</html>
