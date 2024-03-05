
var radioInput = document.getElementById('opciones_1');
var radioInput2 = document.getElementById('opciones_2');
const select = document.getElementById('CoD');
const acts = document.getElementById('actividades');
const sals = document.getElementById('salidas');
const paqs = document.getElementById('paquetes');
const cates = document.getElementById('lista-categorias');
var url = new URL(window.location.href);
var nombreAct = url.searchParams.get("nombreAct");



radioInput.addEventListener('change', function() {
    if (this.checked) {
		radioInput2.checked = false;
		select.innerHTML = `<select class="form-select" id="CoD" aria-label="Default select example"></select>`;
		acts.innerHTML = `<select class="form-select" id="actividades" aria-label="Default select example"></select>`;
        fetch('/pruebaPA/getd')
		.then(res => res.json())
		.then(data => {
			const arrd = data.departamentos;
			select.innerHTML += `<option value="0">Departamentos</option>`;
			for (let i = 0; i < arrd.length; i++){
				select.innerHTML += `<option value="${arrd[i].nombre}">${arrd[i].nombre}</option>`;
			}
		})
    }
});

radioInput2.addEventListener('change', function() {
    if (this.checked) {
		radioInput.checked = false;
		select.innerHTML = `<select class="form-select" id="CoD" aria-label="Default select example"></select>`;
		acts.innerHTML = `<select class="form-select" id="actividades" aria-label="Default select example"></select>`;
        fetch('/pruebaPA/getc')
		.then(res => res.json())
		.then(data => {
			const arrd2 = data.categorias;
			select.innerHTML += `<option value="0">Categorias</option>`;
			for (let i = 0; i < arrd2.length; i++){
				select.innerHTML += `<option value="${arrd2[i].nombre}">${arrd2[i].nombre}</option>`;
			}
		})
    }
});

document.getElementById('CoD').addEventListener('change', () => {
	if(document.getElementById('CoD').value == "0")
		return;
	if (radioInput.checked) {
	const data = {
			nomDep: document.getElementById('CoD').value,
		}	
		fetch('/pruebaPA/geta', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			const arra = data.actividades;
			acts.innerHTML = `<select class="form-select" id="actividades" aria-label="Default select example"></select>`;
			acts.innerHTML += `<option value="0">Actividades</option>`;
			for (let i = 0; i < arra.length; i++){
				acts.innerHTML += `<option value="${arra[i].nombre}">${arra[i].nombre}</option>`;
			}
    	})
    }
});

document.getElementById('CoD').addEventListener('change', () => {
	if(document.getElementById('CoD').value == "0")
		return;
	if (radioInput2.checked) {
	const data = {
			nomCat: document.getElementById('CoD').value,
		}	
		fetch('/pruebaPA/getac', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			const arra = data.actividades;
			acts.innerHTML = `<select class="form-select" id="actividades" aria-label="Default select example"></select>`;
			acts.innerHTML += `<option value="0">Actividades</option>`;
			for (let i = 0; i < arra.length; i++){
				acts.innerHTML += `<option value="${arra[i].nombre}">${arra[i].nombre}</option>`;
			}
    	})
    }
});


acts.addEventListener('change', () => {
	if(acts.value == "0")
		return;
	const dat = {
		nomAct: acts.value,
	}
//	if (radioInput.checked){
//		fetch('/pruebaPA/verc', {
//			method: 'POST',
//			headers: {'Content-Type': 'application/json'},
//			body: JSON.stringify(dat)
//		})
//		.then(res => res.json())
//		.then(dat => {
//			const arrc = dat.categorias;
//			cates.innerHTML = '';
//			cates.innerHTML += `<li class="sinpunto">Categorias: </li>`;
//			for (let i = 0; i < arrc.length; i++){
//				cates.innerHTML += `<li class="list-group-item list-group-item-action">${arrc[i].nombre}</li>`;
//			}
//		})
//	}else{
//		fetch('/pruebaPA/verc2', {
//			method: 'POST',
//			headers: {'Content-Type': 'application/json'},
//			body: JSON.stringify(dat)
//		})
//		.then(res => res.json())
//		.then(dat => {
//			const arrc = dat.categorias;
//			cates.innerHTML = '';
//			cates.innerHTML += `<li class="sinpunto">Categorias: </li>`;
//			for (let i = 0; i < arrc.length; i++){
//				cates.innerHTML += `<li class="list-group-item list-group-item-action">${arrc[i].nombre}</li>`;
//			}
//		})
//	}
		
	fetch('/pruebaPA/getdt', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(dat)
	})
	.then(res => res.json())
	.then(dat => {
		const nom = document.getElementById('nombre');
		const dur = document.getElementById('duracion');
		const cost = document.getElementById('costo');
		const ciu = document.getElementById('ciudad');
		const desc = document.getElementById('desc');
		const fech = document.getElementById('fecha');
		nom.value = dat.nombre
		dur.value = dat.duracion
		cost.value = dat.costo
		ciu.value = dat.ciudad
		desc.value = dat.descripcion
		fech.value = dat.fecha
		const arrc = dat.categorias;
		cates.innerHTML = '';
		cates.innerHTML += `<li class="sinpunto">Categorias: </li>`;
		for (let i = 0; i < arrc.length; i++){
			cates.innerHTML += `<li class="list-group-item list-group-item-action">${arrc[i].nombre}</li>`;
		}
		const image = document.getElementById('image');
		image.src= dat.imagen;
	})
	
	
//	const datos = {
//		nomAct:  acts.value,
//		CoD: document.getElementById('CoD').value,
//	}
//	fetch('/pruebaPA/getia', {
//		method: 'POST',
//		headers: {'Content-Type': 'application/json'},
//		body: JSON.stringify(datos)
//	})
//	.then(res => res.json())
//	.then(dat => {
//		const image = document.getElementById('image');
//		image.src=dat.imagen;
//	})
});

function mostrarSelect1() {;
  sals.style.display = "block";
}

function mostrarSelect2() {
  paqs.style.display = "block";
}


document.getElementById("botonver2").addEventListener("click", () => {
	if(((acts.value == "0") && (nombreAct == null)) || acts.value == "" || 
		document.getElementById('CoD').value == "0" || document.getElementById('CoD').value == "")
		return;
	if (nombreAct != null) {
		acts.value = nombreAct;
	}
	mostrarSelect1();
	const datos = {
		nomAct: acts.value,
	}
	if (radioInput.checked){
		fetch('/pruebaPA/gets', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(datos)
		})
		.then(res => res.json())
		.then(dat => {
			const arrs = dat.salidas;
			sals.innerHTML = `<select class="form-select" id="salidas" aria-label="Default select example"></select>`;
			sals.innerHTML += `<option value="0">Salidas</option>`;
			for (let i = 0; i < arrs.length; i++){
				sals.innerHTML += `<option value="${arrs[i].nombre}">${arrs[i].nombre}</option>`;
			}
		})
	}else{
		fetch('/pruebaPA/gets2', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(datos)
		})
		.then(res => res.json())
		.then(dat => {
			const arrs = dat.salidas;
			sals.innerHTML = `<select class="form-select" id="salidas" aria-label="Default select example"></select>`;
			sals.innerHTML += `<option value="0">Salidas</option>`;
			for (let i = 0; i < arrs.length; i++){
				sals.innerHTML += `<option value="${arrs[i].nombre}">${arrs[i].nombre}</option>`;
			}
		})
	}
});

document.getElementById("botonver").addEventListener("click", () => {
	if(((acts.value == "0") && (nombreAct == null)) || acts.value == "" || 
		document.getElementById('CoD').value == "0" || document.getElementById('CoD').value == "")
		return;
	if (nombreAct != null) {
		acts.value = nombreAct;
	}
	mostrarSelect2();
	const da = {
		nomAct: acts.value,
	}
	fetch('/pruebaPA/getp', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(da)
	})
	.then(res => res.json())
	.then(dat => {
		const arrp = dat.paquetes;
		paqs.innerHTML = `<select class="form-select" id="paquetes" aria-label="Default select example"></select>`;
		paqs.innerHTML += `<option value="0">Paquetes</option>`;
		for (let i = 0; i < arrp.length; i++){
			paqs.innerHTML += `<option value="${arrp[i].nombre}">${arrp[i].nombre}</option>`;
		}
	})
//	if (radioInput.checked){
//		fetch('/pruebaPA/getp', {
//			method: 'POST',
//			headers: {'Content-Type': 'application/json'},
//			body: JSON.stringify(da)
//		})
//		.then(res => res.json())
//		.then(dat => {
//			const arrp = dat.paquetes;
//			paqs.innerHTML = `<select class="form-select" id="paquetes" aria-label="Default select example"></select>`;
//			paqs.innerHTML += `<option value="0">Paquetes</option>`;
//			for (let i = 0; i < arrp.length; i++){
//				paqs.innerHTML += `<option value="${arrp[i].nombre}">${arrp[i].nombre}</option>`;
//			}
//		})
//	}else{
//		fetch('/pruebaPA/getp2', {
//			method: 'POST',
//			headers: {'Content-Type': 'application/json'},
//			body: JSON.stringify(da)
//		})
//		.then(res => res.json())
//		.then(dat => {
//			const arrp = dat.paquetes;
//			paqs.innerHTML = `<select class="form-select" id="paquetes" aria-label="Default select example"></select>`;
//			paqs.innerHTML += `<option value="0">Paquetes</option>`;
//			for (let i = 0; i < arrp.length; i++){
//				paqs.innerHTML += `<option value="${arrp[i].nombre}">${arrp[i].nombre}</option>`;
//			}
//		})
//	}
});



if (nombreAct != null) {
	
	var dato = {
		nomAct: nombreAct
	}
	
	radioInput.checked = true;
	var eventoChange = new Event('change');
	radioInput.dispatchEvent(eventoChange);
	
	var nomDep = null;
	
	fetch('/pruebaPA/setd', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(dato)
	})
	.then(res => res.json())
	.then(dat => {
		nomDep = dat.departamento;
		var valorDeseado = nomDep;
	
	    var newOption = document.createElement("option");
	    newOption.value = valorDeseado;
	    newOption.text = valorDeseado;
	    select.add(newOption);
	
		select.value = valorDeseado;
		
		eventoChange = new Event('change');
		select.dispatchEvent(eventoChange);
		
		var valorDeseado2 = nombreAct;
		
	    var newOption2 = document.createElement("option");
	    newOption2.value = valorDeseado2;
	    newOption2.text = valorDeseado2;
	    acts.add(newOption2);
		
		acts.value = valorDeseado2;
		
		acts.querySelector(`option[value="${valorDeseado2}"]`).selected = true;

		eventoChange = new Event('change');
		acts.dispatchEvent(eventoChange);
	})
} 

sals.addEventListener('change', () => {
	if((acts.value == "0") || acts.value == "" || 
	document.getElementById('CoD').value == "0" || document.getElementById('CoD').value == "")
	return;
	var dep = select.value;
	var act = acts.value;
	var sal = sals.value;
	
	// Construir la URL con los parámetros
	var nuevaURL = "/pruebaPA/salida?nomDep=" + encodeURIComponent(dep) + "&nomAct=" + encodeURIComponent(act) + "&nomSal=" + encodeURIComponent(sal);
	
	// Redirigir a la nueva página
	window.location.href = nuevaURL;
});

paqs.addEventListener('change', () => {
	if((acts.value == "0") || acts.value == "" || 
	document.getElementById('CoD').value == "0" || document.getElementById('CoD').value == "")
	return;
	var paq = paqs.value;
	
	// Construir la URL con los parámetros
	var nuevaURL = "/pruebaPA/paquete?nomPaq=" + encodeURIComponent(paq);
	
	// Redirigir a la nueva página
	window.location.href = nuevaURL;
});







