<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вхід в систему</title>

    <link href="static/css/bootstrap.css" rel="stylesheet">
    <link href="static/css/signin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form class="form-signin" method="post" action="/login">
        <h2 class="form-signin-heading">Будь ласка увійдіть в систему</h2>
        <c:if test="${errorInput}">
            <div class="alert alert-danger">
                Помилка авторизіції
            </div>
        </c:if>
        <label for="inputEmail" class="sr-only">Електрона адресса</label>
        <input id="inputEmail" name="inputEmail" class="form-control" placeholder="Email" required="" autofocus=""
               type="email">
        <label for="inputPassword" class="sr-only">Пароль</label>
        <input id="inputPassword" name="inputPassword" class="form-control" placeholder="Пароль" required=""
               type="password">
        <div class="checkbox">
            <label>
                <input name="remember-me" value="remember-me" type="checkbox"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>


</body>
</html>
