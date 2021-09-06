<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle scope="session" basename="language"/>

<c:set var="all">all</c:set>
<c:set var="active">active</c:set>
<c:set var="locked">locked</c:set>
<c:set var="founded">founded</c:set>
<c:set var="proposal">proposal</c:set>
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
            href="assets/css/main11.css"
            rel="stylesheet"
    />
    <link
            href="assets/css/slider2.css"
            rel="stylesheet"
    />
    <title><fmt:message key="main.catalog"/></title>
</head>
<body class="body-size2">
<script src="assets/js/slider1.js">
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var slider = new SimpleAdaptiveSlider('.slider2');

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
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="to_home_page_command"/>
            <button class="header-title" type="submit">
                <fmt:message key="header.main-title"/>
            </button>
        </form>
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
    <section class="block4">
        <div class="block4-background">
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_proposal_page_command"/>
                    <button class="block2-button-create" type="submit"><fmt:message key="main.proposal-button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_tattoo_by_id_page_command"/>
                    <div class="block2-text"><fmt:message key="tattoo.search-by-id"/></div>
                    <input class="block2-input"
                           type="number"
                           name="id"
                           placeholder="<fmt:message key="tattoo.search-by-id-input"/>"
                           min="1"
                           max="9223372036854775807"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="tattoo.search-button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_tattoo_by_name_page_command"/>
                    <div class="block2-text"><fmt:message key="tattoo.search-by-name"/></div>
                    <input class="block2-input"
                           type="text"
                           name="name"
                           placeholder="<fmt:message key="tattoo.search-by-name-input"/>"
                           minlength="1"
                           maxlength="40"
                           pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="tattoo.search-button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_tattoo_by_place_page_command"/>
                    <div class="block2-text"><fmt:message key="tattoo.search-by-place"/></div>
                    <select class="block2-select" name="place" data-live-search="true">
                        <option value="HAND"><fmt:message key="add.tattoo.select.place.hand"/></option>
                        <option value="LEG"><fmt:message key="add.tattoo.select.place.leg"/></option>
                        <option value="BACK"><fmt:message key="add.tattoo.select.place.back"/></option>
                        <option value="CHEST"><fmt:message key="add.tattoo.select.place.chest"/></option>
                        <option value="FACE"><fmt:message key="add.tattoo.select.place.face"/></option>
                        <option value="NECK"><fmt:message key="add.tattoo.select.place.neck"/></option>
                        <option value="WRIST"><fmt:message key="add.tattoo.select.place.wrist"/></option>
                    </select>
                    <button class="block2-button1" type="submit"><fmt:message key="tattoo.search-button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_tattoo_by_price_range_page_command"/>
                    <div class="block2-text"><fmt:message key="tattoo.search-by-price"/></div>
                    <input class="block2-input"
                           type="number"
                           name="min_price"
                           placeholder="<fmt:message key="tattoo.search-by-price-min"/>"
                           min="0"
                           max="2147483647"
                    />
                    <input class="block2-input"
                           type="number"
                           name="max_price"
                           placeholder="<fmt:message key="tattoo.search-by-price-max"/>"
                           min="0"
                           max="2147483647"
                    />
                    <button class="block2-button2" type="submit"><fmt:message key="tattoo.search-button"/></button>
                </form>
            </div>
        </div>
    </section>
    <div class="catalog-background2">
        <section class="catalog">
            <div class="main-title1"><fmt:message key="main.catalog"/></div>
            <c:if test="${title_tattoos == all}">
                <div class="main-title2">
                    <fmt:message key="tattoo.list-all"/>
                </div>
            </c:if>
            <c:if test="${title_tattoos == active}">
                <div class="main-title2">
                    <fmt:message key="tattoo.list-active"/>
                </div>
            </c:if>
            <c:if test="${title_tattoos == locked}">
                <div class="main-title2">
                    <fmt:message key="tattoo.list-locked"/>
                </div>
            </c:if>
            <c:if test="${title_tattoos == founded}">
                <div class="main-title2">
                    <fmt:message key="tattoo.list-founded"/>
                </div>
            </c:if>
            <c:if test="${title_tattoos == proposal}">
                <div class="main-title2">
                    <fmt:message key="tattoo.list-proposal"/>
                </div>
            </c:if>
            <c:if test="${number_of_tattoos == 0}">
                <div class="find-empty-text2">
                    <fmt:message key="tattoo.find-error-message1"/>
                </div>
            </c:if>
                <div class="slider2">
                    <div class="slider__wrapper">
                        <div class="slider__items">
                            <c:forEach var="item" begin="1" end="${pages_number}" varStatus="loop">
                                <c:choose>
                                    <c:when test="${item != pages_number}">
                                        <div class="slider__item">
                                            <div class="main-grid-container">
                                                <c:forEach var="tattoo" items = "${catalog}" begin="${(item - 1) * elements_per_page}" end="${item * elements_per_page - 1}">
                                                    <div class="main-grid-item" title="${tattoo.description}">
                                                        <ul>
                                                            <li class="main-text" ><fmt:message key="tattoo.number-tattoo"/> ${tattoo.tattooId}</li>
                                                            <li class="main-text"><fmt:message key="tattoo.name-tattoo"/> ${tattoo.name}</li>
                                                            <li class="main-text"><fmt:message key="tattoo.price-tattoo"/> ${tattoo.price}</li>
                                                            <li class="main-text"><fmt:message key="tattoo.width-tattoo"/> ${tattoo.width}</li>
                                                            <li class="main-text"><fmt:message key="tattoo.height-tattoo"/> ${tattoo.height}</li>
                                                            <li class="main-text"><fmt:message key="tattoo.image-tattoo"/>
                                                                <a href="${tattoo.imageUrl}">
                                                                    <fmt:message key="tattoo.click-tattoo"/>
                                                                </a>
                                                            </li>
                                                            <li class="main-text"><fmt:message key="tattoo.body-part-tattoo"/> ${tattoo.places}</li>
                                                        </ul>
                                                        <c:if test="${title_tattoos == proposal}">
                                                            <form method="post" action="ProjectServlet">
                                                                <input type="hidden" name="command" value="to_approve_tattoo_page_command"/>
                                                                <input type="hidden" name="id" value="${tattoo.tattooId}"/>
                                                                <button class="tattoo-item-button"><fmt:message key="tattoo.proposal"/></button>
                                                            </form>
                                                        </c:if>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="slider__item">
                                            <div class="main-grid-container">
                                            <c:forEach var="tattoo" items = "${catalog}" begin="${(item - 1) * elements_per_page}" end="${number_of_tattoos}">
                                                <div class="main-grid-item" title="${tattoo.description}">
                                                    <ul>
                                                        <li class="main-text" ><fmt:message key="tattoo.number-tattoo"/> ${tattoo.tattooId}</li>
                                                        <li class="main-text"><fmt:message key="tattoo.name-tattoo"/> ${tattoo.name}</li>
                                                        <li class="main-text"><fmt:message key="tattoo.price-tattoo"/> ${tattoo.price}</li>
                                                        <li class="main-text"><fmt:message key="tattoo.width-tattoo"/> ${tattoo.width}</li>
                                                        <li class="main-text"><fmt:message key="tattoo.height-tattoo"/> ${tattoo.height}</li>
                                                        <li class="main-text"><fmt:message key="tattoo.image-tattoo"/>
                                                            <a href="${tattoo.imageUrl}">
                                                                <fmt:message key="tattoo.click-tattoo"/>
                                                            </a>
                                                        </li>
                                                        <li class="main-text"><fmt:message key="tattoo.body-part-tattoo"/> ${tattoo.places}</li>
                                                    </ul>
                                                    <c:if test="${title_tattoos == proposal}">
                                                        <form method="post" action="ProjectServlet">
                                                            <input type="hidden" name="command" value="to_approve_tattoo_page_command"/>
                                                            <input type="hidden" name="id" value="${tattoo.tattooId}"/>
                                                            <button class="tattoo-item-button"><fmt:message key="tattoo.proposal"/></button>
                                                        </form>
                                                    </c:if>
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
