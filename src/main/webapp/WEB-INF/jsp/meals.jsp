<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.4.7/jquery.datetimepicker.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container" id="meals-container">
        <div class="shadow">
            <h3><spring:message code="meals.title"/></h3>
            <div class="container collapse" id="filter-section">
                <hr>
                <form:form method="post" class="form-horizontal" role="form" id="filter">
                    <div style="margin-right: 10px;" class="form-group col-xs-6 col-sm-6 col-md-3">
                        <label for="startDate"><spring:message code="meals.startDate"/>:</label>
                        <input class="form-control" name="startDate" id="startDate">
                    </div>

                    <div style="margin-right: 10px;" class="form-group col-xs-6 col-sm-6 col-md-3">
                        <label for="endDate"><spring:message code="meals.endDate"/>:</label>
                        <input class="form-control" name="endDate" id="endDate">
                    </div>

                    <div style="margin-right: 10px;" class="form-group col-xs-6 col-sm-6 col-md-3">
                        <label for="startTime"><spring:message code="meals.startTime"/>:</label>
                        <input class="form-control" name="startTime" id="startTime">
                    </div>

                    <div style="margin-right: 10px;" class="form-group col-xs-6 col-sm-6 col-md-3">
                        <label for="endTime"><spring:message code="meals.endTime"/>:</label>
                        <input class="form-control" name="endTime" id="endTime">
                    </div>

                    <div class="form-group col-xs-12">
                        <button class="btn btn-default pull-right" type="button"
                                onclick="updateTable()" id="filter-submit-button"><spring:message
                                code="meals.filter"/></button>

                        <button style="margin-right: 3px" class="btn btn-default pull-right" type="button"
                                onclick="clearFilter()" id="filter-clear-button"><spring:message
                                code="meals.clear"/></button>
                    </div>
                </form:form>
            </div>
            <a class="btn btn-sm btn-info" onclick="add('<spring:message code="meals.add"/>')"><spring:message
                    code="meals.add"/></a>
            <button style="margin-bottom: 1px" class="btn btn-sm" data-toggle="collapse"
                    data-target="#filter-section"><spring:message code="meals.filter"/></button>
            <table class="table table-hover table-condensed" id="datatable">
                <thead>
                <tr>
                    <th><spring:message code="meals.dateTime"/></th>
                    <th><spring:message code="meals.description"/></th>
                    <th><spring:message code="meals.calories"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3"><spring:message
                                code="meals.dateTime"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="dateTime"
                                   name="dateTime" placeholder="<spring:message code="meals.dateTime"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><spring:message
                                code="meals.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="<spring:message code="meals.description"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3"><spring:message
                                code="meals.calories"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories"
                                   placeholder="1000">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()"><spring:message
                                    code="common.save"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var edit_title = '<spring:message code="meals.edit"/>';
</script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="webjars/datetimepicker/2.4.7/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/mealDatatables.js"></script>
</html>