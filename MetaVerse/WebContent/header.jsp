<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
            <div class="container">
                <nav class="navbar navbar-default">

                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <div class="current-weather">
                                <div class="img-container">
                                    <img src="assets/img/weather_icon.png" style="display:none;"/>
                                </div>
                                <div class="info">
                                    <div class="city">Пловдив</div>
                                    <div id="current-condition"></div>
                                    <div class="pull-right"><span id="current-temperature"></span> ℃</div>
                                </div>
                            </div>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                            
                            	<c:if test='${ currentLocation.equals("home") }'>
                                	<li class="active">
                                </c:if>
                                <c:if test='${ !currentLocation.equals("home") }'>
                                	<li>
                               	</c:if>
                                    <a href="home.jsp">
                                        <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                                        <span class="nav-label">Начало</span>
                                    </a>
                                </li>
                                <c:if test="${ currentLocation.equals('friends') }">
                                	<li class="active">
                                </c:if>
                                <c:if test="${ !currentLocation.equals('friends') }">
                                	<li>
                                </c:if>
                                    <a href="HelloWorldServlet?action=friends">
                                        <i class="fa fa-users" aria-hidden="true"></i>
                                        <span class="nav-label">Приятели</span>
                                    </a>
                                </li>
                                                     
                     			<c:choose>
                                	<c:when test="${currentLocation.equals('inbox') }">
                                		<li class="active">
                                	</c:when>
                                	<c:otherwise>
                                		<li>  
                                	</c:otherwise>
                               	</c:choose>                          
                                    <a href="inbox.jsp">
                                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                        <span class="nav-label">Известия</span>
                                    </a>
                                </li>
     
     							
                               	<li 
                               		<% if (session.getAttribute("currentLocation") != null && 
                               		session.getAttribute("currentLocation").equals("profile")) { %>
                               		
                               			class="active"                              	
                               		
                               		<% } %>
                               	>
          
                                    <a href="profile.jsp">
                                        <i class="fa fa-cogs" aria-hidden="true"></i>
                                        <span class="nav-label">Профил</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="index.jsp">
                                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                        <span class="nav-label">Изход</span>
                                    </a>
                                </li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                </nav>
            </div>
        </header>