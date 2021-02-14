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

<form:form method="post" action="/admin/addPayment">
    <table border="0" align="center">
        <tr>
            <th></th>
            <th></th>
        </tr>
        <tr><td>Дата платежа </td>
            </td><td><form>
                <input type="date" name="date" value="${now}">
            </form>
        <tr><td>Сумма платежа: </td><td><input type="text" name="sum"  size="40" required></td>
        <tr><td>Список учеников:
        </td><td><select name="listChild">
            <c:forEach items="${listChild}" var="item" >
                    <option value="${item.id}">${item.name} ${item.surname}</option>
            </c:forEach>
        </select><br></td>
    </table>
    <br>

    <div align="center">
        <input type="submit" class="shine-button" size="100" value="сохранить">
    </div>
</form:form>
<br>
<div align="center">
    <br><div align="center">
    <form style="display:inline-block" name="test" method="get" action="/admin">
        <button type="submit">на главную</button>
    </form>
</div>
</div>
</body>
</html>
