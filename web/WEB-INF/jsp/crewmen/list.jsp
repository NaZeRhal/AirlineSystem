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
<fmt:message var="title" key="crewman.list.title"/>

<u:html title="${title}">
    <h2 id="pageName" class="listH" align="center">${title}</h2>
    <c:if test="${not empty param.badMessage}">
        <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
    <div class="tables">
        <div id="buttonsList" class="navinput" align="center">
            <div id="buttonBoxCrewMan" class="buttonBoxCrewMan">
                <fmt:message var="fullList" key="crewman.list.fullList"/>
                <fmt:message var="pilots" key="crewman.list.pilots"/>
                <fmt:message var="navigators" key="crewman.list.navigators"/>
                <fmt:message var="radiomen" key="crewman.list.radiomen"/>
                <fmt:message var="stewards" key="crewman.list.stewards"/>
                <fmt:message var="flightList" key="crewman.list.flightsList"/>
                <c:url var="listCrewManUrl" value="/crewmen/list.html?language=${language}"/>
                <form action="${listCrewManUrl}" method="get">
                    <input id="fullList" type="submit" name="fullList" value="${fullList}">
                </form>
                <input id="pilot" type="submit" name="pilots" value="${pilots}">
                <input id="navigator" type="submit" name="navigators" value="${navigators}">
                <input id="radioman" type="submit" name="radiomen" value="${radiomen}">
                <input id="steward" type="submit" name="stewards" value="${stewards}">
                <c:url var="listFlightsUrl" value="/flights/list.html?language=${language}"/>
                <form action="${listFlightsUrl}" method="get">
                    <input type="submit" name="flightFullList" value="${flightList}"/>
                </form>
            </div>
            <fmt:message var="search" key="crewman.list.search"/>
            <input class="searchIn" type="text" id="searchInput" onkeyup="myFunction()"
                   placeholder="${search}" title="">
        </div>
        <table class="listStyle" id="crewManList">
            <thead>
            <tr>
                <th><fmt:message key="crewman.table.name"/></th>
                <th><fmt:message key="crewman.table.surname"/></th>
                <th><fmt:message key="crewman.table.dateOfBirth"/></th>
                <th><fmt:message key="crewman.table.profession"/></th>
                <th><fmt:message key="button.edit"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="freeCrewMan" items="${requestScope.crewManList}">
                <tr id="${freeCrewMan.id}">
                    <td>${freeCrewMan.firstName}</td>
                    <td>${freeCrewMan.lastName}</td>
                    <td>${freeCrewMan.dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}</td>
                    <td>${freeCrewMan.profession.name}</td>
                    <c:url var="editUrl" value="/crewmen/edit.html?language=${language}">
                        <c:param name="crewManId" value="${freeCrewMan.id}"/>
                    </c:url>
                    <td>
                        <a href="${editUrl}" type=""><i class="fa fa-pencil" aria-hidden="true"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="downButtons">
            <a href="#" title="" class="topbutton">
                <i class="fa fa-arrow-circle-up" aria-hidden="true"></i></a>
            <%--<c:url var="addUrl" value="/crewmen/add.html?language=${language}"/>--%>
            <form action="${editUrl}" method="get">
                <button class="addButton" type="submit" title="" value="">
                    <i class="fa fa-plus-square" aria-hidden="true"></i>
                </button>
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

        function myFunction() {
            let input, filter, table, tr, td, i;

            input = document.getElementById("searchInput");
            filter = input.value;
            table = document.getElementById("crewManList");
            tr = table.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1];
                if (td) {
                    if (td.innerHTML.indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</u:html>