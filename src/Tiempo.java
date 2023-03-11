
public class Tiempo extends Conversor {
	
	public String [] listaTiempos = new String [] {
			"Segundos","Minutos","Horas", "Dias", "Semanas", "Meses", "Anhos"};
	
	public String[] getListaOpciones() {
		return listaTiempos;
	}
	
	public String testEntrada() {
		return "Estas en Tiempo";
	}

	@Override
	public double convertirValores(double valor, String tipo1, String tipo2) {		
		return 0;
	}

	
}
