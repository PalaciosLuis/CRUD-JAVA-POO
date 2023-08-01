<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<body class="bg-dark text-center">
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

	
	<h1 class="text-light">${titulo }</h1>
	
	
	<div class="container-fluid row">
		<%
						List<Consola> listConsola = (List<Consola>)request.getAttribute("data");
						if(listConsola != null){
							for(Consola item : listConsola){
		%>
	    <div class="col d-flex justify-content-center mb-4">
	    <div class="card shadow mb-1 rounded" style="width: 20rem;">
	    <div class="card-body">
	        <h5 class="card-title"><%=item.getNombre()%></h5>
	        <div class="d-grid gap-2">
	        <a href="ServletConsultasCliente?tipo=Consola&id=<%=item.getIdConsola() %>" class="btn btn-success"><i class="bi bi-list"></i> Lista de Juegos</a>
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