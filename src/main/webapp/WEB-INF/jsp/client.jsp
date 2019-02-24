<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin Page</title>
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
    <spring:url value="/resources/img/user.png" var="imuser"/>
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
            <a href="index" class="brand-logo">Tour De Team</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down" style="margin-right: 20px">
                <li><a href="/listoftours">Tours</a></li>
                <li><a href="/addtour">Add Tour</a></li>
                <li><a href="/reservation/1" id="Reservation">
                    <span class="badge" style="background-color: #ab4160; color: #ffffff"
                          id="myBadge">${generalAmount}</span>
                    Reservations
                </a></li>
                <li><a href="#">Clients</a></li>
                <li>
                    <button class="btn waves-effect waves-light" type="submit" name="action">
                        <a class="forButton" href="/logout">Log Out</a>
                    </button>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <h2>Client profile</h2>
        <div class="profile">
            <div class="avatar card-profile-image">
                <div class="card-content">
                    <div class="row">
                        <div class="col s2 card-profile-image">
                            <img src="${imuser}" alt="profile image" class="circle">
                        </div>
                        <div class="col s3">
                            <p class="medium-small grey-text">Email: ${person.email}</p>
                            <p class="medium-small grey-text">Role: ${person.role}</p>
                        </div>
                        <div class="col s12 offset-s2">
                                <a class="btn waves-effect waves-light modal-trigger" data-target="modal" href="#modal"> Change Password
                                </a>
                        </div>
                        <!-- Modal Structure -->
                        <div id="modal" class="modal modal-fixed-footer">
                            <div class="modal-content registration-page">
                                    <div class="registration">
                                        <div class="row">
                                            <form name="user" id="change-pwd" class="col s12" autocomplete="off" onsubmit="return validatePageData()" action="" method="POST" >
                                                <h4>Change Password</h4>
                                                <c:set var="val"><spring:message code="wrongpass"/></c:set>
                                                <input id="wrongpass" type="hidden" value="${val}"/>
                                                <span id="forpass" style="color: red; font-weight: bold"></span>
                                                <p style="color: red;">${message}</p>
                                                <div class="row">
                                                    <div class="input-field col s6">
                                                        <input id="password" type="password" class="validate" name="password" onchange="return validatePassword()" autocomplete="off">
                                                        <label for="password"><spring:message code="newpassword" /></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="input-field col s6">
                                                        <input id="password2" type="password" class="validate" name="password2" onchange="return checkIfPasswordsAreEqual()" autocomplete="off">
                                                        <label for="password2"><spring:message code="confirmnewpassword" /></label>
                                                    </div>
                                                </div>
                                            </form>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div>
                                    <a href="" class="modal-close waves-effect waves-green btn-flat">Cancel</a>
                                    <button type="submit" form="change-pwd" class="waves-effect waves-light btn">Сhange</button>
                                </div>
                            </div>
                        </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <h4>Reservations</h4>
        <table>
            <c:if test="${reservations.size()>0}">
                <tr>
                    <th>№</th>
                    <th>Discount Id</th>
                    <th>Offer Id</th>
                    <th>Number Of People</th>
                    <th>Status</th>
                    <th>Total Price</th>
                    <th></th>
                </tr>
            </c:if>
            <c:if test="${reservations.size() == 0}"><p>There are no active reservations for this client</p></c:if>
            <c:forEach var="reservation" items="${reservations}">
            <tr>
                <td>${reservation.id}</td>
                <td>${reservation.discountId}</td>
                <td> ${reservation.tourOfferId}</td>
                <td> ${reservation.numberOfPeople}</td>
                <td> ${reservation.status}</td>
                <td> ${reservation.totalPrice}</td>
                </c:forEach>
            </tr>

        </table>
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
            © 2019 Tour de Team
        </div>
    </div>
</footer>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${jsprofile}"></script>
</body>
</html>
