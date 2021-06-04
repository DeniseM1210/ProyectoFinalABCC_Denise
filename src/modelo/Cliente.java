package modelo;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String correoE;
	private String numTel;
	private String direccion;
	
	public Cliente() {}
	
	public Cliente(int idCliente, String nombre, String correoE, String numTel, String direccion) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.correoE = correoE;
		this.numTel = numTel;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoE() {
		return correoE;
	}

	public void setCorreoE(String correoE) {
		this.correoE = correoE;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", correoE=" + correoE + ", numTel=" + numTel
				+ ", direccion=" + direccion + "]";
	}

	
	
	
	
	

}
