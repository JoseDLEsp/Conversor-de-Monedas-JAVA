# Conversor de unidades Java
## Sobre el proyecto
Este proyecto fue realizado como un challenge de programación como parte de mi certificación en desarrollo backend con Java, del programa [Oracle Next Education](https://www.oracle.com/mx/education/oracle-next-education/). Se trata de un conversor de distintos tipos de unidades (entre ellas un convertidor de divisas en tiempo real) que fue desarrollado desde cero, haciendo únicamente uso de JAVA y empleando la extención WindowsBuilder para crear una GUI de escritorio.
>Si te intereza conocer más sobre mí y mis proyectos, te invito a visitar mi sitio web https://josedelucas.netlify.app/#about



## Interfaz
El menú que se despliega de forma inicial nos da a elegir entre distintas unidades de conversión, y al dar click en "Next", nos lleva a un segundo menú donde se puede realizar la conversión deseada.

>Para ser coherente con la inormación brindada por la API de divisas, la interfaz se construyó completamente en inglés, aunque el código está escrito en español.

El proyecto cuenta con tres conversores:
- Currency: Divisas en tiempo real (Empleando una API)
- Temperature: Unidades de temperatura
- Time: Unidades de tiempo, desde segundos hasta años.

![image](https://user-images.githubusercontent.com/121602508/225802794-9bf66948-564d-45de-9d50-f1d327dfd41b.png)

Una vez elegimos el tipo de conversión a realizar se nos desplegará el menú de cambio. La información se ingresa mediante el cuadro de texto superior, que corresponde a la unidad mostrada a su izquierda,y es desplegada en el cuadro inferior una vez damos click en el botón "Convert".

![image](https://user-images.githubusercontent.com/121602508/225803245-dc8241a8-3207-4d40-beda-036c058fb699.png)

La interfaz del convertidor cuenta con un botón "Go Back" para poder regresar al menú principal y hacer uso de un conversor diferente.

## Conversores


### Clase Conversor y cómo funciona la interfaz
Los tres convertidores Divisas, Temperatura y Tiempo son clases hijas de la clase padre Convertidor, la cual cuenta con los tres métodos ensenciales para que la interfaz solicite la información correcta y despliegue resultados. 

``` java
public abstract class Conversor {
	public abstract String [] getListaOpciones();
	public abstract double convertirValores(double valor,String tipo1, String tipo2, int index1, int index2);
}
```

Dichos métodos tienen las siguientes funciones:
- getListaOpciones(): Nos regresa una lista con todas las opciones de unidades para ser desplegadas en el comboBox de la interfaz.
- convertirValores(): Lleva la inormación ingresada a la interfaz y le aplica la lógica deseada, regresando el valor final.

Se hace uso de los índices de la lista en cada conversor para obtener las unidades ingresadas y facilitar la lógica de la conversión, ya que cada conversor emplea distintas lógicas y usa distintos recursos ingresados en la interfaz para llegar al resultado.


### Clase Divisas y DivisasAPIHandler
De los tres convertidores, la clase Divisas es la más robusta y la principal de este proyecto. 

Partimos de que la clase Divisas funciona mediante la [CurrencyAPI](https://currency.getgeoapi.com/) de GEO, por lo que se apoya en la clase DivisasAPIHandler, que se encarga de realizar las llamdas a la API, entregarnos la información desesada y llamar a realizar las conversiones.


>El consumo de API se apoya en las librerías [org.JSON](https://mvnrepository.com/artifact/org.json/json) y [Apache Commons IO](https://commons.apache.org/proper/commons-io/).


La clase DivisasAPIHandler cuenta con dos métodos:

- listasDivisas(): Acepta el parámetro lista, la cual indica si deseamos que se nos regrese una lista con los nombres de las divisas o las abreviaciones desde la API.
- valorFinal(): Acepta las abrebviaciones de las divisas a convertir, así como la cantidad a convertir. Envía la información a la API y retorna el resultado final.


La clase Divisas comunica la GUI con la lógica relacionada a la API, dejándolo en el formato correcto para ser desplegado. El método convertirValores() de Divisas no contiene la lógica del cambio como sus dos clases hermanas, sino que obtiene indices y los llama en la lista de abreviaciones para que la DivisasAPIHandler realice la llamada.


``` java
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
 ```
 
 ## Clases Tiempo y Temperatura
 Estas últimas, a diferencia de Divisas, guardan la lógica de la conversión en sus métdos.
 
Para el caso de tiempo, se emplea un array y empleando los índeces seleccionados en la interfaz realiza una operación matemática que aplica para cualquier imput del usuario de la siguiente forma:


	public double convertirValores(double valor,String tipo1, String tipo2, int index1, int index2) {			
		double [] equivalenciaSeg = new double [] {1, 60, 3600, 86400, 604800, 2629800, 31557600};
 ``` java
		return ((valor*equivalenciaSeg[index1])/equivalenciaSeg[index2]);	
 ```	
 }

Por su parte, la lógica del conversor Temperatura radica en emplear un switch que nos lleva a una de las 6 conversiones posibles de temperatura. El código es el siguiente:

 ``` java
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
 ```	
 
 ## Posibles inputs incorrectos
 El programa hace uso de la NumberFormatException desplegada por el método Double.toDouble() para desplegar un mensaje de error en la caja de texto inferior en caso de que el usuario ingrese un valor inválido.
 
 ![image](https://user-images.githubusercontent.com/121602508/225809491-20360b20-707b-446b-bab6-1280e45d4140.png)

 ## Cómo correr el proyecto
 1. Clonar el proyecto en tu computadora.
 2. Instalar las dos librerías requeridas [org.JSON](https://mvnrepository.com/artifact/org.json/json) y [Apache Commons IO](https://commons.apache.org/proper/commons-io/).
 3. Ejecutar la clase Main desde el IDE de tu preferencia.
 
 
 
