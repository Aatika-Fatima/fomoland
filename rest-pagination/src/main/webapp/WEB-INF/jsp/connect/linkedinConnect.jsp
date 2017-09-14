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
			<h3>Connect to LinkedIn</h3>
			
			<form  action="/connect/linkedin"method="POST">
				<input type="hidden" name="_csrf"  value="${_csrf.token}" />
				<input type="hidden" name="scope" value="r_basicprofile" />
				<div class="formInfo">
					<p>
						You haven't created any connections with LinkedIn yet. Click the button to connect Spring Social Showcase with your LinkedIn account. 
						(You'll be redirected to LinkedIn where you'll be asked to authorize the connection.)
					</p>
				</div>
				<p><button type="submit">Connect with LinkedIn</button></p>
			</form>
		</div>		
</body>
</html>