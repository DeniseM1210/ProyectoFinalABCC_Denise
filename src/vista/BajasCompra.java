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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.ClienteDAO;
import controlador.CompraProductoDAO;
import modelo.Cliente;

class BajasCompra extends JInternalFrame implements ActionListener{
	JTextField cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor;
	JButton btnEliminar, btnBorrar, btnCancelar, btnBuscar;
	JComboBox<String> comboCantidad = new JComboBox<String>();
	
	ImageIcon iconoEliminar = new ImageIcon("./archivos/eliminar.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	ImageIcon iconoBorrarUsu = new ImageIcon("./archivos/borrarUsu.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	
	JTable tablaCompra = new JTable(5,5);
	
	public BajasCompra() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Bajas Compra");
		
		JLabel lblTexto = new JLabel("Bajas Compra");
		lblTexto.setBounds(200, 20, 200, 20);
		lblTexto.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblTexto);
		
		JLabel lblClave = new JLabel("ID Producto: ");
		lblClave.setBounds(20, 50, 100, 20);
		add(lblClave);
		JLabel lblFecha = new JLabel("Fecha(DD-MM-AAAA): ");
		lblFecha.setBounds(20, 80, 200, 20);
		add(lblFecha);
		JLabel lblNombreP = new JLabel("Nombre del Producto: ");
		lblNombreP.setBounds(20, 110, 150, 20);
		add(lblNombreP);
		JLabel lblCant = new JLabel("Cantidad: ");
		lblCant.setBounds(20, 140, 100, 20);
		add(lblCant);
		JLabel lblColor = new JLabel("Color: ");
		lblColor.setBounds(20, 170, 100, 20);
		add(lblColor);
		
		cajaClaveProd = new JTextField();
		cajaClaveProd.setBounds(100, 50, 250, 20);
		add(cajaClaveProd);
		cajaFecha = new JTextField();
		cajaFecha.setBounds(150, 80, 200, 20);
		add(cajaFecha);
		cajaNombreProd = new JTextField();
		cajaNombreProd.setBounds(150, 110, 200, 20);
		add(cajaNombreProd);
		
		for(int i = 1; i < 100; i++) {
			comboCantidad.addItem("" + i);
		}
		comboCantidad.setBounds(150, 140, 200, 20);
		add(comboCantidad);
		cajaColor = new JTextField();
		cajaColor.setBounds(150, 170, 200, 20);
		add(cajaColor);
		
		
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
		JScrollPane sp = new JScrollPane(tablaCompra);
		tablaCompra.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				obtenerRegistroTabla();
			}
		
		});
		sp.setBounds(0, 280, 550, 100);
		add(sp);
		
		//Validacion
		cajaClaveProd.addKeyListener(new KeyListener() {
			
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
		
		cajaColor.addKeyListener(new KeyListener() {
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
		
		cajaNombreProd.addKeyListener(new KeyListener() {
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
		CompraProductoDAO cpDAO = new CompraProductoDAO();
		
		if(e.getSource() == btnBuscar) {
			actualizarTabla2();
		}
		else if(e.getSource() == btnEliminar) {
			if(cpDAO.eliminarRegistro(Integer.parseInt(cajaClaveProd.getText()))) {
				JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente");
				actualizarTabla();
				reestablecer(cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor);
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar la compra");
			}
		}else if(e.getSource() == btnBorrar) {
			reestablecer(cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor);
		}
		
	}
	
	public void actualizarTabla() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM compra_producto";
		
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
		tablaCompra.setModel(modeloDatos);
	}
	
	public void actualizarTabla2() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM compra_producto WHERE clave_producto = " + cajaClaveProd.getText();
		
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
		tablaCompra.setModel(modeloDatos);
	}
	
	public void obtenerRegistroTabla() {
		cajaClaveProd.setText((String) tablaCompra.getValueAt(tablaCompra.getSelectedRow(), 0));
		cajaFecha.setText((String) tablaCompra.getValueAt(tablaCompra.getSelectedRow(), 1));
		cajaNombreProd.setText((String) tablaCompra.getValueAt(tablaCompra.getSelectedRow(), 2));
		int x = (int) tablaCompra.getValueAt(tablaCompra.getSelectedRow(), 3);
		comboCantidad.setSelectedIndex(x-1);
		cajaColor.setText((String) tablaCompra.getValueAt(tablaCompra.getSelectedRow(), 4));
	}
	
	public void reestablecer(Component...componentes) {
		for(Component Component : componentes) {
			if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}
		}
	}
	
	public boolean validarCajasVacias() {
		if(cajaClaveProd.getText().isEmpty()) {
			return false;
		}else if(cajaFecha.getText().isEmpty()) {
			return false;
		}else if(cajaNombreProd.getText().isEmpty()) {
			return false;
		}else if(cajaColor.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
}//Bajas compra
