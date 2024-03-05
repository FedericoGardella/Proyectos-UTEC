var radioInput = document.getElementById('opc1');
var radioInput2 = document.getElementById('opc2');
const select = document.getElementById('DoC');
const acts = document.getElementById('actividadesCS');
const sals = document.getElementById('salidasCS');
const inputNom = document.getElementById('nombreSal');
const inputFecS = document.getElementById('fechaSal');
const inputHora = document.getElementById('horaSal');
const inputLugar = document.getElementById('lugarSal');
const inputCant = document.getElementById('cantSal');
const inputAlta = document.getElementById('altaSal');
const btnInscribirse = document.querySelector('#inscripcion')
var url = new URL(window.location.href);
var nombreSal = url.searchParams.get("nomSal");
var nombreDep = url.searchParams.get("nomDep");
var nombreAct = url.searchParams.get("nomAct");
/*
const paqs = document.getElementById('paquetes');
const cates = document.getElementById('lista-categorias');
*/
radioInput.addEventListener('change', function() {
    if (this.checked) {
		radioInput2.checked = false;
		select.innerHTML = `<select class="form-select" id="DoC" aria-label="Default select example"></select>`;
		acts.innerHTML = `<select class="form-select" id="actividadesCS" aria-label="Default select example"></select>`;
		sals.innerHTML = `<select class="form-select" id="salidasCS" aria-label="Default select example"></select>`;
		inputNom.value = "";
		inputFecS.value = "";
		inputHora.value = "";
        inputLugar.value = "";
        inputCant.value = "";
        inputAlta.value = "";
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
		select.innerHTML = `<select class="form-select" id="DoC" aria-label="Default select example"></select>`;
		acts.innerHTML = `<select class="form-select" id="actividadesCS" aria-label="Default select example"></select>`;
		sals.innerHTML = `<select class="form-select" id="salidasCS" aria-label="Default select example"></select>`;
		inputNom.value = "";
		inputFecS.value = "";
		inputHora.value = "";
        inputLugar.value = "";
        inputCant.value = "";
        inputAlta.value = "";
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

select.addEventListener('change', () => {
	if(select.value == "0")
		return;
	if (radioInput.checked) {
		const data = {
			nomDep: select.value,
		}
		fetch('/pruebaPA/geta', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			const arra = data.actividades;
			acts.innerHTML = `<select class="form-select" id="actividadesCS" aria-label="Default select example"></select>`;
			acts.innerHTML += `<option value="0">Actividades</option>`;
			for (let i = 0; i < arra.length; i++){
				acts.innerHTML += `<option value="${arra[i].nombre}">${arra[i].nombre}</option>`;
			}
    	})
    }
	if (radioInput2.checked) {
		const data = {
			nomCat: document.getElementById('DoC').value
		}
		fetch('/pruebaPA/getac', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			const arra = data.actividades;
			acts.innerHTML = `<select class="form-select" id="actividadesCS" aria-label="Default select example"></select>`;
			acts.innerHTML += `<option value="0">Actividades</option>`;
			for (let i = 0; i < arra.length; i++){
				acts.innerHTML += `<option value="${arra[i].nombre}">${arra[i].nombre}</option>`;
			}
    	})
    }
});

acts.addEventListener('change', () => {
	if(document.getElementById('DoC').value == "0" || acts.value == "0")
		return;
	const data = {
		nomAct: acts.value,
	}
	fetch('/pruebaPA/gets', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(data)
	})
	.then(res => res.json())
	.then(data => {
		const arra = data.salidas;
		sals.innerHTML = `<select class="form-select" id="salidasCS" aria-label="Default select example"></select>`;
		sals.innerHTML += `<option value="0">Salidas</option>`;
		for (let i = 0; i < arra.length; i++){
			sals.innerHTML += `<option value="${arra[i].nombre}">${arra[i].nombre}</option>`;
		}
	})
    
});

sals.addEventListener('change', () => {
	if(document.getElementById('DoC').value == "0" || acts.value == "0" || sals.value == "0")
		return;
	if (radioInput.checked || radioInput2.checked) {
		const data = {
			nomSal: sals.value,
		}
		fetch('/pruebaPA/getdts', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
		
		.then(res => res.json())
		.then(data => {			
			inputNom.value = data.nombre;
			inputFecS.value = data.fecha;
			inputHora.value = data.hora;
			inputLugar.value = data.lugar;
			inputCant.value = data.cant;
			inputAlta.value = data.alta;
			document.getElementById('imagenSal').src = data.imagen;
		})
    }
});

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
btnInscribirse.addEventListener('click', e => {
	if (!radioInput.checked && !radioInput2.checked) return
	if (select.selectedIndex <= 0) return
	if (acts.selectedIndex <= 0) return
	if (sals.selectedIndex <= 0) return					
	const firstSelect = select.value
	const actividadSelect = acts.value
	const salidaSelect = sals.value
	const data = {
		esDep: radioInput.checked,
		DoC: firstSelect,
		act: actividadSelect,
		sal: salidaSelect
	}
	console.log(data)
	const p =new URLSearchParams(data).toString();
	fetch(`/pruebaPA/inscribirseSalida?${p}`)
		.then(ret=> ret.json())
		.then(ret => {
		if (ret.success) {
			window.location.href = ret.redirect
		} else {
			mostrarModal({title: 'Error al intentar inscribirse', msg: ret.message})
		}
	})

})

if (nombreSal != null) {
	
	radioInput.checked = true;
	var eventoChange = new Event('change');
	radioInput.dispatchEvent(eventoChange);
	
	if (nombreDep != null) {
		var valorDeseado = nombreDep;
	    var newOption = document.createElement("option");
	    newOption.value = valorDeseado;
	    newOption.text = valorDeseado;
	    select.add(newOption);
	
		select.value = valorDeseado;
	}

	if (nombreAct != null) {
		var valorDeseado2 = nombreAct;
		
	    var newOption2 = document.createElement("option");
	    newOption2.value = valorDeseado2;
	    newOption2.text = valorDeseado2;
	    acts.add(newOption2);
	    
		acts.value = valorDeseado2;
	}
	
	var valorDeseado3 = nombreSal;
	
    var newOption3 = document.createElement("option");
    newOption3.value = valorDeseado3;
    newOption3.text = valorDeseado3;
    sals.add(newOption3);
    
	sals.value = valorDeseado3;

	eventoChange = new Event('change');
	sals.dispatchEvent(eventoChange);
}
