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
