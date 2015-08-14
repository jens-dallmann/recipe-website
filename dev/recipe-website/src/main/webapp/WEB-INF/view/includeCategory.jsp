<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="context" type="de.jd.recipeWebsite.Context"--%>
<%--@elvariable id="context.entity" type="de.jd.entities.Category"--%>

<div>
    <h1 class="includeCategory-title">${context.entity.title}</h1>
    <p class="includeCategory-text">${context.entity.text}</p>
</div>
