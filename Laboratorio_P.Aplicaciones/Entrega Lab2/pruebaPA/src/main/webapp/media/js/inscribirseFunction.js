const btnInscribir = document.querySelector('#btnInscribir')
const btnGeneral = document.querySelector('#btnGeneral')
const btnPaquete = document.querySelector('#btnPaquete')
const pago = document.querySelector('.pago')
const select = document.querySelector('#paquetes')
const enviar = document.querySelector('#enviar')
const enviar2 = document.querySelector('#enviar2')
const cantidad = document.querySelector('#cantidad')
const DoC = document.querySelector('#DoC').value
const act = document.querySelector('#nombreAct').value
const sal = document.querySelector('#nombreSal').value


btnInscribir.addEventListener('click', () => {
	btnGeneral.style.display = "flex";
	btnPaquete.style.display = "flex";
	pago.style.display = "flex";
})

btnGeneral.addEventListener('click', () => {
	cantidad.style.display = "flex";
	enviar.style.display = "flex";
})

btnPaquete.addEventListener('click', () => {
	const dat = {
		nomSal: sal,
	}
	fetch('/pruebaPA/vpd', {
		method: 'POST',
		headers: {'content-type' : 'application\json'},
		body: JSON.stringify(dat)
	}).then(ret=> ret.json())
	.then(ret => {
		if(ret.vacio == "false"){
			select.style.display = "flex";
			enviar2.style.display = "flex";
			cantidad.style.display = "flex";
			const arrp = ret.paquetes;
			select.innerHTML = `<select class="form-select bajo-boton selecto" id="paquetes"></select>`;
			for (let i = 0; i < arrp.length; i++){
				select.innerHTML += `<option value="${arrp[i].nombre}">${arrp[i].nombre}</option>`;
			}
		}else{
			mostrarModal({title: 'No hay paquetes comprados con esta salida', msg: ""})
		}
	})
})


enviar.addEventListener('click', () => {
	const data = {
		esDep: type,
		DoC,
		act,
		sal,
		cant: cantidad.value
	}
	
	if (cantidad.value == "")
		return;
	
	fetch('/pruebaPA/inscribirseSalida', {
		method: 'POST',
		headers: {'content-type' : 'application\json'},
		body: JSON.stringify(data)
	}).then(ret=> ret.json())
	.then(ret => {
		if (ret.success) {
			mostrarModal({title: 'Cambios guardados correctamente', msg: ret.message })			
		} else {
			mostrarModal({title: 'Error al intentar inscribirse', msg: ret.message})
		}
	})
})


enviar2.addEventListener('click', () => {
	const data = {
		esDep: type,
		DoC,
		act,
		sal,
		cant: cantidad.value,
		nomp: select.value
	}
	
	if (cantidad.value == "")
		return;
	
	fetch('/pruebaPA/isp', {
		method: 'POST',
		headers: {'content-type' : 'application\json'},
		body: JSON.stringify(data)
	}).then(ret=> ret.json())
	.then(ret => {
		if (ret.success) {
			mostrarModal({title: 'Cambios guardados correctamente', msg: ret.message })			
		} else {
			mostrarModal({title: 'Error al intentar inscribirse', msg: ret.message})
		}
	})
})

function inscribirse(){
	
	const data = {
		esDep: type,
		DoC,
		act,
		sal
	}
	
	fetch('/pruebaPA/inscribirseSalida', {
		method: 'POST',
		headers: {'content-type' : 'application\json'},
		body: JSON.stringify(data)
	}).then(ret=> ret.json())
	.then(ret => {
		if (ret.success) {
			mostrarModal({title: 'Cambios guardados correctamente', msg: ret.message })			
		} else {
			mostrarModal({title: 'Error al intentar inscribirse', msg: ret.message})
		}
	})
}

const mostrarModal = ({ title, msg } ) => {
	document.body.style.overflow = "hidden"
	if (document.documentElement.scrollHeight > window.innerHeight)
		document.body.style.paddingRight = "17px"
	document.getElementById("modal").style.display = "block"
	document.getElementById("modalTitle").textContent = title
	document.getElementById("modalBody").textContent = msg
}

const ocultarModalError = () => {
 	document.body.style.overflow = "auto"
	document.body.style.paddingRight = ""
	document.getElementById("modal").style.display = "none"
}