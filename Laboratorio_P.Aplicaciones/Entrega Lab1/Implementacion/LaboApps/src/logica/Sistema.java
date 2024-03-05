package logica;

import datatype.DtActTur;

import java.awt.Color;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;
import java.util.ArrayList;


import datatype.DtModUser;
import datatype.DtPaquete;
import datatype.DtUsuario;
import presentacion.Pantalla;
import datatype.DtActTur;
import datatype.DtSalidaTuristica;
import datatype.DtDepartamento;

public class Sistema implements ISistema {
	private Map<String, Usuario> usuarios;
	private Usuario memUsuario;
	private Map<String, Departamento> departamentos;
	private Departamento memDepartamento;
	private ActividadTuristica memActividadTuristica;
	private Map<String, Paquete> paquetes;
	private Paquete memPaquete;
	private EntityManager em;
	private static Sistema instance;
	
	public Map<String, Departamento> cargarDepartamentos() {
		String jpql = "SELECT d FROM Departamento d";
		List<Departamento> deps = em.createQuery(jpql, Departamento.class).getResultList();
		
        Map<String, Departamento> depas = new HashMap<>();
        for (Departamento dep : deps) {
            depas.put(dep.getNombre(), dep);
        }
        return depas;
	}
	
	public Map<String, Usuario> cargarUsuarios() {
		String jpql = "SELECT u FROM Usuario u";
		List<Usuario> usrs = em.createQuery(jpql, Usuario.class).getResultList();
		
        Map<String, Usuario> usus = new HashMap<>();
        for (Usuario u : usrs) {
            usus.put(u.getNick(), u);
        }
        return usus;
	}
	
	public Map<String, Paquete> cargarPaquetes() {
		String jpql = "SELECT p FROM Paquete p";
		List<Paquete> pqs = em.createQuery(jpql, Paquete.class).getResultList();
		
        Map<String, Paquete> paqs = new HashMap<>();
        for (Paquete p : pqs) {
            paqs.put(p.getNombre(), p);
        }
        return paqs;
	}
	
	private Sistema() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("LabPA");
		em = factory.createEntityManager();
		usuarios = cargarUsuarios();
		departamentos = cargarDepartamentos();
		paquetes = cargarPaquetes();
	}
	
	public static Sistema getInstance() {
		if (instance == null) 
			instance = new Sistema();
		return instance;
	}
	
	public void altaUsuario(boolean provotour, DtUsuario data) throws Exception{
        if (usuarios.containsKey(data.getNick())) {
        	throw new Exception("Ya existe un usuario con ese Nick");
        }
        String email = data.getEmail();
        String email2;
		for (Usuario us : usuarios.values()) {
			email2 = us.getEmail();
			if (email.equals(email2)) {
				throw new Exception("Ya existe un usuario con ese Email");
			}
		}
		Usuario u;
		if (provotour == false) {
            u = new Turista(data.getNick(),data.getNom(),data.getApe(),data.getEmail(),data.getFechaNac(),data.getNacion());
        } else {
        	u = new Proveedor(data.getNick(),data.getNom(),data.getApe(),data.getEmail(),data.getFechaNac(),data.getDesc(),data.getLink());
        }
		this.usuarios.put(data.getNick(),u);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(u);
		tx.commit();
	};
	

	
	public void obtenerDatosDepartamento(String nombre) throws Exception {
		if (this.departamentos.get(nombre) != null)
			throw new Exception("Departamento ya existente con ese nombre");
		departamentos.put(nombre, new Departamento(nombre));		
	}
	
	public void obtenerDatosDepartamento(Departamento d) throws Exception {
		if (this.departamentos.get(d.getNombre()) != null)
			throw new Exception("Departamento ya existente con ese nombre");
		departamentos.put(d.getNombre(), d);
	}
	
	public List<String> listarProv() {
		List<String> ret = new ArrayList<String>();
		for (Usuario u : usuarios.values()) {
			if (u instanceof Proveedor) {
				ret.add(u.getNick());		
			}
		}
		return ret;
	}
	
	//Inscripcion a Salida Turistica
	
	public List<String> listarTuris() {
		List<String> ret = new ArrayList<String>();
		for (Usuario u : usuarios.values()) {
			if (u instanceof Turista) {
				ret.add(u.getNick());		
			}
		}
		return ret;
	}
	
	public List<String> salidasVigentesDeAct(String nombreAct) {
		try {
			this.memActividadTuristica = this.memDepartamento.seleccActividad(nombreAct);
		} catch (Exception e) {
			System.err.println(e);
		}
		return this.memActividadTuristica.verSalidasVigentes();
	}
	
	public SalidaTuristica buscarSalida(String nombreSal) {
		return this.memActividadTuristica.buscarSalida(nombreSal);
	}
	
	public void altaInscripcion(String nickTuris, String nombreSal, int cant ) throws Exception{
		EntityTransaction tx = em.getTransaction();
		SalidaTuristica sal = buscarSalida(nombreSal);
		if (sal.getCantMaxTuristas() < cant) {
			throw new Exception("Cantidad de inscriptos superada.");
		}
		Turista tur = (Turista) usuarios.get(nickTuris);
		
		TuristaSalida ins = tur.crearInscripcion(sal, cant);
		tx.begin();
		em.persist(ins);
		tx.commit();
		
	}

//	Alta Actividad

	public void altaActividad(String prov, String dep, DtActTur data) throws Exception {
		Usuario u = usuarios.get(prov);
		if (u == null)
			throw new Exception("Usuario no encontrado");
		if (!u.esProveedor())
			throw new Exception("Usuario debe ser proveedor");
		Departamento d = departamentos.get(dep);
		if (d == null)
			throw new Exception("Departamento no encontrado");
		
		EntityTransaction tx = em.getTransaction();
		ActividadTuristica act = d.crearAct(data);
	
		u.linkearAct(act);
		
		tx.begin();
		em.persist(act);
		tx.commit();
	}

	public void modificarDatos(DtModUser datosNuevos) {
		Usuario u = memUsuario;
		EntityTransaction tx = em.getTransaction();
		u.modificarDatos(datosNuevos);
		
		tx.begin();
		em.persist(u);
		tx.commit();
	}
	
	public List<String> listarUsuarios() {
		List<String> ret = new ArrayList<String>();
		usuarios.forEach((t, u) -> {
//			ret.add(u.getName() + " " + u.getApellido());
			ret.add(t);
		});
		return ret;
	}
	

	public List<String> listarDepartamentos() {
		List<String> ret = new ArrayList<String>();
		departamentos.forEach((t, u) -> {
			ret.add(t);
		});
		return ret;
	}
	
	// Consulta de Usuario
	
	
	public DtUsuario obtenerDataUsuarioPlus(String nick) {
		Usuario user = usuarios.get(nick);
		if (user == null)
			return null;
		if (user instanceof Proveedor) {
			Proveedor u = (Proveedor)user;
			List<String> acts = u.listarActividades();
			List<String> sals = u.listarSalidas();
			return new DtUsuario(u.getNick(), u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(), u.getDesc(), u.getLink(), null,acts,sals);
		} else {
			Turista u = (Turista)user;
			List<String> sals = u.listarSalidas();
			return new DtUsuario(u.getNick(), u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(), null, null, u.getNacionalidad(),null,sals);
		}
	}
	
	//
	
	public DtUsuario obtenerDataUsuario(String nick) {
		Usuario user = usuarios.get(nick);
		if (user == null)
			return null;
		if (user instanceof Proveedor) {
			Proveedor u = (Proveedor)user;
			return new DtUsuario(u.getNick(), u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(), u.getDesc(), u.getLink(), null);
		} else {
			Turista u = (Turista)user;
			return new DtUsuario(u.getNick(), u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(), null, null, u.getNacionalidad());
		}
	}
	
	public void selecUsuario(String nick) {
		try {
			Usuario u = usuarios.get(nick);
			if (u == null)
				new Exception("No se encontro dicho nick.");
			memUsuario = u;
		}catch (Exception e) {
			System.out.println(e);
		}
	}


//	Crear Paquete de Actividades Turisticas
	public void altaPaquete(DtPaquete dataPaquete) throws Exception {

		if (paquetes.containsKey(dataPaquete.getNombre())) 
			throw new Exception("Nombre de paquete ya existente.");
		
		EntityTransaction tx = em.getTransaction();
		Paquete p = new Paquete(dataPaquete);
		paquetes.put(p.getNombre(), p);
		
		tx.begin();
		em.persist(p);
		tx.commit();
	}
	
//	Agregar Actividad Turistica a Paquete
	public List<String> listarPaquetesExistentes() {
		List<String> ret = new ArrayList<String>();
		paquetes.forEach((t, u) -> {
			ret.add(t);
		});	
		return ret;
	}
	
	public void seleccionarPaquete(String nombre) throws Exception {
		Paquete p = paquetes.get(nombre);
		if (p == null)
			throw new Exception("No existe un paquete con ese nombre");
		this.memPaquete = p;
	}

	/**
	 * Lista las actividades turísticas
	 * que se realizan en dicho departamento que no forman parte del paquete
	 * seleccionado
	 */
	public List<String> listarActividadesDepartamento() {
		List<String> ret = memDepartamento.listarActividadesPaquete(memPaquete.getNombre());
		return ret;
	}

//	public void seleccionarActividadParaPaquete(String nombreAct) throws Exception {
//		memPaquete.agregarActividad(memDepartamento.seleccActividad(nombreAct));
//	}
	
	public void seleccionarActividadParaPaquete(String nombreAct) throws Exception {
	    ActividadTuristica act = memDepartamento.seleccActividad(nombreAct);

	    Paquete paq = memPaquete;
	    // Añade la actividad al paquete
	    paq.agregarActividad(act);

	   	EntityTransaction tx = em.getTransaction();

	    tx.begin();

	    // Actualiza las entidades en el contexto de persistencia
	    em.merge(act); // Si no estaba siendo administrada
	    em.merge(paq);   // Si no estaba siendo administrada

	    // Finaliza la transacción
	    tx.commit();
	}
	
	
	
	
// 	Consulta de Paquete de Actividades Turisticas
	public DtPaquete imprimirInfoPaquete(String nombre) throws Exception {
		Paquete p = this.paquetes.get(nombre);
		if (p == null)
			throw new Exception("Paquete no encontrado");
		return p.getDataTodo();	
	}
		
//	Consulta de Actvididad Turistica
	public List<String> seleccionarDepartamento(String nombreDep) throws Exception {
		Departamento dep = departamentos.get(nombreDep);
		if (dep == null)
			throw new Exception("No existe un departamento con ese nombre");
		this.memDepartamento = dep;
		return dep.printActividades();
	}	
	
	public void seleccionarActividad(String nombreAct) {
		this.memDepartamento.selecActividad(nombreAct);
	}
	
	public DtActTur seleccionarActividadDt(String nombreAct) {
		return this.memDepartamento.selecActividadDt(nombreAct);
	}

	public List<String> verProveedorCA(String nombreAct) throws Exception {
		try {
			this.memActividadTuristica = this.memDepartamento.seleccActividad(nombreAct);
		} catch (Exception e) {
			System.err.println(e);
		}
		return this.memDepartamento.listProveedores(nombreAct);
	}


//	Alta de Salida Turistica
	public void confirmarAltaSalTur(String nombreAct, DtSalidaTuristica datos) throws Exception {
		EntityTransaction tx = em.getTransaction();
		
		SalidaTuristica sal = this.memDepartamento.confirmarAltaSalTur(nombreAct, datos);
		
		tx.begin();
		em.persist(sal);
		tx.commit();
	}
	

//	Consulta de Salida Turistica
	public List<String> seleccionarActividadST(String nombreAct) throws Exception {
		try {
			this.memActividadTuristica = this.memDepartamento.seleccActividad(nombreAct);
		} catch (Exception e) {
			System.err.println(e);
		}
		return this.memDepartamento.listSalidas(nombreAct);
	}
	
	public DtSalidaTuristica seleccionarSalida(String nombreSal) {
		return this.memActividadTuristica.datosSalTur(nombreSal);
	}
	
// Alta Departamento
	public void altaDepartamento(DtDepartamento data){
	    EntityTransaction tx = em.getTransaction();
	    
		Departamento d = new Departamento(data.getNom(),data.getDescr(),data.getURL());
		this.departamentos.put(data.getNom(),d);
		
		tx.begin();
		em.persist(d);
		tx.commit();
	}

	public List<String> verActividadesCU(String nickProv) throws Exception {
		Usuario prov = usuarios.get(nickProv);
		if (prov == null)
			throw new Exception("No existe un proveedor con ese nick");
		this.memUsuario = prov;
		Proveedor u = (Proveedor)prov;
		return u.listarActividades();		
	}	
	
	public List<String> verSalidasCU(String nombreAct) throws Exception {
		
		Usuario prov = this.memUsuario;
		Proveedor u = (Proveedor)prov;
		ActividadTuristica act = u.seleccActividad(nombreAct);
		
		this.memActividadTuristica = act;
		return act.listarSalidas();
	}

	public List<String> verInscripcionesCU(String nickTur) throws Exception {
		Usuario tur = usuarios.get(nickTur);
		if (tur == null)
			throw new Exception("No existe un turista con ese nick");
		this.memUsuario = tur;
		Turista u = (Turista)tur;
		return u.listarSalidas();		
	}	

}