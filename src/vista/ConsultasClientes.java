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

class ConsultasClientes extends JInternalFrame implements ActionListener{
	JTextField cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion;
	JButton btnBorrar, btnCancelar, btnBuscar;
	
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	
	JTable tablaClientes = new JTable(5,5);
	String opciones[] = {"Selecciona una opcion...", "ID cliente", "Nombre", "Correo", "Num. Tel", "Direccion", "Todos"};
	JComboBox<String> comboConsultar = new JComboBox<String>(opciones);
	byte opcionSel = 0;
	
	public ConsultasClientes() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Consultas Clientes");
		
		JLabel lblTexto = new JLabel("Cambios Clientes");
		lblTexto.setBounds(200, 10, 200, 20);
		lblTexto.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblTexto);
		
		JLabel lblIdCliente = new JLabel("Id Cliente: ");
		lblIdCliente.setBounds(20, 80, 100, 20);
		add(lblIdCliente);
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(20, 110, 100, 20);
		add(lblNombre);
		JLabel lblCorreo = new JLabel("Correo Electronico: ");
		lblCorreo.setBounds(20, 140, 150, 20);
		add(lblCorreo);
		JLabel lblNumTel = new JLabel("Num. Telefono: ");
		lblNumTel.setBounds(20, 170, 100, 20);
		add(lblNumTel);
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(20, 200, 100, 20);
		add(lblDireccion);
		
		cajaidCliente = new JTextField();
		cajaidCliente.setBounds(90, 80, 200, 20);
		add(cajaidCliente);
		cajaNombre = new JTextField();
		cajaNombre.setBounds(90, 110, 200, 20);
		add(cajaNombre);
		cajaCorreo = new JTextField();
		cajaCorreo.setBounds(130, 140, 160, 20);
		add(cajaCorreo);
		cajaNumTel = new JTextField();
		cajaNumTel.setBounds(110, 170, 180, 20);
		add(cajaNumTel);
		cajaDireccion = new JTextField();
		cajaDireccion.setBounds(90, 200, 200, 20);
		add(cajaDireccion);
		
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
		ClienteDAO clDAO = new ClienteDAO();
		
		if(e.getSource() == btnBuscar) {
			if(comboConsultar.getSelectedIndex() == 1) {
				if(cajaidCliente.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 1;
				}
				btnBuscar.setEnabled(true);
				cajaidCliente.setEnabled(true);
				cajaNombre.setEnabled(false);
				cajaCorreo.setEnabled(false);
				cajaNumTel.setEnabled(false);
				cajaDireccion.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 2) {
				if(cajaNombre.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 2;
				}
				btnBuscar.setEnabled(true);
				cajaidCliente.setEnabled(false);
				cajaNombre.setEnabled(true);
				cajaCorreo.setEnabled(false);
				cajaNumTel.setEnabled(false);
				cajaDireccion.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 3) {
				if(cajaCorreo.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 3;
				}
				btnBuscar.setEnabled(true);
				cajaidCliente.setEnabled(false);
				cajaNombre.setEnabled(false);
				cajaCorreo.setEnabled(true);
				cajaNumTel.setEnabled(false);
				cajaDireccion.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 4) {
				if(cajaNumTel.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 4;
				}
				btnBuscar.setEnabled(true);
				cajaidCliente.setEnabled(false);
				cajaNombre.setEnabled(false);
				cajaCorreo.setEnabled(false);
				cajaNumTel.setEnabled(true);
				cajaDireccion.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 5) {
				if(cajaDireccion.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 5;
				}
				btnBuscar.setEnabled(true);
				cajaidCliente.setEnabled(false);
				cajaNombre.setEnabled(false);
				cajaCorreo.setEnabled(false);
				cajaNumTel.setEnabled(false);
				cajaDireccion.setEnabled(true);
			}else if(comboConsultar.getSelectedIndex() == 0) {
				opcionSel = 0;
				btnBuscar.setEnabled(false);
				cajaidCliente.setEnabled(false);
				cajaNombre.setEnabled(false);
				cajaCorreo.setEnabled(false);
				cajaNumTel.setEnabled(false);
				cajaDireccion.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 6) {
				opcionSel = 6;
				btnBuscar.setEnabled(true);
				cajaidCliente.setEnabled(true);
				cajaNombre.setEnabled(true);
				cajaCorreo.setEnabled(true);
				cajaNumTel.setEnabled(true);
				cajaDireccion.setEnabled(true);
			}
			actualizarTabla();
		}
		else if(e.getSource() == btnBorrar) {
			reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
			opcionSel = 0;
			actualizarTabla();
			comboConsultar.setSelectedIndex(0);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
			opcionSel = 0;
			actualizarTabla();
			comboConsultar.setSelectedIndex(0);
		}
		
	}
	
	public void actualizarTabla() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM cliente";
		
		ResultSetTableModel modeloDatos = null;
		
		if(opcionSel == 6) {
			int id = -1;
			if(cajaidCliente.getText() != "") {
				id = Integer.parseInt(cajaidCliente.getText());
			}
			consulta = "SELECT * FROM cliente WHERE id_cliente = " + id + "AND nombre = '" + cajaNombre.getText() +"' AND correo_electronico = '" + cajaCorreo.getText() + 
					"' AND num_telefono = " + cajaNumTel.getText() + " AND direccion = '" + cajaDireccion.getText() + "';";
		}else if(opcionSel == 1) {
			int id = -1;
			if(cajaidCliente.getText() != "") {
				id = Integer.parseInt(cajaidCliente.getText());
			}
			consulta = "SELECT * FROM cliente WHERE id_cliente = "+ id + ";";
		}else if(opcionSel == 2) {
			consulta = "SELECT * FROM cliente WHERE nombre = '" + cajaNombre.getText() + "';";
		}else if(opcionSel == 3) {
			consulta = "SELECT * FROM cliente WHERE correo_electronico = '" + cajaCorreo.getText() + "';";
		}else if(opcionSel == 4) {
			consulta = "SELECT * FROM cliente WHERE num_telefono = " + cajaNumTel.getText() + ";";
		}else if(opcionSel == 5) {
			consulta = "SELECT FROM cliente WHERE direccion = '" + cajaDireccion.getText() + "';";
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
}//class consultas clientes
