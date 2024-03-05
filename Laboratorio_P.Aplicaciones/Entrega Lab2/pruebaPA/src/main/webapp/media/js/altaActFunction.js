
function MultiSelectTag(e, t = { shadow: false, rounded: true }) {
  var n = null,
      d = null,
      l = null,
      a = null,
      s = null,
      i = null,
      o = null,
      c = null,
      r = null,
      u = null,
      p = null,
      v = null,
      m = new DOMParser;

  function h(e = null) {
    for (var t of (v.innerHTML = "", d))
      if (t.selected) !w(t.value) && f(t);
      else {
        const n = document.createElement("li");
        n.innerHTML = t.label,
        n.dataset.value = t.value,
        e && t.label.toLowerCase().startsWith(e.toLowerCase())
          ? v.appendChild(n)
          : e || v.appendChild(n);
      }
  }

  function f(e) {
    const t = document.createElement("div");
    t.classList.add("item-container");
    const n = document.createElement("div");
    n.classList.add("item-label"),
    (n.innerHTML = e.label),
    (n.dataset.value = e.value);
    const l = (new DOMParser).parseFromString(
      '<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="item-close-svg">\n' +
      '  <line x1="18" y1="6" x2="6" y2="18"></line>\n' +
      '  <line x1="6" y1="6" x2="18" y2="18"></line>\n' +
      '</svg>',
      "image/svg+xml"
    ).documentElement;
    
    l.addEventListener("click", (t) => {
      d.find((t) => t.value == e.value).selected = !1, g(e.value), h(), E();
    }),
    t.appendChild(n),
    t.appendChild(l),
    o.append(t);
  }

  function L() {
    for (var e of v.children)
      e.addEventListener("click", (e) => {
        d.find((t) => t.value == e.target.dataset.value).selected = !0,
        (r.value = null),
        h(),
        E(),
        r.focus();
      });
  }

  function w(e) {
    for (var t of o.children)
      if (!t.classList.contains("input-body") && t.firstChild.dataset.value == e)
        return !0;
    return !1;
  }

  function g(e) {
    for (var t of o.children)
      t.classList.contains("input-body") ||
        t.firstChild.dataset.value != e ||
        o.removeChild(t);
  }

  function E() {
    for (var e = 0; e < d.length; e++) n.options[e].selected = d[e].selected;
  }

  n = document.getElementById(e);

  (function () {
    (d = [...n.options].map((e) => ({
      value: e.value,
      label: e.label,
      selected: e.selected,
    }))),
    n.classList.add("hidden"),
    (l = document.createElement("div")).classList.add("mult-select-tag"),
    (a = document.createElement("div")).classList.add("wrapper"),
    (i = document.createElement("div")).classList.add("body"),
    t.shadow && i.classList.add("shadow"),
    t.rounded && i.classList.add("rounded"),
    (o = document.createElement("div")).classList.add("input-container"),
    (r = document.createElement("input")).classList.add("input"),
    (r.placeholder = `${t.placeholder || "Search..."}`),
    (c = document.createElement("inputBody")).classList.add("input-body"),
    c.append(r),
    i.append(o),
    (s = document.createElement("div")).classList.add("btn-container"),
    (u = document.createElement("button")).type = "button",
    s.append(u);
    const e = m.parseFromString(
      '<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">\n' +
      '  <polyline points="18 15 12 21 6 15"></polyline>\n' +
      '</svg>',
      "image/svg+xml"
    ).documentElement;

    u.append(e), i.append(s), a.append(i), (p = document.createElement("div")).classList.add("drawer", "hidden"), t.shadow && p.classList.add("shadow"), t.rounded && p.classList.add("rounded"), p.append(c), (v = document.createElement("ul")), p.appendChild(v), l.appendChild(a), l.appendChild(p), n.nextSibling ? n.parentNode.insertBefore(l, n.nextSibling) : n.parentNode.appendChild(l);
  })(),
  h(),
  L(),
  E(),
  u.addEventListener("click", () => {
    p.classList.contains("hidden") && (h(), L(), p.classList.remove("hidden"), r.focus());
  }),
  r.addEventListener("keyup", (e) => {
    h(e.target.value), L();
  }),
  r.addEventListener("keydown", (e) => {
    if ("Backspace" === e.key && !e.target.value && o.childElementCount > 1) {
      const e = i.children[o.childElementCount - 2].firstChild;
      d.find((t) => t.value == e.dataset.value).selected = !1, g(e.dataset.value), E();
    }
  }),
  window.addEventListener("click", (e) => {
    l.contains(e.target) || p.classList.add("hidden");
  });
  
  return {
    addOption: function(value, label) {
      const option = document.createElement('option');
      option.value = value;
      option.label = label;
      n.appendChild(option);
      d.push({ value: value, label: label, selected: false });
      h();
      L();
      E();
    },
    clearSelections: function() {
      d.forEach(item => item.selected = false);
      E();
    },
    reset: function() {
      d.forEach(item => item.selected = false);
      o.innerHTML = '';
      h();
      L();
      E(); 
    }
  };
}

var multiselect = new MultiSelectTag('combo');

document.addEventListener("DOMContentLoaded", () => {
	fetch('/pruebaPA/getd')
	.then(res => res.json())
	.then(data => {
		const select = document.getElementById('departamentos');
		select.innerHTML += `<option value="0">Departamentos</option>`;
		const arrd = data.departamentos;
		for (let i = 0; i < arrd.length; i++){
			select.innerHTML += `<option value="${arrd[i].nombre}">${arrd[i].nombre}</option>`;
		}
	})
	fetch('/pruebaPA/getc')
	.then(res => res.json())
	.then(dat => {
		const arrc = dat.categorias;
		for (let i = 0; i < arrc.length; i++){
			multiselect.addOption(arrc[i].nombre, arrc[i].nombre);
		}
	})
})

const boton = document.getElementById('botonn');

var mensaje = document.getElementById('mensaje');

boton.addEventListener('click', () =>{

	const data = new FormData();
	
	var multiSel= document.getElementById('combo');
	
	const fileInput = document.querySelector('#fileInput');

	var selectedValues = [];
	
	
	for (var i = 0; i < multiSel.options.length; i++) {
	  if (multiSel.options[i].selected) {
	    selectedValues.push(multiSel.options[i].value);
	  }
	}
	
	var categorias = selectedValues.join(',');
	
	const departamento = document.getElementById('departamentos');
	const nombre = document.getElementById('nombre');
	const desc = document.getElementById('desc');
	const dur = document.getElementById('dur');
	const costo = document.getElementById('costo');
	const ciudad = document.getElementById('ciudad');
	
	if ((nombre.value === '') || (desc.value === '') || (dur.value === '') || (costo.value === '') ||
		(ciudad.value === '') || (selectedValues.length === 0) || (departamento.value == '0')){
		mensaje.textContent = 'Debe rellenar todos los campos';
		mensaje.style.color = 'red';	
		return
	}
	
	data.append('departamento', departamento.value);
	data.append('nombre', nombre.value);
	data.append('desc', desc.value);
	data.append('dur', dur.value);
	data.append('costo', costo.value);
	data.append('ciudad', ciudad.value);
	data.append('categorias', categorias);
	if(fileInput.files[0] != null){
		data.append('hayImage', "true");
		data.append('image', fileInput.files[0]);
	}else{
		data.append('hayImage', "false")
	}

	
	
	fetch('/pruebaPA/act', {
		method: 'POST',
		body: data
	})
	.then(res => res.json())
	.then(data => {
		if (data.success){
			departamento.value = "",
			nombre.value = "",
			desc.value = "",
			dur.value = "",
			costo.value = "",
			ciudad.value = "",
			fileInput.value = "",
			multiselect.clearSelections(),
			multiselect.reset(),
			mensaje.textContent = 'Actividad registrada correctamente!';
			mensaje.style.color = 'green';
		}
		else{
			console.log(data.message);
			if (data.message === 'A different object with the same identifier value was already associated with the session : [logica.ActividadTuristica#${nombre.value}')
				mensaje.textContent = 'Ya existe una actividad con ese nombre';
			if (data.message === 'Ya existe una actividad con ese nombre')
				mensaje.textContent = 'Ya existe una actividad con ese nombre';
			if (data.message === 'Departamento no encontrado')
				mensaje.textContent = 'Selecciona un Departamento';
			mensaje.style.color = 'red';
		}
	})
})










