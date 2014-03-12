package entite;

public class Intervenant extends Participant
{
	private int idatelierintervenant;
	// constructeur
	public Intervenant(Integer wnum,String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail, String wstatut, int widatelier)
	{
		//appel du constructeur de la classe mère Participant
		super(wnum, wnom, wprenom, wadr1, wadr2, wcp, wville, wmail,wstatut);
		idatelierintervenant = widatelier ;
	}
	public int getIdatelierintervenant() 
	{
		return idatelierintervenant;
	}
	public void setIdatelierintervenant(int widatelier) 
	{
		idatelierintervenant = widatelier;
	}
	public String req_InsertParticipant() {
		return "insert into Participant (nomparticipant,prenomparticipant,"
			+ "adresse1participant, adresse2participant, cpparticipant, villeparticipant, "
			+ "mailparticipant, statutparticipant, idatelierintervenant) "
			+ "values('"+super.getNomparticipant()+"','"+super.getPrenomparticipant()
			+"','"+super.getAdress1participant()+"','"+ super.getAdress2participant()+ "','"+super.getCpparticipant()
			+ "','"+ super.getVilleparticipant()+ "','"+super.getMailparticipant()+ "','"+super.getStatutparticipant()
			+ "','"+this.idatelierintervenant+"')"; 
		}
	}
