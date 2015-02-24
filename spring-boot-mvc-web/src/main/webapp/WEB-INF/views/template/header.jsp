<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="header" class="text-center">
	<div class="span10">
		<h3><spring:message code="home.welcome" /> <sec:authentication property="principal.username" /></h3>
		
	</div>
	
	
	
	<div class="span2">
		<a href="?lang=es" class="btn btn-primary">es</a> 
		<a href="?lang=en"class="btn btn-success">en</a>
							 
		<form action="logout" method="post"
				id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div>
					<input type="submit" value="Logout" />
				</div>
		</form>

	</div>
</div>