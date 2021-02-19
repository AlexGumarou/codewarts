<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="app.edit.group.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='container'>
    <div class='window'>
        <div class='overlay'>
            <div class='content'>
                <form method="post" action="/admin/edit/editGroupSave"><br><br>
                    <h2 class='welcome'><spring:message code="app.edit.group.new.name"/></h2>
                    <input class='input-line full-width' type="text" size="40" name="nameGroup" value="${childGroup.name}" required>
                    <input type="hidden" name="idGroup" value="${childGroup.id}"><br><br>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                    <spring:message code="app.save" var="save"></spring:message>
                    <input class='ghost-round full-width' type="submit" name="button" value="${save}">
                </form>
                <form name="test" method="get" action="/admin">
                    <button class='ghost-round full-width' type="submit"><spring:message code="app.to.main"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
