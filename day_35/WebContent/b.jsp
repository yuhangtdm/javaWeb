<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h4>BBB PAGE</h4>
	
	<a href="<%= response.encodeURL("c.jsp") %>">To C Page</a>
	
	<br><br>
	currentTime: <%= new Date() %>
	<br><br>
	sessionTime: <%= session.getAttribute("time") %>
	
</body>
</html>