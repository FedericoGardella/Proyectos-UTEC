package datatype;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DtUsuario {
	private String nick;
	private String nom;
	private String ape;
	private String email;
	private LocalDate fechaNac;
	private String desc;
	private String link;
	private String nacion;
	private List<String> nombreActividades;
	private List<String> nombreSalidas;
	
	public String getNick() {
		return nick;
	}
	public String getNom() {
		return nom;
	}
	public String getApe() {
		return ape;
	}
	public String getEmail() {
		return email;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public String getDesc() {
		return desc;
	}
	public String getLink() {
		return link;
	}
	public String getNacion() {
		return nacion;
	}
	public List<String> getNombreActividades() {
		return nombreActividades;
	}
	public List<String> getNombreSalidas() {
		return nombreSalidas;
	}
	
	public DtUsuario(String nick, String nom, String ape, String email, LocalDate fechaNac, String desc, String link, String nacion) {
		this.nick = nick;
		this.nom = nom;
		this.ape = ape;
		this.email = email;
		this.fechaNac = fechaNac;
		this.desc = desc;
		this.link = link;
		this.nacion = nacion;
		this.nombreActividades = null;
		this.nombreSalidas = null;
	}
	public DtUsuario(String nick, String nom, String ape, String email, LocalDate fechaNac, String desc, String link, String nacion,List<String> nA,List<String> nS) {
		this.nick = nick;
		this.nom = nom;
		this.ape = ape;
		this.email = email;
		this.fechaNac = fechaNac;
		this.desc = desc;
		this.link = link;
		this.nacion = nacion;
		this.nombreActividades = nA;
		this.nombreSalidas = nS;
	}
}