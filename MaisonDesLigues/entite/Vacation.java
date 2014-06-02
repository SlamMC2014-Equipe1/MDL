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
		return "insert into Atelier (idatelier, idvacation, dateheuredebut, dateheurefin)" +
				"values ('"+this.getNoatelier()+"', '"+this.getNovacation()+"','"+this.getdated()+"','"+this.getdatef()+"')"; 
		
	}
	

}


