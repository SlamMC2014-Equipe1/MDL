package controle;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.swing.JOptionPane;

import entite.*;
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
		Benevole lBenevole = new Benevole(0, wnom,wprenom,wadr1,wadr2,wcp,wville, wmail, wstatut, wnolicence,wdatenaiss);
		String requete= lBenevole.req_InsertParticipant();
		return executeReq(requete);
	}
	public boolean enregistrerLicencie	(String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail,String wstatut,Date wdateins, Date wdateenr,String Wclewifi, String wnolicence,int widqualite)
	{
		Licencie lLicencie = new Licencie(0, wnom,wprenom,wadr1,wadr2,wcp,wville,wmail,wstatut, wdateins,wdateenr,Wclewifi,wnolicence,widqualite);
		String requete= lLicencie.req_InsertParticipant();
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
					Licencie lLicencie=new Licencie(numero,nom,prenom,adr1,adr2,cp,ville,mail,"L",result.getDate(9),result.getDate(10),result.getString(11),result.getString(13),result.getInt(15));
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
					Licencie lLicencie=new Licencie(numero,nom,prenom,adr1,adr2,cp,ville,mail,"L",result.getDate(9),result.getDate(10),result.getString(11),result.getString(13),result.getInt(15));
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
	
	public GestVacationList chargeVacation(int idat){
		String requete="select idvacation from Vacation where idatelier="+idat;
		GestVacationList Listedesvacations = new GestVacationList();
		Vacation uneVacation;
		try
		{
			Statement state = ControleConnexion.getControleConnexion().getConnexion().createStatement();
			ResultSet result=state.executeQuery(requete);
			if(!result.next())
				return null;
			do {
				uneVacation = new Vacation(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
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
