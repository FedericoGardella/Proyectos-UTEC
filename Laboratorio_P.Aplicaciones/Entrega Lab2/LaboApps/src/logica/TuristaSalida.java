package logica;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inscripciones")
public class TuristaSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "nombreSal")
	private SalidaTuristica miSalida;
	private Integer cant;
	private Float costo;
	private LocalDate fecha;
	
	public SalidaTuristica getMiSalida() {
		return miSalida;
	}
	public void setMiSalida(SalidaTuristica miSalida) {
		this.miSalida = miSalida;
	}
	public Integer getCant() {
		return cant;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public TuristaSalida() {}
	
	public TuristaSalida(SalidaTuristica sal, Integer cant, LocalDate fecha, Turista tur ) {
		this.miSalida = sal;
		this.cant = cant;
		this.fecha = fecha;
	}
	public Float getCosto() {
		return costo;
	}
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	
}