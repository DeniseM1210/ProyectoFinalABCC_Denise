package modelo;

public class CompraProducto {
	private int claveProducto;
	private String fecha;
	private String nombreProducto;
	private byte cantidad;
	private String color;
	
	public CompraProducto() {}
	
	public CompraProducto(int claveProducto, String fecha, String nombreProducto, byte cantidad, String color) {
		super();
		this.claveProducto = claveProducto;
		this.fecha = fecha;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.color = color;
	}

	public int getClaveProducto() {
		return claveProducto;
	}

	public void setClaveProducto(int claveProducto) {
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

	public byte getCantidad() {
		return cantidad;
	}

	public void setCantidad(byte cantidad) {
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
