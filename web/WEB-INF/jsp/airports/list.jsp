<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language :
       not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<fmt:message var="title" key="airports.list.title"/>
<u:html title="${title}">
    <h2 id="pageName" class="listH" align="center"><fmt:message key="airports.list.title"/></h2>
    <c:if test="${not empty param.badMessage}">
        <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
        <c:url var="listFlightsUrl" value="/flights/list.html?language=${language}"/>
        <fmt:message var="flightList" key="airports.list.flightsList"/>
        <form action="${listFlightsUrl}" method="get">
            <input class="flightList" type="submit" name="airports" value="${flightList}">
        </form>

        <table class="listStyle" id="airportsList">
            <thead>
            <tr>
                <th><fmt:message key="airports.table.city"/></th>
                <th><fmt:message key="airports.table.code"/></th>
                <th><fmt:message key="button.edit"/></th>
            </tr>
            </thead>
            <jsp:useBean id="airportsList" scope="request" type="java.util.List"/>
            <c:forEach var="airport" items="${airportsList}">
                <tbody>
                <tr id="${airport.id}">
                    <td>${airport.city}</td>
                    <td>${airport.airportCode}</td>
                    <c:url var="editUrl" value="/airports/edit.html?language=${language}">
                        <c:param name="airportId" value="${airport.id}"/>
                    </c:url>
                    <td>
                        <a href="${editUrl}" type=""><i class="fa fa-pencil" aria-hidden="true"></i></a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
        <div class="downButtons">
            <a href="#" title="" class="topbutton">
                <i class="fa fa-arrow-circle-up" aria-hidden="true"></i></a>
            <form action="${editUrl}" method="get">
                <button class="addButton" type="submit" title="" value="">
                    <i class="fa fa-plus-square" aria-hidden="true"></i>
                </button>
            </form>
        </div>
</u:html>

