<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="content">
		<h3>Your Facebook Profile</h3>
		<p>Hello, ${profile.firstName}!</p>
		<dl>
			<dt>Facebook ID:</dt>
			<dd>${profile.id }</dd>
			<dt>Name:</dt>
			<dd>${profile.name}</dd>
			<dt>Email:</dt>
			<dd>${profile.email}</dd>
		</dl>


	</div>

	<form id="disconnect" method="post" action="/connect/facebook">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div class="formInfo">
			<p>Spring Social Showcase is connected to your Facebook account.
				Click the button if you wish to disconnect.</p>
		</div>
		<button type="submit">Disconnect</button>
		<input type="hidden" name="_method" value="delete" />
	</form>
	
		<form id="disconnect" method="post" action="/facebook/feed">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div class="formInfo">
			<p>Spring Social Showcase is connected to your Facebook account.
				Click the button if you wish to disconnect.</p>
		</div>
		<button type="submit">POST</button>
 	</form>
</body>
</html>