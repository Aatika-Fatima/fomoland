<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html>

<html>
<head>
	<title>FOMOLAND | Social connect</title>
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
		<div class="determinate  white animated fadeInLeft delay_1s" style="width: 90%;"></div>
	</div>
	<div class="container center">
		<img src="/img/fomo_logo.svg" class="resposive-img animated fadeInUp" />
	</div>
	<div class="container">
	<c:forEach items="${providerIds}" var="provider">
		<h4>${provider}</h4>
		<c:if test="${not empty connectionMap[provider]}">
				You are connected to ${provider} as ${connectionMap[provider][0].displayName}
		</c:if>
		<c:if test="${empty connectionMap[provider]}">
			<div>
				<c:choose>
					<c:when test="${provider}=='facebook'"></c:when>
				</c:choose>
				You are not yet connected to ${provider}. Click
				<a href="<spring:url
						value="/connect/${provider}"/>">here</a>
				to connect to ${provider}.
			</div>
		</c:if>
	</c:forEach>
  		<div class="card indigo lighten-3">
			<div class="card-content center">
				<!-- card content -->
				<div class="center"><span class="card-header white-text">PAYMENTS</span></div>
				<button class="btn btn-large btn-round waves-effect white orange-text"><i class="ion-social-facebook"></i></button>
				<button class="btn btn-large btn-round waves-effect white indigo-text"><i class="ion-social-twitter"></i></button>
				<button class="btn btn-large btn-round waves-effect white indigo-text"><i class="ion-social-whatsapp"></i></button>
				<button class="btn btn-large btn-round waves-effect white indigo-text"><i class="ion-social-instagram-outline"></i></button>
				<button class="btn btn-large btn-round waves-effect white indigo-text"><i class="ion-social-pinterest"></i></button>
				<button class="btn btn-large btn-round waves-effect white indigo-text"><i class="ion-social-linkedin"></i></button>
			</div>
		</div>
	</div>
	<!-- FAB next button-->
	<div class="fixed-action-btn">
    <a class="btn-floating btn-large white" href="/web/contentFeed">
      <i class="large ion-ios-fastforward red-text waves-effect waves-red"></i>
    </a>
  </div>
</body>
</html>