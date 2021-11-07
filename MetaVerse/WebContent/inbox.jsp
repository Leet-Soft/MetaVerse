<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Weather App</title>

    <meta name="apple-mobile-web-app-capable" content="yes">

    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/main.css" rel="stylesheet">

    <script src="js/vendor/jquery/jquery-1.12.1.min.js"></script>
    <script src="js/vendor/bootstrap/bootstrap.min.js"></script>
    <script src="js/main.js"></script>


</head>
<body>

<div class="inbox-page">

    	<% session.setAttribute("currentLocation", "inbox"); %>
		
        <%@ include file="header.jsp" %>	


        <div class="container">


            <div class="row" style="margin-top:30px;">

                <div class="col-sm-12">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Известия</h3>
                        </div>

                        <ul class="list-group" style="min-height:241px;">

                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-sm-2 col-xs-3">
                                        <img class="img-thumbnail" src="assets/img/user.jpg">                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <h4>Иван</h4>
                                        <p>Иска да бъдете приятели</p>
                                    </div>
                                    <div class="col-sm-3 col-xs-12">
                                        <button type="button" class="btn btn-danger pull-right remove-post"><span class="glyphicon glyphicon-remove"></span> Откажи</button>
                                        <button type="button" class="btn btn-primary pull-right publish"><span class="glyphicon glyphicon-plus"></span> Приеми</button>
                                    </div>
                                </div>
                            </li>

                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-sm-2 col-xs-3">
                                        <img class="img-thumbnail" src="assets/img/user.jpg">                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <h4>Иван</h4>
                                        <p>Иска да бъдете приятели</p>
                                    </div>
                                    <div class="col-sm-3 col-xs-12">
                                        <button type="button" class="btn btn-danger pull-right remove-post"><span class="glyphicon glyphicon-remove"></span> Откажи</button>
                                        <button type="button" class="btn btn-primary pull-right publish"><span class="glyphicon glyphicon-plus"></span> Приеми</button>
                                    </div>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>

            </div>
        </div>

</div>


<script>
    getCurrentTemp();
</script>
</body>
</html>
