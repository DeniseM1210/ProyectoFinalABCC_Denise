package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Producto;

class ConsultaProductos implements Runnable{
	ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	String filtro;
	
	public ConsultaProductos(String filtro) {
		this.filtro = filtro;
	}

	@Override
	public void run() {
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		
		try {
			if(rs.next()) {
				do {
					listaProductos.add(new Producto(rs.getString(1),
							rs.getString(2),
							rs.getInt(3), 
							rs.getString(4)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
}

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
