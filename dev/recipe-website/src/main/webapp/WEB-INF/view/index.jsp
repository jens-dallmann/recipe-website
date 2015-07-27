<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Recipe</title></head>
    <link rel="stylesheet" type="text/css" href="css/recipe-website.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-variables.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-theme.css">
    <script src="js/bootstrap.js"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row header-placement" >
        <div class="col-md-3 col-lg-3">
                <c:import url="/recipe/header"/>
        </div>
        <div class="col-md-9 col-lg-9">
            <!--Recipe Navigation -->
            <c:import url="/sidebar"/>
        </div>
    </div>
    <!-- main section -->
    <div class="row main-placement">
        <div class="col-sm-12 col-md-12 col-lg-12"><c:import url="/recipe/recipe-2"/></div>
    </div>

    <!-- footer section -->
    <div class="row footer-placement">
        <div class="col-sm-12 col-md-12 col-lg-12"><c:import url="/footer"/></div>
    </div>
</div>

<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap/bootstrap.js"></script>
</body>
</html>
