<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Recipe</title>
  </head>
  <body>
  <table>
        <c:forEach items="${recipes}" var="recipe">
            <tr class="recipe">
                <td>${recipe.title}</td>
            </tr>
        </c:forEach>
      </table>
  </body>
</html>