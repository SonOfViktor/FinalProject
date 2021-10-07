<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle scope="session" basename="language"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
            href="https://fonts.googleapis.com/css2?family=Bebas+Neue&amp;display=swap"
            rel="stylesheet"
    >
    <link
            href="https://fonts.googleapis.com/css2?family=Roboto"
            rel="stylesheet"
    />
    <link
            href="https://fonts.googleapis.com/css2?family=Open Sans"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/main16.css"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/register6.css"
            rel="stylesheet"
    />
    <title><fmt:message key="add.tattoo.main-title"/></title>
</head>
<body class="body-size1">
<div class="header-background">
    <header>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="to_home_page"/>
            <button class="header-title" type="submit">
                <fmt:message key="header.main-title"/>
            </button>
        </form>
        <ul>
            <li class="header-ref1">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_catalog_page"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.catalog"/>
                    </button>
                </form>
            </li>
            <li class="header-ref2">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_orders_page"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.orders"/>
                    </button>
                </form>
            </li>
            <li class="header-sign-in">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_profile_page"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.profile"/>
                    </button>
                </form>
            </li>
        </ul>
    </header>
</div>
<div class="container-login">
    <div class="register-login-info">
        <fmt:message key="login.message.info"/>
    </div>
    <form method="post" action="ProjectServlet">
        <input type="hidden" name="command" value="approve_tattoo"/>
        <c:choose>
            <c:when test="${id == 0}">
                <div class="info-div">
                    <input type="number" name="id" placeholder="<fmt:message key="change.tattoo-status.id"/>"
                           required
                           oninvalid="this.setCustomValidity('<fmt:message key="regex.id"/>')"
                           oninput="setCustomValidity('')"
                           min="1"
                           max="9223372036854775807"
                    >
                </div>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="id" value="${id}"/>
            </c:otherwise>
        </c:choose>
        <div class="info-div">
            <input type="number" name="price" placeholder="<fmt:message key="add.tattoo.price"/>"
                   required
                   oninvalid="this.setCustomValidity('<fmt:message key="regex.price"/>')"
                   oninput="setCustomValidity('')"
                   min="0"
                   max="10000000"
            >
        </div>
        <div class="info-div">
            <input type="number" name="discount" placeholder="<fmt:message key="add.tattoo.discount"/>"
                   required
                   oninvalid="this.setCustomValidity('<fmt:message key="regex.discount"/>')"
                   oninput="setCustomValidity('')"
                   min="0"
                   max="10"
            >
        </div>
        <input type="hidden" name="active" value="true"/>
        <c:choose>
            <c:when test="${id == 0}">
                <button class="submit" type="submit"><fmt:message key="approve.button"/></button>
            </c:when>
            <c:otherwise>
                <button class="login" type="submit"><fmt:message key="approve.button"/></button>
            </c:otherwise>
        </c:choose>
    </form>
    <c:if test="${id != 0}">
        <form class="register-form" method="post" action="ProjectServlet" width="290px">
            <input type="hidden" name="command" value="change_tattoo_status"/>
            <input type="hidden" name="active" value="false"/>
            <input type="hidden" name="id" value="${id}"/>
            <button class="register" type="submit"><fmt:message key="change.button-block"/></button>
        </form>
    </c:if>
</div>
<footer class="footer">
    <div class="footer-background">
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="to_home_page"/>
            <button class="footer-title" type="submit">
                <fmt:message key="footer.main-title"/>
            </button>
        </form>
        <ul class="footer-info">
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_home_page"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.home"/></button>
                </form>
            </li>
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_orders_page"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.orders"/></button>
                </form>
            </li>
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_about_us_page"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.about-us"/></button>
                </form>
            </li>
        </ul>
        <ul class="footer-info-refs">
            <li class="footer-info-refs-item">
                <a href="https://github.com/Daetwen/FinalProject">
                    <img class="footer-github-logo"
                         src="assets/image/github-logo.png"
                         alt="<fmt:message key="footer.alt.git"/>" >
                </a>
            </li>
            <li class="footer-info-refs-item">
                <a href="https://vk.com/vlad_makarey">
                    <img class="footer-vk-logo"
                         src="assets/image/vk-logo.svg"
                         alt="<fmt:message key="footer.alt.vk"/>">
                </a>
            </li>
            <li class="footer-info-refs-item">
                <a href="https://t.me/Daetwen">
                    <svg class="svg-icon5" width="26" height="23" viewBox="0 0 26 23" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M23.4299 0.755723L0.838079 9.93565C-0.0710467 10.3631 -0.378545 11.2193 0.618303 11.6839L6.41413 13.6246L20.4275 4.49886C21.1927 3.92594 21.976 4.07873 21.302 4.70893L9.26632 16.1918L8.88824 21.0513C9.23843 21.8016 9.87963 21.8051 10.2886 21.4322L13.6185 18.1122L19.3214 22.612C20.6459 23.4383 21.3666 22.905 21.6516 21.3906L25.3922 2.72707C25.7806 0.862859 25.1182 0.0414915 23.4299 0.755723Z" fill="#040303"/>
                    </svg>
                </a>
            </li>
        </ul>
    </div>
</footer>
</body>
</html>