package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import conexionBD.ConexionBD;
import controlador.ClienteDAO;
import controlador.CompraProductoDAO;
import controlador.EmpleadoDAO;
import controlador.ProductoDAO;
import controlador.ProveedorDAO;
import controlador.PuestosTrabajoDAO;

class Interfaz extends JFrame{
	ConexionBD conexion = ConexionBD.getInstace();
	ClienteDAO clDAO = ClienteDAO.getInstance();
	CompraProductoDAO comProdDAO = CompraProductoDAO.getInstance();
	EmpleadoDAO empDAO = EmpleadoDAO.getInstance();
	ProductoDAO prodDAO = ProductoDAO.getInstance();
	ProveedorDAO provDAO = ProveedorDAO.getInstance();
	PuestosTrabajoDAO puesTrabDAO = PuestosTrabajoDAO.getInstance();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu cliente = new JMenu("Cliente");
	JMenu compraProducto = new JMenu("Compra de Producto");
	JMenu empleado = new JMenu("Empleado");
	JMenu producto = new JMenu("Producto");
	JMenu proveedor = new JMenu("Proveedor");
	JMenu puestosTrabajo = new JMenu("Puestos de Trabajo");
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(584, 490);
		setTitle("Talabarteria");
		setLocationRelativeTo(null);
		
		
		
		setVisible(true);
	}
	
}

class Login extends JFrame{
	JTextField txtUsuario = new JTextField();
	JPasswordField txtContra = new JPasswordField();
	
	JButton ingresar = new JButton("Ingresar");
	ImageIcon iconoUsuario = new ImageIcon("./archivos/usuario.png");
	
	public Login() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 430);
		setLocationRelativeTo(null);
		setTitle("Login");
		
		
		
		setVisible(true);
	}
}


public class VentanaInicio {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Login();
				
			}
		});

	}

}
