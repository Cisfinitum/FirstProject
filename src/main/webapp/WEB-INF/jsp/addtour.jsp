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

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<header>
    <nav>
        <div class="nav-wrapper tab ">
            <a href="index" class="brand-logo"   style="font-size: xx-large">Tour De Team</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down" style="margin-right: 20px">
                <li><a href="/listoftours"   style="font-size: x-large"><spring:message code="tours" /></a></li>
                <li><a href="/addtour"   style="font-size: x-large"><spring:message code="addtour" /></a></li>
                <li><a href="/reservation"   style="font-size: x-large"><spring:message code="reservations" /></a></li>
                <li><a href="/clients"   style="font-size: x-large"><spring:message code="clients" /></a></li>
                <li style="margin-right: 20px"><a href="/hotels"   style="font-size: x-large"><spring:message code="addhotel" /></a></li>
                <li><a href="/logout"   style="font-size: x-large"><spring:message code="logout" /></a></li>
                <li><a href="addtour?lang=en"><img src="${imgen}" width="48" height="32"></a>
                    <a href="addtour?lang=ru"><img src="${imgru}" width="48" height="32"></a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <h2><spring:message code="addtour" /></h2>
        <p><spring:message code="addtourinfo" /></p>
        <form method="POST" action="addtour">
            <div class="row">
                <c:if test="${not empty result}">
                    <span style="color: green; font-weight: bold">${result}</span>
                </c:if>
                <c:if test="${not empty error}">
                    <span style="color: red; font-weight: bold">${error}</span>
                </c:if>
                </div>
            <div class="row">
                <div class="col s4">
                    <div class="input-field">
                        <input type="text" id="autocomplete-input" class="autocomplete" name="tourType"
                               value="${tourType}">
                        <label for="autocomplete-input"   style="font-size: x-large"><spring:message code="tourtype" /></label>
                    </div>
                </div>
                <div class="col s4">
                    <div class="input-field">
                        <i class="material-icons prefix">event_available</i>
                        <input type="text" id="autocomplete-date" class="datepicker" name="startDate"
                               value="${startDate}">
                        <label for="autocomplete-date"   style="font-size: x-large"><spring:message code="dod" /></label>
                    </div>
                </div>
                <div class="col s4">
                    <div class="input-field">
                        <i class="material-icons prefix">event_available</i>
                        <input type="text" id="autocomplete-date2"   style="font-size: x-large" class="datepicker" name="endDate" value="${endDate}">
                        <label for="autocomplete-date2"   style="font-size: x-large"><spring:message code="ad" /></label>
                    </div>
                </div>
                <div class="col s4">
                    <div class="input-field">
                        <select class="pricePerPerson" name="hotel">
                            <c:forEach var="hotel" items="${hotelList}">
                                <option value="${hotel.name}"> ${hotel.name} ${hotel.numberOfStars}★
                                </option>
                            </c:forEach>
                        </select>
                        <label   style="font-size: x-large"><spring:message code="hotel" /></label>
                    </div>
                </div>
                <div class="col s4">
                    <div class="input-field">
                        <i class="material-icons prefix">local_offer</i>
                        <input type="text" id="autocomplete-input2" class="pricePerPerson" name="pricePerPerson"
                               value="${price}">
                        <label   style="font-size: x-large" for="autocomplete-input2"><spring:message code="tourprice" /></label>
                    </div>
                </div>
                <div class="col s4">
                    <div class="input-field">
                        <i class="material-icons prefix">loyalty</i>
                        <input type="text" id="autocomplete-input3" class="pricePerPerson" name="discount"
                               value="${discount}">
                        <label   style="font-size: x-large" for="autocomplete-input3"><spring:message code="tourdiscount" /></label>
                    </div>
                </div>
                <div class="input-field">
                    <div class="input-field col s12">
                        <input type="text" id="textarea1" class="materialize-textarea" name="tourDescription"
                               value="${description}">
                        <label   style="font-size: x-large" for="textarea1"><spring:message code="tourdescription" /></label>
                    </div>
                </div>
                <div class="col s2 offset-s10">
                    <div class="input-field">
                        <button class="btn waves-effect waves-light" type="submit" name="action"> <spring:message code="add" />
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