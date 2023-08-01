<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Producto" %>
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
<body class="text-light bg-dark text-center">
<a href="indexAdmin.jsp" class="btn btn-outline-light m-3 fs-2 position-absolute top-0 start-0">
	<i class="bi bi-house"></i>
	</a>
	
	<a href="ServletProducto?tipo=Trash" class="btn btn-outline-light fs-2 m-3 position-absolute top-0 end-0">
<i class="bi bi-trash3 "></i>
</a>

<h1 class="text-light">${titulo }</h1>

<div class="container row mx-auto">
	<form class="col" action="ServletProducto" method="post">
	<div class="input-group col my-3 mx-auto">
  		<input type="text" class="form-control" name="txtConsulta" value="" placeholder="Busqueda por nombre...">
  		<button type="submit" class="btn btn-outline-secondary" name="tipo" value="Buscar" required>
  		<i class="bi bi-search"></i>
  		</button>
	</div>
  	</form>
	
  	<form class="col" action="ServletConsola" method="post">
	<div class="input-group col my-3 mx-auto">
  		<select class="form-control" type="text"  name="id" required>
  			<option disabled selected>Busqueda por consola</option>
				<%
				List<Consola> listConsola = (List<Consola>)request.getAttribute("ListaConsola"); 
				if(listConsola != null){
							for(Consola item : listConsola){%>
					<option value="<%=item.getIdConsola() %>"> <%=item.getNombre() %> </option>
							<%}}; %>
					
			</select>
		<button type="submit" class="btn btn-outline-secondary" name="tipo" value="Productos" required>
		<i class="bi bi-joystick"></i>
  		</button>
	</div>
	</form>
	</div>

	<a href="ServletProducto?tipo=Pre" class="btn btn-success"><i class="bi bi-plus-square"></i> Registrar nuevo producto</a>
	<div class="container">
		<div class="row">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre de Videojuego</th>
						<th>Precio</th>
						<th>Stock</th>
						<th>Consola</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Producto> listProducto = (List<Producto>)request.getAttribute("data");
						if(listProducto != null){
							for(Producto item : listProducto){
					%>
						<tr>
							<td><%=item.getIdProducto() %></td>
							<td><%=item.getNombreProducto() %></td>
							<td><%=item.getPrecioVenta() %></td>
							<td><%=item.getStock() %></td>
							<td><%=item.getConsola() %></td>
							
							<td>
								<a href="ServletProducto?tipo=Info&id=<%=item.getIdProducto() %>" class="btn btn-primary"><i class="bi bi-pencil-square"></i></i></a>
								<%if(item.getStock()>0){ %>
								<a href="ServletCompra?tipo=PreProducto&id=<%=item.getIdProducto() %>" class="btn btn-success"><i class="bi bi-cart"></i></i></a>
								<% }%>
								<a href="ServletProducto?tipo=Borrar&id=<%=item.getIdProducto() %>" onclick="return confirm('¿Deseas borrar?')" class="btn btn-danger"><i class="bi bi-trash-fill"></i></a>
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