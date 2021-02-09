<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Добро пожаловать, ${name}</h2>
    <h3 style="color: blue">${msg}</h3>
    <form style="display:inline-block" name="test" method="get" action="/director/addStaff">
        <button type="submit" >Добавить персонал</button>
    </form>


<table border="1">
    <tr>
        <th><h2>Данные по отработанному <br>времени:</h2></th>
        <th><h2>Данные по платежам <br> учеников:</h2></th>
        <th><h2>Установить стоимость <br>часа:</h2></th>
    </tr>
    <tr><td>
        <form style="display:inline-block" name="test" method="post" action="/director/teacherData">
            <div align="center">
            <h3>Выберите учителя:</h3>
            <select name="ListTeacher">
                <c:forEach items="${ListTeacher}" var="item" >
                    <option value="${item.id}">${item.name} ${item.surname}</option>
                </c:forEach>
            </select>
            <br><br>
            C:<input style="display:inline-block" type="date" name="dateFirst" size="40"/>
            По:<input style="display:inline-block" type="date" name="dateLast" size="40"/>
            <br><br>
            <button type="submit" >Найти</button>
            </div>
        </form>
    </td>
    <td>
        <form style="display:inline-block" name="test" method="post" action="/director/payments">
            <div align="center">
                <h3>Выберите период</h3>
                <br><br>
                C:<input style="display:inline-block" type="date" name="dateFirst" size="40"/>
                По:<input style="display:inline-block" type="date" name="dateLast" size="40"/>
                <br><br>
                <button type="submit" >Найти</button>
            </div>
        </form>
    </td>
    <td>
        <form style="display:inline-block" name="test" method="post" action="/director/teacherPrice">
            <div align="center">
                <h3>Выберите учителя:</h3>
                <select name="ListTeacher">
                    <c:forEach items="${ListTeacher}" var="item" >
                        <option value="${item.id}">${item.name} ${item.surname}</option>
                    </c:forEach>
                </select>
                <br>
                <h3>Установить сумму за 1 час:</h3>
                <input type="text" name="sum" size="10">
                <button type="submit" >Сохранить</button>
            </div>
        </form>
    </td></tr>
</table>
    <h2 style="color: blue">${resultMsg}</h2>

<form name="test" method="get" action="/login">
    <button type="submit" name="button">выход из программы</button>
</form>
</div>
</body>
</html>
