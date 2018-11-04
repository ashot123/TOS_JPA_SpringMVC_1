<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<jsp:useBean id="flights" scope="request" type="java.util.List<tos.entity.Flight>"/>--%>

<html>
<head>
    <style type="text/css">
        td {
            border: 1px solid black;
        }
    </style>

    <title>Find Flights</title>

    <link href="<c:url value="/css/screen.css" />" rel="stylesheet">
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">


</head>

<body>
<jsp:include page="include/header.jsp"/>

<div>
    <fieldset>
        <legend>Found Flights</legend>
        <div>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Departure City</th>
                    <th>Arrival City</th>
                    <th>Departure Date</th>
                    <th>Arrival Date</th>
                    <th>Aircraft</th>
                    <th>Company</th>
                    <th>Order</th>
                </tr>


                <c:forEach items="${flights}" var="flight">
                    <tr>
                        <td>${flight.id}</td>
                        <td>${flight.departureCity.name}</td>
                        <td>${flight.arrivalCity.name}</td>
                        <td><fmt:formatDate value="${flight.departureDate}" type="both"
                                            pattern="yyyy.MM.dd HH:mm"/></td>
                        <td><fmt:formatDate value="${flight.arrivalDate}" type="both" pattern="yyyy.MM.dd HH:mm"/></td>
                        <td>${flight.aircraft.model}</td>
                        <td>${flight.aircraft.company.name}</td>
                        <td>
                            <c:set var="path" value="${'/order/'}${flight.id}"/>
                            <a href="<c:url value="${path}" >
                              <%-- <c:param name="flightId" value="${flight.id}"/>--%>
                             </c:url>"><c:out value="Order"/>
                            </a>
                        </td>


                    </tr>
                </c:forEach>
                <%--<c:forEach var="flight" items="${flights}">
                    <tr>
                        <td>${flight.id}</td>
                        <td><c:out value="${flight.departureCity.name}"/></td>
                        <td><c:out value="${flight.arrivalCity.name}"/></td>
                        <td><fmt:formatDate value="${flight.departureDate}" type="both" pattern="yyyy.MM.dd HH:mm"/></td>
                        <td><fmt:formatDate value="${flight.arrivalDate}" type="both" pattern="yyyy.MM.dd HH:mm"/></td>
                        <td><c:out value="${flight.aircraft.model}"/></td>
                        <td><c:out value="${flight.aircraft.company.name}"/></td>
                        <td>
                         &lt;%&ndash;  <a href="<c:url value="/showOrderTicketFormController.do" >
                               <c:param name="flightId" value="${flight.id}"/>
                             </c:url>"><c:out value="Order"/>
                            </a&ndash;%&gt;>
                        </td>
                    </tr>
                </c:forEach>--%>

            </table>
        </div>
    </fieldset>

    <br/>
    <%--   <a href="<c:url value="/logoutController.do"/>">Logout</a> if you are not <b><c:out
           value="${sessionScope.user}"/></b>
   --%>
</div>
</body>
</html>
