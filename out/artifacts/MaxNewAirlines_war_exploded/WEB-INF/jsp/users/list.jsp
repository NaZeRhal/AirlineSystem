<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language
       : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<fmt:message var="title" key="user.list.title"/>
<u:html title="${title}">
    <h2 id="pageName" class="listH" align="center">${title}</h2>
    <c:if test="${not empty param.badMessage}">
      <p class="error"><fmt:message key="${param.badMessage}"/></p>
    </c:if>
    <c:if test="${not empty param.goodMessage}">
        <p class="done"><fmt:message key="${param.goodMessage}"/></p>
    </c:if>
    <div class="tables">
        <div align="center" class="navinput">
            <fmt:message var="search" key="user.list.search"/>
            <input class="searchIn" type="text" id="searchInput" onkeyup="myFunction()"
                   placeholder="${search}" title="">
        </div>
        <table class="listStyle" id="usersList">
            <thead>
            <tr>
                <th><fmt:message key="user.table.name"/></th>
                <th><fmt:message key="user.table.surname"/></th>
                <th><fmt:message key="user.table.login"/></th>
                <th><fmt:message key="user.table.password"/></th>
                <th><fmt:message key="user.table.userType"/></th>
                <th><fmt:message key="button.edit"/></th>
            </tr>
            </thead>
            <c:forEach var="user" items="${requestScope.users}">
                <tbody>
                <tr id="${user.id}">
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.userType.name}</td>
                    <c:url var="editUrl" value="/users/edit.html?language=${language}">
                        <c:param name="userId" value="${user.id}"/>
                    </c:url>
                    <td>
                        <a href="${editUrl}" type=""><i class="fa fa-pencil" aria-hidden="true"></i></a>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <div class="downButtons">
            <%--<c:url var="edit" value="/users/edit.html?language=${language}"/>--%>
            <a href="#" title="" class="topbutton"><i class="fa fa-arrow-circle-up" aria-hidden="true"></i></a>
            <form action="${editUrl}" method="get">
                <fmt:message var="add" key="button.add"/>
                <button class="addButton" type="submit" title="" value="${add}">
                    <i class="fa fa-plus-square" aria-hidden="true"></i>
                </button>
            </form>
        </div>

        <script>
            function myFunction() {
                let input, filter, table, tr, td, i;

                input = document.getElementById("searchInput");
                filter = input.value;
                table = document.getElementById("usersList");
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
    </div>
</u:html>

