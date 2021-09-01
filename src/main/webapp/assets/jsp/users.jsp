<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle scope="session" basename="language"/>

<c:set var="status_active">ACTIVE</c:set>
<c:set var="status_blocked">BLOCKED</c:set>
<c:set var="role_admin">ADMIN</c:set>
<html>
<head>
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
    <link
            href="assets/css/users3.css"
            rel="stylesheet"
    />
    <link
            href="assets/css/slider2.css"
            rel="stylesheet"
    />
    <title><fmt:message key="users.main-title"/></title>
</head>
<body>
<script src="assets/js/slider1.js">
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var slider = new SimpleAdaptiveSlider('.slider1');
        document.querySelector('.btn-prev').onclick = function () {
            slider.prev();
        }
        document.querySelector('.btn-next').onclick = function () {
            slider.next();
        }
    });
</script>
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
                    <input type="hidden" name="command" value="to_profile_page_command"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.profile"/>
                    </button>
                </form>
            </li>
        </ul>
    </header>
</div>
<main class="main-catalog">
    <section class="block2">
        <div class="block2-background">
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_user_by_id_page_command"/>
                    <div class="block2-text"><fmt:message key="users.search.title.id"/></div>
                    <input class="block2-input"
                           type="number"
                           name="id"
                           placeholder="<fmt:message key="users.search.placeholder.id"/>"
                           min="1"
                           max="9223372036854775807"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="users.search.button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_user_by_login_page_command"/>
                    <div class="block2-text"><fmt:message key="users.search.title.login"/></div>
                    <input class="block2-input"
                           type="text"
                           name="login"
                           placeholder="<fmt:message key="users.search.placeholder.login"/>"
                           minlength="1"
                           maxlength="40"
                           pattern="[\w][\w._-]{0,39}"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="users.search.button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_users_by_name_page_command"/>
                    <div class="block2-text"><fmt:message key="users.search.title.name"/></div>
                    <input class="block2-input"
                           type="text"
                           name="name"
                           placeholder="<fmt:message key="users.search.placeholder.name"/>"
                           minlength="1"
                           maxlength="40"
                           pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="users.search.button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_users_by_surname_page_command"/>
                    <div class="block2-text"><fmt:message key="users.search.title.surname"/></div>
                    <input class="block2-input"
                           type="text"
                           name="surname"
                           placeholder="<fmt:message key="users.search.placeholder.surname"/>"
                           minlength="1"
                           maxlength="40"
                           pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="users.search.button"/></button>
                </form>
            </div>
        </div>
    </section>

    <div class="catalog-background">
        <section class="catalog">
            <c:set var="all">all</c:set>
            <c:set var="blocked">blocked</c:set>
            <c:set var="active">active</c:set>
            <c:set var="founded">founded</c:set>
            <c:if test="${title_users == all}">
                <div class="user-list-title">
                    <fmt:message key="users.list.all"/>
                </div>
            </c:if>
            <c:if test="${title_users == blocked}">
                <div class="user-list-title">
                    <fmt:message key="users.list.blocked"/>
                </div>
            </c:if>
            <c:if test="${title_users == active}">
                <div class="user-list-title">
                    <fmt:message key="users.list.active"/>
                </div>
            </c:if>
            <c:if test="${title_users == founded}">
                <div class="user-list-title">
                    <fmt:message key="users.list.found"/>
                </div>
            </c:if>
            <c:if test="${number_of_users == 0}">
                <div class="find-empty-text">
                    <fmt:message key="users.message.empty"/>
                </div>
            </c:if>
            <div class="slider1">
                <div class="slider__wrapper">
                    <div class="slider__items">
                        <c:forEach var="item" begin="1" end="${pages_number}" varStatus="loop">
                            <c:choose>
                                <c:when test="${item != pages_number}">
                                    <div class="slider__item">
                                        <div class="user-list-container">
                                            <c:forEach var="user" items = "${users}" begin="${(item - 1) * elements_per_page}" end="${item * elements_per_page - 1}">
                                                    <div class="user-list-item">
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.id"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.userId}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.email"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.email}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.login"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.login}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.name"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.name}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.surname"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.surname}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.discount"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.discount}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.balance"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.balance}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.registration-date"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.registrationDate}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.role"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.role}
                                                            </div>
                                                        </div>
                                                        <div class="user-list-text-item">
                                                            <div class="user-list-text1">
                                                                <fmt:message key="users.user.status"/>
                                                            </div>
                                                            <div class="user-list-text2">
                                                                    ${user.status}
                                                            </div>
                                                        </div>
                                                        <form method="post" action="ProjectServlet">
                                                            <input type="hidden" name="command" value="change_user_status_command"/>
                                                            <input type="hidden" name="id" value="${user.userId}"/>
                                                            <input type="hidden" name="active" value="false"/>
                                                            <input type="hidden" name="title_users" value="${title_users}"/>
                                                            <c:if test="${user.status == status_active && user.role != role_admin}">
                                                                <button class="user-list-item-button" type="submit"><fmt:message key="change.button-block"/></button>
                                                            </c:if>
                                                            <c:if test="${user.status == status_blocked || user.role == role_admin}">
                                                                <button disabled="disabled" class="user-list-item-button" type="submit"><fmt:message key="change.button-block"/></button>
                                                            </c:if>
                                                        </form>
                                                        <form class="form-button-user2" method="post" action="ProjectServlet">
                                                            <input type="hidden" name="command" value="change_user_status_command"/>
                                                            <input type="hidden" name="id" value="${user.userId}"/>
                                                            <input type="hidden" name="active" value="true"/>
                                                            <input type="hidden" name="title_users" value="${title_users}"/>
                                                            <c:if test="${user.status == status_active || user.role == role_admin}">
                                                                <button disabled="disabled" class="user-list-item-button" type="submit"><fmt:message key="change.button-unblock"/></button>
                                                            </c:if>
                                                            <c:if test="${user.status == status_blocked && user.role != role_admin}">
                                                                <button class="user-list-item-button" type="submit"><fmt:message key="change.button-unblock"/></button>
                                                            </c:if>
                                                        </form>
                                                    </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="slider__item">
                                        <div class="user-list-container">
                                            <c:forEach var="user" items = "${users}" begin="${(item - 1) * elements_per_page}" end="${number_of_users}">
                                                <div class="user-list-item">
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.id"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.userId}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.email"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.email}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.login"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.login}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.name"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.name}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.surname"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.surname}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.discount"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.discount}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.balance"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.balance}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.registration-date"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.registrationDate}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.role"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.role}
                                                        </div>
                                                    </div>
                                                    <div class="user-list-text-item">
                                                        <div class="user-list-text1">
                                                            <fmt:message key="users.user.status"/>
                                                        </div>
                                                        <div class="user-list-text2">
                                                                ${user.status}
                                                        </div>
                                                    </div>
                                                    <form method="post" action="ProjectServlet">
                                                        <input type="hidden" name="command" value="change_user_status_command"/>
                                                        <input type="hidden" name="id" value="${user.userId}"/>
                                                        <input type="hidden" name="active" value="false"/>
                                                        <input type="hidden" name="title_users" value="${title_users}"/>
                                                        <c:if test="${user.status == status_active && user.role != role_admin}">
                                                            <button class="user-list-item-button" type="submit"><fmt:message key="change.button-block"/></button>
                                                        </c:if>
                                                        <c:if test="${user.status == status_blocked || user.role == role_admin}">
                                                            <button disabled="disabled" class="user-list-item-button" type="submit"><fmt:message key="change.button-block"/></button>
                                                        </c:if>
                                                    </form>
                                                    <form class="form-button-user2" method="post" action="ProjectServlet">
                                                        <input type="hidden" name="command" value="change_user_status_command"/>
                                                        <input type="hidden" name="id" value="${user.userId}"/>
                                                        <input type="hidden" name="active" value="true"/>
                                                        <input type="hidden" name="title_users" value="${title_users}"/>
                                                        <c:if test="${user.status == status_active || user.role == role_admin}">
                                                            <button disabled="disabled" class="user-list-item-button" type="submit"><fmt:message key="change.button-unblock"/></button>
                                                        </c:if>
                                                        <c:if test="${user.status == status_blocked && user.role != role_admin}">
                                                            <button class="user-list-item-button" type="submit"><fmt:message key="change.button-unblock"/></button>
                                                        </c:if>
                                                    </form>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
                <a class="slider__control slider__control_prev" href="#" role="button" data-slide="prev"></a>
                <a class="slider__control slider__control_next" href="#" role="button" data-slide="next"></a>
            </div>
        </section>
    </div>
</main>
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
</body>
</html>
