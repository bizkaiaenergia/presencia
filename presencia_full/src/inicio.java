

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;


public class inicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private String usuario; 
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicio frame = new inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	
	public inicio() {
		
		
	        
	        
		 usuario = quien_eres.tu_nombre(null);
		 
		final String  id_usuario = quien_eres.quien_eres_nombre_corto(usuario);
		final String Jefe_dpto = quien_eres.jefe_dpto(usuario);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccesoABase = new JLabel("ACCESO A BASE DE DATOS DE PRESENCIA");
		lblAccesoABase.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccesoABase.setBounds(20, 11, 320, 14);
		contentPane.add(lblAccesoABase);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(20, 147, 81, 14);
		contentPane.add(lblUsuario);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(368, 144, 46, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Mensajes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 178, 414, 83);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 17, 393, 55);
		panel_1.add(textArea);
		textArea.setBackground(SystemColor.info);
		textArea.setEditable(false);
		
		
		
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(76, 144, 244, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		JButton btnAcceder = new JButton("Acceder");
		if (id_usuario.equals("")){
			textArea.setText("No es un usuario registrado. \nContacte con el Administrador en: \nadministrador@bizkaiaenergia.com;");
			textField.setText(usuario);
			textField_2.setText(id_usuario);
			btnAcceder.setEnabled(false);
			
		}else{
		textField.setText(usuario);
		textField_2.setText(id_usuario);
		btnAcceder.setEnabled(true);
		}
		
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
					datos_usuario  datos_usuario_nuevo = new datos_usuario(usuario);
				
				datos_usuario_nuevo.setVisible(true);
				
				
				
				
				
			}
		});
		btnAcceder.setBounds(236, 324, 89, 23);
		contentPane.add(btnAcceder);
					
			String [] usuarios = lista_de_usuarios.listado();
					
			
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 272, 414, 41);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(usuarios));
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JComboBox cb = (JComboBox)arg0.getSource();
					String usuario =   (String)cb.getSelectedItem();
					datos_usuario  datos_usuario_nuevo = new datos_usuario(usuario);
					datos_usuario_nuevo.setVisible(true);
					
				}
			});
		
			comboBox.setBounds(228, 11, 162, 20);
			panel.add(comboBox);
			
			JLabel label = new JLabel("Jefes Departamento");
			label.setBounds(10, 14, 192, 14);
			panel.add(label);
					
					
					
					
		
	
	//	tglbtnJefesDepartamento.setToolTipText("No implementado todav\u00EDa");
	if (Jefe_dpto.equals("1")){
		comboBox.setEnabled(true);
		comboBox.setVisible(true);
		label.setVisible(true);
	}else{
		
		comboBox.setEnabled(false);
		comboBox.setVisible(false);
		label.setVisible(false);
	}
	
		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.setToolTipText("No implementado todav\u00EDa");
		btnAyuda.setEnabled(false);
		btnAyuda.setBounds(348, 7, 76, 23);
		contentPane.add(btnAyuda);
		
	
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(330, 147, 28, 14);
		contentPane.add(lblId);
		
		
		
			
			JButton btnNewButton = new JButton("Salir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);	
				}
			});
			btnNewButton.setBounds(335, 324, 89, 23);
			contentPane.add(btnNewButton);
			
			
			ImageIcon image = new ImageIcon("images\\logoPrincipal.gif");
			JLabel imageLabel = new JLabel(image);
			imageLabel.setBounds(10, 36, 414, 97);
			contentPane.add(imageLabel);
			
			
			
			
			
			
		
	
		
		
	}
}
