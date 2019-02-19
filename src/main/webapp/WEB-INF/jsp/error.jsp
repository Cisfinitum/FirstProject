<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <spring:url value="/resources/css/materialize.css" var="css" />
    <spring:url value="/resources/js/materialize.js" var="js" />
    <spring:url value="/resources/css/login.css" var="error" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${css}"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="${error}"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Error</title>
</head>
<body>
<div class="container">
    <div class="login-page">
        <div class="form">
            <h3>Error</h3>
        <h6>Please contact our support team</h6>
        </div>
    </div>
</div>
<script type="text/javascript" src="${js}"></script>
</body>
</html>
