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

import logica.Paquete;
import logica.SalidaTuristica;
import logica.Turista;
import logica.TuristaSalida;

public class TestTuristaSalida {
	
	private TuristaSalida createTS() {
		SalidaTuristica sal = new SalidaTuristica("nombreSal", LocalDate.of(2023, 11, 20), 
				LocalTime.of(20,30), "lugarSal", 5, LocalDate.of(2023, 10, 26));
		Turista tur = new Turista("nickTest", "nameTest", "apeTest", "emailTest",
		   		LocalDate.of(2023, 10, 30), "nacionTest");
		
		return new TuristaSalida(sal, 5, LocalDate.of(2023, 11, 26),tur);		
	}
	
	@Test
	public void TestgetMiSalida() {
		SalidaTuristica sal = new SalidaTuristica("nombreSal", LocalDate.of(2023, 11, 20), 
				LocalTime.of(20,30), "lugarSal", 5, LocalDate.of(2023, 10, 26));
		Turista tur = new Turista("nickTest", "nameTest", "apeTest", "emailTest",
		   		LocalDate.of(2023, 10, 30), "nacionTest");
		TuristaSalida ts = new TuristaSalida(sal, 5, LocalDate.of(2023, 11, 26),tur);
		
		assertEquals(sal, ts.getMiSalida());
	}
	
	@Test
    public void testSetMiSalida() {
		TuristaSalida ts = createTS();
		SalidaTuristica sal = new SalidaTuristica("nomSal", LocalDate.of(2023, 12, 20), 
				LocalTime.of(21,30), "lugarSal", 5, LocalDate.of(2023, 10, 26));
		ts.setMiSalida(sal);
        assertEquals(sal, ts.getMiSalida());
    }

	@Test
    public void testSetCant() {
		TuristaSalida ts = createTS();
		ts.setCant(20);
        assertEquals(20, ts.getCant());
    }

	@Test
    public void testSetFecha() {
		TuristaSalida ts = createTS();
		ts.setFecha(LocalDate.of(2023, 10, 26));
        assertEquals(LocalDate.of(2023, 10, 26), ts.getFecha());
    }
	
	@Test
    public void testConstructor() {
		TuristaSalida ts = new TuristaSalida();
        assertNotNull(ts);
	}
	
	@Test
    public void testSetCosto() {
		TuristaSalida ts = createTS();
		ts.setCosto(123f);
        assertEquals(123f,ts.getCosto());
    }





}