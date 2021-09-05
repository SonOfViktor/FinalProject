<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle scope="session" basename="language"/>

<c:set var="all">all</c:set>
<c:set var="active">active</c:set>
<c:set var="completed">completed</c:set>
<c:set var="founded">founded</c:set>
<c:set var="person">person</c:set>
<c:set var="statusAdmin">ADMIN</c:set>
<c:set var="statusUser">USER</c:set>
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
            href="assets/css/main10.css"
            rel="stylesheet"
    />
    <link
            href="assets/css/orders1.css"
            rel="stylesheet"
    />
    <link
            href="assets/css/slider2.css"
            rel="stylesheet"
    />
    <title><fmt:message key="orders.main-title"/></title>
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
            <li class="header-ref1">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_home_page_command"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.home"/>
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
<main class="main-catalog">
    <section class="block2">
        <div class="block2-background">
            <c:if test="${sessionScope.role == statusUser}">
                <div class="block2-item">
                    <form class="block2-form" method="post" action="ProjectServlet">
                        <input type="hidden" name="command" value="to_create_order_page_command"/>
                        <button class="block2-button-create" type="submit"><fmt:message key="orders.create.order"/></button>
                    </form>
                </div>
            </c:if>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_order_by_id_page_command"/>
                    <div class="block2-text"><fmt:message key="orders.search.title.id"/></div>
                    <input class="block2-input"
                           type="number"
                           name="id"
                           placeholder="<fmt:message key="orders.search.placeholder.id"/>"
                           min="1"
                           max="9223372036854775807"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="orders.search.button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_order_by_status_page_command"/>
                    <div class="block2-text"><fmt:message key="orders.search.title.status"/></div>
                    <select class="block2-select" name="order_status" data-live-search="true">
                        <option value="ACTIVE"><fmt:message key="orders.search.select.active"/></option>
                        <option value="COMPLETED"><fmt:message key="orders.search.select.completed"/></option>
                    </select>
                    <button class="block2-button1" type="submit"><fmt:message key="orders.search.button"/></button>
                </form>
            </div>
            <c:if test="${sessionScope.role == statusAdmin}">
                <div class="block2-item">
                    <form class="block2-form" method="post" action="ProjectServlet">
                        <input type="hidden" name="command" value="to_find_order_by_login_page_command"/>
                        <div class="block2-text"><fmt:message key="orders.search.title.login"/></div>
                        <input class="block2-input"
                               type="text"
                               name="login"
                               placeholder="<fmt:message key="orders.search.placeholder.login"/>"
                               minlength="1"
                               maxlength="40"
                               pattern="[\w][\w._-]{0,39}"
                        />
                        <button class="block2-button1" type="submit"><fmt:message key="orders.search.button"/></button>
                    </form>
                </div>
            </c:if>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="cancel_order_command"/>
                    <div class="block2-text"><fmt:message key="orders.cancel.title"/></div>
                    <input class="block2-input"
                           type="number"
                           name="id"
                           placeholder="<fmt:message key="orders.search.placeholder.id"/>"
                           min="1"
                           max="9223372036854775807"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="orders.cancel.button"/></button>
                </form>
            </div>
        </div>
    </section>

    <div class="catalog-background">
        <section class="catalog">
            <c:if test="${title_orders == all}">
                <div class="order-list-title">
                    <fmt:message key="orders.title.all"/>
                </div>
            </c:if>
            <c:if test="${title_orders == active}">
                <div class="order-list-title">
                    <fmt:message key="orders.title.active"/>
                </div>
            </c:if>
            <c:if test="${title_orders == completed}">
                <div class="order-list-title">
                    <fmt:message key="orders.title.completed"/>
                </div>
            </c:if>
            <c:if test="${title_orders == founded}">
                <div class="order-list-title">
                    <fmt:message key="orders.title.found"/>
                </div>
            </c:if>
            <c:if test="${title_orders == person}">
                <div class="order-list-title">
                    <fmt:message key="orders.title.personal"/>
                </div>
            </c:if>
            <c:if test="${number_of_orders == 0 && empty_orders_message != true}">
                <div class="find-empty-text">
                    <fmt:message key="orders.empty.message.all"/>
                </div>
            </c:if>
            <c:if test="${number_of_orders == 0 && empty_orders_message == true}">
                <div class="find-empty-text">
                    <fmt:message key="orders.empty.message.personal"/>
                </div>
            </c:if>
            <div class="slider1">
                <div class="slider__wrapper">
                    <div class="slider__items">
                        <c:forEach var="item" begin="1" end="${pages_number}" varStatus="loop">
                            <c:choose>
                                <c:when test="${item != pages_number}">
                                    <div class="slider__item">
                                        <div class="order-list-container">
                                            <c:forEach var="order" items = "${orders}" begin="${(item - 1) * elements_per_page}" end="${item * elements_per_page - 1}">
                                                <div class="order-list-item">
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.id"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.orderId}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.paid"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.paid}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.registration-date"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.registrationDate}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.login"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.userLogin}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.status"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.orderStatus}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.tattoo.id"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.tattooId}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.tattoo.price"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.tattooPrice}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.tattoo.name"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                            ${order.tattooName}
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="slider__item">
                                        <div class="order-list-container">
                                            <c:forEach var="order" items = "${orders}" begin="${(item - 1) * elements_per_page}" end="${number_of_orders}">
                                                <div class="order-list-item">
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.id"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.orderId}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.paid"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.paid}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.registration-date"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.registrationDate}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.login"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.userLogin}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.status"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.orderStatus}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.tattoo.id"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.tattooId}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.tattoo.price"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.tattooPrice}
                                                        </div>
                                                    </div>
                                                    <div class="order-list-text-item">
                                                        <div class="order-list-text1">
                                                            <fmt:message key="order.tattoo.name"/>
                                                        </div>
                                                        <div class="order-list-text2">
                                                                ${order.tattooName}
                                                        </div>
                                                    </div>
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
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_proposal_page_command"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.proposals"/></button>
                </form>
            </li>
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_catalog_page_command"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.catalog"/></button>
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
