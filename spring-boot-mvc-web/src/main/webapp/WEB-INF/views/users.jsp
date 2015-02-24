<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>






<div id="content">
	<div id="tableUsers" class=>
		<legend><spring:message code="users.legend"/></legend>
		<table
			class="table table-bordered table-striped table-hover table-condensed">
			<tr>
				<th><spring:message code="users.table.user"/></th>
				<th><spring:message code="users.table.name"/></th>
				<th><spring:message code="users.table.surname"/></th>
				<th><spring:message code="users.table.rol"/></th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.user}" /></td>
					<td><c:out value="${user.name}" /></td> 
					<td><c:out value="${user.surname}" /></td> 
					<td><c:out value="${user.rol}" /></td> 
				</tr>
			</c:forEach>
		</table>
		
		<a id="showButton" class="btn btn-default" href="addUser" >Add User</a>
		
	 </div>
	
	
</div>
