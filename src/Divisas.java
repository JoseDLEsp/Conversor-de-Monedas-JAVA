import java.io.IOException;

public class Divisas extends Conversor {
	
	private String[] getArrays(String lista) {
		try {
			String [] listaDivisas = new DivisasAPIHandler().listasDivisas(lista);
			return listaDivisas;
		}catch(IOException ioe) {
			return null;
		}
		
	}
	
	
	public String[] getListaOpciones() {
		return getArrays("abrev");
	}
	
	
	public String testEntrada() {
		return "Estas en Divisas";
	}

	@Override
	public double convertirValores(double valor, String tipo1, String tipo2) {
		System.out.println("Convirtiendo valores");
		try {
			return new DivisasAPIHandler().valorFinal(tipo1, tipo2, valor);
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
			System.out.println("Algo salio mal");
			return 0;
		}
		
	}


	
}
