package entite;
import java.sql.Date;
public class Vacation 
{
	private String idatelier;
	private String idvacation;
	private String dateheuredebut;
	private String dateheurefin;
	// constructeur
	public Vacation(String wnoat,String wnovac,String wdated, String wdatef)	{
		idatelier = wnoat;
		idvacation = wnovac;
		dateheuredebut = wdated;
		dateheurefin = wdatef;
	}
	public String getNoatelier() {
		return idatelier;
	}
	public String getNovacation() {
		return idvacation;
	}
	public String getdated() {
		return dateheuredebut;
	}
	public String getdatef() {
		return dateheurefin;
	}
	public String req_InsertVacation() {
		return "EXEC SP_INSERT_VACATION @IDATELIER = " + this.getNoatelier() + ", "
				+ "@IDVACATION = " + this.getNovacation() +", "
				+ "@DATEHEUREDEBUT = '" + this.getdated() + "', "
				+ "@DATEHEUREFIN = '" + this.getdatef() + "';";
	}
	
	public String req_SelectMaxIdVacation() {
		return "SELECT MAX(IDVACATION) FROM Vacation WHERE IDATELIER = " + this.getNoatelier();
	}

}


