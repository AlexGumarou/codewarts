<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="app.director.add.staff.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <form:form method="post" action="/director/addStaff" modelAttribute="staff">
                    <spring:message code="app.director.add.staff.login" var="login"></spring:message>
                    <form:input path="login" placeholder="${login}"  class='input-lineLeft full-width'/>
                    <form:errors cssStyle="color: red" path="login" cssClass="error"/>
                    <spring:message code="app.director.add.staff.pass" var="pass"></spring:message>
                    <form:password path="pass" placeholder="${pass}" class='input-lineLeft full-width' />
                    <form:errors cssStyle="color: red" path="pass" cssClass="error"/>
                    <spring:message code="app.director.add.staff.name" var="name"></spring:message>
                    <form:input path="name" placeholder="${name}"  class='input-lineLeft full-width'/>
                    <form:errors cssStyle="color: red" path="name" cssClass="error"/>
                    <spring:message code="app.director.add.staff.surname" var="surname"></spring:message>
                    <form:input path="surname" placeholder="${surname}"  class='input-lineLeft full-width'/>
                    <form:errors cssStyle="color: red" path="surname" cssClass="error"/>
                    <spring:message code="app.director.add.staff.address" var="address"></spring:message>
                    <form:input path="address" placeholder="${address}"  class='input-lineLeft full-width'/>
                    <form:errors cssStyle="color: red" path="address" cssClass="error"/>
                    <spring:message code="app.director.add.staff.email" var="email"></spring:message>
                    <form:input path="email" placeholder="${email}"  class='input-lineLeft full-width'/>
                    <form:errors cssStyle="color: red" path="email" cssClass="error"/>
                    <spring:message code="app.director.add.staff.phone" var="phone"></spring:message>
                    <form:input path="phone" placeholder="${phone}"  class='input-lineLeft full-width'/>
                    <form:errors cssStyle="color: red" path="phone" cssClass="error"/>
                    <spring:message code="app.director.add.staff.phoneadd" var="phoneadd"></spring:message>
                    <form:input path="phoneAdditional" placeholder="${phoneadd}"  class='input-lineLeft full-width'/>
                    <spring:message code="app.director.add.staff.price" var="price"></spring:message>
                    <form:input path="pricePerHour" placeholder="${price}"  class='input-lineLeft full-width'/>
                    <h3 class='welcome2'><spring:message code="app.director.add.staff.role"/>:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="role">
                        <c:forEach items="${listRoles}" var="item" >
                            <option value="${item.id}">${item.role}</option>
                        </c:forEach>
                    </select>
                    <h3 class='welcome2'><spring:message code="app.director.add.staff.dep"/>:</h3>
                    <select style="background: #8CA6DB" class='ghost-round full-width' name="dep">
                        <c:forEach items="${listDepartment}" var="item" >
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select><br>
                    <spring:message code="app.director.add.staff.add" var="add"></spring:message>
                    <input class='ghost-round full-width' type="submit" value="${add}">
                </form:form>
                <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <form name="test" method="get" action="/director">
                        <button class='ghost-round full-width' type="submit"><spring:message code="app.to.main"/>
                        </button>
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
