const select = document.getElementById('paquetesCom');
const inputFechaCom = document.getElementById('fechaComp');
const inputVenc = document.getElementById('vencimientoComp');
const inputCosto = document.getElementById('costoComp');
const inputCant = document.getElementById('cantidadComp');
const botonCompra = document.getElementById('comprarPaquete');


document.addEventListener("DOMContentLoaded", () => {
   
   
   		inputFechaCom.value = obtenerFechaActual();
		select.innerHTML = `<select class="form-select" id="paquetesCom" aria-label="Default select example" name="paquetesCom"></select>`;
        fetch('/pruebaPA/getpcom')
		.then(res => res.json())
		.then(data => {
			const arrd = data.paquetes;
			select.innerHTML += `<option value="0">Paquetes</option>`;
			for (let i = 0; i < arrd.length; i++){
				select.innerHTML += `<option value="${arrd[i].nombre}">${arrd[i].nombre}</option>`;
			}
		});
    
});

function obtenerFechaActual() {
	const fecha = new Date();
	let diaActual = fecha.getDate();
	let mesActual = fecha.getMonth() + 1; 
	let anioActual = fecha.getFullYear() % 100;
  
	if (diaActual < 10) {
		diaActual = "0" + diaActual;
	}
	if (mesActual < 10) {
		mesActual = "0" + mesActual;
	}
	if (anioActual < 10) {
		anioActual = "0" + anioActual;
	}

  return diaActual + "-" + mesActual + "-" + anioActual;
}

function obtenerFechaVencimiento(periodo) {
	let fecha = new Date();
	fecha.setDate(fecha.getDate() + periodo);
	
	let diaActual = fecha.getDate();
	let mesActual = fecha.getMonth() + 1; 
	let anioActual = fecha.getFullYear() % 100;
	
	
  
	if (diaActual < 10) {
		diaActual = "0" + diaActual;
	}
	if (mesActual < 10) {
		mesActual = "0" + mesActual;
	}
	if (anioActual < 10) {
		anioActual = "0" + anioActual;
	}

  return diaActual + "-" + mesActual + "-" + anioActual;
};

document.getElementById('paquetesCom').addEventListener('change', () => {
	if(document.getElementById('paquetesCom').value == "0")
		return;
	
	const data = {
			nomPaq: document.getElementById('paquetesCom').value,
		}
		fetch('/pruebaPA/getdtp', {
			method: 'POST',
			headers: {'Content-Type': 'application/json'},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			
			inputVenc.value = obtenerFechaVencimiento(data.periodo);
			inputCosto.value = data.costoTotal;
			inputCant.value = data.cantTur;
    	})
    
});

botonCompra.addEventListener('click', () => {
	if((inputCosto.value === '') || (select.value === 'Paquetes')){
		alert("Seleccione un paquete");
		return;
	}else{
		// Obtén el valor del campo de entrada
	    const nombrePaq = select.value;
	    
	    console.log(nombrePaq)
	    
	    const data = new FormData();
	    
	    data.append('nombrePaq', select.value);
	
	    // Realiza la solicitud POST al servidor
	    fetch('/pruebaPA/comprapaq', {
	        method: 'POST',
	        body: data
	    })
	    .then(response => {
	        if (response.status === 201) {
	            alert("Compra realizada con exito");
	            /*window.location.href = "http://localhost:8080/pruebaPA/comprapaq";*/
	        } else {
	            console.error('Error al comprar el paquete');
	        }
	    })
	    .catch(error => {
	        console.error('Error en la solicitud: ' + error);
	    });
	}
	

});





