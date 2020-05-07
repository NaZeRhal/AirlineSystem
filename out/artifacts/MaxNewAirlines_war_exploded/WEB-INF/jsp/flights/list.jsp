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
<fmt:message var="title" key="flights.list.title"/>

<u:html title="${title}">
    <h2 id="pageName" class="listH" align="center">${title}</h2>
    <c:if test="${not empty param.badMessage}">
        <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
    <div class="navinput" align="center">
        <c:if test="${sessionScope.get('currentUser').userType.name == 'DISPATCHER'}">
            <c:set var="hiddenDispatcher" value="hidden"/>
        </c:if>
        <c:if test="${sessionScope.get('currentUser').userType.name == 'ADMINISTRATOR'}">
            <c:set var="hiddenAdmin" value="hidden"/>
        </c:if>
        <c:url var="listFlightsUrl" value="/flights/list.html?language=${language}"/>
        <form action="${listFlightsUrl}" method="get">
            <fmt:message var="actual" key="flights.flight.current"/>
            <fmt:message var="completed" key="flights.flight.completed"/>
            <fmt:message var="canceled" key="flights.flight.canceled"/>
            <fmt:message var="unregistered" key="flights.flight.unregistered"/>
            <fmt:message var="allFlights" key="flights.flight.all"/>
            <fmt:message var="airportsList" key="flights.list.airports"/>
            <fmt:message var="crewManList" key="flights.list.crewman"/>
            <input type="submit" name="actualList" value="${actual}">
            <input type="submit" name="doneList" value="${completed}" ${hiddenDispatcher}>
            <input type="submit" name="cancelledList" value="${canceled}" ${hiddenDispatcher}>
            <input type="submit" name="unregisteredList" value="${unregistered}">
            <input type="submit" name="fullList" value="${allFlights}">
        </form>
        <c:url var="listAirportsUrl" value="/airports/list.html?language=${language}"/>
        <form action="${listAirportsUrl}">
            <input type="submit" name="crewManFullList" value="${airportsList}" ${hiddenDispatcher}>
        </form>
        <c:url var="listCrewManUrl" value="/crewmen/list.html?language=${language}"/>
        <form action="${listCrewManUrl}">
            <input type="submit" name="crewManFullList" value="${crewManList}" ${hiddenAdmin}>
        </form>

        <br>

    </div>

    <div align="center" class="inputSearch">
        <c:if test="${requestScope.actionNumber != 5}">
            <c:set var="hidden" value="hidden"/>
        </c:if>
        <fmt:message var="search" key="flights.list.search"/>
        <input class="searchIn" type="text" id="searchInput" onkeyup="myFunction()"
               placeholder="${search}" title="" ${hidden}>
    </div>

    <table class="FlightlistStyle" id="flightList">
        <thead>
        <tr>
            <th><fmt:message key="flights.table.code"/></th>
            <th><fmt:message key="flights.table.departureAirport"/></th>
            <th><fmt:message key="flights.table.arrivalAirport"/></th>
            <th><fmt:message key="flights.table.departureTime"/></th>
            <th><fmt:message key="flights.table.arrivalTime"/></th>
            <th><fmt:message key="flights.table.flightStatus"/></th>
            <c:choose>
                <c:when test="${sessionScope.get('currentUser').userType.name == 'ADMINISTRATOR'}">
                    <th><fmt:message key="button.edit"/></th>
                </c:when>
                <c:when test="${sessionScope.get('currentUser').userType.name == 'DISPATCHER'
                    && (requestScope.actionNumber == 5 || requestScope.actionNumber == 1)}">
                    <th><fmt:message key="button.viewCrew"/></th>
                </c:when>
                <c:otherwise>
                    <th><fmt:message key="button.addCrew"/></th>
                </c:otherwise>
            </c:choose>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="flight" items="${requestScope.flights}">
        <tr id="${flight.id}">
            <c:url var="editUrl" value="/flights/edit.html?language=${language}">
                <c:param name="flightId" value="${flight.id}"/>
            </c:url>
            <td>${flight.flightCode}</td>
            <td>${flight.departureAirport.city} ${flight.departureAirport.airportCode}</td>
            <td>${flight.arrivalAirport.city} ${flight.arrivalAirport.airportCode}</td>
            <td>${flight.departureTime.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))}</td>
            <td>${flight.arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))}</td>
            <td>${flight.flightStatus}</td>
            <td>
                <c:choose>
                    <c:when test="${sessionScope.get('currentUser').userType.name == 'ADMINISTRATOR'}">
                        <a href="${editUrl}" type="" id="editPencil">
                            <i class="fa fa-pencil" aria-hidden="true"></i>
                        </a>
                    </c:when>
                    <c:when test="${sessionScope.get('currentUser').userType.name == 'DISPATCHER'
                        && (requestScope.actionNumber == 5 || requestScope.actionNumber == 1)}">
                        <c:url var="crewListUrl" value="/crew/list.html?language=${language}">
                            <c:param name="flightId" value="${flight.id}"/>
                        </c:url>
                        <a href="${crewListUrl}" type=""><i class="fa fa-eye" aria-hidden="true"></i></a>
                    </c:when>
                    <c:otherwise>
                        <c:url var="crewEditUrl" value="/crew/edit.html?language=${language}">
                            <c:param name="flightId" value="${flight.id}"/>
                        </c:url>
                        <a href="${crewEditUrl}" type=""><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
                    </c:otherwise>
                </c:choose>
            </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>

    <div class="downButtons">
        <a href="#" title="" class="topbutton">
            <i class="fa fa-arrow-circle-up" aria-hidden="true"></i></a>
        <c:url var="addUrl" value="/flights/edit.html"/>
        <c:if test="${sessionScope.get('currentUser').userType.name == 'DISPATCHER'}">
            <c:set var="hiddenAdd" value="hidden"/></c:if>
        <form action="${addUrl}" method="get">
            <button class="addButton" title="" type="submit" value="" ${hiddenAdd}>
                <i class="fa fa-plus-square" aria-hidden="true"></i>
            </button>
        </form>

    </div>
    <script>
        function myFunction() {
            let input, filter, table, tr, td, i;

            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("flightList");
            tr = table.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</u:html>