<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tests</title>
</head>
<body>
<%@ include file="menu.jsp"%>
<h3 style="margin-left: 100px">Hello ${user.login}</h3>
<br>
<div id="test-list">
    <c:choose>
        <c:when test="${not empty testList}">
            <h3>All tests</h3>
            <table id="sortable" class="table table-condensed table-hover">
                <thead>
                <tr>
                    <c:if test="${user.role.title == 'ADMIN'}">
                        <th>Id</th>
                    </c:if>
                    <th>Subject</th>
                    <th>Difficulty</th>
                    <th>Number of questions</th>
                    <th>Timer (min)</th>
                    <c:if test="${user.role.title == 'ADMIN'}">
                        <th>Questions list</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="test" items="${testList}">
                    <tr>
                        <c:if test="${user.role.title == 'ADMIN'}">
                            <td><c:out value="${test.id}"/></td>
                        </c:if>
                        <td><c:out value="${test.subject}"/></td>
                        <td><c:out value="${test.difficulty}"/></td>
                        <td><c:out value="${test.questionNumber}"/></td>
                        <td><c:out value="${test.timer}"/></td>
                        <c:if test="${user.role.title == 'ADMIN'}">
                            <td><c:forEach items="${test.questionAnswersMap}" var="entry" varStatus="status">
                                <div id="questions">
                                    <c:out value="${status.count}.${entry.key}"/>
                                    <div id="questions">
                                        <c:forEach items="${entry.value.existingAnswers}" var="answer">
                                            <p style="text-align: left"><c:out value="${answer}"/></p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach></td>
                            <td width="30px"><a href="/delete?id=${test.id}" class="btn btn-danger">Delete</a></td>
                            <td width="30px"><a href="/update?id=${test.id}" class="btn btn-info">Update</a></td>
                        </c:if>
                        <c:if test="${user.role.title == 'USER'}">
                            <td width="30px"><a href="start?id=${test.id}" class="btn btn-primary">Start</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <c:if test="${user.role.title == 'USER'}">
                <h4>Admin hasn't added any tests yet</h4>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
<c:if test="${user.role.title == 'ADMIN'}">
    <div id="add_test">
        <h4>Create new Test</h4>
        <br>
        <form action="/add_test" method="get">
            <div class="form-group">
                <label for="subject">Enter subject</label>
                <select class="form-control" name="subject" title="Choose one of the following..."
                        id="subject">
                    <option value="Algebra">Algebra</option>
                    <option value="Art">Art</option>
                    <option value="Biology">Biology</option>
                    <option value="Computer science">Computer science</option>
                    <option value="English">English</option>
                    <option value="Geography">Geography</option>
                    <option value="Geometry">Geometry</option>
                    <option value="Health">Health</option>
                    <option value="History">History</option>
                    <option value="Literature">Literature</option>
                    <option value="Math">Math</option>
                    <option value="Physics">Physics</option>
                </select>
            </div>
            <div class="form-group">
                <label for="difficulty">Enter difficult</label><select
                    class="form-control" name="difficulty"
                    title="Choose one of the following..." id="difficulty">
                <option value="Easy">Easy</option>
                <option value="Middle">Middle</option>
                <option value="Hard">Hard</option>
            </select>
            </div>
            <button type="submit" class="btn btn-primary">Next</button>
        </form>
    </div>
</c:if>
</body>
</html>
