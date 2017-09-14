<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<title>Fomoland | Play and earn</title>
<!-- meta tags for resposive -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.1/css/materialize.min.css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.1/js/materialize.min.js"></script>
	<!-- css links -->
	<link rel="stylesheet" href="/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="/css/ionicons.min.css">
	<link rel="stylesheet" type="text/css" href="/css/animated_delays.css">
	<!-- js links -->
	<script type="text/javascript" src="/js/init.js"></script>
	<!-- validation js file -->
	<script type="text/javascript" src="/js/validation.js"></script>
</head>
<body>
<!-- navbar -->
	<nav class="indigo">
		<div class="row container">
			<div class="col s6 m3">
				<!-- toggle switches tab -->
				<div class="row">
					<div class="col s6 center indigo darken-2 btn_toggle z-depth-1 waves-effect waves-light">
						<a href="#">READ</a>
					</div>
					<div class="col s6 center waves-effect waves-light">
						<a href="#">SHOP</a>
					</div>
				</div>
			</div>
			<div class="col s6 m6">
				<!-- points display -->
				<span class="right yellow-text" style="padding: 0 5px;">${requestScope.userTotalPoints}<span class="white-text">POINTS</span></span>
			</div>
		</div>
	</nav>
	<div style="clear: both;"></div>
	<div class="container">
		<h5 class="indigo-text">PLAY AND EARN</h5>
		<p>
			<small>100 points for playing. Additional 500 points for correct answer.</small>
		</p>
		<form>
		<!-- questions and quiz div -->
		<div class="card">
			<input type="hidden" name="qid" id="qid" value="${requestScope.question.qid}"/>
			<div class="card-content">
				<!-- quiz question here -->
				<span class="card-title blue-text">${requestScope.question.qText}?</span>
				<div class="divider"></div><br>
				<!-- quiz options here -->
					<div class="val_opt">
					<p>
			      <input type="radio" id="option_1" name="quiz_group" value="${requestScope.question.optA}" />
			      <label for="option_1">${requestScope.question.optA}</label>
			    </p>
			    <p>
			      <input type="radio" id="option_2" name="quiz_group" value="${requestScope.question.optB}"/>
			      <label for="option_2">${requestScope.question.optB}</label>
			    </p>
			    <p>
			      <input type="radio" id="option_3" name="quiz_group" value="${requestScope.question.optC}"/>
			      <label for="option_3">${requestScope.question.optC}</label>
			    </p>
			    <p>
			      <input type="radio" id="option_4" name="quiz_group" value="${requestScope.question.optD}"/>
			      <label for="option_4">${requestScope.question.optD}</label>
			    </p>
					</div>
			</div>
		</div>
		</form>
	</div>
	<!-- submit button for the response -->
	<div class="fab-cust center">
		<button class="trig btn btn-large-round btn-large red white-text waves-effect waves-light" 
		 onclick="validateAnswer()">SUBMIT</button>
		 <!-- 		data-target="quiz_result_display" -->
	</div>
	<!-- modal to display the result of quiz-->
	<div class="modal" id="quiz_result_display">
		<div class="modal-content">
			<h5 class="green-text center animated flipInY delay_0_5s"><i class="ion-ios-checkmark-outline"></i> Correct Answer</h5>
			<p class="center orange-text animated fadeIn delay_0_5s">You win <label id="points"></label> points!</p>
			<!-- social share options checklist -->
				<div class="row animated flipInY delay_1s">

					<div class="col s12 m12">Choose friends :</div>
					<div class="col s6 m6">
						<p>
							<input type="checkbox" id="share_facebook" name="facebook"
								value="${requestScope.facebook.points}" />
							<label for="share_facebook">Facebook</label>
						</p>
					</div>
					<div class="col s6 m6">
						<p>
							<input type="checkbox" id="share_whatsapp" name="whatsapp"
								value="${requestScope.whatsapp.points}" />
							<label for="share_whatsapp">Whatsapp</label>
						</p>
					</div>
					<div class="col s6 m6">
						<p>
							<input type="checkbox" id="share_twitter" name="twitter"
								value="${requestScope.twitter.points}" />
							<label for="share_twitter">Twitter</label>
						</p>
					</div>
					<div class="col s6 m6">
						<p>
							<input type="checkbox" id="share_pinerest" name="pinterest"
								value="${requestScope.pinterest.points}" />
							<label for="share_pinerest">Pinerest</label>
						</p>
					</div>
					<div class="col s6 m6">
							<p>
							<input type="checkbox" id="share_instagram" name="instagram"
								value="${requestScope.instagram.points}" />
							<label for="share_instagram">Instagram</label>
						</p>
					</div>
					<div class="col s6 m6">
						<p>
							<input type="checkbox" id="share_linkedin" name="linkedin"
								value="${requestScope.linkedin.points}" />
							<label for="share_linkedin">LinkedIn</label>
						</p>
					</div>

				</div>
		</div>
		<div class="modal-footer animated fadeIn delay_0_5s">
			<button class="btn-flat waves-effect waves-light modal-action">Share & earn</button>
			<button class="btn-flat waves-effect waves-light modal-action modal-close">Replay</button>
		</div>
	</div>
</body>
<script>
	function validateAnswer(){
		alert('button clicked');
		var selectedVal = "";
		var selected = $("input[type='radio'][name='quiz_group']:checked");
		if (selected.length > 0) {
		    selectedVal = selected.val();
		}
		alert($('form').serialize());
		$.ajax({
			url:'postQuestion',
			type:'POST',
			data:$('form').serialize(),
 			success:function(result, xhr){
				alert('success...');
				$('#points').html(result);
				window.location.reload();
				$('.modal').modal('show');
			 
				//window.location.href="connect";
			}
			
		}); 
	}
</script>
</html>
