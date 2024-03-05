package logica;

public class Fabrica {
	
	public static ISistema getSistema() {
		return Sistema.getInstance();
	}
}
