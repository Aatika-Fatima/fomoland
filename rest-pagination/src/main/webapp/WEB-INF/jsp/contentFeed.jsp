<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="redirectUrl" value="/web/contentFeed" scope="session"/>
<html>
<head>
<title>Fomoland | Feed</title>
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
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="/css/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="/css/animated_delays.css">
<!-- js links -->
<script type="text/javascript" src="/js/init.js"></script>
</head>
<body>
<c:set var="header" scope="session" value="/web/contentFeed"/>
	<!-- navbar -->
	<nav class="indigo">
		<div class="row container">
			<div class="col s6 m3">
				<!-- toggle switches tab -->
				<div class="row">
					<div
						class="col s6 center indigo darken-2 btn_toggle z-depth-1 waves-effect waves-light">
						<a href="#">READ</a>
					</div>
					<div class="col s6 center waves-effect waves-light">
						<a href="#">SHOP</a>
					</div>
				</div>
			</div>
			<div class="col s6 m6">
				<!-- points display -->
				<span class="right yellow-text" style="padding: 0 5px;">${requestScope.userTotalPoints}<span
					class="white-text">POINTS</span></span>
			</div>
		</div>
	</nav>
	<div class="after_nav">
		<!-- read and shop tabs -->
		<div id="read_div">
			<!-- image here -->
			<div class="feed-img center">
				<!-- <img src="/img/placeholder.svg"> -->
				<img src="${requestScope.contentFeed.imageUrl}">
			</div>
			<!-- article here -->
			<div class="container">
				<!-- article contains only 150 characters -->
				<p>
					${requestScope.contentShortDescription} <br>
					<!-- Read full story link for pop up -->
					<a href="#story" class="modal-trigger">Read full story >></a>
				</p>
				<!-- modal with full story of article -->
				<div class="modal modal-fixed-footer" id="story">
					<!-- article written over here, the full story article or full article -->
					<div class="modal-content flow-text">
						<h6 class="indigo white-text">Article heading</h6>
						${requestScope.contentFeed.description}
					</div>
					<!-- close button for modal -->
					<div class="modal-footer">
						<button
							class="modal-action modal-close waves-effect waves-red btn-flat ">Close</button>
					</div>
				</div>

				<!-- social share options checklist -->
				<div class="row" id="share_on_media">

					<div class="col s6 m6">Choose friends :</div>
					<div class="col s6 m6">
						<a href="#edit_text" class="modal-trigger right">Edit text</a>
						<!-- edit text modal option-->
						<div id="edit_text" class="modal">
							<div class="modal-content">
								<!-- textarea to edit text -->
								<div class="input-field">
									<textarea id="edit_text_field" class="materialize-textarea"></textarea>
									<label for="edit_text_field">Edit share text</label>
								</div>
							</div>
							<div class="modal-footer">
								<button
									class="btn-flat modal-action modal-close waves-effect waves-green"
									onclick="Materialize.toast('Text edited', 4000)">Done</button>
							</div>
						</div>
					</div>
		
						
					<div class="col s4 m6">
						<p>
							<input type="checkbox" id="share_facebook" name="facebook"
								value="${requestScope.facebook.points}" />
							<label for="share_facebook">Facebook</label>
						</p>
					</div>
					<div class="col s4 m6">
						<p>
							<input type="checkbox" id="share_whatsapp" name="whatsapp"
								value="${requestScope.whatsapp.points}" />
							<label for="share_whatsapp">Whatsapp</label>
						</p>
					</div>
		<%-- 			<div class="col s4 m6">
						<p>
							<input type="checkbox" id="share_facebook"  name="facebook"
								value="${requestScope.facebook.points}" />
							<label for="share_fb">Facebook</label>
						</p>
					</div> --%>
					<div class="col s4 m6">
						<p>
							<input type="checkbox" id="share_twitter" name="twitter"
								value="${requestScope.twitter.points}" />
							<label for="share_twitter">Twitter</label>
						</p>
					</div>
					<div class="col s4 m6">
						<p>
							<input type="checkbox" id="share_pinerest" name="pinterest"
								value="${requestScope.pinterest.points}" />
							<label for="share_pinerest">Pinerest</label>
						</p>
					</div>
					<div class="col s4 m6">
						<p>
							<input type="checkbox" id="share_instagram" name="instagram"
								value="${requestScope.instagram.points}" />
							<label for="share_instagram">Instagram</label>
						</p>
					</div>
					<div class="col s4 m6">
						<p>
							<input type="checkbox" id="share_linkedin" name="linkedin"
								value="${requestScope.linkedin.points}" />
							<label for="share_linkedin">LinkedIn</label>
						</p>
					</div>

				</div>


			</div>
		</div>
	</div>
	<!-- share button -->
	<!-- FAB next button-->
	<div class="fab-cust center">
	<div class="row">
			<!-- swipe left -->
			<div class="col s2">
				<!-- this is diabled now -->
				<!-- remove the disabled class from the class attribute in the tag to enable the button -->
				<a id="prev" href="#"><i class="ion-ios-arrow-thin-left btn btn-floating indigo white-text  waves-effect waves-light"></i></a>
			</div>
			<div class="col s8">
<!-- 				<button id="decision_btn" class="disabled btn btn-large-round btn-large red white-text waves-effect waves-light" onclick="Materialize.toast('You got xxxx points', 4000)">SHARE AND EARN xxxx POINTS</button>
 -->			
 					<button id="share_on_media_btn"
					class="btn btn-large-round btn-large red white-text waves-effect waves-light"
					onclick="shareOnFacebook()">
					SHARE AND EARN
					<label class="white-text" id="points"></label>
					POINTS
					</button>
 			</div>
			<!-- swipe right -->
			<div class="col s2">
				<a id="next" href="#"><i class="ion-ios-arrow-thin-right btn btn-floating indigo white-text waves-effect waves-light"></i></a>
			</div>
		</div>
<!-- 		<button id="share_on_media_btn"
			class="btn btn-large-round btn-large red white-text waves-effect waves-light"
			onclick="shareOnFacebook()">
			SHARE AND EARN
			<label class="white-text" id="points"></label>
			POINTS
		</button> -->
	</div>
</body>
<script type="text/javascript">
	var points = 0;
	$(function() {
		$(':checkbox').change(function() {
			if ($(this).is(':checked')) {

				points = parseInt(points) + parseInt($(this).val());
				alert(points);
				$('#points').html(points);
			} else {
				points = parseInt(points) - parseInt($(this).val());
				alert(points);
				$('#points').html(points);
			}
		});

	});

	function shareOnFacebook() {

		alert('check box data');
		var names = [];
		$('#share_on_media input:checked').each(function() {
			names.push(this.name);
		});
		// alert(names);
		names
				.forEach(function(providerId) {
					alert(providerId);
					var url = '/web/' + providerId
							+ '/share';
					$.ajax({
						url : url,
						headers:{'cachedUrl':location.pathname},
						type : 'POST',
						success : function(result) {
							alert('done-->' + result);
							window.location.reload();
							$("input[name='" + providerId + "']").prop(
									"disabled", true);
						},
						statusCode : {
							308 : function(result) {
								var form = $(document.createElement('form'));
								$(form).attr("action", "/signin/"+providerId);
								$(form).attr("method", "POST");
								form.appendTo(document.body)
								$(form).submit();
								
							},
							226:function(result){
								alert("u hav earned "+ result.facebook.points);
								alert("saved 226");
							},
							404:function(result){
								alert('redirect from twitter');
							}
						},
						failure : function(xhr) {
							alert(xhr.responseText);
						}
					});
				});
 
	}
 

	function shareAndUpdatePoints() {
		$(':checkbox').change(function() {
			if ($(this).is(':checked')) {

				points = parseInt(points) + parseInt($(this).val());
				alert(points);
				$('#points').html(points);
			} else {
				points = parseInt(points) - parseInt($(this).val());
				alert(points);
				$('#points').html(points);
			}
		});
	}
</script>

<script>

 	var count = 1;
	$(document).ready(function() {
		$("#prev").click(function(event) {
			event.preventDefault();
			alert('PREV clicked');
			alert($(this).attr("href"));
			$.ajax({
		 		type:'GET',
		 		url:$(this).attr("href"),
		 		success:function(result){
		 			procesLinks(result);
		 			$.each(result.content,function(key,value){
		 				 alert(key + " " + value.id + " "+ value.contactName + " " + value.contactNumber);
 		 			});
 		 			
		 		}
		 			
		 	});
		});
		$("#next").click(function(event) {
			event.preventDefault();
			alert('NEXT clicked');
			alert($(this).attr("href"));
			$.ajax({
		 		type:'GET',
		 		url:$(this).attr("href"),
		 		success:function(result){
		 			procesLinks(result);
		 			$.each(result.content,function(key,value){
		 				 alert(key + " " + value.id + " "+ value.contactName + " " + value.contactNumber);
 		 			});
 		 		}
			
		 			
		 	});
		});
		if(count == 1){
			count=2;
		 	$.ajax({
				type:'GET',
				url:"https://localhost:9000/contacts?page=0&size=1",
				success:function(result){
					procesLinks(result);
					$.each(result.content,function(key,value){
						 alert(key + " " + value.id + " "+ value.contactName + " " + value.contactNumber);
					});
				}
					
			});
		}
	
		 	function procesLinks(result){
		 		var baseUrl="https://localhost:9000/contacts?page=";
 		 		 
		 		var currentPage = result.number;
		 		alert('Current Page = ' + currentPage);
				var nextPage;
				var prevPage;
				if(result.first == true && result.last==false){
					nextPage = currentPage + 1;
				//	baseUrl = baseUrl+nextPage+"&size=1";
 					$("#prev").hide(); 
					$("#next").show();
					$("#next").attr("href",baseUrl+nextPage+"&size=1");			
				} 
				
				else if(result.first == false && result.last==false) {
					$("#prev").show();
					prevPage = parseInt(currentPage) -1;
					//baseUrl =baseUrl+prevPage+"&size=1";
					alert("Case 2 --\nPrev Page = " +prevPage +"\n"+baseUrl+prevPage+"&size=1");
					$("#prev").attr("href", baseUrl+prevPage+"&size=1");
					$("#next").show();
					nextPage = parseInt(currentPage) + 1;
					//baseUrl = baseUrl+nextPage+"&size=1";
					$("#next").attr("href", baseUrl+nextPage+"&size=1");
					alert("Case 2 --\nNext Page = " +nextPage +"\n"+baseUrl+nextPage+"&size=1");

				}

				else if(result.first == true && result.last==true) {
					alert("Case 3 --\nPrev Page = " +prevPage +"\n"+baseUrl);
					$("#prev").hide();
					$("prev").attr("href", window.location.href);
					$("#next").hide();
					$("#prev").attr("href", window.location.href);

				}

				
				else if(result.first == false && result.last==true) {
					$("#prev").show();
					prevPage = parseInt(currentPage) -1;
					alert("Case 4 --\nPrev Page = " +prevPage +"\n"+baseUrl+prevPage+"&size=1");
					$("#prev").attr("href", baseUrl+prevPage+"&size=1");
					$("#next").hide();
					$("#next").attr("href",window.location.href);

				}
				
		 	}
			
 
		
		
	});
</script>
</html>