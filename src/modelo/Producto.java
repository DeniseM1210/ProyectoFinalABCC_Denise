package modelo;

public class Producto {
	private String clave;
	private String descripcion;
	private double precio;
	private int cantidad;
	private String color;
	
	public Producto(String clave, String descripcion, double precio, int cantidad, String color) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
		this.color = color;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Producto [clave=" + clave + ", descripcion=" + descripcion + ", precio=" + precio + ", cantidad="
				+ cantidad + ", color=" + color + "]";
	}
	
	
	

}
