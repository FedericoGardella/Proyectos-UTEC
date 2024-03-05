<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Consulta Salidas</title>
	<link rel="stylesheet" href="<%=ListURL.BootstrapURL.getURL()%>">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/css/multi-select-tag.css">
	<link rel="stylesheet" href="<%= ListURL.headerStylesURL.getURL() %>">

	<style type="text/css">
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
	    	margin-bottom: 20px;
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
	
		.label-custom {
	    	/*width: 100px; /* Puedes ajustar este valor según tus necesidades */
	    	display: inline-block;
		}
	
	 	.imagen {
 			display: grid;
 			margin-top: 280px;
			
			justify-content: center;
			padding-top: 15px;
		}
		
		@media (max-width: 575px) {
		   .imagen {
		  	margin-top: 0; /* Sin relleno para pantallas más pequeñas cuando se aplica la clase "col-sm-8" */
		  }
		}
		
		.derecha {
			float: right;
		}
		
		
		
	</style>
</head>
<body>	
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div class="modal fade show" id="modal" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-modal="true" role="dialog" style="display: none;">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="modalTitle"></h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="ocultarModalError()"></button>
	      </div>
	      <div class="modal-body" id="modalBody">
	        <p>This is a vertically centered modal.</p>
	      </div>
	      <div class="modal-footer" id="modalFooter">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="ocultarModalError()">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="container d-flex justify-content-center mt-4">
		<div class="row">
			<div id="formu" class="col-sm-6">
				
				
				<input type="radio" name="opciones_1" id="opc1" value="opcion_1">
		    	<label for="opc1">Por Departamento</label>
				<div class="derecha">
					<input type="radio" name="opciones_2" id="opc2" value="opcion_2">
				    <label for="opc2">Por Categoría</label>
			    </div>
				
				<!-- Departamentos o Categorías -->
				<select class="form-select" id="DoC" aria-label="Default select example">
				</select>
				
				<!-- Actividades -->
				<select class="form-select" id="actividadesCS" aria-label="Default select example">
				</select>
				
				<!-- Salidas -->
				<select class="form-select" id="salidasCS" aria-label="Default select example">
				</select>
				
				<!-- Nombre -->	
				<div>
					<label for="nombreSal" class="label-custom">Nombre: </label>
					<input id="nombreSal" type="text" name="nombre"
						placeholder="Nombre" readonly
						class="inputs" required>
				</div>
					
				<!-- Fecha Salida -->
				<div>
					<label for="fechaSal" class="label-custom">Fecha Salida: </label>
					<input id="fechaSal" type="text" name="fecha"
						placeholder="Fecha" readonly
						class="inputs" required>
				</div>	
	
				<!-- Hora -->
				<div>
					<label for="horaSal" class="label-custom">Hora Salida: </label>
					<input id="horaSal" type="text" name="hora"
						placeholder="Hora" readonly
						class="inputs" required>
				</div>	
				
				<!-- Lugar -->				
				<div>
					<label for="lugarSal" class="label-custom">Lugar:</label>
					<input id="lugarSal" type="text" name="lugar"
						placeholder="Lugar" readonly
						class="inputs" required>
				</div>		
	               
	          	<!-- Cant maxima -->
	           	<div>
					<label for="cantSal" class="label-custom">Cantidad máxima turistas:</label>
					<input id="cantSal" type="text" name="cant"
						placeholder="Cant" readonly
						class="inputs" required>
				</div>
				
				<!-- Fecha Alta -->
	           	<div>
					<label for="altaSal" class="label-custom">Fecha Alta:</label>
					<input id="altaSal" type="text" name="alta"
						placeholder="Alta" readonly
						class="inputs" required>
				</div>
			
			</div>
			
			<div class="imagen col-sm-6">
				<img id="imagenSal" src="<%=ListURL.ImagesURL.getURL() %>amiguis.png" class="img-fluid">
				<h5 style="text-align:center">Imagen de la salida</h5>
			</div>
			<button type="button" id="inscripcion" class="btn btn-secondary col-4 mx-auto">Inscribirse</button>
		</div>
	</div>
	<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
	<script src="<%= ListURL.headerJSURL.getURL() %>"></script>
	<script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag/dist/js/multi-select-tag.js"></script>
</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>consultaSalida.js" ></script>
</html>