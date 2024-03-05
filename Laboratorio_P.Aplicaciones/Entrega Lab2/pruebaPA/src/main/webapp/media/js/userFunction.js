const alternarLoginRegistarReal = () => {
	const $divRegister = document.getElementById("divRegister")
	const $divLogin = document.getElementById("divLogin")
	const $txtMedio = document.getElementById("textoCompartido").querySelector('h1')
	const esRegister = Boolean(new URL(window.location.href).searchParams.get('r'))
	if (esRegister) {
		$divRegister.classList.remove('d-none')
		$divLogin.classList.add('d-none')
		$txtMedio.innerHTML = "Crea tu cuenta"
		document.title = "Registrarse"
	} else {
		$divRegister.classList.add('d-none')
		$divLogin.classList.remove('d-none')
		$txtMedio.innerHTML = "Inicia tu cuenta"
		document.title = "Inicia Sesion"
	}
}
alternarLoginRegistarReal()

document.getElementById('toLogin').addEventListener('click', () => {
	const currentUrl = new URL(window.location.href); // Obtén la URL actual
	currentUrl.searchParams.delete('r'); // Elimina un parámetro de consulta (en este caso, 'r')
	window.history.replaceState({}, '', currentUrl); // Actualiza la URL en la barra de direcciones sin recargar la página
	alternarLoginRegistarReal()
})
document.getElementById('toRegister').addEventListener('click', () => {
	const currentUrl = new URL(window.location.href); // Actualiza la URL sin recargar la página
	if (currentUrl.searchParams.get('r')) return
	currentUrl.searchParams.append('r', 'true'); // Agrega un parámetro de consulta
	window.history.pushState({}, '', currentUrl); // Actualiza la URL sin recargar la página
	document.getElementById("divLogin").classList.add('animacionLoginDerecha');
	alternarLoginRegistarReal()
})

const mostrarCampos = () => {
	const seleccion = document.getElementById("userType").value
	// Muestra el campo de entrada específico según la opción seleccionada
	if (seleccion === "Proveedor") {
		document.getElementById("linkAndDesc").classList.remove("d-none")
		document.getElementById("nacionDiv").classList.add("d-none")
		document.getElementById("link").readOnly = false
		document.getElementById("desc").readOnly = false
		document.getElementById("nacionalidad").readOnly = true
	} else if (seleccion === "Turista") {
		document.getElementById("nacionDiv").classList.remove("d-none")
		document.getElementById("linkAndDesc").classList.add("d-none")
		document.getElementById("link").readOnly = true
		document.getElementById("desc").readOnly = true
		document.getElementById("nacionalidad").readOnly = false
	} else {
		document.getElementById("nacionDiv").classList.add("d-none")
		document.getElementById("linkAndDesc").classList.add("d-none")
		document.getElementById("link").readOnly = true
		document.getElementById("desc").readOnly = true
		document.getElementById("nacionalidad").readOnly = true
	}
}
mostrarCampos()

; (() => {
	'use strict'

	// Obtenes todos los elementos con la clase needs-validation
	const forms = document.querySelectorAll('.needs-validation')

	// Se convierte lo anterior en un array y se itera con un foreach, en cada uno al hacer submit se agrega el evento de la arrow function
	Array.from(forms).forEach(form => {
		form.addEventListener('submit', event => {
			if (!form.checkValidity()) {
				event.preventDefault()
				event.stopPropagation()
			}
			form.classList.add('was-validated')
		}, false)
	})
})();

const $formLogin = document.getElementById('formLogin')
$formLogin.addEventListener('submit', e => {
	e.preventDefault()
	if (!$formLogin.checkValidity()) return
	const data = {
		"nickNameLogin": document.getElementById('nickNameLogin').value,
		"passwordLogin": document.getElementById('passwordLogin').value
	}
	fetch('/pruebaPA/login', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(data)
	})
	.then(res => res.json())
	.then(data => {
		if (data.success)
			window.location.href = data.redirect
		else {
			mostrarErrorModal({ title:'Error al Iniciar Sesion', msg: data.message, btn: { show: false } })
		}
	})
})


const $formRegister = document.getElementById('formRegister')

$formRegister.addEventListener('submit', e => {
	e.preventDefault()
	if (!$formRegister.checkValidity()) return
	
	
	const fechaNac = new Date(document.getElementById('fechaNac').value)
  	let day = fechaNac.getDate() +1
  	let month = fechaNac.getMonth() +1
  	day = day < 10 ? '0' + day : day
  	month = month < 10 ? '0' + month : month
	let year = fechaNac.getFullYear()
	
	
	fechaNacimiento = `${day}-${month}-${year}`
	
	const data = {
		"nickName": document.getElementById('nickName').value,
		"email": document.getElementById('email').value,
		"nombre": document.getElementById('nameUser').value,
		"apellido": document.getElementById('apellido').value,
		"fechaNac": fechaNacimiento.toString(),
		"userType": document.getElementById('userType').value,
		"nacionalidad": document.getElementById('nacionalidad').value,
		"link": document.getElementById('link').value,
		"desc": document.getElementById('desc').value,
		"password": document.getElementById('password').value,
		"passwordConfirmation": document.getElementById('passwordConfirmation').value
	}
	
	
	
	if (data.nickName.trim() === '')
		return
	if (data.email.trim() === '')
		return
	if (data.nombre.trim() === '')
		return
	if (data.apellido.trim() === '')
		return
	if (data.userType.trim() === '')
		return
	if(data.userType === "Proveedor") {
		if (data.desc.trim() === '')
			return
	}
	if (data.userType === "Turista" && data.nacionalidad.trim() === '')
		return
	
	
	fetch('/pruebaPA/user', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(data)
	})
	.then(res => res.json())
	.then(data => {
		if (data.success) {
			mostrarErrorModal({ title: data.message, msg: data.message, btn: { show: true, url: data.redirect } })
		} else {
			mostrarErrorModal({ title:'Error al registrarse', msg: data.message, btn: { show: false } })
		}
	})
})

const password = document.getElementById("password");
const confirmPassword = document.getElementById("passwordConfirmation");

password.addEventListener("input", function() {
	confirmPassword.pattern = "^" + password.value.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&') + "$";
});

const passwordConfirmationError = document.getElementById("passwordMatchError");

confirmPassword.addEventListener("input", () => {
	if (confirmPassword.value.length > 0 && password.value !== confirmPassword.value) 
		passwordConfirmationError.style.display = "block";
	else
		passwordConfirmationError.style.display = "none";
});


const mostrarErrorModal = ({ title, msg, btn } ) => {
	document.body.style.overflow = "hidden"
	if (document.documentElement.scrollHeight > window.innerHeight)
		document.body.style.paddingRight = "17px"
	document.getElementById("modal").style.display = "block"
	document.getElementById("modalTitle").textContent = title
	document.getElementById("modalBody").textContent = msg
	if (btn.show) {
		document.getElementById('modalFooter').querySelector('.optionalBtn').style.display  = "inline-block"
		document.getElementById('modalFooter').querySelector('.optionalBtn').onclick = () => {location.href = btn.url}
	} else {
		document.getElementById('modalFooter').querySelector('.optionalBtn').style.display = "none"
	}
}
const ocultarModalError = () => {
 	document.body.style.overflow = "auto"
	document.body.style.paddingRight = ""
	document.getElementById("modal").style.display = "none"
	document.getElementById('modalFooter').querySelector('.optionalBtn').style = "none"
}

