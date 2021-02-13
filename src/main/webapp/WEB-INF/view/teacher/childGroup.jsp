<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
</head>
<body>
<div class="container">
<div align="center">
    <h2>Добро пожаловать, ${name}</h2>
    <h2 style="display:inline-block">Список прошедших тем данной группы:</h2>
    <select class="selectpicker" data-style="btn-info" data-live-search="true" style="width: auto">
        <c:forEach items="${listThemePast}" var="item" >
            <option>${item.date} было занятие по теме: ${item.theme.name}</option>
        </c:forEach>
    </select>
    <br>
    <form name="test" method="post" action="/teacher">
        <h2 style="display:inline-block">Пожайлуйста, выберете тему сегодняшнего урока</h2>

    <select name="themeName" class="selectpicker" data-style="btn-info" data-live-search="true">
        <c:forEach items="${listTheme}" var="item" >
        <option  value="${item.id}">${item.name}</option>
        </c:forEach>
    </select>

    <br>
        <h2>Отметьте, пожалуйста, всех присутствующих:</h2>
    <table border="1">

    <c:forEach items="${listChild}" var="item" >
        <div style="display:inline-block">
            <tr><td><h2>${item.name} ${item.surname}</h2></td>
        </div>
        <td><input type="checkbox" name="child" value="${item.id}"></td><br>
    </c:forEach>
    </table><br>

        <button type="submit" name="button">Save</button>
    </form>
    <form name="test" method="get" action="/teacher">
        <button type="submit" name="button">к списку групп</button>
    </form>
</div>
</div>
</body>
</html>
