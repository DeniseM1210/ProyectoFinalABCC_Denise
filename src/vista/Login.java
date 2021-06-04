package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controlador.UsuarioDAO;
import modelo.Usuario;

public class Login extends JFrame implements ActionListener {
	public static boolean bandera;
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
		UsuarioDAO uDAO = new UsuarioDAO();
		uDAO.filtro = cajaUsuario.getText();
		Thread hilo = new Thread(uDAO);
        hilo.start();
        if(bandera == false) {
        	Usuario u1 = new Usuario();
        	u1 = uDAO.buscar(cajaUsuario.getText());
        	if(u1 != null) {
        		if(u1.getContraseña().equals(cajaContra.getText())) {
        			SwingUtilities.invokeLater(new Runnable() {
        				
        				@Override
        				public void run() {
        					new Interfaz();
        				}
        			});
        			setVisible(false);
        		}
        	}
        }
		if(cajaUsuario.getText().equals("") || cajaContra.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "ERROR! Faltan datos");
		}else if(cajaUsuario.getText().equals("Usuario") || cajaContra.getText().equals("12345")) {
			
		}else {
			JOptionPane.showMessageDialog(null, "Datos incorrectos");
		}
		
	}
}

