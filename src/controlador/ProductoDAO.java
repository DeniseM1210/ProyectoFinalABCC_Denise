package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD2;
import modelo.Producto;

public class ProductoDAO implements Runnable{
	ConexionBD2 conexion;
	private int filtro;
	
	public ProductoDAO() {
		conexion = new ConexionBD2();
	}
		
	public boolean insertarProducto(Producto p) {
		boolean resultado = false;

		String sql = "INSERT INTO producto VALUES("+ p.getClave() + ",'"+ p.getDescripcion()+"',"
				+p.getPrecio()+",'"+p.getDisponibilidad()+"');";
		resultado = conexion.ejecutarInstruccion(sql);

		return resultado;
	}
	
	public boolean eliminarRegistro(int idProducto) {
		boolean resultado = false;
		String sql = "DELETE FROM producto WHERE clave_producto = \"" + idProducto + "\"";
		resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	public boolean modificarProducto(Producto p) {
		boolean resultado = false;
		String sql = "UPDATE producto SET descripcion = '" + p.getDescripcion() + "', precio = " + p.getPrecio() +", disponibilidad = '"+
				p.getDisponibilidad() + "' WHERE clave_producto = " + p.getClave() + "";
				resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	

	@Override
	public void run() {
		
	}
	

}
