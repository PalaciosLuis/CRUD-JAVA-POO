<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Producto" %>
<%@page import="entidades.Cliente" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head >
<meta charset="ISO-8859-1">
<title>Registrar Nueva Compra</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="text-light bg-dark">
<a href="indexAdmin.jsp" class="btn btn-outline-light m-3 position-absolute top-0 start-0">
	<i class="bi bi-house"></i>
	</a>
	
<div class="container w-50 my-auto">
<%
	Producto producto = (Producto)request.getAttribute("data");
%>
	<h4>Registrar Nueva Compra</h4>
	<h1><%= producto.getNombreProducto()%></h1>
	
	<p><%= producto.getConsola() %> - <%= producto.getPrecioVenta() %></p>
	<h5><%= producto.getDescripcion() %></h5>
	
	<form class="row g-3 needs-validation" novalidate action="ServletCompra" method="post">
		<div class="form-group">
			<label>Cliente</label>
			<select class="form-control" name="idCliente" required>
				<%
				List<Cliente> listCliente= (List<Cliente>)request.getAttribute("dataClientes"); 
				if(listCliente!= null){
							for(Cliente item : listCliente){%>
					<option value="<%=item.getIdCliente() %>"> <%=item.getNombre() %> </option>
							<%}}; %>
			</select>
		</div>
		<div class="row">
		<div class="col form-group">
			<label>Cantidad</label>
			<input class="form-control" type="number" name="numCantidad" 
			id="numCantidad" required min="1" max="<%=producto.getStock() %>" >
			<div class="invalid-feedback">
				Ingrese la cantidad de videojuegos a comprar
    		</div>
		</div>
		
		<div class="col form-group">
			<label>Precio total</label>
			<input readonly class="form-control" type="number" name="precioTotal" id="precioTotal" required>
			<div class="invalid-feedback">
				Ingrese la cantidad de videojuegos a comprar
    		</div>
		</div>
		
		</div>
		<input hidden name="idProducto" value="<%= producto.getIdProducto() %>">
		<input hidden name="precioVenta" id="precioVenta" value="<%= producto.getPrecioVenta() %>"> 
		<br>
		<input type="submit" class="btn btn-primary" name="tipo" value="Registrar" required>
	</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script>
//Example starter JavaScript for disabling form submissions if there are invalid fields
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

    const cantidad = document.getElementById('numCantidad');
    const precioVenta = document.getElementById('precioVenta');
    const precioTotal = document.getElementById('precioTotal');

    // Agregar el evento "input" al primer input
    cantidad.addEventListener('input', function() {
      precioTotal.value = parseFloat(cantidad.value)*parseFloat(precioVenta.value);
    })

</script>
</body>
</html>