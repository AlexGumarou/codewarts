<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="app.director.teacher.price.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'><br>
                <form name="test" method="post" action="/director/teacherPrice">
                    <h2 class='welcome'><spring:message code="app.director.teacher.price.teacher"/>:</h2>
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="ListTeacher">
                            <c:forEach items="${ListTeacher}" var="item" >
                                <option value="${item.id}">${item.name} ${item.surname}</option>
                            </c:forEach>
                        </select><br><br>
                    <h2 class='welcome2'><spring:message code="app.director.teacher.price.per.hour"/>:</h2>
                    <spring:message code="app.director.teacher.price.per.hour.enter" var="enter"></spring:message>
                        <input type="text" placeholder="${enter}" name="sum" class='input-line full-width'
                               required><br>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <button class='ghost-round full-width' type="submit" ><spring:message code="app.save"/></button>
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