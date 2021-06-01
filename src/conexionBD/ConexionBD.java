package conexionBD;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cliente;
import modelo.CompraProducto;
import modelo.Empleado;
import modelo.Producto;
import modelo.Proveedor;
import modelo.PuestosTrabajo;

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
	
	public static boolean actualizarProducto(Producto a) {
		try {
			pstm = conexion.prepareStatement("UPDATE producto SET descripcion = ?, precio = ?, disponibilidad = ? where  clave_producto = '" + a.getClave() + "'");
			pstm.setString(1, a.getDescripcion());
			pstm.setDouble(2, a.getPrecio());
			pstm.setString(3, a.getDisponibilidad());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		return false;
		
	}
	
	public static boolean actualizarCliente(Cliente c) {
		
		try {
			pstm = conexion.prepareStatement("UPDATE cliente SET correo_electronico = ?, num_telefono = ?, direccion = ? WHERE id_cliente = '" + c.getIdCliente() + "'" );
			pstm.setString(1, c.getNombre());
			pstm.setString(2, c.getCorreoE());
			pstm.setInt(3, c.getNumTel());
			pstm.setString(4, c.getDireccion());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean actualizarEmpleado(Empleado e) {
		
		try {
			pstm = conexion.prepareStatement("UPDATE empleado set RFC_empleado = ?, nombre = ?, apellidos = ?, id_puesto = ? WHERE id_empleado =  '" + e.getIdEmpleado() + "'");
			pstm.setString(1, e.getRFCEmpleado());
			pstm.setString(2, e.getNombre());
			pstm.setString(3, e.getApellidos());
			pstm.setInt(4, e.getIdPuesto());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e1) {
			
		}
		
		return false;
	}
	
	public static boolean actualizarCompraProducto(CompraProducto cp) {
		
		try {
			pstm = conexion.prepareStatement("UPDATE compra_producto set fecha = ?, nombre_producto = ?, cantidad = ?, color = ? WHERE clave_producto = '" + cp.getClaveProducto() + "'");
			pstm.setString(1, cp.getFecha());
			pstm.setString(2, cp.getNombreProducto());
			pstm.setInt(3, cp.getCantidad());
			pstm.setString(4, cp.getColor());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean actualizarProveedor(Proveedor p) {
		
		try {
			pstm = conexion.prepareStatement("UPDATE proveedor set nombre_proveedor = ?, num_telefono = ?, producto_provee = ?, cantidad_piezas = ? WHERE idProveedor =  '"+ p.getIdProveedor() + "'");
			pstm.setString(1, p.getNombreProveedor());
			pstm.setInt(2, p.getNumTelefono());
			pstm.setString(3, p.getProducto());
			pstm.setInt(4, p.getCantidadPiezas());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean actualizarPuestosTrabajo(PuestosTrabajo pt) {
		
		try {
			pstm = conexion.prepareStatement("UPDATE puestos_trabajo set nombre_puesto = ? WHERE id_puesto = '" + pt.getIdPuesto() + "'");
			pstm.setString(1, pt.getNombrePuesto());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean agregarCliente(Cliente c) {
		
		try {
			pstm = conexion.prepareStatement("INSERT INTO cliente VALUES(" + c.getIdCliente()+ ", ?, ?, ?, ?)");
			pstm.setString(1, c.getNombre());
			pstm.setString(2, c.getCorreoE());
			pstm.setInt(3, c.getNumTel());
			pstm.setString(4, c.getDireccion());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean agregarCompraProducto(CompraProducto cp) {
		
		try {
			pstm = conexion.prepareStatement("INSERT INTO compra_producto VALUES(" + cp.getClaveProducto() + ", ?, ?, ?, ?)");
			pstm.setString(1, cp.getFecha());
			pstm.setString(2, cp.getNombreProducto());
			pstm.setInt(3, cp.getCantidad());
			pstm.setString(4, cp.getColor());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean agregarEmpleado(Empleado e) {
		
		try {
			pstm = conexion.prepareStatement("INSERT INTO empleado VALUES(" + e.getIdEmpleado() + ", ?, ?, ?, ?)");
			pstm.setString(1, e.getRFCEmpleado());
			pstm.setString(2, e.getNombre());
			pstm.setString(3, e.getApellidos());
			pstm.setInt(4, e.getIdPuesto());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e1) {
			
		}
		
		return false;
	}
	
	public static boolean agregarProducto(Producto p) {
		
		try {
			pstm = conexion.prepareStatement("INSERT INTO producto VALUES(" + p.getClave() + ", ?, ?, ?)");
			pstm.setString(1, p.getDescripcion());
			pstm.setDouble(2, p.getPrecio());
			pstm.setString(3, p.getDisponibilidad());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean agregarProveedor(Proveedor p) {
		
		try {
			pstm = conexion.prepareStatement("INSERT INTO proveedor VALUES(" + p.getIdProveedor() + ", ?, ?, ?, ?) ");
			pstm.setString(1, p.getNombreProveedor());
			pstm.setInt(2, p.getNumTelefono());
			pstm.setString(3, p.getProducto());
			pstm.setInt(4, p.getCantidadPiezas());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public static boolean agregarPuestosTrabajo(PuestosTrabajo pt) {
		
		try {
			pstm = conexion.prepareStatement("INSERT INTO puestos_trabajo VALUES(" + pt.getIdPuesto() + "?)");
			pstm.setString(1, pt.getNombrePuesto());
			
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
