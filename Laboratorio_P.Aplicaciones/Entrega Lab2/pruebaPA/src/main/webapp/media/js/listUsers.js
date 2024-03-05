const listaNicks = document.querySelector("#listaNicks")
const nickUser = document.querySelector('#nickUser')
const emailUser = document.querySelector('#emailUser')
const nombreUser = document.querySelector('#nombreUser')
const apellidoUser = document.querySelector('#apellidoUser')
const fechaNacUser = document.querySelector('#fechaNacUser')

const cargarInfoGeneral = (nick, email, nombre, apellido, fechaNac, salidas) => {
	nickUser.innerHTML = nick
	emailUser.innerHTML = email
	nombreUser.value = nombre
	apellidoUser.value = apellido
	fechaNacUser.value = fechaNac

	const listarSalidasConfirmadas = document.querySelector('#listarSalidasConfirmadas')
	listarSalidasConfirmadas.innerHTML = ''
	if (salidas.length == 0) {
		listarSalidasConfirmadas.innerHTML = `<li class="list-group-item list-group-item-action disabled">No hay salidas asociadas</l1>`
		return;
	}
	const fragmentSalidas = document.createDocumentFragment()
	salidas.forEach(e => {
		const li = document.createElement('li')
		const a = document.createElement('a')
		const span = document.createElement('span')
		a.classList.add('nav-link', 'fs-4', 'text-decoration-underline')
		a.href = `/pruebaPA/salida?nomSal=${e.nombre}`
		a.textContent = e.nombre
		li.appendChild(a)
		span.innerHTML += `
			Lugar: ${e.lugar}<br>
			Cantidad de turistas maximos: ${e.cantMaxTuristas}<br>
			Fecha de Alta: ${e.fechaAlta}<br>
			Fecha de Salida: ${e.fechaSalida} ${e.horaSalida}`
		li.classList.add("list-group-item", "list-group-item-action")
		li.appendChild(span)
		fragmentSalidas.appendChild(li)
	})
	listarSalidasConfirmadas.appendChild(fragmentSalidas)
}

const cargarInfoProveedor = (link, desc, actividades) => {
	document.querySelector('#enlaceUser').value = link
	document.querySelector('#descUser').value = desc
	const listaActividadesConfirmadas = document.querySelector('#listaActividadesConfirmadas')
	listaActividadesConfirmadas.innerHTML = ''
	if (actividades.length == 0) {
		listaActividadesConfirmadas.innerHTML = `<li class="list-group-item list-group-item-action disabled">No hay actividades asociadas</l1>`
		return;
	}
	const fragmentActividades = document.createDocumentFragment()
	actividades.forEach(e => {
		const li = document.createElement('li')
		const a = document.createElement('a')
		const span = document.createElement('span')
		a.classList.add('nav-link', 'fs-4', 'text-decoration-underline')
		a.href = `/pruebaPA/consact?nombreAct=${e.nombre}`
		a.textContent = e.nombre
		li.appendChild(a)
		estado = e.estado
		estado = `${estado[0].toUpperCase()}${estado.slice(1).toLowerCase()}`
		span.innerHTML += estado
		li.classList.add("list-group-item", "list-group-item-action")
		li.appendChild(span)
		fragmentActividades.appendChild(li)
	})
	listaActividadesConfirmadas.appendChild(fragmentActividades)
}

listaNicks.addEventListener('click', e => {
	const nickSelected = e.target.getAttribute('data-nick-id')
	const liActual = e.target.parentElement.querySelector('li.active')
	if (liActual && liActual != e.target) liActual.classList.remove('active')
	if (liActual == e.target) return
	fetch(`/pruebaPA/api/getUserInfo?nick=${nickSelected}`)
		.then(ret => ret.json())
		.then(ret => {
			if (!ret.success)
				return alert(ret.message)
			alternarProveedorTurista((ret.nacion == null), !(ret.nacion == null))
			cargarInfoGeneral(ret.nick, ret.email, ret.nombre, ret.apellido, ret.fechaNac, ret.salidas)
			if (ret.nacion == null) {
				cargarInfoProveedor(ret.link, ret.desc, ret.actividades)
			} else {
				document.querySelector('#nacionalidadUser').value = ret.nacion
			}


			document.querySelector('#img-profile-select').src = ret.urlImgProfile
			e.target.classList.add('active')
		})
})


const alternarProveedorTurista = (showProveedor, showTurista) => {
	const nacionalidad = document.querySelector('#nacionalidadDiv')
	const enlace = document.querySelector('#enlaceDiv')
	const desc = document.querySelector('#descDiv')
	const actividadesProveedorDiv = document.querySelector('#actividadesProveedorDiv')
	nacionalidad.classList.toggle('d-none', !showTurista)
	enlace.classList.toggle('d-none', !showProveedor)
	desc.classList.toggle('d-none', !showProveedor)
	actividadesProveedorDiv.classList.toggle('d-none', !showProveedor)
}