package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import datatype.DtModUser;
import datatype.DtUsuario;

@Entity
@DiscriminatorValue("Proveedor")
public class Proveedor extends Usuario {
    @OneToMany
    @JoinColumn(name = "nombreProv")
    @MapKey(name = "nombre")
	private Map<String,ActividadTuristica> actividadesp;
	private String descr;
	private String link;
	public String getDesc() {
		return descr;
	}
	public void setDesc(String desc) {
		this.descr = desc;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public Proveedor() {

	}
	public Proveedor(String nick, String name, String ape, String email,
			LocalDate fecha, String desc, String link) {
		super(nick, name, ape, email, fecha);
		actividadesp = new HashMap<String, ActividadTuristica>();
		this.descr = desc;
		this.link = link;
	}
	public void modificarDatos(DtModUser datosNuevos) {
		// realizar comprobaciones necesarioas antes de permitir el modificar
		super.setName(datosNuevos.getNom());
		super.setApellido(datosNuevos.getApe());
		super.setFechaNac(datosNuevos.getFechaNac());
		this.setDesc(datosNuevos.getDesc());
		this.setLink(datosNuevos.getLink());
	}
	public DtUsuario getData() {
		return new DtUsuario(getNick(), getName(), getApellido(), getEmail(),
				getFechaNac(), getDesc(), getLink(), null);
	}

	@Override
	public String toString() {
		return super.toString() + "Proveedor [desc=" + descr + ", link=" + link
				+ "]";
	}
 
	public void linkearAct(ActividadTuristica act) {
		actividadesp.put(act.getNombre(), act);
	}	
	
	public boolean esProveedor() {
		return true;
	}
	
	public List<String> listarActividades(){
		List<String> ret = new ArrayList<String>();
		actividadesp.forEach((t, u) -> {
			ret.add(t);
		});	
		return ret;
	}
	
	public List<String> listarActividadesConfirmadas(){
		List<String> ret = new ArrayList<String>();
		actividadesp.forEach((t, u) -> {
			if(u.getEstado() == estadosActividad.CONFIRMADA) {				
				ret.add(t);
			}
		});	
		return ret;
	}
	
	public List<String> listarSalidas(){
		List<String> ret = new ArrayList<String>();
		actividadesp.forEach((t, u) -> {
			ret.addAll(u.listarSalidas());
		});	
		return ret;
	}
	
	public List<String> listarSalidasConfirmadas(){
		List<String> ret = new ArrayList<String>();
		actividadesp.forEach((t, u) -> {
			if(u.getEstado() == estadosActividad.CONFIRMADA) {		
				ret.addAll(u.listarSalidas());
			}
		});	
		return ret;
	}
	
	public ActividadTuristica seleccActividad(String nombreAct) throws Exception {
		ActividadTuristica act = actividadesp.get(nombreAct);
		if (act == null)
			throw new Exception("No existe una actividad con ese nombre para seleccionar");
		
		return act;
	}
}
