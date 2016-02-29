<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value='/static/css/reset.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value='/static/js/bootstrap.js'/>"></script>
    <title>${title}</title>
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
                        <li><a href="record">Записи</a></li>
                        <li><a href="logout">Вихід</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div><!--/.container-fluid -->
        </nav>
    </header>
    <div class="well lead">Запис на відправку</div>
    <form method="post" action="record" enctype="multipart/form-data">

        <%--id_record--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">ID Користувача</span>
                <div class="col-md-7">
                    <input type="text" readonly name="id_record" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <%--number--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">№ документа</span>
                <div class="col-md-7">
                    <input type="text" name="number" class="form-control input-sm">
                </div>
            </div>
        </div>
        <%--date_of_receipt_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Дата надходження</span>
                <div class="col-md-7">
                    <input type="date" name="date_of_receipt_request" class="form-control input-sm">
                </div>
            </div>
        </div>

        <%--correspondent--%>
        <div class=" row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Кореспондент</span>
                <div class="col-md-7">
                    <input type="text" name="correspondent" class="form-control input-sm">
                </div>
            </div>
        </div>
        <%--name_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Назва документа</span>
                <div class="col-md-7">
                    <input type="text" name="name_request" class="form-control input-sm">
                </div>
            </div>
        </div>
        <%--description_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Зміст документа</span>
                <div class="col-md-7">
                    <textarea name="description_request" class="form-control input-sm" style="resize:none" cols="150"
                              rows="8"></textarea>
                </div>
            </div>
        </div>
        <%--file_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Файл документа</span>
                <div class="col-md-7">
                    <input type="file" name="file_request" class="btn btn-info">
                </div>
            </div>
        </div>
        <%--rdo--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">РДО якому передається</span>
                <div class="col-md-7">
                    <select name="rdo" class="form-control input-sm">
                        <c:forEach items="${rdos}" var="rdo">
                            <option value="${rdo.idRdo}" ${rdo.idRdo == user.rdo.idRdo ? "selected" : ""}>${rdo.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">

                <input type="submit" value="Зберегти" class="btn btn-primary btn-sm"/>

                або
                <a class="btn btn-danger btn-sm" href="/record?action=recordList">Відмінити</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
