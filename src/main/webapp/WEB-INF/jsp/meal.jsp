<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>Meal</title>
</head>
<body>
<section>
    <h2><a href="index.jsp">Home</a></h2>
    <h3>${null == param.id ? 'Create meal' : 'Edit meal'}</h3>
    <hr>
    <%--<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>--%>
    <form:form modelAttribute="meal" method="post" action="${null == param.id ? 'create' : 'update'}">
        <input type="hidden" name="id" value="${id}">
        <dl>
            <dt><form:label path="dateTime">DateTime:</form:label></dt>
            <dd><form:input type="datetime-local" value="${dateTime}" path="dateTime"/></dd>
        </dl>
        <dl>
            <dt><form:label path="description">Description:</form:label></dt>
            <dd><form:input type="text" value="${description}" path="description"/></dd>
        </dl>
        <dl>
            <dt><form:label path="calories">Calories:</form:label></dt>
            <dd><form:input type="number" value="${calories}" path="calories"/></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form:form>
</section>
</body>
</html>
