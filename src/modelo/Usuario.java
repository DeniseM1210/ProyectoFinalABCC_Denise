package modelo;

public class Usuario {
	private String nombre;
	private String contrase�a;
	
	public Usuario() {}
	
	public Usuario(String nombre, String contrase�a) {
		super();
		this.nombre = nombre;
		this.contrase�a = contrase�a;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
}