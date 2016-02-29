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

    <div class="row marketing">
        <div class="col-lg-10" ${userRole != "manager"?"hidden":""}>
            <a class="btn btn-info" href="record?action=addNewRecord">Add record</a>
        </div>
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
                    <tr class="${record.recordRead == false? "active":""}

                      <c:choose>
                                <c:when test="${record.recordStatus == true && record.recordRead == true}">
                                   success
                                </c:when>
                                <c:when test="${record.recordStatus == false && record.recordRead == true}">
                                   danger
                                </c:when>
                            </c:choose>
                            ">
                        <td>${record.number}</td>
                        <td>${record.correspondent}</td>
                        <td><fmt:formatDate type="DATE" pattern="yyyy-MM-dd"
                                            value="${record.dateOfReceiptOfRequest}"/></td>
                        <td>${record.nameRequest}</td>
                        <td>${fn:substring(record.descriptionRequest,0,15)}</td>
                        <td>${record.rdo.name}</td>
                        <td><fmt:formatDate type="DATE" pattern="yyyy-MM-dd" value="${record.dateSent}"/></td>
                        <td><fmt:formatDate type="DATE" pattern="yyyy-MM-dd" value="${record.dateWork}"/></td>
                        <td>${record.userSender.email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${record.recordStatus == true && record.recordRead == true}">
                                    Прийняли
                                </c:when>
                                <c:when test="${record.recordStatus == false && record.recordRead == true}">
                                    Відмовили
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><a class="btn btn-info"
                               href="record?action=request&idRecord=${record.idRecord}"> </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
