<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language :
       not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<c:if test="${empty requestScope.airport.airportCode}">
    <fmt:message var="title" key="airports.add.title"/>
    <c:set var="hidden" value="hidden"/>
</c:if>
<c:if test="${!empty requestScope.airport.airportCode}">
    <fmt:message var="title" key="airports.edit.title"/>
</c:if>

<u:html title="${title}">
    <c:if test="${not empty param.badMessage}">
        <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
    <h2 id="pageName" class="editH" align="center">${title}</h2>
    <c:url var="edit" value="/airports/edit.html?language=${language}"/>
    <form action="${edit}" method="post">
        <div class="tables">
            <input type="hidden" name="airportId" value="${requestScope.airport.id}"/>
            <table class="editStyle" id="airportsList">
                <thead>
                <tr>
                    <th><fmt:message key="airports.table.city"/></th>
                    <th><fmt:message key="airports.table.code"/></th>
                    <th ${hidden}></th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td><input type="text" name="city" value="${requestScope.airport.city}" title=""></td>
                    <td><input type="text" name="airportCode" value="${requestScope.airport.airportCode}" title=""></td>
                    <td ${hidden}>
                        <c:url var="deleteUrl" value="/airports/delete.html?language=${language}"/>
                        <button id="deleteButton" formaction="${deleteUrl}" class="deleteButton"
                                formmethod="post">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
            <fmt:message var="confirmation" key="button.confirm"/>
            <div align="center">
                <input class="agreeButton" type="submit" value="${confirmation}">
            </div>
            <br>
        </div>
    </form>
    <c:url var="listUrl" value="/airports/list.html?language=${language}"/>
    <fmt:message var="back" key="button.back"/>
    <form action="${listUrl}" method="get">
        <div align="center">
            <input class="backButton" type="submit" value="${back}">
        </div>
    </form>
</u:html>

