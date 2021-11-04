<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Something went wrong</title>

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

<% 
	String error = "";

	if(request.getAttribute("error") != null){
		error = request.getAttribute("error").toString();
	}
%>

<div class="home-page">

        <%@ include file="header.jsp" %>

        <div class="container">
        	<h1 style="color:red"> <%= error %></h1>            
        </div>

</div>


<script>
    getCurrentTemp();
</script>
</body>
</html>
