package logica;

import java.time.LocalDate;

import datatype.DtActTur;
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
	/**
	 * @param prov Nick de usuario tipo proveedor
	 * @param dep Nombre de departamento
	 * @param data Datatype con info para crear la actividad
	 * @throws Exception 
	 */
	public abstract void altaActividad(String prov, String dep, DtActTur data) throws Exception;
	public abstract void altaUsuario(boolean provotour, DtUsuario data) throws Exception;
	public abstract void altaDepartamento(DtDepartamento data);
	public abstract List<String> listarDepartamentos();
	public abstract DtUsuario obtenerDataUsuario(String nick);
	
//	Modificar Datos de Usuario
	public abstract List<String> listarUsuarios();
	public abstract void selecUsuario(String nick);
	public abstract void modificarDatos(DtModUser datosNuevos);

//	Crear Paquete de Actividades Turisticas
	public abstract void altaPaquete(DtPaquete dataPaquete) throws Exception;
	
//	Agregar Actividad Turistica a Paquete
	public abstract List<String> listarPaquetesExistentes();
	public abstract void seleccionarPaquete(String nombre) throws Exception;
	public abstract List<String> listarActividadesDepartamento();
	public abstract void seleccionarActividadParaPaquete(String nombreAct) throws Exception;
	
	
// Consulta de Paquete de Actividades Turisticas
	public abstract DtPaquete imprimirInfoPaquete(String nombre) throws Exception;
	
//	Consulta de Actividad Turistica

	public abstract List<String> seleccionarDepartamento(String nombreDep) throws Exception;
	public abstract void seleccionarActividad(String nombreAct);
	
	public abstract DtActTur seleccionarActividadDt(String nombreAct);
	public abstract List<String> verProveedorCA(String nombreAct) throws Exception;
//	Alta de Salida Turistica	
	public abstract void confirmarAltaSalTur(String nombreAct, DtSalidaTuristica datos) throws Exception;
//	Consulta de Salida Turistica
	public abstract List<String> seleccionarActividadST(String nombreAct) throws Exception;
	public abstract DtSalidaTuristica seleccionarSalida(String nombreSal);
	
	public abstract List<String> verActividadesCU(String nickProv) throws Exception;
	public abstract  List<String> verSalidasCU(String nombreAct) throws Exception;
	
	public abstract List<String> listarTuris();
	public abstract List<String> salidasVigentesDeAct(String nombreAct);
	public abstract void altaInscripcion(String nickTuris, String nombreSal, int cant ) throws Exception;
	public abstract List<String> verInscripcionesCU(String nickTur) throws Exception;

}
