

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


public class vacaciones {
	
	public static String[] vacaciones_listado ;
	public static String[] vacaciones_totales ;
	
	public static Object[][]  listado_vacaciones(String empleado,String año){
		vacaciones_listado = null;
		String ID_EMPLEADO = "";
		String ID_departamento = "";
		Object[][] data = null;
	try {	
	/*
	propiedades Archivopropiedades = new propiedades();
	String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
	String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
	String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
	*/
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
	
	/*SELECT Datos_Jornada_día.Vacaciones, Datos_Jornada_día.Fecha, Datos_Jornada_día.Id_empleado FROM Datos_Jornada_día WHERE (((Datos_Jornada_día.Vacaciones)<>0) AND ((Datos_Jornada_día.Fecha)>=CDate("01/01/" & [Cuadro combinado207]) And (Datos_Jornada_día.Fecha)<=CDate("31/12/" & [Cuadro combinado207])));*/
	//String query = "SELECT `Fecha de Turno`, `ContadorTG1`,`ContadorTG2`, `ContadorST` FROM `RegistrosContadoresTurbinas` AS `turb` WHERE `Fecha de Turno` > {D '"+ fecha_string +"'} AND `Fecha de Turno` < {D '"+fecha_mas_un_dia_string+"'}";
	
	//SELECT Empleados.Id_empleado, Empleados.Nombre, Empleados.Apellidos, [Nombre] & " " & [Apellidos] AS Expr1, Empleados.Id_departamento FROM Empleados WHERE ((([Nombre] & " " & [Apellidos])=[Formularios]![frmLogin]![LoginName]));
	String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaaa ,Id_departamento  FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
	ResultSet rs = s.executeQuery (query1);
	//System.out.println (query1); 
	while (rs.next()) 
	{ 
		//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
		//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
		 ID_EMPLEADO = rs.getString(1);
		 ID_departamento = rs.getString(5);
	//	System.out.println (ID_departamento); 

	}
	String query3 ="";
	if (ID_departamento.equals("5.0")){
		 query3 = "SELECT Horas_vacaciones, Fecha,Id_empleado FROM S_FC  WHERE (Horas_vacaciones <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')";
	}else{
	
	 query3 = "SELECT Vacaciones, Fecha,Id_empleado FROM Datos_Jornada_día  WHERE (Vacaciones <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
	
	}
	ResultSet rs3 = s.executeQuery (query3);
	//System.out.println (query3); 
	int i = 0;
	while ( rs3.next() )
	i++;
	
	//rs3.first();
	ResultSet rs4 = s.executeQuery (query3); 
	
	data = new Object[i][2];
	
	while (rs4.next()) 
	{ 
		Integer largo_tabla = rs4.getRow();
		String Horas = (rs4.getString(1)+";" + rs4.getDate(2));
		String[] vacaciones=Horas.split(";");
		data[largo_tabla-1][0]= vacaciones[0];
		data[largo_tabla-1][1]= vacaciones[1];
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
	
	
	public static Float totales_vacaciones (String empleado,String año){
		String ID_EMPLEADO = "";
		String ID_departamento = "";
		Float vacaciones_totales = null;
		try {	
		
			/*
			propiedades Archivopropiedades = new propiedades();
		String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
		String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
		String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
		*/
			
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
		
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa,Id_departamento FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
		while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			 ID_departamento = rs.getString(5);
			//System.out.println (ID_EMPLEADO); 

		}
		
		
		String query2 ="";
		if (ID_departamento.equals("5.0")){
			 query2 ="SELECT sum(Horas_vacaciones) FROM S_FC  WHERE (Horas_vacaciones <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
		}else{
		
		
		 query2 = "SELECT sum(Vacaciones) FROM Datos_Jornada_día  WHERE (Vacaciones <> 0 AND Fecha >= {D '"+fecha_inicio_año+"'} AND  Fecha <= {D '"+fecha_fin_año +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
		}
		 ResultSet rs2 = s.executeQuery (query2);
		//System.out.println (query2); 
		while (rs2.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			String Horas = (rs2.getString(1));
			//System.out.println (Horas); 
			if (Horas != null) {
			vacaciones_totales=Float.parseFloat(Horas);
			} else{
			vacaciones_totales = Float.parseFloat("0");	
			}		
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
		
	return vacaciones_totales;

	
	
	}
	
	public static Float vacaciones_año_anterior (String empleado,String año){
	
		String ID_EMPLEADO = "";
		String ID_departamento = "";
		Float vacaciones_totales = null;
		try {	
	/*
			propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			*/
			
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
			
			String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa,Id_departamento FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
			ResultSet rs = s.executeQuery (query1);
			//System.out.println (query1); 
			while (rs.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
				 ID_EMPLEADO = rs.getString(1);
				 ID_departamento = rs.getString(5);
				//System.out.println (ID_EMPLEADO); 

			}
			
			
			
			String query2 ="";
			if (ID_departamento.equals("5.0")){
				 query2 = "SELECT Id_empleado,Año,Vacaciones_Inicio_año_dias FROM S_HE_VAC_AÑO_ANTERIOR  WHERE (Año = "+año+" AND ID_empleado = '"+ID_EMPLEADO+"')"; 
			}else{
			
			
			 query2 = "SELECT Id_empleado,Año,Vacaciones_Inicio_año_dias FROM S_HE_VAC_AÑO_ANTERIOR  WHERE (Año = "+año+" AND ID_empleado = '"+ID_EMPLEADO+"')"; 
			}
			 ResultSet rs2 = s.executeQuery (query2);
			//System.out.println (query2); 
			while (rs2.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
				Float Horas = (rs2.getFloat(3));
				//System.out.println (Horas); 
				
				if (Horas != null) {
					vacaciones_totales=Horas;
					} else{
					vacaciones_totales = Float.parseFloat("0");	
					}	
				
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
		
	return vacaciones_totales;
	}
	
	public static Float vacaciones_a_disfrutar_año_actual (String empleado,String año){
	
		String ID_EMPLEADO = "";
		Float vacaciones_totales = null;
		try {	
	/*
			propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
		
			*/
			
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
			
			
			/*
			SELECT S_HE_VAC_AÑO_ANTERIOR.Id_empleado, S_HE_VAC_AÑO_ANTERIOR.Año, S_HE_VAC_AÑO_ANTERIOR.Vacaciones_Inicio_año_dias FROM S_HE_VAC_AÑO_ANTERIOR GROUP BY S_HE_VAC_AÑO_ANTERIOR.Id_empleado, S_HE_VAC_AÑO_ANTERIOR.Año, S_HE_VAC_AÑO_ANTERIOR.Vacaciones_Inicio_año_dias HAVING (((S_HE_VAC_AÑO_ANTERIOR.Id_empleado)=""" & [Id_empleado] & """) AND ((S_HE_VAC_AÑO_ANTERIOR.Año)=" & [Lista21] & "));
			*/
			String query2 = "SELECT Id_empleado,Año,Vacaciones_año FROM S_HE_VAC_AÑO_ANTERIOR  WHERE (Año = "+año+" AND ID_empleado = '"+ID_EMPLEADO+"')"; 
			ResultSet rs2 = s.executeQuery (query2);
			//System.out.println (query2); 
			while (rs2.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
				Float Horas = (rs2.getFloat(3));
				//System.out.println (Horas); 
				
				if (Horas != null) {
					vacaciones_totales=Horas;
					} else{
					vacaciones_totales = Float.parseFloat("0");	
					}
				
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
		
	return vacaciones_totales;
	}
		
		
		
		
}
