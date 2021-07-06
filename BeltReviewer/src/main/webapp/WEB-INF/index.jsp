<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Register/Login</title>
</head>
<body>
    <div class="d-flex justify-content-evenly">
        <div>
            <h2>Register</h2>
            <form:form action="/register" method="post" modelAttribute="newUser">
                <div class="form-group">
                    <label>User Name:</label>
                    <form:input path="userName" class="form-control" />
                    <form:errors path="userName" class="text-danger" />
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <form:input path="email" class="form-control" />
                    <form:errors path="email" class="text-danger" />
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <form:password path="password" class="form-control" />
                    <form:errors path="password" class="text-danger" />
                </div>
                <div class="form-group">
                    <label>Confirm Password:</label>
                    <form:password path="confirm" class="form-control" />
                    <form:errors path="confirm" class="text-danger" />
                </div>
                <input type="submit" value="Register" class="btn btn-primary" />
            </form:form>
        </div>
        <div>
            <h2>Login</h2>
            <form:form action="/login" method="post" modelAttribute="newLogin">
                <div class="form-group">
                    <label>Email:</label>
                    <form:input path="email" class="form-control" />
                    <form:errors path="email" class="text-danger" />
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <form:password path="password" class="form-control" />
                    <form:errors path="password" class="text-danger" />
                </div>
                <input type="submit" value="Login" class="btn btn-success" />
            </form:form>
        </div>
    </div>
</body>
</html>