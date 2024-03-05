<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Comprar Paquete</title>
		<link rel="stylesheet" href="<%=ListURL.BootstrapURL.getURL()%>">
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
			
			#txtim{
				margin-top: 20px;
			}
		
			.label-custom {
		    	width: 100px; /* Puedes ajustar este valor según tus necesidades */
		    	display: inline-block;
			}
			
			.botones {
				display: flex;
				margin-top: 8px;
			}
		
		</style>
		
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/template/header.jsp" />
		
		<div id="cont" class="d-flex justify-content-center">
		<div class="container">
			<div class="row">
				<div id="formu" class="col-sm-6">
					<form action="/pruebaPA/comprapaq" method="POST" enctype="multipart/form-data">			
									
					<!-- Paquetes -->
					<select class="form-select" id="paquetesCom" aria-label="Default select example" name="paquetesCom">
					</select>
					
					<!-- Fecha de compra -->	
					<div>
						<label for="fechaComp" class="label-custom">Fecha de compra: </label>
						<input id="fechaComp" type="text" name="fecha"
							placeholder="Fecha" readonly
							class="inputs" required>
					</div>
						
					<!-- Costo -->
					<div>
						<label for="costoComp" class="label-custom">Costo: </label>
						<input id="costoComp" type="text" name="costo"
							placeholder="Costo" readonly
							class="inputs" required>
					</div>	
		
					<!-- Cantidad -->
					<div>
						<label for="cantidadComp" class="label-custom">Cantidad de Turistas: </label>
						<input id="cantidadComp" type="text" name="cantidad"
							placeholder="Cantidad" readonly
							class="inputs" required>
					</div>	
					
					<!-- Fecha de Vencimiento -->				
					<div>
						<label for="vencimientoComp" class="label-custom">Fecha de Vencimiento:</label>
						<input id="vencimientoComp" type="text" name="vencimiento"
							placeholder="Vencimiento" readonly
							class="inputs" required>
					</div>		
					
					<div class="botones col-sm-7">
						<!-- Boton de compra -->
						<div class="form-group col-lg-12 mx-auto mb-0">
							<input id="comprarPaquete" type="submit" class="btn btn-primary btn-block py-2" value="Confirmar compra">
						</div>
						
						<!-- 
						<div class="form-group col-lg-12 mx-auto mb-0">
							<input type="submit" class="btn btn-primary btn-block py-2" value="Cancelar compra">
						</div> -->
					</div>
		        	</form>
				</div>
				

				
				
				
				
				
				
			</div>
		</div>
	</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
		<script src="<%= ListURL.headerJSURL.getURL() %>"></script>
	</body>
	
	<script src="<%=ListURL.JSFilesURL.getURL()%>compraPaquete.js" ></script>

</html>