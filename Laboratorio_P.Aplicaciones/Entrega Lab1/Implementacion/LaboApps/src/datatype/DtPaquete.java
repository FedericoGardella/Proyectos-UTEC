package datatype;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DtPaquete {
	private String nombre;
	private String desc;
	private Integer periodoValidez;
	private float descuento;
	private LocalDate fechaAlta;
	private List<String> nombreActividades;
	
	public String getNombre() {
		return nombre;
	}
	public String getDesc() {
		return desc;
	}
	public Integer getPeriodoValidez() {
		return periodoValidez;
	}
	public float getDescuento() {
		return descuento;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public List<String> getNombreActividades() {
		return new ArrayList<String>(nombreActividades);
	}
	public DtPaquete(String nombre, String desc, Integer periodoValidez, float descuento, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.desc = desc;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.nombreActividades = null;
	}
	public DtPaquete(String nombre, String desc, Integer periodoValidez, float descuento, LocalDate fechaAlta, List<String> nA) {
		this.nombre = nombre;
		this.desc = desc;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.nombreActividades = nA;
	}
	
	@Override
	public String toString() {
		return "[" + nombre + "],[" + desc + "],[" + Integer.toString(periodoValidez) + "],[" + Float.toString(descuento) + "],[" + fechaAlta.toString() + "]";
	}

}
