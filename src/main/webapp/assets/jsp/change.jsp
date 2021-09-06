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
            href="assets/css/main11.css"
            rel="stylesheet"
    />
    <link
            href="assets/css/register5.css"
            rel="stylesheet"
    />
    <title><fmt:message key="change.title"/></title>
</head>
<body class="body-size1">
<div class="header-background">
    <header>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="to_home_page_command"/>
            <button class="header-title" type="submit">
                <fmt:message key="header.main-title"/>
            </button>
        </form>
        <ul>
            <li class="header-ref0">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_home_page_command"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.home"/>
                    </button>
                </form>
            </li>
            <li class="header-ref1">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_catalog_page_command"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.catalog"/>
                    </button>
                </form>
            </li>
            <li class="header-ref2">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_orders_page_command"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.orders"/>
                    </button>
                </form>
            </li>
            <li class="header-sign-in">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_profile_page_command"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.profile"/>
                    </button>
                </form>
            </li>
        </ul>

        <c:set var="localeRu">ru</c:set>
        <c:set var="localeEn">en</c:set>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_language_command"/>
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
    <c:set var="localEmail">email</c:set>
    <c:set var="localName">name</c:set>
    <c:set var="localSurname">surname</c:set>
    <c:set var="localPassword">password</c:set>
    <c:set var="localBalance">balance</c:set>
    <c:set var="localRole">role</c:set>
    <c:set var="localUserStatus">status</c:set>
    <c:set var="localTattooStatus">tattoo_status</c:set>
    <c:set var="localOrdersStatus">order_status</c:set>
    <c:if test="${what_change == localEmail}">
        <div class="register-login-info">
            <fmt:message key="login.message.info"/>
        </div>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_email_command"/>
            <div class="info-div">
                <input type="email" name="email" placeholder="<fmt:message key="change.email"/>"
                       required
                       minlength="2"
                       maxlength="50"
                       pattern="^[A-z0-9._-]+@[a-z0-9._-]+\.[a-z]{2,4}$"
                />
            </div>
            <button type="submit"
                    class="submit">
                <fmt:message key="change.button"/>
            </button>
        </form>
    </c:if>
    <c:if test="${what_change == localName}">
        <div class="register-login-info">
            <fmt:message key="login.message.info"/>
        </div>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_name_command"/>
            <div class="info-div">
                <input type="info-field" name="name" placeholder="<fmt:message key="change.name"/>"
                       required
                       minlength="1"
                       maxlength="40"
                       pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}"
                />
            </div>
            <button type="submit"
                    class="submit">
                <fmt:message key="change.button"/>
            </button>
        </form>
    </c:if>
    <c:if test="${what_change == localSurname}">
        <div class="register-login-info">
            <fmt:message key="login.message.info"/>
        </div>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_surname_command"/>
            <div class="info-div">
                <input type="info-field" name="surname" placeholder="<fmt:message key="change.surname"/>"
                       required
                       minlength="1"
                       maxlength="40"
                       pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}"
                />
            </div>
            <button type="submit"
                    class="submit">
                <fmt:message key="change.button"/>
            </button>
        </form>
    </c:if>
    <c:if test="${what_change == localPassword}">
        <div class="register-login-info">
            <fmt:message key="login.message.info"/>
        </div>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_password_command"/>
            <div class="info-div">
                <input type="password"
                       autocomplete="current-password"
                       name="password_old"
                       placeholder="<fmt:message key="change.password-old"/>"
                       required
                       minlength="8"
                       maxlength="45"
                       pattern="[-\w_!@#$%^&*()]{8,45}"
                />
            </div>
            <div class="info-div">
                <input type="password"
                       autocomplete="current-password"
                       name="password_new"
                       placeholder="<fmt:message key="change.password-new"/>"
                       required
                       minlength="8"
                       maxlength="45"
                       pattern="[-\w_!@#$%^&*()]{8,45}"
                >
            </div>
            <button type="submit"
                    class="submit">
                <fmt:message key="change.button"/>
            </button>
            <c:if test = "${change_password_error}">
                <div class="change-error-message">
                    <fmt:message key="change.password.message.error"/>
                </div>
            </c:if>
        </form>
    </c:if>
    <c:if test="${what_change == localBalance}">
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_balance_command"/>
            <div class="info-div">
                <input type="number"
                       name="balance"
                       placeholder="<fmt:message key="profile.change.balance"/>"
                       required
                       min="0"
                       max="9223372036854775807"
                />
            </div>
            <button type="submit"
                    class="submit">
                <fmt:message key="change.button"/>
            </button>
        </form>
    </c:if>
    <c:if test="${what_change == localUserStatus && active == false}">
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_user_status_command"/>
            <div class="info-div">
                <input type="number" name="id" placeholder="<fmt:message key="change.user-status.id"/>"
                       required
                       min="1"
                       max="9223372036854775807"
                />
            </div>
                <input type="hidden" name="active" value="false"/>
                <button class="submit" type="submit"><fmt:message key="change.button-block"/></button>
        </form>
    </c:if>
    <c:if test="${what_change == localUserStatus && active == true}">
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_user_status_command"/>
            <div class="info-div">
                <input type="number" name="id" placeholder="<fmt:message key="change.user-status.id"/>"
                       required
                       min="1"
                       max="9223372036854775807"
                >
            </div>
            <input type="hidden" name="active" value="true"/>
            <button class="submit" type="submit"><fmt:message key="change.button-unblock"/></button>
        </form>
    </c:if>
    <c:if test="${what_change == localTattooStatus && active == false}">
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_tattoo_status_command"/>
            <div class="info-div">
                <input type="number" name="tattoo_status" placeholder="<fmt:message key="change.tattoo-status.id"/>"
                       required
                       min="1"
                       max="9223372036854775807"
                >
            </div>
            <input type="hidden" name="active" value="false"/>
            <button class="submit" type="submit"><fmt:message key="change.button-block"/></button>
        </form>
    </c:if>
    <c:if test="${what_change == localTattooStatus && active == true}">
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="change_tattoo_status_command"/>
            <div class="info-div">
                <input type="number" name="id" placeholder="<fmt:message key="change.tattoo-status.id"/>"
                       required
                       minlength="1"
                       maxlength="9223372036854775807"
                >
            </div>
            <input type="hidden" name="active" value="true"/>
            <button class="submit" type="submit"><fmt:message key="change.button-unblock"/></button>
        </form>
    </c:if>
</div>

<footer class="footer">
    <div class="footer-background">
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="to_home_page_command"/>
            <button class="footer-title" type="submit">
                <fmt:message key="footer.main-title"/>
            </button>
        </form>
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
</body>
</html>
