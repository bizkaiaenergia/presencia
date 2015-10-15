import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.ButtonModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;



public class datos_usuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField_HorasTotalesACompensar;
	private JTextField textField_HorasCompensadas;
	private JTextField textField_HorasCompensarQuedan;
	private JTextField textField_HorasACobrarAñoActual;
	private JTextField textField_ACobrarAñoActualEfectivas;
	private JTextField textField_VacacionesAñoAnterior;
	private JTextField textField_VacacionesActual;
	private JTextField textField_VacacionesDisfrutadas;
	private JTextField textField_VacacionesQuedan;
	private JTable table;
	private JTextField textField_año_anterior;
	private JTextField textField_año_actual;
	private JTextField textField_disfrutadas;
	private JTextField textField_quedan;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField_total_horas_cobrar_año_actual;
	private JTextField textField_total_horas_cobrar_efectivas_año_actual;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_QUEDAN_PARA_AÑO_SIGUIENTE;
	private JTextField textField_INCL_AÑO_ANT;
	private String año_combobox;
	private JRadioButton rdbtnNewRadioButton;
	private JTable table_3;
	private JTable horas_extras_trabajadas_table;
	private JTable disfrute_horas_extras_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String usuario = quien_eres.tu_nombre(null);
					datos_usuario frame = new datos_usuario(usuario);
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
	public datos_usuario(final String usuario) {
		
		
		propiedades Archivopropiedades = new propiedades();
		String absentismo = Archivopropiedades.absentismo();
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String [] AÑOS = lista_de_años.listado_años();
		Date Fecha = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String año = formatter.format(Fecha);
		List<String> list = Arrays.asList(AÑOS);  
		Integer Index=list.indexOf(año);
		
	
		setBounds(100, 100, 1141, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 53, 1105, 493);
		contentPane.add(tabbedPane);
		
		JPanel Resumen = new JPanel();
		tabbedPane.addTab("Resumen", null, Resumen, null);
		Resumen.setLayout(null);
		
		JLabel lblResumenUsuario = new JLabel("HORAS EXTRAS");
		lblResumenUsuario.setBounds(10, 149, 184, 23);
		Resumen.add(lblResumenUsuario);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 171, 465, 68);
		Resumen.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblHorasTotalesACompensar = new JLabel("GENERADAS");
		lblHorasTotalesACompensar.setToolTipText("Horas extras generadas en este a\u00F1o");
		lblHorasTotalesACompensar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblHorasTotalesACompensar.setBounds(10, 11, 77, 14);
		panel_2.add(lblHorasTotalesACompensar);
		
		textField_HorasTotalesACompensar = new JTextField();
		textField_HorasTotalesACompensar.setToolTipText("Horas extras generadas en este a\u00F1o");
		textField_HorasTotalesACompensar.setBounds(10, 31, 36, 20);
		panel_2.add(textField_HorasTotalesACompensar);
		textField_HorasTotalesACompensar.setColumns(10);
		
		
		textField_INCL_AÑO_ANT = new JTextField();
		textField_INCL_AÑO_ANT.setToolTipText("Suma de horas extras generadas el a\u00F1o actual y las pendientes del a\u00F1o anterior");
		textField_INCL_AÑO_ANT.setText("<dynamic>");
		textField_INCL_AÑO_ANT.setColumns(10);
		textField_INCL_AÑO_ANT.setBounds(119, 31, 36, 20);
		panel_2.add(textField_INCL_AÑO_ANT);
		
		
		
		JLabel lblHorasCompensadas = new JLabel("COMPENSADAS");
		lblHorasCompensadas.setToolTipText("Horas Extras compensadas el a\u00F1o actual");
		lblHorasCompensadas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblHorasCompensadas.setBounds(265, 11, 103, 14);
		panel_2.add(lblHorasCompensadas);
		
		textField_HorasCompensadas = new JTextField();
		textField_HorasCompensadas.setToolTipText("Horas Extras compensadas el a\u00F1o actual");
		textField_HorasCompensadas.setColumns(10);
		textField_HorasCompensadas.setBounds(276, 31, 36, 20);
		panel_2.add(textField_HorasCompensadas);
		
		JLabel lblQuedan = new JLabel("QUEDAN(*)");
		lblQuedan.setToolTipText("Horas Extras pendientes de disfrutar");
		lblQuedan.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblQuedan.setBounds(378, 11, 77, 14);
		panel_2.add(lblQuedan);
		
		textField_HorasCompensarQuedan = new JTextField();
		textField_HorasCompensarQuedan.setToolTipText("Horas Extras pendientes de disfrutar");
		textField_HorasCompensarQuedan.setColumns(10);
		textField_HorasCompensarQuedan.setBounds(388, 31, 36, 20);
		panel_2.add(textField_HorasCompensarQuedan);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////
		
		 float horas_totales_compensadas_año_actual_SOLO_PARADA = horas_a_compensar_solo_parada.totales_horas_compensadas_SOLO_PARADA(usuario, año);
		
		 float horas_totales_generadas_año_actual_SOLO_PARADA = horas_a_compensar_solo_parada.generadas_año_SOLO_PARADA(usuario, año);
		
		 float quedan_a_compensar_SOLO_PARADA = horas_totales_generadas_año_actual_SOLO_PARADA - horas_totales_compensadas_año_actual_SOLO_PARADA;
		 DecimalFormat dec2 = new DecimalFormat("##0.00"); 
		
		 String horas_totales_compensadas_año_actual_SOLO_PARADA_STRING = dec2.format(horas_totales_compensadas_año_actual_SOLO_PARADA);
			String horas_totales_generadas_año_actual_SOLO_PARADA_STRING = dec2.format(horas_totales_generadas_año_actual_SOLO_PARADA);
			String quedan_a_compensar_SOLO_PARADA_STRING = dec2.format(quedan_a_compensar_SOLO_PARADA);
		
	
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(10, 276, 366, 68);
		Resumen.add(panel_4);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblTotal.setBounds(10, 11, 180, 14);
		panel_4.add(lblTotal);
		
		textField_HorasACobrarAñoActual = new JTextField();
		textField_HorasACobrarAñoActual.setColumns(10);
		textField_HorasACobrarAñoActual.setBounds(10, 31, 36, 20);
		panel_4.add(textField_HorasACobrarAñoActual);
		
		JLabel lblEfectivas = new JLabel("EFECTIVAS");
		lblEfectivas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEfectivas.setBounds(179, 11, 94, 14);
		panel_4.add(lblEfectivas);
		
		textField_ACobrarAñoActualEfectivas = new JTextField();
		
		textField_ACobrarAñoActualEfectivas.setColumns(10);
		textField_ACobrarAñoActualEfectivas.setBounds(179, 31, 36, 20);
		panel_4.add(textField_ACobrarAñoActualEfectivas);
		
		JLabel lblHorasACobrar = new JLabel("HORAS A COBRAR A\u00D1O ACTUAL");
		lblHorasACobrar.setBounds(10, 250, 184, 23);
		Resumen.add(lblHorasACobrar);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 386, 742, 68);
		Resumen.add(panel_5);
		
		textField_VacacionesAñoAnterior = new JTextField();
		textField_VacacionesAñoAnterior.setColumns(10);
		textField_VacacionesAñoAnterior.setBounds(10, 31, 36, 20);
		panel_5.add(textField_VacacionesAñoAnterior);
		
		textField_VacacionesActual = new JTextField();
		textField_VacacionesActual.setColumns(10);
		textField_VacacionesActual.setBounds(121, 31, 36, 20);
		panel_5.add(textField_VacacionesActual);
		
		textField_VacacionesDisfrutadas = new JTextField();
		textField_VacacionesDisfrutadas.setColumns(10);
		textField_VacacionesDisfrutadas.setBounds(200, 31, 36, 20);
		panel_5.add(textField_VacacionesDisfrutadas);
		
		textField_VacacionesQuedan = new JTextField();
		textField_VacacionesQuedan.setColumns(10);
		textField_VacacionesQuedan.setBounds(362, 31, 36, 20);
		panel_5.add(textField_VacacionesQuedan);
		
		JLabel lblQuedan_1 = new JLabel("Quedan (a\u00F1o actual)");
		lblQuedan_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblQuedan_1.setBounds(362, 11, 165, 14);
		panel_5.add(lblQuedan_1);
		
		JLabel lblAoActual = new JLabel("A\u00F1o actual");
		lblAoActual.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblAoActual.setBounds(121, 11, 69, 14);
		panel_5.add(lblAoActual);
		
		JLabel lblAoAnterior = new JLabel("A\u00F1o anterior");
		lblAoAnterior.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblAoAnterior.setBounds(10, 11, 180, 14);
		panel_5.add(lblAoAnterior);
		
		JLabel lblDisfrutadas = new JLabel("Disfrutadas (a\u00F1o actual)");
		lblDisfrutadas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblDisfrutadas.setBounds(200, 11, 118, 14);
		panel_5.add(lblDisfrutadas);
		
		JLabel lblVacaciones = new JLabel("VACACIONES");
		lblVacaciones.setBounds(10, 367, 184, 23);
		Resumen.add(lblVacaciones);
		
		JLabel lblResumenAoActual = new JLabel("RESUMEN A\u00D1O ACTUAL " + "(" + año + ")");
		lblResumenAoActual.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResumenAoActual.setBounds(235, 135, 222, 14);
		Resumen.add(lblResumenAoActual);
		
		
			
			JLabel label_4 = new JLabel("HORAS EXTRAS");
			label_4.setBounds(10, 24, 184, 23);
			Resumen.add(label_4);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_1.setBounds(10, 46, 465, 68);
			Resumen.add(panel_1);
			
			JLabel lblQuedanParaAo = new JLabel("QUEDAN PARA A\u00D1O SIGUIENTE");
			lblQuedanParaAo.setToolTipText("Horas extras no disfrutadas a\u00F1o anterior");
			lblQuedanParaAo.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lblQuedanParaAo.setBounds(10, 11, 181, 14);
			panel_1.add(lblQuedanParaAo);
			
			textField_QUEDAN_PARA_AÑO_SIGUIENTE = new JTextField();
			textField_QUEDAN_PARA_AÑO_SIGUIENTE.setToolTipText("Horas extras no disfrutadas a\u00F1o anterior");
			textField_QUEDAN_PARA_AÑO_SIGUIENTE.setColumns(10);
			textField_QUEDAN_PARA_AÑO_SIGUIENTE.setBounds(10, 37, 36, 20);
			panel_1.add(textField_QUEDAN_PARA_AÑO_SIGUIENTE);
			
			JLabel lblResumenAoAnterior = new JLabel("A\u00F1o anterior");
			lblResumenAoAnterior.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblResumenAoAnterior.setBounds(235, 11, 222, 14);
			Resumen.add(lblResumenAoAnterior);
			
			
		
			
			JPanel horas_compensar = new JPanel();
			
			tabbedPane.addTab("Horas a Compensar Extendido", null, horas_compensar, null);
			
			horas_compensar.setLayout(null);
			
			
			
			//final String Jefe_dpto = quien_eres.jefe_dpto(usuario);	
			final String jefe_dpto = inicio.puedes_ver_todo();
			if (jefe_dpto.equals("1")){
				tabbedPane.setEnabledAt(1, true);
				//horas_compensar.setVisible(false);
			}else{
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.remove(1);
				//horas_compensar.setVisible(false);
			}
			
			
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(20, 93, 1070, 361);
			horas_compensar.add(scrollPane_2);
		
			table_2 = new JTable();
			scrollPane_2.setViewportView(table_2);
		
		
		
		
		//table_2.setDefaultRenderer(Object.class, new MiRender());
		
		rdbtnNewRadioButton = new JRadioButton("Todas");
		
		JComboBox comboBox_compensar = new JComboBox();
		comboBox_compensar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//String empleado = quien_eres.tu_nombre(null);
				String empleado = usuario;
				//String empleado = "Marcos Rodriguez";
				 //String año = "2012";
				
				JComboBox cb = (JComboBox)arg0.getSource();
		         año_combobox = (String)cb.getSelectedItem();
		        
		        rdbtnNewRadioButton.setSelected(true);
			//	String[] columnNames = {"Id","Fecha","Id_hora_extra","Fecha_caducidad","Mes Contabilizado","INICIO","FIN","TRAB","H_BASE","COEF","H_ORD_AJ","HORAS_A_COMPENSAR","DISFRUTADAS","CUANDO","horas disfrutadas_TOTAL"};
			//	Object[][] data = horas_a_compensar.listado_horas_compensar(empleado, año_combobox);
				
			//	table_2.setModel(new DefaultTableModel(data,columnNames));
				
		        DefaultTableModel horas_compensar = horas_a_compensar.listado_horas_compensar(empleado, año_combobox);
		        table_2.setModel(horas_compensar);
		        
		        
				table_2.setAutoCreateRowSorter(true);
				//table_2.setDefaultRenderer(Object.class, new MiRender());
			//	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			//	rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
			//	table_2.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(5).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(6).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(7).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(8).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(9).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(10).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(11).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(12).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(13).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(14).setCellRenderer( rightRenderer );
				table_2.setDefaultRenderer(Object.class, new MiRender());
						}
		});
		comboBox_compensar.setBounds(10, 11, 151, 20);
		comboBox_compensar.setModel(new DefaultComboBoxModel(AÑOS));
		comboBox_compensar.setSelectedIndex(Index);
		horas_compensar.add(comboBox_compensar);
		
		
	//////	rdbtnNewRadioButton = new JRadioButton("Todas");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String empleado = quien_eres.tu_nombre(null);
				String empleado = usuario;
				
		    	//String[] columnNames = {"Id","Fecha","Id_hora_extra","Fecha_caducidad","Mes Contabilizado","INICIO","FIN","TRAB","H_BASE","COEF","H_ORD_AJ","HORAS_A_COMPENSAR","DISFRUTADAS","CUANDO","horas disfrutadas_TOTAL"};
				//Object[][] data = horas_a_compensar.listado_horas_compensar(empleado, año_combobox);
				//table_2.setModel(new DefaultTableModel(data,columnNames));
				 DefaultTableModel horas_compensar = horas_a_compensar.listado_horas_compensar(empleado, año_combobox);
			        table_2.setModel(horas_compensar);
				
				table_2.setAutoCreateRowSorter(true);
			
				//DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				//rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
				//table_2.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(5).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(6).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(7).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(8).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(9).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(10).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(11).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(12).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(13).setCellRenderer( rightRenderer );
				//table_2.getColumnModel().getColumn(14).setCellRenderer( rightRenderer );
				table_2.setDefaultRenderer(Object.class, new MiRender());
			}
		});
		rdbtnNewRadioButton.setEnabled(true);
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(297, 10, 87, 23);
		horas_compensar.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Solo Parada");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String empleado = quien_eres.tu_nombre(null);
				String empleado = usuario;
		    	//String[] columnNames = {"Id","Fecha","Id_hora_extra","Fecha_caducidad","Mes Contabilizado","INICIO","FIN","TRAB","H_BASE","COEF","H_ORD_AJ","HORAS_A_COMPENSAR","DISFRUTADAS","CUANDO","horas disfrutadas_TOTAL"};
				//Object[][] data = horas_a_compensar.listado_horas_compensar_SOLO_PARADA(empleado, año_combobox);
				//table_2.setModel(new DefaultTableModel(data,columnNames));
			
				   DefaultTableModel presencia = horas_a_compensar.listado_horas_compensar_SOLO_PARADA(empleado, año_combobox);
					table_2.setModel(presencia);
					table_2.setAutoCreateRowSorter(true);
				
				
				
				table_2.setAutoCreateRowSorter(true);
			
			//	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			//	rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
			//	table_2.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(5).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(6).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(7).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(8).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(9).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(10).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(11).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(12).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(13).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(14).setCellRenderer( rightRenderer );
				
				table_2.setDefaultRenderer(Object.class, new MiRender());
				
				
				
				
			}
		});
		rdbtnNewRadioButton_1.setEnabled(true);
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(398, 10, 109, 23);
		horas_compensar.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnOtras = new JRadioButton("No Parada");
		rdbtnOtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String empleado = quien_eres.tu_nombre(null);
				String empleado = usuario;
		    	//String[] columnNames = {"Id","Fecha","Id_hora_extra","Fecha_caducidad","Mes Contabilizado","INICIO","FIN","TRAB","H_BASE","COEF","H_ORD_AJ","HORAS_A_COMPENSAR","DISFRUTADAS","CUANDO","horas disfrutadas_TOTAL"};
				//Object[][] data = horas_a_compensar.listado_horas_compensar_NO_PARADA(empleado, año_combobox);
				//table_2.setModel(new DefaultTableModel(data,columnNames));
			
				   DefaultTableModel presencia = horas_a_compensar.listado_horas_compensar_NO_PARADA(empleado, año_combobox);
					table_2.setModel(presencia);
					table_2.setAutoCreateRowSorter(true);
				
			//	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			//	rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
			//	table_2.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(5).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(6).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(7).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(8).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(9).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(10).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(11).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(12).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(13).setCellRenderer( rightRenderer );
			//	table_2.getColumnModel().getColumn(14).setCellRenderer( rightRenderer );
				
				
				table_2.setDefaultRenderer(Object.class, new MiRender());
				
			}
		});
		rdbtnOtras.setEnabled(true);
		buttonGroup.add(rdbtnOtras);
		rdbtnOtras.setBounds(509, 10, 109, 23);
		horas_compensar.add(rdbtnOtras);
		
		JPanel panel_3 = new JPanel();
		panel_3.setToolTipText("En este color las columnas Horas extras trabajadas");
		panel_3.setBackground(new Color(255, 235, 205));
		panel_3.setBounds(20, 52, 511, 40);
		horas_compensar.add(panel_3);
		
		JLabel lblHorasExtrasTrabajadas = new JLabel("Horas extras trabajadas");
		panel_3.add(lblHorasExtrasTrabajadas);
		
		JPanel panel_7 = new JPanel();
		panel_7.setToolTipText("En este color las columnas que tienen informaci\u00F3n del disfrute de las horas extras");
		panel_7.setBackground(new Color(230, 230, 250));
		panel_7.setBounds(804, 52, 286, 40);
		horas_compensar.add(panel_7);
		
		JLabel lblDisfruteDeHoras = new JLabel("Disfrute de horas extras");
		panel_7.add(lblDisfruteDeHoras);
		
		JPanel horas_a_compensar_usuario = new JPanel();
		tabbedPane.addTab("Horas a compensar", null, horas_a_compensar_usuario, null);
		horas_a_compensar_usuario.setLayout(null);
		
		JScrollPane horas_extras_trabajadas_scrollPane_4 = new JScrollPane();
		horas_extras_trabajadas_scrollPane_4.setBounds(20, 93, 745, 361);
		horas_a_compensar_usuario.add(horas_extras_trabajadas_scrollPane_4);
		
		horas_extras_trabajadas_table = new JTable();
		horas_extras_trabajadas_scrollPane_4.setViewportView(horas_extras_trabajadas_table);
		
		/*
		NUEVO
		*/
		
		JScrollPane disfrute_horas_extras_scrollPane_5 = new JScrollPane();
		disfrute_horas_extras_scrollPane_5.setBounds(775, 93, 315, 361);
		horas_a_compensar_usuario.add(disfrute_horas_extras_scrollPane_5);
		
		
		
		disfrute_horas_extras_table = new JTable();
		disfrute_horas_extras_scrollPane_5.setViewportView(disfrute_horas_extras_table);
	
		
		
		
		JComboBox comboBox_2 = new JComboBox();
		
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
					String empleado = usuario;
				
					
					JComboBox cb = (JComboBox)arg0.getSource();
			         año_combobox = (String)cb.getSelectedItem();
			       
			        rdbtnNewRadioButton.setSelected(true);
			
			        DefaultTableModel horas_compensar_nuevo = horas_a_compensar_nuevo.listado_horas_compensar(empleado, año_combobox);
			        
			        horas_extras_trabajadas_table.setModel(horas_compensar_nuevo);
			        
			        horas_extras_trabajadas_table.setAutoCreateRowSorter(true);
				
			        horas_extras_trabajadas_table.setDefaultRenderer(Object.class, new MiRender_nuevo());
			 //       DefaultTableModel disfrute_horas_extras_table_model = horas_a_compensar_nuevo.listado_horas_disfrute(empleado, año_combobox);
			        
			 //       disfrute_horas_extras_table.setModel(disfrute_horas_extras_table_model);
			        
			 //       disfrute_horas_extras_table.setAutoCreateRowSorter(true);
			 //       disfrute_horas_extras_table.setDefaultRenderer(Object.class, new MiRender_nuevo2());
			       			        
				
			}
		});
		
		comboBox_2.setBounds(20, 11, 151, 20);
		comboBox_2.setModel(new DefaultComboBoxModel(AÑOS));
		comboBox_2.setSelectedIndex(Index);
		horas_a_compensar_usuario.add(comboBox_2);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(20, 52, 745, 40);
		panel_8.setToolTipText("En este color las columnas Horas extras trabajadas");
		panel_8.setBackground(new Color(255, 235, 205));
		horas_a_compensar_usuario.add(panel_8);
		
		JLabel label_5 = new JLabel("Horas extras trabajadas");
		panel_8.add(label_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(775, 52, 315, 40);
		panel_9.setToolTipText("En este color las columnas que tienen informaci\u00F3n del disfrute de las horas extras");
		panel_9.setBackground(new Color(230, 230, 250));
		horas_a_compensar_usuario.add(panel_9);
		
		JLabel label_6 = new JLabel("Disfrute de horas extras");
		panel_9.add(label_6);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String empleado = usuario;
				
				
				JComboBox cb = (JComboBox)arg0.getSource();
		         año_combobox = (String)cb.getSelectedItem();
		       
		        rdbtnNewRadioButton.setSelected(true);
		
		   //     DefaultTableModel horas_compensar_nuevo = horas_a_compensar_nuevo.listado_horas_compensar(empleado, año_combobox);
		        
		    //    horas_extras_trabajadas_table.setModel(horas_compensar_nuevo);
		        
		   //     horas_extras_trabajadas_table.setAutoCreateRowSorter(true);
			
		    //    horas_extras_trabajadas_table.setDefaultRenderer(Object.class, new MiRender_nuevo());
		        DefaultTableModel disfrute_horas_extras_table_model = horas_a_compensar_nuevo.listado_horas_disfrute(empleado, año_combobox);
		        
		        disfrute_horas_extras_table.setModel(disfrute_horas_extras_table_model);
		        
		        disfrute_horas_extras_table.setAutoCreateRowSorter(true);
		        disfrute_horas_extras_table.setDefaultRenderer(Object.class, new MiRender_nuevo2());
				
				
				
				
				
				
			}
		});
		comboBox_3.setBounds(939, 11, 151, 20);
		comboBox_3.setModel(new DefaultComboBoxModel(AÑOS));
		comboBox_3.setSelectedIndex(Index);
		
		horas_a_compensar_usuario.add(comboBox_3);
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		JPanel horas_cobrar = new JPanel();
		tabbedPane.addTab("Horas a cobrar", null, horas_cobrar, null);
		horas_cobrar.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 44, 1064, 354);
		horas_cobrar.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		

		

		JLabel lblNewLabel = new JLabel("Total horas a cobrar a\u00F1o actual:");
		lblNewLabel.setBounds(10, 419, 184, 14);
		horas_cobrar.add(lblNewLabel);
		
		textField_total_horas_cobrar_año_actual = new JTextField();
		textField_total_horas_cobrar_año_actual.setEditable(false);
		textField_total_horas_cobrar_año_actual.setBounds(10, 434, 86, 20);
		horas_cobrar.add(textField_total_horas_cobrar_año_actual);
		textField_total_horas_cobrar_año_actual.setColumns(10);
		
		JLabel lblTotalHorasEfectivas = new JLabel("Total horas efectivas a cobrar a\u00F1o actual:(*)");
		lblTotalHorasEfectivas.setBounds(260, 419, 259, 14);
		horas_cobrar.add(lblTotalHorasEfectivas);
		
		textField_total_horas_cobrar_efectivas_año_actual = new JTextField();
		textField_total_horas_cobrar_efectivas_año_actual.setEditable(false);
		textField_total_horas_cobrar_efectivas_año_actual.setColumns(10);
		textField_total_horas_cobrar_efectivas_año_actual.setBounds(260, 434, 86, 20);
		horas_cobrar.add(textField_total_horas_cobrar_efectivas_año_actual);
		
		
		
		
		
		
		JComboBox comboBox_cobrar = new JComboBox();
		comboBox_cobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String empleado = usuario;
				
				JComboBox cb = (JComboBox)arg0.getSource();
		        String año = (String)cb.getSelectedItem();
		      
				
				DefaultTableModel modelo = horas_a_cobrar.listado_horas_cobrar(empleado, año);
				table_1.setModel(modelo);
			/*	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
				table_1.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(5).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(6).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(7).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(8).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(9).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(10).setCellRenderer( rightRenderer );
				table_1.getColumnModel().getColumn(11).setCellRenderer( rightRenderer );
			*/	
				table_1.setDefaultRenderer(Object.class, new MiRender_table_1());
				//
				table_1.setAutoCreateRowSorter(true);
				Float totales_cobradas = horas_a_cobrar.totales_horas_cobrar(empleado, año);
				
				if (totales_cobradas == null){
					totales_cobradas = Float.parseFloat("0");
				}
				
				Float totales_efectivas_cobradas = horas_a_cobrar.totales_horas_efectivas_cobrar(empleado, año);
				if (totales_efectivas_cobradas == null){
					totales_efectivas_cobradas = Float.parseFloat("0");
				}
				
				DecimalFormat dec = new DecimalFormat("##0.00"); 
				String totales_cobradas_string = dec.format(totales_cobradas);
				//System.out.println("totales_cobradas_string" + totales_cobradas_string);
				String totales_efectivas_cobradas_string = dec.format(totales_efectivas_cobradas);
				
				
				
			if (totales_efectivas_cobradas < 80) {
				
				textField_ACobrarAñoActualEfectivas.setBackground(new Color(152, 251, 152));
				textField_total_horas_cobrar_efectivas_año_actual.setBackground(new Color(152, 251, 152));
			}else{
			textField_ACobrarAñoActualEfectivas.setBackground(new Color(255, 0, 0));
			textField_total_horas_cobrar_efectivas_año_actual.setBackground(new Color(255, 0, 0));
			}
			textField_total_horas_cobrar_año_actual.setText(totales_cobradas_string);
			textField_total_horas_cobrar_efectivas_año_actual.setText(totales_efectivas_cobradas_string);
				
				
				
			textField_HorasACobrarAñoActual.setText(totales_cobradas_string);
				
			
			textField_ACobrarAñoActualEfectivas.setText(totales_efectivas_cobradas_string);
				
				
				
				
				
				
			}
		});
		comboBox_cobrar.setBounds(10, 11, 151, 20);
		comboBox_cobrar.setModel(new DefaultComboBoxModel(AÑOS));
		comboBox_cobrar.setSelectedIndex(Index);
	//	comboBox_1.setSelectedIndex(2);
		horas_cobrar.add(comboBox_cobrar);
		/*
		JLabel lblNewLabel = new JLabel("Total horas a cobrar a\u00F1o actual:");
		lblNewLabel.setBounds(10, 419, 184, 14);
		horas_cobrar.add(lblNewLabel);
		
		textField_total_horas_cobrar_año_actual = new JTextField();
		textField_total_horas_cobrar_año_actual.setEditable(false);
		textField_total_horas_cobrar_año_actual.setBounds(10, 434, 86, 20);
		horas_cobrar.add(textField_total_horas_cobrar_año_actual);
		textField_total_horas_cobrar_año_actual.setColumns(10);
		
		JLabel lblTotalHorasEfectivas = new JLabel("Total horas efectivas a cobrar a\u00F1o actual:(*)");
		lblTotalHorasEfectivas.setBounds(260, 419, 259, 14);
		horas_cobrar.add(lblTotalHorasEfectivas);
		
		textField_total_horas_cobrar_efectivas_año_actual = new JTextField();
		textField_total_horas_cobrar_efectivas_año_actual.setEditable(false);
		textField_total_horas_cobrar_efectivas_año_actual.setColumns(10);
		textField_total_horas_cobrar_efectivas_año_actual.setBounds(260, 434, 86, 20);
		horas_cobrar.add(textField_total_horas_cobrar_efectivas_año_actual);
		*/
		JPanel Vacaciones = new JPanel();
		tabbedPane.addTab("Vacaciones", null, Vacaciones, null);
		Vacaciones.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 333, 412);
		Vacaciones.add(scrollPane);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(370, 42, 166, 412);
		Vacaciones.add(panel);
		panel.setLayout(null);
		
		JLabel lblVacaciones_1 = new JLabel("Vacaciones:");
		lblVacaciones_1.setBounds(10, 11, 125, 14);
		panel.add(lblVacaciones_1);
		
		JLabel label = new JLabel("A\u00F1o anterior");
		label.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label.setBounds(27, 64, 118, 14);
		panel.add(label);
		
		textField_año_anterior = new JTextField();
		textField_año_anterior.setEditable(false);
		textField_año_anterior.setColumns(10);
		textField_año_anterior.setBounds(27, 84, 69, 20);
		panel.add(textField_año_anterior);
		
		
		textField_año_actual = new JTextField();
		textField_año_actual.setEditable(false);
		textField_año_actual.setColumns(10);
		textField_año_actual.setBounds(27, 158, 69, 20);
		panel.add(textField_año_actual);
		
		JLabel label_1 = new JLabel("A\u00F1o actual");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_1.setBounds(27, 138, 69, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Disfrutadas (a\u00F1o actual)");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_2.setBounds(27, 211, 118, 14);
		panel.add(label_2);
		
		textField_disfrutadas = new JTextField();
		textField_disfrutadas.setEditable(false);
		textField_disfrutadas.setColumns(10);
		textField_disfrutadas.setBounds(27, 231, 69, 20);
		panel.add(textField_disfrutadas);
		
		JLabel label_3 = new JLabel("Quedan (a\u00F1o actual)");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_3.setBounds(27, 288, 165, 14);
		panel.add(label_3);
		
		textField_quedan = new JTextField();
		textField_quedan.setEditable(false);
		textField_quedan.setColumns(10);
		textField_quedan.setBounds(27, 308, 69, 20);
		panel.add(textField_quedan);
		
		
		////////
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(AÑOS));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//String empleado = quien_eres.tu_nombre(null);
				String empleado = usuario;
				//String empleado = "Marcos Rodriguez";
				 //String año = "2012";
				
				JComboBox cb = (JComboBox)arg0.getSource();
		        String año = (String)cb.getSelectedItem();
		        //updateLabel(petName);
				
				String[] columnNames = {"Horas de vacaciones", "Fecha"};
				Object[][] data = vacaciones.listado_vacaciones(empleado, año);
				table.setModel(new DefaultTableModel(data,columnNames));
				table.setAutoCreateRowSorter(true);
				

				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
				table.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
				table.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
				
				
				Float totales_vacaciones = vacaciones.totales_vacaciones(empleado, año);
				if (totales_vacaciones == null){
					totales_vacaciones = Float.parseFloat("0");
				}
				Float vacaciones_año_anterior = vacaciones.vacaciones_año_anterior(empleado, año);
				if (vacaciones_año_anterior == null){
					vacaciones_año_anterior = Float.parseFloat("0");
				}
				Float vacaciones_a_disfrutar_año_actual = vacaciones.vacaciones_a_disfrutar_año_actual(empleado, año);
				if (vacaciones_a_disfrutar_año_actual == null){
					vacaciones_a_disfrutar_año_actual = Float.parseFloat("0");
				}
				
				/*
				if (totales_vacaciones != null || vacaciones_año_anterior != null || vacaciones_a_disfrutar_año_actual != null){
				
			
				}*/
				float horas_dia = Float.parseFloat("7.5");
				totales_vacaciones = totales_vacaciones / horas_dia;
				
				DecimalFormat dec = new DecimalFormat("##0.00"); 
				String totales_vacaciones_string = dec.format(totales_vacaciones);
				String vacaciones_año_anterior_string = dec.format(vacaciones_año_anterior);
				String vacaciones_a_disfrutar_año_actual_string = dec.format(vacaciones_a_disfrutar_año_actual);
			
				
				Float quedan_total = vacaciones_año_anterior+vacaciones_a_disfrutar_año_actual-totales_vacaciones;
				String quedan_string = dec.format(quedan_total);
			
				textField_quedan.setText(quedan_string);
				textField_disfrutadas.setText(totales_vacaciones_string);
				textField_año_anterior.setText(vacaciones_año_anterior_string);
				textField_año_actual.setText(vacaciones_a_disfrutar_año_actual_string);
				
			}
		});
		
		comboBox.setSelectedIndex(2);
		comboBox.setSelectedIndex(Index);
		comboBox.setBounds(10, 11, 151, 20);
		Vacaciones.add(comboBox);
		
		
		///////////////////resumen año actual
		
		//String empleado = quien_eres.tu_nombre(null);
	String empleado = usuario;
		
		Float totales_vacaciones = vacaciones.totales_vacaciones(empleado, año);
		Float vacaciones_año_anterior = vacaciones.vacaciones_año_anterior(empleado, año);
		Float vacaciones_a_disfrutar_año_actual = vacaciones.vacaciones_a_disfrutar_año_actual(empleado, año);
		if (totales_vacaciones != null || vacaciones_año_anterior != null || vacaciones_a_disfrutar_año_actual != null){
		
		float horas_dia = Float.parseFloat("7.5");
		totales_vacaciones = totales_vacaciones / horas_dia;
		}
		DecimalFormat dec = new DecimalFormat("##0.00"); 
		String totales_vacaciones_string = dec.format(totales_vacaciones);
		String vacaciones_año_anterior_string = dec.format(vacaciones_año_anterior);
		String vacaciones_a_disfrutar_año_actual_string = dec.format(vacaciones_a_disfrutar_año_actual);
	
		
		Float quedan_total = vacaciones_año_anterior+vacaciones_a_disfrutar_año_actual-totales_vacaciones;
		String quedan_string = dec.format(quedan_total);
		
		String eres = quien_eres.tu_nombre(null);
		JLabel lblDatosDe = new JLabel("Datos de: " + usuario);
		lblDatosDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosDe.setBounds(472, 28, 497, 14);
		contentPane.add(lblDatosDe);
		Integer año_integer = Integer.parseInt(año);
		Integer año_anterior = año_integer - 1;
		String año_anterior_string = String.valueOf(año_anterior);
		
		
		 float horas_totales_compensadas_año_actual = horas_a_compensar.totales_horas_compensdas(empleado, año);
		// float horas_totales_compensadas_año_anterior = horas_a_compensar.disfrutadas_año(empleado, año_anterior_string);
		// float horas_totales_generadas_año_anterior = horas_a_compensar.generadas_año(empleado, año_anterior_string);
		float horas_totales_compensadas_año_anterior = horas_a_compensar.disfrutadas_años_anteriores(empleado, año_anterior_string);
		float horas_totales_generadas_año_anterior = horas_a_compensar.generadas_años_anteriores(empleado, año_anterior_string);
			
		 
		 
		 
		 float horas_totales_generadas_año_actual = horas_a_compensar.generadas_año(empleado, año);
		
		 float pendientes_año_anterior = horas_totales_generadas_año_anterior -horas_totales_compensadas_año_anterior ;
		 float totales_a_compensar = pendientes_año_anterior + horas_totales_generadas_año_actual;
		
		float qudan_a_compensar = totales_a_compensar - horas_totales_compensadas_año_actual;
		
		
		
		 dec = new DecimalFormat("##0.00"); 
		
		String horas_totales_compensadas_año_actual_string = dec.format(horas_totales_compensadas_año_actual);
		String horas_totales_compensadas_año_anterior_string = dec.format(horas_totales_compensadas_año_anterior);
		String horas_totales_generadas_año_anterior_string = dec.format(horas_totales_generadas_año_anterior);
		String horas_totales_generadas_año_actual_string = dec.format(horas_totales_generadas_año_actual);
		String pendientes_año_anterior_string = dec.format(pendientes_año_anterior);
		String totales_a_compensar_string = dec.format(totales_a_compensar);
		String qudan_a_compensar_string = dec.format(qudan_a_compensar);
		textField_QUEDAN_PARA_AÑO_SIGUIENTE.setText(pendientes_año_anterior_string);
		
		textField_HorasTotalesACompensar.setText(horas_totales_generadas_año_actual_string);
		textField_HorasCompensadas.setText(horas_totales_compensadas_año_actual_string);
		textField_HorasCompensarQuedan.setText(qudan_a_compensar_string);
		
		JLabel lblInclaoanterior = new JLabel("INCL.A\u00D1O.ANTERIOR");
		lblInclaoanterior.setToolTipText("Suma de horas extras generadas el a\u00F1o actual y las pendientes del a\u00F1o anterior");
		lblInclaoanterior.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblInclaoanterior.setBounds(108, 11, 103, 14);
		panel_2.add(lblInclaoanterior);
		
	
		textField_INCL_AÑO_ANT.setText(totales_a_compensar_string);
		textField_VacacionesQuedan.setText(quedan_string);
		textField_VacacionesDisfrutadas.setText(totales_vacaciones_string);
		textField_VacacionesAñoAnterior.setText(vacaciones_año_anterior_string);
		textField_VacacionesActual.setText(vacaciones_a_disfrutar_año_actual_string);
		
			JPanel Presencia = new JPanel();
		
		
		Presencia.setLayout(null);
		
if (absentismo.equals("1")){
		tabbedPane.addTab("Presencia", null, Presencia, null);
}
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 42, 888, 412);
		Presencia.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(AÑOS));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String empleado = usuario;
			
				JComboBox cb = (JComboBox)arg0.getSource();
		        String año = (String)cb.getSelectedItem();
			
		        
		        DefaultTableModel presencia = dias_presencia.listado_dias_presencia(empleado, año);
				table_3.setModel(presencia);
				table_3.setAutoCreateRowSorter(true);
				
				//DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			//	rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
			//	table_3.getColumnModel().getColumn(0).setCellRenderer( rightRenderer );
			//	table_3.getColumnModel().getColumn(1).setCellRenderer( rightRenderer );
			//	table_3.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
			//	table_3.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );	
			//	table_3.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
			//	table_3.getColumnModel().getColumn(5).setCellRenderer( rightRenderer );
			//	table_3.getColumnModel().getColumn(6).setCellRenderer( rightRenderer );
			//	table_3.getColumnModel().getColumn(7).setCellRenderer( rightRenderer );
				table_3.setDefaultRenderer(Object.class, new MiRender2());
				
				
			}
		});
		comboBox_1.setSelectedIndex(2);
		comboBox_1.setBounds(10, 11, 151, 20);
		Presencia.add(comboBox_1);
		
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inicio frame2 = new inicio();
				frame2.setVisible(true);
				datos_usuario.this.dispose();
				//frame.setVisible(false);
				//System.exit(0);	
			}
		});
		btnSalir.setBounds(1026, 26, 89, 23);
		contentPane.add(btnSalir);
		
		
		JTableHeader header = table_2.getTableHeader();
		ColumnHeaderToolTips tips = new ColumnHeaderToolTips();
		 TableColumn col = table_2.getColumnModel().getColumn(0);
		 tips.setToolTip(col, "Identificador Hora extra");
		  col = table_2.getColumnModel().getColumn(1);
		 tips.setToolTip(col, "Fecha en que se hizo la hora extra");
		 col = table_2.getColumnModel().getColumn(2);
		 tips.setToolTip(col, "Mes en que se contabiliza la hora extra");
		 col = table_2.getColumnModel().getColumn(3);
		 tips.setToolTip(col, "Hora de inicio");
		 col = table_2.getColumnModel().getColumn(4);
		 tips.setToolTip(col, "Hora de fin");
		 col = table_2.getColumnModel().getColumn(5);
		 tips.setToolTip(col, "Horas Trabajadas");
		 col = table_2.getColumnModel().getColumn(6);
		 tips.setToolTip(col, "Horas base");
		 col = table_2.getColumnModel().getColumn(7);
		 tips.setToolTip(col, "Coef. de hora extra");
		 col = table_2.getColumnModel().getColumn(8);
		 tips.setToolTip(col, "Horas ordinarias ajustadas");
		 col = table_2.getColumnModel().getColumn(9);
		 tips.setToolTip(col, "Horas a compensar");
		 col = table_2.getColumnModel().getColumn(10);
		 tips.setToolTip(col, "Identificador de hora compensada");
		 col = table_2.getColumnModel().getColumn(11);
		 tips.setToolTip(col, "Cuando se ha compensado");
		 col = table_2.getColumnModel().getColumn(12);
		 tips.setToolTip(col, "Horas disfrutadas");
		 col = table_2.getColumnModel().getColumn(13);
		 tips.setToolTip(col, "Horas totales disfrutadas");
		
		
		 
		header.addMouseMotionListener(tips);
		
		
		
	}
	
	
	public class ColumnHeaderToolTips extends MouseMotionAdapter {
	    // Current column whose tooltip is being displayed.
	    // This variable is used to minimize the calls to setToolTipText().
	    TableColumn curCol;

	    // Maps TableColumn objects to tooltips
	    Map tips = new HashMap();

	    // If tooltip is null, removes any tooltip text.
	    public void setToolTip(TableColumn col, String tooltip) {
	        if (tooltip == null) {
	            tips.remove(col);
	        } else {
	            tips.put(col, tooltip);
	        }
	    }

	    public void mouseMoved(MouseEvent evt) {
	        TableColumn col = null;
	        JTableHeader header = (JTableHeader)evt.getSource();
	        JTable table = header.getTable();
	        TableColumnModel colModel = table.getColumnModel();
	        int vColIndex = colModel.getColumnIndexAtX(evt.getX());

	        // Return if not clicked on any column header
	        if (vColIndex >= 0) {
	            col = colModel.getColumn(vColIndex);
	        }

	        if (col != curCol) {
	            header.setToolTipText((String)tips.get(col));
	            curCol = col;
	        }
	    }
	}
	
	
	
	
	
	
	
	public class MiRenderOLD extends DefaultTableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
			
			if (Float.parseFloat(((String)table.getValueAt(row, 11)).replace(",",".")) > Float.parseFloat(((String)table.getValueAt(row, 14)).replace(",",".")))
					
				{
				setOpaque(true);
				setHorizontalAlignment( JLabel.RIGHT );
				setBackground(Color.green);
				setForeground(Color.blue);	
				}else{
				//	setOpaque(true);
					setHorizontalAlignment( JLabel.RIGHT );
					setBackground(Color.white);
					//setForeground(Color.blue);	
				}
				return this;
		}
	}
	
	public class MiRender extends DefaultTableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
						
		//	if (Float.parseFloat(((String)table.getValueAt(row, 11)).replace(",",".")) > Float.parseFloat(((String)table.getValueAt(row, 14)).replace(",",".")))
			//if (((Float)table.getValueAt(row, 12)) > ((Double)table.getValueAt(row, 15)))
			if (((Float)table.getValueAt(row, 9)) > ((Double)table.getValueAt(row, 13)))
			//if (true)
				{
				setOpaque(true);
				setHorizontalAlignment( JLabel.RIGHT );
				setBackground(Color.green);
				setForeground(Color.blue);	
				}else{
					if (column < 10){
							setHorizontalAlignment( JLabel.RIGHT );
						   setOpaque(true);
						   setBackground(new Color(255, 235, 205));
						//   setBackground(Color.white);
					   }else{
						   setHorizontalAlignment( JLabel.RIGHT );
						   setOpaque(true);
						   setBackground(new Color(230, 230, 250));
						 //  setBackground(Color.white);
					   }
						
				}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			String date = "1900/12/30" ;
			Date utilDate;
			try {
				utilDate = formatter.parse(date);
			
			if (value instanceof Date && ((Date) value).after(utilDate)) {
				String strDate = new SimpleDateFormat("dd/MM/yyyy").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );			
			}
			
			if (value instanceof Date && !((Date) value).after(utilDate)){
				String strDate = new SimpleDateFormat("hh:mm").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );		
			}
			
		  
			
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (value instanceof Float || value instanceof Double) {
				DecimalFormat df = new DecimalFormat("0.00");
				this.setText(df.format(value));
			}
			
			
			
			JTableHeader header = table.getTableHeader();
			ColumnHeaderToolTips tips = new ColumnHeaderToolTips();
			 TableColumn col = table.getColumnModel().getColumn(0);
			 tips.setToolTip(col, "Identificador Hora extra");
			  col = table.getColumnModel().getColumn(1);
			 tips.setToolTip(col, "Fecha en que se hizo la hora extra");
			 col = table.getColumnModel().getColumn(2);
			 tips.setToolTip(col, "Mes en que se contabiliza la hora extra");
			 col = table.getColumnModel().getColumn(3);
			 tips.setToolTip(col, "Hora de inicio");
			 col = table.getColumnModel().getColumn(4);
			 tips.setToolTip(col, "Hora de fin");
			 col = table.getColumnModel().getColumn(5);
			 tips.setToolTip(col, "Horas Trabajadas");
			 col = table.getColumnModel().getColumn(6);
			 tips.setToolTip(col, "Horas base");
			 col = table.getColumnModel().getColumn(7);
			 tips.setToolTip(col, "Coef. de hora extra");
			 col = table.getColumnModel().getColumn(8);
			 tips.setToolTip(col, "Horas ordinarias ajustadas");
			 col = table.getColumnModel().getColumn(9);
			 tips.setToolTip(col, "Horas a compensar");
			 col = table.getColumnModel().getColumn(10);
			 tips.setToolTip(col, "Identificador de hora compensada");
			 col = table.getColumnModel().getColumn(11);
			 tips.setToolTip(col, "Cuando se ha compensado");
			 col = table.getColumnModel().getColumn(12);
			 tips.setToolTip(col, "Horas disfrutadas");
			 col = table.getColumnModel().getColumn(13);
			 tips.setToolTip(col, "Horas totales disfrutadas");
			
			
			 
			header.addMouseMotionListener(tips);
			
			
						
			
			
			
				return this;
		}
	}
	
	
	
	
	public class MiRender2 extends DefaultTableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
		//	SimpleDateFormat f = new SimpleDateFormat("dd/MM/aa");

			if (value instanceof Date){
				String strDate = new SimpleDateFormat("dd/MM/yyyy").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );
				}else{
					setHorizontalAlignment( JLabel.RIGHT );
					//setBackground(Color.blue);
					setBackground(Color.white);
				}
				return this;
		}
	}
	
	public class MiRender_table_1 extends DefaultTableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
		//	SimpleDateFormat f = new SimpleDateFormat("dd/MM/aa");
			//String value_string = (String) value;
			//if (value instanceof Date && column == 2 ){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			String date = "1900/12/30" ;
			Date utilDate;
			try {
				utilDate = formatter.parse(date);
			
						
			if (value instanceof Date && ((Date) value).after(utilDate)) {
				String strDate = new SimpleDateFormat("dd/MM/yyyy").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );			
				}else{
					setHorizontalAlignment( JLabel.RIGHT );
					//setBackground(Color.blue);
					setBackground(Color.white);
				}
			
			//if (value instanceof Date && column != 2 ){
				if (value instanceof Date && !((Date) value).after(utilDate)){
				String strDate = new SimpleDateFormat("hh:mm").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );			
				}else{
					setHorizontalAlignment( JLabel.RIGHT );
					//setBackground(Color.blue);
					setBackground(Color.white);
				}
			if (value instanceof Float) {
				DecimalFormat df = new DecimalFormat("#.00");
				this.setText(df.format(value));
			}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
				return this;
		}
	
	}

	public class MiRender_nuevo extends DefaultTableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
						
		//	if (Float.parseFloat(((String)table.getValueAt(row, 11)).replace(",",".")) > Float.parseFloat(((String)table.getValueAt(row, 14)).replace(",",".")))
			//if (((Float)table.getValueAt(row, 12)) > ((Double)table.getValueAt(row, 15)))
			//if (((Float)table.getValueAt(row, 9)) > ((Double)table.getValueAt(row, 13)))
			
							setHorizontalAlignment( JLabel.RIGHT );
						   setOpaque(true);
						   setBackground(new Color(255, 235, 205));
						//   setBackground(Color.white);
					  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			String date = "1900/12/30" ;
			Date utilDate;
			try {
				utilDate = formatter.parse(date);
			
			if (value instanceof Date && ((Date) value).after(utilDate)) {
				String strDate = new SimpleDateFormat("dd/MM/yyyy").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );			
			}
			
			if (value instanceof Date && !((Date) value).after(utilDate)){
				String strDate = new SimpleDateFormat("hh:mm").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );		
			}
			
		  
			
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (value instanceof Float || value instanceof Double) {
				DecimalFormat df = new DecimalFormat("0.00");
				this.setText(df.format(value));
			}
			
			
			/*
			JTableHeader header = table.getTableHeader();
			ColumnHeaderToolTips tips = new ColumnHeaderToolTips();
			 TableColumn col = table.getColumnModel().getColumn(0);
			 tips.setToolTip(col, "Identificador Hora extra");
			  col = table.getColumnModel().getColumn(1);
			 tips.setToolTip(col, "Fecha en que se hizo la hora extra");
			 col = table.getColumnModel().getColumn(2);
			 tips.setToolTip(col, "Mes en que se contabiliza la hora extra");
			 col = table.getColumnModel().getColumn(3);
			 tips.setToolTip(col, "Hora de inicio");
			 col = table.getColumnModel().getColumn(4);
			 tips.setToolTip(col, "Hora de fin");
			 col = table.getColumnModel().getColumn(5);
			 tips.setToolTip(col, "Horas Trabajadas");
			 col = table.getColumnModel().getColumn(6);
			 tips.setToolTip(col, "Horas base");
			 col = table.getColumnModel().getColumn(7);
			 tips.setToolTip(col, "Coef. de hora extra");
			 col = table.getColumnModel().getColumn(8);
			 tips.setToolTip(col, "Horas ordinarias ajustadas");
			 col = table.getColumnModel().getColumn(9);
			 tips.setToolTip(col, "Horas a compensar");
			 col = table.getColumnModel().getColumn(10);
			 tips.setToolTip(col, "Identificador de hora compensada");
			 col = table.getColumnModel().getColumn(11);
			 tips.setToolTip(col, "Cuando se ha compensado");
			 col = table.getColumnModel().getColumn(12);
			 tips.setToolTip(col, "Horas disfrutadas");
			 col = table.getColumnModel().getColumn(13);
			 tips.setToolTip(col, "Horas totales disfrutadas");
			
			
			 
			header.addMouseMotionListener(tips);
			
			*/
						
			
			
			
				return this;
		}
	}

	public class MiRender_nuevo2 extends DefaultTableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table,
				Object value,
				boolean isSelected,
				boolean hasFocus,
				int row,
				int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				
			 setHorizontalAlignment( JLabel.RIGHT );
			   setOpaque(true);
			   setBackground(new Color(230, 230, 250));
						//   setBackground(Color.white);
					  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			String date = "1900/12/30" ;
			Date utilDate;
			try {
				utilDate = formatter.parse(date);
			
			if (value instanceof Date && ((Date) value).after(utilDate)) {
				String strDate = new SimpleDateFormat("dd/MM/yyyy").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );			
			}
			
			if (value instanceof Date && !((Date) value).after(utilDate)){
				String strDate = new SimpleDateFormat("hh:mm").format((Date)value);
				this.setText( strDate );
				setOpaque(true);
				//setBackground(Color.gray);
				setHorizontalAlignment( JLabel.RIGHT );		
			}
			
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (value instanceof Float || value instanceof Double) {
				DecimalFormat df = new DecimalFormat("0.00");
				this.setText(df.format(value));
			}
			
			
			/*
			JTableHeader header = table.getTableHeader();
			ColumnHeaderToolTips tips = new ColumnHeaderToolTips();
			 TableColumn col = table.getColumnModel().getColumn(0);
			 tips.setToolTip(col, "Identificador Hora extra");
			  col = table.getColumnModel().getColumn(1);
			 tips.setToolTip(col, "Fecha en que se hizo la hora extra");
			 col = table.getColumnModel().getColumn(2);
			 tips.setToolTip(col, "Mes en que se contabiliza la hora extra");
			 col = table.getColumnModel().getColumn(3);
			 tips.setToolTip(col, "Hora de inicio");
			 col = table.getColumnModel().getColumn(4);
			 tips.setToolTip(col, "Hora de fin");
			 col = table.getColumnModel().getColumn(5);
			 tips.setToolTip(col, "Horas Trabajadas");
			 col = table.getColumnModel().getColumn(6);
			 tips.setToolTip(col, "Horas base");
			 col = table.getColumnModel().getColumn(7);
			 tips.setToolTip(col, "Coef. de hora extra");
			 col = table.getColumnModel().getColumn(8);
			 tips.setToolTip(col, "Horas ordinarias ajustadas");
			 col = table.getColumnModel().getColumn(9);
			 tips.setToolTip(col, "Horas a compensar");
			 col = table.getColumnModel().getColumn(10);
			 tips.setToolTip(col, "Identificador de hora compensada");
			 col = table.getColumnModel().getColumn(11);
			 tips.setToolTip(col, "Cuando se ha compensado");
			 col = table.getColumnModel().getColumn(12);
			 tips.setToolTip(col, "Horas disfrutadas");
			 col = table.getColumnModel().getColumn(13);
			 tips.setToolTip(col, "Horas totales disfrutadas");
			
			
			 
			header.addMouseMotionListener(tips);
			
			*/
						
			
			
			
				return this;
		}
	}
}









