<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
            href="https://fonts.googleapis.com/css2?family=Bebas+Neue&amp;display=swap"
            rel="stylesheet">
    <link
            href="${pageContext.request.contextPath}/assets/css/error2.css"
            rel="stylesheet"
    />
    <title>Error 403</title>
</head>
<body>
<div class="container">
    <div class="error-title-error">
        403
    </div>
    <div class="error-title">
        FORBIDDEN
    </div>
    <form method="post" action="ProjectServlet">
        <a class="error-text" href="ProjectServlet?command=to_home_page_command">
            GO BACK TO HOME PAGE AND TRY ANOTHER FUNCTIONALITY
        </a>
    </form>
</div>
</body>
</html>