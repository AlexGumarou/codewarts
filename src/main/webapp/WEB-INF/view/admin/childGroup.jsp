<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Добро пожаловать, ${name}</h2>
        <h2>Список всех учеников группы:</h2>
        <table border="0">

            <c:forEach items="${listChild}" var="item" >
                <div style="display:inline-block">
                    <tr><td><h2><a href="/admin/child/${item.id}">${item.name} ${item.surname}</a></h2></td>
                </div>
            </c:forEach>
        </table><br>

    <form name="test" method="get" action="/admin">
        <button type="submit" name="button">к списку групп</button>
    </form>
</div>
</body>
</html>
