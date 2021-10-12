<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
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
            rel="stylesheet">
    <link
            href="${pageContext.request.contextPath}/assets/css/error3.css"
            rel="stylesheet"
    />
    <script type="text/javascript">
        window.history.forward();
        function noBack()
        {
            window.history.forward();
        }
    </script>
    <title><fmt:message key="error.404.main-title"/></title>
</head>
<body>
<div class="container">
    <div class="error-title-error">
        404
    </div>
    <div class="error-title2">
        <fmt:message key="error.404.title"/>
    </div>
    <form method="post" action="ProjectServlet">
        <input type="hidden" name="command" value="to_home_page"/>
        <button class="error-text" type="submit">
            <fmt:message key="error.ref"/>
        </button>
    </form>
</div>
</body>
</html>