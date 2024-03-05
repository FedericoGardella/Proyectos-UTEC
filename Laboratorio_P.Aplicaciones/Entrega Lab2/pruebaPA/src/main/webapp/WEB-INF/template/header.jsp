<%@ page import="com.pruebaPA.config.ListURL" %>
<%@ page import="com.pruebaPA.models.EstadosLogin" %>
<% HttpSession sesion = request.getSession(false); %>

<div id="header">
    <nav class="navbar navbar-expand-lg bg-body-tertiary position-relative">
        <div class="container-fluid fs-5">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<%= ListURL.HomeURL.getURL() %>">Home</a>
                    </li>
                    <li class="nav-item">
						<a class="nav-link" href="<%=ListURL.SalidaURL.getURL() %>">Salidas</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=ListURL.ConsActURL.getURL() %>">Actividades</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=ListURL.PaqueteURL.getURL() %>">Paquetes</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=ListURL.ListUsers.getURL() %>">Usuarios</a>
					</li>
                </ul>
                <% if (sesion.getAttribute("estado_sesion") == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN) {%>
                <ul class="navbar-nav">
					<li class="nav-item">
				    	<a class="nav-link" href="<%=ListURL.LoginURL.getURL() %>">Inicia Sesion</a>
					</li>
					<li class="nav-item">
				    	<a class="nav-link" href="<%=ListURL.RegisterURL.getURL() %>">Registrate</a>
					</li>
                </ul>		
				<% } else { %>
                <ul class="navbar-nav me-5">
					<li class="navbar-text dropstart">
						<button class="span-like-button" type="button"
							data-bs-toggle="dropdown" 
						><%=sesion.getAttribute("userNick") %>
						</button>
						<ul class="dropdown-menu">
				            <li><a class="dropdown-item" href="<%=ListURL.ProfileURL.getURL() %>">Mi perfil</a></li>
				            <% if (sesion.getAttribute("userType").equals("Proveedor")) { %>
				            <!-- Esta parte solo la ven los proveedores -->
		          			<li><a class="dropdown-item" href="<%=ListURL.ActURL.getURL() %>">Agregar Actividad</a></li>
		          			<li><a class="dropdown-item" href="<%=ListURL.AltaSalidaURL.getURL() %>">Agregar Salida</a></li>
		          			<%} %>
		          			<% if (sesion.getAttribute("userType").equals("Turista")) { %>
				            <!-- Esta parte solo la ven los turistas -->
		          			<li><a class="dropdown-item" href="<%=ListURL.ComprapaqURL.getURL() %>">Comprar Paquete</a></li>
		          			<%} %>
				            <li><hr class="dropdown-divider"></li>
				            <li><a class="dropdown-item" href="<%=ListURL.LogoutURL.getURL() %>">Cerrar Sesion</a></li>
		          		</ul>
					</li>
                </ul>		
				<% } %>
        </div>
    </nav>
</div>