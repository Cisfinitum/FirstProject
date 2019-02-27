<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <spring:url value="/resources/css/materialize.css" var="css"/>
    <spring:url value="/resources/js/materialize.js" var="js"/>
    <spring:url value="/resources/css/registration.css" var="registration"/>
    <spring:url value="/resources/js/chregistration.js" var="chregistration"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${css}" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="${registration}" media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title></title>
</head>
<body>
<div class="registration-page">
    <div class="registration">
        <div class="row">
            <P style="font-size: large; color: green"><b>Tour was reserved successfully</b></P>
            <p style="font-size: large">Number Of People: ${numberOfPeople} $</p>
            <p style="font-size: large">Price per Person ${pricePerUnit} </p>
            <p style="font-size: large">Discount ${discountId} </p>
            <div class="divider"></div>
            <p style="font-size: large">Total price is ${totalPrice} $</p>
            <div class="row">
                <div class="col s6">
                    <div class="input-field">
                        <form name="user" class="col s12" action="/pay" method="POST">
                            <input type="hidden" name="reservationId" value="${reservationId}">
                            <button type="submit" class="waves-effect waves-light btn">PAY NOW
                            </button>

                        </form>
                    </div>
                </div>
                <div class="col s5">
                    <div class="input-field">
                        <form name="later" action="/payLater" method="POST">
                            <button type="submit" class="waves-effect waves-light btn">PAY LATER
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${js}"></script>
</body>
</html>