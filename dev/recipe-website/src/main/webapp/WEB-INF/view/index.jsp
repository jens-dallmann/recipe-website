<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="jd" uri="/resources/linkTag.tld"%>
<%--@elvariable id="isIncluded" type="java.lang.Boolean"--%>
<html>
<head>
    <title>Recipe</title></head>
<link rel="stylesheet" type="text/css" href="<jd:link path="/css/recipe-website.css"/>">
<link rel="stylesheet" type="text/css" href="<jd:link path="/css/recipe-website-navbar.css"/>">
<link rel="stylesheet" type="text/css" href="<jd:link path="/css/bootstrap.css"/>">

</head>
<body>
<div class="container-fluid">
    <div class="row header-placement">
        <div class="col-md-3 col-lg-3">
            <c:import url="/recipe/header"/>
        </div>
    </div>
    <div class="row recipe-navbar">
        <div class="col-sm-12"><c:import url="/navbar"/></div>
    </div>
    <!-- main section -->
    <div class="row main-placement">
        <c:choose>
            <c:when test="${context != null}">
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <c:if test="${isIncluded}">
                        <c:import url="/main/include/${context}">
                            <c:param name="urlParam" value='${urlParam}' />
                        </c:import>
                    </c:if>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <c:import url="/recipe/"/>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- footer section -->
    <div class="row footer-placement">
        <div class="col-sm-12 col-md-12 col-lg-12"><c:import url="/footer"/></div>
    </div>
</div>
<script src="<jd:link path="/js/jquery.js"/>"></script>
<script src="<jd:link path="/js/bootstrap.js"/>"></script>
</body>
</html>
