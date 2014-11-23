<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Recipe</title>
  </head>
  <body>
  <c:forEach items="${recipes}" var="recipe">
    <div class="recipe-link-container" data-imageId="${recipe.id}" onclick="onSidebarLinkClicked(this);">
       <div class="recipe-link-container-link-title"> ${recipe.title}</div>
    </div>
  </c:forEach>
</body>
</html>