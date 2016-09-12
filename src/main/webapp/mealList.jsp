<%--
  Created by IntelliJ IDEA.
  User: Vitalii
  Date: 9/11/2016
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script   src="http://code.jquery.com/jquery-3.1.0.js"
              type="text/javascript"
              integrity="sha256-slogkvB1K3VOkzAI8QITxV3VzpOnkeNVsKvtkYLMjfk="
              crossorigin="anonymous"></script>
    <script>
        $(document).ready(function () {
                var array = ${jsonArray};
                for (var i = 0; i<array.length; i++){
                    var meal = array[i];
                    $("#table_body").append(
                            "<tr>"
                                +"<td>" + meal['dateTime'] + "</td>"
                                +"<td>" + meal['description'] + "</td>"
                                +"<td>" + meal['calories'] + "</td>"
                            +"</tr>"
                    );
                }
        });
        $(function()	{
            $('td').click(function(e)	{
                var t = e.target || e.srcElement;
                var elm_name = t.tagName.toLowerCase();
                if(elm_name == 'input')	{return false;}
                var val = $(this).html();
                var code = '<input formmethod="post" formaction="mealList.jsp" type="text" name="fields" id="edit" value="'+val+'" />';
                $(this).empty().append(code);
                $('#edit').focus();
                $('#edit').blur(function()	{
                    var val = $(this).val();
                    $(this).parent().empty().html(val);
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
        </tbody>
    </table>
</body>
</html>
