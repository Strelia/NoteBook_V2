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
    <div class="well lead">Запис на прийом</div>
    <form method="post" action="/record">
        <%--number--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">№ документа</span>
                <div class="col-md-7">
                    ${record.number}
                </div>
            </div>
        </div>
        <%--date_of_receipt_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Дата надходження</span>
                <div class="col-md-7">
                    <fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${record.dateOfReceiptOfRequest}"/>
                </div>
            </div>
        </div>

        <%--correspondent--%>
        <div class=" row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Кореспондент</span>
                <div class="col-md-7">
                    ${record.correspondent}
                </div>
            </div>
        </div>
        <%--name_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Назва документа</span>
                <div class="col-md-7">
                    ${record.nameRequest}
                </div>
            </div>
        </div>
        <%--description_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Зміст документа</span>
                <div class="col-md-7">
                    ${record.descriptionRequest}
                </div>
            </div>
        </div>
        <%--file_request--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Файл документа</span>
                <div class="col-md-7">
                    <a href="${record.fileRequest}">${fileNameRequest}</a>
                </div>
            </div>
        </div>
        <%--rdo--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">РДО якому передається</span>
                <div class="col-md-7">
                    ${record.rdo.name}
                </div>
            </div>
        </div>

        <%--date_sent--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Дата передачі</span>
                <div class="col-md-7">
                    <fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${record.dateSent}"/>
                </div>
            </div>
        </div>

        <%--date_work--%>
        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Дата передачі</span>
                <div class="col-md-7">
                    <fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${record.dateWork}"/>
                </div>
            </div>
        </div>

        <%--record_status--%>
        <div class="checkbox">
            <label>
                ${record.recordStatus?"Прийняти": "Відмова"}
            </label>
        </div>

        <%--file_answer--%>
        <div class="row" id="file_answer">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">Файл відмова</span>
                <div class="col-md-7">
                    <input type="file" name="file_answer" class="btn btn-info">
                    <a href="${record.fileAnswer}">${fileNameAnswer}</a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <a class="btn btn-danger btn-sm" href="/record?action=recordList">Повернутися назад</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
