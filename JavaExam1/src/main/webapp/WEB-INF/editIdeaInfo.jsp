<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
            <meta charset="UTF-8">
            <title>Edit Menu Item</title>
        </head>

        <body>
            <div class="container">
                <div class="m-5">
                    <h1>Edit ${ideaObj.name}</h1>
                </div>
                <div class="m-5">
                    <form:form action="/idea/${ideaObj.id}/update" method="post" modelAttribute="ideaObj">
                        <p>
                            <form:label path="name">Content:</form:label>
                            <form:errors path="name" />
                            <form:input path="name" />
                        </p>
                        <br/>
                        <input type="submit" value="Update" />
                    </form:form>
                    <br/><br/>
                    <a href="/idea/${ideaObj.id}/delete">Delete</a>
                </div>
            </div>
        </body>

        </html>