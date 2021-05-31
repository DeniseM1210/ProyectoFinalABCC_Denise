package conexionBD;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Producto;

public class ConexionBD {

	private static ConexionBD conexionBD;
	private static Connection conexion = null;
	
	private  static PreparedStatement pstm;
	private static ResultSet rs;
	
	public ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/Talabarteria";
			
			conexion = DriverManager.getConnection(URL, "root", "nanami777");
		
			System.out.println("Conexion establecida");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de DRIVER");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error de conexion a MySQL o de la BD");
		}
	}
	
	public static Connection getConexion() {
		if(conexion == null) {
			new ConexionBD();
		}
		
		return conexion;
	}
	
	static void cerrarConexion() {
		try {
			pstm.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
			e.printStackTrace();
		}
	}
	
	public static boolean ActualizarProducto(Producto a) {
		try {
			pstm = conexion.prepareStatement("UPDATE Producto SET descripcion = ?, precio = ?, disponibilidad = ? where  clave_producto = " + a.getClave() + "");
			pstm.setString(1, a.getDescripcion());
			pstm.setDouble(2, a.getPrecio());
			pstm.setString(3, a.getDisponibilidad());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		return false;
		
	}
	
	public static void main(String[] args) {
	new ConexionBD();
}

}
