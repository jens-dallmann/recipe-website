<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Recipe</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"> </script>
    <script src="/recipe-editor/resources/js/jd.de.imagegallery.js"></script>
    <link rel="stylesheet" type="text/css" href="/recipe-editor/resources/css/imagegallery.css">

  </head>
  <body>
	<c:forEach items="${images}" var="image">
	<div id="recipe-image-gallery-item">
          <img id="recipe-image-gallery-item" src="<c:out value='${image}'/>"/>
          <div id="overlay">
            <p class='title'>${title}</p>
          </div>
    </div>
    </c:forEach>
  </body>
</html>