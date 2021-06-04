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

import controlador.ClienteDAO;
import controlador.CompraProductoDAO;
import controlador.ProductoDAO;
import modelo.Cliente;
import modelo.CompraProducto;
import modelo.Producto;

class Interfaz extends JFrame implements ActionListener{
	
	
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
			altasCli = new JMenuItem("Añadir Cliente");
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
			altasProd.addActionListener(this);
			
			bajasProd = new JMenuItem("Eliminar Producto");
			producto.add(bajasProd);
			bajasProd.addActionListener(this);
			cambiosProd = new JMenuItem("Modificar Producto");
			producto.add(cambiosProd);
			cambiosProd.addActionListener(this);
			consultasProd = new JMenuItem("Buscar Producto");
			producto.add(consultasProd);
			consultasProd.addActionListener(this);
		
		menuBar.add(cliente);
		menuBar.add(compraProducto);
		menuBar.add(producto);
		setJMenuBar(menuBar);
		
		dp.setBounds(0, 0, 567, 490);
		dp.setBackground(Color.PINK);
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
		}else if(e.getSource() == bajasCli) {
			BajasClientes bc = new BajasClientes();
			dp.add(bc);
			bc.setVisible(true);
			add(dp);
		}else if(e.getSource() == cambiosCli) {
			CambiosClientes cc = new CambiosClientes();
			dp.add(cc);
			cc.setVisible(true);
			add(dp);
		}else if(e.getSource() == consultasCli) {
			ConsultasClientes ccl = new ConsultasClientes();
			dp.add(ccl);
			ccl.setVisible(true);
			add(dp);
		}else if(e.getSource() == altasCom) {
			AltasCompra acp = new AltasCompra();
			dp.add(acp);
			acp.setVisible(true);
			add(dp);
		}else if(e.getSource() == altasProd) {
			AltasProducto ap = new AltasProducto();
			dp.add(ap);
			ap.setVisible(true);
			add(dp);
		}else if(e.getSource() == bajasProd) {
			BajasProducto bp = new BajasProducto();
			dp.add(bp);
			bp.setVisible(true);
			add(dp);
		}else if(e.getSource() == bajasCom) {
			BajasCompra bc = new BajasCompra();
			dp.add(bc);
			bc.setVisible(true);
			add(dp);
		}else if(e.getSource() == cambiosProd) {
			CambiosProducto cp = new CambiosProducto();
			dp.add(cp);
			cp.setVisible(true);
			add(dp);
		}else if(e.getSource() == cambiosCom) {
			CambiosCompra cc = new CambiosCompra();
			dp.add(cc);
			cc.setVisible(true);
			add(dp);
		}else if(e.getSource() == consultasProd) {
			ConsultaProducto cp = new ConsultaProducto();
			dp.add(cp);
			cp.setVisible(true);
			add(dp);
		}else if(e.getSource() == consultasCom) {
			ConsultaCompra cc = new ConsultaCompra();
			dp.add(cc);
			cc.setVisible(true);
			add(dp);
		}
		
	}
	
	
}

public class VentanaInicio {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Login();
				
			}
		});

	}

}
