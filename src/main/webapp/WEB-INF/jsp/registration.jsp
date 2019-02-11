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
                <div class="row">
                    <div class="input-field col s12">
                        <input id="login" type="text" class="validate" name="username">
                        <label for="login">Login</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate" name="password">
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" class="validate" name="email">
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input id="first_name" type="text" class="validate" name="firstname">
                        <label for="first_name">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="last_name" type="text" class="validate" name="secondname">
                        <label for="last_name">Last Name</label>
                    </div>
                </div>
                <div class ="rowregistration">
                    <button type="submit" class="waves-effect waves-light btn">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${chregistration}"></script>
</body>
</html>
