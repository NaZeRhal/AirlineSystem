<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <c:url var="mainCssUrl" value="/css/main.css"/>
    <c:url var="fontIconURL" value="/font-awesome/css/font-awesome.min.css"/>
    <link href="${mainCssUrl}" rel="stylesheet">
    <link href="${fontIconURL}" rel="stylesheet">
</head>

<body>
<div class="wrapper">
    <div class="content" id="wrapper">
        <header>
            <div class="container">

                <div class="row">
                    <c:url var="log" value="/img/log.png"/>
                    <div class="logo"><img src="${log}" width="90px"></div>
                    <div class="slogan">Max AIRLINES<p>Your own Air Company</p></div>
                </div>

                <div class="nav_text">
                    <div class="hello"><fmt:message key="header.hello"/>, ${sessionScope.get("currentUser").firstName}!

                        <div class="formLang">
                            <c:url var="logout" value="/logout.html"/>
                            <form action="${logout}">
                                <button type="submit" name="logout" class="logout" value="">
                                    <i class="fa fa-power-off" aria-hidden="true"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <jsp:doBody/>
    </div>
    <div class="footer">
        <div class="footer_text"> Made by Max&Kuma code ©</div>
    </div>
</div>
</body>
</html>