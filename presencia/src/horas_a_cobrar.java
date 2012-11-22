

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


public class horas_a_cobrar {
	
	public static String[] listado ;
	public static String[] totales ;
	
	public static Object[][]  listado_horas_cobrar(String empleado,String año){
		listado = null;
		String ID_EMPLEADO = "";
		Object[][] data = null;
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
	/*
	String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, 'Mes Contabilizado', Inicio_hora_extra as INICIO, Fin_hora_extra as FIN," +
	"Horas_extras_trab as TRAB, Horas_extras_base as H_BASE, Coeficiente_ajuste as COEF ,horas_ordinarias_ajustadas as H_ORD_AJ," +
	"horas_ordinarias_cobrar as HORAS_A_COBRAR FROM Datos_horas_extras  WHERE (HORAS_A_COBRAR <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  `Fecha` <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
	*/
	///BUENO
	//String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
	//		"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
	//		"horas_ordinarias_cobrar,horas_efectivas_a_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"') ORDER BY Fecha"; 
	//BUENO
	
	
	
	String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
		"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
			"horas_ordinarias_cobrar,horas_efectivas_a_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"' and Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'} ) ORDER BY Fecha"; 
	
	
	
	//System.out.println (query3); 
	ResultSet rs3 = s.executeQuery (query3);
	
	int i = 0;
	while ( rs3.next() )
	i++;
	ResultSet rs4 = s.executeQuery (query3); 
	data = new Object[i][11];
	while (rs4.next()) 
	{ 
		Integer largo_tabla = rs4.getRow();
		
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		
		
		String Horas = (rs4.getString(1)+";" + rs4.getDate(3) +";" + rs4.getString(4)+";" + rs4.getTime(5)+";" + rs4.getTime(6)+";" + df.format(rs4.getFloat(7))+";" + df.format(rs4.getFloat(8))+";" + df.format(rs4.getFloat(9))+";" + df.format(rs4.getFloat(10))+";" + df.format(rs4.getFloat(11))+";" + df.format(rs4.getFloat(12)));
		String[] vacaciones=Horas.split(";");
		data[largo_tabla-1][0]= vacaciones[0];
		data[largo_tabla-1][1]= vacaciones[1];
		data[largo_tabla-1][2]= vacaciones[2];
		data[largo_tabla-1][3]= vacaciones[3];
		data[largo_tabla-1][4]= vacaciones[4];
		data[largo_tabla-1][5]= vacaciones[5];
		data[largo_tabla-1][6]= vacaciones[6];
		data[largo_tabla-1][7]= vacaciones[7];
		data[largo_tabla-1][8]= vacaciones[8];
		data[largo_tabla-1][9]= vacaciones[9];
		data[largo_tabla-1][10]= vacaciones[10];
		
		//System.out.println (Horas); 

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
	
	
	
	
	return data;
	
	
	
	
	}
	
	
	public static Float totales_horas_cobrar (String empleado,String año){
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
		
		
		
		String query2 = "SELECT sum(horas_ordinarias_cobrar) FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
		ResultSet rs2 = s.executeQuery (query2);
		//System.out.println (query2); 
		while (rs2.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			String Horas = (rs2.getString(1));
			
			if (Horas != null) {
				totales=Float.parseFloat(Horas);
				} else{
				totales = Float.parseFloat("0");	
				}
			
			
			//System.out.println (Horas); 
			
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
	
	public static Float totales_horas_efectivas_cobrar (String empleado,String año){
	
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
	
			propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
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
			
			
			/*
			SELECT S_HE_VAC_AÑO_ANTERIOR.Id_empleado, S_HE_VAC_AÑO_ANTERIOR.Año, S_HE_VAC_AÑO_ANTERIOR.Vacaciones_Inicio_año_dias FROM S_HE_VAC_AÑO_ANTERIOR GROUP BY S_HE_VAC_AÑO_ANTERIOR.Id_empleado, S_HE_VAC_AÑO_ANTERIOR.Año, S_HE_VAC_AÑO_ANTERIOR.Vacaciones_Inicio_año_dias HAVING (((S_HE_VAC_AÑO_ANTERIOR.Id_empleado)=""" & [Id_empleado] & """) AND ((S_HE_VAC_AÑO_ANTERIOR.Año)=" & [Lista21] & "));
			*/
			String query2 = "SELECT sum(horas_efectivas_a_cobrar) FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
			ResultSet rs2 = s.executeQuery (query2);
			//System.out.println (query2); 
			while (rs2.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
				Float Horas = (rs2.getFloat(1));
				
				if (Horas != null) {
					totales=Horas;
					} else{
					totales = Float.parseFloat("0");	
					}
				
				//System.out.println (Horas); 
				
				}
			
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return totales;
	}
	
	
		
		
		
		
}
