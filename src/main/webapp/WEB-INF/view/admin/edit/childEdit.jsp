<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="app.child.edit.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <form:form method="post" action="/admin/child/${child.id}" modelAttribute="child">
                    <spring:message code="app.addChild.add.date" var="date"></spring:message>
                    <input type="date" placeholder="${date}" name="date" value="${child.birthdayDate}" class='input-lineEdit full-width' required></input>
                    <spring:message code="app.addChild.add.name" var="name"></spring:message>
                    <input type='text' placeholder="${name}" name="name" value="${child.name}" class='input-lineEdit full-width' required></input>
                    <spring:message code="app.addChild.add.surname" var="surname"></spring:message>
                    <input type='text' placeholder="${surname}" name="surname" value="${child.surname}" class='input-lineEdit full-width' required></input>
                    <spring:message code="app.addChild.add.mother" var="mother"></spring:message>
                    <input type='text' placeholder="${mother}" name="mother" value="${child.parent.mother}" class='input-lineEdit full-width'></input>
                    <spring:message code="app.addChild.add.phone" var="phone"></spring:message>
                    <input type='text' placeholder="${phone}" name="phoneMother" value="${child.parent.phoneMother}" class='input-lineEdit full-width'></input>
                    <spring:message code="app.addChild.add.father" var="father"></spring:message>
                    <input type='text' placeholder="${father}" name="father" value="${child.parent.father}" class='input-lineEdit full-width'></input>
                    <spring:message code="app.addChild.add.father.phone" var="phoneFather"></spring:message>
                    <input type='text' placeholder="${phoneFather}" name="phoneFather" value="${child.parent.phoneFather}" class='input-lineEdit full-width'></input>
                    <h3 class='welcome2'><spring:message code="app.addChild.group.number"/>:</h3>
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
                    <spring:message code="app.save" var="save"></spring:message>
                    <input class='ghost-round full-width' type="submit" value="${save}">
                </form:form>
                    <h3 class='welcome2'><spring:message code="app.child.edit.date.attendance"/>:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="selectAttendance">
                        <c:forEach items="${listAttendance}" var="item" >
                            <option>${item}</option>
                        </c:forEach>
                    </select>
                    <h3 class='welcome2'><spring:message code="app.child.edit.date.payments"/>:</h3>
                    <form name="test" method="post" action="/admin/edit/editPayment">
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="idPayment">
                            <c:forEach items="${listPayments}" var="item" >
                                <option value="${item.id}">${item}</option>
                            </c:forEach>
                        </select>
                        <spring:message code="child.edit.payments" var="edit"></spring:message>
                        <input class='ghost-round full-width' type="submit" value="${edit}">
                    </form>
                    <form name="test" method="post" action="/admin/delete">
                        <input type="hidden" name="idChild" value="${child.id}">
                        <button class='ghost-roundEdit full-width' type="submit" name="delete"><spring:message code="app.delete"/></button>
                    </form>
                </div>
            </div>
        </div>
    <div align="top" >
        <a href="?lang=en"><img src="/img/eng.png"  width="50" height="30" border="1"></a><br>
        <a href="?lang=ru"><img src="/img/rus.png"  width="50" height="30" border="1"></a>
    </div>
    </div>
</body>
</html>
