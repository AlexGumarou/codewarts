<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        select {
            width: 293px; /* Ширина списка в пикселах */
        }
    </style>
</head>
<body>

<form:form method="post" action="/admin/edit/editPaymentSave">
    <table border="0" align="center">
    <tr>
    <th></th>
    <th></th>
    </tr>
        <tr><td>Дата платежа</td>
            <td>Сумма платежа</td>
    <c:forEach items="${paymentToEdit}" var="item" >
        <tr><td>

        <input type="date" name="date" value="${item.paymentDate}"></td>
        <td><input type="text" name="sum" value ="${item.sum}" size="40" required></td>
        <input type="hidden" name="idPayment" value="${item.id}">
    </c:forEach>
    </table>
    <br>
    <div align="center">
        <input type="submit" class="shine-button" size="100" value="сохранить">
    </div>
</form:form>
</body>
</html>
