<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle scope="session" basename="language"/>

<c:set var="role_admin">ADMIN</c:set>
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
            href="${pageContext.request.contextPath}/assets/css/main12.css"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/aboutus2.css"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/slider2.css"
            rel="stylesheet"
    />
    <title><fmt:message key="about.us.main-title"/></title>
</head>
<body>
<script src="assets/js/slider1.js">
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
<script src="assets/js/time3.js"></script>
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
<main class="main-background">
    <div class="average-rating-title"><fmt:message key="about.us.users-assessment"/>${average_rating} / 10</div>
    <form method="post" action="ProjectServlet">
        <input type="hidden" name="command" value="create_comment_command"/>
        <input id="date" type="hidden" name="registration_date">
        <button class="comment-button"
                type="submit"
                onclick="timeNow(date)">
            <fmt:message key="about.us.post.comment"/>
        </button>
        <textarea class="comment-input" name="text" minlength="1" maxlength="1000" pattern="[A-Za-zА-Яа-яЁё1-9 ,\.!?\d]{1,1000}" placeholder="<fmt:message key="about.us.textarea.placeholder"/>"></textarea>
    </form>
    <div class="comments-title">
        <fmt:message key="about.us.comments.title"/>
    </div>
    <div class="about-us-background">
        <section class="catalog">
            <div class="slider1">
                <div class="slider__wrapper">
                    <div class="slider__items">
                        <c:forEach var="item" begin="1" end="${pages_number}" varStatus="loop">
                            <c:choose>
                                <c:when test="${item != pages_number}">
                                    <div class="slider__item">
                                        <div class="comments-list-container">
                                            <c:forEach var="comment" items = "${comments}" begin="${(item - 1) * elements_per_page}" end="${item * elements_per_page - 1}">
                                                <div class="comments-list-item">
                                                    <div class="comments-list-text-item1">
                                                        <div class="comments-list-text1">
                                                                ${comment.userLogin}: ${comment.registrationDate}
                                                        </div>
                                                    </div>
                                                    <div class="comments-list-text-item2">
                                                        <div class="comments-list-text2">
                                                                ${comment.text}
                                                        </div>
                                                    </div>
                                                    <c:if test="${sessionScope.role == role_admin}">
                                                        <form class="form-button-comment" method="post" action="ProjectServlet">
                                                            <input type="hidden" name="command" value="delete_comment_command"/>
                                                            <input type="hidden" name="comment_id" value="${comment.commentId}"/>
                                                            <button class="comments-list-item-button" type="submit">
                                                                <fmt:message key="about.us.delete.comment"/>
                                                            </button>
                                                        </form>
                                                        <form class="form-button-comment" method="post" action="ProjectServlet">
                                                            <input type="hidden" name="command" value="change_user_status_command"/>
                                                            <input type="hidden" name="id" value="${comment.userId}"/>
                                                            <input type="hidden" name="active" value="false"/>
                                                            <button class="comments-list-item-button" type="submit">
                                                                <fmt:message key="about.us.block.user"/>
                                                            </button>
                                                        </form>
                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="slider__item">
                                        <div class="comments-list-container">
                                            <c:forEach var="comment" items = "${comments}" begin="${(item - 1) * elements_per_page}" end="${number_of_orders}">
                                                <div class="comments-list-item">
                                                    <div class="comments-list-text-item1">
                                                        <div class="comments-list-text1">
                                                            ${comment.userLogin}: ${comment.registrationDate}
                                                        </div>
                                                    </div>
                                                    <div class="comments-list-text-item2">
                                                        <div class="comments-list-text2">
                                                            ${comment.text}
                                                        </div>
                                                    </div>
                                                    <c:if test="${sessionScope.role == role_admin}">
                                                        <form class="form-button-comment" method="post" action="ProjectServlet">
                                                            <input type="hidden" name="command" value="delete_comment_command"/>
                                                            <input type="hidden" name="comment_id" value="${comment.commentId}"/>
                                                            <button class="comments-list-item-button" type="submit">
                                                                <fmt:message key="about.us.delete.comment"/>
                                                            </button>
                                                        </form>
                                                        <form class="form-button-comment" method="post" action="ProjectServlet">
                                                            <input type="hidden" name="command" value="change_user_status_command"/>
                                                            <input type="hidden" name="id" value="${comment.userId}"/>
                                                            <input type="hidden" name="active" value="false"/>
                                                            <button class="comments-list-item-button" type="submit">
                                                                <fmt:message key="about.us.block.user"/>
                                                            </button>
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

