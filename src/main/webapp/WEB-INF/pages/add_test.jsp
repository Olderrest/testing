<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/styles/style.css"/>
    <title>Add test</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<div id="add-test-body">
    <h4>Selected subject: ${newTest.subject}</h4>
    <h4>Selected difficulty: ${newTest.difficulty}</h4>
    <br>
    <form:form action="create_test" commandName="newTest" method="post">
        <form:input path="subject" value="${newTest.subject}" type="hidden"/>
        <form:input path="difficulty" value="${newTest.difficulty}" type="hidden"/>
        <form:input path="questionNumber" value="${newTest.questionNumber}" type="hidden"/>
        <br>
        <div class="form-group">
            <form:label path="timer">Enter timer for test (min)</form:label>
            <form:input path="timer" type="number" name="timer" class="form-control" id="timer" required="required"/>
            <form:errors path="timer" cssStyle="color: red" cssClass="error"/>
        </div>
        <c:forEach var="i" begin="1" end="${newTest.questionNumber}">
            <div class="form-group">
               <form:label path="questions"> <c:out value="${i}. "/>Enter the question</form:label>
                <form:input path="questions" value="${newTest.questions[i]}" type="text" class="form-control" required="required"/>
            </div>
            <div class="form-group">
                <form:label path="answers">Enter answers</form:label>
                <c:forEach begin="1" end="4" var="index">
                    <form:input path="answers" value="${newTest.answers[index]}" type="text" class="form-control" required="required"/>
                    <form:checkbox path="rightAnswers" value="${index}"/><br>
                    <form:errors path="rightAnswers" cssClass="error"/>
                </c:forEach>
            </div>
        </c:forEach>
        <button id="search-input" type="submit" class="btn btn-success btn-block">Add</button>
    </form:form>
</div>
</body>
</html>
