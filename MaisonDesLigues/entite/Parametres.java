package entite;

public class Parametres 
{
	private String nomUtilisateur;
	private String motDePasse;
	private String serveurBD;
	private String driverSGBD;
	public Parametres (){
      	nomUtilisateur = "GR1";
	  	motDePasse = "GR1";		
	  	driverSGBD ="sun.jdbc.odbc.JdbcOdbcDriver";
	  	serveurBD = "jdbc:odbc:odbc_m2l";
	}
	public String getDriverSGBD() {
		return driverSGBD;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public String getServeurBD() {
		return serveurBD;
	}
	
}
