package modelo;

public class Empleado {
	private String RFCEmpleado;
	private String Nombre;
	private String Apellidos;
	private int idPuesto;
	
	public Empleado() {}
	
	public Empleado(String rFCEmpleado, String nombre, String apellidos, int idPuesto) {
		super();
		RFCEmpleado = rFCEmpleado;
		Nombre = nombre;
		Apellidos = apellidos;
		this.idPuesto = idPuesto;
	}

	public String getRFCEmpleado() {
		return RFCEmpleado;
	}

	public void setRFCEmpleado(String rFCEmpleado) {
		RFCEmpleado = rFCEmpleado;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public int getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}

	@Override
	public String toString() {
		return "Empleado [RFCEmpleado=" + RFCEmpleado + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos
				+ ", idPuesto=" + idPuesto + "]";
	}
	

}
