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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.ClienteDAO;

class ConsultaProducto extends JInternalFrame implements ActionListener{
	JTextField cajaClave, cajaDescripcion, cajaPrecio, cajaDispo;
	JButton btnBorrar, btnCancelar, btnBuscar;
	
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	
	JTable tablaClientes = new JTable(5,5);
	String opciones[] = {"Selecciona una opcion...", "Clave", "Descripcion", "Precio", "Disponibilidad", "Todos"};
	JComboBox<String> comboConsultar = new JComboBox<String>(opciones);
	byte opcionSel = 0;
	
	public ConsultaProducto() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Consultas Productos");
		
		JLabel lblTexto = new JLabel("Consultas Productos");
		lblTexto.setBounds(200, 10, 200, 20);
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
		
		comboConsultar.setBounds(200, 40, 150, 30);
		add(comboConsultar);
		
		btnBuscar = new JButton(iconoBuscar);
		btnBuscar.setIcon(new ImageIcon(iconoBuscar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBuscar.setBounds(380, 30, 50, 50);
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.addActionListener(this);
		add(btnBuscar);
		
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
		sp.setBounds(0, 280, 550, 100);
		add(sp);
		
		//Validacion
		cajaClave.addKeyListener(new KeyListener() {
			
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
		
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnBuscar) {
			if(comboConsultar.getSelectedIndex() == 1) {
				if(cajaClave.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 1;
				}
				btnBuscar.setEnabled(true);
				cajaClave.setEnabled(true);
				cajaDescripcion.setEnabled(false);
				cajaPrecio.setEnabled(false);
				cajaDispo.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 2) {
				if(cajaDescripcion.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 2;
				}
				btnBuscar.setEnabled(true);
				cajaClave.setEnabled(false);
				cajaDescripcion.setEnabled(true);
				cajaPrecio.setEnabled(false);
				cajaDispo.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 3) {
				if(cajaPrecio.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 3;
				}
				btnBuscar.setEnabled(true);
				cajaClave.setEnabled(false);
				cajaDescripcion.setEnabled(false);
				cajaPrecio.setEnabled(true);
				cajaDispo.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 4) {
				if(cajaDispo.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 4;
				}
				btnBuscar.setEnabled(true);
				cajaClave.setEnabled(false);
				cajaDescripcion.setEnabled(false);
				cajaPrecio.setEnabled(false);
				cajaDispo.setEnabled(true);
			}else if(comboConsultar.getSelectedIndex() == 5) {
				if(validarCajasVacias()) {
					opcionSel = 5;
				}else {
					opcionSel = 0;
				}
				btnBuscar.setEnabled(true);
				cajaClave.setEnabled(true);
				cajaDescripcion.setEnabled(true);
				cajaPrecio.setEnabled(true);
				cajaDispo.setEnabled(true);
			}else if(comboConsultar.getSelectedIndex() == 0) {
				opcionSel = 0;
				btnBuscar.setEnabled(false);
				cajaClave.setEnabled(false);
				cajaDescripcion.setEnabled(false);
				cajaPrecio.setEnabled(false);
				cajaDispo.setEnabled(false);
			}
			actualizarTabla();
		}
		else if(e.getSource() == btnBorrar) {
			reestablecer(cajaClave, cajaDescripcion, cajaPrecio, cajaDispo);
			opcionSel = 0;
			actualizarTabla();
			comboConsultar.setSelectedIndex(0);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaClave, cajaDescripcion, cajaPrecio, cajaDispo);
			opcionSel = 0;
			actualizarTabla();
			comboConsultar.setSelectedIndex(0);
		}
		
	}
	
	public void actualizarTabla() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM producto";
		
		ResultSetTableModel modeloDatos = null;
		
		if(opcionSel == 5) {
			int id = -1;
			if(cajaClave.getText() != "") {
				id = Integer.parseInt(cajaClave.getText());
			}
			consulta = "SELECT * FROM producto WHERE clave_producto = " + id + "AND descripcion = '" + cajaDescripcion.getText() +"' AND precio = " + cajaPrecio.getText() + 
					" AND disponibilidad = '" + cajaDispo.getText() + "';";
		}else if(opcionSel == 1) {
			int id = -1;
			if(cajaClave.getText() != "") {
				id = Integer.parseInt(cajaClave.getText());
			}
			consulta = "SELECT * FROM producto WHERE clave_producto = "+ id + ";";
		}else if(opcionSel == 2) {
			consulta = "SELECT * FROM producto WHERE descripcion = '" + cajaDescripcion.getText() + "';";
		}else if(opcionSel == 3) {
			consulta = "SELECT * FROM producto WHERE precio = " + cajaPrecio.getText() + ";";
		}else if(opcionSel == 4) {
			consulta = "SELECT * FROM producto WHERE disponibilidad = " + cajaDispo.getText() + ";";
		}
		
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
}//Consulta producto
