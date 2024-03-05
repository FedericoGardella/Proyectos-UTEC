package logica;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table(name = "paquete_turista")
public class PaqueteTurista {

	@Id
    @ManyToOne
    @JoinColumn(name = "nickTurista")
    private Turista turista;
   
	@Id
    @ManyToOne
    @JoinColumn(name = "nombrePaquete")
    private Paquete paquete;
    private Integer ticketsDisponibles;
	private LocalDate fecha;
	
	public PaqueteTurista() {}
	
	public PaqueteTurista(Paquete miPaq, Turista miTur) {
		this.paquete = miPaq;
		this.turista = miTur;
		this.fecha = LocalDate.now();
		this.ticketsDisponibles = miPaq.getTickets();
	}
	
	public void setFecha(LocalDate fechaNueva) {
		this.fecha = fechaNueva;
	}
	
 	public String getNombrePaquete() {
		return this.paquete.getNombre();
	}
	public String getNombreTurista() {
		return this.turista.getNick();
	}
	public LocalDate getFecha() {
		return this.fecha;
	}
	public LocalDate getVencimiento() {
//		Calculado a partir de 'fecha' + 'periodoValidez' de miPaquete
		return this.fecha.plus(paquete.getPeriodoValiez(), ChronoUnit.DAYS);
	}
	public void enlazarTurista(Turista tur) {
		this.paquete.enlazarPaqueteTurista(this);
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaqueteTurista that = (PaqueteTurista) o;
        return Objects.equals(this.turista.getNick(), that.getNombreTurista())&&
               Objects.equals(this.paquete.getNombre(), that.getNombrePaquete());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.turista.getNick(), this.paquete.getNombre());
    }

	public Integer getTicketsDisponibles() {
		return ticketsDisponibles;
	}

	public void setTicketsDisponibles(Integer ticketsDisponibles) {
		this.ticketsDisponibles = ticketsDisponibles;
	}
	
	public String tieneASal(String sal) {
		if(paquete.tieneASal(sal)) {
			return paquete.getNombre();
		}
		return null;
	}
}
