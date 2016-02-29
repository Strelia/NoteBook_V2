<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value='/static/css/reset.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value='/static/js/bootstrap.js'/>"></script>
    <title>Список користувачів</title>
</head>
<body>
<div class="container">

    <header>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Електронний записник</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="user">Користувачі</a></li>
                        <li><a href="rdo">РДО</a></li>
                        <li><a href="record">Записи</a></li>
                        <li><a href="logout">Вихід</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div><!--/.container-fluid -->
        </nav>
    </header>

    <div class="row marketing">
        <div class="col-lg-10">
            <table class="table">
                <thead>
                <tr>
                    <th>Прізвище</th>
                    <th>Ім’я</th>
                    <th>Email</th>
                    <th>Пароль</th>
                    <th>Роль</th>
                    <th>РДО</th>
                    <th>Змінити</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstname}</td>
                        <td>${user.lastname}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.userRole == 'admin'}">Адміністратор</c:when>
                                <c:when test="${user.userRole == 'manager'}">Менеджер</c:when>
                                <c:when test="${user.userRole == 'user'}">Користувач</c:when>
                            </c:choose>
                       </td>
                        <td>${user.rdo.name}</td>
                        <td><a class="btn btn-info" href="/user?action=edit&userId=<c:out value="${user.idUser}"/>">  </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-lg-10">
            <a class="btn btn-info" href="/user?action=insert">Add Rdo</a>
        </div>
    </div>
</div>
</body>
</html>
