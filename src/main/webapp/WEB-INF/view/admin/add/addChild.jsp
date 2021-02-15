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
                <div align="center">
                    <form:form method="post" action="/admin/addChild" modelAttribute="child" ><br><br><br>
                        <input type="date" placeholder="Дата" name="date" class='input-lineLeft full-width' required></input>
                        <input type='text' placeholder="Имя ребенка" name="name" class='input-lineLeft full-width' required></input>
                        <input type='text' placeholder="Фамилия ребенка" name="surname" class='input-lineLeft full-width' required></input>
                        <input type='text' placeholder="ФИО мамы" name="mother" class='input-lineLeft full-width'></input>
                        <input type='text' placeholder="Контактный телефон" name="phoneMother" class='input-lineLeft full-width'></input>
                        <input type='text' placeholder="ФИО папы" name="father" class='input-lineLeft full-width'></input>
                        <input type='text' placeholder="Контактный телефон" name="phoneFather" class='input-lineLeft full-width'></input>
                        <h3 class='welcome2'>Номер группы:</h3>
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="selectGroup">
                            <c:forEach items="${listGroups}" var="item" >
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select><br><br><br>
                        <div align="center">
                            <input class='ghost-round full-width' type="submit" class="shine-button" size="100" value="добавить">
                        </div>
                    </form:form>
                            <div align="center">
                                <form name="test" method="get" action="/admin">
                                    <button class='ghost-round full-width' type="submit">на главную</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
