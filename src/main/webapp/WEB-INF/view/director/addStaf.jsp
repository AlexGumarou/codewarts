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
                <form:form method="post" action="/director/addStaff" modelAttribute="staff">
                    <input type='text' placeholder="логин" name="login" class='input-lineLeft full-width' required></input></td>
                    <input type='text' placeholder="пароль" name="pass" class='input-lineLeft full-width' required></input></td>
                    <input type='text' placeholder="имя" name="name" class='input-lineLeft full-width' required></input></td>
                    <input type='text' placeholder="фамилия" name="surname" class='input-lineLeft full-width' required></input></td>
                    <input type='text' placeholder="адрес" name="address" class='input-lineLeft full-width' required></input></td>
                    <input type='email' placeholder="электронная почта" name="email" class='input-lineLeft full-width' required></input></td>
                    <input type='text' placeholder="контактный телефон" name="phone" class='input-lineLeft full-width' required></input></td>
                    <input type='text' placeholder="контактный телефон (доп)" name="phoneAdditional" class='input-lineLeft full-width'></input></td>
                    <input type='text' placeholder="стоимость за 1 час (для учителей)" name="pricePerHour" class='input-lineLeft full-width'></input></td>
                    <h3 class='welcome2'>Кого вы хотите добавить (роль):</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="role">
                        <c:forEach items="${listRoles}" var="item" >
                            <option value="${item.id}">${item.role}</option>
                        </c:forEach>
                    </select>
                    <h3 class='welcome2'>Выберите филиал:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="dep">
                        <c:forEach items="${listDepartment}" var="item" >
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                    <input class='ghost-round full-width' type="submit" value="добавить">
                </form:form>
                <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <form name="test" method="get" action="/director">
                        <button class='ghost-round full-width' type="submit">на главную</button>
                    </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
