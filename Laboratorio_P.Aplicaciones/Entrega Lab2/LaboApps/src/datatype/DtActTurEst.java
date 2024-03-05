package datatype;

import java.time.LocalDate;

import logica.estadosActividad;

public class DtActTurEst {
	private String nombre;
	private String desc;
	private Integer duracion;
	private Float costo;
	private String ciudad;
	private LocalDate fecha;
	private estadosActividad estado;

	public String getNombre() {
		return nombre;
	}	
	public String getDesc() {
		return desc;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public Float getCosto() {
		return costo;
	}
	public String getCiudad() {
		return ciudad;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public estadosActividad getEstado() {
		return estado;
	}
	public DtActTurEst(String nombre, String desc, Integer duracion, Float costo, String ciudad, LocalDate fecha, estadosActividad estado) {
		this.nombre = nombre;
		this.desc = desc;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.fecha = fecha;
		this.estado = estado;
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    DtActTurEst other = (DtActTurEst) obj;
	    return nombre.equals(other.nombre) &&
	           desc.equals(other.desc) &&
	           duracion.equals(other.duracion) &&
	           costo.equals(other.costo) &&
	           ciudad.equals(other.ciudad) &&
	           fecha.equals(other.fecha) &&
	           estado == other.estado;
	}
}
