import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


public class quien_eres {

	
	public static  String tu_nombre( String args[] ) {
		String userName = System.getProperty("user.name");
		//userName = "Oscar Jauregui";
		//	userName = "Agustin Prellezo555";
		//System.out.println("Window's Username: "+userName);
		return userName;
		}
	
	public static String  quien_eres_nombre_corto(String empleado){
		
		String ID_EMPLEADO = "";
		
	try {	
	
		
			propiedades Archivopropiedades = new propiedades();
		String servidor_sql = Archivopropiedades.servidor_SQL();
		String basededatos = Archivopropiedades.base_de_datos();
		String jtds_string = servidor_sql + basededatos;
		Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
		Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
		Statement s = conexion.createStatement();
		
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
	
	
	
	
	return ID_EMPLEADO;
	
	
	
	
	}
	
	
public static String  jefe_dpto(String empleado){
		
		//String ID_EMPLEADO = "";
		String Jefe_dpto = "";
	try {	
	
		
			propiedades Archivopropiedades = new propiedades();
		String servidor_sql = Archivopropiedades.servidor_SQL();
		String basededatos = Archivopropiedades.base_de_datos();
		String jtds_string = servidor_sql + basededatos;
		Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
		Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
		Statement s = conexion.createStatement();
		
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa,jefe_dpto FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
			while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
		//	 ID_EMPLEADO = rs.getString(1);
			 Jefe_dpto = rs.getString(5);
			//System.out.println (ID_EMPLEADO); 
	
		}
		//	propiedades Archivopropiedades = new propiedades();
		
		
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
	
	
	
	
	return Jefe_dpto;
	
	
	
	
	}
	
	
}
