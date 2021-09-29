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
    <title><fmt:message key="error.500.main-title"/></title>
</head>
<body>
<div class="container">
    <div class="error-title-error">
        500
    </div>
    <div class="error-title">
        <fmt:message key="error.500.title"/>
    </div>
    <a class="error-text" href="ProjectServlet?command=to_home_page">
        <fmt:message key="error.ref"/>
    </a>
</div>
</body>
</html>