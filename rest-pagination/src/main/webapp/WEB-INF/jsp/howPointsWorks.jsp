<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring - Social</title>
</head>
<body>
<h3>Connections</h3>
Click <a href="<spring:url value='/connect'/>">here</a> to see your Social Network Connections.
</body>
</html> --%>

<!DOCTYPE html>
<html>
<head>
	<title>Fomoland | How point works</title>
	<!-- meta tags for resposive -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.1/css/materialize.min.css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.1/js/materialize.min.js"></script>
	<!-- css links -->
	<link rel="stylesheet" href="/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="/css/ionicons.min.css">
	<link rel="stylesheet" type="text/css" href="/css/animated_delays.css">
	<!-- js links -->
	<script type="text/javascript" src="/js/init.js"></script>
</head>
<body class="indigo">

	<div class="progress indigo lighten-3">
		<div class="determinate  white animated fadeInLeft delay_1s" style="width: 30%;"></div>
	</div>
	<div class="container center">
		<img src="/img/fomo_logo.svg" class="resposive-img animated fadeInUp" />
	</div>
	<div class="container">
		<div class="card indigo lighten-3">
			<div class="card-content white-text">
				<div class="center"><span class="card-header white-text">HOW POINT WORKS</span></div>
				<ul class="collection z-depth-4">
				<li class="collection-item indigo">Earn points while spending time.</li>
				<li class="collection-item indigo">Every action is rewarded with points.</li>
				<li class="collection-item indigo">Use points to enter the FOMO store, the all time flash sale at more crazy discounts!</li>
				<li class="collection-item indigo">You can also get points and get product for free.</li>
				<li class="collection-item indigo">Invite and earn point.</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- FAB next button-->
 	<div class="fixed-action-btn">
    <a class="btn-floating btn-large white" href="/interests">
      <i class="large ion-ios-fastforward red-text waves-effect waves-red"></i>
    </a>
 
  </div>
 </body>
</html>