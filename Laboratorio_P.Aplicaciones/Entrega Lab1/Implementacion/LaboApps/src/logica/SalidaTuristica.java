package logica;

import datatype.DtHora;
import datatype.DtSalidaTuristica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

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
	
	public void printNom() {
		
	}
	public void printDataSal() {
		
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
	}
	
//	Consulta de Salida Turistica
	public void printDataSalidaTuristica() {
		System.out.println("\nSalida Turistica: ");
		System.out.println("Nombre: " + this.getNombre());
		System.out.println("Fecha de salida : " + this.getFechaSalida());
		System.out.println("Hora de salida : " + this.getHora());
		System.out.println("Lugar de salida : " + this.getLugarSalida());
		System.out.println("Cantidad maxima de turistas : " + this.getCantMaxTuristas());
		System.out.println("Fecha de alta: " + this.getFechaAlta());		
	}
	
	public DtSalidaTuristica dtSalTur() {
		DtSalidaTuristica st = new DtSalidaTuristica(this.getNombre(),this.getFechaSalida(),this.getHora(),this.getLugarSalida(),this.getCantMaxTuristas(),this.getFechaAlta());
		return st;
	}
}
