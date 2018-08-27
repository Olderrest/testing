<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <title>Update test</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<div id="update-test-body">
    <h4>Selected subject: ${test.subject}</h4>
    <h4>Selected difficulty: ${test.difficulty}</h4>
    <br>
    <form:form action="update_test" modelAttribute="test" method="post">
        <form:input path="id" value="${test.id}" type="hidden"/>
        <form:input path="subject" value="${test.subject}" type="hidden"/>
        <form:input path="difficulty" value="${test.difficulty}" type="hidden"/>
        <form:input path="questionNumber" value="${test.questionNumber}" type="hidden"/>
        <br>
        <div class="form-group">
            <div class="input-test">
                <form:label path="timer">Enter timer for test (min)</form:label>
                <form:input path="timer" type="number" value="${test.timer}" name="timer" class="form-control" id="timer" required="required"/>
                <form:errors path="timer" cssStyle="color: red" cssClass="error"/>
            </div>
        </div>
        <c:forEach var="entry" items="${test.questionAnswersMap}" varStatus="status">
            <div class="form-group">
                <div class="input-test">
                   <form:label path="questions"> <c:out value="${status.count}. "/>Enter the question</form:label>
                    <form:input path="questions" value="${entry.key}" type="text" class="form-control" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="answers">Enter answers</form:label>
                <c:forEach var="answer" items="${entry.value.existingAnswers}" varStatus="index">
                    <div class="input-test">
                        <form:input path="answers" value="${answer}" type="text" class="form-control" required="required"/>
                        <form:checkbox path="rightAnswers" checked="false" value="${index.count}"/><br>
                        <form:errors path="rightAnswers" cssClass="error"/>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <div class="input-test">
            <button id="search-input" type="submit" class="btn btn-info btn-block">Update</button>
        </div>
    </form:form>
</div>
</body>
</html>
