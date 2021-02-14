<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        select {
            width: 293px; /* Ширина списка в пикселах */
        }
    </style>
</head>
<body>

<form:form method="post" action="/admin/addGroup">
    <table border="0" align="center">
        <tr>
            <th></th>
            <th></th>
        </tr>
        <tr><td>Название группы: </td><td><input type="text" name="name"  size="40" required></td>
        <tr><td>Время урока: </td><td><select name="lessonTime">
                <option value="1 час">1 час</option>
                <option value="1 час 20 минут">1 час 20 минут</option>
            </select><br></td>
    </table>
    <br>

    <div align="center">
        <input type="submit" class="shine-button" size="100" value="сохранить">
    </div>
</form:form>
<br>
<div align="center">
    <br><div align="center">
    <form style="display:inline-block" name="test" method="get" action="/admin">
        <button type="submit">на главную</button>
    </form>
</div>
</div>
</body>
</html>
