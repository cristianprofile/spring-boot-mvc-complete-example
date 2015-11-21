<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content">
	<p>
          <c:if test="${param.logout != null}"> 
                <div class="alert alert-success"> 
                    You have been logged out!!!!.
                </div>  
          </c:if>  
          
            <c:if test="${param.expired != null}"> 
                <div class="alert alert-success"> 
                    sesion expirada!!!!.
                </div>  
          </c:if>
          
           <c:if test="${param.invalid != null}"> 
                <div class="alert alert-success">
                    Sesion invalid!!!!.
                </div>  
          </c:if>  
          
            
		<spring:url value="/info" var="pizzasURL" htmlEscape="true" />
		<a href="${pizzasURL}"><spring:message code="home.text" /></a>
	</p>
</div>
