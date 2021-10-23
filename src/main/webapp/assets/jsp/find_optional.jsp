<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle scope="session" basename="language"/>

<c:set var="roleVisitor">VISITOR</c:set>
<c:set var="roleUser">USER</c:set>
<c:set var="roleAdmin">ADMIN</c:set>
<c:set var="tattooStatusActive">ACTIVE</c:set>
<c:set var="tattooStatusLocked">LOCKED</c:set>
<c:set var="tattooStatusProposal">OFFERED_BY_USER</c:set>
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
    <link
            href="${pageContext.request.contextPath}/assets/css/users4.css"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/orders3.css"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/find1.css"
            rel="stylesheet"
    />
    <link
            href="${pageContext.request.contextPath}/assets/css/errormessage2.css"
            rel="stylesheet"
    />
    <script type="text/javascript">
        window.history.forward();
        function noBack()
        {
            window.history.forward();
        }
    </script>
    <script src="${pageContext.request.contextPath}/assets/js/time5.js"></script>
    <title><fmt:message key="find.main-title"/></title>
</head>
<body class="result-find-body">
<div class="header-background">
    <header>
        <form method="post" action="ProjectServlet">
            <input type="hidden" name="command" value="to_home_page"/>
            <button class="header-title" type="submit">
                <fmt:message key="header.main-title"/>
            </button>
        </form>
        <ul>
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
    </header>
</div>
<div class="result-find-container">
    <div class="result-find-title"><fmt:message key="find.optional.title"/></div>
    <c:if test = "${find_user_error == true}">
        <div class="user-error-message">
            <fmt:message key="find.optional.user.error.message"/>
        </div>
    </c:if>
    <c:if test = "${find_user_error == false}">
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
                <input type="hidden" name="command" value="change_user_status"/>
                <input type="hidden" name="id" value="${user.userId}"/>
                <input type="hidden" name="active" value="false"/>
                <input type="hidden" name="title_users" value="${title_users}"/>
                <button class="user-list-item-button" type="submit"><fmt:message key="change.button-block"/></button>
            </form>
            <form class="form-button-user2" method="post" action="ProjectServlet">
                <input type="hidden" name="command" value="change_user_status"/>
                <input type="hidden" name="id" value="${user.userId}"/>
                <input type="hidden" name="active" value="true"/>
                <input type="hidden" name="title_users" value="${title_users}"/>
                <button class="user-list-item-button" type="submit"><fmt:message key="change.button-unblock"/></button>
            </form>
        </div>
    </c:if>

    <c:if test = "${find_order_error == true}">
        <div class="user-error-message">
            <fmt:message key="find.optional.order.error.message"/>
        </div>
    </c:if>
    <c:if test = "${find_order_error == false}">
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
            <c:if test="${order.orderStatus == orderStatusActive}">
                <form method="post" action="ProjectServlet">
                    <input type="hidden" name="command" value="cancel_order"/>
                    <input type="hidden" name="id" value="${order.orderId}"/>
                    <button class="order-list-item-button">
                        <fmt:message key="orders.cancel.button"/>
                    </button>
                </form>
            </c:if>
        </div>
    </c:if>

    <c:if test = "${find_tattoo_error == true}">
        <div class="user-error-message">
            <fmt:message key="find.optional.tattoo.error.message"/>
        </div>
    </c:if>
    <c:if test = "${find_tattoo_error == false}">
        <div class="tattoo-item-position">
            <div class="main-grid-item" title="${tattoo.description}">
                <ul>
                    <li class="main-text" ><fmt:message key="tattoo.number-tattoo"/> ${tattoo.tattooId}</li>
                    <li class="main-text"><fmt:message key="tattoo.name-tattoo"/> ${tattoo.name}</li>
                    <li class="main-text"><fmt:message key="tattoo.price-tattoo"/>${tattoo.price}</li>
                    <li class="main-text">
                        <fmt:message key="tattoo.width-tattoo"/>
                            ${tattoo.width}
                        <fmt:message key="size.sm"/>
                    </li>
                    <li class="main-text">
                        <fmt:message key="tattoo.height-tattoo"/>
                            ${tattoo.height}
                        <fmt:message key="size.sm"/>
                    </li>
                    <li class="main-text"><fmt:message key="tattoo.image-tattoo"/>
                        <a href="${tattoo.imageUrl}" target="_blank">
                            <fmt:message key="tattoo.click-tattoo"/>
                        </a>
                    </li>
                    <li class="main-text"><fmt:message key="tattoo.body-part-tattoo"/> ${tattoo.places}</li>
                    <c:choose>
                        <c:when test="${tattoo.averageRating > 0}">
                            <li class="main-text"><fmt:message key="tattoo.rating-tattoo"/> ${tattoo.averageRating}</li>
                        </c:when>
                        <c:otherwise>
                            <li class="main-text"><fmt:message key="tattoo.rating-tattoo-empty"/></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <c:if test="${sessionScope.role == roleVisitor || sessionScope.role == roleUser}">
                    <form method="post" action="ProjectServlet">
                        <input type="hidden" name="command" value="create_order"/>
                        <input type="hidden" name="tattoo_id" value="${tattoo.tattooId}"/>
                        <input id="date" type="hidden" name="registration_date"/>
                        <button class="tattoo-item-button"
                                type="submit"
                                onclick="timeNow(date)">
                            <fmt:message key="tattoo.order"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role == roleAdmin}">
                    <c:if test="${tattoo.status == tattooStatusActive}">
                        <form method="post" action="ProjectServlet">
                            <input type="hidden" name="command" value="change_tattoo_status"/>
                            <input type="hidden" name="id" value="${tattoo.tattooId}"/>
                            <input type="hidden" name="active" value="false"/>
                            <button class="tattoo-item-button" type="submit">
                                <fmt:message key="tattoo.block"/>
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${tattoo.status == tattooStatusLocked}">
                        <form method="post" action="ProjectServlet">
                            <input type="hidden" name="command" value="change_tattoo_status"/>
                            <input type="hidden" name="id" value="${tattoo.tattooId}"/>
                            <input type="hidden" name="active" value="true"/>
                            <button class="tattoo-item-button" type="submit">
                                <fmt:message key="tattoo.unblock"/>
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${tattoo.status == tattooStatusProposal}">
                        <form method="post" action="ProjectServlet">
                            <input type="hidden" name="command" value="to_approve_tattoo_page"/>
                            <input type="hidden" name="id" value="${tattoo.tattooId}"/>
                            <button class="tattoo-item-button"><fmt:message key="tattoo.proposal"/></button>
                        </form>
                    </c:if>
                </c:if>
            </div>
        </div>
    </c:if>
</div>
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
                <a href="https://github.com/Daetwen/FinalProject" target="_blank">
                    <img class="footer-github-logo"
                         src="assets/image/github-logo.png"
                         alt="<fmt:message key="footer.alt.git"/>" >
                </a>
            </li>
            <li class="footer-info-refs-item">
                <a href="https://vk.com/vlad_makarey" target="_blank">
                    <img class="footer-vk-logo"
                         src="assets/image/vk-logo.svg"
                         alt="<fmt:message key="footer.alt.vk"/>">
                </a>
            </li>
            <li class="footer-info-refs-item">
                <a href="https://t.me/Daetwen" target="_blank">
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
