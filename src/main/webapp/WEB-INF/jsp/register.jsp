<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Register</title>
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
    <form method="post" action="ProjectServlet">
        <input type="hidden" name="command" value="register_user_command"/>
        <div class="info-div">
            <input type="info-field" name="name" placeholder="Name"
                   required
                   minlength="1"
                   maxlength="40"
                   pattern="[A-Z](\w[^0-9]){1,40}"
            >
        </div>
        <div class="info-div">
            <input type="info-field" name="surname" placeholder="Surname"
                   required
                   minlength="1"
                   maxlength="40"
                   pattern="[A-Z](\w[^0-9]){1,40}"
            >
        </div>
        <div class="info-div">
            <input type="text" name="login" placeholder="Login"
                   required
                   minlength="1"
                   maxlength="40"
                   pattern="[a-zA-Z][\w._-]{1,40}"
            >
        </div>
        <div class="info-div">
            <input type="email" name="email" placeholder="Email"
                   required
                   minlength="2"
                   maxlength="100"
                   pattern="[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,100})"
            >
        </div>
        <div class="info-div">
            <input type="password"
                   autocomplete="current-password"
                   name="password"
                   placeholder="Password"
                   required
                   minlength="8"
                   maxlength="45"
                   pattern="[a-zA-Z\d_-]{8,45}"
            >
        </div>
        <div class="info-div">
            <input type="password"
                   autocomplete="current-password"
                   name="repeat_password"
                   placeholder="Repeat password"
                   required
                   minlength="8"
                   maxlength="45"
                   pattern="[a-zA-Z\d_-]{8,45}"
            >
        </div>
        <div class="info-div">
            <input id="date" type="hidden" name="registration_date">
        </div>
        <button type="submit"
                class="submit"
                onclick="timeNow(date)">
            Register
        </button>
    </form>
</div>
<script>
    function pad(number) {
        if (number < 10) {
            return '0' + number;
        }
        return number;
    }

    function timeNow(date) {
        time = new Date();
        time.setTime(time.getTime() + time.getTimezoneOffset()*60*1000*(-2));
        date.value = time.getUTCFullYear() +
            '-' + pad(time.getUTCMonth() + 1) +
            '-' + pad(time.getUTCDate()) +
            ' ' + pad(time.getUTCHours()) +
            ':' + pad(time.getUTCMinutes()) +
            ':' + pad(time.getUTCSeconds()) +
            '.' + (time.getUTCMilliseconds() / 1000).toFixed(9).slice(2, 11);
    }
</script>
</body>
</html>
