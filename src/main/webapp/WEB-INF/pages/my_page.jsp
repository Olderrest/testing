<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My page</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h1>
                <c:out value="${user.firstName}" />
                <c:out value="${user.lastName}" />
            </h1>
            <p>
                User login:
                <c:out value="${user.login}" />
            </p>
            <c:if test="${user.role.title == 'ADMIN'}">
                <c:choose>
                    <c:when test="${not empty blockedUsers}">
                        <div id="users-list">
                            <h3>Blocked users</h3>
                            <table id="sortable" class="table table-condensed table-hover">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Login</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="blockedUser" items="${blockedUsers}">
                                        <tr>
                                            <td><c:out value="${blockedUser.id}" /></td>
                                            <td><c:out value="${blockedUser.login}" /></td>
                                            <td><c:out value="${blockedUser.firstName}" /></td>
                                            <td><c:out value="${blockedUser.lastName}" /></td>
                                        </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="page_message">
                            <h2>List of blocked users is empty!</h2>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${user.role.title == 'USER'}">
                <c:choose>
                    <c:when test="${not empty resultList}">
                        <div id="results-list">
                            <h3>My Tests</h3>
                            <table id="sortable" class="table table-condensed table-hover">
                                <thead>
                                <tr>
                                    <th>№</th>
                                    <th>Name of test</th>
                                    <th>Difficulty</th>
                                    <th>Result</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="result" items="${resultList}" varStatus="count">
                                    <tr>
                                        <td><c:out value="${count.count}" /></td>
                                        <td><c:out value="${result.testSubject}" /></td>
                                        <td><c:out value="${result.difficulty}" /></td>
                                        <td><c:out value="${result.result}" /></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="page_message">
                            <h2>You didn't completed any test yet!</h2>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
