<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language
       : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<c:if test="${empty requestScope.flight.flightCode}">
    <fmt:message var="title" key="flights.add.title"/>
    <c:set var="hidden" value="hidden"/>
</c:if>
<c:if test="${!empty requestScope.flight.flightCode}">
    <fmt:message var="title" key="flights.edit.title"/>
</c:if>
<u:html title="${title}">
    <h2 id="pageName" class="editH" align="center">${title}
        <c:if test="${not empty requestScope.flight.flightCode}">${requestScope.flight.flightCode}</c:if>
    </h2>
    <c:if test="${not empty param.badMessage}">
        <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
    <div class="tables">
        <c:url var="editUrl" value="/flights/edit.html?language=${language}"/>
        <form action="${editUrl}" method="post">
            <input type="hidden" name="flightId" value="${requestScope.flight.id}"/>
            <table class="editStyle">
                <thead>
                <tr>
                    <th><fmt:message key="flights.table.departureAirport"/></th>
                    <th><fmt:message key="flights.table.arrivalAirport"/></th>
                    <th><fmt:message key="flights.table.departureTime"/></th>
                    <th><fmt:message key="flights.table.arrivalTime"/></th>
                    <th><fmt:message key="flights.table.flightStatus"/></th>
                    <th ${hidden}></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:choose>
                        <c:when test="${requestScope.flight.flightStatus == 'DONE' or requestScope.flight.flightStatus == 'BOARDING'
                       or requestScope.flight.flightStatus == 'DEPARTED' or requestScope.flight.flightStatus == 'LANDED'}">
                            <td>${requestScope.flight.departureAirport.city} ${requestScope.flight.departureAirport.airportCode}</td>
                            <td>${requestScope.flight.arrivalAirport.city} ${requestScope.flight.arrivalAirport.airportCode}</td>
                            <td>${requestScope.flight.departureTime.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))}</td>
                            <td>${requestScope.flight.arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))}</td>
                            <td>${requestScope.flight.flightStatus}</td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <select name="departure" title="" required>
                                    <c:forEach var="airport" items="${requestScope.airports}">
                                        <c:choose>
                                            <c:when test="${airport.equals(requestScope.flight.departureAirport)}">
                                                <c:set var="selected" value="selected"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:remove var="selected"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <option value="${airport.airportCode}" ${selected}>${airport.city} ${airport.airportCode}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><select name="arrival" title="" required>
                                <c:forEach var="airport" items="${requestScope.airports}">
                                    <c:choose>
                                        <c:when test="${airport.equals(requestScope.flight.arrivalAirport)}">
                                            <c:set var="selected" value="selected"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:remove var="selected"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <option value="${airport.airportCode}" ${selected}>${airport.city} ${airport.airportCode}</option>
                                </c:forEach>
                            </select></td>

                            <td><input type="datetime-local" name="departureTime" title="" value="${requestScope.flight.departureTime}"
                                       required/></td>
                            <td><input type="datetime-local" name="arrivalTime" title="" value="${requestScope.flight.arrivalTime}"
                                       required/></td>

                            <td><select name="flightStatus" title="" required>
                                <c:forEach var="status" items="${requestScope.flightStatuses}">
                                    <c:choose>
                                        <c:when test="${requestScope.flight.flightStatus.equals(status)}">
                                            <c:set var="selected" value="selected"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:remove var="selected"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <option value="${status.name}" ${selected}>${status.name}</option>
                                </c:forEach>
                            </select></td>
                        </c:otherwise>
                    </c:choose>

                    <c:url var="deleteUrl" value="/flights/delete.html?language=${language}"/>
                    <%--<c:if test="${not flightCanBeDeleted}"><c:set var="disabled" value="disabled"/></c:if>--%>
                    <td ${hidden}>
                        <button formaction="${deleteUrl}" class="deleteButton" formmethod="post">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
            <br><br>
            <c:if test="${requestScope.flight.flightStatus == 'DONE' or requestScope.flight.flightStatus == 'BOARDING'
                       or requestScope.flight.flightStatus == 'DEPARTED' or requestScope.flight.flightStatus == 'LANDED'}">
                <c:set var="hiddenSubmit" value="hidden"/>
            </c:if>
            <fmt:message var="confirmation" key="button.confirm"/>
            <div align="center">
                <input class="agreeButton" type="submit" value="${confirmation}" ${hiddenSubmit}>
            </div>
            <br>
        </form>
        <c:url var="listUrl" value="/flights/list.html?language=${language}"/>
        <fmt:message var="back" key="button.back"/>
        <form action="${listUrl}" method="get">
            <div align="center">
                <input class="backButton" type="submit" value="${back}">
            </div>
        </form>
    </div>
    <script>

    </script>
</u:html>


