package modelo;

public class CompraProducto {
	private String claveProducto;
	private String fecha;
	private String nombreProducto;
	private int cantidad;
	private String color;
	
	public CompraProducto() {}
	
	public CompraProducto(String claveProducto, String fecha, String nombreProducto, int cantidad, String color) {
		super();
		this.claveProducto = claveProducto;
		this.fecha = fecha;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.color = color;
	}

	public String getClaveProducto() {
		return claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
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
		return "CompraProducto [claveProducto=" + claveProducto + ", fecha=" + fecha + ", nombreProducto="
				+ nombreProducto + ", cantidad=" + cantidad + ", color=" + color + "]";
	}
	
	

}
