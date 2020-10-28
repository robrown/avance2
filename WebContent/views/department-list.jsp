<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Directorio de Empleados</title>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="assets/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>   
</head>
<body>
	<%
        String email=(String)session.getAttribute("email");
        
        //redirect user to login page if not logged in
        if(email==null){
        	response.sendRedirect("index.jsp");
        }
    %>

	<nav class="white" role="navigation">
	    <div class="nav-wrapper container">
	      <a id="logo-container" href="/RRHH/HomeController" class="brand-logo">RRHH</a>
	      <ul class="right hide-on-med-and-down">
	        <li><a href="#">Departamentos</a></li>
	        <li><a href="/RRHH/EmployeeController">Empleados</a></li>
	        <li><a href="${pageContext.request.contextPath}/logout.jsp">Cerrar Sesión</a></li>
	      </ul>

	      
	      <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
	    </div>
	  </nav>
	  <input type="hidden" id="notification" value="${NOTIFICATION}">
	<div class = "container">
		<br>
		
		<div class="row list-header">
			<div class="col s11">
				<h5>Directorio de Departamentos</h5>
			</div>
	      	<div class="col s1" style="    text-align: right;">
	      		<a class="btn-floating btn-large waves-effect waves-light" ><i class="material-icons">add</i></a>
	      	</div>
		</div>
		<hr/>
		<br>
	
		<table id="datatable" class="mdl-data-table" style="width:100%">
			<thead>
				<tr class = "thead-dark">
					<th>Id</th>
					<th>Departamento</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="department">
					<tr>
						<td>${department.id}</td>
						<td>${department.name}</td>
						<td> 
						<a class="waves-effect waves-light btn-small" href = "#"><i class="material-icons center">edit</i></a>
						<a class="waves-effect red lighten-2 btn-small" href = "#"><i class="material-icons center">delete</i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="assets/js/materialize.js"></script>
  <script src="assets/js/init.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"></script>
<script>
	$(document).ready(function(){
		$("#datatable").DataTable( {
	        autoWidth: false,
	        "language": {
	            "decimal": ",",
	            "thousands": ".",
	            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	            "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
	            "infoPostFix": "",
	            "infoFiltered": "(filtrado de un total de _MAX_ registros)",
	            "loadingRecords": "Cargando...",
	            "lengthMenu": "Mostrar _MENU_ registros",
	            "paginate": {
	                "first": "Primero",
	                "last": "Último",
	                "next": "Siguiente",
	                "previous": "Anterior"
	            },
	            "processing": "Procesando...",
	            "search": "Buscar:",
	            "searchPlaceholder": "Término de búsqueda",
	            "zeroRecords": "No se encontraron resultados",
	            "emptyTable": "Ningún dato disponible en esta tabla",
	            "aria": {
	                "sortAscending":  ": Activar para ordenar la columna de manera ascendente",
	                "sortDescending": ": Activar para ordenar la columna de manera descendente"
	            },
	            //only works for built-in buttons, not for custom buttons
	            "buttons": {
	                "create": "Nuevo",
	                "edit": "Cambiar",
	                "remove": "Borrar",
	                "copy": "Copiar",
	                "csv": "fichero CSV",
	                "excel": "tabla Excel",
	                "pdf": "documento PDF",
	                "print": "Imprimir",
	                "colvis": "Visibilidad columnas",
	                "collection": "Colección",
	                "upload": "Seleccione fichero...."
	            },
	            "select": {
	                "rows": {
	                    _: '%d filas seleccionadas',
	                    0: 'clic fila para seleccionar',
	                    1: 'una fila seleccionada'
	                }
	            }
	        },
	        columnDefs: [
	            {
	                targets: ['_all'],
	                className: 'mdc-data-table__cell'
	            }
	        ]
	    } );
		
		var notification = $("#notification").val();
		if(notification !==""){
			M.toast({html: notification})
			$("#notification").val("");
		}
		 
	})
</script>
</body>
</html>