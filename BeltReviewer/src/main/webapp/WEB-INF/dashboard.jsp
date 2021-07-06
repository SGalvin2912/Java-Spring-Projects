<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Menu Dashboard</title>
</head>
<body>
    <div class="container">
        <div class="jumbotron m-5">
            <div class="d-flex justify-content-between">
                <h1>Welcome ${loggedInUser.userName}</h1>
                <a href="/logout">Logout</a>
            </div>
        </div>
        <div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">ID #</th>
                        <th scope="col">Menu Item</th>
                        <th scope="col">Uploaded By</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${allMenuItems}" var="m">
                    <tr>
                        <td scope="row">${m.id}</td>
                        <td><a href="/menu/${m.id}/info">${m.name}</a></td>
                        <td>${m.uploader.userName}</td>
                        <td><a href="/menu/${m.id}/edit">Edit</a>|| <a href="/menu/${m.id}/delete">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <a href="/menu/new">Add new Menu Item</a>
        </div>
    </div>
</body>
</html>