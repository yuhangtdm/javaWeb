<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" import="java.util.Date" %>
<%@ page session="false" isELIgnored="false" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% 
		Date date = new Date();
		DateFormat dateFormat = null;
		
		HttpServletRequest req = null;
		
		//int i = 10 / 0;
	%>
	 
	<form action="hello.jsp" method="get"> 
		
		username: <input type="text" name="username"/>
		<input type="submit" value="Submit"/>
	
	</form>
	
		
</body>
</html>