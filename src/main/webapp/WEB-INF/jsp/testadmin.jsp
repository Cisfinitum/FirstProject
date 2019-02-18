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
        <div class="nav-wrapper tab " >
            <a href="/index" class="brand-logo">    Tour De Team</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down" style="margin-right: 20px">

                <li> <a href="javascript:void(0)" id="defaultTab"
                       onclick="openContent(event, 'Tours')">Tours

                </a></li>
                <li><a href="javascript:void(0)"
                       onclick="openContent(event, 'AddTour')">Add
                    Tour
                </a></li>
                <li><a href="javascript:void(0)"

                       onclick="openContent(event, 'Reservations')">
                    <span class="new badge" style="background-color: #ab4160;">4</span>
                    Reservations
                </a></li>
                <li style="margin-right: 20px" ><a href="javascript:void(0)"

                       onclick="openContent(event, 'Clients')">Clients
                </a></li>
                <li>
                    <button class="btn waves-effect waves-light" type="submit" name="action">Log Out
                    </button>
                </li>

            </ul>
        </div>
    </nav>
</header>
<main>
    <div id="Tours" class="tabcontent">

        <div class="container">
            <h2>Tours</h2>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Tour Type</th>
                    <th>Country</th>
                    <th>City</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Price</th>
                    <th>Hotel</th>
                    <th>Description</th>
                    <th>Discount</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td>1</td>
                    <td>Shopping</td>
                    <td>Thailand</td>
                    <td>Bangkok</td>
                    <td>12/11/20</td>
                    <td>12/12/20</td>
                    <td>20 000</td>
                    <td>El plaza</td>
                    <td>Tour to Thailand</td>
                    <td>50%</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Shopping</td>
                    <td>Thailand</td>
                    <td>Bangkok</td>
                    <td>12/11/20</td>
                    <td>12/12/20</td>
                    <td>20 000</td>
                    <td>El plaza</td>
                    <td>Tour to Thailand</td>
                    <td>50%</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Shopping</td>
                    <td>Thailand</td>
                    <td>Bangkok</td>
                    <td>12/11/20</td>
                    <td>12/12/20</td>
                    <td>20 000</td>
                    <td>El plaza</td>
                    <td>Tour to Thailand</td>
                    <td>50%</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Shopping</td>
                    <td>Thailand</td>
                    <td>Bangkok</td>
                    <td>12/11/20</td>
                    <td>12/12/20</td>
                    <td>20 000</td>
                    <td>El plaza</td>
                    <td>Tour to Thailand</td>
                    <td>50%</td>
                </tr>
                </tbody>
            </table>
            <div class="row">
            <div class="col s2 offset-s10">
                <div class="input-field">
                    <button class="btn waves-effect waves-light" type="submit" name="action"
                            onclick="openContent(event, 'AddTour')"> Add Tour
                    </button>
                </div>
            </div>
            </div>
            <br>
            <br>
            <br>
            <br>
            <br>
        </div>
    </div>

    <div id="AddTour" class="tabcontent">
        <div class="container">
            <h2>Add Tour</h2>
            <form method="POST" action="addtour">
                <div class="row">
                    <div class="col s3">
                        <div class="input-field">
                            <input type="text" id="autocomplete-input" class="autocomplete" name="tourType">
                            <label for="autocomplete-input">Tour type</label>
                        </div>
                    </div>
                    <div class="col s3">
                        <div class="input-field">
                            <i class="material-icons prefix">event_available</i>
                            <input type="text" id="autocomplete-date" class="datepicker" name="startDate">
                            <label for="autocomplete-date">Start Date</label>
                        </div>
                    </div>
                    <div class="col s3">
                        <div class="input-field">
                            <i class="material-icons prefix">event_available</i>
                            <input type="text" id="autocomplete-date2" class="datepicker" name="endDate">
                            <label for="autocomplete-date2">End Date</label>
                        </div>
                    </div>
                    <div class="col s3">
                        <div class="input-field">
                            <input type="text" id="autocomplete-input4" class="pricePerPerson" name="country">
                            <label for="autocomplete-input4">Country</label>
                        </div>
                    </div>
                    <div class="col s3">
                        <div class="input-field">
                            <input type="text" id="autocomplete-input5" class="pricePerPerson" name="city">
                            <label for="autocomplete-input5">City</label>
                        </div>
                    </div>
                    <div class="col s3">
                        <div class="input-field">
                            <input type="text" id="autocomplete-input6" class="pricePerPerson" name="hotel">
                            <label for="autocomplete-input6">Hotel</label>
                        </div>
                    </div>

                    <div class="col s3">
                        <div class="input-field">
                            <input type="text" id="autocomplete-input2" class="pricePerPerson" name="pricePerPerson">
                            <label for="autocomplete-input2">Price Per Person</label>
                        </div>
                    </div>
                    <div class="col s3">
                        <div class="input-field">
                            <input type="text" id="autocomplete-input3" class="pricePerPerson" name="discount">
                            <label for="autocomplete-input3">Discount</label>
                        </div>
                    </div>
                    <div class="input-field">
                        <div class="input-field col s12">
                            <textarea id="textarea1" class="materialize-textarea" name="tourDescription"></textarea>
                            <label for="textarea1">Tour Description</label>
                        </div>
                    </div>
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
    </div>

    <div id="Reservations" class="tabcontent">
        <div class="container">
            <h2>Reservations</h2>
            <form method="POST" action="#">
                <div class="row">
                    <ul class="collection">
                        <li class="collection-item avatar">
                            <i class="material-icons circle green">star</i>
                            <span class="title"><b>Thailand</b></span>
                            <p>Jhon Smith <br>
                                +1 24324 24
                            </p>
                            <a href="#!" class="secondary-content"><i class="material-icons">done_outline</i></a>
                        </li>
                        <li class="collection-item avatar">
                            <i class="material-icons circle green">star</i>
                            <span class="title"><b>Russia</b></span>
                            <p>Fedor <br>
                                +7900223232
                            </p>
                            <a href="#!" class="secondary-content"><i class="material-icons">done_outline</i></a>
                        </li>
                        <li class="collection-item avatar">
                            <i class="material-icons circle green">star</i>
                            <span class="title"><b>America</b></span>
                            <p>Masha Ivanova <br>
                                masha@vanova.ru
                            </p>
                            <a href="#!" class="secondary-content"><i class="material-icons">done_outline</i></a>
                        </li>
                        <li class="collection-item avatar">
                            <i class="material-icons circle green">star</i>
                            <span class="title"><b>Turkey</b></span>
                            <p>Nastya Broti <br>
                                +79800232
                            </p>
                            <a href="#!" class="secondary-content"><i class="material-icons">done_outline</i></a>
                        </li>
                    </ul>
                </div>
            </form>

        </div>
    </div>

    <div id="Clients" class="tabcontent">
        <div class="container">
            <h2>Clients</h2>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td>Alvin</td>
                    <td>Eclair@gmail.com</td>
                    <td>8988888</td>
                    <td><a href="#!" class="secondary-content">
                        <i class="small material-icons">block</i>
                    </a>
                    </td>
                </tr>
                <tr>
                    <td>Alan</td>
                    <td>Jellybean@gmail.com</td>
                    <td>8988888</td>
                    <td><a href="#!" class="secondary-content">
                        <i class="small material-icons">block</i>
                    </a>
                    </td>
                </tr>
                <tr>
                    <td>Jonathan</td>
                    <td>Lollipop@gmail.com</td>
                    <td>8988888</td>
                    <td><a href="#!" class="secondary-content">
                        <i class="small material-icons">block</i>
                    </a>
                    </td>
                </tr>
                </tbody>
            </table>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
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
            © 2018 Copyright Text
        </div>
    </div>
</footer>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="${js}"></script>
<script type="text/javascript" src="${tours}"></script>
</body>
</html>