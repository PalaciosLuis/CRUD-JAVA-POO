<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.Consola" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head >
<meta charset="UTF-8">
<title>${titulo}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<a href="indexAdmin.jsp" class="btn btn-outline-light fs-2 m-3 position-absolute top-0 start-0">
	<i class="bi bi-house "></i>
	</a>
	
<a href="ServletCliente?tipo=Trash" class="btn btn-outline-light fs-2 m-3 position-absolute top-0 end-0">
<i class="bi bi-trash3 "></i>
</a>

<body class="bg-dark text-center">
	<h1 class="text-light">${titulo}</h1>
	
	<form action="ServletCliente" method="post">
	<div class="input-group w-50 my-3 mx-auto">
  		<input type="text" class="form-control" name="txtConsulta" value="" placeholder="Busqueda por nombre...">
  		<button type="submit" class="btn btn-outline-secondary" name="tipo" value="Buscar" required>
  		<i class="bi bi-search"></i>
  		</button>
	</div>
	
	<a href="ServletCliente?tipo=Pre" class="btn btn-success"><i class="bi bi-plus-square"></i> Registrar nuevo cliente</a>
	<div class="container">
		<div class="row">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Tipo Doc.</th>
						<th>Documento</th>
						<th>Telefono</th>
						<th>Email</th>
						<th>Dirección</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Cliente> listCliente = (List<Cliente>)request.getAttribute("data");
						if(listCliente != null){
							for(Cliente item : listCliente){
					%>
						<tr>
							<td><%=item.getIdCliente() %></td>
							<td><%=item.getNombre() %></td>
							<td><%=item.getTipoDocumento() %></td>
							<td><%=item.getDocumento() %></td>
							<td><%=item.getTelefono() %></td>
							<td><%=item.getEmail() %></td>
							<td><%=item.getDireccion() %></td>
							<td>
								<div>
									<a href="ServletCliente?tipo=Info&id=<%=item.getIdCliente() %>" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
									<a href="ServletCompra?tipo=ListarCliente&id=<%=item.getIdCliente() %>" class="btn btn-success"><i class="bi bi-list"></i></a>
									<a href="ServletCliente?tipo=Borrar&id=<%=item.getIdCliente() %>" onclick="return confirm('¿Deseas borrar?')" class="btn btn-danger"><i class="bi bi-trash-fill"></i></a>
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