<%--
  Created by IntelliJ IDEA.
  User: Vitalii
  Date: 9/11/2016
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script   src="http://code.jquery.com/jquery-3.1.0.js"
              type="text/javascript"
              integrity="sha256-slogkvB1K3VOkzAI8QITxV3VzpOnkeNVsKvtkYLMjfk="
              crossorigin="anonymous"></script>
    <script>
          $(function()	{
            $('td').click(function(e)	{
                var t = e.target || e.srcElement;
                var element_name = t.tagName.toLowerCase();
                var elem_id = t.getAttribute("id");
                if(element_name == 'input')	{return false;}
                var code = "";
                alert(elem_id);
                if(elem_id == "date_time"){
                    code = '<input type="datetime-local" id="edit" value="'+val+'"/>';
                }else if(elem_id == "description"){
                    code = '<input type="text" id="edit" value="'+val+'"/>';
                }else {
                    code = '<input type="number" id="edit" value="'+val+'"/>';
                }
                var val = $(this).html();

                $(this).empty().append(code);
                $('#edit').focus();
                $('#edit').blur(function(){
                    var val = $(this).val();
                    $(this).parent().empty().html(val);
                    alert(val);
                    $.post('/topjava/meals',{data:val}).done(function (data) {
                            alert(data);

                    });
                });
            });
        });
    </script>
    <title>Meal List</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Date and time</th>
                <th>Description</th>
                <th>Calories</th>
            </tr>
        </thead>
        <tbody id="table_body">
            <c:forEach items="${mealWithExceedList}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
                <tr>
                    <td id="date_time">${meal.dateTime}</td>
                    <td id="description">${meal.description}</td>
                    <td id="calories">${meal.calories}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
