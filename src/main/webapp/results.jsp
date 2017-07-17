<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chains</title>
</head>
<body>
<h1>Current chainbar</h1>
<b><c:forEach items="${requestScope.showCurrentBars}" var="bar">
    ${bar.getString()}
</c:forEach></b>

<br>
Possible variants for your custom chain:
<c:forEach items="${requestScope.occurences}" var="bar">
    ${bar.getString()}
</c:forEach>
<br>
Your custom chain:
<c:forEach items="${requestScope.showChain}" var="bar">
    ${bar.getString()}
</c:forEach>

<br>

<form name="inputform" method="post" action="/results">
    <p><input type="text" name="digit" size="20"> <input type="submit" name="makechoice" value="Select"></p>
    <input type="submit" name="upload" value="Upload chain">
</form>

<br>

<form name="chainmaker" method="post" action="/results">
    <p><input type="submit" name="chainmaker" value="Make the longest chain"></p>
</form>

<h2>History</h2>
<table class="guests-table">
    <tr>
        <th>Chains</th>
    </tr>
    <c:forEach items="${requestScope.showHistory}" var="chain">
        <tr>
            <td><b>${chain.id}.</b> ${chain.resultChain}</td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="/generate">Back to bars generation page</a>
<br>
<a href="/">Back to main page</a>
</body>
</html>
