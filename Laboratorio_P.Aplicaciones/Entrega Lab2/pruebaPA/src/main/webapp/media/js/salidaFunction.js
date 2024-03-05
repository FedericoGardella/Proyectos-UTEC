document.addEventListener("DOMContentLoaded", () => {
	fetch('/pruebaPA/getd')
	.then(res => res.json())
	.then(data => {
		const select = document.getElementById('departamentos');
		const arrd = data.departamentos;
		for (let i = 0; i < arrd.length; i++){
			select.innerHTML += `<option value="${arrd[i].nombre}">${arrd[i].nombre}</option>`;
		}
	})
})

document.getElementById('departamentos').addEventListener('change', () => {
		const selecta = document.getElementById('actividades')
		selecta.innerHTML = '<select id="actividades" name="actividades" class="form-select" aria-label="Default select example"><option selected>Actividades</option></select>';
		const data = {
			nomDep: document.getElementById('departamentos').value,
		}	
		fetch('/pruebaPA/geta', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
		const arra = data.actividades;
		for (let i = 0; i < arra.length; i++){
			selecta.innerHTML += `<option value="${arra[i].nombre}">${arra[i].nombre}</option>`;
		}
	})
});

const boton = document.getElementById('botonn');

boton.addEventListener('click', () =>{
	
	const data = new FormData();

    const fileInput = document.querySelector('#fileInput');

	const fech = document.getElementById('fecha')
	
	if (fech.value === ""){
		mensaje.textContent = 'Debe rellenar todos los campos';
		mensaje.style.color = 'red';	
		return
	}
	
	const fecha = new Date(fech.value)
  	let day = fecha.getDate() +1
  	let month = fecha.getMonth() +1
  	day = day < 10 ? '0' + day : day
  	month = month < 10 ? '0' + month : month
	let year = fecha.getFullYear()
	
	const fechaa = `${day}-${month}-${year}`
	
	 
	
	const departamento =  document.getElementById('departamentos');
	const actividad =  document.getElementById('actividades');
	const nombre = document.getElementById('nombre');
	const hora = document.getElementById('hora');
	const lugar = document.getElementById('lugar');
	const cantidad = document.getElementById('cantidad');

	data.append('departamento', departamento.value);
	data.append('actividad', actividad.value);
	data.append('nombre', nombre.value);
	data.append('hora', hora.value);
	data.append('lugar', lugar.value);
	data.append('cantidad', cantidad.value);
	data.append('fecha', fechaa);
	if(fileInput.files[0] != null){
		data.append('hayImage', "true");
		data.append('image', fileInput.files[0]);
	}else{
		data.append('hayImage', "false")
	}

	
	
	if ((nombre.value == '')  || (hora.value == '') || (lugar.value == '')
		|| (actividad.value == '0') || (departamento.value == '0') || (cantidad.value == '')){
		mensaje.textContent = 'Debe rellenar todos los campos';
		mensaje.style.color = 'red';	
		return
	}

	
	
	fetch('/pruebaPA/salida', {
		method: 'POST',
		body: data
	})
	.then(res => res.json())
	.then(data => {
		if (data.success){
			departamento.value = "",
			actividad.value = "",
			nombre.value = "",
			hora.value = "",
			lugar.value = "",
			fech.value = "",
			cantidad.value = "",
			mensaje.textContent = 'Salida registrada correctamente!';
			mensaje.style.color = 'green';
		}
		else{
			console.log(data.message);
			if(data.message === 'Ya existe una salida con ese nombre')
				mensaje.textContent = 'Ya existe una salida con ese nombre';
			mensaje.style.color = 'red';
		}
	})
})