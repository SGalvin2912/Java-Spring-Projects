<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="css/date.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="dateAlert()">
	<h1>Date</h1>
	<p><fmt:formatDate type = "date" value = "${date}" /></p>
<script src="js/script.js"></script>
</body>
</html>