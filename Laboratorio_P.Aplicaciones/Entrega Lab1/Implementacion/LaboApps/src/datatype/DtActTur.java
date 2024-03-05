package datatype;

import java.time.LocalDate;

public class DtActTur {
	private String nombre;
	private String desc;
	private Integer duracion;
	private Float costo;
	private String ciudad;
	private LocalDate fecha;

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
	public DtActTur(String nombre, String desc, Integer duracion, Float costo, String ciudad, LocalDate fecha) {
		this.nombre = nombre;
		this.desc = desc;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.fecha = fecha;
	}
	
}
