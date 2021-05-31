package modelo;

public class PuestosTrabajo {
	private int idPuesto;
	private String nombrePuesto;
	
	public PuestosTrabajo() {}
	
	public PuestosTrabajo(int idPuesto, String nombrePuesto) {
		super();
		this.idPuesto = idPuesto;
		this.nombrePuesto = nombrePuesto;
	}

	public int getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	@Override
	public String toString() {
		return "PuestosTrabajo [idPuesto=" + idPuesto + ", nombrePuesto=" + nombrePuesto + "]";
	}
	
	

}
