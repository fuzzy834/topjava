<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">

            <div class="pull-right visible-xs" id="body-header-lang-collapse">
                <jsp:include page="lang.jsp"/>
            </div>

            <div class="pull-right visible-xs" id="logout">
            <form:form action="logout" method="post">
                <sec:authorize access="isAuthenticated()">
                    <input class="btn btn-primary" type="submit"
                           value="<spring:message code="app.logout"/>"/>
                </sec:authorize>
            </form:form>
            </div>
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navigation" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a href="meals" class="navbar-brand visible-sm visible-md visible-lg"><spring:message code="app.title"/></a>
            <a href="meals" class="navbar-brand visible-xs glyphicon glyphicon-home"></a>
        </div>
        <div class="collapse navbar-collapse navbar-left" id="navigation">
            <ul class="nav navbar-nav">

                <li>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a role="button" href="users"><spring:message
                                    code="users.title"/></a>
                        </sec:authorize>
                    </sec:authorize>
                </li>

                <li>
                    <sec:authorize access="isAuthenticated()">
                        <a role="button" href="profile">${userTo.name}
                            <spring:message code="app.profile"/></a>
                    </sec:authorize>
                </li>

            </ul>
        </div>
        <ul class="nav navbar-nav navbar-right visible-sm visible-md visible-lg">
            <li>
            <form:form class="navbar-form" action="logout" method="post">
                <sec:authorize access="isAuthenticated()">
                    <input class="btn btn-primary" type="submit"
                           value="<spring:message code="app.logout"/>"/>
                </sec:authorize>
            </form:form>
           </li>

           <div class="pull-right visible-sm visible-md visible-lg" id="body-header-lang">
               <jsp:include page="lang.jsp"/>
           </div>
        </ul>
    </div>
</div>
