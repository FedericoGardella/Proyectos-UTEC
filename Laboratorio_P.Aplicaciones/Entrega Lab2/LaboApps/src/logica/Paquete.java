package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import datatype.DtPaquete;

@Entity
@Table(name = "paquetes")
public class Paquete {
	@Id
	private String nombre;
	private String descr;
	private Integer periodoValiez;
	private float descuento;
	private LocalDate fechaAlta;
	private Integer cantTur;
	private Integer tickets;

    @ManyToMany(mappedBy = "paquetes")
    @MapKey(name = "nombre")
	private Map<String, ActividadTuristica> actividades;
    
    @OneToMany(mappedBy = "paquete")
    @MapKeyColumn(name = "nickTurista")
	public Map<String, PaqueteTurista> paquetesCompradosPor;
	
	public String getDesc() {
		return descr;
	}
	public void setDesc(String desc) {
		this.descr = desc;
	}
	public Integer getPeriodoValiez() {
		return periodoValiez;
	}
	public void setPeriodoValiez(Integer periodoValiez) {
		this.periodoValiez = periodoValiez;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getCant() {
		return cantTur;
	}
	public void setCant(Integer cant) {
		this.cantTur = cant;
	}
	public Paquete() {		
	}
	public Paquete(String nombre, String desc, Integer periodoValiez, float descuento, LocalDate fechaAlta, Integer cant, Integer tickets) {
		this.nombre = nombre;
		this.descr = desc;
		this.periodoValiez = periodoValiez;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.cantTur = cant;
		this.actividades = new HashMap<String, ActividadTuristica>();
		this.paquetesCompradosPor = new HashMap<String, PaqueteTurista>();
		this.tickets = tickets;
		
	}
	public Paquete(DtPaquete data) {
		this.nombre = data.getNombre();
		this.descr = data.getDesc();
		this.periodoValiez = data.getPeriodoValidez();
		this.descuento = data.getDescuento();
		this.fechaAlta = data.getFechaAlta();
		this.cantTur = data.getCant();
		this.actividades = new HashMap<String, ActividadTuristica>();
		this.paquetesCompradosPor = new HashMap<String, PaqueteTurista>();
		this.tickets = data.getTickets();
	}
	public void agregarActividad(ActividadTuristica a) throws Exception {
		if (this.actividades.get(a.getNombre()) != null)
			throw new Exception("Actividad ya registrada.");
		this.actividades.put(a.getNombre(), a);
		a.agregarPaquete(this);
	}
	public List<String> listarActividades() {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, u) -> ret.add(u.getNombre()));
		return ret;
	}
	public DtPaquete getData() {
		return new DtPaquete(nombre, descr, periodoValiez, descuento, fechaAlta, cantTur, tickets);
	}
	public DtPaquete getDataTodo() {
		return new DtPaquete(nombre, descr, periodoValiez, descuento, fechaAlta, cantTur, listarActividades(), tickets);
	}
	public boolean fueComprado() {
		return !this.paquetesCompradosPor.isEmpty();
	}
	
	public void enlazarPaqueteTurista(PaqueteTurista paq) {
		this.paquetesCompradosPor.put(paq.getNombreTurista(), paq);
	}
	
	public List<String> listarCategorias() {
		List<String> ret = new ArrayList<String>();
		this.actividades.forEach((t, u) -> 
			ret.addAll(u.listarCategorias())
		);
		Set<String> conjunto = new HashSet<>(ret);
		ret.clear();
		ret.addAll(conjunto);
		return ret;
	}
	
	public boolean tieneActividades() {
	    return actividades.size() > 0;
	}
	
	public boolean paqueteCompradoPorTur(String nombreTur) {
	    for (PaqueteTurista paqueteTurista : paquetesCompradosPor.values()) {
	        if (paqueteTurista.getNombreTurista().equals(nombreTur)) {
	            // Se encontró un paquete comprado por el turista
	            return true;
	        }
	    }
	    // No se encontraron paquetes comprados por el turista
	    return false;
	}

	public Float obtenerCostoTotal() {
		Float sumaCostos = (float) 0;
		Integer totalActividades = actividades.size();
		
		for (ActividadTuristica actividad : actividades.values()) {
	        sumaCostos += actividad.getCosto();
	    }

	    if (totalActividades > 0) {
	        return (sumaCostos / totalActividades) * this.cantTur * this.tickets * (1- (this.descuento / 100));
	    } else {
	        return (float) 0; // Devuelve 0 si no hay actividades en el mapa para evitar una división por cero.
	    }
	}
	
	public boolean hayLugar() {
		return this.cantTur > 0;
	}

	public List<String> actividadURL(List<String> acts) {
		List<String> urls = new ArrayList<>();

	    for (String nombre : acts) {
	        ActividadTuristica actividad = actividades.get(nombre);
		
	        String url = actividad.getImagen();
	        if (url != null) {
                urls.add(url);
            } else {
                urls.add("nulo");
            }
	    }
	    return urls;
	}
	public Integer getTickets() {
		return tickets;
	}
	public void setTickets(Integer tickets) {
		this.tickets = tickets;
	}
	
	public boolean tieneASal(String sal) {
		boolean tiene = false;
		for (ActividadTuristica acts : actividades.values()) {
			tiene = (tiene || acts.tieneASal(sal));
		}
		return tiene;
	}
}
