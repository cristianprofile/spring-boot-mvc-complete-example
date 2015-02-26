<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="menu">
	<ul class="nav nav-list well">
		<li class="nav-header">
			<spring:url value="/info" var="infoUrl" htmlEscape="true" />
			<a href="${infoUrl}"><spring:message code="menu.home"/></a>
		</li>
		<li class="divider"></li>
		
		<li class="nav-header">
			<spring:url value="/pizzas" var="pizzas" htmlEscape="true" />
			<a href="${pizzas}"><spring:message code="menu.pizzas"/></a>
		</li>
		
		<li class="nav-header">
			<spring:url value="/addPizza" var="addPizza" htmlEscape="true" />
			<a href="${addPizza}"><spring:message code="menu.addPizza"/></a>
		</li>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="nav-header">
				<spring:url value="/users" var="users" htmlEscape="true" />
				<a href="${users}"><spring:message code="menu.users"/></a>
			</li>
			
			<li class="nav-header">
				<spring:url value="/addUser" var="addUser" htmlEscape="true" />
				<a href="${addUser}"><spring:message code="menu.addUser"/></a>
			</li>
		</sec:authorize>
				
	</ul>
</div>