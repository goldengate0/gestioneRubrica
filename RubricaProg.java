package rubricaProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RubricaProg {
	//metodo per aggiungere un contatto nell'array
	private static void aggiungiContatto(Persona rubrica[], int contaPersone)
	{
		//creo il canale di input
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader tas = new BufferedReader(in);
		
		//inizializzo l'oggetto nell'array
		rubrica[contaPersone] = new Persona();
				
		//chiedo all'utente nome, cognome, email
		System.out.println("Nome: ");
		try
		{
			//aggiungo il valore inserito da tastiera nell'attributo nome
			rubrica[contaPersone].setNome(tas.readLine());
		}
		catch (Exception e) {}
		
		System.out.println("Cognome: ");
		try
		{
			//aggiungo il valore inserito da tastiera nell'attributo cognome
			rubrica[contaPersone].setCognome(tas.readLine());
		}
		catch (Exception e) {}
		
		System.out.println("email: ");
		try
		{
			//aggiungo il valore inserito da tastiera nell'attributo nome
			rubrica[contaPersone].setEmail(tas.readLine());
		}
		catch (Exception e) {}
	}
	
	//metodo per rimuovere un contatto
	private static void eliminaContatto(Persona rubrica[], int startIndex, int ultimoIndex)
	{
		/* la mia idea per eliminare il contatto è che a partire da quell'indice, vengono 
		 * traslati di uno, tutti gli altri dati provenienti da destra
		 * 
		 *  Se il contatto si trova nell'ultima posizione, tutti i campi diventano null */
		
		//startIndex indica l'indice da cui partire, non è altro che l'indice dei dati da elimnare
		
		//se startIndex è minore di contaPersone, traslo, senno elimina semplicemente l'ultima posizione
		for (int i = startIndex; i < ultimoIndex; i++)
		{
			//traslo tutti i dati di uno provienienti da destra
			rubrica[i] = rubrica[i + 1];
		}
		
		/*l'ultima posizione, dopo aver traslato tutti i dati, contiene un dato doppio,
		 * in quanto la penultima posizione prende i dati dell'ultima, e l'ultima non riceve modifiche. 
		 * Bisogna quindi eliminare i dati dell'ultima posizione*/
		rubrica[ultimoIndex] = null;
	}
	
	//metodo per restituire l'indice del contatto cercato
	private static int cercaContatto(Persona rubrica[], String nome, String cognome, int contaPersone)
	{
		//cerco da indice 0 all'ultimo indice con dati inseriti
		for (int i = 0; i < contaPersone; i++)
		{
			//se i dati passati come paramentro sono uguali ai dati contenuti nell'indice i, allora restituisco i
			if ((nome.equals(rubrica[i].getNome()))  && (cognome.equals(rubrica[i].getCognome())))
			{
				return i;
			}
		}
		//se sono ancora qua, significa che i dati non coincidono con nessun dato all'interno dell'array
		return -1;
	}
	
	//metodo per visualizzare tutta la rubrica
	private static void visualizzaRubrica(Persona rubrica[], int contaPersone)
	{
		for (int i = 0; i < contaPersone; i++)
		{
			System.out.print(rubrica[i].getNome() + " ");
			System.out.print(rubrica[i].getCognome() + " ");
			System.out.println(rubrica[i].getEmail());
		}
	}
	
	
	
	public static void main(String[] args) {
		
		//creo il canale di input
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader tas = new BufferedReader(in);
		
		//dichiaro e alloco l'array di classe Persona
		Persona rubrica[] = new Persona[100];
		
		//variabile per salvare la risposta
		char operazione = 'A';
		
		//variabile per salvare l'ultimo indice in cui è stato aggiundo una persona
		int contaPersone = 0;
		
		//variabile per salvare l'indice cercato (var. da usare per metodo cercaContatto)
		int indiceCercato;
		
		//variabile generale per salvare il nome e il cognome da eliminare, cercare, aggiungere
		String nome = "", cognome = "";
		
		
		while(true)
		{
			//chiedo all'utente quale operazione vuole fare
			System.out.println("Quale operazione vuoi fare? ((A)ggiungi, (R)imuovi, (C)erca, (V)isualizza, (E)sci)");
			try
			{
				//converto da stringa a char usando charAt(), che prende il primo carattere di una stringa
				//salvo il valore nella variabile
				operazione = tas.readLine().charAt(0);
			}
			catch (Exception e) {}
			
			//ora in base a cio che mi ha inserito l'utente, eseguo un operazione
			switch (operazione)
			{
			//caso in cui l'utente vuole aggiungere una persona
				case 'A':
					
					aggiungiContatto(rubrica, contaPersone);
					//incremento il valore della conta
					contaPersone++;
					
					break;
				
				//caso in cui l'utente vuole rimuovere una persona
				case 'R':
					//controllo che la prima casella sia diversa da NULL
					if(contaPersone > 0)
					{
						//chiedo il nome e cognome del contatto da eliminare
						System.out.println("Inserisci il nome da eliminare: ");
						try
						{
							nome = tas.readLine();
						}
						catch (Exception e) {}
						
						System.out.println("Inserisci il cognome da eliminare: ");
						try
						{
							cognome = tas.readLine();
						}
						catch (Exception e) {}
						
						//cerco l'indice della persona, se restituisce -1 significa che nome e cognome non sono presenti nell'array
						indiceCercato = cercaContatto(rubrica, nome, cognome, contaPersone);
						
						if (indiceCercato != -1)
						{
							//elimino il contatto
							eliminaContatto(rubrica, indiceCercato, contaPersone - 1);
							//eliminato un contatto, devo anche ridurre il primo indice disponibile
							contaPersone--;
						}
						else
						{
							System.out.println("coincidenza dei dati non trovati nell'array");
						}
					}
					else
					{
						System.out.println("L'array è vuoto, non è possibile eliminare nessun contatto.");
					}
					
					break;
				
				//caso in cui l'utente vuole cercare un contatto
				case 'C':
					//chiedo il nome e cognome del contatto da cercare
					System.out.println("Inserisci il nome da cercare: ");
					try
					{
						nome = tas.readLine();
					}
					catch (Exception e) {}
					
					System.out.println("Inserisci il cognome da cercare: ");
					try
					{
						cognome = tas.readLine();
					}
					catch (Exception e) {}
					
					//cerco l'indice della persona, se restituisce -1 significa che nome e cognome non sono presenti nell'array
					indiceCercato = cercaContatto(rubrica, nome, cognome, contaPersone);
					
					if (indiceCercato != -1)
					{
						System.out.println(rubrica[indiceCercato].getNome());
						System.out.println(rubrica[indiceCercato].getCognome());
						System.out.println(rubrica[indiceCercato].getEmail());
					}
					else
					{
						System.out.println("coincidenza dei dati non trovati nell'array");
					}
					
					break;
				
				//caso in cui l'utente vuole visualizzare tutta la rubrica
				case 'V':
					visualizzaRubrica(rubrica, contaPersone);
					break;
				
				//caso in cui l'utente vuole terminare il programma
				case 'E':
					System.exit(0);
					break;
					
				default: 
					System.out.println("Carattere inserito non corretto. ");
			}
		}
	}
}
