package main;

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
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;



public class Main {
	private static void preCargaBasica() {
		try {
			ISistema sis = Fabrica.getSistema();
			sis.altaDepartamento(new DtDepartamento("Artigas", "123123", "me quemo"));
			sis.altaDepartamento(new DtDepartamento("Canelones", "333123", "arboles o comida? nunca lo sabremos"));
			sis.altaDepartamento(new DtDepartamento("Cerro Largo", "1234423", "Falsos perros"));
			sis.altaDepartamento(new DtDepartamento("Colonia", "1235394", "du sacramentu"));
			sis.altaDepartamento(new DtDepartamento("Durazno", "19993123", "re duro"));
			sis.altaDepartamento(new DtDepartamento("Flores", "120393", "o era florida?"));
			sis.altaDepartamento(new DtDepartamento("Florida", "123245", "o era flores?"));
			sis.altaDepartamento(new DtDepartamento("Lavalleja", "222123", "Jefe de los 33"));
			sis.altaDepartamento(new DtDepartamento("Maldonado", "9823", "o Biendonado"));
			sis.altaDepartamento(new DtDepartamento("Montevideo", "990123", "mas video que monte"));
			sis.altaDepartamento(new DtDepartamento("Paysandu", "8865123", "Paypalsandu"));
			sis.altaDepartamento(new DtDepartamento("Rio Negro", "1345123", "de tanta mugre"));
			sis.altaDepartamento(new DtDepartamento("Rivera", "233123", "resistan colorados"));
			sis.altaDepartamento(new DtDepartamento("Rocha", "3563123", "o cheta?"));
			sis.altaDepartamento(new DtDepartamento("Salto", "78863123", "pero no muy alto"));
			sis.altaDepartamento(new DtDepartamento("San Jose", "9393123", "como canelones pero sin agua salada"));
			sis.altaDepartamento(new DtDepartamento("Soriano", "9494123", "existo, no me olviden"));
			sis.altaDepartamento(new DtDepartamento("Tacuarembo", "3934123", "Ta re cuarembo"));
			sis.altaDepartamento(new DtDepartamento("Treinta y Tres", "123123", "Sesenta y Nueve"));
			
			sis.altaCategoria("Diversion");
			sis.altaCategoria("Ejercicio");
			sis.altaCategoria("Zafari");
			sis.altaCategoria("Exotico");
			
			sis.altaUsuario(true, new DtUsuario("t", "Tiago", "Silva", "tiav", LocalDate.of(2003, 9, 3), "Yo", "y.com", null), "passt123");
			sis.altaUsuario(true, new DtUsuario("t2", "ogaiT", "avliS", "tiavo@e", LocalDate.of(2003, 3, 9), "oY", "y.com", null), "passt2123");
			sis.altaUsuario(false, new DtUsuario("tur", "tur1", "ape", "tur1@ape.com", LocalDate.of(2000, 10, 26), null, null, "EEUU"), "passtur123");
			
			
			sis.altaPaquete(new DtPaquete("paq1", "desc1", 3, 10, LocalDate.of(2023,10,25),5,30)); 
			sis.altaPaquete(new DtPaquete("paq12", "desc12", 3, 15, LocalDate.of(2023,10,25),1,30));
			 
			
			List<String> list = new ArrayList<String>();
			list.add("Diversion");
			list.add("Zafari");
			
			sis.altaActividad("t", "Artigas", new DtActTur("Act1", "DescAct1", 5, 100f, "Sanjo", LocalDate.of(2023, 9, 10)), list);
			sis.altaActividad("t2", "Artigas", new DtActTur("Act2", "DescAct2", 52, 102f, "Sanjo2", LocalDate.of(2023, 9, 12)), list);
			sis.altaActividad("t2", "Colonia", new DtActTur("ActCol", "DescActcol", 52, 102f, "el fuerte", LocalDate.of(2023, 9, 12)), list);
			sis.altaActividad("t", "Colonia", new DtActTur("ActCol2", "DescActcol2", 52, 102f, "la playa", LocalDate.of(2023, 9, 12)), list);
			
			
			sis.seleccionarPaquete("paq1");
			sis.seleccionarDepartamento("Artigas");
			
			sis.seleccionarActividadParaPaquete("Act1");
			sis.seleccionarActividadParaPaquete("Act2");
			
			sis.seleccionarPaquete("paq12");
			sis.seleccionarDepartamento("Colonia");
			
			sis.seleccionarActividadParaPaquete("ActCol");
			sis.seleccionarActividadParaPaquete("ActCol2");
			

			DtSalidaTuristica st = new DtSalidaTuristica("salida1",LocalDate.of(2023, 11, 1),LocalTime.of(20,30),"MiCasa",15,LocalDate.now());
			DtSalidaTuristica st2 = new DtSalidaTuristica("salida2",LocalDate.of(2023, 11, 2),LocalTime.of(21,30),"TuCasa",15,LocalDate.now());
			DtSalidaTuristica st3 = new DtSalidaTuristica("salida3",LocalDate.of(2023, 11, 3),LocalTime.of(21,30),"Casita",15,LocalDate.now());
			DtSalidaTuristica st4 = new DtSalidaTuristica("salida4",LocalDate.of(2023, 11, 4),LocalTime.of(21,30),"mlml",15,LocalDate.now());
			DtSalidaTuristica st5 = new DtSalidaTuristica("salida5",LocalDate.of(2023, 11, 5),LocalTime.of(21,30),"lplp",15,LocalDate.now());
			DtSalidaTuristica st6 = new DtSalidaTuristica("salida6",LocalDate.of(2023, 11, 6),LocalTime.of(21,30),"ioio",15,LocalDate.now());
			
			sis.seleccionarDepartamento("Artigas");
			
			sis.confirmarAltaSalTur("Act1", st);
			sis.confirmarAltaSalTur("Act1", st2);
			
			sis.confirmarAltaSalTur("Act2", st3);
			
			sis.seleccionarDepartamento("Colonia");
			
			sis.confirmarAltaSalTur("ActCol", st4);
			sis.confirmarAltaSalTur("ActCol", st5);
			
			sis.confirmarAltaSalTur("ActCol2", st6);
			
//			Float costo = sis.obtenerCostoTotal("paq12");
//			System.out.print(costo);
		
//			sis.turistaCompraPaquete("tur", "paq12");
//			sis.turistaCompraPaquete("tur", "paq1");
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static void main(String[] args) {

		
		ISistema sis = Fabrica.getSistema();		

//		preCargaBasica();

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
		try {
			sis.listarActividadesConfirmadasDeCategoria("Diversion");
			System.out.println(sis.getImagenDeActividad("a"));
		} catch (Exception e) {
			System.err.println(e);
		}

	}
	
	 
	

	public Main() {
		super();
	}

}