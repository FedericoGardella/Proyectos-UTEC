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
	
	#fecha {
		width: 100%;
		border-radius: var(--bs-border-radius);
		margin-bottom: 20px;
		padding: 20px;
  		height: 30px;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />
<div id="cont" class="d-flex justify-content-center">
	<div class="container">
		<div class="row">
			<div id="formu" class="col-sm-4">
				<select id="departamentos" name="departamentos" class="form-select" aria-label="Default select example">
				  <option selected>Departamentos</option>
				</select>
				<select id="actividades" name="actividades" class="form-select" aria-label="Default select example">
					<option selected>Actividades</option>
				</select>
				<input id="nombre" class="inputs" type="text" name="nombre" placeholder="Nombre">
				<input id="fecha" type="date" name="fecha" min="<%=java.time.LocalDate.now()%>" class="form-control custom-datepicker" required>
				<input id="hora" class="inputs" type="time" pattern="(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]" name="hora">
				<input id="lugar" class="inputs" type="text" name="lugar" placeholder="Lugar">
				<input id="cantidad" class="inputs" type="number" name="cantidad" oninput="validarNumero(event)" placeholder="Cantidad Max. Tur.">
				<div class="row">
					<label id="txtim">¡Puedes adjuntar una imagen!</label>
				    <input type="file" class="file-input" accept="image/*" id="fileInput">
				    <div id="mensaje"></div>
					<button type="button" id="botonn" class="btn btn-primary">Aceptar</button>
				</div>
			</div>
			<div class="col-sm-8">
				<img src="<%=ListURL.ImagesURL.getURL() %>amiguis.png" class="img-fluid">
				<h1 style="text-align:center">Ingresa una nueva Salida</h1>
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
	
		    if (valor > 99) {
		        input.value = 99; // Si es mayor que 100, establece el valor a 100
		    }
		    if (valor < 1) {
		        input.value = 1; // Si es menor que 1, establece el valor a 1
		    }
		}
	</script>
</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>salidaFunction.js" ></script>
</html>