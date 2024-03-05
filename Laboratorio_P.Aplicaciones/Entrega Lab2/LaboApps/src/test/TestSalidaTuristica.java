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

import datatype.DtSalidaTuristica;
import logica.Paquete;
import logica.SalidaTuristica;


public class TestSalidaTuristica {
	
	private SalidaTuristica createSal() {
		SalidaTuristica sal = new SalidaTuristica("nombreSal", LocalDate.of(2023, 11, 20), 
				LocalTime.of(20,30), "lugarSal", 5, LocalDate.of(2023, 10, 26));
		
		return sal;		
	}
	
	@Test
    public void testGetNombre() {
        assertEquals("nombreSal", createSal().getNombre());
    }
	
	@Test
    public void testGetFechaSal() {
        assertEquals(LocalDate.of(2023, 11, 20), createSal().getFechaSalida());
    }
	
	@Test
    public void testGetHora() {
        assertEquals(LocalTime.of(20,30), createSal().getHora());
    }
	
	@Test
    public void testGetLugarSal() {
        assertEquals("lugarSal", createSal().getLugarSalida());
    }
	
	@Test
    public void testGetCant() {
        assertEquals(5, createSal().getCantMaxTuristas());
    }
	
	@Test
    public void testGetFechaAlta() {
        assertEquals(LocalDate.of(2023, 10, 26), createSal().getFechaAlta());
    }
	
	@Test
    public void testSetImagen() {
		SalidaTuristica	sal = createSal();
		sal.setImagen("marihuana.png");
        assertEquals("marihuana.png", sal.getImagen());
    }
	
	@Test
    public void testSetFechaSal() {
		SalidaTuristica	sal = createSal();
		sal.setFechaSalida(LocalDate.of(2023, 12, 26));
        assertEquals(LocalDate.of(2023, 12, 26), sal.getFechaSalida());
    }
	
	@Test
    public void testSetHora() {
		SalidaTuristica	sal = createSal();
		sal.setHora(LocalTime.of(21,30));
        assertEquals(LocalTime.of(21,30), sal.getHora());
    }
	
	@Test
    public void testSetLugarSal() {
		SalidaTuristica	sal = createSal();
		sal.setLugarSalida("siu");
        assertEquals("siu", sal.getLugarSalida());
    }
	
	@Test
    public void testSetCant() {
		SalidaTuristica	sal = createSal();
		sal.setCantMaxTuristas(19);
        assertEquals(19, sal.getCantMaxTuristas());
    }
	
	@Test
    public void testSetFechaAlta() {
		SalidaTuristica	sal = createSal();
		sal.setFechaAlta(LocalDate.of(2024, 12, 26));
        assertEquals(LocalDate.of(2024, 12, 26), sal.getFechaAlta());
    }
	
	@Test
    public void testConstructor() {
		SalidaTuristica	sal = new SalidaTuristica();
        assertNotNull(sal);
	}
	
	@Test
    public void testDtSalTur() {
		SalidaTuristica	sal = createSal();
		DtSalidaTuristica data = sal.dtSalTur();
		
		assertEquals("nombreSal", data.getNombreSalida());
		assertEquals(LocalDate.of(2023, 11, 20), data.getFechaSalida());
		assertEquals(LocalTime.of(20,30), data.getHoraSalida());
		assertEquals("lugarSal", data.getLugarSalida());
		assertEquals(LocalDate.of(2023, 10, 26), data.getFechaAlta());
	}
	
	@Test
    public void testEstaVigente() {
		SalidaTuristica	sal = new SalidaTuristica();
		
		assertFalse(sal.estaVigente());
	}
	
	
	
	
	
}