<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="assets/css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="assets/css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />


</head>
<body>
	<nav class="white" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="/RRHH/HomeController" class="brand-logo">RRHH</a>
			<ul class="right hide-on-med-and-down">
				<li><a href="/RRHH/DepartmentController?action=LIST">Departamentos</a></li>
				<li><a href="/RRHH/EmployeeController?action=LIST">Empleados</a></li>
				<li><a href="${pageContext.request.contextPath}/logout.jsp">Cerrar
						Sesión</a></li>
			</ul>

			<a href="#" data-target="nav-mobile" class="sidenav-trigger"><i
				class="material-icons">menu</i></a>
		</div>
	</nav>
	<div class="container">
		<br>
		<div class="row list-header">
			<div class="col s11">
				<h5>Directorio de Empleados</h5>
			</div>
	      	<div class="col s1" style="    text-align: right;">
	      		<a
			href="${pageContext.request.contextPath}/EmployeeController?action=LIST"
			class="btn-floating btn-large waves-effect waves-light black"><i
			class="material-icons">arrow_back</i></a>
	      	</div>
		</div>

		<br> <br>

		<div class="row">
			<div class="col s12 m4 l3">
				<p></p>
			</div>
			<div class="col s12 m4 l6">
				<form action="${pageContext.request.contextPath}/EmployeeController"
					method="POST">

					<div class="form-group">
						<input type="text" class="form-control" name="name"
							placeholder="Ingresa el Nombre" value="${employee.name}" required />
					</div>

					<div class="form-group">
						<input class="datepicker" name="hireDate" value="${employee.hireDate}"
							placeholder="Seleccione Fecha" required />
					</div>

					<div class="form-group">
						<select class="form-control" name="cmbDepartment" required>
							<option value="">- Seleccione -</option>
							<c:forEach items="${departments}" var="department">
								<c:choose>
									<c:when test="${employee.department.id == department.id }">
										<option value="${department.id}" selected="">${department.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${department.id}">${department.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>

					<input type="hidden" name="id" value="${employee.id}" />
					<br>
					<div class="right-align">
						<button type="submit" class="btn waves-effect waves-light"
							onclick="window.location.href = 'EmployeeController?action=ADD'">
							Guardar<i class="material-icons right">save</i>
						</button>
					</div>
					
				</form>
			</div>
			<div class="col s12 m4 l3">
				<p></p>
			</div>
		</div>
		

	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script>
		$(document).ready(function() {
			$("select").formSelect();
			$('.datepicker').datepicker({
				format : "dd/mm/yyyy"
			});
		});
	</script>
</body>
</html>