<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>

</head>
<body>
<div align="center">
    ${msg}
    <form action="/login" method="post">
        <input type="text" placeholder="login" name="login"><br>
        <input type="text" placeholder="pass" name="pass"><br>
        <input type="submit" value="button" name="button"><br>
    </form>
</div>
</body>
</html>