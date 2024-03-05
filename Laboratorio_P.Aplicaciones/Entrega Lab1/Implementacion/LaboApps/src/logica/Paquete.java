package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import datatype.DtPaquete;
import datatype.DtActTur;

@Entity
@Table(name = "paquetes")
public class Paquete {
	@Id
	private String nombre;
	private String descr;
	private Integer periodoValiez;
	private float descuento;
	private LocalDate fechaAlta;
//	@Transient
	@ManyToMany(mappedBy="paquetes")
	@MapKeyColumn(name = "nombre")
	private Map<String, ActividadTuristica> actividades;
	
	public String getDesc() {
		return descr;
	}
	public void setDesc(String desc) {
		this.descr = desc;
	}
	public Integer getPeriodoValiez() {
		return periodoValiez;
	}
	public void setPeriodoValiez(Integer periodoValiez) {
		this.periodoValiez = periodoValiez;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getNombre() {
		return nombre;
	}
	public Paquete() {		
	}
	
	public Paquete(String nombre, String desc, Integer periodoValiez, float descuento, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.descr = desc;
		this.periodoValiez = periodoValiez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.actividades = new HashMap<String, ActividadTuristica>();
	}
	public Paquete(DtPaquete data) {
		this.nombre = data.getNombre();
		this.descr = data.getDesc();
		this.periodoValiez = data.getPeriodoValidez();
		this.descuento = data.getDescuento();
		this.fechaAlta = data.getFechaAlta();
		this.actividades = new HashMap<String, ActividadTuristica>();
	}
	
	public void printNom() {}
	public void agregarActividad(ActividadTuristica a) throws Exception {
		if (this.actividades.get(a.getNombre()) != null)
			throw new Exception("Actividad ya registrada.");
		this.actividades.put(a.getNombre(), a);
		a.agregarPaquete(this);
	}
	public List<String> listarActividades() {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, u) -> ret.add(u.getNombre()));
		return ret;
	}
	public DtPaquete getData() {
		return new DtPaquete(nombre, descr, periodoValiez, descuento, fechaAlta);
	}
	public DtPaquete getDataTodo() {
		return new DtPaquete(nombre, descr, periodoValiez, descuento, fechaAlta, listarActividades());
	}
	
}
