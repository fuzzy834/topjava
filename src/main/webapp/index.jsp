<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
    <style>
        .authorized{
            display: none;
        }
        .not_authorized{
            display: block;
        }
    </style>
</head>
<body>
<h2>Проект "<a href="https://github.com/JavaWebinar/topjava08" target="_blank">Java Enterprise (Topjava)"</a></h2>
<hr>
<form action="users" method="post">
    Select user:
    <select name="user">
        <option value="none"></option>
        <option value="user">User</option>
        <option value="admin">Admin</option>
    </select>
    <button type="submit">Log in</button>
</form></br>
<ul class="${(authorized==null||authorized==false) ? 'authorized' : 'not_authorized'}">
    <li><a href="users">User List</a></li>
    <li><a href="meals">Meal List</a></li>
</ul>
</body>
</html>
