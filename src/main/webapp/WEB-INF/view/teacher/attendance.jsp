<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>${msg}</h2>
    <form name="test" method="get" action="/teacher">
        <button type="submit" name="button">back</button>
    </form>
</div>
</body>
</html>
