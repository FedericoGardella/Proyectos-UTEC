package logica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

import datatype.DtModUser;

@Entity
@Table(name="usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario {
	@Id
	private String nick;
	private String name;
	private String apellido;
	private String email;
	private LocalDate fechaNac;
	public String getNick() {
		return nick;
	}
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	public String getApellido() {
		return apellido;
	}
	protected void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	protected void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public Usuario() {
		
	}
	
	public Usuario(String nick, String name, String ape, String email, LocalDate fecha) {
		this.nick = nick;
		this.name = name;
		this.apellido = ape;
		this.email = email;
		this.fechaNac = fecha;
	}
	
	public abstract void modificarDatos(DtModUser datosNuevos);
	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", name=" + name + ", apellido="
				+ apellido + ", email=" + email + ", fechaNac=" + fechaNac
				+ "]";
	}
	
	
	public abstract void linkearAct(ActividadTuristica act);
	
	public abstract boolean esProveedor();
	
}
