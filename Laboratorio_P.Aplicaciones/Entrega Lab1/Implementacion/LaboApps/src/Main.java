import logica.Fabrica;
import logica.ISistema;
import presentacion.Pantalla;


import datatype.DtActTur;
import datatype.DtPaquete;
import datatype.DtSalidaTuristica;
import datatype.DtUsuario;
import datatype.DtDepartamento;

import java.awt.EventQueue;
import java.awt.Color;

import java.time.LocalTime;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		ISistema sis = Fabrica.getSistema();
		
        
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					Pantalla v = new Pantalla();
					v.getContentPane().setBackground(new Color(18,18,18));
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

//		try {
//			sis.altaDepartamento(new DtDepartamento("Artigas", "123123", "me quemo"));
//			sis.altaDepartamento(new DtDepartamento("Canelones", "333123", "arboles o comida? nunca lo sabremos"));
//			sis.altaDepartamento(new DtDepartamento("Cerro Largo", "1234423", "Falsos perros"));
//			sis.altaDepartamento(new DtDepartamento("Colonia", "1235394", "du sacramentu"));
//			sis.altaDepartamento(new DtDepartamento("Durazno", "19993123", "re duro"));
//			sis.altaDepartamento(new DtDepartamento("Flores", "120393", "o era florida?"));
//			sis.altaDepartamento(new DtDepartamento("Florida", "123245", "o era flores?"));
//			sis.altaDepartamento(new DtDepartamento("Lavalleja", "222123", "Jefe de los 33"));
//			sis.altaDepartamento(new DtDepartamento("Maldonado", "9823", "o Biendonado"));
//			sis.altaDepartamento(new DtDepartamento("Montevideo", "990123", "mas video que monte"));
//			sis.altaDepartamento(new DtDepartamento("Paysandu", "8865123", "Paypalsandu"));
//			sis.altaDepartamento(new DtDepartamento("Rio Negro", "1345123", "de tanta mugre"));
//			sis.altaDepartamento(new DtDepartamento("Rivera", "233123", "resistan colorados"));
//			sis.altaDepartamento(new DtDepartamento("Rocha", "3563123", "o cheta?"));
//			sis.altaDepartamento(new DtDepartamento("Salto", "78863123", "pero no muy alto"));
//			sis.altaDepartamento(new DtDepartamento("San Jose", "9393123", "como canelones pero sin agua salada"));
//			sis.altaDepartamento(new DtDepartamento("Soriano", "9494123", "existo, no me olviden"));
//			sis.altaDepartamento(new DtDepartamento("Tacuarembo", "3934123", "Ta re cuarembo"));
//			sis.altaDepartamento(new DtDepartamento("Treinta y Tres", "123123", "Sesenta y Nueve"));
//			
//			sis.altaUsuario(true, new DtUsuario("t", "Tiago", "Silva", "tiav", LocalDate.of(2003, 9, 3), "Yo", "y.com", null));
//			sis.altaUsuario(true, new DtUsuario("t2", "ogaiT", "avliS", "tiavo@e", LocalDate.of(2003, 3, 9), "oY", "y.com", null));
//			sis.altaUsuario(false, new DtUsuario("tur", "tur1", "ape", "tur1@ape.com", LocalDate.of(2000, 10, 26), null, null, "EEUU"));
//			
//			sis.altaPaquete(new DtPaquete("paq1", "desc1", 3, 10, LocalDate.of(2003,10,10)));
//			sis.altaPaquete(new DtPaquete("paq12", "desc1", 3, 10, LocalDate.of(2003,10,10)));
//			
//			sis.altaActividad("t", "Artigas", new DtActTur("Act1", "DescAct1", 5, 100f, "Sanjo", LocalDate.of(2023, 9, 10)));
//			sis.altaActividad("t2", "Artigas", new DtActTur("Act2", "DescAct2", 52, 102f, "Sanjo2", LocalDate.of(2023, 9, 12)));
//						
//			sis.seleccionarPaquete("paq1");
//			sis.seleccionarDepartamento("Artigas");
//			
//			Actividades del departamento en memoria
//			sis.seleccionarActividadParaPaquete("Act1");
//			sis.seleccionarActividadParaPaquete("Act2");
//			
//
//			DtSalidaTuristica st = new DtSalidaTuristica("salida1",LocalDate.of(2024, 8, 24),LocalTime.of(20,30),"MiCasa",15,LocalDate.of(2023, 8, 22));
//			DtSalidaTuristica st2 = new DtSalidaTuristica("salida2",LocalDate.of(2023, 8, 28),LocalTime.of(21,30),"TuCasa",15,LocalDate.of(2023, 8, 30));
//			
//			sis.confirmarAltaSalTur("Act1", st);
//			sis.confirmarAltaSalTur("Act1", st2);
//			
//			
//		} catch (Exception e) {
//			System.err.println(e);
//		}
	}

	public Main() {
		super();
	}

}