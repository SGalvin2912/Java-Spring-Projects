<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Idea Dashboard</title>
</head>
<body>
    <div class="container">
        <div class="jumbotron m-5">
            <div class="d-flex justify-content-between">
                <h1>Welcome ${loggedInUser.name}</h1>
                <a href="/logout">Logout</a>
            </div>
        </div>
        <div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Idea</th>
                        <th scope="col">Created By</th>
                        <th scope="col">Likes</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${allIdeaItems}" var="i">
                    <tr>
                        <td><a href="/idea/${i.id}/info">${i.name}</a></td>
                        <td>${i.uploader.name}</td>
                        <td>${i.likeCount()}</td>
                        <td><a href="/addUserToIdea/${i.id}">Like</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <a href="/idea/new">Create an Idea</a>
        </div>
    </div>
</body>
</html>