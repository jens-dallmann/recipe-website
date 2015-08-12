<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="recipe" type="de.jd.entities.RecipeImpl"--%>
<div class="row">
    <div class="col-md-3">
        <c:forEach items="${recipe.images}" var="image">
            <img src="data:image/png;base64,<c:out value='${image}'/>" class="img-responsive"/>

            <div id="overlay">
                <p class='title'>${recipe.title}</p>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-9">
        <p>${recipe.title}</p>
        <p>${recipe.text}</p>
    </div>
</div>

