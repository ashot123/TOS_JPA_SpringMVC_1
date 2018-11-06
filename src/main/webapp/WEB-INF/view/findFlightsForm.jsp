<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>


<html>
<head>

    <title>Find Flights</title>
    <%--<link href="<c:url value="../css/screen.css" />" rel="stylesheet">--%>
    <link href="<c:url value="/css/screen.css" />" rel="stylesheet">
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">

    <script type="text/javascript">
        function changeFunc(time_selector_id, time_id, time_constant) {

            var selectBox = document.getElementById(time_selector_id);
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;

            //alert("selectedValue = " + selectedValue);
            var textBox = document.getElementById(time_id);
            if (selectedValue == "FALSE") {
                textBox.disabled = true;
                textBox.value = time_constant;
                //alert("textBox.disabled = " + textBox.disabled);

            } else if (selectedValue == "TRUE") {
                textBox.disabled = false;
                textBox.value = time_constant;
                //alert("textBox.disabled = " + textBox.disabled)

            } else {
                alert("Something wrong with selector names");
            }
        }

        // Default reset doesn't disable enabled input
        function helpReset(time_constant) {
            alert('entered');
            alert('time_constant  =  ' + time_constant);
            var arrivalTime = document.getElementById('arrival_time_id');
            arrivalTime.disabled = true;
            arrivalTime = time_constant;
            var departureTime = document.getElementById('departure_time_id');
            departureTime.disabled = true;
            departureTime = time_constant;
        }


    </script>

</head>

<body>

<jsp:include page="include/header.jsp"/>
<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: "dd.mm.yy",
            buttonImageOnly: true
        });
    });

    $(function () {
        $("#datepicker2").datepicker({
            dateFormat: "dd.mm.yy"
        });
    });

   /* $("#disable").on("click", function () {
        if (spinner.spinner("option", "disabled")) {
            spinner.spinner("enable");
        } else {
            spinner.spinner("disable");
        }
    });*/
</script>

<div>
    <fieldset>
        <legend><spring:message code="find.title"/></legend>


        <form:form action="submitForm" modelAttribute="flightForm">
            <table>
                <tr>
                    <td colspan=5><form:errors path="" cssClass="commonError"/></td>
                </tr>
                <tr>
                    <td colspan=5><br/></td>
                </tr>

                    <%--Departure City:--%>
                <tr>
                    <td align="left" class="bigBoxSize"><spring:message code="departure.city" text="Not found!!!"/>:
                    </td>
                    <td colspan="2" class="bigBoxSize">
                        <label>
                            <form:select path="departureCityId" id="departure_city_id" cssClass="middleBoxSize">
                                <spring:message code="select.city" var="i18nTooltip1"/>
                                <form:option value="0" label="${i18nTooltip1}"/>
                                <form:options items="${cityNames}" itemValue="id" itemLabel="name"/>
                            </form:select>
                        </label>
                    </td>
                    <td><form:errors path="departureCityId" cssClass="error"/></td>
                    <td colspan="1"></td>
                    <td colspan="1"></td>
                </tr>

                    <%--Arrival City:--%>
                <tr>
                    <td class="bigBoxSize"><spring:message code="arrival.city" text="BBB"/>:</td>
                    <td colspan="2">
                        <label>
                            <form:select path="arrivalCityId" id="arrival_city_id" cssClass="middleBoxSize">
                                <spring:message code="select.city" var="i18nTooltip2"/>
                                <form:option value="0" label="${i18nTooltip2}"/>
                                <form:options items="${cityNames}" itemValue="id" itemLabel="name"/>
                            </form:select>

                        </label>
                    </td>
                    <td><form:errors path="arrivalCityId" cssClass="error"/></td>
                    <td colspan="2"></td>

                </tr>
                <tr>
                    <td colspan="5"><br/></td>
                </tr>


                    <%--Departure date:--%>
                <tr>
                    <td><spring:message code="departure.date"/>:</td>
                    <td>
                        <label>
                            <form:input id="datepicker" path="departureDate" cssClass="smallBoxSize1" readonly="true"/>
                        </label>
                    </td>
                    <td>
                            <%--<label class="smallChar">
                                (dd.MM.yyyy)
                            </label>--%>
                    </td>
                    <td>
                        <form:errors path="departureDate" cssClass="error" title="Hello!!!"/>
                    </td>
                    <td colspan="1"></td>
                </tr>


                    <%--Departure time:--%>
                <tr>
                    <td><spring:message code="departure.time"/>:</td>
                    <td>
                        <label>
                            <fmt:formatDate value="${flightForm.firstMinuteOfDay}" var="dateFirstMinute"
                                            pattern="HH:mm"/>
                            <form:select path="departureTimeSelected"
                                         id="departure_time_selector_id"
                                         cssClass="smallBoxSize1"
                                         onchange='changeFunc("departure_time_selector_id", "departure_time_id", "${dateFirstMinute}");'>

                                <spring:message code="all.day" text="Not found!!!" var="allDayVar"/>
                                <spring:message code="after" text="Not found!!!" var="afterVar"/>
                                <form:option value="FALSE" label="${allDayVar}"/>
                                <form:option value="TRUE" label="${afterVar}"/>
                            </form:select>
                        </label>
                    </td>
                    <td class="smallBoxSize2">
                        <label>

                            <form:input path="departureTime"
                                        id="departure_time_id"
                                        disabled="${!flightForm.departureTimeSelected}"
                                        name="departure_time"
                                        cssClass="smallBoxSize2"/>

                        </label>
                    </td>
                    <td>
                        <form:errors path="departureTime" cssClass="error"/>
                    </td>
                    <td colspan="1"></td>

                </tr>
                <tr>
                    <td colspan="5"><br/></td>
                </tr>


                    <%--Arrival date:--%>
                <tr>
                    <td><spring:message code="arrival.date"/>:</td>
                    <td>
                        <label>
                            <form:input id="datepicker2" path="arrivalDate" cssClass="smallBoxSize1" readonly="true"/>
                        </label>
                    </td>
                    <td>
                            <%--<label class="smallChar">
                                (dd.MM.yyyy)
                            </label>--%>
                    </td>
                    <td>
                        <form:errors path="arrivalDate" cssClass="error"/>
                    </td>
                    <td colspan="1"></td>

                </tr>


                    <%--Arrival time:--%>
                <tr>
                    <td><spring:message code="arrival.time"/>:</td>
                    <td>
                        <label>
                            <fmt:formatDate value="${flightForm.lastMinuteOfDay}" var="dateLastMinute"
                                            pattern="HH:mm"/>
                            <form:select path="arrivalTimeSelected"
                                         id="arrival_time_selector_id"
                                         cssClass="smallBoxSize1"
                                         onchange='changeFunc("arrival_time_selector_id", "arrival_time_id", "${dateLastMinute}");'>

                                <spring:message code="all.day" text="Not found!!!" var="allDayVar"/>
                                <spring:message code="before" text="Not found!!!" var="beforeVar"/>
                                <form:option value="FALSE" label="${allDayVar}"/>
                                <form:option value="TRUE" label="${beforeVar}"/>
                            </form:select>
                        </label>
                    </td>
                    <td class="smallBoxSize2">
                        <label>

                            <form:input path="arrivalTime"
                                        id="arrival_time_id"
                                        disabled="${!flightForm.arrivalTimeSelected}"
                                        name="arrival_time"
                                        cssClass="smallBoxSize2"/>

                        </label>
                    </td>
                    <td>
                        <form:errors path="arrivalTime" cssClass="error"/>
                    </td>
                    <td colspan="1"></td>

                </tr>

                <tr>
                    <td colspan="5"><br/></td>
                </tr>


                <tr>
                    <td colspan="5">
                        <input type="submit" value="<spring:message code="submit"/>"/>
                        <input type="reset" value="<spring:message code="reset"/>"
                               onclick="helpReset(${flightForm.firstMinuteOfDay})"/>
                    </td>
                </tr>

            </table>
        </form:form>

    </fieldset>


    <%-- <br/>
     <a href="<c:url value="/logoutController.do"/>">Logout</a> if you are not <b><c:out
         value="${sessionScope.user}"/></b>--%>

</div>
</body>
</html>
