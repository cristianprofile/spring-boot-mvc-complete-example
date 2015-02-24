<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<legend>
		<spring:message code="login.legend" />		
	</legend>
	
	<form action="login" method="post" class="form-signin">
	
	     <c:if test="${not empty loginError}">
		     <div class="alert alert-danger">  
	  			<a class="close" data-dismiss="alert">×</a>  
	  			<strong><spring:message code="login.error" /></strong>  
			</div> 
             
        </c:if>
        
        <c:if test="${param.expired != null}"> 
                <div class="alert alert-success"> 
                    sesion expirada!!!!.
                </div>  
          </c:if>
	
		<label class="sr-only"><spring:message code="login.user" /> </label>
        <input type="email" name="username" class="form-control" placeholder='<spring:message code="login.user" />' required autofocus>
		
		<label class="sr-only"><spring:message code="login.password" /></label>
        <input name="password"  type="password" id="inputPassword" class="form-control" placeholder='<spring:message code="login.password" />' required>
		
        <div class="checkbox">
          <label>
            <input type="checkbox" id="remember-me" value="remember-me"> <spring:message code="login.rememberme" />
          </label>
        </div>

       <button class="btn btn-lg btn-primary" type="submit"><spring:message code="login.button.singin" /></button>
       
       <a class="btn btn-default" href='${pageContext.request.contextPath}' role="button"><spring:message code="login.button.cancel" /></a>
        
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	
	<legend>		
	</legend>