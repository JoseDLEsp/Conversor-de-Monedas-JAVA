public class Temperatura extends Conversor {

	private String[] listaTemperaturas = new String[] { "Celsius", "Fahrenheit", "Kelvin" };

	@Override
	public String[] getListaOpciones() {
		return listaTemperaturas;
	}

	@Override
	public String testEntrada() {
		return "Estas en Temperatura";
	}

	@Override
	public double convertirValores(double C,String tipo1, String tipo2, int index1, int index2) {
		switch (tipo1) {
		case "Celsius":
			switch (tipo2) {
			case "Celsius":
				return C;
			case "Fahrenheit":
				return Math.round((C * 9 / 5) + 32);
			case "Kelvin":
				return Math.round(C + 273.15);
			}
		case "Fahrenheit":
			switch (tipo2) {
			case "Celcius":
				return Math.round((C - 32) * 5 / 9);
			case "Kelvin":
				return Math.round((C - 32) * 5 / 9 + 273.15);
			case "Fahrenheit":
				return C;
			}
		case "Kelvin":
			switch (tipo2) {
			case "Kelvin":
				return C;
			case "Fahrenheit":
				return Math.round((C - 273.15) * 9 / 5 + 32);
			case "Celsius":
				return Math.round(C - 273.15);
			}
		default: return C;
		}
		

	}
}
