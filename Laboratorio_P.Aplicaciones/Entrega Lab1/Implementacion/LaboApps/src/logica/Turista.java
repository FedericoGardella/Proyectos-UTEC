package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Transient;

import datatype.DtInsc;
import datatype.DtModUser;
import datatype.DtUsuario;

@Entity
@DiscriminatorValue("Turista")
public class Turista extends Usuario {
	private String nacionalidad;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "nombreTur")
    @MapKey(name = "id")
	private Map<String, TuristaSalida> inscripciones;//el id es el nombre de la SalidaTuristica
    
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Turista() {

	}
	public Turista(String nick, String name, String ape, String email,
			LocalDate fecha, String nacion) {
		super(nick, name, ape, email, fecha);
		this.nacionalidad = nacion;
		inscripciones = new HashMap<String, TuristaSalida>();
	}

	public void modificarDatos(DtModUser datosNuevos) {
		// realizar comprobaciones necesarioas antes de permitir el modificar
		super.setName(datosNuevos.getNom());
		super.setApellido(datosNuevos.getApe());
		super.setFechaNac(datosNuevos.getFechaNac());
		this.setNacionalidad(datosNuevos.getNacion());
	}
	public DtUsuario getData() {
		return new DtUsuario(getNick(), getName(), getApellido(), getEmail(),
				getFechaNac(), null, null, nacionalidad);
	}

	@Override
	public String toString() {
		return super.toString() + "Turista [nacionalidad=" + nacionalidad + "]";
	}
	public void linkearAct(ActividadTuristica act) {}

	
	public boolean esProveedor() {
		return false;
	}
	
	public TuristaSalida crearInscripcion(SalidaTuristica sal, int cant) throws Exception {
		String nomSal = sal.getNombre();
		TuristaSalida ins = inscripciones.get(nomSal);
		if (ins != null)
			throw new Exception("El turista ya se encuentra inscripto a la salida");
		ins = new TuristaSalida(sal,cant,LocalDate.now(),this);
		inscripciones.put(nomSal, ins);
		
		return ins;
	}
	
	public List<String> listarSalidas(){
		List<String> ret = new ArrayList<String>();
		inscripciones.forEach((t, u) -> {
			ret.add(t);
		});
		return ret;
	}

}
