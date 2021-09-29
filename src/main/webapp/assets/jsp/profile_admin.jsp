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
    <title><fmt:message key="profile.main-title"/></title>
</head>
<body class="body-size3">
<div class="header-background">
    <header>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="to_home_page"/>
            <button class="header-title" type="submit">
                <fmt:message key="header.main-title"/>
            </button>
        </form>
        <ul>
            <li class="header-ref0">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_home_page"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.home"/>
                    </button>
                </form>
            </li>
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
                    <input type="hidden" name="command" value="logout_person"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.log-out"/>
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
<main class="main-orders">
    <section class="block4">
        <div class="block4-background">
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.email"/></div>
                <div class="block3-text2">${sessionScope.user.email}</div>
            </div>
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.login"/></div>
                <div class="block3-text2">${sessionScope.user.login}</div>
            </div>
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.name"/></div>
                <div class="block3-text2">${sessionScope.user.name}</div>
            </div>
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.surname"/></div>
                <div class="block3-text2">${sessionScope.user.surname}</div>
            </div>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_email_page"/>
                <button class="block3-button1"><fmt:message key="profile.change.email"/></button>
            </form>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_password_page"/>
                <button class="block3-button2"><fmt:message key="profile.change.password"/></button>
            </form>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_name_page"/>
                <button class="block3-button3"><fmt:message key="profile.change.name"/></button>
            </form>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_surname_page"/>
                <button class="block3-button4"><fmt:message key="profile.change.surname"/></button>
            </form>

            <div class="block4-title-admin"><fmt:message key="profile.admin.title"/></div>
            <div class="block4-container-admin">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_all_users_page"/>
                    <button class="block4-button-admin1" type="submit">
                        <fmt:message key="profile.button.admin1.list.user.all"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_blocked_users_page"/>
                    <button class="block4-button-admin2" type="submit">
                        <fmt:message key="profile.button.admin2.list.user.blocked"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_active_users_page"/>
                    <button class="block4-button-admin3" type="submit">
                        <fmt:message key="profile.button.admin3.list.user.active"/>
                    </button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_all_catalog_page"/>
                    <button class="block4-button-admin4" type="submit">
                        <fmt:message key="profile.button.admin4.list.tattoo.all"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_locked_catalog_page"/>
                    <button class="block4-button-admin5" type="submit">
                        <fmt:message key="profile.button.admin5.list.tattoo.blocked"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_proposal_catalog_page"/>
                    <button class="block4-button-admin6" type="submit">
                        <fmt:message key="profile.button.admin6.list.tattoo.suggested"/>
                    </button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_orders_page"/>
                    <button class="block4-button-admin7">
                        <fmt:message key="profile.button.admin7.list.order.all"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_active_orders_page"/>
                    <button class="block4-button-admin8">
                        <fmt:message key="profile.button.admin8.list.order.active"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_completed_orders_page"/>
                    <button class="block4-button-admin9">
                        <fmt:message key="profile.button.admin9.list.order.completed"/>
                    </button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_block_user_page"/>
                    <button class="block4-button-admin10">
                        <fmt:message key="profile.button.admin10.user.block"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_unblock_user_page"/>
                    <button class="block4-button-admin11">
                        <fmt:message key="profile.button.admin11.user.unblock"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_add_tattoo_page"/>
                    <button class="block4-button-admin12">
                        <fmt:message key="profile.button.admin12.tattoo.add"/>
                    </button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_block_tattoo_page"/>
                    <button class="block4-button-admin13">
                        <fmt:message key="profile.button.admin13.tattoo.block"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_unblock_tattoo_page"/>
                    <button class="block4-button-admin14">
                        <fmt:message key="profile.button.admin14.tattoo.unblock"/>
                    </button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_approve_tattoo_page"/>
                    <input type="hidden" name="id" value="0"/>
                    <button class="block4-button-admin15">
                        <fmt:message key="profile.button.admin15.tattoo.proposal"/>
                    </button>
                </form>
            </div>
        </div>
    </section>
</main>
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