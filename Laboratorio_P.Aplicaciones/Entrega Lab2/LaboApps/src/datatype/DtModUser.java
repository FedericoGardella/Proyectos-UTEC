package datatype;

import java.time.LocalDate;

public class DtModUser {
	private String nom;
	private String ape;
	private LocalDate fechaNac;
	private String nacion;
	private String desc;
	private String link;
	public String getNom() {
		return nom;
	}
	public String getApe() {
		return ape;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public String getNacion() {
		return nacion;
	}
	public String getDesc() {
		return desc;
	}
	public String getLink() {
		return link;
	}
	
	public DtModUser(String nom, String ape, LocalDate fechaNac, String desc, String link) {
		this.nom = nom;
		this.ape = ape;
		this.fechaNac = fechaNac;
		this.desc = desc;
		this.link = link;
	}
	public DtModUser(String nom, String ape, LocalDate fechaNac, String nacion) {
		this.nom = nom;
		this.ape = ape;
		this.fechaNac = fechaNac;
		this.nacion = nacion;
	}
	
	
	
}
