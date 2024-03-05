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

const USER_TYPE = {
  PROVEEDOR: 'Proveedor',
  TURISTA: 'Turista'
}
const getProfile = () => {
  	const data = new FormData()
 
  	const fechaNac = new Date(document.getElementById('fechaNacEdit').value)
  	let day = fechaNac.getDate() +1
  	let month = fechaNac.getMonth() +1
  	day = day < 10 ? '0' + day : day
  	month = month < 10 ? '0' + month : month
	const fileInput = document.querySelector('#fileInput')
	
	 
	data.append('nombre', document.querySelector('#nombreEdit').value)
  	data.append('apellido', document.querySelector('#apellidoEdit').value)
  	data.append('fechaNac', `${day}-${month}-${fechaNac.getFullYear()}`)
  	data.append('userType', document.querySelector('#nacionalidadEdit') === null ? USER_TYPE.PROVEEDOR : USER_TYPE.TURISTA)
  	if (data.get('userType') === USER_TYPE.TURISTA) {
  		data.append('nacionalidad', document.querySelector('#nacionalidadEdit').value)
  	}
  	if (data.get('userType') === USER_TYPE.PROVEEDOR) {
		data.append('enlace', document.querySelector('#enlaceEdit').value)
		data.append('desc', document.querySelector('#descEdit').value)
	}
  	data.append('image', fileInput.files[0])
	return data
}
const infoUser = getProfile()

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
function areFormDataEqual(formData1, formData2) {
  // Obtén las entradas (pares clave-valor) de ambos FormData
  const entries1 = formData1.entries();
  const entries2 = formData2.entries();

  // Convierte las entradas en matrices para facilitar la comparación
  const array1 = Array.from(entries1);
  const array2 = Array.from(entries2);

  // Compara las matrices de entradas
  if (array1.length !== array2.length) {
    return false; // Los FormData tienen diferentes cantidades de entradas
  }

  // Compara cada entrada en las matrices
  for (let i = 0; i < array1.length; i++) {
    const [key1, value1] = array1[i];
    const [key2, value2] = array2[i];

    if (key1 !== key2 || value1 !== value2) {
      return false; // Las entradas no coinciden
    }
  }

  // Si todas las entradas coinciden, los FormData son iguales
  return true;
}




const profileEdit = document.querySelector('#profileForm')
profileEdit.addEventListener('submit', e => {
	e.preventDefault()
	if (!profileEdit.checkValidity()) return

	const newData = getProfile()
	
	if (areFormDataEqual(infoUser, newData)) 
		return
	
	fetch("/pruebaPA/profile", {
		method: 'POST',
		body: newData
	})
	.then(res => res.json())
	.then(ret => {
		if (ret.success) {
			let msg = ""
			if (ret.successEditText) {
				msg+="Modificado correctamente"
				if (!ret.successEditImg)
					msg+=", aunque hubo un error al modificar la imagen."
				else {
					msg+= "."
					document.querySelector('#img-profile-select').src = ret.urlImage					
				}
			} else if (ret.successEditImg) {
				msg+="Imagen modificada correctamente"
			}
			mostrarModal({title: 'Cambios guardados correctamente', msg })
			document.querySelector('#fileInput').files = null
			for (var [key, value] of newData.entries()) 
				infoUser.set(key, value);
		}
		else
			mostrarModal({title: 'Error al guardar', msg: ret.message})
	})
})

