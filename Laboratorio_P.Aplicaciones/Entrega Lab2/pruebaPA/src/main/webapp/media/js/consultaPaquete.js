const select = document.getElementById('paquetesCP');
const inputNom = document.getElementById('nombrePaq');
const inputDesc = document.getElementById('descripcionPaq');
const inputPer = document.getElementById('periodoPaq');
const inputDescuento = document.getElementById('descuentoPaq');
const inputFecha = document.getElementById('fechaPaq');
const selectActs = document.getElementById('actividadesCP');
/*const selectCats = document.getElementById('listCategorias');*/
var imagen = document.getElementById("imagenPrueba");
var url = new URL(window.location.href);
var nombrePaq = url.searchParams.get("nomPaq");
const listaCategorias = document.getElementById('listCategorias');
const tituloCategorias = document.getElementById('tituloCategorias');
const container = document.getElementById('cont');
const inputCantTu = document.getElementById('cantTur');
const inputCantTi = document.getElementById('cantTickets');


document.addEventListener('DOMContentLoaded', () => {
	if (nombrePaq != null) {
		select.value = nombrePaq
		if (select.selectedIndex < 0) select.selectedIndex = 0
		select.dispatchEvent(new Event('change'))
	} 
})
    

document.getElementById('paquetesCP').addEventListener('change', () => {
	if(document.getElementById('paquetesCP').value == "0")
		return;
	
	ocultarSelectCat();
	ocultarSelectAct();
	
	const data = {
		nomPaq: document.getElementById('paquetesCP').value,
	}
	fetch('/pruebaPA/getdtp', {
		method: 'POST',
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify(data)
	})
	
	.then(res => res.json())
	.then(data => {
		inputNom.value = data.nombre;
		inputDesc.value = data.desc;
		inputPer.value = data.periodo;
		inputDescuento.value = data.descuento;
		inputFecha.value = data.alta;
		inputCantTu.value = data.cantTur;
		inputCantTi.value = data.cantTi;
	})
	   
});

function mostrarSelectCat() {
  listaCategorias.style.display = "block";
}

function ocultarSelectCat() {
  listaCategorias.style.display = "none";
}

document.getElementById("verCat").addEventListener("click", () => {
	if(document.getElementById('paquetesCP').value == "0" || document.getElementById('paquetesCP').value == "")
		return;
	mostrarSelectCat();
	const data = {
		nomPaq: document.getElementById('paquetesCP').value,
	}
	
	listaCategorias.innerHTML = '';
	
	fetch('/pruebaPA/getccp', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
	})
    .then(res => res.json())
    .then(data => {
        const arrc = data.categorias;

        for (let i = 0; i < arrc.length; i++) {
            const categoria = arrc[i].nombre;

            // Crea un elemento <li> para cada categoría y agrega al <ul>
            const listItem = document.createElement('li');
            listItem.textContent = categoria;
            listaCategorias.appendChild(listItem);
        }
    });

	
});

function mostrarSelectAct() {
  selectActs.style.display = "block";
}

function ocultarSelectAct() {
  selectActs.style.display = "none";
}

document.getElementById("verAct").addEventListener("click", () => {
	if(document.getElementById('paquetesCP').value == "0" || document.getElementById('paquetesCP').value == "")
		return;
	mostrarSelectAct();
	const data = {
		nomPaq: document.getElementById('paquetesCP').value,
	}
	selectActs.innerHTML = '';
	
	/*fetch('/pruebaPA/getacp', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
	.then(res => res.json())
	.then(data => {
		const arrc = data.actividades;
		selectActs.innerHTML = '';
		selectActs.innerHTML += `<option value="0">Actividades</option>`;
		for (let i = 0; i < arrc.length; i++){
			selectActs.innerHTML += `<option value="${arrc[i].nombre}">${arrc[i].nombre}</option>`;
		}

	})*/
/*------------------------------------------------------------------------------------------------------------*/		
	
	fetch('/pruebaPA/getacp', {
	  	method: 'POST',
	 	headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(data)
	})
	.then(res => res.json())
	.then(data => {
		const actividades = data.actividades;
		

		const imagenes = JSON.parse(data.imagenes);
		// Asegúrate de que tengan la misma cantidad de elementos
		if (actividades.length !== imagenes.length) {
			console.error("Error: la cantidad de actividades y URLs de imágenes no coincide.");
			console
		    return;
		}

		// Itera a través de las actividades e imágenes y crea elementos HTML
		for (let i = 0; i < actividades.length; i++) {
		    const actividad = actividades[i].nombre;
		    const imagenURL = imagenes[i].imagen;
	
		    // Crea un elemento <li> para cada actividad
		    const listItem = document.createElement('li');
		
		    // Crea un elemento <p> para el texto de la actividad
		    const actividadA = document.createElement('a');
			actividadA.classList.add('nav-link', 'text-decoration-underline');
			actividadA.textContent = actividad;
			actividadA.href = `/pruebaPA/consact?nombreAct=${actividad}`
			
		    // Crea un elemento <img> para la imagen de la actividad
		    const imagen = document.createElement('img');
		    imagen.src = imagenURL;
		    imagen.alt = `Imagen de ${actividad.nombre}`;
		    imagen.style.height = '100px';
	
		    // Agrega el elemento de texto y la imagen al <li>
		    listItem.classList.add("list-group-item", "list-group-item-action");
		    listItem.appendChild(actividadA);
		    listItem.appendChild(imagen);
	
		    // Agrega el <li> al <ul> (o selectActs en tu caso)
		    selectActs.appendChild(listItem);
		}
	});
});


if (nombrePaq != null) {

	var valorDeseado = nombrePaq;

    var newOption = document.createElement("option");
    newOption.value = valorDeseado;
    newOption.text = valorDeseado;
    select.add(newOption);

	select.value = valorDeseado;
	
	eventoChange = new Event('change');
	select.dispatchEvent(eventoChange);
}

selectActs.addEventListener('change', () => {
	if(selectActs.value == "0" || selectActs.value == "")
		return;
	window.location.href = `/pruebaPA/consact?nombreAct=${selectActs.value}`
});

