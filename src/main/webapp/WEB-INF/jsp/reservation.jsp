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
            <a href="/index" class="brand-logo">Tour De Team</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down" style="margin-right: 20px">
                <li><a href="/listoftours"><spring:message code="tours" /></a></li>
                <li><a href="/addtour"><spring:message code="addtour" /></a></li>
                <li><a href="/reservation"><spring:message code="reservations" /></a></li>
                <li><a href="/clients"><spring:message code="clients" /></a></li>
                <li style="margin-right: 20px"><a href="/hotels"><spring:message code="addhotel" /></a></li>
                <li><a href="/logout"><spring:message code="logout" /></a></li>
                <li><a href="/reservation?lang=en"><img src="${imgen}" width="48" height="32"></a>
                    <a href="/reservation?lang=ru"><img src="${imgru}" width="48" height="32"></a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <h2><spring:message code="reservations" /></h2>
        <form method="POST" action="#">
            <div class="row">
                <table>
                    <c:if test="${listReservation.size()>0}">
                        <tr>
                            <th><spring:message code="discountid" /></th>
                            <th><spring:message code="clientid" /></th>
                            <th><spring:message code="tourid" /></th>
                            <th><spring:message code="nop" /></th>
                            <th><spring:message code="status" /></th>
                            <th><spring:message code="totalprice" /></th>
                            <th></th>
                        </tr>
                    </c:if>
                    <c:if test="${listReservation.size() == 0}"><p>There are no active reservations</p></c:if>
                    <c:forEach var="reservation" items="${listReservation}">
                    <tr>
                        <td>${reservation.id}</td>
                        <td>${reservation.discount}</td>
                        <td>${reservation.clientId}</td>
                        <td> ${reservation.tourOfferId}</td>
                        <td> ${reservation.numberOfPeople}</td>
                        <td> ${reservation.status}</td>
                        <td> ${reservation.totalPrice}</td>
                        <td><a href="deleteReservation/${reservation.id}" class="secondary-content"> <i
                                class="small material-icons">remove</i></a></td>
                        </c:forEach>
                    </tr>

                </table>
                <br>
                <div class="row">
                    <div class="col s6 offset-s5">
                        <ul class="pagination">
                            <c:if test="${generalAmount>4}">
                                <c:forEach var="i" begin="1" end="${amount}">
                                    <li class="waves-effect"><a href="/reservation/${i}">${i}</a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
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