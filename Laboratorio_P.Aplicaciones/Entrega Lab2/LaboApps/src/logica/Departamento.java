package logica;

import datatype.DtActTur;
import datatype.DtActTurEst;
import datatype.DtSalidaTuristica;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.MapKey;
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
	
	public Map<String, ActividadTuristica> getMapActividades() {
		return this.actividades;
	}

	public Departamento(String nombre, String descr, String url) {
		actividades = new HashMap<String, ActividadTuristica>();
		this.nombre = nombre;
		this.descr = descr;
		this.url = url;
	}
	
	public ActividadTuristica crearAct(DtActTur data, Map<String, Categoria> categos) throws Exception{
		ActividadTuristica act = new ActividadTuristica(data.getNombre(), data.getDesc(), data.getDuracion(), data.getCosto(), data.getCiudad(), data.getFecha(), this, categos);
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
	public List<String> listarActividadesPaqueteConfirmada(String nombreP) {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, act) -> {
			if (this.nombre == act.getNombreDepartamento() && !act.enlazadoConPaquete(nombreP) && act.getEstado() == estadosActividad.CONFIRMADA) 
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

	public boolean contieneActividad(String nombreAct) {
		return (this.actividades.containsKey(nombreAct));
	}

//	Aceptar/Rechazar Actividad Turistica
	public List<String> listarActividadesAg() {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, u) -> {
			if (u.getEstado()== estadosActividad.AGREGADA) {
				ret.add(t);
			}
		});
		return ret;
	}
	
	public ActividadTuristica crearActEstado(DtActTur data, estadosActividad est) throws Exception{
		ActividadTuristica act = new ActividadTuristica(data.getNombre(), data.getDesc(), data.getDuracion(), data.getCosto(), data.getCiudad(), data.getFecha(), this, est);
		if (actividades.containsKey(act.getNombre())) {
			throw new Exception("Ya existe una actividad con ese nombre");
		}		
		actividades.put(act.getNombre(), act);
		return act;
	}

	public ActividadTuristica buscarAct(String nombreAct, estadosActividad est) {
		ActividadTuristica act = actividades.get(nombreAct);
//		if (act != null) {
//        	act.cambiarEstado(est);            
//        }
		return act;
	}
	
	public DtActTurEst selecActividadDtEst(String nombreAct) {
		ActividadTuristica act = actividades.get(nombreAct);
		return act.getDataActTurEst();
	}

	public List<String> listarActividadesConf() {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, u) -> {
			if (u.getEstado()== estadosActividad.CONFIRMADA) {
				ret.add(t);
			}
		});
		return ret;
	}

	public DtActTurEst encontrarAct(String nombreAct) {
		ActividadTuristica act = actividades.get(nombreAct);
		if (act != null) {
			return act.getDataActTurEst();            
        }
		return null;
	}
	
	public List<String> listarActividadesPaqueteConf(String nombreP) {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, act) -> {
			if (!act.enlazadoConPaquete(nombreP) && this.nombre == act.getNombreDepartamento() && act.getEstado() == estadosActividad.CONFIRMADA) 
				ret.add(act.getNombre());		
		});
		return ret;
	}
	
	public ActividadTuristica buscarAct2(String nombreAct) {
		ActividadTuristica act = actividades.get(nombreAct);
		return act;
	}
	public ActividadTuristica contieneActividadSegunSalida(String nombreSal) {
		for (Map.Entry<String, ActividadTuristica> act : actividades.entrySet()) {
			if (act.getValue().getSalida(nombreSal) != null) 				
				return act.getValue();		
		}
		return null;
	}
	
	public void guardarImagenSalida(String act, String nom, String newURL){	
		ActividadTuristica a = actividades.get(act);
		a.guardarImagenSalida(nom,newURL);
	}
	
	public void guardarImagenAct(String act, String newURL){	
		ActividadTuristica a = actividades.get(act);
		a.setImagen(newURL);
	}
	
	public String getImagenAct(String act){	
		ActividadTuristica a = actividades.get(act);
		return a.getImagen();
	}
	
	public String getImagenSal(String act, String sal){	
		ActividadTuristica a = actividades.get(act);
		return a.getImagenSal(sal);
	}
}


