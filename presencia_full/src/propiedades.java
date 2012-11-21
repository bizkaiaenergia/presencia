


	import java.io.*;
import java.util.Properties;
public class propiedades {
	
	
	String archivo_properties="properties//presencia.properties";
	
	
	
	String ruta_servidor_odbc;
	String nombre_servidor_odbc;
	String ruta_instalacion;
	String servidor_SQL;
	String base_de_datos;
	String absentismo;
	
	
	Properties prop  = new Properties();
	
	
	String nombre_servidor_odbc(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			nombre_servidor_odbc = prop.getProperty("nombre_servidor_odbc");
		}catch(IOException e){
		}
		return nombre_servidor_odbc;
		}
	
	String ruta_servidor_odbc(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_servidor_odbc = prop.getProperty("ruta_servidor_odbc");
		}catch(IOException e){
		}
		return ruta_servidor_odbc;
		}
	
	String ruta_instalacion(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			ruta_instalacion = prop.getProperty("ruta_instalacion");
		}catch(IOException e){
		}
		return ruta_instalacion;
		}
	
	String base_de_datos(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			base_de_datos = prop.getProperty("base_de_datos");
		}catch(IOException e){
		}
		return base_de_datos;
		}
	
	
	String servidor_SQL(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			servidor_SQL = prop.getProperty("servidor_SQL");
		}catch(IOException e){
		}
		return servidor_SQL;
		}
	
	String absentismo(){
		try{
			prop.load(new FileInputStream(archivo_properties));
			absentismo = prop.getProperty("absentismo");
		}catch(IOException e){
		}
		return absentismo;
		}
	
	
	
	
	
}