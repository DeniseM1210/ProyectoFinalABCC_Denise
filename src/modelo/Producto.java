package modelo;

public class Producto {
	private int clave;
	private String descripcion;
	private String precio;
	private String disponibilidad;
	
	public Producto(int clave, String descripcion, String precio, String disponibilidad) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
	}

	public Producto() {
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}


	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String diponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	@Override
	public String toString() {
		return "Producto [clave=" + clave + ", descripcion=" + descripcion + ", precio=" + precio + ", disponibilidad="
				+ disponibilidad + "]";
	}

	
	
	
	

}
