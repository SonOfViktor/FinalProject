<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Error 404</title>
</head>
<body>
<div class="container">
    <div class="error-title-error">
        404
    </div>
    <div class="error-title">
        PAGE NOT FOUND
    </div>
    <form method="post" action="ProjectServlet">
        <input type="hidden" name="command" value="to_home_page_command"/>
        <button class="error-text" type="submit">
            GO BACK TO HOME PAGE AND TRY ANOTHER FUNCTIONALITY
        </button>
    </form>
</div>
</body>
</html>