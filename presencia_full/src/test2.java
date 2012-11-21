
public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String empleado = "Marcos Rodriguez";
		// String empleado = "Oscar Jauregui";
		 String año = "2010";
		 
		 //horas_a_cobrar.listado_horas_cobrar(empleado, año);
		 //Float a = horas_a_cobrar.totales_horas_cobrar(empleado, año);
		// Float b = horas_a_cobrar.totales_horas_efectivas_cobrar(empleado, año);
	//	 System.out.println(a + " "+b);
		 
	//	 Object[][] data = horas_a_compensar.listado_horas_compensar(empleado, año);
		 float a = horas_a_compensar_solo_parada.totales_horas_compensadas_SOLO_PARADA(empleado, año);
		float b = horas_a_compensar_solo_parada.generadas_año_SOLO_PARADA(empleado,año);
		float c = horas_a_compensar_solo_parada.disfrutadas_año_SOLO_PARADA(empleado, año);
	//	 float b = horas_a_compensar.generadas_año(empleado, año);

}
}