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
            href="assets/css/main10.css"
            rel="stylesheet"
    />
    <link
            href="assets/css/proposal2.css"
            rel="stylesheet"
    />
    <title><fmt:message key="add.tattoo.main-title"/></title>
</head>
<body class="body-size1">
<div class="header-background">
    <header>
        <div class="header-title" display="inline">
            <fmt:message key="header.main-title"/>
        </div>
        <ul>
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
<div class="container-proposal2">
    <div class="proposal-info">
        <fmt:message key="login.message.info"/>
    </div>
    <form method="post" action="ProjectServlet">
        <input type="hidden" name="command" value="add_tattoo_command"/>
        <div class="info-div">
            <input type="info-field" name="name" placeholder="<fmt:message key="add.tattoo.name"/>"
                   required
                   minlength="1"
                   maxlength="40"
                   pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}"
            >
        </div>
        <div class="info-div">
            <input type="info-field" name="description" placeholder="<fmt:message key="add.tattoo.description"/>"
                   required
                   minlength="1"
                   maxlength="2000"
                   pattern="[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё ,\.!?\d]{0,1999}"
            >
        </div>
        <div class="info-div">
            <input type="number" name="price" placeholder="<fmt:message key="add.tattoo.price"/>"
                   required
                   min="0"
                   max="9223372036854775807"
            >
        </div>
        <div class="info-div">
            <input type="number" name="width" placeholder="<fmt:message key="add.tattoo.width"/>"
                   required
                   min="1"
                   max="2147483647"
            >
        </div>
        <div class="info-div">
            <input type="text" name="height" placeholder="<fmt:message key="add.tattoo.height"/>"
                   required
                   min="1"
                   max="2147483647"
            >
        </div>
        <div class="info-div">
            <input type="text" name="image_url" placeholder="<fmt:message key="add.tattoo.link"/>"
                   required
                   minlength="1"
                   maxlength="255"
            >
        </div>
        <div class="info-div-proposal2">
            <select class="block2-select2" id="place" name="place" data-live-search="true">
                <option value="1"><fmt:message key="add.tattoo.select.place.hand"/></option>
                <option value="2"><fmt:message key="add.tattoo.select.place.leg"/></option>
                <option value="3"><fmt:message key="add.tattoo.select.place.back"/></option>
                <option value="4"><fmt:message key="add.tattoo.select.place.chest"/></option>
                <option value="5"><fmt:message key="add.tattoo.select.place.face"/></option>
                <option value="6"><fmt:message key="add.tattoo.select.place.neck"/></option>
                <option value="7"><fmt:message key="add.tattoo.select.place.wrist"/></option>
            </select>
        </div>
        <div class="info-div-proposal2">
            <select class="block2-select2" id="tattoo_status" name="tattoo_status" data-live-search="true">
                <option value="1"><fmt:message key="add.tattoo.select.status.active"/></option>
                <option value="2"><fmt:message key="add.tattoo.select.status.locked"/></option>
                <option value="3"><fmt:message key="add.tattoo.select.status.offered"/></option>
            </select>
        </div>
        <button class="submit"
                type="submit" >
            <fmt:message key="add.button"/>
        </button>
    </form>
</div>
<footer class="footer">
    <div class="footer-background">
        <div class="footer-title"><fmt:message key="footer.main-title"/></div>
        <ul class="footer-info">
            <li class="footer-info-item">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="to_home_page_command"/>
                    <button class="footer-button" type="submit"><fmt:message key="footer.home"/></button>
                </form>
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