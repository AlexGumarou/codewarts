<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Добро пожаловать, ${name}</h2><br>

    <form name="test" method="post" action="/admin/search">
        <input style="display:inline-block" type="search" name="findChild" placeholder="Найти ребенка по фамилии">
        <button type="submit" name="button">Найти</button>
    </form>

    <form style="display:inline-block" name="test" method="get" action="/admin/addChild">
        <button type="submit" name="button">Добавить ребенка</button>
    </form>

    <form style="display:inline-block" name="test" method="get" action="/admin/addPayment">
        <button type="submit" >Добавить оплаты</button>
    </form>
    <form style="display:inline-block" name="test" method="get" action="/admin/addGroup">
        <button type="submit" >Добавить группу</button>
    </form>
    <font color="blue">
        <h3>${msg}</h3>
    </font>
    <h2>Список групп</h2>

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
