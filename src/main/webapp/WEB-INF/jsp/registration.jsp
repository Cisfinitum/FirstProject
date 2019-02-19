<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <spring:url value="/resources/css/materialize.css" var="css" />
    <spring:url value="/resources/js/materialize.js" var="js" />
    <spring:url value="/resources/css/registration.css" var="registration" />
    <spring:url value="/resources/js/chregistration.js" var="chregistration" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${css}"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="${registration}"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title></title>
</head>
<body>
<div class="registration-page">
    <div class="registration">
        <div class="row">
            <form name="user" class="col s12" onsubmit="return validate()" action="registration" method="POST" >
                <c:set var="val"><spring:message code="wrongpass"/></c:set>
                <input id="wrongpass" type="hidden" value="${val}"/>
                <span id="forpass" style="color: red; font-weight: bold"></span>
                <p style="color: red;">${message}</p>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" class="validate" name="email">
                        <label for="email"><spring:message code="emailname" /></label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate" name="password" onchange="checkPass()">
                        <label for="password"><spring:message code="passwordname" /></label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password2" type="password" class="validate" name="password2" onchange="checkPass()">
                        <label for="password2"><spring:message code="passwordname" /></label>
                    </div>
                </div>
                <div class ="rowregistration">
                    <button type="submit" class="waves-effect waves-light btn"><spring:message code="send" /></button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${chregistration}"></script>
</body>
</html>
