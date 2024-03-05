package logica;

import datatype.DtActTur;
import datatype.DtSalidaTuristica;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "departamentos")
public class Departamento {
	@Id
	private String nombre;
	private String descr;
	private String url;
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "nombre")
	private Map<String, ActividadTuristica> actividades;
	
	public String getNombre() {
		return nombre;
	}

	public Departamento() {

	}

	public Departamento(String nombre) {
		actividades = new HashMap<String, ActividadTuristica>();
		this.nombre = nombre;
		this.descr = null;
		this.url = null;
	}


	public Departamento(String nombre, String descr, String url) {
		actividades = new HashMap<String, ActividadTuristica>();
		this.nombre = nombre;
		this.descr = descr;
		this.url = url;
	}
	
	public ActividadTuristica crearAct(DtActTur data) throws Exception{
		ActividadTuristica act = new ActividadTuristica(data.getNombre(), data.getDesc(), data.getDuracion(), data.getCosto(), data.getCiudad(), data.getFecha(), this);
		if (actividades.containsKey(act.getNombre())) {
			throw new Exception("Ya existe una actividad con ese nombre");
		}		
		actividades.put(act.getNombre(), act);
		return act;
	}

	public void imprimirActividades() {
		System.out.println("\nActividades:");
		for (Map.Entry<String, ActividadTuristica> entry : actividades
				.entrySet()) {
			ActividadTuristica act = entry.getValue();
			System.out.println("Nombre: " + act.getNombre());
		}
	}
	
	public void selecActividades(String nombre) {}
	public void selActIngDat(String nombre, DtSalidaTuristica dataSalida) {}
	
	public void selecActividadesVigentes(String nombre) {}

	public List<String> listarActividadesPaquete(String nombreP) {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, act) -> {
			if (!act.enlazadoConPaquete(nombreP) && this.nombre == act.getNombreDepartamento()) 
				ret.add(act.getNombre());		
		});
		return ret;
	}
	
//	Consulta de Actvididad Turistica
	public List<String> printActividades() {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, u) -> {
			ret.add(t);
		});
		return ret;
	}
	
	public void selecActividad(String nombreAct) {
		ActividadTuristica act = actividades.get(nombreAct);
		act.printDataActTur();
	}
	
	public DtActTur selecActividadDt(String nombreAct) {
		ActividadTuristica act = actividades.get(nombreAct);
		return act.getDataActTur();
	}
	
	public List<String> listProveedores(String nombreAct) throws Exception {
		ActividadTuristica act = actividades.get(nombreAct);
		if (act == null)
			throw new Exception("No existe una actividad con ese nombre para listar sus salidas");
		return act.listarProveedores();
	}
	
//	Alta de Salida Turistica
	public SalidaTuristica confirmarAltaSalTur(String nombreAct, DtSalidaTuristica datos) throws Exception{
		ActividadTuristica act = actividades.get(nombreAct);
		SalidaTuristica sal = act.crearSalida(datos);
		return sal;
	}
	
//	Consulta de Salida Turistica
	public ActividadTuristica seleccActividad(String nombreAct) throws Exception {
		ActividadTuristica act = actividades.get(nombreAct);
		if (act == null)
			throw new Exception("No existe una actividad con ese nombre para seleccionar");
		
		return act;
	}
	public List<String> listSalidas(String nombreAct) throws Exception {
		ActividadTuristica act = actividades.get(nombreAct);
		if (act == null)
			throw new Exception("No existe una actividad con ese nombre para listar sus salidas");
		return act.listarSalidas();
	}
}
