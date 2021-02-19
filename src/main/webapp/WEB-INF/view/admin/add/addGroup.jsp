<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="app.addGroup.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                    <form:form method="post" action="/admin/addGroup"><br><br><br>
                        <spring:message code="app.addGroup.name.group" var="nameGroup"></spring:message>
                        <input type="text" placeholder="${nameGroup}" name="name" class='input-line full-width' required></input><br><br>
                            <h3 class='welcome2'><spring:message code="app.addGroup.time.lesson"/>:</h3>
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="lessonTime">
                            <option value="1 час"><spring:message code="app.addGroup.one.hour"/></option>
                            <option value="1 час 20 минут"><spring:message code="app.addGroup.one.hour.twenty.minutes"/></option>
                        </select><br>
                        <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                        <spring:message code="app.save" var="save"></spring:message>
                        <input class='ghost-round full-width' type="submit" class="shine-button" size="100" value="${save}">
                    </form:form><br>
                        <form name="test" method="get" action="/admin">
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
