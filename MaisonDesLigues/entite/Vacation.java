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
		return "insert into Atelier (idatelier, idvacation, dateheuredebut, dateheurefin)" +
				"values ('"+this.getNoatelier()+"', '"+this.getNovacation()+"','"+this.getdated()+"','"+this.getdatef()+"')"; 
		
	}
	

}


