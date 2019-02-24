<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Tour de Team</title>
    <spring:url value="/resources/css/materialize.css" var="css" />
    <spring:url value="/resources/js/materialize.js" var="js" />
    <spring:url value="/resources/css/main.css" var="main" />
    <spring:url value="/resources/js/tours.js" var="tours" />
    <spring:url value="/resources/img/1.jpg" var="img1" />
    <spring:url value="/resources/img/2.jpg" var="img2" />
    <spring:url value="/resources/img/3.jpg" var="img3" />
    <spring:url value="/resources/img/4.jpg" var="img4" />
    <spring:url value="/resources/img/united-kingdom-flag.png" var="imgen" />
    <spring:url value="/resources/img/russia-flag.png" var="imgru" />
    <spring:url value="/resources/img/group.png" var="gr" />
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="${css}"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="${main}"  media="screen,projection"/>

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
                    <h3><spring:message code="tagline1" /></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline11" /></h5>
                </div>
            </li>
            <li>
                <img src="${img2}"> <!-- random image -->
                <div class="caption left-align">
                    <h3><spring:message code="tagline2" /></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline22" /></h5>
                </div>
            </li>
            <li>
                <img src="${img3}"> <!-- random image -->
                <div class="caption right-align">
                    <h3><spring:message code="tagline1" /></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline11" /></h5>
                </div>
            </li>
            <li>
                <img src="${img4}"> <!-- random image -->
                <div class="caption center-align">
                    <h3><spring:message code="tagline2" /></h3>
                    <h5 class="light grey-text text-lighten-3"><spring:message code="tagline22" /></h5>
                </div>
            </li>
        </ul>
    </div>
</header>



<nav>
    <div class="nav-wrapper">
        <a href="index" class="brand-logo center">Tour de Team</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li class="active"><a href="index"><spring:message code="homepage" /></a></li>
            <li><a href="#"><spring:message code="information" /></a></li>
            <li><a href="#"><spring:message code="feedback" /></a></li>
            <li><a href="#"><spring:message code="contacts" /></a></li>
        </ul>
        <ul  class="right hide-on-med-and-down">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <li><a href="login"><spring:message code="signin" /></a></li>
                <li><a href="registration"><spring:message code="signup" /></a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="listoftours/1"><spring:message code="adminpage" /></a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                <li><a href="logout"><spring:message code="logout" /></a></li>
            </sec:authorize>
            <li><a href="homepage?lang=en"><img src="${imgen}" width="48" height="32"></a>
            <a href="homepage?lang=ru"><img src="${imgru}" width="48" height="32"></a></li>
        </ul>
    </div>
</nav>




<main>
    <div class="container">
        <form method="POST" action="searchtours">
            <div class="row">
                <c:if test="${not empty error}">
                    <span style="color: red; font-weight: bold">${error}</span>
                </c:if>
            </div>
            <div class="row">
                <div class="col s3">
                    <div class="input-field">
                        <i class="material-icons prefix">edit_location</i>
                        <input type="text" id="autocomplete-input" class="autocomplete" name="country">
                        <label for="autocomplete-input"><spring:message code="country" /></label>
                    </div>
                </div>
                <div class="col s3">
                    <div class="input-field">
                        <i class="material-icons prefix">event_available</i>
                        <input type="text" id="autocomplete-dateStart" class="datepicker" name="startDate">
                        <label for="autocomplete-dateStart"><spring:message code="dod" /></label>
                    </div>
                </div>
                <div class="col s3">
                    <div class="input-field">
                        <i class="material-icons prefix">event_available</i>
                        <input type="text" id="autocomplete-dateEnd" class="datepicker" name="endDate">
                        <label for="autocomplete-dateEnd"><spring:message code="ad" /></label>
                    </div>
                </div>
                <div class="col s2">
                    <div class="input-field">
                        <select class="icons" name="numberOfPeople">
                            <option value="1" data-icon="${gr}" class="right" selected>1</option>
                            <option value="2" data-icon="${gr}" class="right">2</option>
                            <option value="3" data-icon="${gr}" class="right">3</option>
                            <option value="4" data-icon="${gr}" class="right">4</option>
                            <option value="5" data-icon="${gr}" class="right">5</option>
                            <option value="6" data-icon="${gr}" class="right">6</option>
                            <option value="7" data-icon="${gr}" class="right">7</option>
                            <option value="8" data-icon="${gr}" class="right">8</option>
                        </select>
                        <label><spring:message code="nop" /></label>
                    </div>
                </div>
                <div class="col s1">
                    <div class="input-field">
                        <button class="btn-large waves-effect waves-light" type="submit" name="action"><spring:message code="searchbutton" />
                            <i class="material-icons right">event_available</i>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <c:if test="${not empty list}">
    <div class="container">
        <table>
            <thead>
            <tr>
                <th>Tour Type</th>
                <th>Country</th>
                <th>City</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Price</th>
                <th>Hotel</th>
                <th>Description</th>
                <th>Discount</th>
                <th>Reserve</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="i" begin="0" end="${list.size()-1}">
                    <tr>
                        <td>${list.get(i).tourType}</td>
                        <td>FROM HOTEL ID</td>
                        <td>FROM HOTEL ID</td>
                        <td>${list.get(i).startDate}</td>
                        <td>${list.get(i).endDate}</td>
                        <td>${list.get(i).pricePerUnit}</td>
                        <td>FROM HOTEL ID</td>
                        <td>${list.get(i).description}</td>
                        <td>FROM DISCOUNT ID</td>
                        <td>
                            <form method="post" action="#">
                                <input name="idOfTour" type="hidden" value="${list.get(i).id}">
                                <button class="btn waves-effect waves-light" type="submit" name="action"> Reserve
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    </c:if>
<div class="container">
    <div class="row">
        <div class="col s4">
            <div class="center promo promo-example">
                <i class="large material-icons mm">security</i>
                <p class="promo-caption mm"><spring:message code="securitypromo" /></p>
                <p class="light center mm"><spring:message code="securityp" /></p>
            </div>
        </div>
        <div class="col s4">
            <div class="center promo promo-example">
                <i class="large material-icons mm">group</i>
                <p class="promo-caption mm"><spring:message code="supportpromo" /></p>
                <p class="light center mm"><spring:message code="supportp" /></p>
            </div>
        </div>
        <div class="col s4">
            <div class="center promo promo-example">
                <i class="large material-icons mm">settings</i>
                <p class="promo-caption mm"><spring:message code="conveniencepromo" /></p>
                <p class="light center mm"><spring:message code="conveniencep" /></p>
            </div>
        </div>
    </div>
</div>
</main>
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Tour de Team</h5>
                <p class="grey-text text-lighten-4"><spring:message code="footerinf" /></p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text"><spring:message code="footerlinks" /></h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="index"><spring:message code="homepage" /></a></li>
                    <li><a class="grey-text text-lighten-3" href="#"><spring:message code="information" /></a></li>
                    <li><a class="grey-text text-lighten-3" href="#"><spring:message code="feedback" /></a></li>
                    <li><a class="grey-text text-lighten-3" href="#"><spring:message code="contacts" /></a></li>
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