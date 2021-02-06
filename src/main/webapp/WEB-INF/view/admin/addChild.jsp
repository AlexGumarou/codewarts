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

<form:form method="post" action="/admin/addChild" modelAttribute="child">
    <table border="0" align="center">
        <tr>
            <th></th>
            <th></th>
        </tr>
        <tr><td>Дата рождения </td>
            </td><td><form>
                <input type="date" name="date" />
            </form>
        <tr><td>Имя ребенка: </td><td><input type="text" name="name" size="40"></td>
        <tr><td>Фамилия ребенка: </td><td><input type="text" name="surname" size="40"></td>
        <tr><td>ФИО мамы: </td><td><input type="text" name="mother" size="40"></td>
        <tr><td>Контактный телефон: </td><td><input type="text" name="phoneMother"  size="40"></td>
        <tr><td>ФИО папы: </td><td><input type="text" name="father"  size="40"></td>
        <tr><td>Контактный телефон: </td><td><input type="text" name="phoneFather" size="40"></td>
        <tr><td>Номер группы:
        </td><td><select name="selectGroup">
            <c:forEach items="${listGroups}" var="item" >
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
    <form style="display:inline-block" name="test" method="get" action="/admin">
        <button type="submit" name="button">к списку групп</button>
    </form>
</div>
</div>
</body>
</html>
