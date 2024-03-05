package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import datatype.DtSalidaTuristica;
import datatype.DtActTur;

@Entity
@Table(name = "actividades")
public class ActividadTuristica{
	@Id
	private String nombre;
    @OneToMany
    @JoinColumn(name = "nombreAct")
    @MapKey(name = "nombre")
	private Map<String,SalidaTuristica> salidas;
//	@Transient
	@ManyToMany
	@JoinTable(name="AT_PAQ",
		joinColumns=@JoinColumn(name="AT_ID"),
		inverseJoinColumns=@JoinColumn(name="PAQ_ID"))
	@MapKeyColumn(name = "nombre")
	private Map<String, Paquete> paquetes;
    @ManyToOne
    @JoinColumn(name = "nombre_dep")
	private Departamento departamento;
	
	@Column
	private String descr;
	@Column
	private Integer duracion;
	@Column
	private Float costo;
	@Column
	private String ciudad;
	@Column
	private LocalDate fecha;
	
	public String getNombre() {
		return nombre;
	}
	public String getDesc() {
		return descr;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public Float getCosto() {
		return costo;
	}
	public String getCiudad() {
		return ciudad;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public ActividadTuristica () {
	}
	
	public ActividadTuristica(String nombre, String desc, Integer duracion, Float costo, String ciudad,	LocalDate fecha, Departamento d) {
		this.nombre = nombre;
		this.descr = desc;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.fecha = fecha;
		this.departamento = d;
		this.salidas = new HashMap<String, SalidaTuristica>();
		this.paquetes = new HashMap<String, Paquete>();
	}
	
	public void printDataAct() {}
	
	public List<String> verSalidasVigentes() {
		List<String> ret = new ArrayList<String>();
		for (SalidaTuristica s : salidas.values()) {
			int compar = s.getFechaSalida().compareTo(LocalDate.now());
			if (compar >= 0) {
				ret.add(s.getNombre());		
			}
		}
		return ret;
	}

	public SalidaTuristica buscarSalida(String nombre) {
		return salidas.get(nombre);
	}

	public String getNombreDepartamento() {
		return this.departamento.getNombre();
	}
	
	public boolean enlazadoConPaquete(String nombre) {
		return (paquetes.get(nombre) != null);
	}
	
//	Consulta de Actvididad Turistica
	public void printDataActTur() {
		System.out.println("\nActividad Turistica: ");
		System.out.println("Nombre: " + this.getNombre());
		System.out.println("Descripcion: " + this.getDesc());
		System.out.println("Duracion: " + this.getDuracion());
		System.out.println("Costo por turista: " + this.getCosto());
		System.out.println("Ciudad: " + this.getCiudad());
		System.out.println("Fecha de alta: " + this.getFecha());
//		Faltan mostrar las salidas y paquetes asociados (que no los hemos creado todavia)
//		Falta mostrar la opcion de seleccionar una salida o paquete, y si lo hace llamar a Consulta
//		Salida Turistica o Consulta Paquete
	}
	
	public DtActTur getDataActTur() {
		DtActTur at = new DtActTur(this.getNombre(),this.getDesc(),this.getDuracion(),this.getCosto(),this.getCiudad(),this.getFecha());
		return at;
	}
	
//	Alta de Salida Turistica
	public SalidaTuristica crearSalida(DtSalidaTuristica datos) throws Exception {
		SalidaTuristica sal = new SalidaTuristica(datos.getNombreSalida(),datos.getFechaSalida(),datos.getHoraSalida(),datos.getLugarSalida(),datos.getCantMaxTur(),datos.getFechaAlta());
		if (salidas.containsKey(sal.getNombre())) {
			throw new Exception("Ya existe una salida con ese nombre");
		}
		salidas.put(sal.getNombre(), sal);
		return sal;
	}
	
//	Consulta de Salida Turistica
	public void verSalidas() {
		System.out.println("\nSalidas:");
		for (Map.Entry<String, SalidaTuristica> entry : salidas.entrySet()) {
			SalidaTuristica st = entry.getValue();
			System.out.println("Nombre: " + st.getNombre());
		}
	}
	
	public List<String> listarSalidas() {
		List<String> ret = new ArrayList<String>();
		this.salidas.forEach((t, u) -> {
			ret.add(t);
		});
		return ret;
	}
	
//	public void printDataSalTur(String nombreSal) {
//		SalidaTuristica sal = salidas.get(nombreSal);
//		sal.printDataSalidaTuristica();		
//	}
	public void agregarPaquete(Paquete p) throws Exception{
		if (this.paquetes.containsKey(p.getNombre()))
			throw new Exception("Paquete ya existente en la actividad");
		this.paquetes.put(p.getNombre(), p);
	}
	
	public DtSalidaTuristica datosSalTur(String nombreSal) {
		SalidaTuristica sal = salidas.get(nombreSal);
		return sal.dtSalTur();
	}

	public DtActTur getData() {
		return new DtActTur(nombre, descr, duracion, costo, ciudad, fecha);
	}
	
	public List<String> listarProveedores() {
		List<String> ret = new ArrayList<String>();
		this.paquetes.forEach((t,u)-> {
			ret.add(t);
		});
		return ret;
	}
	
}
