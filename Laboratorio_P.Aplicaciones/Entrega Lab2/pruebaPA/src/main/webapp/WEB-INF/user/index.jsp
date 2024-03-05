<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="<%=ListURL.BootstrapURL.getURL()%>">
<style type="text/css">
	.form-control:not(select) {
	    padding: 0.5rem;
	}
	.form-control::placeholder {
	    color: #ccc;
	    font-weight: bold;
	    font-size: 0.9rem;
	}
	.form-control:focus {
	    box-shadow: none;
	}
	
	.custom-datepicker::-webkit-calendar-picker-indicator {
	    filter: invert(1);
	}
	#passwordMatchError {
	    position: absolute;
	    top: 100%;
	    z-index: 5;
	    display: none;
	    max-width: 100%;
	    padding: 0.25rem 0.5rem;
	    margin-top: 0.1rem;
	    font-size: .875rem;
	    color: #fff;
	    background-color: var(--bs-danger);
	    border-radius: var(--bs-border-radius);
	}
	#modalError {
		background-color: rgba(0, 0, 0, 0.5);
	}
</style>
</head>
<body>

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
	        <button type="button" class="btn btn-secondary optionalBtn" data-bs-dismiss="modal">Ir al Home</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="ocultarModalError()">Close</button>
	      </div>
	    </div>
	  </div>
	</div>


	<div class="container">
		<div class="row vh-100 py-5 justify-content-center align-items-center">
			
			<div id="divLogin" class="col-md-7 col-lg-4 ml-auto d-none">
				<form id="formLogin" action="login" method="POST" class="needs-validation" novalidate>
					<div class="row">
						<!-- Nickname -->
						<div class="input-group col-lg-6 mb-4">
							<input id="nickNameLogin" type="text" name="nickNameLogin"
								placeholder="Nickname"
								class="form-control bg-white border-left-0 border-md text-dark" required>
						</div>

						<!-- Password -->
						<div class="input-group col-lg-6 mb-4">
							<input id="passwordLogin" type="password" name="passwordLogin"
								placeholder="Password" minlength="8"
								class="form-control bg-white border-left-0 border-md text-dark" required>
							<div class="invalid-tooltip">
								Debe poseer minimo 8 caracteres.
							</div>
						</div>
						
						<!-- Submit Button -->
						<div class="form-group col-lg-12 mx-auto mb-0">
							<input type="submit" class="btn btn-primary btn-block py-2" value="Inicia Sesion">
						</div>
						<!-- Already Registered -->
						<div class="text-center w-100">
							<p class="text-muted font-weight-bold">
								¿Aun no tienes cuenta? 
								<button id="toRegister" type="button" class="btn btn-link text-primary">Registrate</button>
								<br>Volver al
								<a href="<%=ListURL.HomeURL.getURL() %>" class="text-primary">Inicio</a>
							</p>
						</div>

					</div>
				</form>
			</div>
			
			<div id="textoCompartido" class="col-md-4 pr-lg-5 mb-5 mb-md-0">
				<jsp:include page="/media/img/loginRegister.svg"/>
				<h1 style="text-align:center">Inicia tu cuenta</h1>
			</div>

			<!-- Registeration Form -->
			<div id="divRegister" class="col-md-4 col-lg-4 ml-auto d-none">
				<form action="/pruebaPA/user" method="POST" class="needs-validation" id="formRegister" novalidate>
					<div class="row">

						<!-- Nickname -->
						<div class="input-group col-lg-6 mb-4">
							<input id="nickName" type="text" name="nickName"
								placeholder="Nickname"
								class="form-control bg-white border-left-0 border-md text-dark" required pattern="^\S(.*\S)?$">
						</div>

						<!-- Email -->
						<div class="input-group col-lg-6 mb-4">
							<input id="email" type="email" name="email"
								placeholder="Email"
								class="form-control bg-white border-left-0 border-md text-dark" required pattern="^\S(.*\S)?$">
						</div>

						<!-- Nombre -->
						<div class="input-group col-lg-12 mb-4">
							<input id="nameUser" type="text" name="nameUser"
								placeholder="Nombre"
								class="form-control bg-white border-left-0 border-md text-dark" required pattern="^\S(.*\S)?$">
						</div>

						<!-- Apellido -->
						<div class="input-group col-lg-12 mb-4">
							<input id="apellido" type="text" name="apellido"
								placeholder="Apellido"
								class="form-control bg-white border-md border-left-0 pl-3 text-dark" required pattern="^\S(.*\S)?$">
						</div>
						
						<!-- Fecha de Nacimiento -->
						<div class="input-group col-lg-12 mb-4">
							<input id="fechaNac" type="date" name="fechaNac" max="<%=java.time.LocalDate.now()%>"
								placeholder="Fecha de Nacimiento"
								class="form-control bg-white border-md border-left-0 pl-3 text-dark custom-datepicker" required>
						</div>
						
						<!-- Tipo de Usuario -->
						<div class="input-group col-lg-12 mb-4">
							<select id="userType" name="userType" onchange="mostrarCampos()"
								class="form-control custom-select bg-white border-left-0 border-md text-dark" required>
								<option selected disabled value="">Selecciona tu rol</option>
								<option value="Proveedor">Proveedor</option>
								<option value="Turista">Turista</option>
							</select>
						</div>
						
						<!-- Nacionalidad -->
						<div id="nacionDiv" class="input-group col-lg-12 mb-4">
							<input id="nacionalidad" type="text" name="nacionalidad"
								placeholder="Nacionalidad"
								class="form-control bg-white border-md border-left-0 pl-3 text-dark" required pattern="^\S(.*\S)?$">
						</div>
						
						<div id="linkAndDesc">
							<!-- Link Web -->
							<div class="input-group col-lg-12 mb-4">
								<input id="link" type="text" name="link"
									placeholder="Link Web"
									class="form-control bg-white border-md border-left-0 pl-3 text-dark">
							</div>
							
							<!-- Descripcion -->
							<div class="input-group col-lg-12 mb-4 ">
								<textarea class="form-control bg-white border-md border-left-0 pl-3 text-dark"
									placeholder="Escribe una descripcion" style="height: 120px; resize: none;"
									id="desc" name="desc" required pattern="^\S(.*\S)?$"></textarea>
							</div>
						</div>

						<!-- Password -->
						<div class="input-group col-lg-6 mb-4">
							<input id="password" type="password" name="password"
								placeholder="Password" minlength="8"
								class="form-control bg-white border-left-0 border-md text-dark" required pattern="^\S(.*\S)?$">
							<div class="invalid-tooltip">
								Debe poseer minimo 8 caracteres.
							</div>
						</div>

						<!-- Password Confirmation -->
						<div class="input-group col-lg-6 mb-4">
							<input id="passwordConfirmation" type="password"
								name="passwordConfirmation" placeholder="Confirm Password" minlength="8"
								class="form-control bg-white border-left-0 border-md text-dark" required pattern="">
							<div id="passwordMatchError">Las contraseñas no coinciden.</div>
						</div>
						<!-- Submit Button -->
						<div class="form-group col-lg-12 mx-auto mb-0">
							<input type="submit" class="btn btn-primary btn-block py-2" value="Crea tu cuenta">
						</div>
						
						<!-- Already Registered -->
						<div class="text-center w-100">
							<p class="text-muted font-weight-bold">
								¿Ya estas registrado?
								<button id="toLogin" type="button" class="btn btn-link text-primary">Inicia Sesion</button>
								<br>Volver al
								<a href="<%=ListURL.HomeURL.getURL() %>" class="text-primary">Inicio</a>
							</p>
						</div>

					</div>
				</form>
			</div>
		
		</div>
	</div>

</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>userFunction.js" ></script>
</html>