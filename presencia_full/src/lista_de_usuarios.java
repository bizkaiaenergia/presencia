

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class lista_de_usuarios {
	
	public static String[] vacaciones_listado ;
	public static String[] vacaciones_totales ;
	
	public static String[]  listado(){
		
		String[] data = null;
	try {	
	
	propiedades Archivopropiedades = new propiedades();
	String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
	String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
	String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
	Statement s = conexion.createStatement();
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
String query3 = "SELECT (Nombre + ' ' + Apellidos) as aaa FROM Empleados";
	//String query3 = "SELECT AÑO FROM AÑO"; 
	ResultSet rs3 = s.executeQuery (query3);
	//System.out.println (query3); 
	int i = 0;
	while ( rs3.next() )
	i++;
	
	//rs3.first();
	ResultSet rs4 = s.executeQuery (query3); 
	
	data = new String[i];
	
	while (rs4.next()) 
	{ 
		
		
		Integer largo_tabla = rs4.getRow();
		String nombre_largo = (rs4.getString(1));
		
		data[largo_tabla-1]= nombre_largo;
		
		
		//System.out.println (Años); 

	}
	
		
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	return data;
	
	//return vacaciones_listado;
	
	
	}
	
	

		
		
}
