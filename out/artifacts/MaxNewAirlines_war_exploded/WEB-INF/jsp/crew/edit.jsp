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
<fmt:message var="title" key="crew.add.title"/>

<u:html title="${title}">
    <h2 id="pageName" class="listH" align="center">
        <c:if test="${not empty param.badMessage}">
            <p class="error"><fmt:message key="${param.badMessage}"/></p>
        </c:if>
        <c:if test="${not empty param.goodMessage}">
            <p class="done"><fmt:message key="${param.goodMessage}"/></p>
        </c:if>
        <fmt:message key="crew.add.title"/> ${requestScope.flight.flightCode}
        <c:choose>
            <c:when test="${requestScope.flight.departureTime.dayOfYear == requestScope.flight.arrivalTime.dayOfYear}">
                ${requestScope.flight.departureTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
            </c:when>
            <c:otherwise>
                ${requestScope.flight.departureTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
                - ${requestScope.flight.arrivalTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}
            </c:otherwise>
        </c:choose>
    </h2>

    <div id="buttonBoxCrewMan" class="buttonBoxCrewMan">
        <fmt:message var="fullList" key="crewman.list.fullList"/>
        <fmt:message var="pilots" key="crewman.list.pilots"/>
        <fmt:message var="navigators" key="crewman.list.navigators"/>
        <fmt:message var="radiomen" key="crewman.list.radiomen"/>
        <fmt:message var="stewards" key="crewman.list.stewards"/>
        <fmt:message var="flightList" key="crewman.list.flightsList"/>
        <c:url var="listCrewUrl" value="/crews/add.html?language=${language}&flightId=${requestScope.flight.id}"/>
        <form action="${listCrewUrl}" method="post">
            <input id="fullList" type="submit" name="fullList" value="${fullList}">
        </form>
        <input id="pilot" type="submit" name="pilots" value="${pilots}">
        <input id="navigator" type="submit" name="navigators" value="${navigators}">
        <input id="radioman" type="submit" name="radiomen" value="${radiomen}">
        <input id="steward" type="submit" name="stewards" value="${stewards}">
    </div>
    <div id="flightVsCrewMan">
        <div id="list1">

            <table class="crewManListTable" id="crewManList">
                <thead>
                <tr>
                    <th><fmt:message key="crew.table.name"/></th>
                    <th><fmt:message key="crew.table.surname"/></th>
                    <th><fmt:message key="crew.table.dateOfBirth"/></th>
                    <th><fmt:message key="crew.table.profession"/></th>
                    <th><fmt:message key="button.add"/></th>
                </tr>
                </thead>
                <tbody id="crewManListBody">
                <c:forEach var="freeCrewMan" items="${requestScope.freeCrewManList}">
                    <tr id="${freeCrewMan.id}">
                        <td>${freeCrewMan.firstName}</td>
                        <td>${freeCrewMan.lastName}</td>
                        <td>${freeCrewMan.dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
                        <td>
                                ${freeCrewMan.profession.name}
                        </td>
                        <c:url var="editUrl" value="/crew/edit.html?language=${language}">
                            <c:param name="crewManId" value="${freeCrewMan.id}"/>
                            <c:param name="flightId" value="${requestScope.flight.id}"/>
                        </c:url>
                        <td>
                            <a href="${editUrl}" type="button">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="list2">
            <table class="flightVsCrewTable" id="flightVsCrewTable">
                <thead>
                <tr>
                    <th colspan="4">
                            ${requestScope.flight.departureAirport.city} ${requestScope.flight.departureAirport.airportCode}
                        - ${requestScope.flight.arrivalAirport.city} ${requestScope.flight.arrivalAirport.airportCode}
                            ${requestScope.flight.departureTime.format(DateTimeFormatter.ofPattern("HH:mm"))}
                        - ${requestScope.flight.arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"))}
                    </th>
                    <th>
                            ${requestScope.flight.flightStatus}
                    </th>
                </tr>
                </thead>
                <tbody id="crewManVsFlightBody">
                <tr>
                    <td><fmt:message key="crew.table.name"/></td>
                    <td><fmt:message key="crew.table.surname"/></td>
                    <td><fmt:message key="crew.table.dateOfBirth"/></td>
                    <td><fmt:message key="crew.table.profession"/></td>
                    <td><fmt:message key="button.removeCrew"/></td>
                </tr>
                <c:forEach var="onBoardCrewMan" items="${requestScope.onBoardCrewManList}">
                    <tr id="${onBoardCrewMan.id}">
                        <td>${onBoardCrewMan.firstName}</td>
                        <td>${onBoardCrewMan.lastName}</td>
                        <td>${onBoardCrewMan.dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
                        <td>
                                ${onBoardCrewMan.profession.name}
                        </td>
                        <c:url var="deleteUrl" value="/crew/delete.html?language=${language}">
                            <c:param name="crewManId" value="${onBoardCrewMan.id}"/>
                            <c:param name="flightId" value="${requestScope.flight.id}"/>
                        </c:url>
                        <td>
                            <a href="${deleteUrl}" type="button">
                                <i class="fa fa-minus-circle" aria-hidden="true"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="AgreeBack" align="center">
            <c:url var="listUrl" value="/flights/list.html">
                <c:param name="flightId" value="${requestScope.flight.id}"/>
            </c:url>
            <fmt:message var="confirmation" key="button.confirm"/>
            <form action="${listUrl}" method="post">
                <input id="addCrewButton" class="agreeButton" type="submit" value="${confirmation}">
            </form>
            <fmt:message var="back" key="button.back"/>
            <form action="${listUrl}" method="get">
                <div align="center">
                    <input class="backButton" type="submit" value="${back}">
                </div>
            </form>
        </div>
    </div>
    <script>
        window.onload = function () {
            let input, filter, profession, table, tr, td, i;

            let arr = document.getElementById("buttonBoxCrewMan").children;

            for (let elem of arr) {
                if (!!elem.id) {
                    elem.onclick = function () {
                        input = document.getElementById(elem.id);
                        filter = input.value;
                        profession = filter.substring(0, 4);
                        table = document.getElementById("crewManList");
                        tr = table.getElementsByTagName("tr");
                        for (i = 0; i < tr.length; i++) {
                            td = tr[i].getElementsByTagName("td")[3];
                            if (td) {
                                if (td.innerHTML.toUpperCase().indexOf(profession.toUpperCase()) > -1) {
                                    tr[i].style.display = "";
                                } else {
                                    tr[i].style.display = "none";
                                }
                            }
                        }
                    }
                }
            }
        };

        let button, table, tr, td, messageTr, messageTd;
        let pilot = 0;
        table = document.getElementById('crewManVsFlightBody');
        tr = table.getElementsByTagName('tr');
        button = document.getElementById('addCrewButton');
        for (let i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName('td')[3];
            if (td.innerHTML.indexOf('Пилот') > -1 || td.innerHTML.indexOf('PILOT') > -1) {
                pilot++;
            }
        }
        if (tr.length < 6) {
            button.disabled = true;
            button.style.backgroundColor = 'grey';
        } else if (tr.length >= 6) {
            if (pilot === 0) {
                button.disabled = true;
                button.style.backgroundColor = 'grey';
                messageTr = document.createElement('tr');
                messageTd = document.createElement('td');
                messageTd.colSpan = 5;
                messageTd.style.backgroundColor = 'rgba(214, 83, 83, 0.91)';
                messageTd.style.color = 'white';
                messageTd.innerHTML = '<fmt:message key="crew.table.pilot.notEnough"/>';
                messageTr.appendChild(messageTd);
                table.appendChild(messageTr);
            }
        }
    </script>
</u:html>