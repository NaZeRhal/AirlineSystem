<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language :
       not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<c:if test="${empty requestScope.user.id}">
    <fmt:message var="title" key="user.add.title"/>
    <c:set var="addHidden" value="hidden"/>
    <c:set var="action" value="add"/>
</c:if>
<c:if test="${!empty requestScope.user.id}">
    <fmt:message var="title" key="user.edit.title"/>
    <c:set var="editHidden" value="hidden"/>
    <c:set var="action" value="edit"/>
</c:if>

<u:html title="${title}">
    <h2 id="pageName" class="editH" align="center">${title}</h2>
    <c:if test="${not empty param.badMessage}">
        <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
    <div class="tables">
        <c:url var="editUrl" value="/users/edit.html?language=${language}&action=${action}"/>
        <form action="${editUrl}" method="post">
            <input type="hidden" name="userId" value="${requestScope.user.id}"/>
<%--            <input type="hidden" name="password" value="${requestScope.user.password}"/>--%>
            <table class="editStyle">
                <thead>
                <tr>
                    <th><fmt:message key="user.table.name"/></th>
                    <th><fmt:message key="user.table.surname"/></th>
                    <th><fmt:message key="user.table.login"/></th>
                    <th ${editHidden}><fmt:message key="user.table.password"/></th>
                    <th><fmt:message key="user.table.userType"/></th>
                    <th ${addHidden}></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="text" name="firstName" title="" value="${requestScope.user.firstName}" required/>
                    </td>
                    <td><input type="text" name="lastName" title="" value="${requestScope.user.lastName}" required/>
                    </td>
                    <td><input type="text" name="login" title="" value="${requestScope.user.login}" required/></td>
                    <td ${editHidden}><input type="text" name="password" title="" value="${requestScope.user.password}" required/>
                    </td>
                    <td><select name=userTypeId title="">
                        <c:forEach var="userType" items="${requestScope.userTypes}">
                            <c:choose>
                                <c:when test="${requestScope.user.userType.equals(userType)}">
                                    <c:set var="selected" value="selected"/>
                                </c:when>
                                <c:otherwise>
                                    <c:remove var="selected"/>
                                </c:otherwise>
                            </c:choose>
                            <option value="${userType.id}" ${selected}>${userType.name}</option>
                        </c:forEach>
                    </select></td>
                    <td ${addHidden}>
                        <c:url var="deleteUrl" value="/users/delete.html?language=${language}"/>
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
        </form>
    </div>
    <c:url var="listUrl" value="/users/list.html?language=${language}"/>
    <fmt:message var="back" key="button.back"/>
    <form action="${listUrl}" method="get">
        <div align="center">
            <input class="backButton" type="submit" value="${back}">
        </div>
    </form>
</u:html>

