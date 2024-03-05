<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<%@ page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<% 
	HttpSession sesion = request.getSession(false);
	List<String> paquetes = (List<String>)sesion.getAttribute("paquetes");
	sesion.removeAttribute("paquetes");
%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Inicio</title>
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
		
	/* 	#imagen {
			background-color: grey;
			margin: 10px;
			margin-left: 0px;
			padding-right: 0px;
			width: 100%;
		} */
		
		#txtim{
			margin-top: 20px;
		}
	
		.label-custom {
	    	width: 100px; /* Puedes ajustar este valor según tus necesidades */
	    	display: inline-block;
		}
	
	 /* 	.imagen {
 			display: grid;
 			margin-top: 280px;
			
			justify-content: center;
			padding-top: 15px;
		} */
		
		@media (max-width: 575px) {
		   .imagen {
		  	margin-top: 0; /* Sin relleno para pantallas más pequeñas cuando se aplica la clase "col-sm-8" */
		  }
		}
		
		.imagen-actividad {
		    display: flex;
		    text-align: center;
		    align-items: center;
		    margin-bottom: 8px;
		}
		
		.half-width-image {
		    max-width: 50%;
		}		
		
		.actividades {
			padding-left: 20px;
		}
		
		.acts {
			margin-botton: 18px;
		}
		
		.cats {
			margin-top: 12px;
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
				<div id="formu" class="col-sm-6">					
					
					<!-- Paquetes -->
					<select class="form-select" id="paquetesCP" aria-label="Default select example">
						<option value="0">Paquetes</option>
			        	<%  for (String elemento : paquetes) { %>
						<option><%=elemento%></option>
						<%} %>

					</select>
					
					<!-- Nombre -->	
					<div>
						<label for="nombrePaq" class="label-custom">Nombre: </label>
						<input id="nombrePaq" type="text" name="nombre"
							placeholder="Nombre" readonly
							class="inputs" required>
					</div>
					
					<!-- Descripcion -->
		           	<div>
						<label for="descripcionPaq" class="label-custom">Descripción:</label>
						<input id="descripcionPaq" type="text" name="descripcion"
							placeholder="Descripcion" readonly
							class="inputs" required>
					</div>
						
					<!-- Periodo validez -->
					<div>
						<label for="periodoPaq" class="label-custom">Periodo de validez: </label>
						<input id="periodoPaq" type="text" name="periodo"
							placeholder="Periodo" readonly
							class="inputs" required>
					</div>	
		
					<!-- Descuento -->
					<div>
						<label for="descuentoPaq" class="label-custom">Descuento: </label>
						<input id="descuentoPaq" type="text" name="descuento"
							placeholder="Descuento" readonly
							class="inputs" required>
					</div>	
					
					<!-- Fecha de Alta -->				
					<div>
						<label for="fechaPaq" class="label-custom">Fecha de Alta:</label>
						<input id="fechaPaq" type="text" name="fecha"
							placeholder="Fecha" readonly
							class="inputs" required>
					</div>		
					
					<div>
						<label for="cantTur" class="label-custom">Cant. Tur.:</label>
						<input id="cantTur" type="text" name="cantTur"
							placeholder="Cant. Tur." readonly
							class="inputs" required>
					</div>
					
					<div>
						<label for="cantTickets" class="label-custom">Cant. Tickets:</label>
						<input id="cantTickets" type="text" name="cantTickets"
							placeholder="Cant. Tickets" readonly
							class="inputs" required>
					</div>		
						
					
					<!-- <ul id="listCategorias">
						<li id="tituloCategorias" style="display: none;">Categorías:</li>
					</ul> -->
		            
		          	
				</div>
				
				
				
				<div class="actividades col-sm-6">
				
					<h3>Actividades</h3>
					
					<div>
						<button id="verAct">Ver Actividades</button>
						<!-- <select class="form-select oculto mt-2" id="actividadesCP" aria-label="Default select example">
						</select> -->
						<ul class="oculto mt-2" id="actividadesCP"></ul>
						<button id="verCat">Ver Categorías</button>
						<ul class="oculto mt-2" id="listCategorias"></ul>
					</div>
	
				        
				</div>
				

				
				
				
				
				
				
			</div>
		</div>
	</div>
	<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
	<script src="<%= ListURL.headerJSURL.getURL() %>"></script>

</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>consultaPaquete.js" ></script>

</html>