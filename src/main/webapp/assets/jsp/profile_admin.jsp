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
            href="assets/css/main8.css"
            rel="stylesheet"
    />
    <title><fmt:message key="profile.main-title"/></title>
</head>
<body class="body-size3">
<div class="header-background">
    <header>
        <div class="header-title" display="inline">
            <fmt:message key="header.main-title"/>
        </div>
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
                    <input type="hidden" name="command" value="logout_person_command"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.log-out"/>
                    </button>
                </form>
            </li>
        </ul>
    </header>
</div>
<main class="main-orders">
    <section class="block4">
        <div class="block4-background">
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.email"/></div>
                <div class="block3-text2">${profile.email}</div>
            </div>
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.login"/></div>
                <div class="block3-text2">${profile.login}</div>
            </div>
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.name"/></div>
                <div class="block3-text2">${profile.name}</div>
            </div>
            <div class="block3-item">
                <div class="block3-text1"><fmt:message key="profile.surname"/></div>
                <div class="block3-text2">${profile.surname}</div>
            </div>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_email_page_command"/>
                <button class="block3-button1"><fmt:message key="profile.change.email"/></button>
            </form>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_password_page_command"/>
                <button class="block3-button2"><fmt:message key="profile.change.password"/></button>
            </form>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_name_page_command"/>
                <button class="block3-button3"><fmt:message key="profile.change.name"/></button>
            </form>
            <form method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="to_change_surname_page_command"/>
                <button class="block3-button4"><fmt:message key="profile.change.surname"/></button>
            </form>

            <div class="block4-title-admin">Возможности администратора</div>
            <div class="block4-container-admin">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_all_users_page_command"/>
                    <button class="block4-button-admin1" type="submit">Список всех пользователей</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_blocked_users_page_command"/>
                    <button class="block4-button-admin2" type="submit">Список заблокированных пользователей</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_active_users_page_command"/>
                    <button class="block4-button-admin3" type="submit">Список активных пользователей</button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_all_catalog_page_command"/>
                    <button class="block4-button-admin4" type="submit">Список всех тату</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_locked_catalog_page_command"/>
                    <button class="block4-button-admin5" type="submit">Список заблокированных тату</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_proposal_catalog_page_command"/>
                    <button class="block4-button-admin6" type="submit">Список предложенных тату</button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_orders_page_command"/>
                    <button class="block4-button-admin7">Список всех заказов</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_active_orders_page_command"/>
                    <button class="block4-button-admin8">Список активных заказов</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_completed_orders_page_command"/>
                    <button class="block4-button-admin9">Список выполненных заказов</button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_block_user_page_command"/>
                    <button class="block4-button-admin10">Заблокировать пользователя</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_unblock_user_page_command"/>
                    <button class="block4-button-admin11">Разблокировать пользователя</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_add_tattoo_page_command"/>
                    <button class="block4-button-admin12">Добавить тату</button>
                </form>

                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_block_tattoo_page_command"/>
                    <button class="block4-button-admin13">Заблокировать тату</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_unblock_tattoo_page_command"/>
                    <button class="block4-button-admin14">Разблокировать тату</button>
                </form>
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_approve_tattoo_page_command"/>
                    <input type="hidden" name="id" value="0"/>
                    <button class="block4-button-admin15">К одобрению пользовательского тату</button>
                </form>
            </div>
        </div>
    </section>
</main>
<footer class="footer">
    <div class="footer-background">
        <div class="footer-title"><fmt:message key="footer.main-title"/></div>
        <ul class="footer-info">
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_proposal_page_command"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.proposals"/></button>
                </form>
            </li>
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_orders_page_command"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.orders"/></button>
                </form>
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