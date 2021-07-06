<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
            <meta charset="UTF-8">
            <title>Idea Info</title>
        </head>

        <body>
            <div class="container">
                <div class="m-5">
                    <h1>${ideaObj.name}</h1>
                </div>
                <div class="m-5">
                    <p>Created by: ${ideaObj.uploader.name}</p>
                    <br/>
                    <p>Users who liked your idea:</p>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ideaToShow.usersWhoLike}" var="u">
                            <tr>
                                <td value="${u.id}">${u.name}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <a href="/idea/${ideaObj.id}/edit">Edit</a>
                </div>
            </div>
        </body>

        </html>