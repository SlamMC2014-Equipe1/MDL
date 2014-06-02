package entite;
public class Vacation 
{
	private Integer idatelier;
	private Integer idvacation;
	private String dateheuredebut;
	private String dateheurefin;
	// constructeur
	public Vacation(Integer wnoat,Integer wnovac,String wdated, String wdatef)	{
		idatelier = wnoat;
		idvacation = wnovac;
		dateheuredebut = wdated;
		dateheurefin = wdatef;
	}
	public Integer getNoatelier() {
		return idatelier;
	}
	public Integer getNovacation() {
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


