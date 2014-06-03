package entite;
import java.sql.Date;

public class Benevole extends Participant
{
	private String nolicencebenevole;
	private Date datenaissbenevole;

	// constructeur
	public Benevole
	(Integer wnum, String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail, String wstatut, String wnolicence, Date wdatenaiss)
	{
		//appel du constructeur de la classe mère Participant
		super( wnum, wnom, wprenom, wadr1, wadr2, wcp, wville, wmail,wstatut);
		nolicencebenevole = wnolicence;
		datenaissbenevole = wdatenaiss;
	}

	public String getNolicencebenevole() 
	{
		return nolicencebenevole;
	}
	public void setNolicencebenevole(String wnolicence) 
	{
		nolicencebenevole = wnolicence;
	}
	public Date getDatenaissbenevole() 
	{
		return datenaissbenevole;
	}
	public void setDatenaissbenevole(Date wdatenaiss) 
	{
		datenaissbenevole = wdatenaiss;
	}

	public String req_InsertParticipant() {
		return "insert into Participant (nomparticipant,prenomparticipant,"
			+ "adresse1participant, adresse2participant, cpparticipant, villeparticipant, "
			+ "mailparticipant, statutparticipant, numerolicence, datenaissancebenevole)"
			+ "values('"+super.getNomparticipant()+"','"+super.getPrenomparticipant()
			+"','"+super.getAdress1participant()+"','"+ super.getAdress2participant()
			+"','"+super.getCpparticipant()+ "','"+ super.getVilleparticipant()+ "','"
			+super.getMailparticipant()+ "','"+ super.getStatutparticipant()+"','"
			+this.getNolicencebenevole()+ "','"+ this.getDatenaissbenevole()+ "')"; 
		}

	public String getType() {
		return "Benevole";
	}

}
