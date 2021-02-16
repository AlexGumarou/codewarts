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
                <form method="post" action="/admin/edit/editGroupSave"><br><br>
                    <h2 class='welcome'>Выберите новое название группы:</h2>
                    <input class='input-line full-width' type="text" size="40" name="nameGroup" value="${childGroup.name}" required>
                    <input type="hidden" name="idGroup" value="${childGroup.id}"><br><br>
                    <input class='ghost-round full-width' type="submit" name="button" value="изменить">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
