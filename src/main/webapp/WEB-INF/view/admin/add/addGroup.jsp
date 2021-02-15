<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <form:form method="post" action="/admin/addGroup"><br><br><br>
                        <input type="text" placeholder="Введение название группы" name="name" class='input-line full-width' required></input><br><br>
                            <h3 class='welcome2'>Время урока:</h3>
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="lessonTime">
                            <option value="1 час">1 час</option>
                            <option value="1 час 20 минут">1 час 20 минут</option>
                        </select><br>
                        <input class='ghost-round full-width' type="submit" class="shine-button" size="100" value="сохранить">
                    </form:form><br>
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
