<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/dataTables.bootstrap.min.css">
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>

    <div class="jumbotron">
        <div class="container">
            <div class="shadow">

                <h3><fmt:message key="meals.title"/></h3>

                <div class="view-box">
                    <form method="post" class="form-horizontal" role="form" id="filter">
                        <div class="form-group">

                            <label class="control-label" name="startDate"><fmt:message key="meals.startDate"/>:</label>
                            <div class="col-sm-2">
                                <input class="form-control" id="startDate" type="date" name="startDate"
                                       value="${param.startDate}"/>
                            </div>

                            <label class="control-label" name="endDate"><fmt:message key="meals.endDate"/>:</label>
                            <div class="col-sm-2">
                                <input class="form-control" id="endDate" type="date" name="endDate"
                                       value="${param.endDate}"/>
                            </div>

                        </div>

                        <div class="form-group">

                            <label class="control-label" name="startTime"><fmt:message key="meals.startTime"/>:</label>

                            <div class="col-sm-2">
                                <input class="form-control" id="startTime" type="time" name="startTime"
                                       value="${param.startTime}"/>
                            </div>

                            <label class="control-label" name="endTime"><fmt:message key="meals.endTime"/>:</label>

                            <div class="col-sm-2">
                                <input class="form-control" id="endTime" type="time" name="endTime"
                                       value="${param.endTime}"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <button type="button" onclick="filter()"><fmt:message key="meals.filter"/></button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="view-box">
                    <%--<a href="meals/create"><fmt:message key="meals.add"/></a>--%>
                    <a class="btn btn-sm btn-info" onclick="add()"><fmt:message key="meals.add"/></a>
                    <hr>
                    <table class="table table-striped display" id="datatable">
                        <thead>
                        <tr>
                            <th><fmt:message key="meals.dateTime"/></th>
                            <th><fmt:message key="meals.description"/></th>
                            <th><fmt:message key="meals.calories"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${meals}" var="meal">
                            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                                <td>
                                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                        ${fn:formatDateTime(meal.dateTime)}
                                </td>
                                <td>${meal.description}</td>
                                <td>${meal.calories}</td>
                                <td><a class="btn btn-xs btn-primary edit"><fmt:message
                                        key="common.update"/></a></td>
                                <td><a class="btn btn-xs btn-danger delete" onclick="deleteRow(${meal.id})"><fmt:message
                                        key="common.delete"/></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="users.edit"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="datetime-local" class="form-control" id="dateTime" name="dateTime" placeholder="Date">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="Description">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3">Calories</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories" placeholder="2000">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" class="btn btn-primary" onclick="save()"><fmt:message key="common.save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = '/topjava/ajax/profile/meals/';
    var datatableApi;

    // $(document).ready(function () {
//    $(function () {
//        datatableApi = $('#datatable').DataTable({
//            "bPaginate": false,
//            "bInfo": false,
//            "aoColumns": [
//                {
//                    "mData": "dateTime"
//                },
//                {
//                    "mData": "description"
//                },
//                {
//                    "mData": "calories"
//                },
//                {
//                    "sDefaultContent": "Edit",
//                    "bSortable": false
//                },
//                {
//                    "sDefaultContent": "Delete",
//                    "bSortable": false
//                }
//            ],
//            "aaSorting": [
//                [
//                    0,
//                    "desc"
//                ]
//            ]
//        });
//        makeEditable();
//    });


    function filter(){
        $.ajax({
            type: 'POST',
            url: ajaxUrl + 'filter',
            data: $('#filter').serialize(),
            success: updateTableWithData
        });
    }

    $(function () {
        datatableApi = $('#datatable').DataTable({
            paging: false,
            info: false,
            columns: [
                {data: "dateTime"},
                {data: "description"},
                {data: "calories"},
                {defaultContent: "<fmt:message key="common.update"/>", orderable: false},
                {defaultContent: "<fmt:message key="common.delete"/>", orderable: false}
            ],
            order: [[0, "desc"]]
        });
        makeEditable();
    });



</script>
</html>
