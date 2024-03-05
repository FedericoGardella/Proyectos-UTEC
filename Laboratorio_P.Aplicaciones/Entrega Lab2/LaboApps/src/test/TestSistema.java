package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import datatype.DtActTur;
import datatype.DtActTurEst;
import datatype.DtSalidaTuristica;
import logica.Categoria;
import logica.Fabrica;
import logica.ISistema;
import logica.Sistema;
import logica.Departamento;
import logica.Usuario;
import logica.estadosActividad;
import logica.Paquete;
import logica.Proveedor;
import logica.SalidaTuristica;
import static org.junit.Assert.*;
import org.junit.Before;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;



import datatype.DtModUser;
import datatype.DtPaquete;
import datatype.DtUsuario;
import datatype.DtDepartamento;



public class TestSistema {

	private ISistema createSistema(){
		ISistema sis = Fabrica.getSistema();
		return sis;
	}
	
	@Test
	public void testListarDepartamentos(){
		ISistema sis = createSistema();
		Departamento d = new Departamento("Depa1");
		try {
			sis.obtenerDatosDepartamento(d);
		} catch (Exception e) {};
		List<String> lista = new ArrayList<String>();
		lista.add("Artigas");
		lista.add(d.getNombre());
		assertEquals(lista, sis.listarDepartamentos());
	}
	
	@Test
	public void testListarCategorias() {
		ISistema sis = createSistema();
		try {
			sis.altaCategoria("Cate");
		} catch (Exception e) {}
		Categoria cat1 = new Categoria("Cate");
		List<String> lista = new ArrayList<String>();
		lista.add("Divertido");
		lista.add(cat1.getNombre());
		lista.add("Cat");
		assertEquals(lista, sis.listarCategorias());
	}
	
	@Test
	public void testListarProveedor() {
		ISistema sis = createSistema();
		try {
			sis.altaUsuario(true, new DtUsuario("t", "Tiago", "Silva", "tiav", LocalDate.of(2003, 9, 3), "Yo", "y.com", null), "passt123");
		} catch (Exception e) {}
		List<String> lista = new ArrayList<String>();
		lista.add("t");
		lista.add("tuu");
		lista.add("tiagop");
		assertEquals(lista, sis.listarProv());
		try {
			sis.verActividadesCU("t");
		} catch (Exception e) {}
	    assertThrows(Exception.class, () -> {
	    	sis.verActividadesCU("lolete");
	    });
	    try {
			sis.modificarImagenUser("t", "newURL");
		} catch (Exception e) {}
	    assertThrows(Exception.class, () -> {
	    	sis.modificarImagenUser("tpp", "newURL");
	    });
	}
	
	@Test
	public void testListarTur() {
		ISistema sis = createSistema();
		try {
			sis.altaUsuario(false, new DtUsuario("ti", "Tiago", "Silva", "tiavoo",null, null, null, null));
		} catch (Exception e) {}
		List<String> lista = new ArrayList<String>();
		lista.add("ti");
		lista.add("tiagot");
		lista.add("titi");
		lista.add("turru");
		assertEquals(lista, sis.listarTuris());
		sis.obtenerDataUsuarioConEmail("tiavoo");
	}
	
	@Test
	public void testObtenerDatosDepartamento(){
		ISistema sis = createSistema();
		try {
			sis.obtenerDatosDepartamento("hola");
			
		} catch (Exception e) {};
		List<String> lista = new ArrayList<String>();
		lista.add("Artigas");
		lista.add("Depa1");
		lista.add("hola");
		assertEquals(lista, sis.listarDepartamentos());
		try {
			sis.seleccionarDepartamento("Artigas");
		} catch (Exception e) {}
	    assertThrows(Exception.class, () -> {
	    	sis.seleccionarDepartamento("lololo");
	    });
	}
	
	@Test
	public void testObtenerDatosDepartamento2(){
		ISistema sis = createSistema();
		try {
			sis.obtenerDatosDepartamento("hola");
			
		} catch (Exception e) {};
	}
	
	@Test
	public void testObtenerDatosDepartamento3(){
		ISistema sis = createSistema();
		Departamento d = new Departamento("Depa1");
		try {
			sis.obtenerDatosDepartamento(d);
			
		} catch (Exception e) {};
	}
	
	@Test
	public void testListarUsuarios() {
		ISistema sis = createSistema();
		List<String> lista = new ArrayList<String>();
		lista.add("gfvdf");
		lista.add("t");
		lista.add("tuu");
		lista.add("ti");
		lista.add("tiagot");
		lista.add("tiagop");
		lista.add("titi");
		lista.add("turru");
		assertEquals(lista, sis.listarUsuarios());
	}
	
	@Test
	public void testObtenerDataUser() {
		ISistema sis = createSistema();
		assertEquals(null, sis.obtenerDataUsuario("lolo"));
		DtUsuario t = sis.obtenerDataUsuario("t");
	}
	
	@Test
	public void testObtenerDataUser2() {
		ISistema sis = createSistema();
		try {
			sis.altaUsuario(false, new DtUsuario("titi", "Tiago", "Silva", "tiavo", null,null, null, null));
		} catch (Exception e) {}
		assertEquals(null, sis.obtenerDataUsuario("lolo"));
		DtUsuario t = sis.obtenerDataUsuario("titi");
		try {
			sis.selecUsuario("titi");
		} catch (Exception e) {}
		DtModUser dt = new DtModUser("nom", "ape", null, "desc", "link");
		sis.modificarDatos(dt);
		List<String> lista = new ArrayList<String>();
		assertEquals(lista, sis.listarPaquetesExistentesNoComprados("titi"));
		sis.getDtUserWeb("titi");
		sis.getDtUserWebPropias("titi");
	}
	
	@Test
	public void testSelecUsuario2(){
		ISistema sis = createSistema();
		try {
			sis.selecUsuario("uu");
		} catch (Exception e) {}
	}
	
	@Test
	public void testListarPaqExistientes(){
		ISistema sis = createSistema();
		try {
			sis.altaPaquete( new DtPaquete ("nombre", "desc", 3, 3f, LocalDate.now(), 3, 3));
		} catch (Exception e) {}
		List<String> lista = new ArrayList<String>();
		lista.add("paq1");
		lista.add("nombreee");
		lista.add("nombre");
		
		assertEquals(lista, sis.listarPaquetesExistentes());
	    assertThrows(Exception.class, () -> {
	    	sis.altaPaquete( new DtPaquete ("nombre", "desc", 3, 3f, LocalDate.now(), 3, 3));
	    });
	    try {
			sis.seleccionarPaquete("nombre");
		} catch (Exception e) {}
	    assertThrows(Exception.class, () -> {
	    	sis.seleccionarPaquete("holi");
	    });
	    assertEquals(lista, sis.listarPaquetesExistentesNoComprados("titi"));
	}
	
	@Test
	public void testAltaActividad(){
		ISistema sis = createSistema();
		try {
			sis.altaUsuario(true, new DtUsuario("tuu", "Tiago", "Silva", "tiaoov", LocalDate.of(2003, 9, 3), "Yo", "y.com", null), "passt123");
		} catch (Exception e) {}
		sis.altaDepartamento(new DtDepartamento("Artigas", "123123", "me quemo"));
		DtActTur act = new DtActTur("Act1", "DescAct1", 5, 100f, "Sanjo", LocalDate.of(2023, 9, 10));
		try {
			sis.altaCategoria("Cat");
		} catch (Exception e) {}
		List<String> lista = new ArrayList<String>();
		lista.add("Cat");
		try {
			sis.altaActividad("tuu", "Artigas", act, lista);
		} catch (Exception e) {}
	    assertThrows(Exception.class, () -> {
	    	sis.altaActividad("tuu", "Artigas", act, lista);
	    });
	    sis.guardarDepartamentoSegunAct("banana");
	    sis.guardarDepartamentoSegunAct("Act1");
	    assertEquals(new DtUsuario("tuu", "Tiago", "Silva", "tiaoov", LocalDate.of(2003, 9, 3), "Yo", "y.com", null), sis.obtenerDataUsuarioConEmail("tiaoov"));
	    assertEquals(null, sis.obtenerDataUsuarioConEmail("tiaoopspdv"));
	    sis.guardarImagenAct("Artigas", "Act1", "newURL");
	    sis.getImagenAct("Artigas", "Act1");
	    sis.getDtUserWeb("tuu");
	    sis.getDtUserWebPropias("tuu");
	    
	    
	    
	    List<String> list =  new ArrayList<>();
	  
	    assertEquals(list, sis.listarActividadesConfirmadasDeDepartamento("Artigas"));
	}
	
	@Test
	public void testTuristaComPaq(){
		ISistema sis = createSistema();
		try {
			sis.altaUsuario(false, new DtUsuario("turru", "Tiago", "Silva", "tiaoopv", LocalDate.of(2003, 9, 3), null,null,null));
		} catch (Exception e) {}
		try {
			sis.altaPaquete( new DtPaquete ("nombreee", "desc", 3, 3f, LocalDate.now(), 3, 3));
		} catch (Exception e) {}
		try {
			sis.turistaCompraPaquete("turru", "nombreee");
		} catch (Exception e) {}
	    assertThrows(Exception.class, () -> {
	    	sis.turistaCompraPaquete("turru", "nombreee");
	    });
	    assertThrows(Exception.class, () -> {
	    	sis.turistaCompraPaquete("t", "nombreee");
	    });
	}
	
	@Test
	public void testGetURLImageUser() {
		ISistema sis = createSistema();
		try {
			sis.altaUsuario(false, new DtUsuario("gfvdf", "crerc", "cee", "ferferf", LocalDate.of(2003, 9, 3), null,null,null));
		} catch (Exception e) {}
		
		String urlImage = sis.getURLImageUser("gfvdf");
		assertEquals(null, urlImage);		
	}
	
	@Test
	public void testSeleccionarActividadDtEst() {
		ISistema sis = createSistema();
		
		DtActTur act = new DtActTur("Act17", "DescAct1", 5, 100f, "Sanjo", LocalDate.of(2023, 9, 10));
		List<String> lista = new ArrayList<String>();
		try {
			sis.altaActividad("t", "Artigas", act, lista);
		} catch (Exception e) {}
		assertNull(sis.seleccionarActividadDtEst("jjjj"));
		assertNotNull(sis.seleccionarActividadDtEst("Act17"));		
	}
	
	@Test
    public void testSeleccionarDepartamentoConf() {
		ISistema sis = createSistema();
		List<String> actividadesConf;
		try {
			assertThrows(Exception.class, () -> {
				sis.seleccionarDepartamentoConf("hhhh");
		    });
			actividadesConf = sis.seleccionarDepartamentoConf("Artigas");
			assertEquals(0, actividadesConf.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void testListarPaquetesCompradosTur() {
		ISistema s = createSistema();
		assertEquals(new ArrayList<String>(), s.listarPaquetesCompradosTur("turru", "sal"));
	}
	@Test
	public void testSalidasVigentesDeAct() {
		ISistema s = createSistema();
		List<String> es = new ArrayList<String>();
		es.add("SalidaTur");
		assertEquals(es, s.salidasVigentesDeAct("Act1"));
	}
	
	@Test
	public void testVoids() {
		ISistema s = createSistema();
		try {
			s.imprimirLista();
		} catch (Exception e) {}
		
		try {
			s.altaActividadEstado("tuu", "Artigas", new DtActTur("ActTest1", "DescTest1", 3, 100f, "ciudadtruco", LocalDate.of(2023, 11, 20)), "RECHAZADA");			
		} catch (Exception e) {}
		
		try {
			s.acepRecActividad("ActTest1", "CONFIRMADA");
		} catch (Exception e) {}
	}
	@Test
	public void testSeleccionarActividadDtEst2() {
		ISistema s = createSistema();
		try {
			assertEquals(new DtActTurEst("ActTest1", "DescTest1", 3, 100f, "ciudadtruco", LocalDate.of(2023, 11, 20), estadosActividad.CONFIRMADA), s.seleccionarActividadDtEst("ActTest1"));
		} catch (Exception e) {}
		try {
			assertNull(s.seleccionarActividadDtEst("noexiste"));
		} catch (Exception e) {}
	}
	
	@Test 
	public void testSeleccionarDepartamentoConf2() {
		ISistema s = createSistema();
		assertThrows(Exception.class, () -> s.seleccionarDepartamentoConf("Noexisto pa"));
		try {			
			assertEquals(new ArrayList<String>(), s.seleccionarDepartamentoConf("Artigas"));
		} catch (Exception e) {}
	}
	@Test
	public void testCheckExistWeb() throws Exception{
		ISistema s = createSistema();

		s.altaUsuario(false, new DtUsuario("tiagot", "tiagot", "apetest", "tiaval@test", LocalDate.of(2003, 2, 1), null, null, "uruguay", new ArrayList<String>(), new ArrayList<String>()), "contrat");
		
		assertTrue(s.checkUserNickExist("tiagot"));
		assertTrue(s.checkUserEmailExist("tiaval@test"));
		assertFalse(s.checkUserEmailExist("tiaval@tesadsfgafgdst"));
		assertTrue(s.checkValidUser("tiagot", "contrat"));
		assertFalse(s.checkValidUser("tiagot", "coantrat"));
		assertTrue(s.checkValidUser("tiaval@test", "contrat"));
		assertFalse(s.checkValidUser("tiaval@test", "contrata"));
		assertFalse(s.checkValidUser("tiavavl@test", "contrat"));
	}
	@Test
	public void testObtenerDataUsuarioConEmail() {
		ISistema s = createSistema();
		assertNull(s.obtenerDataUsuarioConEmail("tiavafadsvijnhgfani"));
		assertNotNull(s.obtenerDataUsuarioConEmail("tiaval@test"));
		assertNotNull(s.obtenerDataUsuarioConEmail("tiav"));
	}
	@Test
	public void testModificarDatosWeb() throws Exception{
		ISistema s = createSistema();
		assertThrows(Exception.class, () -> s.modificarDatosWeb("tiagasdadsot", new DtModUser("Tiago", "Silva", LocalDate.of(2003, 9, 3), "Uruguay")));
		s.modificarDatosWeb("tiagot", new DtModUser("Tiago", "Silva", LocalDate.of(2003, 9, 3), "Uruguay"));
	}
	@Test
	public void testModificarImagenUser() throws Exception{
		ISistema s = createSistema();
		assertThrows(Exception.class, () -> s.modificarImagenUser("sADFSG", "a"));
		s.modificarImagenUser("tiagot", "Fachero.jpg");
		assertEquals("Fachero.jpg", s.getURLImageUser("tiagot"));
	}
	@Test
	public void testAltaSalida() throws Exception {
		ISistema s = createSistema();
		assertThrows(Exception.class, () -> s.seleccionarDepartamentoSegunActividad("Act1ghsgdhhdsgghd"));
		s.seleccionarDepartamento("Artigas");
		s.confirmarAltaSalTur("Act1", new DtSalidaTuristica("SalidaTur", LocalDate.of(2023, 11, 26), LocalTime.of(20, 20), "Micui", 3, LocalDate.now()));
	}
	@Test
	public void testGuardarImagenSalida() {
		ISistema s = createSistema();
		s.guardarImagenSalida("Artigas", "Act1", "SalidaTur", "/prueba/img/");
	}
	@Test
	public void testGuardarImagenAct() {
		ISistema s = createSistema();
		s.guardarImagenAct("Artigas", "Act1", "/prueba/img/");
		
		assertEquals("/prueba/img/", s.getImagenAct("Artigas", "Act1"));
	}
	@Test
	public void testVerActividadesCP() {
		ISistema s = createSistema();
		assertEquals(new ArrayList<String>(), s.verActividadesCP("nombre"));	
	}
	@Test
	public void testListarPaquetesExistentesNoCompradosConActividad() {
		ISistema s = createSistema();
		List<String> esperado = new ArrayList<String>();
		esperado.add("paq1");
		assertEquals(esperado, s.listarPaquetesExistentesNoCompradosConActividad("tiagot"));	
	}
	
	@Test
	public void testObtenerCostoTotal() {
		ISistema s = createSistema();
		assertEquals(0f, s.obtenerCostoTotal("nombreee"));
	}
	@Test
	public void testGetDtUserWeb() throws Exception {
		ISistema s = createSistema();
		s.altaUsuario(true, new DtUsuario("tiagop", "tiagop", "apetestpr", "tiaval@testpr", LocalDate.of(2003, 2, 3), "desc", "link2ed", null, new ArrayList<String>(), new ArrayList<String>()), "passtest123");
		assertEquals("uruguay", s.getDtUserWeb("tiagot").getNacion());
		assertEquals("desc", s.getDtUserWeb("tiagop").getDesc());
		assertEquals("uruguay", s.getDtUserWebPropias("tiagot").getNacion());
		assertEquals("desc", s.getDtUserWebPropias("tiagop").getDesc());
	}
	
	@Test
	public void testAltaInscripcionWebDep() throws Exception {
		ISistema s = createSistema();
		assertThrows(Exception.class, () -> s.altaInscripcionWebDep("Artigas", "Act1", "SalidaTur", "tiagot", 433425));
		s.altaInscripcionWebDep("Artigas", "Act1", "SalidaTur", "tiagot", 2);
		
		s.altaCategoria("Divertido");
		List<String> cates = new ArrayList<String>();
		cates.add("Divertido");
		s.altaActividad("tiagop", "Artigas", new DtActTur("ActTest2", "desc1", 5, 100f, "micasa", LocalDate.of(2023, 11, 20)), cates);
		s.seleccionarDepartamento("Artigas");
		s.confirmarAltaSalTur("ActTest2", new DtSalidaTuristica("SalidaTur2", LocalDate.of(2023, 11, 26), LocalTime.of(20, 20), "Micu3", 5, LocalDate.now()));
		s.altaPaquete(new DtPaquete("paq1", "desc1", 3, 10, LocalDate.of(2023,10,25),5,30)); 
		s.seleccionarPaquete("paq1");
		s.seleccionarDepartamento("Artigas");
		s.seleccionarActividadParaPaquete("ActTest2");
		
		assertThrows(Exception.class, () -> s.altaInscripcionWebCat("Divertido", "ActTest2", "SalidaTur", "tiagot", 433425));
//		s.altaInscripcionWebCat("Divertido", "ActTest2", "SalidaTur2", "tiagot", 2);
	}
	
	@Test
	public void testGetImageWeb() {
		ISistema s = createSistema();
		assertNull(s.getImagenSal("Artigas", "ActTest2", "SalidaTur2"));
//		assertNull(s.getImagenSal("Divertido", "ActTest2", "SalidaTur2"));
		assertNull(s.getImagenSal("SalidaTur2"));
		assertNull(s.getImagenSal("SalihgdaTur2"));
	}
//	@Test
//	public void testActividadURL() {
//		ISistema s = createSistema();
//		List<String> r = new ArrayList<String>();
//		r.add("ActTest2");
//		assertEquals(r, s.actividadURL("paq1", r));
//	}
	@Test
	public void testAllQueryWeb() {
		ISistema s = createSistema();
		assertEquals(new ArrayList<String>(), s.listarActividadesConfirmadasDeDepartamento("Artigas"));
		assertEquals(new ArrayList<String>(), s.listarActividadesConfirmadasDeCategoria("Divertido"));
		assertEquals(new ArrayList<String>(), s.listarSalidasDeActividad("ActTest1"));
		assertEquals(new ArrayList<String>(), s.listarCategoriasDeActividad("ActTest2"));
		assertEquals(new ArrayList<String>(), s.listarPaquetesDeActividad("ActTest2"));
		assertEquals(null, s.getImagenDeActividad("ActTest1"));
	}
}
