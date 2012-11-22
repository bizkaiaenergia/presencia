

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;


public class horas_a_compensar_nuevo {
	
	
	
	public static DefaultTableModel  listado_horas_compensar(String empleado,String año){
	DefaultTableModel modelo = null; 
	
		String ID_EMPLEADO = "";
	//	Object[][] data = null;
	try {	
	
		
			propiedades Archivopropiedades = new propiedades();
		String servidor_sql = Archivopropiedades.servidor_SQL();
		String basededatos = Archivopropiedades.base_de_datos();
		String jtds_string = servidor_sql + basededatos;
		Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
		Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
		Statement s = conexion.createStatement();
		String fecha_inicio_año = año + "-01-01";
		String fecha_fin_año = año + "-12-31";
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
	
			while (rs.next()) 
		{ 
		
			 ID_EMPLEADO = rs.getString(1);
			
	
		}
		
			
			
			
			Statement s2 = conexion.createStatement();
			
			
			
			
			
			
			
			//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Connection conexion2 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			//Statement s2 = conexion2.createStatement();
	
			
			
			
	//	String query3 = "SELECT * from Horas_a_disfrutar_por_empleado_JAVA WHERE (ID_empleado = '"+ID_EMPLEADO+"') AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";
		String query3 = "SELECT Id_hora_extra as [ID extra],Fecha,[Mes Contabilizado] as [MES Cont],Inicio_hora_extra as [H IN],Fin_hora_extra as [H FIN],Horas_extras_trab as [H TRAB],Horas_extras_base as [H BASE],Coeficiente_ajuste as [COEF],horas_ordinarias_ajustadas as [H Ord AJ],horas_ordinarias_compensar as [H Comp] from Datos_horas_extras WHERE (ID_empleado = '"+ID_EMPLEADO+"') AND (horas_ordinarias_compensar <> 0) AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";	
			
	//System.out.println (query3); 
	ResultSet rs3 = s2.executeQuery (query3);
	
	
	 modelo = new DefaultTableModel();
		
		ResultSetMetaData metaDatos = rs3.getMetaData();
		
		// Se obtiene el número de columnas.
		int numeroColumnas = metaDatos.getColumnCount();

		
		// Se crea un array de etiquetas para rellenar
		Object[] etiquetas = new Object[numeroColumnas];
		
		// Se obtiene cada una de las etiquetas para cada columna
		for (int i = 0; i < numeroColumnas; i++)
		{
		   // Nuevamente, para ResultSetMetaData la primera columna es la 1.
		   etiquetas[i] = metaDatos.getColumnLabel(i + 1);
		}
		
		
		modelo.setColumnIdentifiers(etiquetas);
		
		
		while (rs3.next())
		{
		   // Se crea un array que será una de las filas de la tabla.
		   Object [] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla

		   // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		   for (int i=0;i<numeroColumnas;i++)
		      fila[i] = rs3.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

		   // Se añade al modelo la fila completa.
		   modelo.addRow(fila);
		}
	
	
	
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	return modelo;
	
	
	
	
	}
	
	
	public static DefaultTableModel  listado_horas_disfrute(String empleado,String año){
		DefaultTableModel modelo = null; 
		
			String ID_EMPLEADO = "";
		//	Object[][] data = null;
		try {	
		
			
				propiedades Archivopropiedades = new propiedades();
			String servidor_sql = Archivopropiedades.servidor_SQL();
			String basededatos = Archivopropiedades.base_de_datos();
			String jtds_string = servidor_sql + basededatos;
			Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
			Statement s = conexion.createStatement();
			String fecha_inicio_año = año + "-01-01";
			String fecha_fin_año = año + "-12-31";
			String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
			ResultSet rs = s.executeQuery (query1);
		
				while (rs.next()) 
			{ 
			
				 ID_EMPLEADO = rs.getString(1);
				
		
			}
			
				
				
				
				Statement s_new = conexion.createStatement();
				
			
				
		//	String query3 = "SELECT * from Horas_a_disfrutar_por_empleado_JAVA WHERE (ID_empleado = '"+ID_EMPLEADO+"') AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";
			String query3 = "SELECT Id_horas_extras_d as [ID extra],ID,Fecha_d as [Fecha],Numero_horas_d as [H DISFRUT] from Datos_horas_extras_disfrutadas WHERE (ID_empleado = '"+ID_EMPLEADO+"')  AND ((Fecha_d >= {D '"+fecha_inicio_año+"'}  And Fecha_d <= {D '"+fecha_fin_año+"'}))";	
		//	String query3 = "SELECT * from Datos_horas_extras";
		//System.out.println (query3); 
		ResultSet rs3 = s_new.executeQuery (query3);
		
		
		 modelo = new DefaultTableModel();
			
			ResultSetMetaData metaDatos = rs3.getMetaData();
			
			// Se obtiene el número de columnas.
			int numeroColumnas = metaDatos.getColumnCount();

			
			// Se crea un array de etiquetas para rellenar
			Object[] etiquetas = new Object[numeroColumnas];
			
			// Se obtiene cada una de las etiquetas para cada columna
			for (int i = 0; i < numeroColumnas; i++)
			{
			   // Nuevamente, para ResultSetMetaData la primera columna es la 1.
			   etiquetas[i] = metaDatos.getColumnLabel(i + 1);
			}
			
			
			modelo.setColumnIdentifiers(etiquetas);
			
			
			while (rs3.next())
			{
			   // Se crea un array que será una de las filas de la tabla.
			   Object [] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla

			   // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
			   for (int i=0;i<numeroColumnas;i++)
			      fila[i] = rs3.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

			   // Se añade al modelo la fila completa.
			   modelo.addRow(fila);
			}
		
		
		
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return modelo;
		
		
		
		
		}
		
	
	
	
	
}
