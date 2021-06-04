package controlador;

import java.sql.ResultSet;

import conexionBD.ConexionBD2;
import modelo.Usuario;
import vista.Login;

public class UsuarioDAO implements Runnable{
	ConexionBD2 conexion;
	public String filtro;
	
	public UsuarioDAO() {
		conexion = new ConexionBD2();
	}
	
	public Usuario buscar(String filtro){
        Usuario u1= new Usuario();
        String sql="SELECT * FROM usuario WHERE nombre_usuario ='"+filtro+"';";

        ResultSet rs = conexion.ejecutarConsulta(sql);

        try {

            if(rs.next()) {
                u1.setNombre(rs.getString(1));
                u1.setContraseña(rs.getString(2));
                Login.bandera = false;
            }else {
            	Login.bandera = true;
                return null;
     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u1;
    }

	@Override
	public void run() {
		buscar(filtro);
		
	}
}
