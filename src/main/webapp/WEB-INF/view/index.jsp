<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href = "css/style.css" type="text/css" rel = "stylesheet"/>
</head>
<body>
<form action="/login" method="post">
<div class='bold-line'></div>
    <div class='container'>
        <div class='window'>
            <div class='overlay'>
                <div class='content'>
                    <div class='welcome'>Welcome to <br>Codewarts</div>
                    ${msg}
                        <div class='input-fields'>
                            <input type='text' placeholder="Login" name="login" class='input-line full-width'></input>
                            <input type='text' placeholder='Password' name="pass" class='input-line full-width'></input>
                        </div>
                        <br><br>
                    <div><button class='ghost-round full-width'>Login</button></div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
