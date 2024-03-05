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
	private Integer cantTur;
	private List<String> nombreActividades;
	private Integer tickets;
	
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
	public Integer getCant() {
		return cantTur;
	}
	public Integer getTickets() {
		return tickets;
	}
	public void setTickets(Integer tickets) {
		this.tickets = tickets;
	}

	public List<String> getNombreActividades() {
		return new ArrayList<String>(nombreActividades);
	}
	public DtPaquete(String nombre, String desc, Integer periodoValidez, float descuento, LocalDate fechaAlta, Integer cant, Integer tickets) {
		this.nombre = nombre;
		this.desc = desc;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.cantTur = cant;
		this.nombreActividades = null;
		this.tickets = tickets;
	}
	public DtPaquete(String nombre, String desc, Integer periodoValidez, float descuento, LocalDate fechaAlta, Integer cant, List<String> nA, Integer tickets) {
		this.nombre = nombre;
		this.desc = desc;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.cantTur = cant;
		this.nombreActividades = nA;
		this.tickets = tickets;
	}
	
	@Override
	public String toString() {
		return "[" + nombre + "],[" + desc + "],[" + Integer.toString(periodoValidez) + "],[" + Float.toString(descuento) + "],[" + fechaAlta.toString() + "],[" + Integer.toString(cantTur) + "]";
	}

}
