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
            <form name="user" class="col s12" onsubmit="return validatePageData()" action="registration" method="POST" >
                <c:set var="val"><spring:message code="wrongpass"/></c:set>
                <p id="error_message" style="color: red;">${message}</p>
                <input id="wrongpass" type="hidden" value="${val}"/>
                <span id="forpass" style="color: red; font-weight: bold"></span>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" name="email" onchange="return validateEmail()">
                        <label for="email"><spring:message code="emailname" /></label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input id="firstName" type="text" name="firstName" onchange="return validateFirstName()">
                        <label for="firstName"><spring:message code="firstname" /></label>
                    </div>
                    <div class="input-field col s6">
                        <input id="lastName" type="text" name="lastName" onchange="return validateLastName()">
                        <label for="lastName"><spring:message code="lastname" /></label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="phoneNumber" type="text" name="phoneNumber" onchange="return validatePhoneNumber()">
                        <label for="phoneNumber"><spring:message code="phoneNumber" /></label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" name="password" onchange="return validatePassword()">
                        <label for="password"><spring:message code="passwordname" /></label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password2" type="password" name="password2" onchange="return checkIfPasswordsAreEqual()">
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
