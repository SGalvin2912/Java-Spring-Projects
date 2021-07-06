<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
            <meta charset="UTF-8">
            <title>New Menu Item</title>
        </head>

        <body>
            <div class="container">
                <div class="m-5">
                    <h1>Create a new Menu Item</h1>
                </div>
                <div class="m-5">
                    <form:form action="/menu/create" method="post" modelAttribute="menu">
                        <p>
                            <form:label path="name">Item Name:</form:label>
                            <form:errors path="name" />
                            <form:input path="name" />
                        </p>
                        <p>
                            <form:label path="description">Description:</form:label>
                            <form:errors path="description" />
                            <form:textarea path="description" />
                        </p>
                        <br/>
                        <input type="submit" value="Create" />
                    </form:form>
                </div>
            </div>
        </body>

        </html>