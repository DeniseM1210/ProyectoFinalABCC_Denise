package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

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
import controlador.ProductoDAO;
import modelo.Cliente;


class AltasCliente extends JInternalFrame implements ActionListener{
	JTextField cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion;
	JButton btnAgregar, btnBorrar, btnCancelar;
	
	ImageIcon iconoAgregar = new ImageIcon("./archivos/anadir.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	
	JTable tablaClientes = new JTable(5,5);
	
	public AltasCliente() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
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
		btnAgregar.addActionListener(this);
		add(btnAgregar);
		
		btnBorrar = new JButton(iconoLimpiar);
		btnBorrar.setIcon(new ImageIcon(iconoLimpiar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBorrar.setBounds(380, 90, 50, 50);
		btnBorrar.setBackground(Color.LIGHT_GRAY);
		btnBorrar.addActionListener(this);
		add(btnBorrar);
		
		btnCancelar = new JButton(iconoCancelar);
		btnCancelar.setIcon(new ImageIcon(iconoCancelar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnCancelar.setBounds(380, 150, 50, 50);
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(this);
		add(btnCancelar);
		
		actualizarTabla();
		JScrollPane sp = new JScrollPane(tablaClientes);
		sp.setBounds(0, 250, 567, 100);
		add(sp);
		
		//Validacion
		cajaidCliente.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		cajaNombre.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if(Character.isLetter(car) || Character.isSpaceChar(car)) {
				}else {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		cajaNumTel.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAgregar) {
			if(validarCajasVacias()) {
				int id = Integer.parseInt(cajaidCliente.getText());
				long numTel = Long.parseLong(cajaNumTel.getText());
				Cliente c = new Cliente(id, cajaNombre.getText(), cajaCorreo.getText(), numTel, cajaDireccion.getText());
				if(ConexionBD.agregarCliente(c)) {
					JOptionPane.showMessageDialog(null, "Se agrego el cliente correctamente");
					actualizarTabla();
					reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
				}else {
					JOptionPane.showMessageDialog(rootPane, "No se agrego el cliente", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
			}
		}else if(e.getSource() == btnBorrar) {
			reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
		}
		
	}
	
	public void actualizarTabla() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM cliente";
		
		ResultSetTableModel modeloDatos = null;
		
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, consulta);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablaClientes.setModel(modeloDatos);
	}
	
	public void reestablecer(Component...componentes) {
		for(Component Component : componentes) {
			if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}
		}
	}
	
	public boolean validarCajasVacias() {
		if(cajaidCliente.getText().isEmpty()) {
			return false;
		}else if(cajaNombre.getText().isEmpty()) {
			return false;
		}else if(cajaCorreo.getText().isEmpty()) {
			return false;
		}else if(cajaNumTel.getText().isEmpty()) {
			return false;
		}else if(cajaDireccion.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
}//Class altasClientes

class BajasClientes extends JFrame implements ActionListener{
	JTextField cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion;
	JButton btnEliminar, btnBorrar, btnCancelar;
	
	ImageIcon iconoEliminar = new ImageIcon("./archivos/eliminar.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	ImageIcon iconoBorrarUsu = new ImageIcon("./archivos/borrarUsu.png");
	
	JTable tablaClientes = new JTable(5,5);
	
	public BajasClientes() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Bajas Clientes");
		
		JLabel lblImagen = new JLabel(iconoBorrarUsu);
		lblImagen.setBounds(150, 20, 30, 30);
		lblImagen.setIcon(new ImageIcon(iconoBorrarUsu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		add(lblImagen);
		
		JLabel lblTexto = new JLabel("Bajas Clientes");
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
		
		btnEliminar = new JButton(iconoAgregar);
		btnEliminar.setIcon(new ImageIcon(iconoAgregar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnEliminar.setBounds(380, 30, 50, 50);
		btnAgregar.setBackground(Color.LIGHT_GRAY);
		btnAgregar.addActionListener(this);
		add(btnAgregar);
		
		btnBorrar = new JButton(iconoLimpiar);
		btnBorrar.setIcon(new ImageIcon(iconoLimpiar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBorrar.setBounds(380, 90, 50, 50);
		btnBorrar.setBackground(Color.LIGHT_GRAY);
		btnBorrar.addActionListener(this);
		add(btnBorrar);
		
		btnCancelar = new JButton(iconoCancelar);
		btnCancelar.setIcon(new ImageIcon(iconoCancelar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnCancelar.setBounds(380, 150, 50, 50);
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(this);
		add(btnCancelar);
		
		actualizarTabla();
		JScrollPane sp = new JScrollPane(tablaClientes);
		sp.setBounds(0, 250, 567, 100);
		add(sp);
		
		//Validacion
		cajaidCliente.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		cajaNombre.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if(Character.isLetter(car) || Character.isSpaceChar(car)) {
				}else {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		cajaNumTel.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAgregar) {
			if(validarCajasVacias()) {
				int id = Integer.parseInt(cajaidCliente.getText());
				long numTel = Long.parseLong(cajaNumTel.getText());
				Cliente c = new Cliente(id, cajaNombre.getText(), cajaCorreo.getText(), numTel, cajaDireccion.getText());
				if(ConexionBD.agregarCliente(c)) {
					JOptionPane.showMessageDialog(null, "Se agrego el cliente correctamente");
					actualizarTabla();
					reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
				}else {
					JOptionPane.showMessageDialog(rootPane, "No se agrego el cliente", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
			}
		}else if(e.getSource() == btnBorrar) {
			reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
		}
		
	}
	
	public void actualizarTabla() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM cliente";
		
		ResultSetTableModel modeloDatos = null;
		
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, consulta);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablaClientes.setModel(modeloDatos);
	}
	
	public void reestablecer(Component...componentes) {
		for(Component Component : componentes) {
			if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}
		}
	}
	
	public boolean validarCajasVacias() {
		if(cajaidCliente.getText().isEmpty()) {
			return false;
		}else if(cajaNombre.getText().isEmpty()) {
			return false;
		}else if(cajaCorreo.getText().isEmpty()) {
			return false;
		}else if(cajaNumTel.getText().isEmpty()) {
			return false;
		}else if(cajaDireccion.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
}

class Interfaz extends JFrame implements ActionListener{
	ConexionBD conexion = ConexionBD.getInstace();
	ClienteDAO clDAO = ClienteDAO.getInstance();
	CompraProductoDAO comProdDAO = CompraProductoDAO.getInstance();
	ProductoDAO prodDAO = ProductoDAO.getInstance();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu cliente = new JMenu("Cliente");
	JMenuItem altasCli, bajasCli, cambiosCli, consultasCli;
	
	JMenu compraProducto = new JMenu("Compra de Producto");
	JMenuItem altasCom, bajasCom, cambiosCom, consultasCom;
	
	JMenu producto = new JMenu("Producto");
	JMenuItem altasProd, bajasProd, cambiosProd, consultasProd;
	
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
			altasCli = new JMenuItem("A�adir Cliente");
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
		
		menuBar.add(cliente);
		menuBar.add(compraProducto);
		menuBar.add(producto);
		setJMenuBar(menuBar);
		
		dp.setBounds(0, 0, 567, 490);
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
		
		JLabel lblContra = new JLabel("Contrase�a: ");
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
