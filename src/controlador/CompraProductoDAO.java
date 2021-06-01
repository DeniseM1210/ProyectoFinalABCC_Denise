package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.CompraProducto;

class ConsultaCompraProductos implements Runnable{
	ArrayList<CompraProducto> listaCompraProductos = new ArrayList<CompraProducto>();
	String filtro;
	
	public ConsultaCompraProductos(String filtro) {
		this.filtro = filtro;
	}



	@Override
	public void run() {
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		
		try {
			if(rs.next()) {
				do {
					listaCompraProductos.add(new CompraProducto(rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getString(5)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<CompraProducto> getListaCompraProductos() {
		return listaCompraProductos;
	}


	public void setListaCompraProductos(ArrayList<CompraProducto> listaCompraProductos) {
		this.listaCompraProductos = listaCompraProductos;
	}



	public String getFiltro() {
		return filtro;
	}



	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}

public class CompraProductoDAO {
	
	private static CompraProductoDAO compraProductoDAO = null;
	
	private CompraProductoDAO() {}
	
	public static synchronized CompraProductoDAO getInstance() {
		if(compraProductoDAO == null) {
			compraProductoDAO = new CompraProductoDAO();
		}
		return compraProductoDAO;
	}
	
	public boolean insertarCompraProducto(CompraProducto cp) {
		boolean resultado = false;
		resultado = ConexionBD.agregarCompraProducto(cp);
		return resultado;
	}
	
	public boolean eliminarRegistro(int claveProducto) {
		boolean resultado = false;
		String sql = "DELETE FROM compra_producto WHERE clave_producto = " + claveProducto;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarCompraProducto(CompraProducto cp, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarCompraProducto(cp);
		
		return resultado;
		}
	
	public synchronized ArrayList<CompraProducto> buscarCompraProductos(String filtro){
		ConsultaCompraProductos ccp = new ConsultaCompraProductos(filtro);
		Thread h1 = new Thread(ccp);
		h1.start();
		
		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ccp.getListaCompraProductos();
	}
}
