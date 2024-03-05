<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="datatype.DtUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<% 
	HttpSession sesion = request.getSession(false); 
	DtUsuario dtUser = (DtUsuario)sesion.getAttribute("dtUser");
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String fechaParse = dtUser.getFechaNac().format(formato).toString();
%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mi perfil</title>
<link rel="stylesheet" href="<%=ListURL.BootstrapURL.getURL()%>">
<link rel="stylesheet" href="<%=ListURL.headerStylesURL.getURL()%>">
<style type="text/css">
    .profile-image {
      position: relative;
	}

    .profile-image:hover::before {
      content: "Cambiar imagen";
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(0, 0, 0, 0.5);
      color: #fff;
      text-align: center;
      display: flex;
      align-items: center;
      justify-content: center;
      cursor: pointer;
    }

    .file-input {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      opacity: 0;
      cursor: pointer;
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
	<div class="container rounded mt-5 mb-5">
		<div class="row-md-6 d-flex justify-content-center">
			<div class="col-md-3 col-sm-12">
				<div class="d-flex gap-3 flex-column align-items-center text-center p-3 py-5">
					<label class="profile-image">
						<img id="img-profile-select" class="rounded-circle" width="150px" src="<%=sesion.getAttribute("urlImgProfile")%>">
				      	<input type="file" class="file-input" accept="image/*" id="fileInput">
					</label>
					<span class="font-weight-bold text-decoration-underline"><%= dtUser.getNick()%></span>
					<span class="font-weight-bold text-decoration-underline"><%=dtUser.getEmail() %></span>
				</div>
			</div>
			<div class="col-md-5">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-right">Profile Settings</h4>
					</div>
					<form action="/pruebaPA/profile" method="POST" class="needs-validation" id="profileForm" novalidate>
						<div class="row mt-3">
							<div class="col-md-12 py-1">
								<label class="labels" for="nombreEdit">Nombre</label>
								<input id="nombreEdit" type="text" class="form-control" value="<%= dtUser.getNom()%>" required pattern="^\S(.*\S)?$">
							</div>
							<div class="col-md-12 py-1">
								<label class="labels" for="apellidoEdit">Apellido</label>
								<input id="apellidoEdit" type="text" class="form-control" value="<%= dtUser.getApe()%>" required pattern="^\S(.*\S)?$">
							</div>
							<div class="col-md-12 py-1">
								<label class="labels" for="fechaNacEdit">Fecha de Nacimiento</label>
								<input id="fechaNacEdit" type="date" class="form-control" value="<%= fechaParse%>" max="<%=java.time.LocalDate.now()%>" required>
							</div>
							<% if (sesion.getAttribute("userType").equals("Turista")) { %>
							
							<div class="col-md-12 py-1">
								<label class="labels" for="nacionalidadEdit">Nacionalidad</label>
								<input id="nacionalidadEdit" type="text" class="form-control" value="<%= dtUser.getNacion()%>" required pattern="^\S(.*\S)?$">
							</div>
							<% } else if (sesion.getAttribute("userType").equals("Proveedor")) { %>
							<div class="col-md-12 py-1">
								<label class="labels" for="enlaceEdit">Enlace</label>
								<input id="enlaceEdit" type="text" class="form-control" value="<%= dtUser.getLink()%>">
							</div>
							<div class="col-md-12 py-1">
								<label class="labels" for="descEdit">Descripcion</label>
								<textarea id="descEdit" class="form-control" required pattern="^\S(.*\S)?$" style="max-height: 200px"><%= dtUser.getDesc()%></textarea>
							</div>
							<% } %>
							<div class="mt-5 text-center">
								<input class="btn btn-primary profile-button" 
								type="submit" value="Guardar Cambios"> 
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
<script src="<%=ListURL.headerJSURL.getURL()%>"></script>
<script src="<%=ListURL.JSFilesURL.getURL()%>profileFunction.js"></script>

</html>