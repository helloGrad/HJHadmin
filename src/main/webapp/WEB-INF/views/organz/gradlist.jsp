<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/list.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
		<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/list.js"></script>
</head>
<body>

	<c:import url="/WEB-INF/views/include/header.jsp" />
	<div class="container">
	
		<div class="row">
			<c:import url="/WEB-INF/views/include/adminorglist.jsp" />
			
		</div>
		
		<div class="row">
			<c:import url="/WEB-INF/views/include/charlist.jsp" />
		</div>
		
		<hr class="nav-line">
		<div id="fetchList" class="row">
		<c:forEach items="${list }" var="list" varStatus="status">
			<div class="col-md-12">
				<h3>
					<a
						href="${pageContext.servletContext.contextPath }/organz/updateform?no=${list.orgnzNo}&type=${list.orgnzDstnct}">${list.orgnzNm }</a>
				</h3>
				<hr>
			</div>
		</c:forEach>

		</div>


	</div>

	<c:import url="/WEB-INF/views/include/footer.jsp" />
	
</body>
</html>