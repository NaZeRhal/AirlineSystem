<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language
       : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:message var="title" key="crew.view.title"/>

<u:html title="$title">
    <h2 id="pageName" align="center">${title} ${requestScope.flight.flightCode}
        <c:choose>
            <c:when test="${requestScope.flight.departureTime.dayOfYear == requestScope.flight.arrivalTime.dayOfYear}">
                ${requestScope.flight.departureTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
            </c:when>
            <c:otherwise>
                ${requestScope.flight.departureTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
                - ${requestScope.flight.arrivalTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
            </c:otherwise>
        </c:choose>
    </h2
    <div class="tables">
        <table class="flightCrew" id="flightViewCrewList">
            <thead>
            <tr>
                <th colspan="3">
                        ${requestScope.flight.departureAirport.city} ${requestScope.flight.departureAirport.airportCode}
                    - ${requestScope.flight.arrivalAirport.city} ${requestScope.flight.arrivalAirport.airportCode}
                        ${requestScope.flight.departureTime.format(DateTimeFormatter.ofPattern("HH:mm"))}
                    - ${requestScope.flight.arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"))}
                </th>
                <th id="flightStatusId">${requestScope.flight.flightStatus}</th>
            </tr>

            </thead>
            <tbody>
            <tr>
                <th><fmt:message key="crew.table.name"/></th>
                <th><fmt:message key="crew.table.surname"/></th>
                <th><fmt:message key="crew.table.dateOfBirth"/></th>
                <th><fmt:message key="crew.table.profession"/></th>
            </tr>
            <c:forEach var="crewMan" items="${requestScope.crewManList}">
                <tr id="${crewMan.id}">
                    <td>${crewMan.firstName}</td>
                    <td>${crewMan.lastName}</td>
                    <td>${crewMan.dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
                    <td>${crewMan.profession.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <c:url var="editUrl" value="/crew/edit.html?language=${language}">
            <c:param name="flightId" value="${requestScope.flight.id}"/>
        </c:url>
        <fmt:message var="edit" key="button.edit"/>
        <form action="${editUrl}" method="post">
            <div align="center">
                <input id="agreeButton" class="agreeButton" type="submit" value="${edit}">
            </div>
        </form>
        <br>
        <%--<c:url var="listUrl" value="/flights/list.html?language=${language}"/>--%>
        <fmt:message var="back" key="button.back"/>
        <%--<form action="${listUrl}" method="get">--%>
            <div align="center">
                <input class="backButton" type="submit" value="${back}" onclick="history.back()">
            </div>
        <%--</form>--%>
    </div>
    <script>
        window.addEventListener('load', function () {
            let status = document.getElementById('flightStatusId').innerHTML;
            if (status === 'DEPARTED' || status === 'LANDED' || status === 'DONE' || status === 'BOARDING') {
                document.getElementById('agreeButton').hidden = true;
            }
        });
    </script>
</u:html>