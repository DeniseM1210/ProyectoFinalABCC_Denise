package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Cliente;

class ConsultaClientes implements Runnable{
	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	String filtro;
	
	public ConsultaClientes(String filtro) {
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
					listaClientes.add(new Cliente(rs.getInt(1),
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


	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public String getFiltro() {
		return filtro;
	}


	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}

public class ClienteDAO {
	private static ClienteDAO clienteDAO = null;
	
	private ClienteDAO() {
		
	}
	
	public static synchronized ClienteDAO getInstance() {
		if(clienteDAO == null) {
			clienteDAO = new ClienteDAO();
		}
		return clienteDAO;
	}
	
	public boolean insertarCliente(Cliente c) {
		boolean resultado = false;
		resultado = ConexionBD.agregarCliente(c);
		return resultado;
	}
	
	public boolean eliminarRegistro(int idCliente) {
		boolean resultado = false;
		String sql = "DELETE FROM cliente WHERE id_cliente = " + idCliente;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarCliente(Cliente c, boolean flags[]) {
		boolean resultado = false;
		
		resultado = ConexionBD.actualizarCliente(c);
		
		return resultado;
	}
	
	public synchronized ArrayList<Cliente> buscarClientes(String filtro){
		ConsultaClientes cc = new ConsultaClientes(filtro);
		Thread h1 = new Thread(cc);
		h1.start();
		
		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cc.getListaClientes();
		
	}

}
