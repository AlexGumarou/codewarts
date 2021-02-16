<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <h2 class='welcome'>Добро пожаловать, ${name}</h2>
                    <select style="background: #8CA6DB" class='ghost-round full-width' >
                        <c:forEach items="${listThemePast}" var="item" >
                            <option>${item.date} было занятие по теме: ${item.theme.name}</option>
                        </c:forEach>
                    </select>
                <h3 class='welcome2'>Список прошедших тем данной группы:</h3>
                    <form name="test" method="post" action="/teacher">
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="themeName">
                            <c:forEach items="${listTheme}" var="item" >
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                <h3 class='welcome2'>Выберете тему сегодняшнего урока</h3><br>
                <h3 class='welcome2'>Отметьте, пожалуйста, всех присутствующих:</h3>
                    <div align="center">
                        <table border="0" width="350">
                            <c:forEach items="${listChild}" var="item" >
                                <tr><td><h2 class='input-lineLeft full-width'>${item.name} ${item.surname}</h2></td>
                                <td><input class='welcome' type="checkbox" name="child" value="${item.id}"></td>
                            </c:forEach>
                        </table>
                    <button class='ghost-round full-width' type="submit" name="button">сохранить</button>
                    </form>
                     </div>
                <form name="test" method="get" action="/teacher">
                    <button class='ghost-round full-width' type="submit" >на главную</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
