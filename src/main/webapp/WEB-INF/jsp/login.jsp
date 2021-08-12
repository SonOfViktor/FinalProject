<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
            href="https://fonts.googleapis.com/css2?family=Bebas+Neue&amp;display=swap"
            rel="stylesheet"
    >
<%--    <link--%>
<%--            href="assets/css/loginpage.css"--%>
<%--            rel="stylesheet"--%>
<%--    />--%>
    <title>Login</title>
    <style>
        body {
            background: #181617;
            margin-top: auto;
            margin-bottom: auto;
            margin-left: auto;
            margin-right: auto;
        }
        .container {
            margin-left: auto;
            margin-right: auto;
            margin-top: 5%;
            width: 1000px;
            height: 500px;
        }
        .button{
            background: #CD6326;
        }
        button:hover {
            background: #E39128;
        }
        button:active {
            background: #8F5613;
        }
        input {
            position: relative;
            left: 20%;
            right: 5%;
            top: 5%;
            border: 1px solid #666666;
            border-radius: 5px 5px 5px 5px;
            background: #CCCCCC;
            outline: none;
            width: 600px;
            height: 55px;
            color: #333333;
            font-family: Roboto;
            font-style: normal;
            font-weight: normal;
            font-size: 23px;
            padding-left: 15px;
        }
        form {
            padding-top: 1%;
        }
        .info-div {
            padding-top: 1%;
        }
        .info-field {
            position: relative;
            left: 5%;
            right: 5%;
            font-family: Open Sans;
            font-style: normal;
            font-weight: normal;
            font-size: 17px;
            line-height: 27px;
            text-align: center;
            color: #F7F7F7;
        }
        .submit {
            position: relative;
            width: 600px;
            height: 60px;
            left: 20%;
            top: 5%;
            background: #CD6326;
            border-radius: 5px;
            outline: none;
            font-family: Roboto;
            font-style: normal;
            font-weight: normal;
            font-size: 23px;
            line-height: 27px;
            text-align: center;
            color: #F7F7F7;
        }
        .register {
            position: relative;
            width: 290px;
            height: 60px;
            background: #CD6326;
            border-radius: 5px;
            outline: none;
            font-family: Roboto;
            font-style: normal;
            font-weight: normal;
            font-size: 23px;
            line-height: 27px;
            text-align: center;
            color: #F7F7F7;
        }
        .register-form {
            position: relative;
            width: 290px;
            height: 60px;
            left: 51%;
            top: -9%;
        }
        .login {
            position: relative;
            width: 290px;
            height: 60px;
            left: 20%;
            top: 5%;
            background: #CD6326;
            border-radius: 5px;
            outline: none;
            font-family: Roboto;
            font-style: normal;
            font-weight: normal;
            font-size: 23px;
            line-height: 27px;
            text-align: center;
            color: #F7F7F7;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="info-div">
        <input type="text" name="Login" placeholder="Login"
               required
               minlength="1"
               maxlength="40"
               pattern="[a-zA-Z][\w._-]{1,40}"
        >
    </div>
    <div class="info-div">
        <input type="email" name="Email" placeholder="Email"
               required
               minlength="2"
               maxlength="100"
               pattern="[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,100})"
        >
    </div>
    <div class="info-div">
        <input type="password" autocomplete="current-password" name="Password" placeholder="Password"
               required
               minlength="8"
               maxlength="45"
               pattern="[a-zA-Z\d_-]{8,45}"
        >
    </div>
    <button class="login">Log in</button>
    <form class="register-form" method="post" action="ProjectServlet" width="290px">
        <input type="hidden" name="command" value="to_register_page_command"/>
        <button class="register">Register</button>
    </form>
</div>
</body>
</html>