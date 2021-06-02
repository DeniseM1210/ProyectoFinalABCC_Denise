package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Cliente;
import vista.VentanaInicio;

public class ClienteDAO implements Runnable {
	private int filtro;

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
	
	public Cliente buscarClientes(int filtro){
		Cliente c = new Cliente();
		String sql = "SELECT * FROM cliente WHERE id_cliente = " + filtro + ";";
		
		ResultSet rs = ConexionBD.ejecutarConsulta(sql);
		
		try {
			if(rs.next()) {
				c.setIdCliente(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setCorreoE(rs.getString(3));
				c.setNumTel(rs.getString(4));
				c.setDireccion(rs.getString(5));
			}else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}

	public int getFiltro() {
		return filtro;
	}
	
	@Override
	public void run() {
		buscarClientes(this.filtro);
		
	}

	public void setFiltro(int filtro) {
		this.filtro = filtro;
	}

}
