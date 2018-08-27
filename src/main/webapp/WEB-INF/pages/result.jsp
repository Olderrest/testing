<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/styles/style.css"/>
    <title>Result</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<div id="info">
    <h3>
        Test subject:
        <c:out value="${result.testSubject}" />
    </h3>
    <h3>
        Test difficulty:
        <c:out value="${result.difficulty}" />
    </h3>
    <h3>
        Your result:
        <c:out value="${result.result}" />
    </h3>
    <a href="/all_tests" class="btn btn-primary" >All tests</a>
</div>
</body>
</html>
