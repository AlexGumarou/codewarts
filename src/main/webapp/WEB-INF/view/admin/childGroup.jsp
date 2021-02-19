<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="app.childGroup.title"/></title>
    <link href = "/css/style_admin.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<div class='bold-line'></div>
    <div class='container'>
        <div class='window'>
            <div class='overlay'>
                <div class='content'>
                <br><br>
                <h2 class='welcome'><spring:message code="app.childGroup.list.Child"/>:</h2><br>

                    <table border="0">
                        <c:forEach items="${listChild}" var="item" >
                            <form name="test" method="get" action="/admin/child/${item.id}">
                                <button class='ghost-round full-width' type="submit">${item.name}
                                        ${item.surname}</button>
                            </form>
                        </c:forEach>
                    </table><br><br><br><br><br>
                    <h3 class='welcome2' style="color: aqua" ><em>${msg}</em></h3>
                <form name="test" method="get" action="/admin/edit/editGroups">
                    <button class='ghost-round full-width' type="submit" name="button"
                            value="${idChildGroup}"><spring:message code="app.childGroup.change.group.name"/></button>
                </form>
                <form name="test" method="post" action="/admin/delete/group">
                    <button class='ghost-round full-width' type="submit" name="button"
                            value="${idChildGroup}"><spring:message code="app.childGroup.remove.group"/></button>
                </form>
                <form name="test" method="get" action="/admin">
                    <button class='ghost-round full-width' type="submit"><spring:message code="app.to.main"/></button>
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
