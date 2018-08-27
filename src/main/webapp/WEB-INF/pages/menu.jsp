<%@ include file="lib.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/all_tests">Tests</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <c:if test="${user.role.title == 'ADMIN'}">
                    <li><a href="/all_users">All users</a></li>
                </c:if>
                <li><a href="/my_page">My page</a></li>
                <li><a href="/logout">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>
