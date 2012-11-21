

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


public class horas_a_compensar {
	
	
	
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
		
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion2 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			Statement s2 = conexion2.createStatement();
	
			
			
			
	//	String query3 = "SELECT * from Horas_a_disfrutar_por_empleado_JAVA WHERE (ID_empleado = '"+ID_EMPLEADO+"') AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";
		String query3 = "SELECT Id_hora_extra as [ID extra],Fecha,[Mes Contabilizado] as [MES Cont],INICIO as [H IN],FIN as [H FIN],TRAB as [H TRAB],H_BASE as [H BASE],COEF,H_ORD_AJ as [H Ord AJ],HORAS_A_COMPENSAR as [H Comp],ID,Cuando,Disfrutadas as [Disfrut],[horas disfrutadas_TOTAL] as [H disfrut total] from Horas_a_disfrutar_por_empleado_JAVA WHERE (ID_empleado = '"+ID_EMPLEADO+"') AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";	
			
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
	public static DefaultTableModel listado_horas_compensar_SOLO_PARADA(String empleado,String año){
		
		String ID_EMPLEADO = "";
		DefaultTableModel modelo = null; 
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
		//System.out.println (query1); 
			while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 
	
		}
		//	propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion2 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			Statement s2 = conexion2.createStatement();
	/*
	
	
	
	String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
			"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
			"horas_ordinarias_cobrar,horas_efectivas_a_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"') ORDER BY Fecha"; 
	*/
	//////bueno
	//		String query3 = "SELECT * from Horas_a_disfrutar_por_empleado_JAVA WHERE ID_empleado = '"+ID_EMPLEADO+"'  ";
	/////bueno
			
		//	String query3 = "SELECT * from Horas_a_disfrutar_por_empleado_JAVA_PP WHERE (ID_empleado = '"+ID_EMPLEADO+"') and 'Mes Contabilizado' Not Like '*PP*' AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";
			String query3 = "SELECT Id_hora_extra as [ID extra],Fecha,[Mes Contabilizado] as [MES Cont],INICIO as [H IN],FIN as [H FIN],TRAB as [H TRAB],H_BASE as [H BASE],COEF,H_ORD_AJ as [H Ord AJ],HORAS_A_COMPENSAR as [H Comp],Id,Cuando,Disfrutadas as [Disfrut],[horas disfrutadas_TOTAL] as [H disfrut total] from Horas_a_disfrutar_por_empleado_JAVA_PP WHERE (ID_empleado = '"+ID_EMPLEADO+"') and 'Mes Contabilizado' Not Like '*PP*' AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";
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
	
	
	public static DefaultTableModel  listado_horas_compensar_NO_PARADA(String empleado,String año){
		
		String ID_EMPLEADO = "";
		DefaultTableModel modelo = null; 
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
		//System.out.println (query1); 
			while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 
	
		}
		//	propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion2 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			Statement s2 = conexion2.createStatement();
	
			//String query3 = "SELECT * from Horas_a_disfrutar_por_empleado_JAVA_NOT_PP WHERE (ID_empleado = '"+ID_EMPLEADO+"') and 'Mes Contabilizado' Not Like '*PP*' AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";
			String query3 = "SELECT Id_hora_extra as [ID extra],Fecha,[Mes Contabilizado] as [MES Cont],INICIO as [H IN],FIN as [H FIN],TRAB as [H TRAB],H_BASE as [H BASE],COEF,H_ORD_AJ as [H Ord AJ],HORAS_A_COMPENSAR as [H Comp],Id,Cuando,Disfrutadas as [Disfrut],[horas disfrutadas_TOTAL] as [H disfrut total] from Horas_a_disfrutar_por_empleado_JAVA_NOT_PP WHERE (ID_empleado = '"+ID_EMPLEADO+"') and 'Mes Contabilizado' Not Like '*PP*' AND ((Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'}))";
			
			
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
	
	
	
	
	
	public static Float totales_horas_compensdas (String empleado,String año){
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
			propiedades Archivopropiedades = new propiedades();
			//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			String servidor_sql = Archivopropiedades.servidor_SQL();
			String basededatos = Archivopropiedades.base_de_datos();
			String jtds_string = servidor_sql + basededatos;
			
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			//Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
			
			//Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			
		Statement s = conexion.createStatement();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_inicio_año = año + "-01-01";
		String fecha_fin_año = año + "-12-31";
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
		String Horas=null;	
		while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 

		}
		
		/*String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
				"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
				"horas_ordinarias_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"') ORDER BY Fecha"; 
		*/
		
		
		String query2 = "SELECT Sum(Numero_horas_d),Id_empleado FROM Datos_horas_extras_disfrutadas WHERE ((Fecha_d >= {D '"+fecha_inicio_año+"'}  And Fecha_d <= {D '"+fecha_fin_año+"'})) GROUP BY Id_empleado HAVING Id_empleado ='"+ID_EMPLEADO+"'";
		
		//String query2 = "SELECT sum(horas_ordinarias_cobrar) FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
		//System.out.println (query2);
		ResultSet rs2 = s.executeQuery (query2);
		
		//System.out.println (query2); 
		while (rs2.next()) 
		{ 
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 Horas = (df.format(rs2.getFloat(1)));
		}	
			if (Horas != null) {
				totales=Float.parseFloat(Horas.replace(",","."));
					
				} else{
				totales = Float.parseFloat("0");	
				}
			
			
			//System.out.println (Horas); 
			
		
		
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
		
	return totales;

	
	
	}
	//generadas año TODAS 
	public static Float generadas_año (String empleado,String año){
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
			propiedades Archivopropiedades = new propiedades();
			//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			String servidor_sql = Archivopropiedades.servidor_SQL();
			String basededatos = Archivopropiedades.base_de_datos();
			String jtds_string = servidor_sql + basededatos;
			
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			//Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
			
			//Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			
		Statement s = conexion.createStatement();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_inicio_año = año + "-01-01";
		
		String fecha_fin_año = año + "-12-31";
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
		while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 

		}
		
		/*String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
				"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
				"horas_ordinarias_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"') ORDER BY Fecha"; 
		*/
		//generadas año 
		String query2 = "SELECT Sum(horas_ordinarias_compensar) FROM Datos_horas_extras  WHERE (Id_empleado ='"+ID_EMPLEADO+"') AND (Fecha >= {D '"+fecha_inicio_año+"'})  And (Fecha <= {D '"+fecha_fin_año+"'})";
//		String query2 = "SELECT Sum(Datos_horas_extras.horas_ordinarias_compensar) AS SumaDehoras_ordinarias_compensar FROM Datos_horas_extras WHERE (((Datos_horas_extras.Id_empleado)=""" & [Id_empleado] & """) AND ((Datos_horas_extras.Fecha)>= CDate(""01/01/" & [Lista21] - 1 & """) And (Datos_horas_extras.Fecha)< CDate(""01/01/" & [Lista21] & """)));";

		
		//String query2 = "SELECT sum(horas_ordinarias_cobrar) FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
		//System.out.println (query2);
		ResultSet rs2 = s.executeQuery (query2);
		//System.out.println (query2); 
		String Horas=null;
		while (rs2.next()) 
		{ 
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			Horas = (df.format(rs2.getFloat(1)));
		}	
			if (Horas != null) {
				totales=Float.parseFloat(Horas.replace(",","."));
					
				} else{
				totales = Float.parseFloat("0");	
				}
			
			
			//System.out.println (Horas + "generadas año "+ año); 
			
				

		
		
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
		
	return totales;

	
		
		
}
	
	
	//disfrutadas año TODAS 
	public static Float disfrutadas_año (String empleado,String año){
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
			propiedades Archivopropiedades = new propiedades();
			//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			String servidor_sql = Archivopropiedades.servidor_SQL();
			String basededatos = Archivopropiedades.base_de_datos();
			String jtds_string = servidor_sql + basededatos;
			
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			//Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
			
			//Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			
		Statement s = conexion.createStatement();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_inicio_año = año + "-01-01";
	
		String fecha_fin_año = año + "-12-31";
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
		while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 

		}
		
		
	
		//disfrutadas año //
		String query3 = "SELECT Sum(Numero_horas_d) FROM Datos_horas_extras INNER JOIN Datos_horas_extras_disfrutadas ON (Datos_horas_extras.Id_empleado = Datos_horas_extras_disfrutadas.Id_empleado) AND (Datos_horas_extras.Id_hora_extra = Datos_horas_extras_disfrutadas.Id_horas_extras_d) WHERE (Datos_horas_extras.Id_empleado)='"+ID_EMPLEADO+"' AND (Datos_horas_extras_disfrutadas.Fecha_d >= {D '"+fecha_inicio_año+"'})  AND (Datos_horas_extras_disfrutadas.Fecha_d <= {D '"+fecha_fin_año+"'}) AND (Datos_horas_extras.Fecha >= {D '"+fecha_inicio_año+"'})  And (Datos_horas_extras.Fecha <= {D '"+fecha_fin_año+"'})";
//	
	//	System.out.println (query3);
		ResultSet rs3 = s.executeQuery (query3);
		//System.out.println (query3); 
		while (rs3.next()) 
		{ 
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			String Horas = (df.format(rs3.getFloat(1)));
			
			if (Horas != null) {
				totales=Float.parseFloat(Horas.replace(",","."));
					
				} else{
				totales = Float.parseFloat("0");	
				}
			
			
			//System.out.println (Horas + "disfrutadas año " + año); 
			
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
		
	return totales;

	
		
		
}
	
	
	public static Float generadas_años_anteriores (String empleado,String año){
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
			propiedades Archivopropiedades = new propiedades();
			//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			String servidor_sql = Archivopropiedades.servidor_SQL();
			String basededatos = Archivopropiedades.base_de_datos();
			String jtds_string = servidor_sql + basededatos;
			
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			//Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
			
			//Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			
		Statement s = conexion.createStatement();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String fecha_inicio_año = año + "-01-01";
		Integer año_integer = Integer.parseInt( año)-2;
		
		String fecha_inicio_año = año_integer + "-01-01";
		
		String fecha_fin_año = año + "-12-31";
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
		while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 

		}
		
		/*String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
				"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
				"horas_ordinarias_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"') ORDER BY Fecha"; 
		*/
		//generadas año 
		String query2 = "SELECT Sum(horas_ordinarias_compensar) FROM Datos_horas_extras  WHERE (Id_empleado ='"+ID_EMPLEADO+"') AND (Fecha >= {D '"+fecha_inicio_año+"'})  And (Fecha <= {D '"+fecha_fin_año+"'})";
//		String query2 = "SELECT Sum(Datos_horas_extras.horas_ordinarias_compensar) AS SumaDehoras_ordinarias_compensar FROM Datos_horas_extras WHERE (((Datos_horas_extras.Id_empleado)=""" & [Id_empleado] & """) AND ((Datos_horas_extras.Fecha)>= CDate(""01/01/" & [Lista21] - 1 & """) And (Datos_horas_extras.Fecha)< CDate(""01/01/" & [Lista21] & """)));";

		
		//String query2 = "SELECT sum(horas_ordinarias_cobrar) FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
		//System.out.println (query2);
		ResultSet rs2 = s.executeQuery (query2);
		//System.out.println (query2); 
		String Horas=null;
		while (rs2.next()) 
		{ 
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			Horas = (df.format(rs2.getFloat(1)));
		}	
			if (Horas != null) {
				totales=Float.parseFloat(Horas.replace(",","."));
					
				} else{
				totales = Float.parseFloat("0");	
				}
			
			
			//System.out.println (Horas + "generadas año "+ año); 
			
				

		
		
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
		
	return totales;

	
		
		
}
	
	
	
	public static Float disfrutadas_años_anteriores (String empleado,String año){
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
			propiedades Archivopropiedades = new propiedades();
			//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			String servidor_sql = Archivopropiedades.servidor_SQL();
			String basededatos = Archivopropiedades.base_de_datos();
			String jtds_string = servidor_sql + basededatos;
			
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			//Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
			
			//Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			
		Statement s = conexion.createStatement();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String fecha_inicio_año = año + "-01-01";
		Integer año_integer = Integer.parseInt( año)-2;
		
		String fecha_inicio_año = año_integer + "-01-01";
		String fecha_fin_año = año + "-12-31";
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
		while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 

		}
		
		
	
		//disfrutadas año //
		String query3 = "SELECT Sum(Numero_horas_d) FROM Datos_horas_extras INNER JOIN Datos_horas_extras_disfrutadas ON (Datos_horas_extras.Id_empleado = Datos_horas_extras_disfrutadas.Id_empleado) AND (Datos_horas_extras.Id_hora_extra = Datos_horas_extras_disfrutadas.Id_horas_extras_d) WHERE (Datos_horas_extras.Id_empleado)='"+ID_EMPLEADO+"' AND (Datos_horas_extras_disfrutadas.Fecha_d >= {D '"+fecha_inicio_año+"'})  AND (Datos_horas_extras_disfrutadas.Fecha_d <= {D '"+fecha_fin_año+"'}) AND (Datos_horas_extras.Fecha >= {D '"+fecha_inicio_año+"'})  And (Datos_horas_extras.Fecha <= {D '"+fecha_fin_año+"'})";
//	
	//	System.out.println (query3);
		ResultSet rs3 = s.executeQuery (query3);
		//System.out.println (query3); 
		while (rs3.next()) 
		{ 
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			String Horas = (df.format(rs3.getFloat(1)));
			
			if (Horas != null) {
				totales=Float.parseFloat(Horas.replace(",","."));
					
				} else{
				totales = Float.parseFloat("0");	
				}
			
			
			//System.out.println (Horas + "disfrutadas año " + año); 
			
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
		
	return totales;

	
		
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
