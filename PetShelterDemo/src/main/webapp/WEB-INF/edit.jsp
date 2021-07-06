<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit a Pet</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
                integrity="undefined" crossorigin="anonymous">
</head>
<body>
    <h1>Edit Pet</h1>
    <form:form action="/pets/${pet.id}/update" method="post" modelAttribute="pet">
                    <p>
                        <form:label path="name">Name</form:label>
                        <form:errors path="name"/>
                        <form:input path="name"/>
                    </p>
                    <p>
                        <form:label path="age">Age</form:label>
                        <form:errors path="age"/>
                        <form:input type="number" path="age"/>
                    </p>
                    <p>
                        <form:label path="ownerName">Owner Name</form:label>
                        <form:errors path="ownerName"/>
                        <form:input path="ownerName"/>
                    </p>
                    <input type="submit" value="Submit"/>
                </form:form>
</body>
</html>