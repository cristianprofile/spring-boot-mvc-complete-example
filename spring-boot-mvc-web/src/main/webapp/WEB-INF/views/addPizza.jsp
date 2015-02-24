<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div id="content">	
	
	
		<form:form method="post" action="addPizza" modelAttribute="pizza"
			role="form" class="form-horizontal">
			<div class="form-group">
	
				
				<form:label for="name" path="name">Name</form:label>
				<form:input path="name" placeholder="Enter name" />
				<font color='red'><form:errors path='name' /></font>
				
				
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-success btn-large">Submit</button>
			</div>


		</form:form>
	
</div>
