<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="context" type="de.jd.recipeWebsite.Context"--%>
<%--@elvariable id="context.entity" type="de.jd.entities.Recipe"--%>

<div class="row">
    <div class="col-md-3">
        <c:forEach items="${context.entity.images}" var="image">
            <img src="data:image/png;base64,<c:out value='${image}'/>" class="img-responsive"/>

            <div id="overlay">
                <p class='title'>${context.entity.title}</p>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-9">
        <h1 class="recipe-title">${context.entity.title}</h1>
        <p class="recipe-text">${context.entity.text}</p>
    </div>
</div>

