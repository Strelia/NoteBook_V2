<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <th>ID</th>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${rdos}" var="rdo">
                    <tr>
                        <td>${rdo.idRdo}</td>
                        <td>${rdo.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-lg-10">
            <a class="btn btn-info" href="/rdo?action=insert">Add Rdo</a>
        </div>
    </div>
</div>
</body>
</html>
