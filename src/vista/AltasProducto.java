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

class AltasProducto extends JInternalFrame implements ActionListener{
	JTextField cajaClaveProd, cajaDescripcion, cajaPrecio, cajaDisponibilidad;
	JButton btnAgregar, btnBorrar, btnCancelar;
	
	ImageIcon iconoAgregar = new ImageIcon("./archivos/anadir.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	
	JTable tablaProductos = new JTable(5,5);
	
	public AltasProducto() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Altas Productos");
		
		JLabel lblTexto = new JLabel("Altas Productos");
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
		
		cajaClaveProd = new JTextField();
		cajaClaveProd.setBounds(110, 80, 200, 20);
		add(cajaClaveProd);
		cajaDescripcion = new JTextField();
		cajaDescripcion.setBounds(100, 110, 210, 20);
		add(cajaDescripcion);
		cajaPrecio = new JTextField();
		cajaPrecio.setBounds(70, 140, 240, 20);
		add(cajaPrecio);
		cajaDisponibilidad = new JTextField();
		cajaDisponibilidad.setBounds(110, 170, 200, 20);
		add(cajaDisponibilidad);
		
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
		JScrollPane sp = new JScrollPane(tablaProductos);
		sp.setBounds(0, 250, 567, 100);
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
		
		cajaDescripcion.addKeyListener(new KeyListener() {
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
		
		cajaDisponibilidad.addKeyListener(new KeyListener() {
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
		if(e.getSource() == btnAgregar) {
			ProductoDAO pDAO = new ProductoDAO();
			if(cajaClaveProd.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
			}else {
				int id = Integer.parseInt(cajaClaveProd.getText());
				Producto p = new Producto(id, cajaDescripcion.getText(), cajaPrecio.getText(), cajaDisponibilidad.getText());
				if(pDAO.insertarProducto(p)) {
					JOptionPane.showMessageDialog(null, "Se agrego el cliente correctamente");
					actualizarTabla();
					reestablecer(cajaClaveProd, cajaDescripcion, cajaPrecio, cajaDisponibilidad);
				}else {
					JOptionPane.showMessageDialog(rootPane, "No se agrego el cliente", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(e.getSource() == btnBorrar) {
			reestablecer(cajaClaveProd, cajaDescripcion, cajaPrecio, cajaDisponibilidad);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaClaveProd, cajaDescripcion, cajaPrecio, cajaDisponibilidad);
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
		}else if(cajaDescripcion.getText().isEmpty()) {
			return false;
		}else if(cajaPrecio.getText().isEmpty()) {
			return false;
		}else if(cajaDisponibilidad.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
}//altas producto
