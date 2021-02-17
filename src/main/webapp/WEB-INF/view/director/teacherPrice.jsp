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
                <form name="test" method="post" action="/director/teacherPrice">
                    <h2 class='welcome'>Выберите учителя:</h2>
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="ListTeacher">
                            <c:forEach items="${ListTeacher}" var="item" >
                                <option value="${item.id}">${item.name} ${item.surname}</option>
                            </c:forEach>
                        </select><br><br>
                    <h2 class='welcome2'>Установить сумму за 1 час:</h2>
                        <input type="text" placeholder="введите сумму" name="sum" class='input-line full-width' required></input><br>
                    <button class='ghost-round full-width' type="submit" >сохранить</button>
                </form>
                <form name="test" method="get" action="/director">
                    <button class='ghost-round full-width' type="submit">на главную</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>