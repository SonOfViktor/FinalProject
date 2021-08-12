<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
            href="https://fonts.googleapis.com/css2?family=Bebas+Neue&amp;display=swap"
            rel="stylesheet"
    >
    <link
            href="assets/css/loginpage.css"
            rel="stylesheet"
    />
    <title>Register</title>
</head>
<body>
<div class="container">
    <form method="post" action="projectServlet">
        <input type="hidden" name="command" value="register_user_command"/>
        <form>
            <input type="info-field" name="name" placeholder="Name"
                   required
                   minlength="1"
                   maxlength="40"
                   pattern="[A-Z](\w[^0-9]){1,40}"
            >
        </form>
        <form>
            <input type="info-field" name="surname" placeholder="Surname"
                   required
                   minlength="1"
                   maxlength="40"
                   pattern="[A-Z](\w[^0-9]){1,40}"
            >
        </form>
        <form>
            <input type="text" name="login" placeholder="Login"
                   required
                   minlength="1"
                   maxlength="40"
                   pattern="[a-zA-Z][\w._-]{1,40}"
            >
        </form>
        <form>
            <input type="email" name="email" placeholder="Email"
                   required
                   minlength="2"
                   maxlength="100"
                   pattern="[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,100})"
            >
        </form>
        <form>
            <input type="password"
                   autocomplete="current-password"
                   name="password"
                   placeholder="Password"
                   required
                   minlength="8"
                   maxlength="45"
                   pattern="[a-zA-Z\d_-]{8,45}"
            >
        </form>
        <form>
            <input type="password"
                   autocomplete="current-password"
                   name="repeat_password"
                   placeholder="Repeat password"
                   required
                   minlength="8"
                   maxlength="45"
                   pattern="[a-zA-Z\d_-]{8,45}"
            >
        </form>
        <form method="post" action="ProjectServlet">
            <button type="submit"
                    class="submit"
                    onclick="timeNow(date)"
                    href="ProjectServlet?command=register_user_command">
                Register
            </button>
        </form>
        <form>
            <input id="date" type="hidden" name="registration_date">
        </form>
    </form>
</div>
<script src="assets/js/timeNow.js"></script>
</body>
</html>
