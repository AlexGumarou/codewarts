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
                <div align="center">
                    <br><h2 class='welcome'>Добро пожаловать, ${name}</h2>
                    <c:forEach items="${listBirthday}" var="item" >
                    <h3 class='welcome2' style="color: aqua" ><em>${item.birthdayDate} день рождения у
                            ${item.name} ${item.surname}</em></h3>
                    </c:forEach>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <form name="test" method="post" action="/admin/search">
                        <input class='input-line full-width'  type="search" name="findChild" placeholder="Найти ребенка по фамилии">
                        <button class='ghost-round full-width' type="submit" name="button">Найти</button>
                    </form>
                    <form name="test" method="get" action="/admin/addChild">
                        <button class='ghost-round full-width' type="submit">Добавить ребенка</button>
                    </form>
                    <form name="test" method="get" action="/admin/addPayment">
                        <button class='ghost-round full-width' type="submit">Добавить оплаты</button>
                    </form>
                    <form name="test" method="get" action="/admin/addGroup">
                        <button class='ghost-round full-width' type="submit">Добавить группу</button>
                    </form>
                    <h3 class='welcome'>Список групп</h3>
                    <c:forEach items="${listGroups}" var="item"  >
                    <form style="display:inline-block" name="test" method="post" action="/admin/${item.id}">
                        <button class='ghost-round full-width' type="submit" name="button" value="${item.id}">${item.name}</button>
                    </form>
                    </c:forEach>
                    <br><br>
                    <form name="test" method="POST" action="/logout">
                        <button class='ghost-round full-width' type="submit" name="button">выход из программы</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>