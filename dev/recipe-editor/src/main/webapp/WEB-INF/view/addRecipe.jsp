<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Handing Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>
        <link rel="stylesheet" type="text/css" href="/recipe-editor/resources/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="/recipe-editor/resources/css/bootstrap/bootstrap-theme.css">
</head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
	<h1>Form</h1>
    <form:form method="POST" action="add" commandName="recipe-entity" role="form">
        <div class="formGroup">
            <label for="recipeTitle"> Title</label>
            <input type="text" class="form-control" placeholder="Enter Title" id="recipeTitle"/>
        </div>
        <div class="formGroup">
            <label for="gallery-upload">Image Upload</label>
            <input type="file" id="gallery-upload" class="btn">
        </div>
        <div class="formGroup">
            <label for="submit">Submit Recipe</label>
            <input id="submit" type="button" name="submit" class="btn" value="Submit">
        </div>
    </form:form>
    <script src="/recipe-editor/resources/js/jquery.min.js"></script>
    <script src="/recipe-editor/resources/js/bootstrap/bootstrap.js"></script>
</body>
</html>