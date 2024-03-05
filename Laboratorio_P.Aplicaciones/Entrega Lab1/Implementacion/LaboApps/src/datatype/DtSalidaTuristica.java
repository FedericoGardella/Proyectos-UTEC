package datatype;

import java.time.LocalDate;
import java.time.LocalTime;

public class DtSalidaTuristica {
	private String nombreSalida;
	private LocalDate fechaSalida;
	private LocalTime horaSalida;
	private String lugarSalida;
	private Integer cantMaxTur;
	private LocalDate fechaAlta;
	
	public String getNombreSalida() {
		return nombreSalida;
	}
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public LocalTime getHoraSalida() {
		return horaSalida;
	}
	public String getLugarSalida() {
		return lugarSalida;
	}
	public Integer getCantMaxTur() {
		return cantMaxTur;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public DtSalidaTuristica() {
		// TODO Auto-generated constructor stub
	}
	public DtSalidaTuristica(String nombreSalida, LocalDate fechaSalida, LocalTime horaSalida, String lugarSalida, Integer cantMaxTur, LocalDate fechaAlta) {
		this.nombreSalida = nombreSalida;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.lugarSalida = lugarSalida;
		this.cantMaxTur = cantMaxTur;
		this.fechaAlta = fechaAlta;
	}
	@Override
    public String toString() {
        return "Salida Turística:\n" +
               "Nombre: " + nombreSalida + "\n" +
               "Fecha: " + fechaSalida + "\n" +
               "Hora: " + horaSalida + "\n" +
               "Lugar: " + lugarSalida + "\n" +
               "Cant. Máx. Turistas: " + cantMaxTur + "\n" +
               "Fecha de Alta: " + fechaAlta;
    }	
}
