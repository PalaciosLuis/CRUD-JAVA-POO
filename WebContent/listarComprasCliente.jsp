<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Compra" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head >
<meta charset="UTF-8">
<title>${titulo }</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="text-center">
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




<%String mensaje = (String)request.getAttribute("mensaje"); 
		if(mensaje!=null){%>
<h1 class="alert alert-dark"><%=mensaje %></h1>
	<%} %>


    			
	<h1 class="text-light">${titulo }</h1>
	
	
	<a href="ServletConsultasCliente?tipo=Listar" class="btn btn-success"><i class="bi bi-plus-square"></i> Registrar nuevo Compra</a>
	<div class="container">
		<div class="row">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>ID</th>
						<th>Fecha Compra</th>
						<th>Cliente</th>
						<th>Producto</th>
						<th>Cantidad</th>
						<th>Precio Total</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Compra> listCompra = (List<Compra>)request.getAttribute("data");
						if(listCompra != null){
							for(Compra item : listCompra){
					%>
						<tr>
							<td><%=item.getIdCompra() %></td>
							<td><%=item.getFecha() %></td>
							<td><%=item.getCliente() %></td>
							<td><%=item.getProducto() %></td>
							<td><%=item.getCantidad() %></td>
							<td><%=item.getPagoTotal() %></td>
							
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
<script>
(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()

</script>
</body>
</html>