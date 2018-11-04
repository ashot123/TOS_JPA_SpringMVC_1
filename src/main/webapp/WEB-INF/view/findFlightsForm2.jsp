<%@ page import="java.util.HashMap" %>
<%--
  User: Ashot
  Date: 1/11/14
  Time: 12:30 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:useBean id="cityList" scope="request" type="java.util.List"/>
<jsp:useBean id="now" class="java.util.Date"/>
<jsp:useBean id="ERRORS" scope="request" class="tos.web.utils.ErrorHolder"/>

<html>
<head>
    <title>Find Flights</title>
    <link rel="stylesheet"
          href="./css/screen.css"
          type="text/css"/>

    <link rel="stylesheet"
          href="./css/styles.css"
          type="text/css"/>
    <script type="text/javascript">

        function changeFunc(time_selector_id, time_id, time_constant) {
            var selectBox = document.getElementById(time_selector_id);
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            var textBox = document.getElementById(time_id);
            if (selectedValue == "all_day") {
                textBox.disabled = true;
                textBox.value = time_constant;
            } else if (selectedValue == "timeLimit") {
                textBox.disabled = false;
                textBox.value = time_constant;
            } else {
                alert("Something wrong with selector names");
            }
        }

    </script>
</head>

<body>
<div>
    <fieldset>
        <legend>Find Flights</legend>
        <form action="findFlightsController.do" method="post">
            <table>
                <%--Departure City:--%>
                <tr>
                    <td class="bigBoxSize">Departure City:</td>
                    <td colspan="2">
                        <label>
                            <select name="departure_city_id" class="middleBoxSize">
                                <option value="0">---Select City---</option>
                                <c:forEach var="city" items="${cityList}">
                                    <option value="<c:out value="${city.id}"/>">
                                        <c:out value="${city.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                    </td>
                    <td class="error" colspan="2">
                        <c:if test="${not empty ERRORS.asMap['departure_city_id']}">
                            <c:out value="${ERRORS.asMap['departure_city_id']}"/>
                        </c:if>
                   </td>
                </tr>

                <%--Arrival City:--%>
                <tr>
                    <td>Arrival City:</td>
                    <td colspan="2">
                        <label>
                            <select name="arrival_city_id" class="middleBoxSize">
                                <option value="0">---Select City---</option>
                                <c:forEach var="city" items="${cityList}">
                                    <option value="<c:out value="${city.id}"/>">
                                        <c:out value="${city.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                    </td>
                    <td class="error">
                        <c:if test="${not empty ERRORS.asMap['arrival_city_id']}">
                            <c:out value="${ERRORS.asMap['arrival_city_id']}"/>
                        </c:if>
                    </td>
                </tr>

                <tr>
                    <td colspan="4"><br/></td>
                </tr>
                <%--Departure date:--%>
                <tr>
                    <td>Departure date:</td>
                    <td colspan="2">
                        <label>

                            <%-- <c:if test="${empty flightForm.departureDate}">


 </c:if>--%>
                            <%--<fmt:formatDate value="${currentDay}" var="dateString" pattern="dd.MM.yyyy"/>--%>
                            <%--<fmt:formatDate value="${currentDay}" var="dateString" pattern="dd.MM.yyyy"/>&ndash;%&gt;--%>
                            <%-- <form:input path="arrivalDate" value="${dateString}" cssClass="smallBoxSize1"/>--%>
                            <input type="text"
                                   name="departure_date"
                                   value="<fmt:formatDate value="${now}" type="date" pattern="dd.MM.yyyy"/>"
                                   class="middleBoxSize"/>
                        </label>
                    </td>
                    <td class="error" >
                        <c:if test="${not empty ERRORS.asMap['departure_date']}">
                            <c:out value="${ERRORS.asMap['departure_date']}"/>
                        </c:if>
                    </td>
                </tr>

                <%--Departure time:--%>
                <tr>
                    <td>Departure time:</td>
                    <td>
                        <label>
                            <select id="departure_time_selector_id"
                                    name="departure_time_selector"
                                    class="smallBoxSize1"
                                    onchange='changeFunc("departure_time_selector_id", "departure_time_id", "<c:out
                                            value="${requestScope.startTime}"/>");'>
                                <option value="all_day" selected>All Day</option>
                                <option value="timeLimit">After</option>
                            </select>
                        </label>
                    </td>
                    <td class="smallBoxSize2" >
                        <label>
                            <%-- <fmt:formatDate value="${firstMinute}" pattern="HH:mm"/>--%>
                            <input type="text"
                                   id="departure_time_id"
                                   disabled="disabled"
                                   name="departure_time"
                                   class="smallBoxSize2"
                                   value="<c:out value="${requestScope.startTime}"/>"/>
                        </label>
                    </td>
                    <td class="error" >
                        <c:if test="${not empty ERRORS.asMap['departure_time']}">
                            <c:out value="${ERRORS.asMap['departure_time']}"/>
                        </c:if>
                    </td>
                </tr>

                <tr>
                    <td colspan="4"><br/></td>
                </tr>
                <%--Arrival date:--%>
                <tr>
                    <td>Arrival date:</td>
                    <td colspan="2">
                        <label>
                            <input type="text"
                                   name="arrival_date"
                                   value="<fmt:formatDate value="${now}"
                                   type="date" pattern="dd.MM.yyyy"/>"
                                   class="middleBoxSize"/>
                        </label>
                    </td>
                    <td class="error" >
                        <c:if test="${not empty ERRORS.asMap['arrival_date']}">
                            <c:out value="${ERRORS.asMap['arrival_date']}"/>
                        </c:if>
                    </td>

                </tr>

                <%--Arrival time:--%>
                <tr>
                    <td>Arrival time:</td>
                    <td>
                        <label>
                            <select id="arrival_time_selector_id"
                                    name="arrival_time_selector"
                                    class="smallBoxSize1"
                                    onchange='changeFunc("arrival_time_selector_id", "arrival_time_id", "<c:out
                                            value="${requestScope.endTime}"/>");'>
                                <option value="all_day" selected>All Day</option>
                                <option value="timeLimit">Before</option>
                            </select>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="text"
                                   id="arrival_time_id"
                                   disabled="disabled"
                                   name="arrival_time"
                                   class="smallBoxSize2"
                                   value="<c:out value="${requestScope.endTime}"/>"/>
                        </label>
                    </td>
                    <td class="error" >
                        <c:if test="${not empty ERRORS.asMap['arrival_time']}">
                            <c:out value="${ERRORS.asMap['arrival_time']}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="4"><br/></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="submit" value="Submit">

                        <input type="reset" value="Reset">
                    </td>
                </tr>

            </table>
        </form>
    </fieldset>


   <%-- <br/>
    <a href="<c:url value="/logoutController.do"/>">Logout</a> if you are not <b><c:out
        value="${sessionScope.user}"/></b>--%>

</div>
</body>
</html>
