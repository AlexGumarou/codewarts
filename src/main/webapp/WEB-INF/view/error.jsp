<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>AccessDenied page</title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='bold-line'></div>
<div class='container'>
    <div class='content'>
        <h2 class='welcome'>Что-то пошло не так. :(<br>Возможно, что у Вас нет доступа
            <br>Возможно, что такой страницы не существует</h2>
        <form name="test" method="POST" action="/logout">
            <button class='ghost-round full-width' type="submit" name="button">на главную</button>
        </form>
    </div>
</div>
</body>
</html>