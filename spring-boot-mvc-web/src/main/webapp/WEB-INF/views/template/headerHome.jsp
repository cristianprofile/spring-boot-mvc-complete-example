<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="header" class="text-center">
	<div class="span10">
		<h3><spring:message code="home.welcome" /></h3>
		
	</div>	
	
	<div class="span2">
		<a href="?lang=es" class="btn btn-primary">es</a> 
		<a href="?lang=en"class="btn btn-success">en</a>
							 

	</div>
</div>