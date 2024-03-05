package datatype;

import java.time.LocalDate;

import logica.SalidaTuristica;

public class DtInsc {
	private SalidaTuristica nombre;
	private Integer cant;
	private LocalDate fecha;
	
	public SalidaTuristica getNombre() {
		return nombre;
	}
	public Integer getCant() {
		return cant;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public DtInsc(SalidaTuristica nombre, Integer cant, LocalDate fecha) {
		super();
		this.nombre = nombre;
		this.cant = cant;
		this.fecha = fecha;
	}
}
