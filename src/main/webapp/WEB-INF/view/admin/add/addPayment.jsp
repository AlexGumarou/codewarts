<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="app.addPayment.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <form:form method="post" action="/admin/addPayment">
                    <br><br>
                    <input class='input-line full-width' type="date" name="date" value="${now}"><br><br>
                    <spring:message code="app.addPayment.sum" var="sum"></spring:message>
                    <input type="text" placeholder="${sum}" name="sum" class='input-line full-width' required></input><br><br>
                    <h3 class='welcome2'><spring:message code="app.addPayment.choose"/>:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="listChild">
                        <c:forEach items="${listChild}" var="item" >
                        <option value="${item.id}">${item.name} ${item.surname}</option>
                        </c:forEach>
                    </select><br><br>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <spring:message code="app.save" var="save"></spring:message>
                    <input type="submit" class='ghost-round full-width' size="100" value="${save}">
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
