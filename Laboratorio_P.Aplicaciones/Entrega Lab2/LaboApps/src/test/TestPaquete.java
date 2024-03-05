package test;
	
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import datatype.DtPaquete;
import datatype.DtSalidaTuristica;
import logica.ActividadTuristica;
import logica.Categoria;
import logica.Departamento;
import logica.Paquete;
import logica.PaqueteTurista;
import logica.Turista;

public class TestPaquete {
	
	private Paquete createPaq() {
		
		Map<String, ActividadTuristica> actividades = new HashMap<String, ActividadTuristica>();
		Map<String, PaqueteTurista> compPor = new HashMap<String, PaqueteTurista>();
        return new Paquete("nomTest", "descTest", 10, 15, LocalDate.of(2023, 10, 20), 7, 5);
    }
	
	@Test
    public void testGetDesc() {
        assertEquals("descTest", createPaq().getDesc());
    }
	
	@Test
    public void testGetPeriodo() {
        assertEquals(10, createPaq().getPeriodoValiez());
    }

	@Test
    public void testGetDescuento() {
        assertEquals(15, createPaq().getDescuento());
    }
	
	@Test
    public void testGetAlta() {
        assertEquals(LocalDate.of(2023, 10, 20), createPaq().getFechaAlta());
    }
	
	@Test
    public void testGetNombre() {
        assertEquals("nomTest", createPaq().getNombre());
    }
	
	@Test
    public void testGetCant() {
        assertEquals(7, createPaq().getCant());
    }
	
	@Test
    public void testSetDesc() {
		Paquete paq = createPaq();
		paq.setDesc("cocaina");
        assertEquals("cocaina", paq.getDesc());
    }
	
	@Test
    public void testSetTicket() {
		Paquete paq = createPaq();
		paq.setTickets(15);
        assertEquals(15, paq.getTickets());
    }
	
	@Test
    public void testSetPeriodo() {
		Paquete paq = createPaq();
		paq.setPeriodoValiez(4);
        assertEquals(4, paq.getPeriodoValiez());
    }
	
	@Test
    public void testSetDescuento() {
		Paquete paq = createPaq();
		paq.setDescuento(9);
        assertEquals(9, paq.getDescuento());
    }
	
	@Test
    public void testSetAlta() {
		Paquete paq = createPaq();
		paq.setFechaAlta(LocalDate.of(2024, 10, 20));
        assertEquals(LocalDate.of(2024, 10, 20), paq.getFechaAlta());
    }
	
	@Test
    public void testSetCant() {
		Paquete paq = createPaq();
		paq.setCant(11);;
        assertEquals(11, paq.getCant());
    }
	
	@Test
    public void testConstructor() {
        Paquete paq = new Paquete();
        assertNotNull(paq);
	}
	
	@Test
	public void testPaqueteDt() {
		DtPaquete p = new DtPaquete("nomTest", "descTest", 10, 15, LocalDate.of(2023, 10, 20), 7, 5);
		Paquete paq = new Paquete(p);
		
		assertEquals("nomTest", paq.getNombre());
		assertEquals("descTest", paq.getDesc());
		assertEquals(10, paq.getPeriodoValiez());
		assertEquals(15, paq.getDescuento());
		assertEquals(LocalDate.of(2023, 10, 20), paq.getFechaAlta());
		assertEquals(7, paq.getCant());	
	}
	
	@Test
    public void testListarActividades() {
		Paquete paq = createPaq();
		
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		ActividadTuristica act2 = new ActividadTuristica("Act2", "Desc2Test", 10, 100f, "CiudadTest", LocalDate.of(2024, 12, 20), new Departamento("Depa2Test"), cats);
		try {
			paq.agregarActividad(act1);
			paq.agregarActividad(act2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Exception exception = assertThrows(Exception.class, () -> {
            paq.agregarActividad(act2);
        });
		
		List<String> lista = paq.listarActividades();
		
		assertEquals(2, lista.size());
        assertEquals("Act1", lista.get(0));
        assertEquals("Act2", lista.get(1));
        assertEquals("Actividad ya registrada.", exception.getMessage());
	}
	
	@Test
    public void testGetData() {
		Paquete paq = createPaq();
		
		DtPaquete data = paq.getData();
		
		assertEquals("nomTest", data.getNombre());
		assertEquals("descTest", data.getDesc());
		assertEquals(10, data.getPeriodoValidez());
		assertEquals(15, data.getDescuento());
		assertEquals(LocalDate.of(2023, 10, 20), data.getFechaAlta());
		assertEquals(7, data.getCant());
	}
	
	@Test
    public void testGetDataTodo() {
		Paquete paq = createPaq();
		
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		ActividadTuristica act2 = new ActividadTuristica("Act2", "Desc2Test", 10, 100f, "CiudadTest", LocalDate.of(2024, 12, 20), new Departamento("Depa2Test"), cats);
		try {
			paq.agregarActividad(act1);
			paq.agregarActividad(act2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DtPaquete data = paq.getDataTodo();
		
		assertEquals("nomTest", data.getNombre());
		assertEquals("descTest", data.getDesc());
		assertEquals(10, data.getPeriodoValidez());
		assertEquals(15, data.getDescuento());
		assertEquals(LocalDate.of(2023, 10, 20), data.getFechaAlta());
		assertEquals(7, data.getCant());
		
		assertEquals(2, data.getNombreActividades().size()); // Verifica que haya dos actividades en la lista
        assertEquals("Act1", data.getNombreActividades().get(0));
        assertEquals("Act2", data.getNombreActividades().get(1));
	}
	
	@Test
    public void testTieneActividades() {
        Paquete paq = createPaq();

        assertFalse(paq.tieneActividades());

        Map<String, Categoria> cats = new HashMap<String, Categoria>();
        ActividadTuristica actividad = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
        try {
            paq.agregarActividad(actividad);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(paq.tieneActividades());
    }
	
	@Test
    public void testObtenerCostoTotal() {
		Paquete paq = createPaq();
		
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		ActividadTuristica act2 = new ActividadTuristica("Act2", "Desc2Test", 10, 100f, "CiudadTest", LocalDate.of(2024, 12, 20), new Departamento("Depa2Test"), cats);
		
		assertEquals(0,paq.obtenerCostoTotal());
		
		try {
			paq.agregarActividad(act1);
			paq.agregarActividad(act2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(2975,paq.obtenerCostoTotal());	
	}
	
	@Test
    public void testHayLugar() {
		Paquete paq = createPaq();
		
		assertTrue(paq.hayLugar());
		
		paq.setCant(0);
		
		assertFalse(paq.hayLugar());
	}
	
	@Test
    public void testListarCategorias() {
		Paquete paq = createPaq();
		
		Map<String, Categoria> categorias = new HashMap<>();
        categorias.put("Aventura", new Categoria("Aventura"));
        categorias.put("Cultura", new Categoria("Cultura"));
		
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), categorias);
		categorias.put("Diversion", new Categoria("Diversion"));
		ActividadTuristica act2 = new ActividadTuristica("Act2", "Desc2Test", 10, 100f, "CiudadTest", LocalDate.of(2024, 12, 20), new Departamento("Depa2Test"), categorias);
		
		try {
            paq.agregarActividad(act1);
            paq.agregarActividad(act2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> listaCategorias = paq.listarCategorias();

        assertEquals(3, listaCategorias.size());
        assertTrue(listaCategorias.contains("Aventura"));
        assertTrue(listaCategorias.contains("Cultura"));
        assertTrue(listaCategorias.contains("Diversion"));
	}
	
	@Test
	public void testEnlazarPaqueteTurista() {
		Paquete paq = createPaq();
	    Turista tur = new Turista("nickTest", "nameTest", "apeTest", "emailTest",
   		LocalDate.of(2023, 10, 30), "nacionTest");
	        
	    PaqueteTurista pt = new PaqueteTurista(paq,tur);
	        
	    paq.enlazarPaqueteTurista(pt);
	        
	    assertEquals(1, paq.paquetesCompradosPor.size());
        assertEquals(pt, paq.paquetesCompradosPor.get("nickTest"));
	        
	}
	
	@Test
	public void TestFueComprado() {
		Paquete paq = createPaq();
		
		assertFalse(paq.fueComprado());
		
		Turista tur = new Turista("nickTest", "nameTest", "apeTest", "emailTest",
		   		LocalDate.of(2023, 10, 30), "nacionTest");	        
	    PaqueteTurista pt = new PaqueteTurista(paq,tur);
	        
	    paq.enlazarPaqueteTurista(pt);
		
	    assertTrue(paq.fueComprado());	
	}
	
	@Test
    public void testPaqueteCompradoPorTur() {
		Paquete paq = createPaq();
		Turista tur = new Turista("nickTest", "nameTest", "apeTest", "emailTest", LocalDate.of(2023, 10, 30), "nacionTest");	        
	    PaqueteTurista pt = new PaqueteTurista(paq,tur);
		
	    assertFalse(paq.paqueteCompradoPorTur("nickTest"));
	    
	    paq.enlazarPaqueteTurista(pt);
		
	    assertTrue(paq.paqueteCompradoPorTur("nickTest"));	
	}
	
	@Test
    public void testActividadURL() {
		Paquete paq = createPaq();
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		act1.setImagen("lsd.png");
		ActividadTuristica act2 = new ActividadTuristica("Act2", "Desc2Test", 10, 100f, "CiudadTest", LocalDate.of(2024, 12, 20), new Departamento("Depa2Test"), cats);
		
		try {
			paq.agregarActividad(act1);
			paq.agregarActividad(act2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> urls = paq.actividadURL(List.of("Act1", "Act2"));
		
		assertEquals("lsd.png", urls.get(0));
        assertEquals("nulo", urls.get(1));	
	}
	
	@Test
    public void testTieneASal() {
		Paquete paq = createPaq();
		Map<String, Categoria> cats = new HashMap<String, Categoria>();
		ActividadTuristica act1 = new ActividadTuristica("Act1", "DescTest", 10, 100f, "CiudadTest", 
				LocalDate.of(2023, 10, 20), new Departamento("DepaTest"), cats);
		ActividadTuristica act2 = new ActividadTuristica("Act2", "Desc2Test", 10, 100f, "CiudadTest", 
				LocalDate.of(2024, 12, 20), new Departamento("Depa2Test"), cats);
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
			paq.agregarActividad(act2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(paq.tieneASal("nombreSal"));	
	}
	
	
	
	
	
	
	
}