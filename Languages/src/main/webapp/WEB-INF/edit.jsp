<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div>
        <a href="/languages/${language.id}/delete">Delete</a>
        <a href="/languages">Dashboard</a>
    </div>
    <div>
        <form:form action="/languages/${language.id}/update" method="post" modelAttribute="language">
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
                        <form:input path="version"/>
                    </p>
                    <input type="submit" value="Submit"/>
        </form:form>
    </div>
</body>
</html>