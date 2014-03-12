package controle;
//permet d �tablir le lien avec la classe Parametres
import entite.Parametres;
//importation des classes pour JDBC pour la connexion
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//permet l affichage des bo�tes de dialogue
import javax.swing.JOptionPane;

public class ControleConnexion 
{
	//PROPRIETES statiques appartiennent � la classe et non aux objets (instances )
	private static ControleConnexion controlConnexion;

	private Parametres lesParametres;
	private boolean etatConnexion;
	private Connection laConnectionStatique;

	//	 CONSTRUCTEUR
	private ControleConnexion()
	{
		lesParametres = new Parametres();
		etatConnexion=false;
	}

	public void connect(){
		boolean ok = true;

		try {
			// enregistrement du pilote
			// cr�ation d une instance charg�e en m�moire et enregistrement aupr�s de la classe DriverManager
			Class.forName(lesParametres.getDriverSGBD());
			etatConnexion = true;
		}
		catch(ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Classes non trouv�es"
					+ " pour le chargement du pilote JDBC/ODBC ",
					"ALERTE", JOptionPane.ERROR_MESSAGE);
			ok = false;
			etatConnexion = false;
		}
		if (ok == true)
		{
			try {
				// r�cup�ration des param�tres pr�sents dans la classe Parametres
				String urlBD = lesParametres.getServeurBD();
				String nomUtilisateur = lesParametres.getNomUtilisateur();
				String MDP = lesParametres.getMotDePasse();
				// cr�ation d une connexion contenant les param�tres de connexion
				// appel de la m�thode statique getConnection de la classe DriverManager
				laConnectionStatique = DriverManager.getConnection(urlBD, nomUtilisateur, MDP);
				etatConnexion = true;
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Impossible de se connecter � la base de donn�es" +
						'\n'+"Erreur: "+e.getMessage()
						,"ALERTE", JOptionPane.ERROR_MESSAGE);
				etatConnexion = false;
			}

		}
	}

	public static ControleConnexion getControleConnexion(){
		if(controlConnexion==null){
			controlConnexion=new ControleConnexion();
		}
		return controlConnexion;
	}

	//	 METHODES
	//	 les accesseurs statiques
	public Parametres getParametres()
	{
		return lesParametres;
	}
	public boolean getEtatControleConnexion()
	{
		return etatConnexion;
	}
	public Connection getConnexion()
	{
		return laConnectionStatique;
	}
	//	 les autres m�thodes
	public boolean controle(String Nom, String MotDePasse)
	{
		boolean verificationSaisie;
		if (Nom.equals(lesParametres.getNomUtilisateur())
				&& MotDePasse.equals(lesParametres.getMotDePasse()))
		{
			verificationSaisie = true;
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "V�rifier votre saisie.",
					"ERREUR", JOptionPane.ERROR_MESSAGE);
			verificationSaisie = false;
		}
		return verificationSaisie;
	}

	public void fermetureSession()
	{
		try {
			laConnectionStatique.close();
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Probl�me rencontr�" +
					"� la fermeture de la connexion","ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
}







