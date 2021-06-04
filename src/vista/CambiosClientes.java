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

import controlador.ClienteDAO;
import modelo.Cliente;

class CambiosClientes extends JInternalFrame implements ActionListener{
	JTextField cajaidCliente, cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion;
	JButton btnGuardarCambios, btnBorrar, btnCancelar, btnBuscar;
	
	ImageIcon iconoGuardar = new ImageIcon("./archivos/guardar.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	
	JTable tablaClientes = new JTable(5,5);
	
	public CambiosClientes() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Cambios Clientes");
		
		JLabel lblTexto = new JLabel("Cambios Clientes");
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
		
		btnBuscar = new JButton(iconoBuscar);
		btnBuscar.setIcon(new ImageIcon(iconoBuscar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnBuscar.setBounds(380, 30, 50, 50);
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.addActionListener(this);
		add(btnBuscar);
		
		btnGuardarCambios = new JButton(iconoGuardar);
		btnGuardarCambios.setIcon(new ImageIcon(iconoGuardar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnGuardarCambios.setBounds(380, 90, 50, 50);
		btnGuardarCambios.setBackground(Color.LIGHT_GRAY);
		btnGuardarCambios.addActionListener(this);
		add(btnGuardarCambios);
		
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
		JScrollPane sp = new JScrollPane(tablaClientes);
		tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				obtenerRegistroTabla();
			}
		
		});
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
			actualizarTabla2();
		}
		else if(e.getSource() == btnGuardarCambios) {
			String idcli = cajaidCliente.getText();
			int id = Integer.parseInt(idcli);
			Cliente c = new Cliente(id, cajaNombre.getText(), cajaCorreo.getText(), cajaNumTel.getText(), cajaDireccion.getText());
			
			if(clDAO.modificarCliente(c)) {
				JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
				actualizarTabla();
				reestablecer(cajaNombre, cajaCorreo, cajaNumTel, cajaDireccion);
				cajaNombre.setEnabled(false);
				cajaCorreo.setEnabled(false);
				cajaNumTel.setEnabled(false);
				cajaDireccion.setEnabled(false);
			}else {
				JOptionPane.showMessageDialog(null, "No se modifico el cliente");
			}
			btnGuardarCambios.setEnabled(false);
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
	
	public void actualizarTabla2() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM cliente WHERE id_cliente = " + cajaidCliente.getText();
		
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
	
	public void obtenerRegistroTabla() {
		int i = (int) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0);
		cajaidCliente.setText(i + "");
		cajaNombre.setText((String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 1));
		cajaCorreo.setText((String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 2));
		cajaNumTel.setText((String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3));
		cajaDireccion.setText((String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 4));
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
	
}//class CambiosClientes
