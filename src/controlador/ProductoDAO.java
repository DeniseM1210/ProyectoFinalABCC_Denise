package controlador;

import conexionBD.ConexionBD;

public class ProductoDAO implements Runnable{
	
	ConexionBD conexion;
	
	public ProductoDAO() {
		conexion = new ConexionBD();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	

}
