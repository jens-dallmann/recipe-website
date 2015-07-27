<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
  <c:forEach items="${recipes}" var="recipe">
     <tr>
       <td>${recipe.title}</td>
     </tr>
  </c:forEach>
</table>