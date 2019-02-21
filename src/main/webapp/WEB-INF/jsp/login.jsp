<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <spring:url value="/resources/css/materialize.css" var="css" />
    <spring:url value="/resources/js/materialize.js" var="js" />
    <spring:url value="/resources/css/login.css" var="login" />
    <spring:url value="/resources/js/chloginpass.js" var="chloginpass" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${css}"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="${login}"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login</title>
</head>
<body>
        <div class="login-page">            <div class="form">
                <c:if test="${not empty param.error}">
                    <span style="color: red; font-weight: bold">Logging error
                        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
                </c:if>
                <form name="passlogform" method="POST" onsubmit="return validate()" action="<c:url value="/j_spring_security_check" />">
                     <p style="color: forestgreen;">${registration_status}</p>
                    <div class="rowlogin">
                        <div class="input-field col s12">
                            <input id="login" type="text" name="j_username"  class="validate">
                            <label for="login"><spring:message code="loginname" /></label>
                        </div>
                    </div>
                    <div class ="rowlogin">
                        <div class="input-field col s12">
                            <input id="password" type="password" name="j_password" class="validate">
                            <label for="password"><spring:message code="passwordname" /></label>
                        </div>
                    </div>
                    <div class ="rowlogin">
                        <button type="submit" id="submit" class="waves-effect waves-light btn"><spring:message code="signin" /></button>
                    </div>
                </form>
                <div class ="rowlogin">
                    <form action="registration">
                        <button type="submit" class="waves-effect waves-light btn"><spring:message code="signup" /></button>
                    </form>
                </div>
            </div>
        </div>
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${chloginpass}"></script>
</body>
</html>
