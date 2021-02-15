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
                <form:form method="post" action="/admin/addPayment">
                    <br><br>
                    <input class='input-line full-width' type="date" name="date" value="${now}"><br><br>
                    <input type="text" placeholder="Введите сумму платежа" name="sum" class='input-line full-width' required></input><br><br>
                    <h3 class='welcome2'>Выберите ученика:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="listChild">
                        <c:forEach items="${listChild}" var="item" >
                        <option value="${item.id}">${item.name} ${item.surname}</option>
                        </c:forEach>
                    </select><br><br>
                    <input type="submit" class='ghost-round full-width' size="100" value="сохранить">
                </form:form><br><br><br><br><br>
                <form name="test" method="get" action="/admin">
                    <button class='ghost-round full-width' type="submit">на главную</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
