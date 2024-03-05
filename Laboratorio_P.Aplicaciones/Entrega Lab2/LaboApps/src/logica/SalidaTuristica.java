package logica;

import datatype.DtSalidaTuristica;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salidaTuristica")
public class SalidaTuristica {
	@Id
	private String nombre;
	private LocalDate fechaSalida;
	private LocalTime horaSalida;
	private String lugarSalida;
	private Integer cantMaxTuristas;
	private LocalDate fechaAlta;
	private Integer cuposDisponibles;
	private String URLimagen;

	
	public Integer getCantMaxTuristas() {
		return cantMaxTuristas;
	}
	public void setCantMaxTuristas(Integer cantMaxTuristas) {
		this.cantMaxTuristas = cantMaxTuristas;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getLugarSalida() {
		return lugarSalida;
	}
	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}
	public String getNombre() {
		return nombre;
	}
	public LocalTime getHora() {
		return this.horaSalida;
	}
	public void setHora(LocalTime hora) {
		this.horaSalida = hora;
	}
	public String getImagen() {
		return this.URLimagen;
	}
	public void setImagen(String URL) {
		this.URLimagen = URL;
	}
	
	public boolean estaVigente() {
		
		return false;
	}
	
	public SalidaTuristica(){}
	
	public SalidaTuristica(String nombre, LocalDate fechaSalida, LocalTime horaSalida, String lugarSalida, Integer cant, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.lugarSalida = lugarSalida;
		this.cantMaxTuristas = cant;
		this.fechaAlta = fechaAlta;
		this.URLimagen = null;
		this.cuposDisponibles = cant;
	}
	
	public DtSalidaTuristica dtSalTur() {
		DtSalidaTuristica st = new DtSalidaTuristica(this.getNombre(),this.getFechaSalida(),this.getHora(),this.getLugarSalida(),this.getCantMaxTuristas(),this.getFechaAlta());
		return st;
	}
	public Integer getCuposDisponibles() {
		return cuposDisponibles;
	}
	public void setCuposDisponibles(Integer cuposDisponibles) {
		this.cuposDisponibles = cuposDisponibles;
	}
	
}
