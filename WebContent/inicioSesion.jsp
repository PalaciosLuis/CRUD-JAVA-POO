<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<h3>Registrar Cliente</h3>
	<form class="row g-3 needs-validation" novalidate action="ProyectoServlet" method="post">
		<div class="form-group">
			<label>Nombre de usuario</label>
			<input class="form-control" type="text" name="txtUsuario" minlength="5" required>
			    <div class="invalid-feedback">
    			El nombre de usuario debe de tener mínimo 5 caracteres
    			</div>
		</div>
		<div class="form-group">
			<label>Clave</label>
			<input class="form-control" type="password" name="passClave" minlength="5" required>
			    <div class="invalid-feedback">
    			La clave debe de tener mínimo 5 caracteres
    			</div>
		</div>
		<br>
		<input type="submit" class="btn btn-primary" value="Iniciar Sesion" required>
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