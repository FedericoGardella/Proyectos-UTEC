package test;
	
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
	
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import datatype.DtActTur;
import datatype.DtSalidaTuristica;
import logica.ActividadTuristica;
import logica.Categoria;
import logica.Departamento;
import logica.Paquete;
import logica.estadosActividad;
	


public class TestActividadTuristica {

    private ActividadTuristica createAct() throws Exception{
    	Map<String, Categoria> cats = new HashMap<String, Categoria>();
    	Categoria c = new Categoria("CatTest1");
    	cats.put(c.getNombre(), c);
    	ActividadTuristica a = new ActividadTuristica("ActTest", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
    	DtSalidaTuristica dt = new DtSalidaTuristica("SalidaTest1", LocalDate.of(2023, 10, 27), LocalTime.of(10, 10), "Micasa", 3, LocalDate.now());
		a.crearSalida(dt);
        return a;
    }
    @Test
    public void testGetNombre() throws Exception{
        assertEquals("ActTest", createAct().getNombre());
    }
    @Test
    public void testGetDescripcion() throws Exception{
        assertEquals("DescTest", createAct().getDesc());
    }
    @Test
    public void testGetDuracion() throws Exception{
        assertEquals(10, createAct().getDuracion());
    }
    @Test
    public void testGetCosto() throws Exception{
        assertEquals(100f, createAct().getCosto());
    }
    @Test
    public void testGetCiudad() throws Exception{
        assertEquals("CiudadTest", createAct().getCiudad());
    }
	@Test
	public void testGetFecha() throws Exception{
		LocalDate expResult = LocalDate.of(2023, 10, 20);
		assertEquals(expResult, createAct().getFecha());
	}
	@Test
	public void testGetNombreDepartamento() throws Exception{
		String expResult = "DepaTest";
		String result = createAct().getNombreDepartamento();
		assertEquals(expResult, result);
	}
	@Test
	public void testGetNombreImagen() throws Exception{
		String expResult = null;
		String result = createAct().getImagen();
		assertEquals(expResult, result);
	}
	@Test
	public void testGetEstado() throws Exception{
		assertEquals(estadosActividad.AGREGADA, createAct().getEstado());
	}
	@Test
	public void testCreateActividad(){
		ActividadTuristica act = new ActividadTuristica("Act", "Desc", 5, 50f, "CiudadTest2", LocalDate.of(2023, 9, 10), new Departamento("DepaTest2"), estadosActividad.CONFIRMADA);
		assertEquals(estadosActividad.CONFIRMADA, act.getEstado());
	}
	@Test
	public void testSetImage() throws Exception{
		ActividadTuristica a = createAct();
		a.setImagen("agua.png");
		assertEquals("agua.png", a.getImagen());
	}
	@Test
	public void testEnlazadoConPaquete() throws Exception {
		ActividadTuristica a = createAct();
		Paquete p = new Paquete("Paq1Test", "Paq1DescTest", 10, 3, LocalDate.of(2022, 10, 3), 6, 7);
		a.agregarPaquete(p);
		
		assertThrows(Exception.class, () -> a.agregarPaquete(p));
	
		assertTrue(a.enlazadoConPaquete("Paq1Test"));
	}
	@Test
	public void testAgregarPaquete() throws Exception {
		ActividadTuristica act = createAct();
		Paquete paq = new Paquete("Paq1Test", "Paq1DescTest", 10, 3, LocalDate.of(2022, 10, 3), 6, 7);
		Paquete paq2 = new Paquete("Paq2Test", "Paq2DescTest", 11, 4, LocalDate.of(2022, 10, 4), 7, 8);
		
		assertFalse(act.enlazadoConPaquete("Paq1Test"));
		
		act.agregarPaquete(paq);
		
        assertTrue(act.enlazadoConPaquete("Paq1Test"));
        
        act.agregarPaquete(paq2);
        
        List<String> listaDePaquetes = act.listarProveedores();
        assertEquals(2, listaDePaquetes.size());
        assertTrue(listaDePaquetes.contains("Paq1Test"));
        assertTrue(listaDePaquetes.contains("Paq2Test"));
	}
	@Test
	public void testPrintDataAct() {
		new ActividadTuristica().printDataAct();
	}
	@Test
	public void testPrintDataActTur() {
		new ActividadTuristica().printDataActTur();
	}
	@Test
	public void testCrearSalida() throws Exception {
		DtSalidaTuristica dt = new DtSalidaTuristica("nombreSal", LocalDate.of(2023, 11, 20), 
				LocalTime.of(20,30), "lugarSal", 5, LocalDate.of(2023, 10, 26));
		ActividadTuristica act = createAct();
		act.crearSalida(dt);
		
		act.datosSalTur("nombreSal");
		act.getData();		
		act.getDataActTurEst();
		act.guardarImagenSalida("nombreSal", "nuevaimg.png");
		assertEquals(true,act.tieneASal("nombreSal"));
		assertEquals(false,act.tieneASal("asd"));
	}
	@Test
	public void testCrearSalidaExistente() throws Exception{
		DtSalidaTuristica dt = new DtSalidaTuristica("SalidaTest1", LocalDate.of(2023, 10, 27), LocalTime.of(10, 10), "Micasa", 3, LocalDate.now());
		ActividadTuristica a = createAct();
		try {
			a.crearSalida(dt);
		} catch (Exception e) {
			assertEquals("Ya existe una salida con ese nombre", e.getMessage());
		}
	}
	@Test 
	public void testVerSalidasVigentes() throws Exception {
		ActividadTuristica a = createAct();
		List<String> esperado = new ArrayList<String>();
		esperado.add("SalidaTest1");
		assertEquals(esperado, a.verSalidasVigentes());
		DtSalidaTuristica dt2 = new DtSalidaTuristica("SalidaTest2", LocalDate.of(2020, 8, 24),LocalTime.of(20,30), "Micasa", 3, LocalDate.now());
		a.crearSalida(dt2);
		assertEquals(esperado, a.verSalidasVigentes());
	}
	@Test
	public void testVerSalidas() throws Exception {
		ActividadTuristica a = createAct();
		DtSalidaTuristica dt = new DtSalidaTuristica("SalidaTest2", LocalDate.of(2020, 8, 24),LocalTime.of(20,30), "Micasa", 3, LocalDate.now());
		a.crearSalida(dt);
		a.verSalidas();
	}
	@Test
	public void testBuscarSalida() throws Exception{
		ActividadTuristica a = createAct();
		assertEquals(null, a.buscarSalida("SalidaTestNoExiste"));
		assertEquals("SalidaTest1", a.buscarSalida("SalidaTest1").getNombre());
	}
	@Test
	public void testGetDataActTur() throws Exception{
		DtActTur act1 = new DtActTur("ActTest", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20));
		assertEquals(act1, createAct().getDataActTur());
	}
	@Test
	public void testListarSalidas() throws Exception{
		ActividadTuristica a = createAct();
		List<String> esperado = new ArrayList<String>();
		esperado.add("SalidaTest1");
		assertEquals(esperado, a.listarSalidas());
		DtSalidaTuristica dt = new DtSalidaTuristica("SalidaTest2", LocalDate.of(2024, 8, 24),LocalTime.of(20,30), "Micasa", 3, LocalDate.now());
		a.crearSalida(dt);		
		List<String> esperado2 = new ArrayList<String>();
		esperado2.add("SalidaTest2");
		esperado2.add("SalidaTest1");
		assertEquals(esperado2, a.listarSalidas());
	}
	@Test 
	public void testGetSalida() throws Exception{
		ActividadTuristica a = createAct();
		assertEquals("SalidaTest1", a.getSalida("SalidaTest1").getNombre());
	}
	@Test
	public void testListarCategorias() throws Exception{
		List<String> esperado = new ArrayList<String>();
		esperado.add("CatTest1");
		assertEquals(esperado, createAct().listarCategorias());
	}
	@Test
	public void testCambiarEstado() throws Exception{
		ActividadTuristica a = createAct();
		a.cambiarEstado(estadosActividad.RECHAZADA);
		assertEquals(estadosActividad.RECHAZADA, a.getEstado());
	}
	@Test
	public void testGetImgSalida() throws Exception{
		assertNull(createAct().getImagenSal("SalidaTest1"));
	}
	

}
