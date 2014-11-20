<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Handing Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
	<h1>Form</h1>
    <form:form method="POST" action="add" commandName="recipe-entity">
        <table>
            <tr>
                <td><form:label path="id" >Id:</form:label></td>
                <td><form:input path="id"></form:input></td>
            </tr>
            <tr>
                <td><form:label path="title">Title:</form:label></td>
                <td><form:input path="title"></form:input></td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</body>
</html>