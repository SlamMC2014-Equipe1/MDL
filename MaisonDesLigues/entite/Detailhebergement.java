package entite;
public class Detailhebergement 
{
	private Integer idparticipant;
	private Integer numordre;
	private Integer codehotel;
	private Integer idcategorie;
	private Integer iddatenuite;

	// constructeur
	public Detailhebergement(Integer widp, Integer wnumo, Integer wcodh, Integer widc,Integer widdn)
	{
		idparticipant = widp;
		numordre = wnumo;
		codehotel = wcodh;
		idcategorie=widc;
		iddatenuite=widdn;
	}
	public Integer getCodehotel() {
		return codehotel;
	}
	public Integer getIdparticipant() {
		return idparticipant;
	}
	public Integer getIdcategorie() {
		return idcategorie;
	}
	public Integer getIddatenuite() {
		return iddatenuite;
	}
	public Integer getNumordre() {
		return numordre;
	}
	public String req_InsertDetailHebergement() {
		return "insert into detailhebergement (idparticipant,numordre,"
			+ "codehotel, idcategorie, iddatenuitee) "
			+ "values('"+this.getIdparticipant()+"','"+this.getNumordre()
			+"','"+this.getCodehotel()+"','"+ this.getIdcategorie()+ "','"
			+this.getIddatenuite()+ "')"; 
		}

}
