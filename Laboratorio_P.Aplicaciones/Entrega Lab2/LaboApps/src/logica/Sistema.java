package logica;


import datatype.DtActTur;
import datatype.DtActTurEst;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import datatype.DtModUser;
import datatype.DtPaquete;
import datatype.DtUsuario;
import datatype.DtSalidaTuristica;
import datatype.DtDepartamento;

import org.mindrot.jbcrypt.BCrypt;

public class Sistema implements ISistema {
	private Map<String, Usuario> usuarios;
	private Usuario memUsuario;
	private Map<String, Departamento> departamentos;
	private Departamento memDepartamento;
	private ActividadTuristica memActividadTuristica;
	private Categoria memCategoria;
	private Map<String, Paquete> paquetes;
	private Map<String, Categoria> categorias;
	private Paquete memPaquete;
	private EntityManager em;
	private static Sistema instance;
	
	private String encriptarPassword(String password) {
	    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	    return hashedPassword;
	}
	
	/**
	 * Valida si una contraseña sin encriptar, es igual a una encriptada
	 * 
	 * @param passwordPlano Contraseña sin encriptar a comprar
	 * @param passwordEncriptado Contraseña encriptada (posbilemente proveniente de la BD)
	*/
	private boolean verificarPassword(String passwordPlano, String passwordEncriptado) {
	    return BCrypt.checkpw(passwordPlano, passwordEncriptado);
	}
	
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
	
	public Map<String, Categoria> cargarCategorias() {
		String jpql = "SELECT c FROM Categoria c";
		List<Categoria> cats = em.createQuery(jpql, Categoria.class).getResultList();
        Map<String, Categoria> cates = new HashMap<>();
        for (Categoria c : cats) {
            cates.put(c.getNombre(), c);
        }
        return cates;
	}
	
	
	private Sistema() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("LabPA");
		em = factory.createEntityManager();
		usuarios = cargarUsuarios();
		departamentos = cargarDepartamentos();
		paquetes = cargarPaquetes();
		categorias = cargarCategorias();
		memUsuario = null;
		memDepartamento = null;
		memActividadTuristica = null;
		memCategoria = null;
		memPaquete = null;	
	}
	
	public static Sistema getInstance() {
		if (instance == null) 
			instance = new Sistema();
		return instance;
	}
	
	public List<String> listarPaquetesCompradosTur(String tur,String sal){
		Turista turi = (Turista) usuarios.get(tur);
		return turi.listarPaquetesCompradosConSal(sal);
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
	public void altaUsuario(boolean provotour, DtUsuario data, String password) throws Exception{
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
		em.createNativeQuery("UPDATE usuarios SET userPassword = :uPasswd WHERE nick = :uNick")
			.setParameter("uPasswd", encriptarPassword(password))
			.setParameter("uNick", data.getNick())
			.executeUpdate();
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
		em.merge(tur);
		tx.commit();
		
	}
	
// Alta Categoria
	
	public void altaCategoria(String nombre) throws Exception{
		EntityTransaction tx = em.getTransaction();
		Categoria cat = categorias.get(nombre);
		if (cat != null) {
			throw new Exception("Ya existe una categoria con ese nombre.");
		}
		cat = new Categoria(nombre);
		categorias.put(cat.getNombre(), cat);
		
		tx.begin();
		em.persist(cat);
		tx.commit();
	}
	
	public List<String> listarCategorias() {
		List<String> ret = new ArrayList<String>();
		categorias.forEach((t, u) -> {
			ret.add(t);
		});
		return ret;
	}
	
	public List<String> verCategoriasCA(String nombreAct) throws Exception {
		try {
			this.memActividadTuristica = this.memDepartamento.seleccActividad(nombreAct);
		} catch (Exception e) {
			System.err.println("Exception verProveedorCA:" + e.getMessage());
		}
		return this.memActividadTuristica.listarCategorias();
	}
	
	public List<String> verCategoriasCAC(String nombreAct) throws Exception {
		try {
			this.memActividadTuristica = this.memCategoria.seleccActividad(nombreAct);
		} catch (Exception e) {
			System.err.println("Exception verProveedorCA:" + e.getMessage());
		}
		return this.memActividadTuristica.listarCategorias();
	}
	
	public List<String> verCategoriasCP(String nombrePaq){
		Paquete p = paquetes.get(nombrePaq);
		return p.listarCategorias();
	}
	
	public List<String> verActividadesCat(String cat) {
		this.memCategoria = categorias.get(cat);
		Categoria c = categorias.get(cat);
		return c.listarActividades();
	}
	

//	Alta Actividad

	public void altaActividad(String prov, String dep, DtActTur data, List<String> cates) throws Exception {
		Usuario u = usuarios.get(prov);
		if (u == null)
			throw new Exception("Usuario no encontrado");
		if (!u.esProveedor())
			throw new Exception("Usuario debe ser proveedor");
		Departamento d = departamentos.get(dep);
		if (d == null)
			throw new Exception("Departamento no encontrado");
		
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		Categoria c;
        for (String item : cates) {
            c = categorias.get(item);
            categos.put(c.getNombre(),c);
        }
		EntityTransaction tx = em.getTransaction();
		ActividadTuristica act = d.crearAct(data,categos);
	
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
	
	public void selecUsuario(String nick) throws Exception {
		Usuario u = usuarios.get(nick);
		if (u == null)
			throw new Exception("No se encontro dicho nick.");
		this.memUsuario = u;
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
	
	public List<String> listarPaquetesExistentesNoComprados(String nombreTur) {
		List<String> ret = new ArrayList<String>();
		paquetes.forEach((t, u) -> {
			if (!u.paqueteCompradoPorTur(nombreTur)) 
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
	public List<String> listarActividadesDepartamentoConfirmadas() {
		List<String> ret = memDepartamento.listarActividadesPaqueteConfirmada(memPaquete.getNombre());
		return ret;
	}

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
	
	public void seleccionarDepartamentoSegunActividad (String nombreAct) throws Exception {
		boolean encontrado = false;
		for (Departamento dep : this.departamentos.values()) {
			if (dep.contieneActividad(nombreAct)) {
				this.memDepartamento = dep;
				encontrado = true;
			}
		}
		if (!encontrado)
			throw new Exception("Nombre de actividad no encontrado en ningun departamento");
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
		} catch (Exception secondException) {
	            System.err.println("Exception verProveedorCA:" + secondException.getMessage());   
	    }
		return this.memDepartamento.listProveedores(nombreAct);
	}

	public List<String> verProveedorCAC(String nombreAct) throws Exception {
		try {
			this.memActividadTuristica = this.memCategoria.seleccActividad(nombreAct);
		} catch (Exception secondException) {
	            System.err.println("Exception verProveedorCA:" + secondException.getMessage());   
	    }
		return this.memActividadTuristica.listarProveedores();
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
			System.err.println("Exception seleccionarActividadST:" + e.getMessage());
		}
		return this.memDepartamento.listSalidas(nombreAct);
	}
	
	public List<String> seleccionarActividadSTC(String nombreAct) throws Exception {
		try {
			this.memActividadTuristica = this.memCategoria.seleccActividad(nombreAct);
		} catch (Exception e) {
			System.err.println("Exception seleccionarActividadST:" + e.getMessage());
		}
		return this.memActividadTuristica.listarSalidas();
	}
	
	public void guardarDepartamentoYActividadSegunSalida(String nombreSal) {
		ActividadTuristica act;
		for (Map.Entry<String, Departamento> d: departamentos.entrySet()) {
			act = d.getValue().contieneActividadSegunSalida(nombreSal);
			if (act != null) {
				this.memDepartamento = d.getValue();
				this.memActividadTuristica = act;
				return;
			}
		}
	}
	
	public String guardarDepartamentoSegunAct(String act) {
      for (Departamento departamento : departamentos.values()) {
           
    	  Map<String, ActividadTuristica> actividades = departamento.getMapActividades();
          ActividadTuristica actividad = actividades.get(act);
            
          if (actividad != null) {
              this.memDepartamento = departamento;
              this.memActividadTuristica = actividad;
              return departamento.getNombre();
          }
       }
      return null;
	}
	
	public DtSalidaTuristica seleccionarSalida(String nombreSal) {
		return this.memActividadTuristica.datosSalTur(nombreSal);
	}
	
// 	Alta Departamento
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

	private boolean esTurista(String nickTur) throws Exception {
		if (!this.usuarios.containsKey(nickTur))
			throw new Exception("Nick no existe");
		return (this.usuarios.get(nickTur) instanceof Turista);
	}
	
	public void turistaCompraPaquete(String nickTur, String nombrePaq) throws Exception {
		if (!esTurista(nickTur))
			throw new Exception("No es turista");
		Turista tur = (Turista)this.usuarios.get(nickTur);
		Paquete paq = this.paquetes.get(nombrePaq);
		if (tur.contienePaquete(nombrePaq))
			throw new Exception("Dicho turista ya contiene ese paquete");
		PaqueteTurista paqTur = tur.comprarPaquete(paq);
		paq.enlazarPaqueteTurista(paqTur);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(paqTur);
		em.merge(tur);
		em.merge(paq);
		tx.commit();
	}

//	Aceptar/Rechazar Actividad Turistica
	
	public List<String> listarActAgregadas() {
		List<String> ret = new ArrayList<String>();
		this.departamentos.forEach((t,u) -> {
			u.listarActividadesAg().forEach(ret::add);
		});
		
		return ret;
	}
	
	public void imprimirLista() throws Exception {
		List<String> lista = listarActAgregadas();
		for (String elemento : lista) {
	        System.out.println(elemento);
	    }
	}
	
	public void altaActividadEstado(String prov, String dep, DtActTur data, String est) throws Exception {
		Usuario u = usuarios.get(prov);
		if (u == null)
			throw new Exception("Usuario no encontrado");
		if (!u.esProveedor())
			throw new Exception("Usuario debe ser proveedor");
		Departamento d = departamentos.get(dep);
		if (d == null)
			throw new Exception("Departamento no encontrado");
		
		estadosActividad eact = estadosActividad.valueOf(est);
		
		EntityTransaction tx = em.getTransaction();
		ActividadTuristica act = d.crearActEstado(data,eact);
	
		u.linkearAct(act);
		
		tx.begin();
		em.persist(act);
		tx.commit();
	}
	
	public void acepRecActividad(String nombreAct, String es) {
		EntityTransaction tx = em.getTransaction();
		estadosActividad est = estadosActividad.valueOf(es);
		for (Departamento dep : departamentos.values()) {
	        ActividadTuristica act = dep.buscarAct(nombreAct,est);
	        if (act != null) {
//	        	System.out.println("Siuuu");
	        	act.cambiarEstado(est);
	        	tx.begin();
	    		em.persist(act);
	    		tx.commit();
	            return;
	        }
	    }
	}
	
	public DtActTurEst seleccionarActividadDtEst(String nombreAct) {
		
		for (Departamento dep : departamentos.values()) {
			DtActTurEst dtact = dep.encontrarAct(nombreAct);
	        if (dtact != null) {
	        	return dtact;
	        }
	    }
		return null;
	}

	public List<String> seleccionarDepartamentoConf(String nombreDep) throws Exception {
		Departamento dep = departamentos.get(nombreDep);
		if (dep == null)
			throw new Exception("No existe un departamento con ese nombre");
		this.memDepartamento = dep;
		return dep.listarActividadesConf();
	}	

	public List<String> listarActividadesDepartamentoConf() {
		List<String> ret = memDepartamento.listarActividadesPaqueteConf(memPaquete.getNombre());
		return ret;
	}
	
	public DtSalidaTuristica seleccionarSalidaA(String nombreSal, String nombreAct, String nombreDep) {
		Departamento dep = this.departamentos.get(nombreDep);
		System.out.println(dep.getNombre());
		this.memActividadTuristica = dep.buscarAct2(nombreAct);
		
		
		return this.memActividadTuristica.datosSalTur(nombreSal);
	}

//	Agregadas para la segunda entrega del laboratorio
	
	public void cerrarConexionBD() {
        if (em != null && em.isOpen()) {
            em.close();
        }
	}
	
	public boolean checkUserNickExist(String nick) {
		return this.usuarios.containsKey(nick);
	}
	public boolean checkUserEmailExist(String email) {
		for (Map.Entry<String, Usuario> e : this.usuarios.entrySet())
			if (((Usuario) e.getValue()).getEmail().equals(email)) 
				return true;
		return false;
	}
	
	public boolean checkValidUser(String nick, String password) {
		if (this.usuarios.containsKey(nick)) {
			List<String> passwordEncrypted = em.createNativeQuery("SELECT userpassword FROM usuarios WHERE nick = :uNick")
				.setParameter("uNick", nick)
				.getResultList();
			if (passwordEncrypted.size() == 1)
				return verificarPassword(password, passwordEncrypted.get(0));
		}
		if (checkUserEmailExist(nick)) {
			List<String> passwordEncrypted = em.createNativeQuery("SELECT userpassword FROM usuarios WHERE email = :uEmail")
					.setParameter("uEmail", nick)
					.getResultList();
			if (passwordEncrypted.size() == 1)
				return verificarPassword(password, passwordEncrypted.get(0));
		}
		
		return false;
	}
	public DtUsuario obtenerDataUsuarioConEmail(String email) {
		Usuario user = null;;
		for (Map.Entry<String, Usuario> e : this.usuarios.entrySet()) {			
			if (((Usuario) e.getValue()).getEmail().equals(email)) {
				user = e.getValue();
				break;
			}
		}
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
	public void modificarDatosWeb(String nick, DtModUser newData) throws Exception{
		Usuario u = usuarios.get(nick);
		if (u == null)
			throw new Exception("No se encontro dicho nick.");
		EntityTransaction tx = em.getTransaction();
		u.modificarDatos(newData);
		
		tx.begin();
		em.persist(u);
		tx.commit();
	}
	public void modificarImagenUser(String nick, String newURL) throws Exception{	
		Usuario u = usuarios.get(nick);
		if (u == null)
			throw new Exception("No se encontro dicho nick.");
		EntityTransaction tx = em.getTransaction();
		u.setUrlImage(newURL);
		tx.begin();
		em.persist(u);
		tx.commit();
		
	}
	
	public void guardarImagenSalida(String dep, String act, String nom, String newURL){	
		Departamento d = departamentos.get(dep);

		EntityTransaction tx = em.getTransaction();
		d.guardarImagenSalida(act,nom,newURL);
		tx.begin();
		em.persist(d);
		tx.commit();
		
	}
	
	public void guardarImagenAct(String dep, String act, String newURL){	
		Departamento d = departamentos.get(dep);
		
		EntityTransaction tx = em.getTransaction();
		d.guardarImagenAct(act,newURL);
		tx.begin();
		em.persist(d);
		tx.commit();
		
	}
	public String getImagenAct(String CoD, String act){	
		Departamento d = departamentos.get(CoD);
		if (d == null) {
			Categoria c = categorias.get(CoD);
			return c.getImagenAct(act);
		}
		return d.getImagenAct(act);
	}
	/*
	 * Precondicion: Existe el usuario
	 * 
	 * */
	public String getURLImageUser(String nick) {
		return this.usuarios.get(nick).getUrlImage();
	}
	
		
	public List<String> verActividadesCP(String nombrePaq){
		Paquete p = paquetes.get(nombrePaq);
		return p.listarActividades();
	}	
	
//	Lista paquetes que no han sido comprados y tienen al menos una actividad
	public List<String> listarPaquetesExistentesNoCompradosConActividad(String nombreTur) {
		List<String> ret = new ArrayList<String>();
		paquetes.forEach((t, u) -> {
			if (!u.paqueteCompradoPorTur(nombreTur) && u.tieneActividades() && u.hayLugar()) 
				ret.add(t);
		});	
		return ret;
	}
	
	public float obtenerCostoTotal(String nombrePaq) {
		float res = 0;
		Paquete p = paquetes.get(nombrePaq);
		res = p.obtenerCostoTotal();
		
		return res;
	}
	

	/*
	 * Precondicion: Existe el usuario
	 * 
	 * */
	public DtUsuario getDtUserWeb(String nick) {
		Usuario u = this.usuarios.get(nick);
		DtUsuario ret = null;
		if (u.esProveedor()) {
			Proveedor p = (Proveedor)u;
			ret = new DtUsuario(nick, u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(),p.getDesc(), p.getLink(), null, p.listarActividadesConfirmadas(), p.listarSalidasConfirmadas());
		} else {
			Turista t = (Turista)u;
			ret = new DtUsuario(nick, u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(), null, null, t.getNacionalidad(), null, t.listarSalidas());
		}
		
		return ret;
	}
	public DtUsuario getDtUserWebPropias(String nick) {
		Usuario u = this.usuarios.get(nick);
		DtUsuario ret = null;
		if (u.esProveedor()) {
			Proveedor p = (Proveedor)u;
			ret = new DtUsuario(nick, u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(),p.getDesc(), p.getLink(), null, p.listarActividades(), p.listarSalidas());
		} else {
			Turista t = (Turista)u;
			ret = new DtUsuario(nick, u.getName(), u.getApellido(), u.getEmail(), u.getFechaNac(), null, null, t.getNacionalidad(), null, t.listarSalidas());
		}
		
		return ret;
	}
	public DtSalidaTuristica getDtSalida(String nombreSal) {
		ActividadTuristica act;
		for (Map.Entry<String, Departamento> d: departamentos.entrySet()) {
			act = d.getValue().contieneActividadSegunSalida(nombreSal);
			if (act != null) {
				return act.datosSalTur(nombreSal);
			}
		}
		return null;
	}
	public void altaInscripcionWebDep(String nombreDep, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas) throws Exception{
		Departamento dep = this.departamentos.get(nombreDep);
		ActividadTuristica act = dep.buscarAct2(nombreAct);
		SalidaTuristica sal = act.getSalida(nombreSalida);
		if (sal.getCuposDisponibles() < cantTuristas) {
			throw new Exception("Cantidad de inscriptos superada.");
		}
		Turista tur = (Turista) usuarios.get(nickTurista);
		
		TuristaSalida ins = tur.crearInscripcion(sal, cantTuristas);
		ins.setCosto(cantTuristas*act.getCosto());
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(ins);
		em.merge(tur);
		tx.commit();
	}
	public void altaInscripcionWebCat(String nombreCat, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas) throws Exception{
		Categoria cat = this.categorias.get(nombreCat);
		ActividadTuristica act = cat.seleccActividad(nombreAct);
		SalidaTuristica sal = act.getSalida(nombreSalida);
		if (sal.getCuposDisponibles() < cantTuristas) {
			throw new Exception("Cantidad de inscriptos superada.");
		}
		sal.setCuposDisponibles(sal.getCuposDisponibles() - cantTuristas);
		Turista tur = (Turista) usuarios.get(nickTurista);
		
		TuristaSalida ins = tur.crearInscripcion(sal, cantTuristas);
		ins.setCosto(cantTuristas*act.getCosto());
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(ins);
		em.merge(tur);
		tx.commit();
	}
	
	public void altaInscripcionWebDepPaq(String nombreDep, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas, String nombrePaq) throws Exception{
		Departamento dep = this.departamentos.get(nombreDep);
		ActividadTuristica act = dep.buscarAct2(nombreAct);
		SalidaTuristica sal = act.getSalida(nombreSalida);
		Turista tur = (Turista) usuarios.get(nickTurista);
		if (sal.getCuposDisponibles() < cantTuristas) {
			throw new Exception("Cantidad de inscriptos superada.");
		}
		if (!tur.quedanTicketsPaq(nombrePaq, cantTuristas)) {
			throw new Exception("No quedan tickets disponibles en el paquete o el paquete expiro.");
		}

		TuristaSalida ins = tur.crearInscripcion(sal, cantTuristas);
		ins.setCosto(0f);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(ins);
		em.merge(tur);
		tx.commit();
	}
	
	public void altaInscripcionWebCatPaq(String nombreCat, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas, String nombrePaq) throws Exception{
		Categoria cat = this.categorias.get(nombreCat);
		ActividadTuristica act = cat.seleccActividad(nombreAct);
		SalidaTuristica sal = act.getSalida(nombreSalida);
		Turista tur = (Turista) usuarios.get(nickTurista);
		if (sal.getCuposDisponibles() < cantTuristas) {
			throw new Exception("Cantidad de inscriptos superada.");
		}
		if (!tur.quedanTicketsPaq(nombrePaq, cantTuristas)) {
			throw new Exception("No quedan tickets disponibles en el paquete o el paquete expiro.");
		}
		
		
		TuristaSalida ins = tur.crearInscripcion(sal, cantTuristas);
		ins.setCosto(0f);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(ins);
		em.merge(tur);
		tx.commit();
	}
	
	public String getImagenSal(String CoD, String act, String sal) {	
		Departamento d = departamentos.get(CoD);
		if (d == null) {
			Categoria c = categorias.get(CoD);
			return c.getImagenSal(act, sal);
		}
		return d.getImagenSal(act, sal);
	}
	
	public String getImagenSal(String nombreSal) {
		ActividadTuristica act;
		for (Map.Entry<String, Departamento> d: departamentos.entrySet()) {
			act = d.getValue().contieneActividadSegunSalida(nombreSal);
			if (act != null) {
				return act.getSalida(nombreSal).getImagen();
			}
		}
		return null;
	}
	
 	public List<String> actividadURL(String nombrePaq, List<String> acts) {
		Paquete p = paquetes.get(nombrePaq);
		System.out.println("Paquetes:" + p);
		return p.actividadURL(acts);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarActividadesConfirmadasDeDepartamento(String nombreDep) {	
		List<String> nombreActs = em.createNativeQuery("SELECT nombre FROM actividades WHERE nombre_dep = :nombre_dep AND estado = 'CONFIRMADA'")
				.setParameter("nombre_dep", nombreDep)
				.getResultList();
		return nombreActs;
	}	
	@SuppressWarnings("unchecked")
	public List<String> listarActividadesConfirmadasDeCategoria(String nombreCat) {	
		List<String> nombreActs = em.createNativeQuery("SELECT actividades.nombre FROM actividades JOIN  actividad_categoria ON actividad_categoria.at_id = actividades.nombre WHERE actividades.estado = 'CONFIRMADA' AND actividad_categoria.cat_id = :catName GROUP BY actividades.nombre")
				.setParameter("catName", nombreCat)
				.getResultList();
		return nombreActs;
	}
	@SuppressWarnings("unchecked")
	public List<String> listarSalidasDeActividad(String nombreAct) {
		List<String> nombreSal = em.createNativeQuery("SELECT salidaturistica.nombre FROM salidaturistica WHERE nombreact = :actName")
				.setParameter("actName", nombreAct)
				.getResultList();
		return nombreSal;
	}
	@SuppressWarnings("unchecked")
	public List<String> listarCategoriasDeActividad(String nombreAct) {
		List<String> nombreCats = em.createNativeQuery("SELECT cat_id FROM actividad_categoria WHERE at_id = :actName")
				.setParameter("actName", nombreAct)
				.getResultList();
		return nombreCats;
		
	}
	@SuppressWarnings("unchecked")
	public List<String> listarPaquetesDeActividad(String nombreAct) {
		List<String> nombrePaq = em.createNativeQuery("SELECT paq_id FROM actividad_paquete WHERE at_id = :actName")
				.setParameter("actName", nombreAct)
				.getResultList();
		return nombrePaq;
		
	}
	@SuppressWarnings("unchecked")
	public String getImagenDeActividad(String nombreAct) {
		List<String> imgUrl = em.createNativeQuery("SELECT urlimagen FROM actividades WHERE nombre = :actName")
				.setParameter("actName", nombreAct)
				.getResultList();
		if (imgUrl.isEmpty())
			return null;
		return imgUrl.get(0);
		
	}
}