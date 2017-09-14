<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div id="content" layout:fragment="content">
			<h3>Your LinkedIn Profile</h3>
			
			<p>Hello, <span th:text="${profile.firstName}">first name</span>!</p>
			<img th:src="${profile.profilePictureUrl}"/>
			<dl>
				<dt>LinkedIn ID:</dt>
				<dd><a th:href="${profile.publicProfileUrl}" target="_blank" th:text="${profile.id}">profile id</a></dd>
				<dt>Email Address:</dt>
				<dd>${profile.emailAddress}</dd>
				<dt>Headline:</dt>
				<dd>${profile.headline}</dd>
				<dt>Industry:</dt>
				<dd>${profile.industry}</dd>
				<dt>Summary:</dt>
				<dd>${profile.summary}</dd>
			</dl>
			
			<form id="disconnect" action="/connect/linkedin" method="post">
				<input type="hidden" name="_csrf" value="${_csrf.token}" />
				<button type="submit">Disconnect from LinkedIn</button>	
				<input type="hidden" name="_method" value="delete" />
			</form>
		</div>
</body>
</html>