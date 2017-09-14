<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="../webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="../css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="">DashBoard</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">Link1</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
      				<li><a href="#"><span class="glyphicon glyphicon-user"></span> <sec:authentication property="principal.username" /></a></li>
     				 <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    			</ul>
			</div>
		</div>
	</nav>

	<div class="container">

	 	<H2>Welcome <sec:authentication property="principal.username" /> </H2>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../webjars/jquery/3.1.1/jquery.min.js"></script>
</body>

</html>
