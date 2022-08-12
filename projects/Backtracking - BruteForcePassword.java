// Design and implement an algorithm that finds a password by brute force, using Backtracking technique.
// Passwords had to be composed of the following requirements: at least 1 capital letter, 1 small letter, 1 digit and exactly 1 of the following special characters {+, −, ∗, /, %, &}.
// Given parameters are the password to decipher and its length.
// In order to find it the algorithm is supposed to generate only the valid possible passwords.

package asd;
import java.util.Vector;


public class TPO 
{
	private int i;
	private String guess;
	private boolean found;
	private Boolean[] req;
	
	
	public static void main(String[] args) 
	{
		TPO trial = new TPO();
		String contraseña = "w6+D";				// Contraseña a buscar.
		int n = contraseña.length();			// Tamaño de la contraseña.
		
		trial.run(contraseña, n);
	}
	
	public void run(String password, int n) 
	{
		String minus[] = new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String mayus[] = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String numeros[] = new String[] {"0","1","2","3","4","5","6","7","8","9"};
		String especiales[] = new String[] {"+", "−", "∗", "/", "%", "&"};
		
		// Inicializamos arrays con los caracteres que vamos a utilizar.
		
		String Sol[] = new String[n];		// Inicializamos un array para guardar nuestra solución.
		found = Passwords(Sol, n, 0, password, 0, 0, 0, 0, minus, mayus, numeros, especiales);	// Corremos el programa y guardamos el resultado en una variable llamada found.
		if (found != true)		// Si no encontramos la contraseña.
		{
			System.out.println("No encontrado");
		}
	}
	
		
		
	public boolean Passwords(String[] SolAct, int n, int etapa, String password, int contMayus, int contMinus, int contNum, int contEsp, String[] minus, String[] mayus, String[] numeros, String[] especiales)
	{
		contMinus += 1; // Agregamos uno al contador de minusculas ya que a continuación agregaremos una minúscula.
		
		req = new Boolean[] {contMayus == 0 , contNum == 0 , contEsp != 1};				// Requisitos que faltan cumplir en la posible contraseña generada.
		if (contar(req) < n - etapa) 													// n - etapa = posiciones disponibles (cantidad de caracteres que aún no utilicé). 
		/* Si los requisitos faltantes son menores que la cantidad de caracteres aún disponibles, 
		 * significa que agregar un caracter de tipo minúscula no influiría en nuestra capacidad de poder arma una password válida.*/
		{																				
			for (String car : minus)							// Iteramos sobre cada caracter del array.
			{
				SolAct[etapa] = car;							// Asignamos el caracter en nuestra solución actual.
				if (etapa == n-1) 								// Si ya tenemos una contraseña formada:
				{
					guess = String.join("", SolAct);			// Convertimos la solución actual de array a String.
					if (guess.compareTo(password) == 0) 		// Verificamos si es la contraseña dada.
					{
						System.out.println("La contraseña es: " + guess);
						return true;							// Devolvemos true porque encontramos la contraseña.
					}
				}
				else 
				{		// Ya con el caracter de esta posición asignada, pasamos a la posición siguiente.
					if (Passwords(SolAct, n, etapa + 1, password, contMayus, contMinus, contNum, contEsp, minus, mayus, numeros, especiales))
					/* Passwords() devuelve un boolean, y si el método nos devuelve true, significa que encontramos la contraseña. */
					{
						return true;							// Dado que encontramos la contraseña, seguimos devolviendo true.
					}
				}
				
			}
		}
		
		contMinus -= 1; 
		/* Si llegamos hasta acá, significa que vamos a cambiar el caracter de esta posición y ya no va a ser minúscula 
		 * por lo que restamos del contador la minúscula que habíamos agregado. */
		
		
		contMayus += 1;
		
		// A partir de aquí realizamos lo mismo que en el bloque anterior, pero con caracteres mayusculas.
		
		req = new Boolean[] {contMinus == 0 , contNum == 0 , contEsp != 1};				// Requisitos que debe cumplir la contraseña generada.
		if (contar(req) < n - etapa) 
		{
			for (String car : mayus)
			{
				SolAct[etapa] = car;
				if (etapa == n-1) 
				{
					String guess = String.join("", SolAct);
					if (guess.compareTo(password) == 0) 
					{
						System.out.println("La contraseña es: " + guess);
						return true;
					}
				}
				else
				{
					if (Passwords(SolAct, n, etapa + 1, password, contMayus, contMinus, contNum, contEsp, minus, mayus, numeros, especiales))
					{
						return true;
					}
				}
				
			}
		}
		
		contMayus -= 1;
		
		contNum += 1;
		
		// A partir de aquí realizamos lo mismo que en el primer bloque, pero con caracteres numéricos.
		
		req = new Boolean[] {contMinus == 0 , contNum == 0 , contEsp != 1};
		if (contar(req) < n - etapa) 
		{
			for (String car : numeros)
			{
				SolAct[etapa] = car;
				if (etapa == n-1) 
				{
					String guess = String.join("", SolAct);
					if (guess.compareTo(password) == 0) 
					{
						System.out.println("La contraseña es: " + guess);
						return true;
					}
				}
				else
				{
					if (Passwords(SolAct, n, etapa + 1, password, contMayus, contMinus, contNum, contEsp, minus, mayus, numeros, especiales))
					{
						return true;
					}
				}
				
			}
		}
		
		contNum -= 1;
		
		contEsp += 1;
		
		// A partir de aquí realizamos lo mismo que en el primer bloque, pero con los caracteres especiales dados.
		
		req = new Boolean[] {contMinus == 0 , contNum == 0 , contEsp != 1};
		if (contar(req) < n - etapa) 
		{
			for (String car : especiales)
			{
				SolAct[etapa] = car;
				if (etapa == n-1) 
				{
					String guess = String.join("", SolAct);
					if (guess.compareTo(password) == 0) 
					{
						System.out.println("La contraseña es: " + guess);
						return true;
					}
				}
				else
				{
					if (Passwords(SolAct, n, etapa + 1, password, contMayus, contMinus, contNum, contEsp, minus, mayus, numeros, especiales))
					{
						return true;
					}
				}
				
			}
		}
		
		contEsp -= 1;
		
		return false;
		
				
	}

	public int contar(Boolean[] array) {
		/* Metodo creado para contar cuantos de los requisitos son verdaderos. */
		int trueCount = 0;
		for (Boolean element : array) 
		{
		    if (element) {
		    	trueCount++;
		    }
		}
		return trueCount;
	}
}
