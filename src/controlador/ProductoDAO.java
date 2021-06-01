package controlador;

import conexionBD.ConexionBD;
import modelo.Producto;

public class ProductoDAO{
	
	private static ProductoDAO productoDAO = null;
	
	private ProductoDAO() {
		
	}
	
	public static synchronized ProductoDAO getInstance() {
		if(productoDAO == null) {
			productoDAO = new ProductoDAO();
		}
		return productoDAO;
	}
	
	public boolean insertarProducto(Producto p) {
		boolean resultado = false;
		resultado = ConexionBD.agregarProducto(p);
		return resultado;
	}
	
	

}
