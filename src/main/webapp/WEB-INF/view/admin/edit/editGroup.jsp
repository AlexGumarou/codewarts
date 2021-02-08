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

<form:form method="post" action="/admin/edit/editGroupSave">
    <div align="center">
<H2>Выберите новое название группы:</H2><br>
        <input type="text" size="40" name="name" value="${childGroup.name}" required>
        <input type="hidden" name="id" value="${childGroup.id}">
            <input type="submit" size="40" value="изменить">
    </div>
</form:form>
</body>
</html>
