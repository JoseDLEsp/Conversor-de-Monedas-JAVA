
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.json.*;

public class DivisasAPIHandler {
	public String[] listasDivisas(String lista) throws IOException {
		
		String get_list_url = "https://api.getgeoapi.com/v2/currency/list?api_key=4b031babc87288795edffd2673d6b0c448b3bbce&format=json";
		URL url = new URL(get_list_url);

		String json = IOUtils.toString(url, Charset.forName("UTF-8"));
		JSONObject jo = new JSONObject(json);

		JSONObject currencies = jo.getJSONObject("currencies");
		JSONArray keys = currencies.names();
		
		int n = keys.length();
		
		String [] abreviaciones = new String [n];
		String [] nombres = new String [n];
		
		
		
		for(int i = 0; i<n; i++) {
			String key = keys.getString(i);
			String value = currencies.getString(key);
			
			abreviaciones[i] = key;
			nombres[i] = value;			
		}
		
		if (lista == "nombres") {
			return nombres;
		}
		else {
			return abreviaciones;
		}
		
	
	}

	public double valorFinal(String moneda, String cambio, double valor) throws IOException {
		System.out.println("Obteniendo valor final");
		String get_list_url = "https://api.getgeoapi.com/v2/currency/convert?api_key=4b031babc87288795edffd2673d6b0c448b3bbce&from="+ moneda +"&to="+ cambio +"&amount=" + Double.toString(valor) + "&format=json";
		URL url = new URL(get_list_url);

		String json = IOUtils.toString(url, Charset.forName("UTF-8"));
		System.out.println(json);
		JSONObject jo = new JSONObject(json);
		
		String stringFinal = jo.getJSONObject("rates").getJSONObject(cambio).getString("rate_for_amount");
		System.out.println(stringFinal);
		
		double valorFinal = Double.parseDouble(stringFinal);
		
		return valorFinal ;
		
	}	
	
}
