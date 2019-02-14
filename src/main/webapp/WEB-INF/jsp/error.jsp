<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <spring:url value="/resources/css/materialize.css" var="css" />
    <spring:url value="/resources/js/materialize.js" var="js" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${css}"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Error</title>
</head>
<body>
<div class="container">
    ERROR
</div>
<script type="text/javascript" src="${js}"></script>
</body>
</html>
