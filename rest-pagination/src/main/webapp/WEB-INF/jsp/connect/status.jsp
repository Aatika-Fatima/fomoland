<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	 session.setAttribute("redirectUrl", "/connect");
%>

<!DOCTYPE html>

<html>
<head>
<title>FOMOLAND | Social connect</title>
<!-- meta tags for resposive -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.1/css/materialize.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.1/js/materialize.min.js"></script>
<!-- css links -->
<link rel="stylesheet" href="/css/main.css" />
<link rel="stylesheet" type="text/css" href="/css/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="/css/animated_delays.css">
<!-- js links -->
<script type="text/javascript" src="/js/init.js"></script>

</head>
<body class="indigo">
	<div class="progress indigo lighten-3">
		<div class="determinate  white animated fadeInLeft delay_1s"
			style="width: 90%;"></div>
	</div>
	<div class="container center">
		<img src="/img/fomo_logo.svg" class="resposive-img animated fadeInUp" />
	</div>
	<div class="container">

		<div class="card indigo lighten-3">
			<div class="card-content center">
				<!-- card content -->
				<div class="center">
					<span class="card-header white-text">CONNECT TO YOUR CIRCLE</span>
				</div>
 
			 <c:forEach items="${providerIds}" var="provider">
					<c:choose>
						<c:when test="${provider == 'facebook'}">

							<c:if test="${not empty connectionMap[provider]}">
								<button
									class="btn btn-large btn-round waves-effect white orange-text"
									disabled="disabled">
									<i class="ion-social-facebook"></i>
								</button>
							</c:if>
							<c:if test="${empty connectionMap[provider]}">
								<form action="/connect/facebook" method="post">
									<button type="submit"
										class="btn btn-large btn-round waves-effect white orange-text"
										formaction="/connect/facebook">
										<i class="ion-social-facebook"></i>
									</button>
								</form>
							</c:if>
						</c:when>

						<c:when test="${provider == 'twitter'}">

							<c:if test="${not empty connectionMap[provider]}">
								<button class="btn btn-large btn-round  white orange-text"
									disabled="disabled">
									<i class="ion-social-twitter"></i>
								</button>
							</c:if>
							<c:if test="${empty connectionMap[provider]}">
								<form action="/signin/twitter" method="post">
 									<button type="submit" 
										class="btn btn-large btn-round waves-effect white indigo-text">								 >
										<i class="ion-social-twitter"></i>
									</button>
 								</form>
							</c:if>
						</c:when>

						<c:when test="${provider == 'linkedin'}">
							<c:if test="${not empty connectionMap[provider]}">
								<button class="btn btn-large btn-round  white orange-text"
									disabled="disabled">
									<i class="ion-social-linkedin"></i>
								</button>
							</c:if>
							<c:if test="${empty connectionMap[provider]}">
								<form action="/signin/linkedin" method="post">
									<button type="submit"
										class="btn btn-large btn-round waves-effect white indigo-text"
										formaction="/signin/linkedin" formmethod="post">
										<i class="ion-social-linkedin"></i>
									</button>
								</form>

							</c:if>
						</c:when>
						<c:when test="${provider == 'pinterest'}">

							<c:if test="${not empty connectionMap[provider]}">
								<button class="btn btn-large btn-round  white orange-text"
									disabled="disabled">
									<i class="ion-social-pinterest"></i>
								</button>
							</c:if>
							<c:if test="${empty connectionMap[provider]}">
								<form action="/signin/pinterest" method="post">
									<input type="hidden" name="scope" value="read_public write_public" />
									<button type="submit"
										class="btn btn-large btn-round waves-effect white indigo-text">
										<i class="ion-social-pinterest"></i>
									</button>
								</form>

							</c:if>
						</c:when>

					</c:choose>

				</c:forEach> 

			</div>
		</div>
	</div>
	<!-- FAB next button-->
	<div class="fixed-action-btn">
		<a class="btn-floating btn-large white" href="/payments">
			<i class="large ion-ios-fastforward red-text waves-effect waves-red"></i>
		</a>
	</div>
</body>
<script>
$("#twitterBtn").click(function(event){
	event.preventDefault();
	alert('sign in twitter..');
	$.ajax({
		url:'/signin/twitter',
		type:'POST',
		success:function(result){
			alert('connected');
			window.location.reload();
		}
	});
});
	function twitterSignin( ){
  		alert('sign in twitter');
		$.ajax({
			url:'/signin/twitter',
			type:'POST',
			success:function(result){
				alert('connected');
				window.location.reload();
			}
		});
	}
	function pinterestSignin(){
		$.ajax({
			url:'/signin/pinterest',
			type:'POST', 
			data:$('form').serialize(),
			success:function(result){
				alert(result);
			}
		});
	}
</script>
</html>