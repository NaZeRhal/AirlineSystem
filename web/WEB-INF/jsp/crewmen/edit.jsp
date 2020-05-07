<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language
       : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<c:if test="${empty requestScope.crewman.profession}">
    <fmt:message var="title" key="crewman.add.title"/>
    <c:set var="hidden" value="hidden"/>
</c:if>
<c:if test="${!empty requestScope.crewman.profession}">
    <fmt:message var="title" key="crewman.edit.title"/>
</c:if>

<u:html title="${title}">
    <h2 id="pageName" class="editH" align="center">${title}</h2>
    <c:if test="${not empty param.badMessage}">
        <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
    <c:url var="editUrl" value="/crewmen/edit.html?language=${language}"/>
    <form action="${editUrl}" method="post">
        <div class="tables">
            <input type="hidden" name="crewManId" value="${requestScope.crewman.id}"/>
            <table class="editStyle">
                <thead>
                <tr>
                    <th><fmt:message key="crewman.table.name"/></th>
                    <th><fmt:message key="crewman.table.surname"/></th>
                    <th><fmt:message key="crewman.table.dateOfBirth"/></th>
                    <th><fmt:message key="crewman.table.profession"/></th>
                    <th ${hidden}></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="text" name="firstName" title="" value="${requestScope.crewman.firstName}" required/></td>
                    <td><input type="text" name="lastName" title="" value="${requestScope.crewman.lastName}" required/></td>
                    <td><input type="date" name="dateOfBirth" title="" value="${requestScope.crewman.dateOfBirth}" required/></td>
                    <td><select name="professionId" title="" required>
                        <c:forEach var="profession" items="${requestScope.professions}">
                            <c:choose>
                                <c:when test="${requestScope.crewman.profession.equals(profession)}">
                                    <c:set var="selected" value="selected"/>
                                </c:when>
                                <c:otherwise>
                                    <c:remove var="selected"/>
                                </c:otherwise>
                            </c:choose>
                                    <option value="${profession.id}" ${selected}>${profession.name}</option>
                        </c:forEach>
                    </select></td>
                    <c:url var="deleteUrl" value="/crewmen/delete.html?language=${language}"/>
                    <td ${hidden}>
                        <button class="deleteButton" formaction="${deleteUrl}" title="" formmethod="post">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
            <br>
            <br>
            <fmt:message var="confirmation" key="button.confirm"/>
            <div align="center">
                <input class="agreeButton" type="submit" value="${confirmation}">
            </div>
            <br>

        </div>
    </form>
    <c:url var="listUrl" value="/crewmen/list.html?language=${language}"/>
    <fmt:message var="back" key="button.back"/>
    <form action="${listUrl}" method="get">
    <div align="center">
        <input class="backButton" type="submit" value="${back}">
    </div>
    </form>
</u:html>

