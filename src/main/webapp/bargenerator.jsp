<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bars generator</title>
</head>
<body>

<table class="bars-table">

    <tr>
        <th>Your bars</th>
    </tr>

    <c:forEach items="${requestScope.showUserBars}" var="bar">
        <tr>
            <td>${bar.getString()}</td>
        </tr>
    </c:forEach>
</table>

<form name="generate" method="post" action="/generate">
    <p><input type="submit" name="start" value="START">
    <p><input type="submit" name="roll" value="RE-ROLL">
</form>
<br>
<a href="/results">To results page</a>

</body>
</html>