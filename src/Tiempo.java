
public class Tiempo extends Conversor {
	
	public String [] listaTiempos = new String [] {
			"Seconds","Minutes","Hours", "Days", "Weeks", "Months", "Years"};
	
	public String[] getListaOpciones() {
		return listaTiempos;
	}
	
	public String testEntrada() {
		return "Estas en Tiempo";
	}

	@Override
	public double convertirValores(double valor,String tipo1, String tipo2, int index1, int index2) {			
		double [] equivalenciaSeg = new double [] {1, 60, 3600, 86400, 604800, 2629800, 31557600};
		
		return ((valor*equivalenciaSeg[index1])/equivalenciaSeg[index2]);
		
		
	}

	
	
	
}
