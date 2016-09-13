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

          $(document).ready(updateList());

          $(window).keydown(function(event){
              if(event.keyCode == 13) {
                  $('#edit').blur();
              }
          });

          function updateList () {
              $.post('/topjava/meals',{data : "receive_data"}, function (msg) {
                  var array = JSON.parse(msg);
                  for (var i = 0; i < msg.length; i++){
                      var meal = array[i];
                      $("#table_body").append(
                              "<tr class='generated'>"
                              +"<td onclick='eventHandler(this)' class='date_time'>" + meal['dateTime'] + "</td>"
                              +"<td onclick='eventHandler(this)' class='description'>" + meal['description'] + "</td>"
                              +"<td onclick='eventHandler(this)' class='calories'>" + meal['calories'] + "</td>"
                              +"</tr>"
                      );
                  }
              });
          }

          function eventHandler (clickTarget) {
                  var element_name = clickTarget.tagName.toLowerCase();
                  var elem_class = clickTarget.getAttribute("class");
                  if (element_name == 'input') {
                      return false;
                  }
                  var code = "";
                  if (elem_class == "date_time") {
                      code = '<input type="datetime-local" id="edit" value="' + val + '"/>';
                  } else if (elem_class == "description") {
                      code = '<input type="text" id="edit" value="' + val + '"/>';
                  } else {
                      code = '<input type="number" id="edit" value="' + val + '"/>';
                  }
                  var val = $(clickTarget).html();

                  $(clickTarget).empty().append(code);
                  $('#edit').focus();
                  $('#edit').blur(function () {
                      var val = $(clickTarget).val();
                      $(clickTarget).parent().empty().html(val);
                      $.post('/topjava/meals', {value: val, element_class: elem_class}).done(function (element_class) {
                          $(".generated").remove();
                          updateList();
                      });
                  });
              }

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
