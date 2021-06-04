package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD2;
import modelo.CompraProducto;


public class CompraProductoDAO implements Runnable {
	ConexionBD2 conexion;
	
	public CompraProductoDAO() {
		conexion = new ConexionBD2();
	}
	
	public boolean insertarCompraProducto(CompraProducto cp) {
		boolean resultado = false;
		String sql = "INSERT INTO compra_producto VALUES("+ cp.getClaveProducto() + ",'"+cp.getFecha()+"','"
				+cp.getNombreProducto()+"',"+cp.getCantidad()+",'"+cp.getColor()+"');";
				resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	public boolean eliminarRegistro(int claveProducto) {
		boolean resultado = false;
		String sql = "DELETE FROM compra_producto WHERE clave_producto = \"" + claveProducto + "\"";
		resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	public boolean modificarCompraProducto(CompraProducto cp) {
		boolean resultado = false;
		String sql = "UPDATE compra_producto SET fecha = '" + cp.getFecha() + "', nombre_producto = '" + cp.getNombreProducto() +"', cantidad = "+
				cp.getCantidad() + ", color = '" + cp.getColor() + "' WHERE clave_producto = " + cp.getClaveProducto() + "";
				resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
		}
	
	@Override
	public void run() {
		
		
	}
	
	
}
