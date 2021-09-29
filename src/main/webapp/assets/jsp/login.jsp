<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="login.main-title"/></title>
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
                    <input type="hidden" name="command" value="to_home_page"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.home"/>
                    </button>
                </form>
            </li>
        </ul>

        <c:set var="localeRu">ru</c:set>
        <c:set var="localeEn">en</c:set>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_language"/>
            <button class="language-button" type="submit">
                <c:if test="${language == localeRu}">
                    <fmt:message key="header.language.ru"/>
                </c:if>
                <c:if test="${language == localeEn}">
                    <fmt:message key="header.language.en"/>
                </c:if>
            </button>
        </form>

    </header>
</div>
<div class="container-login">
    <div class="register-login-info">
        <fmt:message key="login.message.info"/>
    </div>
    <form class="login-form" method="post" action="ProjectServlet" width="290px">
        <input type="hidden" name="command" value="login_person"/>
    <div class="info-div2">
        <input type="text"
               name="login"
               placeholder="<fmt:message key="login.input.login"/>"
               required
               oninvalid="this.setCustomValidity('<fmt:message key="regex.login"/>')"
               oninput="setCustomValidity('')"
               minlength="1"
               maxlength="40"
               pattern="[\w][\w._-]{0,39}"
        />
    </div>
    <div class="info-div2">
        <input type="password"
               autocomplete="current-password"
               name="password"
               placeholder="<fmt:message key="login.input.password"/>"
               required
               oninvalid="this.setCustomValidity('<fmt:message key="regex.password"/>')"
               oninput="setCustomValidity('')"
               minlength="8"
               maxlength="45"
               pattern="[-\w_!@#$%^&*()]{8,45}"
        />
    </div>
        <button class="login"><fmt:message key="login.button.log-in"/></button>
    </form>
    <form class="register-form" method="post" action="ProjectServlet" width="290px">
        <input type="hidden" name="command" value="to_register_page"/>
        <button class="register"><fmt:message key="login.button.register"/></button>
    </form>
    <c:if test = "${login_error}">
        <div class="login-error-message">
            <fmt:message key="login.message.error"/>
        </div>
    </c:if>
    <c:if test = "${user_blocked_error}">
        <div class="login-error-message">
            <fmt:message key="login.user-blocked.message.error"/>
        </div>
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
                    <input type="hidden" name="command" value="to_proposal_page"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.proposals"/></button>
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