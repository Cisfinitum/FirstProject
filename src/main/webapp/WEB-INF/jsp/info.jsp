<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Tour de Team</title>
    <spring:url value="/resources/css/materialize.css" var="css"/>
    <spring:url value="/resources/js/materialize.js" var="js"/>
    <spring:url value="/resources/css/main.css" var="main"/>
    <spring:url value="/resources/js/tours.js" var="tours"/>
    <spring:url value="/resources/img/1.jpg" var="img1"/>
    <spring:url value="/resources/img/2.jpg" var="img2"/>
    <spring:url value="/resources/img/3.jpg" var="img3"/>
    <spring:url value="/resources/img/4.jpg" var="img4"/>
    <spring:url value="/resources/img/united-kingdom-flag.png" var="imgen"/>
    <spring:url value="/resources/img/russia-flag.png" var="imgru"/>
    <spring:url value="/resources/img/group.png" var="gr"/>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="${css}" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="${main}" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<header>
    <div class="slider">
        <ul class="slides">
            <li>
                <img src="${img1}"> <!-- random image -->
                <div class="caption center-align">
                    <h3><spring:message code="tagline1"/></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline11"/></h5>
                </div>
            </li>
            <li>
                <img src="${img2}"> <!-- random image -->
                <div class="caption left-align">
                    <h3><spring:message code="tagline2"/></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline22"/></h5>
                </div>
            </li>
            <li>
                <img src="${img3}"> <!-- random image -->
                <div class="caption right-align">
                    <h3><spring:message code="tagline1"/></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline11"/></h5>
                </div>
            </li>
            <li>
                <img src="${img4}"> <!-- random image -->
                <div class="caption center-align">
                    <h3><spring:message code="tagline2"/></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline22"/></h5>
                </div>
            </li>
        </ul>
    </div>
</header>
<nav>
    <div class="nav-wrapper">
        <a href="index" class="brand-logo center">Tour de Team</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li class="active"><a href="/index"><spring:message code="homepage"/></a></li>
            <li><a href="/info"><spring:message code="information"/></a></li>
            <li><a href="/#"><spring:message code="feedback"/></a></li>
            <li><a href="/#"><spring:message code="contacts"/></a></li>
        </ul>
        <ul class="right hide-on-med-and-down">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <li><a href="/login"><spring:message code="signin"/></a></li>
                <li><a href="registration"><spring:message code="signup"/></a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="/listoftours"><spring:message code="adminpage"/></a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <li><a href="/clientProfile"><spring:message code="profile"/></a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                <li><a href="/logout"><spring:message code="logout"/></a></li>
            </sec:authorize>
            <li><a href="/homepage?lang=en"><img src="${imgen}" width="48" height="32"></a>
                <a href="/homepage?lang=ru"><img src="${imgru}" width="48" height="32"></a></li>
        </ul>
    </div>
</nav>
<main>
    <h2 style="text-align: center"><spring:message code="techologies"/> </h2>
    <br>
    <div class="container">
        <div class="row">
            <div class="col s4">
                <ul class="collection">
                    <li class="collection-item avatar">
                        <img src="/resources/img/jsp.png" alt="" class="circle">
                        <span class="title"> JSP </span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/mockito.jpg" alt="" class="circle">
                        <span class="title"> Mockito </span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/junit.jpg" alt="" class="circle">
                        <span class="title"> JUnit</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/h2.jpg" alt="" class="circle">
                        <span class="title"> H2</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/gradle.png" alt="" class="circle">
                        <span class="title"> Gradle</span>
                    </li>

                </ul>
            </div>
            <div class="col s4">
                <ul class="collection">
                    <li class="collection-item avatar">
                        <img src="/resources/img/sping.jpg" alt="" class="circle">
                        <span class="title"> Spring Security</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/sping.jpg" alt="" class="circle">
                        <span class="title"> Spring JDBC</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/sping.jpg" alt="" class="circle">
                        <span class="title"> Spring MVC</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/sping.jpg" alt="" class="circle">
                        <span class="title"> Spring i18n</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/log4j.jpg" alt="" class="circle">
                        <span class="title"> log4j</span>
                    </li>
                </ul>
            </div>
            <div class="col s4">
                <ul class="collection">
                    <li class="collection-item avatar">
                        <img src="/resources/img/tomcat.jpg" class="circle">
                        <span class="title"> Tomcat</span>
                    </li>

                    <li class="collection-item avatar">
                        <img src="/resources/img/github.png" alt="" class="circle">
                        <span class="title"> GitHub</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/travis.png" alt="" class="circle">
                        <span class="title">Travis</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/git.png" alt="" class="circle">
                        <span class="title"> Git</span>
                    </li>
                    <li class="collection-item avatar">
                        <img src="/resources/img/trello.png" alt="" class="circle">
                        <span class="title"> Trello</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</main>
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Tour de Team</h5>
                <p class="grey-text text-lighten-4"><spring:message code="footerinf"/></p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text"><spring:message code="footerlinks"/></h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="/index"><spring:message code="homepage"/></a></li>
                    <li><a class="grey-text text-lighten-3" href="/info"><spring:message code="information"/></a></li>
                    <li><a class="grey-text text-lighten-3" href="/#"><spring:message code="feedback"/></a></li>
                    <li><a class="grey-text text-lighten-3" href="/#"><spring:message code="contacts"/></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2018 Copyright Text
        </div>
    </div>
</footer>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${tours}"></script>
</body>
</html>