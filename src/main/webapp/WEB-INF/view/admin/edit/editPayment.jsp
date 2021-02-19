<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="app.edit.payment.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <form method="post" action="/admin/edit/editPaymentSave">
                    <c:forEach items="${paymentToEdit}" var="item" >
                        <h2 class='welcome'><spring:message code="app.edit.payment.date"/></h2>
                        <input class='input-lineLeft full-width' type="date" name="date" value="${item.paymentDate}">
                        <h2 class='welcome'><spring:message code="app.edit.payment.sum"/></h2>
                        <input class='input-line full-width' type="text" name="sum" value ="${item.sum}" size="40" required>
                        <input type="hidden" name="idPayment" value="${item.id}">
                    </c:forEach>
                    <spring:message code="app.save" var="save"></spring:message>
                    <input class='ghost-round full-width' type="submit" value="${save}">
                </form>
                <form name="test" method="get" action="/admin">
                    <button class='ghost-round full-width' type="submit"><spring:message code="app.to.main"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
