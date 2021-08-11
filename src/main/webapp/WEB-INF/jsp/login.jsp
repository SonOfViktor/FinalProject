<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Login</title>
</head>
<body>
<div class="container">
    <form>
        <input type="text" name="Login" placeholder="Login"
               required
               minlength="1"
               maxlength="40"
               pattern="[a-zA-Z][\w._-]{1,40}"
        >
    </form>
    <form>
        <input type="email" name="Email" placeholder="Email"
               required
               minlength="2"
               maxlength="100"
               pattern="[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,100})"
        >
    </form>
    <form>
        <input type="password" autocomplete="current-password" name="Password" placeholder="Password"
               required
               minlength="8"
               maxlength="45"
               pattern="[a-zA-Z\d_-]{8,45}"
        >
    </form>
    <button class="register">Register</button>
    <button class="login">Log in</button>
</div>
</body>
</html>