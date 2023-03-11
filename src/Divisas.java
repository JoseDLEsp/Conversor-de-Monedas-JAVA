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
		return getArrays("nombres");
	}
	
	
	public String testEntrada() {
		return "Estas en Divisas";
	}

	@Override
	public double convertirValores(double valor,String tipo1, String tipo2, int index1, int index2) {
		System.out.println("Convirtiendo valores");
		String [] listaAbrev = getArrays("abrev");
		String moneda = listaAbrev[index1];
		String cambio = listaAbrev[index2];
		
		try {
			return new DivisasAPIHandler().valorFinal(moneda, cambio, valor);
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
			System.out.println("Algo salio mal");
			return 0;
		}
		
	}


	
}
