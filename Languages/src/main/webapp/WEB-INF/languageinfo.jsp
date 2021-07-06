<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <a href="/languages">Dashboard</a>
    <br><br>
    <p>${languageToShow.name}</p>
    <p>${languageToShow.creator}</p>
    <p>${languageToShow.version}</p>
    <a href="/languages/${languageToShow.id}/edit">Edit</a>
    <a href="/languages/${languageToShow.id}/delete">Delete</a>
</body>
</html>