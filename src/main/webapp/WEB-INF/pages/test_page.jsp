<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="/resources/js/timer.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/styles/style.css"/>
    <title>Test page</title>
</head>
<%@ include file="menu.jsp" %>
<body onload="startTimer()">
<div id="test">
    <h3>
        Subject: <c:out value="${test.subject}"/>
    </h3>
    <h3>
        Number of questions:
        <c:out value="${test.questionNumber}"/>
    </h3>
    <h3>
        Difficulty:
        <c:out value="${test.difficulty}"/>
    </h3>
    <div id="clock">
        <p>
            <label for="timer">Time for test</label>
            <span id="timer" style="color: #4af; font-size: 150%; font-weight: bold;">
                <c:out value="${test.stringTimer}"/></span>
        </p>
    </div>
    <br>
    <form:form action="result" commandName="result" method="post">
        <form:input type="hidden" path="user.id" value="${user.id}"/>
        <form:input type="hidden" path="test_id" value="${test.id}"/>
        <form:input type="hidden" path="testSubject" value="${test.subject}"/>
        <form:input type="hidden" path="difficulty" value="${test.difficulty}"/>
        <c:forEach items="${test.questionAnswersMap}" var="entry" varStatus="nameVar">
            <div class="input-test">
               <h4> <c:out value="${nameVar.count}."/><c:out value="${entry.key}"/></h4>
            </div>
            <c:forEach items="${entry.value.existingAnswers}" var="answer" varStatus="valueVar">
                <div class="form-check">
                    <div class="input-test">
                        <label class="form-check-label">
                            <input type="radio" name="${nameVar.count}" value="${valueVar.count}">
                            <c:out value="${answer}"/></label>
                    </div>
                </div>
            </c:forEach>
        </c:forEach>
        <button id="finish" type="submit" class="btn btn-primary  btn-block">Finish</button>
    </form:form>
</div>
</body>
</html>
