<%@page import="datatype.DtSalidaTuristica"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<%
	HttpSession sesion = request.getSession(false); 
%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Consultar Salida</title>
	<link rel="stylesheet" href="<%=ListURL.BootstrapURL.getURL()%>">
	<link rel="stylesheet" href="<%= ListURL.headerStylesURL.getURL() %>">
	<style type="text/css">
 		.bajo-boton{ 
 			margin: 10px;
 			display: none;
 		} 
		.contenedor{
			max-width: 400px;
			display: flex;
            align-items: center;
            justify-content: center;
		}
		.pago{
			margin-top: 10px;
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
	<div class="container" style="min-height: 400px">
		<%	if (sesion.getAttribute("esDep") == null || 
			sesion.getAttribute("nombreDoC") == null || 
			sesion.getAttribute("nombreActividad") == null || 
			sesion.getAttribute("dataSalida") == null) {
			%>
		<h1 class="text-center">Para acceder correctamente a esta pagina se recomienda utilizar el boton inscribirse en la pagina de Salidas.</h1>
		<%} else {
			String esDep = (String)sesion.getAttribute("esDep");
			String nombreDoC = (String)sesion.getAttribute("nombreDoC");
			String nombreActividad = (String)sesion.getAttribute("nombreActividad");
			DtSalidaTuristica dataSalida = (DtSalidaTuristica)sesion.getAttribute("dataSalida");
			
			String nombreSalida = dataSalida.getNombreSalida();
			String lugarSalida = dataSalida.getLugarSalida();
			int cantMaxTur = dataSalida.getCantMaxTur();			
			String fechaSalida = dataSalida.getFechaSalida().toString();
			String horaSalida = dataSalida.getHoraSalida().toString();
			String fechaAlta = dataSalida.getFechaAlta().toString();
		%>
		<div class="row mt-3 d-flex justify-content-center">
			<h3 class="text-center">Inscripcion a Salida Turistica</h3>
			<div class="col-md-4">
				<div class="p-3 ">
					<div class="row mt-3">
						<div class="col-md-12 py-1">
							<label class="labels" for="DoC"><% out.print(esDep.equals("true") ? "Departamento:" : "Categoria:");%></label>
							<input id="DoC" type="text" class="form-control" value="<%=nombreDoC %>" readonly>
						</div>
						<div class="col-md-12 py-1">
							<label class="labels" for="nombreSal">Nombre de la Salida:</label>
							<input id="nombreSal" type="text" class="form-control" value="<%=nombreSalida %>" readonly>
						</div>
						<div class="col-md-12 py-1">
							<label class="labels" for="lugarAct">Lugar:</label>
							<input id="lugarAct" type="text" class="form-control" value="<%=lugarSalida %>" readonly>
						</div>
						<div class="col-md-12 py-1">
							<label class="labels" for="cantMaxTur">Cantidad Maxima de Turistas:</label>
							<input id="cantMaxTur" type="number" class="form-control" value="<%=cantMaxTur %>" readonly>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="p-3">
					<div class="row mt-3">
						<div class="col-md-12 py-1">
							<label class="labels" for="nombreAct">Nombre de la Actividad:</label>
							<input id="nombreAct" type="text" class="form-control" value="<%=nombreActividad %>" readonly>
						</div>
						<div class="col-md-12 py-1">
							<label class="labels" for="fechaSalida">Fecha de la Salida:</label>
							<input id="fechaSalida" type="date" class="form-control" value="<%=fechaSalida %>" readonly>
						</div>
						<div class="col-md-12 py-1">
							<label class="labels" for="horaSalida">Hora de la salida:</label>
							<input id="horaSalida" type="text" class="form-control" value="<%=horaSalida %>" readonly>
						</div>
						<div class="col-md-12 py-1">
							<label class="labels" for="fechaAlta">Fecha de alta:</label>
							<input id="fechaAlta" type="date" class="form-control" value="<%=fechaAlta %>" readonly>
						</div>
					</div>
				</div>
			</div>
			<div class="mt-5 text-center">
				<input id="btnInscribir" class="btn btn-primary profile-button" type="submit" value="Inscribirte"> 
				<div class="container d-flex justify-content-center contenedor">
					<div class="col-sm-12">
						<label class="pago bajo-boton">
							Metodo de pago:
						</label>
						<div>
							<input id="btnGeneral" class="btn btn-primary profile-button bajo-boton" type="submit" value="General">
							<input id="btnPaquete" class="btn btn-primary profile-button bajo-boton" type="submit" value="Paquete">
						</div>
						<div>
							<label class="labels bajo-boton" for="paquetes">Paquetes:</label>
							<select class="form-select bajo-boton selecto" id="paquetes"></select>
							<input class="inputs bajo-boton" type="number" id="cantidad" name="cantidad" placeholder="Cant.Turistas" required pattern="^\S(.*\S)?$">
							<input id="enviar" class="btn btn-primary profile-button bajo-boton" type="submit" value="Enviar">
							<input id="enviar2" class="btn btn-primary profile-button bajo-boton" type="submit" value="Enviar">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<%} %>
	</div>
</body>
<script type="text/javascript">
	const type = "<%=sesion.getAttribute("esDep")%>"
</script>
<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
<script src="<%= ListURL.headerJSURL.getURL() %>"></script>
<script src="<%=ListURL.JSFilesURL.getURL()%>inscribirseFunction.js" ></script>
</html>