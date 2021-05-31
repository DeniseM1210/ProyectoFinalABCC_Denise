package modelo;

public class Proveedor {
	private int idProveedor;
	private String nombreProveedor;
	private int numTelefono;
	private String producto;
	
	public Proveedor() {}
	
	public Proveedor(int idProveedor, String nombreProveedor, int numTelefono, String producto) {
		super();
		this.idProveedor = idProveedor;
		this.nombreProveedor = nombreProveedor;
		this.numTelefono = numTelefono;
		this.producto = producto;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + ", nombreProveedor=" + nombreProveedor + ", numTelefono="
				+ numTelefono + ", producto=" + producto + "]";
	}
	
	

}
