package controle;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.swing.JOptionPane;

import sun.awt.windows.ThemeReader;
import entite.*;

import java.util.ArrayList;

public class GestionDemandes
{
	public boolean enregistrerDetailHebergement(Integer widp,Integer wnumo,Integer wcodh, Integer widc,Integer widdn)
	{
		Detailhebergement lDetailHebergement = new Detailhebergement(widp, wnumo, wcodh,  widc, widdn);
		String requete= lDetailHebergement.req_InsertDetailHebergement();
		return executeReq(requete);
	}
	public boolean enregistrerIntervenant(String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail, String wstatut, int widatelier)
	{
		Intervenant lIntervenant = new Intervenant(0, wnom, wprenom, wadr1, wadr2, wcp, wville, wmail, wstatut, widatelier);
		String requete= lIntervenant.req_InsertParticipant();
		return executeReq(requete);
	}
	public boolean enregistrerBenevole(String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail, String wstatut, String wnolicence, Date wdatenaiss)
	{
		boolean resultat = true;
		Benevole lBenevole = new Benevole(0, wnom,wprenom,wadr1,wadr2,wcp,wville, wmail, wstatut, wnolicence,wdatenaiss);
		String requete= lBenevole.req_InsertParticipant();
		int numauto = executeReqLi(requete);
		lBenevole.setNumParticipant(numauto); 
		return resultat;
	}
	
	public boolean enregistrerPaiement (int idparticipant, String num, float montant){
		String requete = "insert into Paiement values("+idparticipant+",'"+montant+"','"+num+"','cheque')";
		return executeReq(requete);
	}
	
	public boolean enregistrerPresence(int idparticipant, int num){
		String requete = "insert into Etrepresent values("+idparticipant+","+num+")";
		return executeReq(requete);
	}
	
	public boolean enregistrerLicencie	(String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail,String wstatut,Date wdateins, Date wdateenr,String Wclewifi, String wnolicence,int widqualite, ArrayList<Integer> liste)
	{
		Licencie lLicencie = new Licencie(0, wnom,wprenom,wadr1,wadr2,wcp,wville,wmail,wstatut, wdateins,wdateenr,Wclewifi,wnolicence,widqualite,liste);
		boolean resultat = true;
		
		String requete= lLicencie.req_InsertParticipant();
		int numauto = executeReqLi(requete);
		lLicencie.setNumParticipant(numauto); 
		String requete2= lLicencie.req_InsertInscrire(); 
		resultat = executeReq(requete2);
		return resultat;
	}
	
	public boolean enregistrerAtelier(Integer wnoint,String wlib, Integer wnbplace){
		Atelier lAtelier = new Atelier(null, wnoint, wlib, wnbplace);
		String requete= lAtelier.req_InsertAtelier();
		return executeReq(requete);
	}
	
	public boolean enregistrerVacation(Integer wnoat, String wdatedeb, String wdatefin){
		try {
			String requete = "SELECT MAX(IDVACATION) + 1 FROM Vacation WHERE IDATELIER = " + wnoat; 
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			
			if (!result.next())
				return false;
			
			Vacation lVacation = new Vacation(wnoat.toString(), result.getString(1), wdatedeb, wdatefin);
			requete= lVacation.req_InsertVacation();
			return executeReq(requete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean enregistrerTheme(Integer wnoat, String wlibtheme) {
		try {
			String requete = "SELECT MAX(IDTHEME) + 1 FROM Theme WHERE IDATELIER = " + wnoat; 
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			
			if (!result.next())
				return false;
			
			Theme lTheme = new Theme(wnoat, result.getInt(1), wlibtheme);
			requete = lTheme.req_InsertTheme();
			return executeReq(requete);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean enregistrerAccompagnant(Integer idParticipant, Integer idRestauration)
	{

			String requete = "insert into Inclureaccompagnant values("+idParticipant+","+idRestauration+")";
			
			return executeReq(requete);

	}
	
	public Participant rechercherParticipantsurnom(String wnom){
		
		String requete="select * from Participant where nomparticipant like '"+wnom+"'";
		Participant retourParticipant=null;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			Intervenant lIntervenant;
			if(!result.next())
				return null;
			Integer numero=result.getInt(1);
			String nom=result.getString(2);
			String prenom=result.getString(3);
			String adr1=result.getString(4);
			String adr2=result.getString(5);
			String ville=result.getString(6);
			String cp=result.getString(7);
			String mail=result.getString(8);
			Date dateInscription=result.getDate(9);
			Date dateArrivee = result.getDate(10);
			String cleWifi=result.getString(11); 
			String statut=result.getString(12);
			String numeroLicence=result.getString(13);
			Date dateNaissance=result.getDate(14);
			Integer idAtelier=result.getInt(15);
			Integer idQualite=result.getInt(16);
			if(statut.equals("I"))
			{
				// intervenant
				lIntervenant=new Intervenant(numero,nom,prenom,adr1,adr2,cp,ville,mail,"I",idAtelier);
				retourParticipant= lIntervenant;
			}
			else
			{
				if(statut.equals("L"))
				{
				// licencie
					Licencie lLicencie=new Licencie(numero,nom,prenom,adr1,adr2,cp,ville,mail,"L",dateInscription,dateArrivee,cleWifi,numeroLicence,idQualite, null);
					retourParticipant= lLicencie;
				}
				else
				{	// Benevole
					Benevole lBenevole=new Benevole(numero,nom,prenom,adr1,adr2,cp,ville,mail,"B",numeroLicence,dateNaissance);
					retourParticipant= lBenevole;
				}
			}
			state.close();
			return retourParticipant;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	public Participant rechercherParticipantsurlicence(String licence){
		String requete ="select * from Participant where numerolicence='"+licence+"'";
		Participant retourParticipant=null;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			Intervenant lIntervenant;
			if(!result.next())
				return null;
			Integer numero=result.getInt(1);
			String nom=result.getString(2);
			String prenom=result.getString(3);
			String adr1=result.getString(4);
			String adr2=result.getString(5);
			String cp=result.getString(6);
			String ville=result.getString(7);
			String mail=result.getString(8);
			Date dateInscription=result.getDate(9);
			Date dateArrivee=result.getDate(10);
			String cleWifi=result.getString(11);
			String statut=result.getString(12);
			Date dateNaissanceB=result.getDate(14);
			int idatelier=result.getInt(15);
			int idqualite=result.getInt(16);
			if(statut.equals("I"))
			{
				// intervenant
				lIntervenant=new Intervenant(numero,nom,prenom,adr1,adr2,cp,ville,mail,"I",idatelier);
				retourParticipant= lIntervenant;
			}
			else
			{
				if(statut.equals("L"))
				{
				// licencie
					Licencie lLicencie=new Licencie(numero,nom,prenom,adr1,adr2,cp,ville,mail,"L",dateInscription,dateArrivee,cleWifi,licence,idqualite,null);
					retourParticipant= lLicencie;
				}
				else 
				{	// Benevole
					Benevole lBenevole=new Benevole(numero,nom,prenom,adr1,adr2,cp,ville,mail,"B",licence,dateNaissanceB);
					retourParticipant= lBenevole;
				}
			}
			state.close();
			return retourParticipant;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
		
	public Participant rechercherParticipant(String mat){
		String requete="select * from Participant where idparticipant='"+mat+"'";
		Participant retourParticipant=null;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			Intervenant lIntervenant;
			if(!result.next())
				return null;
			Integer numero=result.getInt(1);
			String nom=result.getString(2);
			String prenom=result.getString(3);
			String adr1=result.getString(4);
			String adr2=result.getString(5);
			String ville=result.getString(6);
			String cp=result.getString(7);
			String mail=result.getString(8);
			if(result.getString(12).equals("I"))
			{
				// intervenant
				lIntervenant=new Intervenant(numero,nom,prenom,adr1,adr2,cp,ville,mail,"I",result.getInt(15));
				retourParticipant= lIntervenant;
			}
			else
			{
				if(result.getString(12).equals("L"))
				{
				// licencie
					Licencie lLicencie=new Licencie(numero,nom,prenom,adr1,adr2,cp,ville,mail,"L",result.getDate(9),result.getDate(10),result.getString(11),result.getString(13),result.getInt(15),null);
					retourParticipant= lLicencie;
				}
				else
				{	// Benevole
					Benevole lBenevole=new Benevole(numero,nom,prenom,adr1,adr2,cp,ville,mail,"B",result.getString(13),result.getDate(14));
					retourParticipant= lBenevole;
				}
			}
			state.close();
			return retourParticipant;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}

	
	public GestAtelierList chargeAtelier(){
		String requete="select * from Atelier";
		GestAtelierList  Listedesateliers = new GestAtelierList();
		Atelier unatelier;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				int idAtelier = result.getInt(1);
				int idParticipant = result.getInt(2);
				String libelleAtelier = result.getString(3);
				int nbPlacesMaxi = result.getInt(4);
				
				unatelier = new Atelier(idAtelier, idParticipant, libelleAtelier, nbPlacesMaxi);
				Listedesateliers.Ajouter(unatelier);
			}
			while (result.next());
			state.close();
			return Listedesateliers;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	
	public GestAtelierList chargeAtelierAvecVacation(){
		String requete="select * from atelier where idatelier in (select idatelier from Vacation) ";
		GestAtelierList  Listedesateliers = new GestAtelierList();
		Atelier unatelier;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				int idAtelier = result.getInt(1);
				int idParticipant = result.getInt(2);
				String libelleAtelier = result.getString(3);
				int nbPlacesMaxi = result.getInt(4);
				
				unatelier = new Atelier(idAtelier, idParticipant, libelleAtelier, nbPlacesMaxi);
				Listedesateliers.Ajouter(unatelier);
			}
			while (result.next());
			state.close();
			return Listedesateliers;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	public boolean majAtelier(GestAtelierList liste){
		// Recopie de la collection atelier dans la table atelier
		for (int i=0;i<liste.Nbelement();i++) {
			String requete = "update atelier set IDPARTICIPANT = "+ liste.elt(i).getNointervenant()+", LIBELLEATELIER = '"+liste.elt(i).getLibelleatelier()+"', NBPLACESMAXI = '"+ liste.elt(i).getMaxplace()+"' where IDATELIER = '"+liste.elt(i).getNoatelier()+"'";
			if (!executeReq(requete))
			{
				return false;
			}
		}
		return true;
	}
	
	public Vacation chargeDate(int idat, int idvac){
		String requete="select * from Vacation where idatelier ='"+ idat +"' and idvacation='"+idvac+"'";
		GestVacationList Listedesvacations = new GestVacationList();
		Vacation uneVacation;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				uneVacation = new Vacation(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
				Listedesvacations.Ajouter(uneVacation);
			}
			while (result.next());
			state.close();
			return uneVacation;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	
	public GestVacationList chargeVacation(int idat){
		String requete="select * from Vacation where idatelier="+idat;
		GestVacationList Listedesvacations = new GestVacationList();
		Vacation uneVacation;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				uneVacation = new Vacation(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
				Listedesvacations.Ajouter(uneVacation);
			}
			while (result.next());
			state.close();
			return Listedesvacations;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean majVacation(String idat, String idvac, String dated, String datef){
			String requete = "update vacation  set dateheuredebut = '"+ dated+"', dateheurefin = '"+ datef+"' where idatelier ='"+ idat +"' and idvacation='"+idvac+"'";
			if (!executeReq(requete))
			{
				return false;
			}
		return true;
	}
	
	public GestQualiteList chargeQualite(){
		String requete="select * from Qualite";
		GestQualiteList  Listedesqualites = new GestQualiteList();
		Qualite unequalite;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				unequalite = new Qualite(result.getInt(1), result.getString(2));
				Listedesqualites.Ajouter(unequalite);
			}
			while (result.next());
			state.close();
			return Listedesqualites;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	public GestCategorieList chargeCategorie(){
		String requete="select * from Categoriechambre";
		GestCategorieList  Listedescategories = new GestCategorieList();
		Categorie unecategorie;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				unecategorie = new Categorie(result.getInt(1), result.getString(2));
				Listedescategories.Ajouter(unecategorie);
			}
			while (result.next());
			state.close();
			return Listedescategories;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	public GestHotelList chargeHotel(){
		String requete="select * from hotel";
		GestHotelList  Listedeshotels = new GestHotelList();
		Hotel unhotel;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				unhotel = new Hotel(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8));
				Listedeshotels.Ajouter(unhotel);
				}
			while (result.next());
			state.close();
			return Listedeshotels;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	private boolean executeReq(String req){
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			state.executeUpdate(req);
			state.close();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private int executeReqLi(String req){
		int numero;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			state.executeUpdate(req);
			ResultSet result = state.executeQuery("select @@IDENTITY");
			if(!result.next())
				return 0;
			numero=result.getInt(1);
			state.close();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur sur la requete: "+e.getMessage(), "ALERTE"
					, JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		return numero;
	}
	public int sp_maxnumordre(int id) {
		int res=0;
		try {
			CallableStatement cstmt = ControleConnexion.getControleConnexion().getConnexion().prepareCall("{call dbo.GetMaxNumOrdre(?, ?)}");
			cstmt.setInt(1, id);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.execute();
			res=cstmt.getInt(2);
			System.out.println(res);
			}
		   catch (Exception e) {
		      e.printStackTrace();
		   }
		return res;
		}
	
	
}
