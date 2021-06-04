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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.ProductoDAO;
import modelo.Producto;

class BajasProducto extends JInternalFrame implements ActionListener{
	JTextField cajaClave, cajaDescripcion, cajaPrecio, cajaDispo;
	JButton btnEliminar, btnBorrar, btnCancelar, btnBuscar;
	
	ImageIcon iconoEliminar = new ImageIcon("./archivos/eliminar.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	ImageIcon iconoBorrarUsu = new ImageIcon("./archivos/borrarUsu.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	
	JTable tablaProductos = new JTable(4,5);
	
	public BajasProducto() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Bajas Producto");
		
		JLabel lblTexto = new JLabel("Bajas Producto");
		lblTexto.setBounds(200, 20, 200, 20);
		lblTexto.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblTexto);
		
		JLabel lblIdCliente = new JLabel("Clave Producto: ");
		lblIdCliente.setBounds(20, 80, 100, 20);
		add(lblIdCliente);
		JLabel lblNombre = new JLabel("Descripcion: ");
		lblNombre.setBounds(20, 110, 100, 20);
		add(lblNombre);
		JLabel lblCorreo = new JLabel("Precio: ");
		lblCorreo.setBounds(20, 140, 150, 20);
		add(lblCorreo);
		JLabel lblNumTel = new JLabel("Disponibilidad: ");
		lblNumTel.setBounds(20, 170, 100, 20);
		add(lblNumTel);
		
		cajaClave = new JTextField();
		cajaClave.setBounds(110, 80, 200, 20);
		add(cajaClave);
		cajaDescripcion = new JTextField();
		cajaDescripcion.setBounds(100, 110, 210, 20);
		add(cajaDescripcion);
		cajaPrecio = new JTextField();
		cajaPrecio.setBounds(70, 140, 240, 20);
		add(cajaPrecio);
		cajaDispo = new JTextField();
		cajaDispo.setBounds(110, 170, 200, 20);
		add(cajaDispo);
		
		btnBuscar = new JButton(iconoBuscar);
		btnBuscar.setIcon(new ImageIcon(iconoBuscar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBuscar.setBounds(380, 30, 50, 50);
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.addActionListener(this);
		add(btnBuscar);
		
		btnEliminar = new JButton(iconoEliminar);
		btnEliminar.setIcon(new ImageIcon(iconoEliminar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnEliminar.setBounds(380, 90, 50, 50);
		btnEliminar.setBackground(Color.LIGHT_GRAY);
		btnEliminar.addActionListener(this);
		add(btnEliminar);
		
		btnBorrar = new JButton(iconoLimpiar);
		btnBorrar.setIcon(new ImageIcon(iconoLimpiar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBorrar.setBounds(380, 150, 50, 50);
		btnBorrar.setBackground(Color.LIGHT_GRAY);
		btnBorrar.addActionListener(this);
		add(btnBorrar);
		
		btnCancelar = new JButton(iconoCancelar);
		btnCancelar.setIcon(new ImageIcon(iconoCancelar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnCancelar.setBounds(380, 210, 50, 50);
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(this);
		add(btnCancelar);
		
		actualizarTabla();
		JScrollPane sp = new JScrollPane(tablaProductos);
		tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				obtenerRegistroTabla();
			}
		
		});
		sp.setBounds(0, 280, 550, 100);
		add(sp);
		
		//Validacion
		cajaPrecio.addKeyListener(new KeyListener() {
			
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
		
		cajaDispo.addKeyListener(new KeyListener() {
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
		
		
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ProductoDAO clDAO = new ProductoDAO();
		
		if(e.getSource() == btnBuscar) {
			actualizarTabla2();
		}
		else if(e.getSource() == btnEliminar) {
			if(clDAO.eliminarRegistro(Integer.parseInt(cajaClave.getText()))) {
				JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
				actualizarTabla();
				reestablecer(cajaClave, cajaDescripcion, cajaPrecio, cajaDispo);
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto");
			}
		}else if(e.getSource() == btnBorrar) {
			reestablecer(cajaClave, cajaDescripcion, cajaPrecio, cajaDispo);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaClave, cajaDescripcion, cajaPrecio, cajaDispo);
		}
		
	}
	
	public void actualizarTabla() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM producto";
		
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
		tablaProductos.setModel(modeloDatos);
	}
	
	public void actualizarTabla2() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM producto WHERE clave_producto = " + cajaClave.getText();
		
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
		tablaProductos.setModel(modeloDatos);
	}
	
	public void obtenerRegistroTabla() {
		int i = (int) tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0);
		cajaClave.setText(i + "");
		cajaDescripcion.setText((String) tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 1));
		int j = (int) tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 2);
		cajaPrecio.setText(j + "");
		cajaDispo.setText((String) tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 3));
	}
	
	public void reestablecer(Component...componentes) {
		for(Component Component : componentes) {
			if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}
		}
	}
	
	public boolean validarCajasVacias() {
		if(cajaClave.getText().isEmpty()) {
			return false;
		}else if(cajaDescripcion.getText().isEmpty()) {
			return false;
		}else if(cajaPrecio.getText().isEmpty()) {
			return false;
		}else if(cajaDispo.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
}//bajas producto
