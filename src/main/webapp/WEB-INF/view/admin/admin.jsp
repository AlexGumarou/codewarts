<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Добро пожаловать, ${name}</h2><br>

    <label>
        <input style="display:inline-block" type="search" name="findChild" placeholder="Найти ребенка по фамилии">
    </label>
    <input type="submit" value="Найти"></p>

    <form name="test" method="get" action="/admin/addChild">
    <h2 style="display:inline-block">Добавить ребенка:</h2>
        <button type="submit" name="button">Добавить</button>
    </form>

    <form name="test" method="get" action="/admin/addStaff">
        <h2 style="display:inline-block">Добавить персонал:</h2>
        <button type="submit" name="button">Добавить</button>
    </form>

    <h2>Список групп</h2>
    <font color="blue">
    <h3>${msg}</h3>
    </font>
    <c:forEach items="${listGroups}" var="item"  >
        <form style="display:inline-block" name="test" method="get" action="/admin/${item.id}">
            <button type="submit" name="button" value="${item.id}">${item.name}</button>
        </form>
    </c:forEach>

    <form name="test" method="get" action="/login">
        <button type="submit" name="button">выход из программы</button>
    </form>
</div>
</body>
</html>
