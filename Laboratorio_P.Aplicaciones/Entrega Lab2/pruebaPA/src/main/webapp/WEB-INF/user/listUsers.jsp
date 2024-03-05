<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<%@ page import="logica.Fabrica"%>
<%@ page import="logica.ISistema"%>
<%@ page import="java.util.List"%>
<%
	ISistema sis = Fabrica.getSistema();
	List<String> listNickUsers = sis.listarUsuarios();
%>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Listar Usuarios</title>
	<link rel="stylesheet" href="<%=ListURL.BootstrapURL.getURL()%>">
	<link rel="stylesheet" href="<%=ListURL.headerStylesURL.getURL()%>">
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<h1 class="text-center">Soy el list usuarios</h1>
	<div class="container mb-5" style="min-height: 500px">
		<div class="row justify-content-center">
			<div class="col-3">
				<h1>Nickname</h1>
				<ul id="listaNicks" class="list-group">
					<% for (String n : listNickUsers){ %>
					<li class="list-group-item list-group-item-action" data-nick-id="<%=n %>"><%=n %></li>
					<%}%>
				</ul>
			</div>
			<div class="col-9">
				<h1 class="text-center">User info</h1>
				<div class="row-md-6 d-flex">
					<div class="col-md-6 col-sm-12">
						<div class="d-flex gap-3 flex-column align-items-center text-center p-3">
							<img id="img-profile-select" class="rounded-circle" width="150px" src="<%=ListURL.ImagesURL.getURL() %>profile_pic.webp" alt="Cuchau" >
							<span id="nickUser" class="font-weight-bold text-decoration-underline">Nickname</span>
							<span id="emailUser" class="font-weight-bold text-decoration-underline">Email</span>
						</div>
					</div>
					<div class="col-md-5">
						<div class="row">
							<div class="col-md-12 py-1">
								<label class="labels" for="nombreUser">Nombre</label>
								<input id="nombreUser" type="text" class="form-control" readonly>
							</div>
							<div class="col-md-12 py-1">
								<label class="labels" for="apellidoUser">Apellido</label>
								<input id="apellidoUser" type="text" class="form-control" readonly>
							</div>
							<div class="col-md-12 py-1">
								<label class="labels" for="fechaNacUser">Fecha de Nacimiento</label>
								<input id="fechaNacUser" type="date" class="form-control" readonly>
							</div>
							
							<!-- Solo si es turista -->
							<div id="nacionalidadDiv" class="col-md-12 py-1 d-none">
								<label class="labels" for="nacionalidadUser">Nacionalidad</label>
								<input id="nacionalidadUser" type="text" class="form-control" readonly>
							</div>

							<!-- Solo si es proveedor -->
							<div id="enlaceDiv" class="col-md-12 py-1 d-none">
								<label class="labels" for="enlaceUser">Enlace</label>
								<input id="enlaceUser" type="text" class="form-control" readonly>
							</div>
							<div id="descDiv" class="col-md-12 py-1 d-none">
								<label class="labels" for="descUser">Descripcion</label>
								<textarea id="descUser" class="form-control" style="max-height: 200px" readonly></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="row-md-6 d-flex">
					<div id="salidasProveedorDiv" class="col-md-6 py-1">
						<h3>Salidas</h3>
						<ul id="listarSalidasConfirmadas" class="list-group"></ul>
					</div>
					<div id="actividadesProveedorDiv" class="col-md-6 py-1 d-none">
						<h3>Actividades Confirmadas</h3>
						<ul id="listaActividadesConfirmadas" class="list-group"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="<%=ListURL.headerJSURL.getURL()%>"></script>
<script type="text/javascript" src="<%=ListURL.JSFilesURL.getURL()%>listUsers.js"></script>
</html>