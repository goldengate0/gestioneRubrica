package rubricaProject;

public class Persona {
	//attributo per il nome
	private String nome;
	//attributo per il cognome
	private String cognome;
	//attributo per email
	private String email;
	
	//metodi set per impostare i valori
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	//metodi get per restituire i valori 
	public String getNome()
	{
		return nome;
	}
	
	public String getCognome()
	{
		return cognome;
	}
	
	public String getEmail()
	{
		return email;
	}
}
