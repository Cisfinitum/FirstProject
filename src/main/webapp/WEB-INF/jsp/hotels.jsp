<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
   <title>Admin Page</title>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<header>
    <nav>
        <div class="nav-wrapper tab ">
            <a href="index" class="brand-logo">Tour De Team</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down" style="margin-right: 20px">
                <li><a href="/listoftours">Tours</a></li>
                <li><a href="/addtour">Add Tour</a></li>
                <li><a href="/reservation">Reservations</a></li>
                <li><a href="/clients">Clients</a></li>
                <li style="margin-right: 20px"><a href="/hotels">Add Hotel</a></li>
                <li>
                    <button class="btn waves-effect waves-light" type="submit" name="action">
                        <a class="forButton" href="logout">Log Out</a>
                    </button>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <h2>Add Hotel</h2>
        <p>Please note that all fields are required.</p>
        <p style="color:green;">${message}</p>
        <p style="color:red;">${errormessage}</p>
        <form method="POST" action="/hotels" name="hotel">
            <div class="row">
                <div class="col s3">
                    <div class="input-field">
                        <input type="text" id="name" name="name" class="autocomplete">
                        <label for="name">Name</label>
                    </div>
                </div>
                <div class="col s3">
                    <div class="input-field">
                        <input type="text" id="country" name="country" class="autocomplete">
                        <label for="country">Country</label>
                    </div>
                </div>
                <div class="col s3">
                    <div class="input-field">
                        <input type="text" id="city" name="city" class="autocomplete">
                        <label for="city">City</label>
                    </div>
                </div>
                <div class="col s3">
                    <div class="input-field" >
                        <select id="stars" name="stars">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                        <label for="stars">Number Of Stars</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col s2 offset-s10">
                    <div class="input-field">
                        <button class="btn waves-effect waves-light" type="submit" name="action"> Add
                            <i class="material-icons right">add</i>
                        </button>
                    </div>
                </div>
            </div>
        </form>
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
                    <li><a class="grey-text text-lighten-3" href="index"><spring:message code="homepage"/></a></li>
                    <li><a class="grey-text text-lighten-3" href="#"><spring:message code="information"/></a></li>
                    <li><a class="grey-text text-lighten-3" href="#"><spring:message code="feedback"/></a></li>
                    <li><a class="grey-text text-lighten-3" href="#"><spring:message code="contacts"/></a></li>
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