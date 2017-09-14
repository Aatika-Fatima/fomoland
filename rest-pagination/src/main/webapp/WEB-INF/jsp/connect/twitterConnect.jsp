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
	<button type="submit"
		class="btn btn-large btn-round waves-effect white indigo-text"
		onclick="twitterSignin()">
		<i class="ion-social-twitter"></i>
	</button>
</body>
<script>

	function twitterSignin(){
		event.preventDefault();
		alert('sign in twitter');
		$.ajax({
			url:'/signin/twitter',
			type:'POST',
			success:function(){
				alert('connected');
				window.location.reload();
			}
		});
	}
</script>
</html>