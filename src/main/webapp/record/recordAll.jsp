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
                        <c:choose>
                            <c:when test="${userRole == 'admin'}">
                                <li><a href="user">Користувачі</a></li>
                                <li><a href="rdo">РДО</a></li>
                            </c:when>
                        </c:choose>
                        <li><a href="record">Записи</a></li>
                        <li><a href="logout">Вихід</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div><!--/.container-fluid -->
        </nav>
    </header>


    <div class="well lead">Перегляд запису</div>
    <form method="post" action="/record">
        <%--number--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">№ документа</span>
                <div class="col-md-7">
                    <p class="text-muted">${record.number}</p>
                </div>
            </div>
        </div>
        <%--date_of_receipt_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Дата надходження</span>
                <div class="col-md-7">
                    <p class="text-muted"><fmt:formatDate type="date" pattern="yyyy-MM-dd"
                                                          value="${record.dateOfReceiptOfRequest}"/></p>
                </div>
            </div>
        </div>

        <%--correspondent--%>
        <div class=" row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Кореспондент</span>
                <div class="col-md-7">
                    <p class="text-muted">${record.correspondent}</p>
                </div>
            </div>
        </div>
        <%--name_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Назва документа</span>
                <div class="col-md-7">
                    <p class="text-muted">${record.nameRequest}</p>
                </div>
            </div>
        </div>
        <%--description_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Зміст документа</span>
                <div class="col-md-7">
                    <p class="text-muted">${record.descriptionRequest}</p>
                </div>
            </div>
        </div>
        <%--file_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Файл документа</span>
                <div class="col-md-7">
                    <a class="btn btn-default btn-lg active" target="_blank"
                       href="${record.fileRequest}">${record.nameRequest}</a>
                </div>
            </div>
        </div>
        <%--rdo--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">РДО якому передається</span>
                <div class="col-md-7">
                    <p class="text-muted">${record.rdo.name}</p>
                </div>
            </div>
        </div>

        <%--date_sent--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Дата передачі</span>
                <div class="col-md-7">
                    <p class="text-muted"><fmt:formatDate type="date" pattern="yyyy-MM-dd"
                                                          value="${record.dateSent}"/></p>
                </div>
            </div>
        </div>
        <%--user_sender--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Почта відпраляючого</span>
                <div class="col-md-7">
                    <p class="text-muted">${record.userSender.email}</p>
                </div>
            </div>
        </div>

        <%--date_work--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Дата роботи</span>
                <div class="col-md-7">
                    <p class="text-muted"><fmt:formatDate type="date" pattern="yyyy-MM-dd"
                                                          value="${record.dateWork}"/></p>
                </div>
            </div>
        </div>

        <%--record_status--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Статус прийняття</span>
                <div class="col-md-7">
                    <p class="text-muted">
                        <c:choose>
                            <c:when test="${record.recordStatus == true && record.recordRead == true}">
                                Прийняли
                                <br/>
                            </c:when>
                            <c:when test="${record.recordStatus == false && record.recordRead == true}">
                                Відмовили
                                <br/>
                            </c:when>
                            <c:otherwise>
                                -
                                <br/>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>
        </div>

        <%--file_answer--%>
        <c:choose>
            <c:when test="${record.fileAnswer != null}">
                <div class="row" id="file_answer">
                    <div class="form-group col-md-12">
                        <span class="col-md-3 control-lable">Файл відмова</span>
                        <div class="col-md-7">
                            <a class="btn btn-default btn-lg active" target="_blank"
                               href="${record.fileAnswer}">${record.fileAnswer}</a>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <div class="row">
            <div class="form-actions floatRight">
                <a class="btn btn-danger btn-sm" href="record?action=recordList">Повернутися назад</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
