package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.ConexionBD;
import modelo.PuestosTrabajo;

class ConsultaPuestosTrabajo implements Runnable{
	ArrayList<PuestosTrabajo> listaPuestosTrabajo = new ArrayList<PuestosTrabajo>();
	String filtro;
	
	public ConsultaPuestosTrabajo(String filtro) {
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
					listaPuestosTrabajo.add(new PuestosTrabajo(rs.getInt(1),
							rs.getString(2)));
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<PuestosTrabajo> getListaPuestosTrabajo() {
		return listaPuestosTrabajo;
	}

	public void setListaPuestosTrabajo(ArrayList<PuestosTrabajo> listaPuestosTrabajo) {
		this.listaPuestosTrabajo = listaPuestosTrabajo;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}


public class PuestosTrabajoDAO {
	private static PuestosTrabajoDAO puestosTrabajoDAO = null;
	
	private PuestosTrabajoDAO() {}
	
	public static synchronized PuestosTrabajoDAO getInstance() {
		if(puestosTrabajoDAO == null) {
			puestosTrabajoDAO = new PuestosTrabajoDAO();
		}
		return puestosTrabajoDAO;
	}
	
	public boolean insertarPuestoTrabajo(PuestosTrabajo pt) {
		boolean resultado = false;
		resultado = ConexionBD.agregarPuestosTrabajo(pt);
		return resultado;
	}
	
	public boolean eliminarRegistro(int idPuesto) {
		boolean resultado = false;
		String sql = "DELETE FROM puestos_trabajo WHERE id_puesto = " + idPuesto;
		resultado = ConexionBD.eliminarRegistro(sql);
		return resultado;
	}
	
	public boolean modificarPuestoTrabajo(PuestosTrabajo pt, boolean flags[]) {
		boolean resultado = false;
		resultado = ConexionBD.actualizarPuestosTrabajo(pt);
		
		return resultado;
	}
	
	public synchronized ArrayList<PuestosTrabajo> buscarPuestosTrabajo(String filtro){
		ConsultaPuestosTrabajo cpt = new ConsultaPuestosTrabajo(filtro);
		Thread h1 = new Thread(cpt);
		h1.start();
		
		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cpt.getListaPuestosTrabajo();
	}

}
