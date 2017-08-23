<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bars generator</title>
    <link rel="stylesheet" href="css/reset.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/jquery-3.2.1.js"></script>
</head>
<body>

<div class="jumbotron">
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
<br>
    <form name="generate" method="post" action="/generate">
        <p><button id="start" class="btn-light">START</button></p>
        <p><button id="reroll" class="btn-warning">RE-ROLL</button></p>
    </form>
</div>
<div id="actions">
    <br>
    <a class="btn btn-outline-primary" href="/results">To results page</a>
</div>
</body>
</html>