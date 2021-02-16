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
                <form name="test" method="post" action="/director/teacherData">
                    <h2 class='welcome'>Выберите учителя:</h2>
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="ListTeacher">
                            <c:forEach items="${ListTeacher}" var="item" >
                                <option value="${item.id}">${item.name} ${item.surname}</option>
                            </c:forEach>
                        </select><br><br>
                    <h2 class='welcome2'>Выберите дату:</h2>
                        <input class='ghost-round full-width' type="date" name="dateFirst" size="40"/>
                    <h2 class='welcome2'>Выберите дату:</h2>
                        <input class='ghost-round full-width' type="date" name="dateLast" size="40"/><br><br>
                    <button class='ghost-round full-width' type="submit" >найти</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>