package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Proveedor;

class ConsultaProveedores implements Runnable{
	ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();
	String filtro; 
	
	public ConsultaProveedores(String filtro) {
		super();
		this.filtro = filtro;
	}


	@Override
	public void run() {
		ResultSet rs;
		
		rs = ConexionBD.ejecutarConsulta(filtro);
		
		try {
			if(rs.next()) {
				do {
					listaProveedores.add(new Proveedor(rs.getInt(1),
							rs.getString(2),
							rs.getInt(3),
							rs.getString(4),
							rs.getInt(5)));
				}while(rs.next());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public ArrayList<Proveedor> getListaProveedores() {
		return listaProveedores;
	}


	public void setListaProveedores(ArrayList<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}


	public String getFiltro() {
		return filtro;
	}


	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}

public class ProveedorDAO {
	private static ProveedorDAO proveedorDAO = null;
	
	private ProveedorDAO() {}
	
	public static synchronized ProveedorDAO getInstance() {
		if(proveedorDAO == null) {
			proveedorDAO = new ProveedorDAO();
		}
		return proveedorDAO;
	}
	
	public boolean insertarProveedor(Proveedor p) {
		boolean resultado = false;
		resultado = ConexionBD.agregarProveedor(p);
		return resultado;
	}
	
	public boolean eliminarRegistro(int idProveedor) {
		boolean resultado = false;
		String sql = "DELE FROM proveedor WHERE idProveedor = " + idProveedor;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarProveedor(Proveedor p, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarProveedor(p);
		
		return resultado;
	}
	
	public synchronized ArrayList<Proveedor> buscarProveedor(String filtro){
		ConsultaProveedores cp = new ConsultaProveedores(filtro);
		Thread h1 = new Thread(cp);
		h1.start();
		
		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cp.getListaProveedores();
	}

}


