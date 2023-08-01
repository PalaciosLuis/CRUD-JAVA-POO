<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Consola" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head >
<meta charset="UTF-8">
<title>${titulo }</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-dark text-center">

	<a href="indexAdmin.jsp" class="btn btn-outline-light m-3 fs-2 position-absolute top-0 start-0">
	<i class="bi bi-house"></i>
	</a>
	
	<a href="ServletConsola?tipo=Trash" class="btn btn-outline-light fs-2 m-3 position-absolute top-0 end-0">
<i class="bi bi-trash3 "></i>
</a>
	
	<h1 class="text-light">${titulo }</h1>
	
	<a href="registrarConsola.jsp" class="btn btn-success"><i class="bi bi-plus-square"></i> Registrar Nueva Consola</a>
	<div class="container">
		<div class="row">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre Consola</th>
						<th>Cantidad Productos</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Consola> listConsola = (List<Consola>)request.getAttribute("data");
						if(listConsola != null){
							for(Consola item : listConsola){
					%>
						<tr>
							<td><%=item.getIdConsola() %></td>
							<td><%=item.getNombre() %></td>
							<td><%=item.getCantidadProductos() %></td>
							<td>
								<div>
									<a href="ServletConsola?tipo=Info&id=<%=item.getIdConsola() %>" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
									<a href="ServletConsola?tipo=Productos&id=<%=item.getIdConsola() %>" class="btn btn-success"><i class="bi bi-list"></i></a>
									<a href="ServletConsola?tipo=Borrar&id=<%=item.getIdConsola() %>" onclick="return confirm('¿Deseas borrar?')" class="btn btn-danger"><i class="bi bi-trash-fill"></i></a>
								</div>
							</td>
						<tr>
					<%
							}
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>