<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Selenium Demo</title>
</head>
<body>

<h1>Login page</h1>
<hr/>

<form action="action" method="POST">
    Username: <input type="text" name="username" value=""/><br/>
    Password: <input type="password" name="password" value=""><br/>
    <c:if test="${not empty requestScope.LOGIN_MESSAGE}">
        <font color="red">${requestScope.LOGIN_MESSAGE}</font>
        <br/>
    </c:if>
    <input type="submit" name="action" value="Login">
</form>


</body>
</html>
