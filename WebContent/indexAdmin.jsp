<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>ArepaJuegos - Main</title>
</head>

<body class="container">

	<h1 class="text-center">ArepaJuegos</h1>
	
	<div class="container btn-group my-3">
		<div class="dropdown">
		  <button class="btn btn-primary btn-lg fs-1 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		    <i class="bi bi-person-circle"></i> Clientes</a>
		  </button>
		  
		  <ul class="dropdown-menu">
			<li><a href="ServletCliente?tipo=ListarTop" class="dropdown-item">Top 10 Clientes</a></li>
			<li><a href="ServletCliente?tipo=Listar" class="dropdown-item">Todos los clientes</a></li>
			<hr>
			<li><a href="ServletCliente?tipo=Pre" class="dropdown-item">Registrar nuevo cliente</a></li>
		  </ul>
		</div>
		
		<div class="dropdown">
		  <button class="btn btn-secondary btn-lg fs-1 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		    <i class="bi bi-controller"></i> Productos</a>
		  </button>
		  <ul class="dropdown-menu">
			<li><a href="ServletProducto?tipo=ListarTop" class="dropdown-item">Top 10 Productos</a></li>
			<li><a href="ServletProducto?tipo=Listar" class="dropdown-item">Todos los productos</a></li>
			<hr>
			<li><a href="ServletProducto?tipo=Pre" class="dropdown-item">Registrar nuevo producto</a></li>
		  </ul>
		</div>
		
	
		<div class="dropdown">
		  <button class="btn btn-danger btn-lg fs-1 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		    <i class="bi bi-joystick"></i> Consolas</a>
		  </button>
		  <ul class="dropdown-menu">
			<li><a href="ServletConsola?tipo=ListarTop" class="dropdown-item">Top 5 Consolas con mas ventas</a></li>
			<li><a href="ServletConsola?tipo=Listar" class="dropdown-item">Todos las Consolas</a></li>
			<hr>
			<li><a href="registrarConsola.jsp" class="dropdown-item">Registrar nuevo consola</a></li>
		  </ul>
		</div>
	
		
		<div class="dropdown">
		  <button class="btn btn-success btn-lg fs-1 dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		    <i class="bi bi-cart"></i> Compras</a>
		  </button>
		  <ul class="dropdown-menu">
			<li><a href="ServletCompra?tipo=ListarTop" class="dropdown-item">Ultimas compras</a></li>
			<li><a href="ServletCompra?tipo=Listar" class="dropdown-item">Todas las compras</a></li>
			<hr>
			<li><a href="ServletProducto?tipo=Listar" class="dropdown-item">Registrar nueva compra</a></li>
		  </ul>
		</div>
		
		
	</div>

<%String mensaje = (String)request.getAttribute("mensaje"); 
		if(mensaje!=null){%>
<h1 class="alert alert-dark"><%=mensaje %></h1>
	<%} %>
    


<footer class="text-center">
<h4>Jorge Torres - i202212392</h4>
<h4>Luis Palacios - i202215563</h4>
<h4>José Alvarado - i202211249</h4>
<h4>Camila - i202212392</h4>


</footer>
</body>
</html>