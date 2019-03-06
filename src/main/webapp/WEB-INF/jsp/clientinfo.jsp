<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>My profile</title>
    <spring:url value="/resources/css/materialize.css" var="css"/>
    <spring:url value="/resources/js/materialize.js" var="js"/>
    <spring:url value="/resources/css/main.css" var="main"/>
    <spring:url value="/resources/js/profile.js" var="jsprofile"/>
    <spring:url value="/resources/img/1.jpg" var="img1"/>
    <spring:url value="/resources/img/2.jpg" var="img2"/>
    <spring:url value="/resources/img/3.jpg" var="img3"/>
    <spring:url value="/resources/img/4.jpg" var="img4"/>
    <spring:url value="/resources/img/united-kingdom-flag.png" var="imgen"/>
    <spring:url value="/resources/img/russia-flag.png" var="imgru"/>
    <spring:url value="/resources/img/group.png" var="gr"/>
    <spring:url value="/resources/img/user.jpg" var="imuser"/>
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
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container" style="width: 85%">
        <h2><spring:message code="clientprofile" /></h2>
        <div class="profile">
            <div class="avatar card-profile-image">
                <div class="card-content">
                    <div class="row">
                        <div class="col s2 card-profile-image">
                            <img src="${imuser}" alt="profile image" class="circle">
                        </div>
                        <div class="col s3">
                            <p class="medium-small grey-text"><spring:message code="emailname" />: ${person.email}</p>
                            <p class="medium-small grey-text"><spring:message code="firstname" />: ${person.firstName}</p>
                            <p class="medium-small grey-text"><spring:message code="lastname" />: ${person.lastName}</p>
                            <p class="medium-small grey-text"><spring:message code="phoneNumber" />: ${person.phoneNumber}</p>
                            <p class="medium-small grey-text"><spring:message code="clientrole" />: ${person.role}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    </div>

    <div class="container" style="width: 85%">
        <p class="medium-small green-text">${paymentMessage}</p>
        <h4><spring:message code="reservations" /></h4>
        <table>
            <c:if test="${reservations.size()>0}">
                <tr>
                    <th><spring:message code="reservationnumber" /></th>
                    <th><spring:message code="abouttour" /></th>
                    <th><spring:message code="tourdiscount" /></th>
                    <th><spring:message code="nop" /></th>
                    <th><spring:message code="status" /></th>
                    <th><spring:message code="totalprice" /></th>
                    <th></th>
                </tr>
            </c:if>
            <c:if test="${reservations.size() == 0}"><p><spring:message code="noactivereservations" /></p></c:if>
            <c:forEach var="reservation" items="${reservations}">
            <tr>
                <td>${reservation.id}</td>
                <td>${description.get(reservation.tourOfferId)} </td>
                <td> ${reservation.discount}%</td>
                <td> ${reservation.numberOfPeople}</td>
                <td> ${reservation.status}</td>
                <td> ${reservation.totalPrice}</td>
                <td>
                    <form method="post" action="/clients/pay/${person.id}" id="pay">
                        <c:if test="${reservation.status == 'UNPAID'}">
                            <a class="waves-effect waves-light btn modal-trigger" href="#modal1">APPROVE</a>
                            <div id="modal1" class="modal">
                                <div class="modal-content">
                                    <h4>You are going to approve reservation with price ${reservation.totalPrice} $</h4>
                                    <p style="font-size: large">Are you sure?</p>
                                    <input name="reservationId" type="hidden" value="${reservation.id}">
                                    <div class="modal-footer">
                                        <button type="submit" class="waves-effect waves-light btn">APPROVE
                                        </button>
                                        <a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancel</a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </form>
            </tr>
            </c:forEach>
        </table>
    </div>
</main>
<div class="footer-copyright">
    <div class="container">
        © 2019 Tour de Team
    </div>
</div>
</footer>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${jsprofile}"></script>
</body>
</html>
