package datatype;

public class DtHora {
	private Integer hora;
	private Integer minutos;
	
	public Integer getHora() {
		return hora;
	}
	public Integer getMinutos() {
		return minutos;
	}
	public DtHora(Integer hora, Integer minutos) {
		this.hora = hora;
		this.minutos = minutos;		
	}
	@Override
    public String toString() {
        return String.format("%02d:%02d", hora, minutos);
    }	
}
