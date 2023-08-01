<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Consola" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head >
<meta charset="ISO-8859-1">
<title>Registrar Nuevo Producto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="text-light bg-dark">
<a href="indexAdmin.jsp" class="btn btn-outline-light m-3 position-absolute top-0 start-0">
	<i class="bi bi-house"></i>
	</a>
<div class="container w-50 my-auto">
	<h3>Registrar Nuevo Producto</h3>
	<form class="row g-3 needs-validation" novalidate action="ServletProducto" method="post">
		<div class="form-group">
			<label>Nombre de Videjuego</label>
			<input class="form-control" type="text" name="txtNombreV" required>
			    <div class="invalid-feedback">
    			Debe ingresar el nombre del videojuego
    			</div>
		</div>
		<div class="form-group">
			<label>Descripción</label>
			<textarea class="form-control" name="txtDescripción" required>Ingrese descripción</textarea>
			    <div class="invalid-feedback">
    			Debe ingresar descripción
    			</div>
		</div>
		<div class="form-group">
			<label>Precio</label>
			<input class="form-control" type="number" step="0.01" name="numPrecio" required>
			<div class="invalid-feedback">
				Ingrese el precio del videojuego
    		</div>
		</div>
		<div class="form-group">
			<label>Stock</label>
			<input class="form-control" type="number" name="numCantidad" required>
			<div class="invalid-feedback">
				Ingrese la cantidad de videojuegos
    		</div>
		</div>
		<div class="form-group">
			<label>Consola</label>
			<select class="form-control" type="text" name="codConsola" required>
				<%
				List<Consola> listConsola = (List<Consola>)request.getAttribute("ListaConsola"); 
				if(listConsola != null){
							for(Consola item : listConsola){%>
					<option value="<%=item.getIdConsola() %>"> <%=item.getNombre() %> </option>
							<%}}; %>
					
			</select>
		</div>
		<br>
		<input type="submit" class="btn btn-primary" name="tipo" value="Registrar" required>
	</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
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