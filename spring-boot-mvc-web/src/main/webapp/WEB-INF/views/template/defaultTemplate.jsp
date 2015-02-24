<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Spring mvc example</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<tiles:insertAttribute name="header" />
		</div>

		<div class="row-fluid">
			<div class="span2">
				<tiles:insertAttribute name="menu" />
			</div>

			<div class="span10">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div class="row-fluid">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>