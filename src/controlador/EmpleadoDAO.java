package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.Empleado;

class ConsultaEmpleados implements Runnable{
	ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
	String filtro;
	
	public ConsultaEmpleados(String filtro) {
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
					listaEmpleados.add(new Empleado(rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}



	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}



	public String getFiltro() {
		return filtro;
	}



	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
}

public class EmpleadoDAO {
	private static EmpleadoDAO empleadoDAO = null;
	
	private EmpleadoDAO() {}
	
	public static synchronized EmpleadoDAO getInstance() {
		if(empleadoDAO == null) {
			empleadoDAO = new EmpleadoDAO();
		}
		return empleadoDAO;
	}
	
	public boolean insertarEmpleado(Empleado e) {
		boolean resultado = false;
		resultado = ConexionBD.agregarEmpleado(e);
		return resultado;
	}
	
	public boolean eliminarRegistro(int idEmpleado) {
		boolean resultado = false;
		String sql = "DELETE FROM empleado WHERE id_empleado = " + idEmpleado;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarEmpleado(Empleado e, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarEmpleado(e);
		
		return resultado;
	}
	
	public synchronized ArrayList<Empleado> buscarEmpleados(String filtro){
		ConsultaEmpleados ce = new ConsultaEmpleados(filtro);
		Thread h1 = new Thread(ce);
		h1.start();
		
		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ce.getListaEmpleados();
	}
	
	

}
