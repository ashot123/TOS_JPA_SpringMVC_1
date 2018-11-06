<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>

    <link href="<c:url value="/css/screen.css" />" rel="stylesheet">
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">

    <title><spring:message code="order.tickets.title"/></title>
    <%--
        <style>
            .error {
                color: #ff0000;
                font-weight: bold;
            }
        </style>
    --%>
</head>
<body>
<jsp:include page="include/header.jsp"/>


<div>
    <fieldset>
        <legend><spring:message code="order.tickets.title"/></legend>
        <div>

            <form:form action="submitForm" modelAttribute="orderForm">

                <table>
                    <tr>
                        <td colspan=3><form:errors path="" cssClass="commonError"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="departure.city"/>:</td>
                        <td>${orderForm.departureCity}</td>
                        <td colspan="1"></td>
                    </tr>

                    <tr>
                        <td><spring:message code="arrival.city"/>:</td>
                        <td>${orderForm.arrivalCity}</td>
                        <td colspan="1"></td>
                    </tr>

                    <tr>
                        <td><spring:message code="departure.date"/>:</td>
                        <fmt:formatDate value="${orderForm.departureDate}" var="departureDate"
                                        pattern="yyyy-MM-dd HH:mm"/>

                        <td>${departureDate}</td>
                        <td colspan="1"></td>
                    </tr>
                    <tr>
                        <td><spring:message code="arrival.date"/>:</td>
                        <fmt:formatDate value="${orderForm.arrivalDate}" var="arrivalDate"
                                        pattern="yyyy-MM-dd HH:mm"/>

                        <td>${arrivalDate}</td>
                        <td colspan="1"></td>
                    </tr>


                    <tr>
                        <td colspan="3"></td>
                    </tr>
                    <tr>
                    <tr><td colspan="3"><b><spring:message code="orderForm.ticketCount"/></b></td></tr>
                    </tr>

                        <%--Class 1--%>
                    <tr>
                        <td class="smallBoxSize2">
                            <spring:message code="orderForm.class1.count"
                                            arguments="${orderForm.class1TicketsAvailable}"/>:
                        </td>

                        <td><form:input path="class1TicketsCount"/></td>
                        <td><form:errors path="class1TicketsCount" cssClass="error"/></td>

                    </tr>

                        <%--Class 2 --%>
                    <tr>
                        <td class="smallBoxSize2">
                            <spring:message code="orderForm.class2.count"
                                            arguments="${orderForm.class2TicketsAvailable}"/>:
                        </td>

                        <td><form:input path="class2TicketsCount"/></td>
                        <td><form:errors path="class2TicketsCount" cssClass="error"/></td>

                    </tr>

                        <%--
                                <tr>
                                    <td class="bigBoxSize">
                                        <c:out value="Class 1 tickets count: "/>
                                    </td>
                                    <td>
                                        <label><input type="text" class="tinyBoxSize" maxlength="2" name="class1_ordered_tickets"/>
                                            <label class="miniAreaClass"><c:out
                                                    value="(Available: ${flight.class1TicketsAvailable})"/>
                                    </td>
                                    <td class="error">
                                        &lt;%&ndash;<c:if test="${not empty ERRORS.asMap['departure_time']}">
                                        <c:out value="${ERRORS.asMap['departure_time']}"/>
                                    </c:if>&ndash;%&gt;
                                    </td>
                                </tr>
                                <tr>
                                    <td class="bigBoxSize">
                                        <c:out value="Class 2 tickets count: "/>
                                    </td>
                                    <td>
                                        <label><input type="text" class="tinyBoxSize" maxlength="2" name="class2_ordered_tickets"/>
                                            <label class="miniAreaClass"><c:out
                                                    value="(Available: ${flight.class2TicketsAvailable})"/>
                                    </td>
                                    <td class="error">
                                        &lt;%&ndash;<c:if test="${not empty ERRORS.asMap['departure_time']}">
                                        <c:out value="${ERRORS.asMap['departure_time']}"/>
                                    </c:if>&ndash;%&gt;
                                    </td>
                                </tr>
                        --%>


                    <tr>
                        <td class="bigBoxSize">
                            <spring:message code="order.creditCard" text="Not found!!!"/>:
                        </td>
                        <td><form:input path="creditCardNumber"/></td>
                        <td><form:errors path="creditCardNumber" cssClass="error"/></td>
                    </tr>


                    <tr>
                        <td colspan="3">
                            <input type="submit" value="<spring:message code="submit"/>"/>
                            <input type="reset" value="<spring:message code="reset"/>">
                        </td>
                    </tr>
                </table>
            </form:form>


        </div>
    </fieldset>

    <br/>
    <%--   <a href="<c:url value="/logoutController.do"/>">Logout</a> if you are not <b><c:out
           value="${sessionScope.user}"/></b>
    --%>
</div>


</body>
</html>