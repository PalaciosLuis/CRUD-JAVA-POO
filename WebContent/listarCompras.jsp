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
	
	<a href="indexAdmin.jsp" class="btn btn-outline-light m-3 fs-2 position-absolute top-0 start-0">
	<i class="bi bi-house"></i>
	</a>
	
	<a href="ServletCompra?tipo=Trash" class="btn btn-outline-light fs-2 m-3 position-absolute top-0 end-0">
<i class="bi bi-trash3 "></i>
</a>
		
	<h1 class="text-light">${titulo }</h1>
	
	<form action="ServletCompra" novalidate method="post">
	<div class="input-group w-50 my-3 mx-auto">
  		<input type="date" class="form-control"  name="dateInicio" >
  		<input type="date" class="form-control"   name="dateFinal">
  		<button type="submit" class="btn btn-outline-secondary" name="tipo" value="Buscar" required>
  		<i class="bi bi-search"></i>
  		</button>
	</div>
	
	<a href="ServletProducto?tipo=Listar" class="btn btn-success"><i class="bi bi-plus-square"></i> Registrar nuevo Compra</a>
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
						<th>Acciones</th>
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
							<td>
								<div>
									<a href="ServletCompra?tipo=Info&id=<%=item.getIdCompra() %>" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
									<a href="ServletCompra?tipo=Borrar&id=<%=item.getIdCompra() %>" onclick="return confirm('¿Deseas borrar?')" class="btn btn-danger"><i class="bi bi-trash-fill"></i></a>
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