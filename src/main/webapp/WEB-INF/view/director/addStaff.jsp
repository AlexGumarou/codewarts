<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<form:form method="post" action="/director/addStaff" modelAttribute="staff">
    <table border="0" align="center">
        <tr>
            <th></th>
            <th></th>
        </tr>
        <tr><td>Логин:</td><td><form:input path="login" size="40"/>
            <form:errors cssStyle="color: red" path="login" cssClass="error"/></td>
        <tr><td>Пароль: </td><td><form:input path="pass" size="40"/>
            <form:errors cssStyle="color: red" path="pass" cssClass="error"/></td>
        <tr><td>Имя: </td><td><form:input path="name" size="40"/>
            <form:errors cssStyle="color: red" path="name" cssClass="error"/></td>
        <tr><td>Фамилия: </td><td><form:input path="surname" size="40"/>
            <form:errors cssStyle="color: red" path="surname" cssClass="error"/></td>
        <tr><td>Адрес: </td><td><form:input path="address" size="40"/>
            <form:errors cssStyle="color: red" path="address" cssClass="error"/></td>
        <tr><td>Электронная почта: </td><td><form:input path="email" size="40"/>
            <form:errors cssStyle="color: red" path="email" cssClass="error"/></td>
        <tr><td>Контактный телефон: </td><td><form:input path="phone" size="40"/>
            <form:errors cssStyle="color: red" path="phone" cssClass="error"/></td>
        <tr><td>Контактный телефон (доп):</td><td><form:input path="phoneAdditional" size="40"/></td>
        <tr><td>Стоимость за 1 час (для учителей):</td><td><form:input path="pricePerHour" size="40"/></td>
        <tr><td>Кого вы хотите добавить (роль):
        </td><td><select name="role">
            <c:forEach items="${listRoles}" var="item" >
                <option value="${item.id}">${item.role}</option>
            </c:forEach>
        </select><br></td>
        <tr><td>Выберите филиал:
        </td><td><select name="dep">
            <c:forEach items="${listDepartment}" var="item" >
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select><br></td>
    </table>
    <br>

    <div align="center">
        <input type="submit" class="shine-button" size="100" value="добавить">
    </div>
</form:form>
<br>
<div align="center">
    <br><div align="center">
    <form style="display:inline-block" name="test" method="get" action="/director">
        <button type="submit" name="button">к списку групп</button>
    </form>
</div>
</div>
</body>
</html>
