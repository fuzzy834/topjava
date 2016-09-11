<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <style>
        .normal {background-color: green;}
        .exceeded {background-color: red;}
        .table_head{background-color: lightgrey;}
    </style>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<a href="meals?action=create">Add Meal</a>
<table border="1" cellpadding="8" cellspacing="0">
    <thead class="table_head">
        <tr>
            <th>Date</th>
            <th>Time</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td> <%= TimeUtil.dateToString(meal.getDateTime().toLocalDate()) %> </td>
                <td> <%= TimeUtil.timeToString(meal.getDateTime().toLocalTime()) %> </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
