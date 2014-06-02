package controle;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javax.swing.JOptionPane;
import entite.*;

public class GestionAjouter {
	
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
	public boolean enregistrerAtelier(Integer wnoat,Integer wnoint,String wlib, Integer wnbplace){
		Atelier lAtelier = new Atelier(wnoat, wnoint, wlib, wnbplace);
		String requete= lAtelier.req_InsertAtelier();
		return executeReq(requete);
	}
	
}
