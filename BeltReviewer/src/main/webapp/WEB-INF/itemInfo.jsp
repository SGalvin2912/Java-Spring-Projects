<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
            <meta charset="UTF-8">
            <title>Menu Item Info</title>
        </head>

        <body>
            <div class="container">
                <div class="m-5">
                    <h1>Details about menu item: ${menuObj.name}</h1>
                </div>
                <div class="m-5">
                    <p>Uploaded by: ${menuObj.uploader.userName}</p>
                    <br/>
                    <p>Description: ${menuObj.description}</p>
                    <br/>
                    <a href="/menu/${menuObj.id}/edit">Edit</a>
                </div>
            </div>
        </body>

        </html>