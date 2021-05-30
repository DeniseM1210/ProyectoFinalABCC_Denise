package conexionBD;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			
			System.out.println("Conexión establecida");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error de DRIVER");
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
	
	public static void main(String[] args) {
	new ConexionBD();
}

}
