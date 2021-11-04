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

<div class="login-page">
    <header>
        <div class="container">
            <nav class="navbar-default">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <img src="assets/img/logo.png"/>
                            <h1>Weather App</h1>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </header>

    <div class="container">
        <div class="row header-info">
            <div class="col-md-12">
                <div class="basic-panel header-info">
                    <h2>Социална мрежа</h2>
                    <p>Намерете своите приятели. Отбелязвайте местата, които посещавате и запазвайте вашите впечатления.</p>
                </div>
            </div>
        </div>
        <div class="row login-container">
            <div class="col-md-7">
                <div class="basic-panel hidden-sm hidden-xs">
                    <img class="img-responsive " src="assets/img/outdoor.jpg"/>
                </div>
            </div>
            <div class="col-md-5">
                <form class="basic-panel" action="HelloWorldServlet" method="POST">
                    <div class="form-group">
                        <label for="username">Потребител</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Потребител">
                        </div>
                        <p class="help-block">Полето "потребител" е празно</p>
                    </div>
                    <div class="form-group">
                        <label for="password">Парола</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Парола">
                        </div>
                        <p class="help-block">Полето "парола" е празно</p>
                    </div>
                    <input type="hidden" name="action" value="login">
                    <button id="login" type="submit" class="btn btn-primary">Влез</button>
                    <button id="register" type="button" class="btn btn-success pull-right">Регистрация</button>
                </form>
            </div>
        </div>
    </div>
    <div id="register-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Регистрация на нов потребител</h4>
                </div>
                <div class="modal-body">

                    <form id="register-form" action="HelloWorldServlet" method="post">
                        <div class="form-group">
                            <label for="register-user">Потребител</label>
                            <input type="text" class="form-control" id="register-user" name="register-user" placeholder="Потребител">
                            <p class="help-block">Полето "потребител" е празно</p>
                        </div>
                        <div class="form-group">
                            <label for="register-email">Email</label>
                            <input type="email" class="form-control" id="register-email" name="register-email" placeholder="example@gmail.com">
                            <p class="help-block">Полето "Email" е празно</p>
                        </div>
                        <div class="form-group">
                            <label for="register-pass">Парола</label>
                            <input type="password" class="form-control" id="register-pass" name="register-password" placeholder="Парола">
                            <p class="help-block">Полето "парола" е празно</p>
                        </div>
                        <div class="form-group">
                            <label for="confirm-register-pass">Повтори паролата</label>
                            <input type="password" class="form-control" id="confirm-register-pass" name="confirm-register-pass" placeholder="Парола">
                            <p class="help-block">Полето "парола" е празно</p>
                        </div>
                        <input type="hidden" name="action" value="register" />
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Затвори</button>
                    <button type="submit" form="register-form"  class="btn btn-success">Регистрирай</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>




<script>
    bindEvents();
</script>
</body>
</html>
