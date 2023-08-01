<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Cliente" %>
<%@page import="entidades.TipoDocumento" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Actualizar Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="text-light bg-dark">
<a href="indexAdmin.jsp" class="btn btn-outline-light m-3 position-absolute top-0 start-0">
	<i class="bi bi-house"></i>
	</a>

<%
	Cliente cliente = (Cliente)request.getAttribute("ClienteData");
%>
<div class="container w-50 my-auto">
<a href="index.jsp" class="btn btn-outline-light m-3 position-absolute top-0 start-0">
	<h3>Actualizar Cliente <%=cliente.getIdCliente() %></h3>
	<form class="row g-3 needs-validation" novalidate action="ServletCliente" method="post">
		<div hidden class="form-group">
			<label>Código de Cliente</label>
			<input class="form-control" type="number" value="<%=cliente.getIdCliente() %>" aria-label="readonly input example" readonly name="codCliente" required>
		</div>
		<div class="form-group">
			<label>Nombre o Razon Social</label>
			<input class="form-control" type="text" name="txtNombre" value="<%=cliente.getNombre() %>" required>
			    <div class="invalid-feedback">
    			Debe ingresar su nombre
    			</div>
		</div>
		<div class="form-group">
			<label>Tipo de Documento</label>
			<select class="form-select" aria-label="Default select example"
			name="txtTipoDocumento">
  			<%
				List<TipoDocumento> listTipoDocumento = (List<TipoDocumento>)request.getAttribute("docs"); 
				if(listTipoDocumento != null){
							for(TipoDocumento item : listTipoDocumento){
							String nombre = item.getTipo();
							if (nombre == cliente.getTipoDocumento()){%>
					<option value="<%=item.getId() %>" selected><%=item.getTipo() %> </option>
							<%}else{ %>
					<option value="<%=item.getId() %>"> <%=item.getTipo() %> </option>
							<%} %>
				 
				<%}
							} %>
			</select>
  			</select> 
		</div>
		<div class="form-group">
			<label>Documento</label>
			<input class="form-control" type="number" name="numDocumento" value="<%=cliente.getDocumento() %>" required>
			<div class="invalid-feedback">
				Ingrese un número de documento de identidad
    		</div>
		</div>
		<div class="form-group">
			<label>Telefono</label>
			<input class="form-control" type="tel" name="txtTelefono" value="<%=cliente.getTelefono() %>" required>
				<div class="invalid-feedback">
				Ingrese un número de télefono válido
    			</div>
		</div>
		<div class="form-group">
			<label>Email</label>
			<input class="form-control" type="email" name="txtEmail" value="<%=cliente.getEmail() %>" required>
				<div class="invalid-feedback">
				Ingrese su correo electrónico
    			</div>
		</div>
		<div class="form-group">
			<label>Dirección</label>
			<input class="form-control" type="text" name="txtDireccion" value="<%=cliente.getDireccion() %>" required>
				<div class="invalid-feedback">
				Ingrese una dirección
    			</div>
		</div>
		<br>
		<input type="submit" class="btn btn-primary" name="tipo" value="Actualizar" required>
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