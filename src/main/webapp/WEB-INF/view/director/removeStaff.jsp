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
                <form name="test" method="post" action="/director/removeStaff">
                    <h2 class='welcome2'><spring:message code="app.director.remove.staff"/>:</h2>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="listStaff">
                        <c:forEach items="${listStaff}" var="item" >
                            <option value="${item.id}">${item.login}</option>
                        </c:forEach>
                    </select><br><br>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <button class='ghost-round full-width' type="submit" ><spring:message code="app.delete"/></button>
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