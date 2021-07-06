<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Languages Home</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
                integrity="undefined" crossorigin="anonymous">
</head>
<body>
    <table class="table table-dark">
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Creator</th>
                <th scope="col">Version</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${allLanguages}" var="language">
            <tr>
                <td><a href="/languages/${language.id}">${language.name}</a></td>
                <td>${language.creator}</td>
                <td>${language.version}</td>
                <td><a href="/languages/${language.id}/edit">Edit</a>|| <a href="/languages/${language.id}/delete">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <div>
        <form:form action="/languages/create" method="post" modelAttribute="language">
            <p>
                <form:label path="name">Name</form:label>
                <form:errors path="name"/>
                <form:input path="name"/>
            </p>
            <p>
                <form:label path="creator">Creator</form:label>
                <form:errors path="creator"/>
                <form:input path="creator"/>
            </p>
            <p>
                <form:label path="version">Version</form:label>
                <form:errors path="version"/>
                <form:input  path="version"/>
            </p>
            <input type="submit" value="Submit"/>
        </form:form>
    </div>

</body>
</html>