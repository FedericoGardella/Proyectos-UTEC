package test;
	
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import datatype.DtSalidaTuristica;
import logica.ActividadTuristica;
import logica.Categoria;
import logica.Departamento;
import logica.SalidaTuristica;

public class TestCategoria {
	
	private Categoria createCat() {
		Map<String, ActividadTuristica> actividades = new HashMap<String, ActividadTuristica>();
		return new Categoria("nombreCat");
	}
	@Test
    public void testGetNombre() {
        assertEquals("nombreCat", createCat().getNombre());
    }

	@Test
    public void testConstructor() {
        Categoria categoria = new Categoria();
        assertNotNull(categoria);
	}
	
	@Test
    public void testAgregarActividadExitosa() {
		Categoria cat = createCat();
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		
		try {
            cat.agregarActividad(act1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		assertTrue(cat.listarActividades().contains("Act1"));
	}
	
	@Test
    public void testAgregarActividadRepetida() {
		Categoria cat = createCat();
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		
		try {
            cat.agregarActividad(act1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		assertThrows(Exception.class, () -> cat.agregarActividad(act1));
	}
	
	@Test
	public void testSeleccActividad() {
		Categoria cat = createCat();
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		
		try {
            cat.agregarActividad(act1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		ActividadTuristica act = cat.seleccActividad("Act1");
        assertNotNull(act);		
	}
	
	@Test
	public void testGetImagenAct() {
		Categoria cat = createCat();
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		act1.setImagen("porro.png");
		try {
            cat.agregarActividad(act1);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		assertEquals("porro.png", cat.getImagenAct("Act1"));	
	}

	
	@Test
	public void testGetImagenSal() {
		Categoria cat = createCat();
		DtSalidaTuristica sal = new DtSalidaTuristica("nombreSal", LocalDate.of(2023, 11, 20), 
			LocalTime.of(20,30), "lugarSal", 5, LocalDate.of(2023, 10, 26));
	  
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", 
			LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats); 
		try {
			act1.crearSalida(sal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		try { 
			cat.agregarActividad(act1); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	  
		assertEquals(null, cat.getImagenSal("Act1", "nombreSal")); 
	  }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}