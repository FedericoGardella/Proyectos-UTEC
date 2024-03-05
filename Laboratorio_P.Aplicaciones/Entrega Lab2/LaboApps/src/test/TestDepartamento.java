package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import datatype.DtActTur;
import datatype.DtActTurEst;
import datatype.DtSalidaTuristica;
import logica.Categoria;
import logica.Departamento;
import logica.SalidaTuristica;
import logica.estadosActividad;

class TestDepartamento {
		
	private Departamento  createDep() throws Exception{
		Departamento d = new Departamento("DepTest1");
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		d.crearAct(new DtActTur("Act1Test1", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), categos);
		return d;
	}
	@Test
	public void testCreateAllDep() {
		Departamento d1 = new Departamento();
		Departamento d2 = new Departamento("Dep2test", "Desc2Test", "url");
	}
	@Test
	public void testGetNombre() throws Exception{
		assertEquals("DepTest1", createDep().getNombre());
	}
	@Test
	public void testCrearAct() throws Exception{
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		assertEquals("Act1Test",  createDep().crearAct(new DtActTur("Act1Test", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), categos).getNombre());
	}
	@Test
	public void testImprimirActividades() throws Exception{
		Departamento d = createDep();
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		d.crearAct(new DtActTur("Act1Test", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), categos);
		d.imprimirActividades();
	}
	@Test
	public void testFuncionesVacias() throws Exception{
		createDep().selecActividades("a");
		createDep().selActIngDat("a", new DtSalidaTuristica());
		createDep().selecActividadesVigentes("a");
	}
	@Test
	public void testListarActividadesPaquete() throws Exception{
		Departamento d = createDep();
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		d.crearAct(new DtActTur("Act1Test", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), categos);
		List<String> esperado = new ArrayList<String>();
		esperado.add("Act1Test");
		esperado.add("Act1Test1");
		assertEquals(esperado, d.listarActividadesPaquete("paquete"));
	}
	@Test
	public void testListarActividadesPaqueteConfirmada() throws Exception{
		Departamento d = createDep();
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		d.crearAct(new DtActTur("Act1Test", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), categos);
		d.crearActEstado(new DtActTur("Act1Test2", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), estadosActividad.CONFIRMADA);
		
		List<String> esperado = new ArrayList<String>();
		esperado.add("Act1Test2");
		assertEquals(esperado, d.listarActividadesPaqueteConfirmada("paquete"));
	}
	@Test
	public void testPrintActividades () throws Exception{
		Departamento d = createDep();
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		d.crearAct(new DtActTur("Act1Test", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), categos);
		
		List<String> esperado = new ArrayList<String>();
		esperado.add("Act1Test");
		esperado.add("Act1Test1");
		assertEquals(esperado, d.printActividades());
	}
	@Test
	public void testSelecActividad() throws Exception{
		Map<String, Categoria> categos = new HashMap<String, Categoria>();
		Departamento d = createDep();
		d.crearAct(new DtActTur("Act1Test", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), categos);
		d.selecActividad("Act1Test");
	}
	@Test
	public void testSelecActividadDt() throws Exception{
		assertEquals("Act1Test1", createDep().selecActividadDt("Act1Test1").getNombre());
	}
	@Test
	public void testListProveedores(){
		try {
			createDep().listProveedores("actividadquenoexiste");
			fail("No deberia llegar");
		}catch (Exception e) {}
		try {
			createDep().listProveedores("Act1Test1");
		}catch (Exception e) {}
		
	}
	@Test
	public void testConfirmarAltaSalTur() throws Exception{
		Departamento d = createDep();
		SalidaTuristica s = d.confirmarAltaSalTur("Act1Test1", new DtSalidaTuristica("Salida1Test", LocalDate.of(2023, 11, 25), LocalTime.of(20, 20), "Miciudad", 4, LocalDate.now()));
		assertEquals("Salida1Test", s.getNombre());
	}
	@Test
	public void testSeleccActividad() {
		try {
			createDep().seleccActividad("actividadquenoexiste");
			fail("No deberia llegar");
		}catch (Exception e) {}
		try {
			assertEquals("Act1Test1", createDep().seleccActividad("Act1Test1").getNombre());
		}catch (Exception e) {}
	}
	@Test
	public void testListSalidas() {
		try {
			createDep().listSalidas("actividadquenoexiste");
			fail("No deberia llegar");
		}catch (Exception e) {}
		try {
			assertEquals(new ArrayList<String>(), createDep().listSalidas("Act1Test1"));
		}catch (Exception e) {}
		
	}
	@Test
	public void testContieneActividad() throws Exception{
		assertTrue(createDep().contieneActividad("Act1Test1"));
		assertFalse(createDep().contieneActividad("Noexistouwu"));
	}
	@Test
	public void testListarActividadesAg() throws Exception{
		Departamento d = createDep();
		List<String> esperado = new ArrayList<String>();
		d.crearActEstado(new DtActTur("Act1Test2", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), estadosActividad.CONFIRMADA);
		esperado.add("Act1Test1");	
		assertEquals(esperado, d.listarActividadesAg());
	}
	@Test
	public void testBuscarAct() throws Exception{
		assertEquals("Act1Test1", createDep().buscarAct("Act1Test1", estadosActividad.CONFIRMADA).getNombre());
		assertEquals("Act1Test1", createDep().buscarAct2("Act1Test1").getNombre());		
	}
	@Test
	public void testSelecActividadDtEst() throws Exception{
		assertEquals(new DtActTurEst("Act1Test1", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25), estadosActividad.AGREGADA), createDep().selecActividadDtEst("Act1Test1"));
		assertNotEquals(new DtActTurEst("Act1Test2", "des2", 21, 13f, "a", LocalDate.of(2323, 1, 15), estadosActividad.CONFIRMADA), createDep().selecActividadDtEst("Act1Test1"));
	}
	@Test
	public void testListarActividadesConfirmadas() throws Exception{
		Departamento d = createDep();
		List<String> esperado = new ArrayList<String>();
		d.crearActEstado(new DtActTur("Act1Test2", "desc", 1, 1f, "miciudad", LocalDate.of(2023, 11, 25)), estadosActividad.CONFIRMADA);
		esperado.add("Act1Test2");	
		assertEquals(esperado, d.listarActividadesConf());
	}
	@Test
	public void testEncontrarAct() throws Exception{
		assertNotNull(createDep().encontrarAct("Act1Test1"));
		assertNull(createDep().encontrarAct("Noexisto"));
	}
	@Test
	public void testListarActividadesPaqueteConf() throws Exception{
		assertEquals(new ArrayList<String>(), createDep().listarActividadesPaqueteConf("Paq1"));
	}
	@Test
	public void testContieneActividadSegunSalida() throws Exception{
		Departamento d = createDep();
		d.confirmarAltaSalTur("Act1Test1", new DtSalidaTuristica("Salida1Test", LocalDate.of(2023, 11, 25), LocalTime.of(20, 20), "Miciudad", 4, LocalDate.now()));
		assertEquals("Act1Test1", d.contieneActividadSegunSalida("Salida1Test").getNombre());
		assertNull(d.contieneActividadSegunSalida("wghadfghdfagh"));
	}
	@Test
	public void testGuardarImagenSalida() throws Exception{
		Departamento d = createDep();
		d.confirmarAltaSalTur("Act1Test1", new DtSalidaTuristica("Salida1Test", LocalDate.of(2023, 11, 25), LocalTime.of(20, 20), "Miciudad", 4, LocalDate.now()));
		d.guardarImagenSalida("Act1Test1", "Salida1Test", "img.png");
		assertEquals("img.png", d.getImagenSal("Act1Test1", "Salida1Test"));
	}
	@Test
	public void testGuardarImagenAct() throws Exception{
		Departamento d = createDep();
		d.guardarImagenAct("Act1Test1", "img2.png");
		assertEquals("img2.png", d.getImagenAct("Act1Test1"));
	}
}
