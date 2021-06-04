package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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

class ConsultaCompra extends JInternalFrame implements ActionListener{
	JTextField cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor;
	JButton btnBorrar, btnCancelar, btnBuscar;
	JComboBox<String> comboCantidad = new JComboBox<String>();
	
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	ImageIcon iconoBuscar = new ImageIcon("./archivos/lupa.png");
	
	JTable tablaCompra = new JTable(5,5);
	String opciones[] = {"Selecciona una opcion...", "ID", "Fecha", "Nombre Producto", "Cantidad", "Color", "Todos"};
	JComboBox<String> comboConsultar = new JComboBox<String>(opciones);
	byte opcionSel = 0;
	
	public ConsultaCompra() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Consultas Compra");
		
		JLabel lblTexto = new JLabel("Consultas Compra");
		lblTexto.setBounds(200, 10, 200, 20);
		lblTexto.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblTexto);
		
		JLabel lblIdCliente = new JLabel("ID Producto: ");
		lblIdCliente.setBounds(20, 80, 100, 20);
		add(lblIdCliente);
		JLabel lblNombre = new JLabel("Fecha(DD-MM-AAAA): ");
		lblNombre.setBounds(20, 110, 200, 20);
		add(lblNombre);
		JLabel lblCorreo = new JLabel("Nombre Producto: ");
		lblCorreo.setBounds(20, 140, 150, 20);
		add(lblCorreo);
		JLabel lblNumTel = new JLabel("Cantidad: ");
		lblNumTel.setBounds(20, 170, 100, 20);
		add(lblNumTel);
		JLabel lblDireccion = new JLabel("Color: ");
		lblDireccion.setBounds(20, 200, 100, 20);
		add(lblDireccion);
		
		cajaClaveProd = new JTextField();
		cajaClaveProd.setBounds(100, 80, 250, 20);
		add(cajaClaveProd);
		cajaFecha = new JTextField();
		cajaFecha.setBounds(150, 110, 200, 20);
		add(cajaFecha);
		cajaNombreProd = new JTextField();
		cajaNombreProd.setBounds(150, 140, 200, 20);
		add(cajaNombreProd);
		
		for(int i = 1; i < 100; i++) {
			comboCantidad.addItem("" + i);
		}
		comboCantidad.setBounds(150, 170, 200, 20);
		add(comboCantidad);
		cajaColor = new JTextField();
		cajaColor.setBounds(150, 200, 200, 20);
		add(cajaColor);
		
		
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
		JScrollPane sp = new JScrollPane(tablaCompra);
		sp.setBounds(0, 280, 550, 100);
		add(sp);
		
		//Validacion
		cajaClaveProd.addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
				      char caracter = e.getKeyChar();
				      if(((caracter < 48) || (caracter > 57)) &&(caracter != '\b')){
				         e.consume(); 
				      }
				   }
				});
		
		cajaNombreProd.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(Character.isLetter(caracter) || Character.isSpaceChar(caracter)) {
				}else {
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
				char caracter = e.getKeyChar();
				if(Character.isLetter(caracter) || Character.isSpaceChar(caracter)) {
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
		ClienteDAO clDAO = new ClienteDAO();
		
		if(e.getSource() == btnBuscar) {
			if(comboConsultar.getSelectedIndex() == 1) {
				if(cajaClaveProd.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 1;
				}
				btnBuscar.setEnabled(true);
				cajaClaveProd.setEnabled(true);
				cajaFecha.setEnabled(false);
				cajaNombreProd.setEnabled(false);
				comboCantidad.setEnabled(false);
				cajaColor.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 2) {
				if(cajaFecha.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 2;
				}
				btnBuscar.setEnabled(true);
				cajaClaveProd.setEnabled(false);
				cajaFecha.setEnabled(true);
				cajaNombreProd.setEnabled(false);
				comboCantidad.setEnabled(false);
				cajaColor.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 3) {
				if(cajaNombreProd.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 3;
				}
				btnBuscar.setEnabled(true);
				cajaClaveProd.setEnabled(false);
				cajaFecha.setEnabled(false);
				cajaNombreProd.setEnabled(true);
				comboCantidad.setEnabled(false);
				cajaColor.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 4) {
				opcionSel = 4;
				btnBuscar.setEnabled(true);
				cajaClaveProd.setEnabled(false);
				cajaFecha.setEnabled(false);
				cajaNombreProd.setEnabled(false);
				//comboCantidad.setEnabled(true);
				cajaColor.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 5) {
				if(cajaColor.getText().isEmpty()) {
					opcionSel = 0;
				}else {
					opcionSel = 5;
				}
				btnBuscar.setEnabled(true);
				cajaClaveProd.setEnabled(false);
				cajaFecha.setEnabled(false);
				cajaNombreProd.setEnabled(false);
				comboCantidad.setEnabled(false);
				cajaColor.setEnabled(true);
			}else if(comboConsultar.getSelectedIndex() == 0) {
				opcionSel = 0;
				btnBuscar.setEnabled(false);
				cajaClaveProd.setEnabled(false);
				cajaFecha.setEnabled(false);
				cajaNombreProd.setEnabled(false);
				comboCantidad.setEnabled(false);
				cajaColor.setEnabled(false);
			}else if(comboConsultar.getSelectedIndex() == 6) {
				opcionSel = 6;
				btnBuscar.setEnabled(true);
				cajaClaveProd.setEnabled(true);
				cajaFecha.setEnabled(true);
				cajaNombreProd.setEnabled(true);
				comboCantidad.setEnabled(true);
				cajaColor.setEnabled(true);
			}
			actualizarTabla();
		}
		else if(e.getSource() == btnBorrar) {
			reestablecer(cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor);
			opcionSel = 0;
			actualizarTabla();
			comboConsultar.setSelectedIndex(0);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor);
			opcionSel = 0;
			actualizarTabla();
			comboConsultar.setSelectedIndex(0);
		}
		
	}
	
	public void actualizarTabla() {
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/talabarteria";
		String consulta = "SELECT * FROM compra_producto";
		
		ResultSetTableModel modeloDatos = null;
		
		if(opcionSel == 6) {
			int id = -1;
			if(cajaClaveProd.getText() != "") {
				id = Integer.parseInt(cajaClaveProd.getText());
			}
			consulta = "SELECT * FROM compra_producto WHERE clave_producto = " + id + "AND fecha = '" + cajaFecha.getText() +"' AND nombre_producto = '" + cajaNombreProd.getText() + 
					"' AND cantidad = " + (comboCantidad.getSelectedIndex() -1) + " AND color = '" + cajaColor.getText() + "';";
		}else if(opcionSel == 1) {
			int id = -1;
			if(cajaClaveProd.getText() != "") {
				id = Integer.parseInt(cajaClaveProd.getText());
			}
			consulta = "SELECT * FROM compra_producto WHERE clave_producto = "+ id + ";";
		}else if(opcionSel == 2) {
			consulta = "SELECT * FROM compra_producto WHERE fecha = '" + cajaFecha.getText() + "';";
		}else if(opcionSel == 3) {
			consulta = "SELECT * FROM compra_producto WHERE nombre_producto = '" + cajaNombreProd.getText() + "';";
		}else if(opcionSel == 4) {
			consulta = "SELECT * FROM compra_producto WHERE cantidad = " + (comboCantidad.getSelectedIndex() -1) + ";";
		}else if(opcionSel == 5) {
			consulta = "SELECT FROM compra_producto WHERE color = '" + cajaColor.getText() + "';";
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
		
		tablaCompra.setModel(modeloDatos);
		
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
}
