<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="app.director.payments.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'><br>
                <form name="test" method="post" action="/director/payments">
                    <h2 class='welcome'><spring:message code="app.director.payments.period"/>:</h2>
                    <h2 class='welcome2'><spring:message code="app.director.teacher.data.data"/>:</h2>
                    <input class='ghost-round full-width' type="date" name="dateFirst" size="40"/>
                    <h2 class='welcome2'><spring:message code="app.director.teacher.data.data"/>:</h2>
                    <input class='ghost-round full-width' type="date" name="dateLast" size="40"/><br><br>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <button class='ghost-round full-width' type="submit" >
                        <spring:message code="app.director.teacher.data.find"/></button>
                </form>
                <form name="test" method="get" action="/director">
                    <button class='ghost-round full-width' type="submit"><spring:message code="app.to.main"/></button>
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