<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
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
		max-width: 900px;
	}
	
	#cont {
		padding-top: 70px;
	}
	
	.form-select {
		margin-bottom: 20px;
	}
	
	#fileInput {
		background-color: grey;
		margin: 10px;
		margin-left: 0px;
		padding-right: 0px;
		width: 100%;
	}
	
	#txtim{
		margin-top: 20px;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />
<div id="cont" class="d-flex justify-content-center">
	<div class="container">
		<div class="row">
			<form id="formu" name="formu" action="/pruebaPA/profile" method="POST" class="needs-validation col-sm-4" novalidate>
				<select class="form-select" id="departamentos" aria-label="Default select example">
				</select>
				<input class="inputs" type="text" id="nombre" name="nombre" placeholder="Nombre" required pattern="^\S(.*\S)?$">
				<input class="inputs" type="text" id="desc" name="desc" placeholder="Descripción" required pattern="^\S(.*\S)?$">
				<input class="inputs" type="number" id="dur" min="1" max="99" name="dur" oninput="validarNumero(event)" placeholder="Duración (horas)" required pattern="^\S(.*\S)?$">
				<input class="inputs" type="number" id="costo" name="costo" min="0" step="0.01" oninput="validarCosto(event)" placeholder="Costo (pesos)" required pattern="^\S(.*\S)?$">
				<input class="inputs" type="text" id="ciudad" name="ciudad" placeholder="Ciudad" required pattern="^\S(.*\S)?$">
				<label>Selecciona las categorías:</label>
				<select id="combo" name="categorias" multiple></select>
				<div class="row">
					<label id="txtim">¡Puedes adjuntar una imagen!</label>
				    <input type="file" class="file-input" accept="image/*" id="fileInput">
				    <div id="mensaje"></div>
					<button type="button" id="botonn" class="btn btn-primary">Aceptar</button>
				</div>
			</form>
			<div class="col-sm-8">
				<img src="<%=ListURL.ImagesURL.getURL() %>consact.png" class="img-fluid">
				<h1 style="text-align:center">Ingresa una nueva Actividad</h1>
			</div>
		</div>
	</div>
</div>
	<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
	<script src="<%= ListURL.headerJSURL.getURL() %>"></script>
	<script>
		function validarNumero(event) {
		    const input = event.target;
		    const valor = parseInt(input.value, 10);
	
		    if (valor > 999) {
		        input.value = 999; // Si es mayor que 100, establece el valor a 100
		    }
		    if (valor < 1) {
		        input.value = 1; // Si es menor que 1, establece el valor a 1
		    }
		}
		function validarCosto(event) {
		    const input = event.target;
		    const valor = parseFloat(input.value);

		    if (valor > 999999999.99) {
		        input.value = 999999999.99;
		    }
		    if (valor < 0.01) {
		        input.value = 0;
		    }
		}
	</script>
</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>altaActFunction.js" ></script>
</html>