package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD2;
import modelo.Cliente;
import vista.VentanaInicio;

public class ClienteDAO{
	ConexionBD2 conexion;
	
	public ClienteDAO() {
		conexion = new ConexionBD2();
	}
	
	public boolean insertarCliente(Cliente c) {
		boolean resultado = false;
		String sql = "INSERT INTO cliente VALUES("+ c.getIdCliente() + ",'"+c.getNombre()+"','"
		+c.getCorreoE()+"','"+c.getNumTel()+"','"+c.getDireccion()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	public boolean eliminarRegistro(int idCliente) {
		boolean resultado = false;
		String sql = "DELETE FROM cliente WHERE id_cliente = \"" + idCliente + "\"";
		resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	public boolean modificarCliente(Cliente c) {
		boolean resultado = false;
		
		String sql = "UPDATE cliente SET nombre = '" + c.getNombre() + "', correo_electronico = '" + c.getCorreoE() +"', num_telefono = '"+
		c.getNumTel() + "', direccion = '" + c.getDireccion() + "' WHERE id_cliente = " + c.getIdCliente() + "";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
}
