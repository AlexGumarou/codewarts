<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="app.index.title"/></title>
    <link href = "css/style.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<form action="/login" method="post">
    <div class='container'>
        <div class='window'>
            <div class='overlay'>
                <div class='content'>
                    <div class='welcome'><spring:message code="app.index.welcome"/><br>Codewarts</div>
                    ${msg}
                    <div class='input-fields'>
                        <spring:message code="app.login" var="login"></spring:message>
                        <input type='text' placeholder="${login}" name="login" class='input-line full-width'></input>
                        <spring:message code="app.pass" var="pass"></spring:message>
                        <input type='text' placeholder='${pass}' name="pass" class='input-line full-width'></input>
                    </div>
                    <br><br>
                    <button class='ghost-round full-width'><spring:message code="app.index.enter"/></button>
                </div>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
