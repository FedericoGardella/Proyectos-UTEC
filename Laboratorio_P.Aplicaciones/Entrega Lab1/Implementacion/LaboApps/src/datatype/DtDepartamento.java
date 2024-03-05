package datatype;


public class DtDepartamento {
	private String nom;
	private String descr;
	private String url;

	public String getNom() {
		return nom;
	}
	public String getDescr() {
		return descr;
	}
	public String getURL() {
		return url;
	}


	public DtDepartamento(String nom, String descr, String url) {
		this.nom = nom;
		this.descr = descr;
		this.url = url;
	}
}