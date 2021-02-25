<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="app.addChild.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <div align="center">
                    <form:form method="post" action="/admin/addChild" modelAttribute="child" ><br><br><br>
                        <spring:message code="app.addChild.add.date" var="data"></spring:message>
                        <input type="date" placeholder="${data}" name="birthdayDate"
                               class='input-lineLeft full-width' required>
                        <spring:message code="app.addChild.add.name" var="name"></spring:message>
                        <form:input path="name" placeholder="${name}"  class='input-lineLeft full-width'/>
                        <form:errors cssStyle="color: red" path="name" cssClass="error"/>
                        <spring:message code="app.addChild.add.surname" var="surname"></spring:message>
                        <form:input path="surname" placeholder="${surname}"  class='input-lineLeft full-width'/>
                        <form:errors cssStyle="color: red" path="surname" cssClass="error"/>
                        <form:form method="post" modelAttribute="parent" >
                        <spring:message code="app.addChild.add.mother" var="mother"></spring:message>
                            <form:input path="mother" placeholder="${mother}"  class='input-lineLeft full-width'/>
                        <spring:message code="app.addChild.add.phone" var="phone"></spring:message>
                            <form:input path="phoneMother" placeholder="${phone}"  class='input-lineLeft full-width'/>
                        <spring:message code="app.addChild.add.father" var="father"></spring:message>
                            <form:input path="father" placeholder="${father}"  class='input-lineLeft full-width'/>
                        <spring:message code="app.addChild.add.father.phone" var="phoneFather"></spring:message>
                            <form:input path="phoneFather" placeholder="${phoneFather}"
                                        class='input-lineLeft full-width'/>
                        <h3 class='welcome2'><spring:message code="app.addChild.group.number"/></h3>
                        <select style="background: #8CA6DB" class='ghost-round full-width' name="selectGroup">
                            <c:forEach items="${listGroups}" var="item" >
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select><br><br><br>
                        <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                        <div align="center">
                            <spring:message code="app.addChild.add" var="add"></spring:message>
                            <input class='ghost-round full-width' type="submit" size="100" value="${add}">
                        </div>
                        </form:form>
                    </form:form>
                            <div align="center">
                                <form name="test" method="get" action="/admin">
                                    <button class='ghost-round full-width' type="submit">
                                        <spring:message code="app.to.main"/></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    <div align="top" >
        <a href="?lang=en"><img src="/img/eng.png"  width="50" height="30" border="1"></a><br>
        <a href="?lang=ru"><img src="/img/rus.png"  width="50" height="30" border="1"></a>
    </div>
        </div>
    </div>
</div>
</body>
</html>
