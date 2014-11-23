<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Recipe</title></head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>
    <link rel="stylesheet" type="text/css" href="/recipe-editor/resources/css/imagegallery.css">
    <link rel="stylesheet" type="text/css" href="/recipe-editor/resources/css/page.css">
    <script src="/recipe-editor/resources/js/jd.de.imagegallery.js"></script>
    <script src="/recipe-editor/resources/js/jd.de.sidebar.js"></script>
    <script src="/recipe-editor/resources/js/jd.de.network.js"></script>
</head>
<body bgcolor="white">

<div id="header"><c:import url="/recipe/recipe-2"/></div>
<div id="sidebar"><c:import url="/sidebar"/></div>
<div id="main"><c:import url="/recipe/recipe-2"/></div>
<div id="footer"><c:import url="/footer"/></div>

</body>
</html>