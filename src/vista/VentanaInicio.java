package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
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


class AltasCliente extends JInternalFrame{
	JTextField cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion;
	JButton btnAgregar, btnBorrar, btnCancelar;
	
	ImageIcon iconoAgregar = new ImageIcon("./archivos/anadir.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	
	JTable tablaClientes = new JTable(4, 4);
	
	public AltasCliente() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,290);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Altas Clientes");
		
		JLabel lblTexto = new JLabel("Altas Clientes");
		lblTexto.setBounds(200, 20, 200, 20);
		lblTexto.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblTexto);
		
		JLabel lblIdCliente = new JLabel("Id Cliente: ");
		lblIdCliente.setBounds(20, 50, 100, 20);
		add(lblIdCliente);
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(20, 80, 100, 20);
		add(lblNombre);
		JLabel lblCorreo = new JLabel("Correo Electronico: ");
		lblCorreo.setBounds(20, 110, 150, 20);
		add(lblCorreo);
		JLabel lblNumTel = new JLabel("Num. Telefono: ");
		lblNumTel.setBounds(20, 140, 100, 20);
		add(lblNumTel);
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(20, 170, 100, 20);
		add(lblDireccion);
		
		cajaidCliente = new JTextField();
		cajaidCliente.setBounds(90, 50, 200, 20);
		add(cajaidCliente);
		cajaNombre = new JTextField();
		cajaNombre.setBounds(90, 80, 200, 20);
		add(cajaNombre);
		cajaCorreo = new JTextField();
		cajaCorreo.setBounds(130, 110, 160, 20);
		add(cajaCorreo);
		cajaNumTel = new JTextField();
		cajaNumTel.setBounds(110, 140, 180, 20);
		add(cajaNumTel);
		cajaDireccion = new JTextField();
		cajaDireccion.setBounds(90, 170, 200, 20);
		add(cajaDireccion);
		
		btnAgregar = new JButton(iconoAgregar);
		btnAgregar.setIcon(new ImageIcon(iconoAgregar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnAgregar.setBounds(380, 30, 50, 50);
		btnAgregar.setBackground(Color.LIGHT_GRAY);
		add(btnAgregar);
		
		btnBorrar = new JButton(iconoLimpiar);
		btnBorrar.setIcon(new ImageIcon(iconoLimpiar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBorrar.setBounds(380, 90, 50, 50);
		btnBorrar.setBackground(Color.LIGHT_GRAY);
		add(btnBorrar);
		
		btnCancelar = new JButton(iconoCancelar);
		btnCancelar.setIcon(new ImageIcon(iconoCancelar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnCancelar.setBounds(380, 150, 50, 50);
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		add(btnCancelar);
		
		setVisible(false);
	}
}

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
	
	JMenu compraProducto = new JMenu("Compra de Producto");
	JMenuItem altasCom, bajasCom, cambiosCom, consultasCom;
	
	JMenu empleado = new JMenu("Empleado");
	JMenuItem altasEmp, bajasEmp, cambiosEmp, consultasEmp;
	
	JMenu producto = new JMenu("Producto");
	JMenuItem altasProd, bajasProd, cambiosProd, consultasProd;
	
	JMenu proveedor = new JMenu("Proveedor");
	JMenuItem altasProv, bajasProv, cambiosProv, consultasProv;
	
	JMenu puestosTrabajo = new JMenu("Puestos de Trabajo");
	JMenuItem altasPt, bajasPt, cambiosPt, consultasPt;
	
	ImageIcon iconoEliminar = new ImageIcon("./archivos/eliminar.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	ImageIcon iconoGuardar = new ImageIcon("./archivos/guardar.png");
	
	JDesktopPane dp = new JDesktopPane();
	
	
	public Interfaz() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(584, 490);
		setTitle("Talabarteria Estrella");
		setLocationRelativeTo(null);
		
		
		
	
		menuBar = new JMenuBar();
		cliente = new JMenu("Cliente");
			altasCli = new JMenuItem("Añadir Cliente");
			cliente.add(altasCli);
			altasCli.addActionListener(this);
	
			bajasCli = new JMenuItem("Eliminar Cliente");
			cliente.add(bajasCli);
			bajasCli.addActionListener(this);
			
			cambiosCli = new JMenuItem("Modificar Cliente");
			cliente.add(cambiosCli);
			cambiosCli.addActionListener(this);
			
			consultasCli = new JMenuItem("Buscar Cliente");
			cliente.add(consultasCli);
			consultasCli.addActionListener(this);
		
		compraProducto = new JMenu("Compra de Producto");
			altasCom = new JMenuItem("Agregar Compra");
			compraProducto.add(altasCom);
			altasCom.addActionListener(this);
			
			bajasCom = new JMenuItem("Eliminar Compra");
			compraProducto.add(bajasCom);
			bajasCom.addActionListener(this);
			
			cambiosCom = new JMenuItem("Modificar Compra");
			compraProducto.add(cambiosCom);
			cambiosCom.addActionListener(this);
			
			consultasCom = new JMenuItem("Buscar Compra");
			compraProducto.add(consultasCom);
			consultasCom.addActionListener(this);
			
		empleado = new JMenu("Empleado");
			altasEmp = new JMenuItem("Agregar Empleado");
			empleado.add(altasEmp);
			altasEmp.addActionListener(this);
			
			bajasEmp = new JMenuItem("Eliminar Empleado");
			empleado.add(bajasEmp);
			bajasEmp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			cambiosEmp = new JMenuItem("Modificar Empleado");
			empleado.add(cambiosEmp);
			cambiosEmp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			consultasEmp = new JMenuItem("Buscar Empleado");
			empleado.add(consultasEmp);
			consultasEmp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		puestosTrabajo = new JMenu("Puestos de Trabajo");
			altasPt = new JMenuItem("Agregar Puesto de Trabajo");
			puestosTrabajo.add(altasPt);
			altasPt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			bajasPt = new JMenuItem("Eliminar Puesto de Trabajo");
			puestosTrabajo.add(bajasPt);
			bajasPt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			cambiosPt = new JMenuItem("Modificar Puesto de Trabajo");
			puestosTrabajo.add(cambiosPt);
			cambiosPt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			consultasPt = new JMenuItem("Buscar Puesto de Trabajo");
			puestosTrabajo.add(consultasPt);
			consultasPt.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		producto = new JMenu("Productos");
			altasProd = new JMenuItem("Agregar Producto");
			producto.add(altasProd);
			altasProd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			bajasProd = new JMenuItem("Eliminar Producto");
			producto.add(bajasProd);
			bajasProd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			cambiosProd = new JMenuItem("Modificar Producto");
			producto.add(cambiosProd);
			cambiosProd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			consultasProd = new JMenuItem("Buscar Producto");
			producto.add(consultasProd);
			consultasProd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		proveedor = new JMenu("Proveedores");
			altasProv = new JMenuItem("Agregar Proveedor");
			proveedor.add(altasProv);
			altasProv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			bajasProv = new JMenuItem("Eliminar Proveedor");
			proveedor.add(bajasProv);
			bajasProv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			cambiosProv = new JMenuItem("Modificar Proveedor");
			proveedor.add(cambiosProv);
			cambiosProv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			consultasProv = new JMenuItem("Buscar Proveedor");
			proveedor.add(consultasProv);
			consultasProv.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		menuBar.add(cliente);
		menuBar.add(compraProducto);
		menuBar.add(empleado);
		menuBar.add(puestosTrabajo);
		menuBar.add(producto);
		menuBar.add(proveedor);
		setJMenuBar(menuBar);
		
		dp.setBounds(0, 0, 567, 425);
		add(dp);
		
		setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == altasCli) {
			AltasCliente ac = new AltasCliente();
			dp.add(ac);
			ac.setVisible(true);
			add(dp);
		}
		
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
