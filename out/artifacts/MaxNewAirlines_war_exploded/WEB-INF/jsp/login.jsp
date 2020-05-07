<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language
       : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<c:url var="cssUrl" value="/css/logIn.css"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Max Airlines</title>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>

<c:url var="log" value="/img/log.png"/>
<div class="wrapper">
    <div class="content">
        <header>
            <div class="container">
                <div class="row">
                    <div class="logo"><img src="${log}" width="90px"></div>
                    <div class="slogan">Max AIRLINES<p>Your own Air Company</p></div>
                </div>
            </div>
            <div id="lang" class="langOption">
            <form>
                <select id="language" name="language" title="choose language" onchange="submit()">
                    <option value="ru" ${language == 'ru' ? 'selected' : ''} selected>Ru</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
                </select>
            </form>
        </div>
        </header>

        <c:if test="${not empty param.badMessage}">
            <p class="error"><fmt:message key="${param.badMessage}"/></p>
        </c:if>
        <c:url var="loginUrl" value="/login.html?language=${language}"/>
        <div class="authorization">
            <fieldset>

                <legend align="center"><fmt:message key="login.title"/></legend>
                <form action="${loginUrl}" method="post">
                    <table class="enter">
                        <tr>
                            <td><label for="login-id"><fmt:message key="login.form.login"/><sup>*</sup> </label></td>
                            <td>

                                <div id="input_container">
                                    <input id="login-id" type="text" name="login" required>
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td><label for="password-id"><fmt:message key="login.form.password"/><sup>*</sup></label>
                            </td>
                            <td><input id="password-id" type="password" name="password" required></td>
                        </tr>

                    </table>
                    <table class="enter">
                        <tr>
                            <td></td>
                            <fmt:message var="enter" key="login.button.login"/>
                            <td><input type="submit" value="${enter}"></td>
                        </tr>
                    </table>
                </form>
            </fieldset>
        </div>
    </div>
    <div class="footer">
        <div class="footer_text"> Made by Max&Kuma code ©</div>
    </div>
</div>
</body>
</html>