<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div id="users-list">
    <h3>All users</h3>
        <table id="sortable" class="table table-condensed table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Is blocked</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${userList}">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.login}" /></td>
                    <td><c:out value="${item.firstName}" /></td>
                    <td><c:out value="${item.lastName}" /></td>
                    <td ><c:out value="${item.block}" /></td>
                    <c:if test="${item.role.title == 'USER'}">
                        <c:choose>
                            <c:when test="${item.block}">
                                <td width="30px"><a href="/block?id=${item.id}" class="btn btn-success">Unblock</a></td>
                            </c:when>
                            <c:when test="${not item.block}">
                                <td width="30px"><a href="/block?id=${item.id}" class="btn btn-danger">Block</a></td>
                            </c:when>
                        </c:choose>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
