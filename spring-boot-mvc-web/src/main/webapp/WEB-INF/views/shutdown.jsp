<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<legend>
	<spring:message code="login.legend" />
</legend>


<c:url var="shutdown" value="http://localhost:9091/manage/shutdown" />

	<form action="${shutdown}" class="form-signin">
		<button class="btn btn-lg btn-primary" type="submit">shutdown</button>
	
		<a class="btn btn-default" href='${pageContext.request.contextPath}'
			role="button"><spring:message code="login.button.cancel" /></a> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

<legend> </legend>