package modelo;

public class Cliente {
	private String nombre;
	private String correoE;
	private int numTel;
	private String direccion;
	
	public Cliente() {}
	
	public Cliente(String nombre, String correoE, int numTel, String direccion) {
		super();
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

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", correoE=" + correoE + ", numTel=" + numTel + ", direccion=" + direccion
				+ "]";
	}
	
	
	
	

}
