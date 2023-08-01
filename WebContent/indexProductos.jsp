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

<nav class="navbar sticky-top justify-content-between p-2  navbar-dark bg-dark navbar-expand-lg ">

  <a class="navbar-brand" href="#">ArepaJuegos</a>
  <ul class="navbar-nav mr-auto  justify-content-between ">
  	<li class="nav-item">
        <a class="nav-link" href="ServletConsultasCliente?tipo=ListarTop">Top Juegos!</a>
     </li>
     <li class="nav-item">
        <a class="nav-link" href="ServletConsultasCliente?tipo=Listar">Todos los juegos</a>
     </li>
     
     <li class="nav-item">
        <a class="nav-link" href="ServletConsultasCliente?tipo=ListarConsolas">Consolas</a>
     </li>
     
     <li class="nav-item">
        <a class="nav-link" href="ServletConsultasCliente?tipo=misCompras&id=${idcliente}">Mis Compras</a>
     </li>
     
     <li class="nav-item">
        <a class="btn btn-outline-danger" href="index.jsp">Log Out</a>
     </li>
     
  </ul>
 
</nav>

<h3 class="alert alert-dark">Bienvenido ${name}!</h3>
<%String mensaje = (String)request.getAttribute("mensaje"); 
		if(mensaje!=null){%>
<p class="alert alert-success"><%=mensaje %></p>
	<%} %>
    

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="text-light bg-dark text-center">


<h1 class="text-light">${titulo }</h1>

<div class="container row mx-auto">
	<form class="col" action="ServletConsultasCliente" method="post">
	<div class="input-group col my-3 mx-auto">
  		<input type="text" class="form-control" name="txtConsulta" value="" placeholder="Busqueda por nombre...">
  		<button type="submit" class="btn btn-outline-secondary" name="tipo" value="Buscar" required>
  		<i class="bi bi-search"></i>
  		</button>
	</div>
  	</form>
	
  	<form class="col" action="ServletConsultasCliente" method="post">
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
		<button type="submit" class="btn btn-outline-secondary" name="tipo" value="Consola" required>
		<i class="bi bi-joystick"></i>
  		</button>
	</div>
	</form>
</div>
	<div class="container-fluid row">
		<%
					List<Producto> listProducto = (List<Producto>)request.getAttribute("data");
					if(listProducto != null){
						for(Producto item : listProducto){
		%>
	    <div class="col d-flex  justify-content-center mb-4">
		    <div class="card shadow mb-1 rounded" style="width: 20rem;">
		    	<div class="card-body border-light justify-content-between ">
			        <h3 class="card-title ls-2"><%=item.getNombreProducto()%></h3>
			        <p class="card-text"><%=item.getDescripcion()%></p>
			        <h5 ><%=item.getPrecioVenta()%></h5>
			        <p class="card-text"><%=item.getConsola()%></p>
			        <div class="d-grid gap-2">
			        	<a href="ServletConsultasCliente?tipo=PreCompra&id=<%=item.getIdProducto() %>" class="btn btn-success"><i class="bi bi-cart-plus-fill"></i>Comprar</a>
			        </div>
		    	</div>
		    </div>
	    </div>
	        		<%
					}
				}
		%>
	  </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>