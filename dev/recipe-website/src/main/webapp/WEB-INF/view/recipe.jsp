<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:forEach items="${images}" var="image">
	<div class="row">
	    <div class="col-md-3">
          <img src="data:image/png;base64,<c:out value='${image}'/>" class="img-responsive"/>
          <div id="overlay">
            <p class='title'>${title}</p>
          </div>
        </div>

        <div class="col-md-9">
          <p>${title}</p>
        </div>
	</div>
    </c:forEach>