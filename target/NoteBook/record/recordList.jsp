<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value='/static/css/reset.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value='/static/js/bootstrap.js'/>"></script>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row marketing">
        <div class="col-lg-10">
            <table class="table">
                <thead>
                <tr>
                    <th>№</th>
                    <th>Кореспондент</th>
                    <th>Дада надходження</th>
                    <th>Ім’я документа</th>
                    <th>Опис документа</th>
                    <th>РДО</th>
                    <th>Дата відправлення</th>
                    <th>Дата початку роботи</th>
                    <th>Почта відпраляючого</th>
                    <th>Статус документа (прийняття)</th>
                    <th>Змінити</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${records}" var="record">
                    <tr class="${record.recordRead == false? "active":""} ${record.recordStatus == true && record.recordRead == true?
                    "success" : "danger"}">
                        <td>${record.number}</td>
                        <td>${record.correspondent}</td>
                        <td><fmt:formatDate type="DATE" pattern="yyyy-MM-dd" value="${record.dateOfReceiptRequest}"/></td>
                        <td>${record.correspondent}</td>
                        <td>${record.nameRequest}</td>
                        <td>${fn:substring(records.descriptionRequest,0,15)}</td>
                        <td>${record.rdo.name}</td>
                        <td><fmt:formatDate type="DATE" pattern="yyyy-MM-dd" value="${record.dateSent}"/></td>
                        <td><fmt:formatDate type="DATE" pattern="yyyy-MM-dd" value="${record.dateWork}"/></td>
                        <td>${record.userSender.email}</td>
                        <td>${record.recordStatus == true && record.recordRead == true?
                                "Прийняли" : "Відмовили"}"
                        </td>
                        <td><a class="btn btn-info"
                               href="/record?action=request&recordId=<c:out value="${record.recordId}"/>"> </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-lg-10">
            <a class="btn btn-info" href="/record?action=addNewRecord">Add record</a>
        </div>
    </div>
</div>
</body>
</html>
