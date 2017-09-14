<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>FOMOLAND | Interests</title>
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
	<sec:authorize access="isAuthenticated()">
		<strong><sec:authentication property="principal" var="username"/></strong>
	</sec:authorize>
<input type="hidden" id="username" value="${username}"/> 

	<div class="progress indigo lighten-3">
		<div class="determinate  white animated fadeInLeft delay_1s"
			style="width: 60%;"></div>
	</div>
	<div class="container center">
		<img src="/img/fomo_logo.svg" class="resposive-img animated fadeInUp" />
	</div>
	<div class="container">
		<form>
			<div class="card indigo lighten-3">

				<div class="card-content">
					<!-- interest buttons -->
					<div class="center">
						<span class="card-header white-text">Interests</span>
					</div>
					buttons here
					<!-- 				<div class="float_buttons white">
					<input type="checkbox" id="list_1" />
					<label for="list_1">Buttons</label>
				</div>
				<div class="float_buttons white">
					<input type="checkbox" id="list_2" />
					<label for="list_2">Buttons</label>
				</div>
				<div class="float_buttons white">
					<input type="checkbox" id="list_3" />
					<label for="list_3">Buttons</label>
				</div>
				<div class="float_buttons white">
					<input type="checkbox" id="list_4" />
					<label for="list_4">Buttons</label>
				</div>
				<div class="float_buttons white">
					<input type="checkbox" id="list_5" />
					<label for="list_5">Buttons</label>
				</div>  -->
				</div>
			</div>
		</form>
	</div>
	<!-- FAB next button-->
	<div class="fixed-action-btn">
		<a class="btn-floating btn-large white" onclick="submitInterest()">
			<i class="large ion-ios-fastforward red-text waves-effect waves-red"></i>
		</a>
	</div>
</body>

<script>

	$(function() {
		//alert('atika');
		loadInterests();
	});
	function submitInterest(){
		
/* 		var data = {};
		$('form').serializeArray().map(function(x) {
			data[x.name] = x.value;
		}); */
		
		var username =$("#username").val();
		var url="rest/v1/accounts/"+username+"/intersts";
	//	alert(url);
	//	alert($('form').serialize());
		$.ajax({
			url:url,
			type:'POST',
			data:$('form').serialize(),
			//data:data,
			success:function(result, xhr){
				window.location.href="connect";
			}
			
		});
	}

	function loadInterests() {
		alert('interests');
		$
				.ajax({
					url : '/rest/v1/interests',
					type : 'GET',
					success : function(result) {
					//	alert(result);
						//var jsonData = JSON.parse(result);
						$
								.each(
										result,
										function(i, item) {
											//	alert(i);
											 
											var divTag = "<div class='float_buttons white'>"
													+ "<input type=checkbox id="+item.interestId+" name='intersets' value='"+item.interestName+"'/>"
													+ "<label for="+item.interestId+">"
													+ item.interestName
													+ "</label>" + "</div>";
											$(".card-content").append(divTag);
										//	alert(divTag);
										});
					}
				});

	}
</script>
</html>