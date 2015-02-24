<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div id="content">	
	
	
		<form:form method="post" action="addUser" modelAttribute="user"
			role="form" class="form-horizontal">
			<div class="form-group">
			    <form:label for="user" path="user">User</form:label>
				<form:input path="user" placeholder="Enter user name" />
				<font color='red'><form:errors path='user' /></font>
				
				<form:label for="password" path="user">password</form:label>
				<form:input type="password" path="password" placeholder="Enter user password" />
				<font color='red'><form:errors path='password' /></font>
				
				
				
				
				<form:label for="name" path="name">Name</form:label>
				<form:input path="name" placeholder="Enter name" />
				<font color='red'><form:errors path='name' /></font>
				
				<form:label for="surname" path="surname">Surname</form:label>
				<form:input path="surname" placeholder="Enter surname" />
				<font color='red'><form:errors path='surname' /></font>
				
				<form:label for="rol" path="rol">Rol</form:label>
				<form:input path="rol" />
				<font color='red'><form:errors path='rol' /></font>
                
                <form:label for="accountNonExpired" path="accountNonExpired">accountNonExpired</form:label>  
                <form:checkbox path="accountNonExpired" />
                
                <form:label for="accountNonLocked" path="accountNonLocked">accountNonLocked</form:label>  
                <form:checkbox path="accountNonLocked" />
                
                <form:label for="credentialsNonExpired" path="credentialsNonExpired">credentialsNonExpired</form:label>  
                <form:checkbox path="credentialsNonExpired" />
                 
                 <form:label for="enabled" path="enabled">enabled</form:label>  
                <form:checkbox path="enabled" />
                
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-success btn-large">Submit</button>
			</div>


		</form:form>
	
</div>
