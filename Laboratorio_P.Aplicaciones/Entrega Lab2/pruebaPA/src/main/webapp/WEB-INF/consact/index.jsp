<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inicio</title>
<link rel="stylesheet" href="<%=ListURL.BootstrapURL.getURL()%>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/css/multi-select-tag.css">
<link rel="stylesheet" href="<%= ListURL.headerStylesURL.getURL() %>">
<style>
	.inputs {
		margin-bottom: 20px;
		padding: 20px;
  		height: 30px;
  		width: 100%;
  		--bs-bg-opacity: 1;
    	background-color: rgba(var(--bs-white-rgb),var(--bs-bg-opacity))!important;
    	border-radius: var(--bs-border-radius);
    	color: black;
    	font-size: 1rem;
    	font-weight: 400;
    	line-height: 1.5;
	}
	
	.inputs::placeholder {
		color: #CDCDCD;
		font-weight: bold;
		font-size: 0.9rem;
	}
	
	button {
		margin-top: 20px;
	}
	
	.mult-select-tag {
    	color: black;
	}
	
	.container {
		max-width: 1200px;
	}
	
	#cont {
		padding-top: 70px;
	}
	
	.form-select {
		margin-bottom: 20px;
	}
	
	#imagen {
		background-color: grey;
		margin: 10px;
		margin-left: 0px;
		padding-right: 0px;
		width: 100%;
	}
	
	#txtim{
		margin-top: 20px;
	}
	.datos{
		margin: 10px;
	}
	
	form * {
		margin-bottom: 10px;
		display: block;
	}
	#paquetes{
		margin-top: 20px;
	}
	#salidas{
		margin-top: 20px;
	}
	.sinpunto{
		list-style-type: none;
		margin-bottom: 10px;
	}
	.oculto{
		display: none;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />
<div id="cont" class="d-flex justify-content-center">
	<div class="container">
		<div class="row">
			<div id="formu" class="col-md-3">
		    	<input type="radio" name="opciones_1" id="opciones_1" value="opcion_1">
		    	<label for="opciones_1">Por Departamento</label>
				<input type="radio" name="opciones_2" id="opciones_2" value="opcion_2">
		    	<label for="opciones_2">Por Categoría</label>
				<select class="form-select" id="CoD" aria-label="Default select example">
				</select>
				<select class="form-select" id="actividades" aria-label="Default select example">
				</select>
				
				<div class="row mt-3">
					<div class="col-md-12 py-1">
						<label class="labels" for="nombre">Nombre:</label>
						<input id="nombre" type="text" class="form-control" readonly>
					</div>
					<div class="col-md-12 py-1">
						<label class="labels" for="duracion">Duracion:</label>
						<input id="duracion" type="text" class="form-control" readonly>
					</div>
					<div class="col-md-12 py-1">
						<label class="labels" for="costo">Costo:</label>
						<input id="costo" type="text" class="form-control" readonly>
					</div>
					<div class="col-md-12 py-1">
						<label class="labels" for="ciudad">Ciudad:</label>
						<input id="ciudad" type="text" class="form-control" readonly>
					</div>
					<div class="col-md-12 py-1">
						<label class="labels" for="desc">Descripcion:</label>
						<input id="desc" type="text" class="form-control" readonly>
					</div>
					<div class="col-md-12 py-1">
						<label class="labels" for="fecha">Fecha:</label>
						<input id="fecha" type="text" class="form-control" readonly>
					</div>
					<div class="col-md-12 py-1">
						<ul id="lista-categorias" class="list-group"></ul>
					</div>
				</div>
				
				
			</div>
			<div class="col-md-6">
				<img id="image" src="<%=ListURL.ImagesURL.getURL() %>consact.png" class="img-fluid">
				<h1 style="text-align:center">Consulta una Actividad</h1>
			</div>
			<div class="col-md-3">
				<button id="botonver">Ver Paquetes</button>
				<select class="form-select oculto" id="paquetes" aria-label="Default select example">
				</select>
				<button id="botonver2">Ver Salidas</button>
				<select class="form-select oculto" id="salidas" aria-label="Default select example">
				</select>
			</div>
		</div>
	</div>
</div>
	<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
	<script src="<%= ListURL.headerJSURL.getURL() %>"></script>
</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>consActFunction.js" ></script>
</html>