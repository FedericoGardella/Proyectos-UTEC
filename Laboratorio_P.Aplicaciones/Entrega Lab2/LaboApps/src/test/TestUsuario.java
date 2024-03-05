package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import datatype.DtModUser;
import datatype.DtSalidaTuristica;
import datatype.DtUsuario;
import logica.ActividadTuristica;
import logica.Categoria;
import logica.Departamento;
import logica.Paquete;
import logica.Proveedor;
import logica.SalidaTuristica;
import logica.Turista;
import logica.estadosActividad;	

public class TestUsuario {
	
	private Proveedor createProveedor(){
		Proveedor p = new Proveedor("nick", "name", "ape", "email", LocalDate.of(2003, 9, 3), "desc", "link");
		return p;
	}
	private Turista createTurista() {
		Turista t = new Turista("nick", "name", "ape", "email", LocalDate.of(2003, 9, 3), "nacion");
		return t;
	}
	@Test
	public void testCreateVoidUsers() {
		Turista t = new Turista();
		t.toString();
		t.linkearAct(null);
		Proveedor p = new Proveedor();
		p.toString();
		assertFalse(t.esProveedor());
		assertTrue(p.esProveedor());
	}
	@Test
	public void testGetNacionalidad() {
		assertEquals("nacion", createTurista().getNacionalidad());
	}
	@Test
	public void testSetNacionalidad() {
		Turista t = createTurista();
		t.setNacionalidad("nacion2");
		assertEquals("nacion2", t.getNacionalidad());
	}
	@Test
	public void testModificarDatos() {
		Turista t = createTurista();
		t.modificarDatos(new DtModUser("Tiago", "Silva", LocalDate.of(2004, 9, 15), "AAAA"));
		assertEquals("AAAA", t.getNacionalidad());
		Proveedor p = createProveedor();
		p.modificarDatos(new DtModUser("Tiago", "Silva", LocalDate.of(2004, 9, 15), "nuevadesc2", "link"));
		assertEquals("nuevadesc2", p.getDesc());
	}
	@Test
	public void testGetData() {
		assertEquals(new DtUsuario("nick", "name", "ape", "email", LocalDate.of(2003, 9, 3), null, null, "nacion"), createTurista().getData());
		assertEquals(new DtUsuario("nick", "name", "ape", "email", LocalDate.of(2003, 9, 3), "desc", "link", null), createProveedor().getData());
	}
	@Test
	public void testCrearIns(){
		Turista t = createTurista();
		SalidaTuristica s = new SalidaTuristica("nombre", LocalDate.of(2023, 11, 25), LocalTime.of(20, 20), "lugarSalida", 10, LocalDate.now());
		try {
			assertNotNull(t.crearInscripcion(s, 1));
			List<String> esperado = new ArrayList<String>();
			esperado.add("nombre");
			assertEquals(esperado, t.listarSalidas());
		}catch(Exception e) {}
		try {
			assertNotNull(t.crearInscripcion(s, 4));
			fail("No deberias estar aqui");
		}catch(Exception e) {}
	}
	@Test
	public void testComprarPaquete() {
		Turista t = createTurista();	
		t.comprarPaquete(new Paquete("nomPaq", "desc", 4, 10f, LocalDate.now(), 4,7));
		assertTrue(t.contienePaquete("nomPaq"));
	}
	@Test
	public void testGettersSettersProveedor() {
		Proveedor p = createProveedor();
		assertEquals("desc", p.getDesc());
		assertEquals("link", p.getLink());
		
		p.setDesc("nuevaDesc");
		p.setLink("nuevoLink");
		assertEquals("nuevaDesc", p.getDesc());
		assertEquals("nuevoLink", p.getLink());
	}
	@Test
	public void testLinkearAct() {
    	ActividadTuristica a = new ActividadTuristica("ActTest", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), new HashMap<String, Categoria>());
    	Proveedor p = createProveedor();
    	p.linkearAct(a);
    	List<String> esperado = new ArrayList<String>();
    	esperado.add("ActTest");
    	assertEquals(esperado, p.listarActividades());
    	
    	List<String> esperado2 = new ArrayList<String>();
    	esperado2.add("Act");
    	ActividadTuristica a2 = new ActividadTuristica("Act", "Desc", 5, 50f, "CiudadTest2", LocalDate.of(2023, 9, 10), new Departamento("DepaTest2"), estadosActividad.CONFIRMADA);
    	p.linkearAct(a2);
    	assertEquals(esperado2, p.listarActividadesConfirmadas());
	}
	@Test
	public void testListarSalidas() {
		Proveedor p = createProveedor();
    	ActividadTuristica a = new ActividadTuristica("ActTest", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), new HashMap<String, Categoria>());
    	ActividadTuristica act = new ActividadTuristica("Act", "Desc", 5, 50f, "CiudadTest2", LocalDate.of(2023, 9, 10), new Departamento("DepaTest2"), estadosActividad.CONFIRMADA);
    	DtSalidaTuristica dt = new DtSalidaTuristica("SalidaTest1", LocalDate.of(2023, 10, 27), LocalTime.of(10, 10), "Micasa", 3, LocalDate.now());
    	DtSalidaTuristica dt2 = new DtSalidaTuristica("SalidaTest2", LocalDate.of(2023, 10, 27), LocalTime.of(10, 10), "Micasa", 3, LocalDate.now());
		try {
			a.crearSalida(dt);
			act.crearSalida(dt2);
		} catch (Exception e) {}
		
		p.linkearAct(a);
		p.linkearAct(act);
		List<String> esperado = new ArrayList<String>();
		esperado.add("SalidaTest2");
		esperado.add("SalidaTest1");
		List<String> esperado2 = new ArrayList<String>();
		esperado2.add("SalidaTest2");
		
		assertEquals(esperado, p.listarSalidas());
    	assertEquals(esperado2, p.listarSalidasConfirmadas());
	}
	@Test
	public void testSeleccActividad() {
		ActividadTuristica a = new ActividadTuristica("ActTest", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), new HashMap<String, Categoria>());
    	Proveedor p = createProveedor();
    	p.linkearAct(a);
    	try {
    		assertEquals(a, p.seleccActividad("ActTest"));
    	}catch (Exception e) {}
    	try {
    		assertEquals(a, p.seleccActividad("noexisto"));
    		fail("NodeberiamosVeresto");
    	}catch (Exception e) {}
	}
	@Test
	public void testGetterSetterUsuario() {
		Turista t = createTurista();
		assertEquals(null, t.getUrlImage());
		t.setUrlImage("jaja.png");
		assertEquals("jaja.png", t.getUrlImage());
	}
	@Test
	public void	listarPaquetesCompradosConSal() {
		Turista t = createTurista();
		Paquete paq = new Paquete("nomTest", "descTest", 10, 15, LocalDate.of(2023, 10, 20), 7, 5);
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", 
				LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		/*
		 * ActividadTuristica act2 = new ActividadTuristica("Act2", "Desc2Test", 10,
		 * 100f, "CiudadTest", LocalDate.of(2024, 12, 20), new
		 * Departamento("Depa2Test"), cats);
		 */
		DtSalidaTuristica sal = new DtSalidaTuristica("nombreSal", LocalDate.of(2023, 11, 20), 
				LocalTime.of(20,30), "lugarSal", 5, LocalDate.of(2023, 10, 26));
		try {
			act1.crearSalida(sal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			paq.agregarActividad(act1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		t.comprarPaquete(new Paquete("nomPaq", "desc", 4, 10f, LocalDate.now(), 4,7));
		List<String> paquetesConSal = t.listarPaquetesCompradosConSal("nop");
        assertEquals(0, paquetesConSal.size());
	}
	@Test
	public void testQuedanTicketsPaq() {
		Turista tur = createTurista();
		Paquete paq = new Paquete("nomTest", "descTest", 10, 15, LocalDate.of(2023, 10, 20), 7, 5);
		int cantTuristas = 7;
		
		tur.comprarPaquete(paq);
		tur.quedanTicketsPaq("nomTest", cantTuristas);
		
		
		Paquete paq2 = new Paquete("nomTest2", "descTest", 10, 15, LocalDate.of(2021, 10, 20), 7, 9);
		
		tur.comprarPaquete(paq2);
		tur.quedanTicketsPaq("nomTest2", cantTuristas);
		
		
		
	}
	
	
	
	
}
