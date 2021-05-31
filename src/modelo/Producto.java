package modelo;

public class Producto {
	private String clave;
	private String descripcion;
	private double precio;
	private String disponibilidad;
	
	public Producto(String clave, String descripcion, double precio, String disponibilidad) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
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
