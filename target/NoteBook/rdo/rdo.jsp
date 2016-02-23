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
    <div class="well lead">РДО</div>
    <form method="post" action="/rdo">

        <div class="row">
            <div class="form-group col-md-12">
                <span class="col-md-3 control-lable">ID РДО</span>
                <div class="col-md-7">
                    <input type="text" readonly name="rdoId" class="form-control input-sm"
                           value=""/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="rdoName">Назва РДО</label>
                <div class="col-md-7">
                    <input type="text" name="rdoName" id="rdoName" class="form-control input-sm"
                           value=""/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">

                <input type="submit" value="Додати" class="btn btn-primary btn-sm"/>
                або
                <a class="btn btn-danger btn-sm" href="/rdo?action=rdoList">Відмінити</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
