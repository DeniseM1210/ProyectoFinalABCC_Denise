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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controlador.CompraProductoDAO;
import modelo.CompraProducto;

class AltasCompra extends JInternalFrame implements ActionListener{
	JTextField cajaClaveProd, cajaFecha, cajaNombreProd, cajaColor;
	JButton btnAgregar, btnBorrar, btnCancelar;
	JComboBox<String> comboCantidad = new JComboBox<String>();
	
	ImageIcon iconoAgregar = new ImageIcon("./archivos/anadir.png");
	ImageIcon iconoLimpiar = new ImageIcon("./archivos/limpio.png");
	ImageIcon iconoCancelar = new ImageIcon("./archivos/cancelar.png");
	
	JTable tablaCompraProducto = new JTable(5,5);
	
	public AltasCompra() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(567,490);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Altas Compra");
		
		JLabel lblTexto = new JLabel("Altas Compra");
		lblTexto.setBounds(200, 20, 200, 20);
		lblTexto.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblTexto);
		
		JLabel lblClave = new JLabel("ID Producto: ");
		lblClave.setBounds(20, 50, 100, 20);
		add(lblClave);
		JLabel lblFecha = new JLabel("Fecha(DD-MM-AAAA): ");
		lblFecha.setBounds(20, 80, 150, 20);
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
		JScrollPane sp = new JScrollPane(tablaCompraProducto);
		sp.setBounds(0, 250, 567, 100);
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
		if(e.getSource() == btnAgregar) {
			CompraProductoDAO cpDAO = new CompraProductoDAO();
			if(validarCajasVacias()) {
				String clave = cajaClaveProd.getText();
				int clave1 = Integer.parseInt(clave);
				CompraProducto cp = new CompraProducto(clave1, cajaFecha.getText(), cajaNombreProd.getText(), (byte)(comboCantidad.getSelectedIndex()+1), cajaColor.getText());
				if(cpDAO.insertarCompraProducto(cp)) {
					JOptionPane.showMessageDialog(null, "Se agrego la compra correctamente");
					actualizarTabla();
					reestablecer(cajaFecha, cajaNombreProd, comboCantidad, cajaColor, cajaClaveProd, comboCantidad);
				}else {
					JOptionPane.showMessageDialog(rootPane, "No se agrego la compra", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
			}
		}else if(e.getSource() == btnBorrar) {
			reestablecer(cajaFecha, cajaNombreProd, comboCantidad, cajaColor, cajaClaveProd, comboCantidad);
		}else if(e.getSource() == btnCancelar) {
			reestablecer(cajaFecha, cajaNombreProd, comboCantidad, cajaColor, cajaClaveProd, comboCantidad);
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
		tablaCompraProducto.setModel(modeloDatos);
	}
	
	public void reestablecer(Component...componentes) {
		for(Component Component : componentes) {
			if(Component instanceof JTextField) {
				((JTextField)Component).setText("");
			}
		}
	}
	
	public boolean validarCajasVacias() {
		if(cajaFecha.getText().isEmpty()) {
			return false;
		}else if(cajaNombreProd.getText().isEmpty()) {
			return false;
		}else if(cajaColor.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}

}//altas compra

