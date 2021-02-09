<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Добро пожаловать, ${name}</h2><br>
    <h2 style="color: blue">${msg}</h2>
    <h2>Список групп</h2>
    <c:forEach items="${listGroups}" var="item"  >
            <form style="display:inline-block" name="test" method="get" action="/teacher/${item.id}">
                <button type="submit" name="button" value="${item.id}">${item.name}</button>
            </form>
    </c:forEach>

    <form name="test" method="get" action="/login">
        <button type="submit" name="button">выход из программы</button>
    </form>
</div>
</body>
</html>
