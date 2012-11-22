import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

	


public class dias_presencia {
	public static Object[][]  listado_dias_presencia(String empleado,String año){
		String listado = null;
		String ID_EMPLEADO = "";
		Object[][] data = null;
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
	
	
	
	String query3 = "SELECT Fecha, horas_trabajadas, CHE, Vacaciones, HCSindical, HTSindical, ILT, BMedicas, PyL  FROM Datos_Jornada_día  WHERE (ID_empleado = '"+ID_EMPLEADO+"' and Fecha >= {D '"+fecha_inicio_año+"'}  And Fecha <= {D '"+fecha_fin_año+"'} ) ORDER BY Fecha"; 
	
	
	
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
		
		
		String Horas = (rs4.getDate(1)+";" + df.format(rs4.getFloat(2)) +";" + df.format(rs4.getFloat(3))+";" + df.format(rs4.getFloat(4))+";" + df.format(rs4.getFloat(5))+";" + df.format(rs4.getFloat(6))+";" + df.format(rs4.getFloat(7))+";" + df.format(rs4.getFloat(8)));
		String[] vacaciones=Horas.split(";");
		data[largo_tabla-1][0]= vacaciones[0];
		data[largo_tabla-1][1]= vacaciones[1];
		data[largo_tabla-1][2]= vacaciones[2];
		data[largo_tabla-1][3]= vacaciones[3];
		data[largo_tabla-1][4]= vacaciones[4];
		data[largo_tabla-1][5]= vacaciones[5];
		data[largo_tabla-1][6]= vacaciones[6];
		data[largo_tabla-1][7]= vacaciones[7];
	//	data[largo_tabla-1][8]= vacaciones[8];
	//	data[largo_tabla-1][9]= vacaciones[9];
	//	data[largo_tabla-1][10]= vacaciones[10];
		
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
	
}
