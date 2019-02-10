<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a id="en" href="/index?lang=en">English</a> | <a id="ru" href="/index?lang=ru">Russian</a>
<div>
<spring:message code="welcome" />, ${name} !</div>
<br>
<a href="testuser">Testing page for user</a>
<a href="testadmin">Testing page for admin</a>
</body>
</html>
