package logica;

import datatype.DtActTur;
import datatype.DtActTurEst;
import datatype.DtDepartamento;
import datatype.DtUsuario;
import java.util.List;

import datatype.DtModUser;

import datatype.DtPaquete;

import datatype.DtSalidaTuristica;

public interface ISistema {

	public abstract List<String> listarProv();
	public abstract void obtenerDatosDepartamento(String nombre) throws Exception;
	public abstract void obtenerDatosDepartamento(Departamento d) throws Exception;
	public abstract void guardarImagenSalida(String dep, String act, String nom, String newURL);
	public abstract void guardarImagenAct(String dep, String act, String newURL);
	public abstract String getImagenAct(String dep, String act);
	public abstract String guardarDepartamentoSegunAct(String act);
	public abstract List<String> listarPaquetesCompradosTur(String tur,String sal);
	public abstract void altaInscripcionWebCatPaq(String nombreCat, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas, String nombrePaq) throws Exception;
	public abstract void altaInscripcionWebDepPaq(String nombreDep, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas, String nombrePaq) throws Exception;
	
//	Alta Categoria
	public abstract void altaCategoria(String nombre) throws Exception;
	public abstract List<String> listarCategorias();
	public abstract List<String> verCategoriasCA(String nombreAct) throws Exception;
	public abstract List<String> verCategoriasCAC(String nombreAct) throws Exception;
	public abstract List<String> verCategoriasCP(String nombrePaq);
	public abstract List<String> verActividadesCat(String cat);
	/**
	 * @param prov Nick de usuario tipo proveedor
	 * @param dep Nombre de departamento
	 * @param data Datatype con info para crear la actividad
	 * @throws Exception 
	 */
	public abstract void altaActividad(String prov, String dep, DtActTur data, List<String> cates) throws Exception;
	public abstract void altaUsuario(boolean provotour, DtUsuario data) throws Exception;
	public abstract void altaUsuario(boolean provotour, DtUsuario data, String password) throws Exception;
	public abstract void altaDepartamento(DtDepartamento data);
	public abstract List<String> listarDepartamentos();
	public abstract DtUsuario obtenerDataUsuario(String nick);
	
//	Modificar Datos de Usuario
	public abstract List<String> listarUsuarios();
	public abstract void selecUsuario(String nick) throws Exception;
	public abstract void modificarDatos(DtModUser datosNuevos);

//	Crear Paquete de Actividades Turisticas
	public abstract void altaPaquete(DtPaquete dataPaquete) throws Exception;
	
//	Agregar Actividad Turistica a Paquete
	public abstract List<String> listarPaquetesExistentes();
	public abstract List<String> listarPaquetesExistentesNoComprados(String nombreTur);
	
	public abstract void seleccionarPaquete(String nombre) throws Exception;
	public abstract List<String> listarActividadesDepartamento();
	public abstract List<String> listarActividadesDepartamentoConfirmadas();
	public abstract void seleccionarActividadParaPaquete(String nombreAct) throws Exception;
	
	
// Consulta de Paquete de Actividades Turisticas
	public abstract DtPaquete imprimirInfoPaquete(String nombre) throws Exception;
	
//	Consulta de Actividad Turistica

	public abstract List<String> seleccionarDepartamento(String nombreDep) throws Exception;
	public abstract void seleccionarDepartamentoSegunActividad(String nombreAct) throws Exception;
	public abstract void seleccionarActividad(String nombreAct);
	
	public abstract DtActTur seleccionarActividadDt(String nombreAct);
	public abstract List<String> verProveedorCA(String nombreAct) throws Exception;
	public abstract List<String> verProveedorCAC(String nombreAct) throws Exception;
	public abstract List<String> seleccionarActividadSTC(String nombreAct) throws Exception;
//	Alta de Salida Turistica	
	public abstract void confirmarAltaSalTur(String nombreAct, DtSalidaTuristica datos) throws Exception;
//	Consulta de Salida Turistica
	public abstract List<String> seleccionarActividadST(String nombreAct) throws Exception;
	public abstract void guardarDepartamentoYActividadSegunSalida(String nombreSal);
	public abstract DtSalidaTuristica seleccionarSalida(String nombreSal);
	
	public abstract List<String> verActividadesCU(String nickProv) throws Exception;
	public abstract  List<String> verSalidasCU(String nombreAct) throws Exception;
	
	public abstract List<String> listarTuris();
	public abstract List<String> salidasVigentesDeAct(String nombreAct);
	public abstract void altaInscripcion(String nickTuris, String nombreSal, int cant ) throws Exception;
	public abstract List<String> verInscripcionesCU(String nickTur) throws Exception;
	public abstract void turistaCompraPaquete(String nombreTur, String nombrePaq) throws Exception;
	
//	Aceptar/Rechazar Actividad Turistica
	public abstract  List<String> listarActAgregadas();
	
	public abstract void imprimirLista()throws Exception;
		
	public abstract void altaActividadEstado(String prov, String dep, DtActTur data, String est) throws Exception;
	
	public abstract void acepRecActividad(String nombreAct, String es);
	
	public abstract DtActTurEst seleccionarActividadDtEst(String nombreAct);
	
	public abstract List<String> seleccionarDepartamentoConf(String nombreDep) throws Exception;
	
	public abstract List<String> listarActividadesDepartamentoConf();

	public abstract DtSalidaTuristica seleccionarSalidaA(String nombreSal, String nombreAct, String nombreDep);
	
//	Agregadas para la segunda entrega del laboratorio
	public abstract void cerrarConexionBD();
	public abstract boolean checkUserNickExist(String nick);
	public abstract DtUsuario obtenerDataUsuarioConEmail(String email);
	public abstract boolean checkUserEmailExist(String email);
	public abstract boolean checkValidUser(String nick, String password);
	public abstract void modificarDatosWeb(String nick, DtModUser newData) throws Exception;
	public abstract void modificarImagenUser(String nick, String newURL) throws Exception;
	public abstract String getURLImageUser(String nick);

	public abstract List<String> verActividadesCP(String nombrePaq);
	public abstract List<String> listarPaquetesExistentesNoCompradosConActividad(String nombreTur);
	public abstract float obtenerCostoTotal(String nombrePaq);

	public abstract DtUsuario getDtUserWeb(String nick);
	public abstract DtUsuario getDtUserWebPropias(String nick);
	public abstract DtSalidaTuristica getDtSalida(String nombreSal);
	public abstract void altaInscripcionWebDep(String nombreDep, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas) throws Exception;
	public abstract void altaInscripcionWebCat(String nombreCat, String nombreAct, String nombreSalida, String nickTurista, int cantTuristas) throws Exception;
	public abstract String getImagenSal(String CoD, String act, String sal);
	public abstract String getImagenSal(String nombreSal);
	public abstract List<String> actividadURL(String nombrePaq, List<String> acts);
	public abstract List<String> listarActividadesConfirmadasDeDepartamento(String nombreDep);
	public abstract List<String> listarActividadesConfirmadasDeCategoria(String nombreCat);
	public abstract List<String> listarSalidasDeActividad(String nombreAct);
	public abstract List<String> listarCategoriasDeActividad(String nombreAct);
	public abstract List<String> listarPaquetesDeActividad(String nombreAct);
	public abstract String getImagenDeActividad(String nombreAct);
}
