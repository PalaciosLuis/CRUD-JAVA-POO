<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" data-bs-theme="dark">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<title>ArepaJuegos</title>
<link rel="icon" type="image/png" href="img/arepa.png">
</head>
<body>

<div class='container mt-4 col-lg-4'>
		<div class='card col-sm-10' >
			<div class='card-body '>
				
				<form  action="ServletCliente" method="post" id="id_form">

					<div class='form-group text-center'>
					<h3>Login</h3>
					<img src='img/a.png' alt="70" width="170"/>
					<label>Bienvenidos al sistema </label>
					</div>
					<div class='form-group'>
					<label>Correo</label>
					<input type="text" name="txtCorreo" class="form-control" required>
					</div>
					<div class='form-group'>
					<label>Password</label>
					<input type="password" name="txtPass" class="form-control" required>
					</div>
					<br>
					<input type="submit"  name="tipo" value="Ingresar" class="btn btn-primary btn btn-b">
				    

				</form>
				<br>
				<%
				String mensaje=(String)request.getAttribute("mensaje");
				if(mensaje!=null){
				%>
				<div class="alert alert-danger">
				<strong>
				Error
				</strong><%=mensaje%>
				</div>
				<%
				}
				
				%>

			</div>
		</div>

	</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script>
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

</html>