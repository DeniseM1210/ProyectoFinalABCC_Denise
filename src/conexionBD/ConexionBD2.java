package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD2 {
	private Connection conexion;
	private Statement stn;
	private ResultSet rs;
	
	public ConexionBD2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String URL = "jdbc:mysql://localhost:3306/Talabarteria";
			
			conexion = DriverManager.getConnection(URL, "root", "nanami777");
		
			//System.out.println("Conexion establecida");
		} catch (ClassNotFoundException e) {
			System.out.println("Error de DRIVER");
		} catch (SQLException e) {
			System.out.println("Error de conexion a MySQL o de la BD");
		}
	}
	
	public void cerrarConexionBD() {
		try {
			stn.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
			e.printStackTrace();
		}
	}
	
	public boolean ejecutarInstruccion(String sql) {
		try {
			stn = conexion.createStatement();
			int resultado = stn.executeUpdate(sql);
			return resultado == 1 ? true: false;
		} catch (SQLException e) {
			System.out.println("No se pudo ejecutar la instruccion");
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet ejecutarConsulta(String sql) {
		
		try {
			stn = conexion.createStatement();
			rs = stn.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("No se pudo ejecutar la instruccion");
			e.printStackTrace();
		}
		return rs;
	}
	
	
}
