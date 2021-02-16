<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='bold-line'></div>
    <div class='container'>
        <div class='window'>
            <div class='overlay'>
                <div class='content'>
                <br><br>
                <h2 class='welcome'>Список всех учеников группы:</h2><br>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <table border="0">
                        <c:forEach items="${listChild}" var="item" >
                            <form name="test" method="get" action="/admin/child/${item.id}">
                                <button class='ghost-round full-width' type="submit">${item.name} ${item.surname}</button>
                            </form>
                        </c:forEach>
                    </table><br><br><br><br><br>
                <form name="test" method="post" action="/admin/edit/editGroups">
                    <button class='ghost-round full-width' type="submit" name="button" value="${idChildGroup}">Изменить название группы</button>
                </form>
                <form name="test" method="post" action="/admin/delete/group">
                    <button class='ghost-round full-width' type="submit" name="button" value="${idChildGroup}">Удалить группу</button>
                </form>
                <form name="test" method="get" action="/admin">
                    <button class='ghost-round full-width' type="submit">на главную</button>
                </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
