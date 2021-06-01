package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import conexionBD.ConexionBD;
import controlador.ClienteDAO;
import controlador.CompraProductoDAO;
import controlador.EmpleadoDAO;
import controlador.ProductoDAO;
import controlador.ProveedorDAO;
import controlador.PuestosTrabajoDAO;

class Interfaz extends JFrame implements ActionListener{
	ConexionBD conexion = ConexionBD.getInstace();
	ClienteDAO clDAO = ClienteDAO.getInstance();
	CompraProductoDAO comProdDAO = CompraProductoDAO.getInstance();
	EmpleadoDAO empDAO = EmpleadoDAO.getInstance();
	ProductoDAO prodDAO = ProductoDAO.getInstance();
	ProveedorDAO provDAO = ProveedorDAO.getInstance();
	PuestosTrabajoDAO puesTrabDAO = PuestosTrabajoDAO.getInstance();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu cliente = new JMenu("Cliente");
	JMenuItem altasCli, bajasCli, cambiosCli, consultasCli;
	JInternalFrame if_altasCli, if_bajasCli, if_cambiosCli, if_consultasCli;
	
	JMenu compraProducto = new JMenu("Compra de Producto");
	JMenuItem altasCom, bajasCom, cambiosCom, consultasCom;
	JInternalFrame if_altasCom, if_bajasCom, if_cambiosCom, if_consultasCom;
	
	JMenu empleado = new JMenu("Empleado");
	JMenuItem altasEmp, bajasEmp, cambiosEmp, consultasEmp;
	JInternalFrame if_altasEmp, if_bajasEmp, if_cambiosEmp, if_consultasEmp;
	
	JMenu producto = new JMenu("Producto");
	JMenuItem altasProd, bajasProd, cambiosProd, consultasProd;
	JInternalFrame if_altasProd, if_bajasProd, if_cambiosProd, if_consultasProd;
	
	JMenu proveedor = new JMenu("Proveedor");
	JMenuItem altasProv, bajasProv, cambiosProv, consultasProv;
	JInternalFrame if_altasProv, if_bajasProv, if_cambiosProv, if_consultasProv; 
	
	JMenu puestosTrabajo = new JMenu("Puestos de Trabajo");
	JMenuItem altasPt, bajasPt, cambiosPt, consultasPt;
	JInternalFrame if_altasPt, if_bajasPt, if_cambiosPt, if_consultasPt;
	
	JInternalFrame tablaCli, tablaComPro, tablaEmp, tablaPro, tablaProv, tablaPuesTra;
	
	ImageIcon iconoAgregar = new ImageIcon("./archivos/anadir.png");
	ImageIcon iconoBorrar = new ImageIcon("./archivos/eliminar.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoEliminar = new ImageIcon("./archivos/eliminar.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	ImageIcon iconoGuardar = new ImageIcon("./archivos/guardar.png");
	
	JButton btnAgregar, btnBorrar, btnLimpiar, btnEliminar, btnBuscar, btnGuardar;
	JTextField cajaNombre, cajaidCliente, cajaCorreoE, cajaNumTel, cajaDireccion,
	cajaClaveProducto, cajaFecha, cajaNombreProd, cajaColor, cajaIdEmp, cajaRFC,
	cajaApellido, cajaIdPuesto, cajaDescripcion, cajaPrecio, cajaIdProveedor, cajaProductoProvee,
	cajaNombrePuesto;
	
	JComboBox comboCantidad, comboDisponibilidad;
	
	JTable tablaCliente, tablaCompraProducto, tablaEmpleado, tablaProducto,
	tablaProveedor, tablaPuestosTrabajo;
	JScrollPane sp = new JScrollPane();
	
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(584, 490);
		setTitle("Talabarteria Estrella");
		setLocationRelativeTo(null);
		
		tablaCli = new JInternalFrame();
		tablaCli.getContentPane().setLayout(null);
		tablaCli.setDefaultCloseOperation(HIDE_ON_CLOSE);
		tablaCli.setSize(567, 137);
		tablaCli.setLocation(0, 290);
		tablaCli.setTitle("Tabla Cliente");
		
		
		menuBar = new JMenuBar();
		cliente = new JMenu("Cliente");
			altasCli = new JMenuItem("Añadir Cliente");
			cliente.add(altasCli);
			altasCli.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			bajasCli = new JMenuItem("Eliminar Cliente");
			cliente.add(bajasCli);
			bajasCli.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			cambiosCli = new JMenuItem("Modificar Cliente");
			cliente.add(cambiosCli);
			cambiosCli.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			consultasCli = new JMenuItem("Buscar Cliente");
			cliente.add(consultasCli);
			consultasCli.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
		menuBar.add(cliente);
		setJMenuBar(menuBar);
		
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class Login extends JFrame implements ActionListener{
	JTextField cajaUsuario = new JTextField();
	JPasswordField cajaContra = new JPasswordField();
	
	JButton btnIngresar = new JButton("Ingresar");
	ImageIcon iconoUsuario = new ImageIcon("./archivos/usuario.png");
	
	public Login() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 430);
		setLocationRelativeTo(null);
		setTitle("Login");
		
		JLabel lblImg = new JLabel();
		lblImg.setBounds(70, 35, 150, 150);
		lblImg.setIcon(new ImageIcon(iconoUsuario.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		add(lblImg);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(40, 200, 200, 20);
		add(lblUsuario);
		cajaUsuario.setBounds(40, 230, 200, 20);
		add(cajaUsuario);
		
		JLabel lblContra = new JLabel("Contraseña: ");
		lblContra.setBounds(40, 260, 200, 20);
		add(lblContra);
		cajaContra.setBounds(40, 290, 200, 20);
		add(cajaContra);
		
		btnIngresar.setBounds(90, 320, 100, 20);
		btnIngresar.setBackground(Color.getHSBColor(285, 20, 100));
		btnIngresar.setForeground(Color.BLACK);
		btnIngresar.addActionListener(this);
		add(btnIngresar);
		
		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(cajaUsuario.getText().equals("") || cajaContra.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "ERROR! Faltan datos");
		}else if(cajaUsuario.getText().equals("Usuario") || cajaContra.getText().equals("12345")) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new Interfaz();
				}
			});
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "Datos incorrectos");
		}
		
	}
}


public class VentanaInicio {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Interfaz();
				
			}
		});

	}

}
