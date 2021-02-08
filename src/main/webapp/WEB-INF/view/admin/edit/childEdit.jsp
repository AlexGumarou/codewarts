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

<form:form method="post" action="/admin/child/${child.id}" modelAttribute="child">
    <table border="0" align="center">
        <tr>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <tr><td>Дата рождения </td>
            </td><td><form>
                <input type="date" name="date" value="${child.birthdayDate}">
            </form>
        <tr><td>Имя ребенка: </td><td><input type="text" name="name" value ="${child.name}" size="40"></td>
        <tr><td>Фамилия ребенка: </td><td><input type="text" name="surname" value ="${child.surname}" size="40"></td>
        <tr><td>ФИО мамы: </td><td><input type="text" name="mother" value ="${child.parent.mother}" size="40"></td>
        <tr><td>Контактный телефон: </td><td><input type="text" name="phoneMother" value ="${child.parent.phoneMother}" size="40"></td>
        <tr><td>ФИО папы: </td><td><input type="text" name="father" value ="${child.parent.father}" size="40"></td>
        <tr><td>Контактный телефон: </td><td><input type="text" name="phoneFather" value ="${child.parent.phoneFather}" size="40"></td>
        <tr><td>Номер группы:
        </td><td><select name="selectGroup">
                <c:forEach items="${listGroups}" var="item" >
                    <c:if test="${item.name == child.childGroup.name}" >
                        <option selected="selected" value="${child.childGroup.id}">${child.childGroup.name}</option>
                    </c:if>
                    <c:if test="${item.name != child.childGroup.name}" >
                        <option value="${item.id}">${item.name}</option>
                    </c:if>
                </c:forEach>
    </select><br></td>
        <tr><td>Даты посещений:
        </td><td><select name="selectAttendance">
            <c:forEach items="${listAttendance}" var="item" >
                    <option>${item}</option>
            </c:forEach>
        </select><br></td>
        <form style="display:inline-block" name="test" method="post" action="/admin/edit/editPayment">
        <tr><td>Даты платежей:</td>
        <td><select name="idPayment" style="display:inline-block">
            <c:forEach items="${listPayments}" var="item" >
                <option value="${item.id}">${item}</option>
            </c:forEach>
        </select>
            <button type="submit" >Редактировать оплаты</button></div>
        </form></td>
    </table>
    <br>

    <div align="center">
    <input type="submit" class="shine-button" size="100" value="сохранить">
    </div>
</form:form>
<br>
<div align="center">
<form style="display:inline-block" name="test" method="post" action="/admin/delete">
    <input type="hidden" name="idChild" value="${child.id}">
    <button type="submit" name="delete">Удалить</button>
</form>
<br><div align="center">
<form style="display:inline-block" name="test" method="get" action="/admin">
    <button type="submit" name="button">к списку групп</button>
</form>
</div>
</div>
</body>
</html>
