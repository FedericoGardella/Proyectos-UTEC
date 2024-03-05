package logica;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	private String nombre;
    @ManyToMany(mappedBy = "categorias")
    @MapKey(name = "nombre")
	private Map<String, ActividadTuristica> actividades;
	
	public String getNombre() {
		return nombre;
	}

	public Categoria() {}

	public Categoria(String nombre) {
		actividades = new HashMap<String, ActividadTuristica>();
		this.nombre = nombre;
	}
	
	public List<String> listarActividades() {
		List<String> ret = new ArrayList<String>();
		actividades.forEach((t, u) -> {
			ret.add(t);
		});
		return ret;
	}
	
	public ActividadTuristica seleccActividad(String nombreAct) {
		ActividadTuristica act = actividades.get(nombreAct);
		System.out.println(actividades);
		return act;
	}
	
	public String getImagenAct(String act){	
		ActividadTuristica a = actividades.get(act);
		return a.getImagen();
	}
	
	public String getImagenSal(String act, String sal){	
		ActividadTuristica a = actividades.get(act);
		return a.getImagenSal(sal);
	}
	
	public void agregarActividad(ActividadTuristica a) throws Exception {
		if (this.actividades.get(a.getNombre()) != null)
			throw new Exception("Actividad ya registrada.");
		this.actividades.put(a.getNombre(), a);
	}
}

