<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value='/static/css/reset.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value='/static/js/bootstrap.js'/>"></script>
    <title>Користувач</title>
</head>
<body>
<div class="container">
    <div class="well lead">Користувач</div>
    <form method="post" action="/user">

        <%--idUser--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">ID Користувача</span>
                <div class="col-md-7">
                    <input type="text" readonly name="idUser" class="form-control input-sm"
                           value="<c:out value="${user.idUser}"/>"/>
                </div>
            </div>
        </div>

        <%--firstname--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Прізвище</span>
                <div class="col-md-7">
                    <input type="text" name="firstname" class="form-control input-sm"
                           value="<c:out value="${user.firstname}"/>"/>
                </div>
            </div>
        </div>

        <%--lastname--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Ім’я</span>
                <div class="col-md-7">
                    <input type="text" name="lastname" class="form-control input-sm"
                           value="<c:out value="${user.lastname}"/>"
                            <c:if test="${edit == true}">
                                readonly
                            </c:if>
                    />
                </div>
            </div>
        </div>
        <%--email--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Email</span>
                <div class="col-md-7">
                    <input type="email" name="email" class="form-control input-sm"
                           value="<c:out value="${user.email}"/>"
                            <c:if test="${edit == true}">
                                readonly
                            </c:if>
                    />
                </div>
            </div>
        </div>
        <%--password--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Пароль</span>
                <div class="col-md-7">
                    <input type="text" name="password" class="form-control input-sm"
                           value="<c:out value="${user.password}"/>"/>
                </div>
            </div>
        </div>
        <%--id_rdo--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">РДО</span>
                <div class="col-md-7">
                    <select name="rdo" class="form-control input-sm">
                        <c:forEach items="${rdos}" var="rdo">
                            <option value="${rdo.idRdo}" ${rdo.idRdo == user.rdo.idRdo ? "selected" : ""}>${rdo.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <%--user_role--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Роль</span>
                <div class="col-md-7">
                    <select name="userRole" class="form-control input-sm">
                        <option value="admin" ${"admin" == user.userRole ? "selected" : ""}>Администратор</option>

                        <option value="manager" ${"manager" == user.userRole ? "selected" : ""}>Менеджер</option>

                        <option value="user" ${"user" == user.userRole ? "selected" : ""}>Користувач</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">

                <input type="submit" value="Зберегти" class="btn btn-primary btn-sm"/>

                або
                <a class="btn btn-danger btn-sm" href="/user?action=userList">Відмінити</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
