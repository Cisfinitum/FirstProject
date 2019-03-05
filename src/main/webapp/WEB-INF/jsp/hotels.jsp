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
            <a href="index" class="brand-logo" >Tour De Team</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down" style="margin-right: 20px">
                <li><a href="/listoftours" ><spring:message code="tours" /></a></li>
                <li><a href="/addtour" ><spring:message code="addtour" /></a></li>
                <li><a href="/reservation" ><spring:message code="reservations" /></a></li>
                <li><a href="/clients"><spring:message code="clients" /></a></li>
                <li style="margin-right: 20px"><a href="/hotels"><spring:message code="addhotel" /></a></li>
                <li><a href="/logout"><spring:message code="logout" /></a></li>
                <li><a href="hotels?lang=en"><img src="${imgen}" width="48" height="32"></a>
                    <a href="hotels?lang=ru"><img src="${imgru}" width="48" height="32"></a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <h2><spring:message code="addhotel" /></h2>
        <p><spring:message code="addtourinfo" /></p>
        <p style="color:green;">${message}</p>
        <p style="color:red;">${errormessage}</p>
        <form method="POST" action="/hotels" name="hotel">
            <div class="row">
                <div class="col s3">
                    <div class="input-field">
                        <input type="text" id="name" name="name" class="autocomplete" value="${name}">
                        <label for="name"><spring:message code="namesimple" /></label>
                    </div>
                </div>
                <div class="col s3">
                    <div class="input-field">
                        <input type="text" id="country" name="country" class="autocomplete" value="${country}">
                        <label for="country"><spring:message code="hotelcountry" /></label>
                    </div>
                </div>
                <div class="col s3">
                    <div class="input-field">
                        <input type="text" id="city" name="city" class="autocomplete" value="${city}">
                        <label for="city"><spring:message code="hotelcity" /></label>
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
                        <label for="stars"><spring:message code="numberofstars" /></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col s2 offset-s10">
                    <div class="input-field">
                        <button class="btn waves-effect waves-light" type="submit" name="action"> <spring:message code="add" />
                            <i class="material-icons right">add</i>
                        </button>
                    </div>
                    <br>
                        <div class="col s6">
                            <div class="input-field">
                                <a class="waves-effect waves-button-input btn modal-trigger" href="#modal1"> <spring:message code="showhotels" /> </a>
                            </div>
                        </div>
                    </div>
                </div>
        </form>
    </div>

    <div id="modal1" class="modal">
        <div class="modal-content">
            <h4>Hotels</h4>
            <input type="text" id="myInput" onkeyup="myFunction()" placeholder= "Start typing the hotel name" >
            <table id="myTable">
                <c:if test="${hotels.size()>0}">
                    <tr>
                        <th><spring:message code="namesimple"/></th>
                        <th><spring:message code="country" /></th>
                        <th><spring:message code="hotelcity" /></th>
                        <th><spring:message code="numberofstars" /></th>
                        <th></th>
                    </tr>
                </c:if>
                <c:if test="${hotels.size() == 0}"><p> No hotel exist</p></c:if>
                <c:forEach var="hotel" items="${hotels}">
                <tr>
                    <td>${hotel.name}</td>
                    <td>${hotel.country}</td>
                    <td>${hotel.city}</td>
                    <td> ${hotel.numberOfStars} ★</td>
                    <td><c:if test="${!map.get(hotel.id)}">
                        <a href="deleteHotel/${hotel.id}" class="secondary-content"> <i
                                class="small material-icons">remove</i></a></c:if></td>
                    </c:forEach>
                </tr>

            </table>
        </div>
        <div class="modal-footer">
            <a href="#!" class="modal-close waves-effect waves-green btn-flat">Close</a>
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
            © 2019 Copyright Text
        </div>
    </div>
</footer>
<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${tours}"></script>
</body>
</html>