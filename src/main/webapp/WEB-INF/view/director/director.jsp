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
            <div class='content'><br>
                <h2 class='welcome'>Добро пожаловать, ${name}</h2>
                <h2 class='welcome2' style="color: aqua"><em>${msg}</em></h2><br>
                <form  name="test" method="get" action="/director/addStaff">
                    <button class='ghost-round full-width' type="submit" >добавить персонал</button>
                </form>
                <form  name="test" method="get" action="/director/teacherData">
                    <button class='ghost-round full-width' type="submit" >данные по отработанному времени</button>
                </form>
                <form  name="test" method="get" action="/director/payments">
                    <button class='ghost-round full-width' type="submit" >данные по платежам учеников</button>
                </form>
                <form  name="test" method="get" action="/director/teacherPrice">
                    <button class='ghost-round full-width' type="submit" >установить стоимость часа</button>
                </form><br><br>
                <form name="test" method="POST" action="/logout">
                    <button class='ghost-round full-width' type="submit" name="button">выход из программы</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
