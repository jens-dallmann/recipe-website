<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Recipe</title>
  </head>
  <body>
  <div class="dropdown">
    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">Recipes</button>
    <ul class="dropdown-menu pull-right" role="menu">

    <c:forEach items="${recipes}" var="recipe">
    <li role="presentation" data-imageid="${recipe.id}" onclick="onSidebarLinkClicked(this);"><p role="menuitem">${recipe.title}</p></li>
    </c:forEach>
  </ul>
  </div>
</body>
</html>