<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>You are Connected to LinkedIn</h3>
	<div class="formInfo">
		<p>Spring Social Showcase is connected to your LinkedIn account.
			Click the button if you wish to disconnect.</p>
	</div>

	<form id="disconnect" method="post" action="/connect/linkedin">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit">Disconnect</button>
		<input type="hidden" name="_method" value="delete" />
	</form>

</body>
</html>