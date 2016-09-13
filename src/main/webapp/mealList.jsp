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
          var exceed = "";

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
                      var elem_id = meal['elem_id'];
                      $("#table_body").append(
                              "<tr id='"+elem_id+"' class='generated'>"
                              +"<td ondblclick='eventHandler(this)' class='date_time'>" + meal['dateTime'] + "</td>"
                              +"<td ondblclick='eventHandler(this)' class='description'>" + meal['description'] + "</td>"
                              +"<td ondblclick='eventHandler(this)' class='calories'>" + meal['calories'] + "</td>"
                              +"</tr>"
                      );
                      if (meal["exceeded"] == "true") {
                          $("#"+elem_id).css("background-color", "red");
                      } else{
                          $("#"+elem_id).css("background-color", "green");
                      }
                  }
              });
          }

          function eventHandler (clickTarget) {
                  var elem_class = clickTarget.getAttribute("class");
                  if ($.contains(document.getElementById("table_body"), document.getElementById("edit"))) {
                      return false;
                  }
                  var tr_id = $(clickTarget).parent().attr("id");
                  var val = $(clickTarget).text();
                  var code = "";
                  if (elem_class == "date_time") {
                      code = '<input type="datetime-local" id="edit" value="' + val + '"/>';
                  } else if (elem_class == "description") {
                      code = '<input type="text" id="edit" value="' + val + '"/>';
                  } else {
                      code = '<input type="number" id="edit" value="' + val + '"/>';
                  }

                  $(clickTarget).empty().append(code);
                  $('#edit').focus();
                  $('#edit').blur(function () {
                      val = $("#edit").val();
                      $("#edit").parent().empty().html(val);
                      $.post('/topjava/meals/edit', {value: val, element_class: elem_class, element_id: tr_id}).done(function () {
                          $(".generated").remove();
                          updateList();
                      });
                  });
              }
    </script>

    <title>Meal List</title>
</head>
<body>
    <table border="1">
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
