<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Handing Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>

    <link rel="stylesheet" type="text/css" href="/recipe-editor/resources/css/imagegallery.css">
    <script src="/recipe-editor/resources/js/jd.de.imagegallery.js"></script>
</head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
	<h1>Form</h1>
    <form:form method="POST" action="add" commandName="recipe-entity">
        <table>
            <tr>
                <td>Title:</td>
                <td><input type="text" id="recipeTitle"/></td>
            </tr>
            <tr>
                <td>Bild</td>
                <td><input type="file" id="gallery-upload"></td>
                <td><ul id="imagegallery"> </ul></td>
            </tr>
            <tr>
                <td><input id="submit" type="button" name="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</body>
</html>