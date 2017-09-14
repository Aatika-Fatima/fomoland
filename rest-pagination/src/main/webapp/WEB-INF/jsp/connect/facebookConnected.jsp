<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Spring Social - Connected to Twitter</title>
</head>
<body>
	<h3>You are Connected to Facebook</h3>
	<div class="formInfo">
		<p>Spring Social Showcase is connected to your Facebook account.
			Click the button if you wish to disconnect.</p>
	</div>

	<form id="disconnect" method="post" action="/connect/facebook">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />

		<button type="submit">Disconnect</button>
		<input type="hidden" name="_method" value="delete" />
	</form>


</body>
</html>