<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jd" uri="/resources/linkTag.tld"%>
<%--@elvariable id="navbarCategories" type="java.util.List"--%>


<div>
    <div id="recipe-navbar" role="navigation" class="navbar navbar-default">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
            </button>
        </div>
        <!-- Collection of nav links and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:forEach items="${navbarCategories}" var="category">
                    <li class="dropdown">
                        <a href="<jd:link path="/main/category/${category.id}"/>" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${category.title} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<jd:link path="/main/category/${category.id}"/>">${category.title}</a></li>
                            <li role="separator" class="divider"></li>
                            <c:forEach items="${category.recipes}" var="recipe">
                                <li><a href="<jd:link path="/main/recipe/${recipe.id}"/>">${recipe.title}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

</div>
