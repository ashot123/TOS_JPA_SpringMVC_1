<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><spring:message code="success.title"/></title>
    <link href="<c:url value="/css/screen.css" />" rel="stylesheet">
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div>
    <fieldset>
        <legend><spring:message code="success.title"/></legend>
        <spring:message code="success.message"/>
        <br/>
        <a href="<c:url value="/flights/selectFlight"/>"><spring:message code="find.another.flight"/></a>
        <b><c:out value="${sessionScope.user}"/></b>
    </fieldset>
</div>


</body>
</html>