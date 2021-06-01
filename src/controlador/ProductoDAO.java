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
	
	public boolean eliminarRegistro(int idProducto) {
		boolean resultado = false;
		String sql = "DELETE FROM producto WHERE clave_producto = " + idProducto;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}

}
