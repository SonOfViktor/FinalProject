<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle scope="session" basename="language"/>

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
            href="${pageContext.request.contextPath}/assets/css/main16.css"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/users4.css"
            rel="stylesheet"
    />
    <title><fmt:message key="users.main-title"/></title>
</head>
<body>
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
                    <input type="hidden" name="command" value="to_profile_page"/>
                    <button class="header-button" type="submit">
                        <fmt:message key="header.profile"/>
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
<main class="main-catalog">
    <section class="block2">
        <div class="block2-background">
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_user_by_id_page"/>
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
                    <input type="hidden" name="command" value="to_find_user_by_login_page"/>
                    <div class="block2-text"><fmt:message key="users.search.title.login"/></div>
                    <input class="block2-input"
                           type="text"
                           name="login"
                           placeholder="<fmt:message key="users.search.placeholder.login"/>"
                           required
                           oninvalid="this.setCustomValidity('<fmt:message key="regex.login"/>')"
                           oninput="setCustomValidity('')"
                           minlength="1"
                           maxlength="40"
                           pattern="[\w][\w._-]{0,39}"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="users.search.button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_users_by_name_page"/>
                    <div class="block2-text"><fmt:message key="users.search.title.name"/></div>
                    <input class="block2-input"
                           type="text"
                           name="name"
                           placeholder="<fmt:message key="users.search.placeholder.name"/>"
                           required
                           oninvalid="this.setCustomValidity('<fmt:message key="regex.name"/>')"
                           oninput="setCustomValidity('')"
                           minlength="1"
                           maxlength="40"
                           pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}"
                    />
                    <button class="block2-button1" type="submit"><fmt:message key="users.search.button"/></button>
                </form>
            </div>
            <div class="block2-item">
                <form class="block2-form" method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_find_users_by_surname_page"/>
                    <div class="block2-text"><fmt:message key="users.search.title.surname"/></div>
                    <input class="block2-input"
                           type="text"
                           name="surname"
                           placeholder="<fmt:message key="users.search.placeholder.surname"/>"
                           oninvalid="this.setCustomValidity('<fmt:message key="regex.surname"/>')"
                           oninput="setCustomValidity('')"
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
            <div class="user-list-container">
                <ctg:users_pagination currentPage="${current_page_number}"
                                       elementsPerPage="${elements_per_page}"
                                       title="${title_users}"/>

                <c:if test="${users.size() > 0}">
                    <form method="post" action="ProjectServlet">
                        <input type="hidden" name="command" value="${requestScope.command}"/>
                        <input type="hidden" name="current_page_number" value="${current_page_number - 1}"/>
                        <c:if test="${current_page_number > 1}">
                            <button class="button-prev"><fmt:message key="pagination.back"/></button>
                        </c:if>
                        <c:if test="${current_page_number == 1}">
                            <button disabled="disabled" class="button-prev"><fmt:message key="pagination.back"/></button>
                        </c:if>
                    </form>
                    <form method="post" action="ProjectServlet">
                        <input type="hidden" name="command" value="${requestScope.command}"/>
                        <input type="hidden" name="current_page_number" value="${current_page_number + 1}"/>
                        <c:if test="${current_page_number < pages_number}">
                            <button class="button-next"><fmt:message key="pagination.forward"/></button>
                        </c:if>
                        <c:if test="${current_page_number == pages_number}">
                            <button disabled="disabled" class="button-next"><fmt:message key="pagination.forward"/></button>
                        </c:if>
                    </form>
                    <div class="page-number"> ${current_page_number} / ${pages_number}</div>
                </c:if>
            </div>
        </section>
    </div>
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
