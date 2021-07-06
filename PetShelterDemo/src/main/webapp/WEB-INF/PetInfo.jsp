<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View one pet</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
                integrity="undefined" crossorigin="anonymous">
</head>
<body>
    <h1>Here is info about ${petToShow.name}</h1>
    <p>Pet Id: ${petToShow.id}</p>
    <p>Pet Name: ${petToShow.name}</p>
    <p>Pet age: ${petToShow.age}</p>
    <p>Pet's Owner Name: ${petToShow.ownerName}</p>
</body>
</html>