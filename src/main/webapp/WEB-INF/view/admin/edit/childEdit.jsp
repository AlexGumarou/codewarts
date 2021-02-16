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
                <form:form method="post" action="/admin/child/${child.id}" modelAttribute="child">
                    <input type="date" placeholder="Дата" name="date" value="${child.birthdayDate}" class='input-lineEdit full-width' required></input>
                    <input type='text' placeholder="Имя ребенка" name="name" value="${child.name}" class='input-lineEdit full-width' required></input>
                    <input type='text' placeholder="Фамилия ребенка" name="surname" value="${child.surname}" class='input-lineEdit full-width' required></input>
                    <input type='text' placeholder="ФИО мамы" name="mother" value="${child.parent.mother}" class='input-lineEdit full-width'></input>
                    <input type='text' placeholder="Контактный телефон" name="phoneMother" value="${child.parent.phoneMother}" class='input-lineEdit full-width'></input>
                    <input type='text' placeholder="ФИО папы" name="father" value="${child.parent.father}" class='input-lineEdit full-width'></input>
                    <input type='text' placeholder="Контактный телефон" name="phoneFather" value="${child.parent.phoneFather}" class='input-lineEdit full-width'></input>
                    <h3 class='welcome2'>Номер группы:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="selectGroup">
                        <c:forEach items="${listGroups}" var="item" >
                            <c:if test="${item.name == child.childGroup.name}" >
                                <option selected="selected" value="${child.childGroup.id}">${child.childGroup.name}</option>
                            </c:if>
                            <c:if test="${item.name != child.childGroup.name}" >
                                <option value="${item.id}">${item.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <input class='ghost-round full-width' type="submit" value="сохранить">
                </form:form>
                    <h3 class='welcome2'>Даты посещений:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="selectAttendance">
                        <c:forEach items="${listAttendance}" var="item" >
                            <option>${item}</option>
                        </c:forEach>
                    </select>
                    <h3 class='welcome2'>Даты платежей:</h3>
                    <form name="test" method="post" action="/admin/edit/editPayment">
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="idPayment">
                            <c:forEach items="${listPayments}" var="item" >
                                <option value="${item.id}">${item}</option>
                            </c:forEach>
                        </select>
                        <input class='ghost-round full-width' type="submit" value="редактировать оплаты">
                    </form>
                    <form name="test" method="post" action="/admin/delete">
                        <input type="hidden" name="idChild" value="${child.id}">
                        <button class='ghost-roundEdit full-width' type="submit" name="delete">удалить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
