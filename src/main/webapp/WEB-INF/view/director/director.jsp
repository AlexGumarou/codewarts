<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="app.director.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'><br>
                <h2 class='welcome'><spring:message code="app.director.welcome"/>, ${name}</h2>
                <h2 class='welcome2' style="color: aqua"><em>${msg}</em></h2><br>
                <form  name="test" method="get" action="/director/addStaff">
                    <button class='ghost-round full-width' type="submit" ><spring:message code="app.director.add.staff"/></button>
                </form>
                <form  name="test" method="get" action="/director/teacherData">
                    <button class='ghost-round full-width' type="submit" ><spring:message code="app.director.time"/></button>
                </form>
                <form  name="test" method="get" action="/director/payments">
                    <button class='ghost-round full-width' type="submit" ><spring:message code="app.director.payment"/></button>
                </form>
                <form  name="test" method="get" action="/director/teacherPrice">
                    <button class='ghost-round full-width' type="submit" ><spring:message code="app.director.price.hour"/></button>
                </form><br><br>
                <form name="test" method="POST" action="/logout">
                    <button class='ghost-round full-width' type="submit" name="button"><spring:message code="app.exit"/></button>
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
