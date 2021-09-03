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
            href="assets/css/main8.css"
            rel="stylesheet"
    />
    <link
            href="assets/css/register4.css"
            rel="stylesheet"
    />
    <title><fmt:message key="create.order.main-title"/></title>
</head>
<body class="body-size1">
<div class="header-background">
    <header>
        <div class="header-title" display="inline">
            <fmt:message key="header.main-title"/>
        </div>
        <ul>
            <li class="header-ref1">
                <a href="ProjectServlet?command=to_catalog_page_command">
                    <fmt:message key="header.catalog"/>
                </a>
            </li>
            <li class="header-ref2">
                <a href="ProjectServlet?command=to_orders_page_command">
                    <fmt:message key="header.orders"/>
                </a>
            </li>
            <li class="header-sign-in">
                <a href="ProjectServlet?command=to_login_page_command">
                    <fmt:message key="header.profile"/>
                </a>
            </li>
        </ul>
    </header>
</div>
<div class="container-login">
    <div class="register-login-info">
        <fmt:message key="login.message.info"/>
    </div>
    <form method="post" action="ProjectServlet">
        <input type="hidden" name="command" value="create_order_command"/>
        <div class="info-div">
            <input type="number"
                   name="tattoo_id"
                   placeholder="<fmt:message key="create.order.tattoo.id"/>"
                   required
                   min="1"
                   max="9223372036854775807"
            />
        </div>
        <div class="info-div">
            <input id="date" type="hidden" name="registration_date"/>
        </div>
        <button type="submit"
                class="submit"
                onclick="timeNow(date)">
            <fmt:message key="create.order.button"/>
        </button>
    </form>
    <c:if test = "${balance_not_enough_error_message}">
        <div class="change-error-message">
            На вашем балансе не достаточно средст для оплаты выбранной тату. пожалуйста, пополните баланс и повторите операцию.
        </div>
    </c:if>
    <c:if test = "${tattoo_id_not_found_message}">
        <div class="change-error-message">
            Тату с введённым вами id не найдено, пожалйста, повторите ввод id.
        </div>
    </c:if>
</div>
<footer class="footer">
    <div class="footer-background">
        <div class="footer-title"><fmt:message key="footer.main-title"/></div>
        <ul class="footer-info">
            <li class="footer-info-item">
                <c:choose>
                    <c:when test="${authentication == true}">
                        <form method="post" action="ProjectServlet">
                            <input type="hidden" name="command" value="to_proposal_page_command"/>
                            <button class="footer-button" type="submit"><fmt:message key="footer.proposals"/></button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form method="post" action="ProjectServlet">
                            <input type="hidden" name="command" value="to_login_page_command"/>
                            <button class="footer-button" type="submit"><fmt:message key="footer.proposals"/></button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="footer-info-item">
                <c:choose>
                    <c:when test="${authentication == true}">
                        <form method="post" action="ProjectServlet">
                            <input type="hidden" name="command" value="to_orders_page_command"/>
                            <button class="footer-button" type="submit"><fmt:message key="footer.orders"/></button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form method="post" action="ProjectServlet">
                            <input type="hidden" name="command" value="to_login_page_command"/>
                            <button class="footer-button" type="submit"><fmt:message key="footer.orders"/></button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_about_us_page_command"/>
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
<script src="assets/js/time2.js"></script>
</body>
</html>
