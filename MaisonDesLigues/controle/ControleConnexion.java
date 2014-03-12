package controle;
//permet d établir le lien avec la classe Parametres
import entite.Parametres;
//importation des classes pour JDBC pour la connexion
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//permet l affichage des boîtes de dialogue
import javax.swing.JOptionPane;

public class ControleConnexion 
{
	//PROPRIETES statiques appartiennent à la classe et non aux objets (instances )
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
			// création d une instance chargée en mémoire et enregistrement auprès de la classe DriverManager
			Class.forName(lesParametres.getDriverSGBD());
			etatConnexion = true;
		}
		catch(ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Classes non trouvées"
					+ " pour le chargement du pilote JDBC/ODBC ",
					"ALERTE", JOptionPane.ERROR_MESSAGE);
			ok = false;
			etatConnexion = false;
		}
		if (ok == true)
		{
			try {
				// récupération des paramètres présents dans la classe Parametres
				String urlBD = lesParametres.getServeurBD();
				String nomUtilisateur = lesParametres.getNomUtilisateur();
				String MDP = lesParametres.getMotDePasse();
				// création d une connexion contenant les paramètres de connexion
				// appel de la méthode statique getConnection de la classe DriverManager
				laConnectionStatique = DriverManager.getConnection(urlBD, nomUtilisateur, MDP);
				etatConnexion = true;
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données" +
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
	//	 les autres méthodes
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
			JOptionPane.showMessageDialog(null, "Vérifier votre saisie.",
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
			JOptionPane.showMessageDialog(null, "Problème rencontré" +
					"à la fermeture de la connexion","ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
}







