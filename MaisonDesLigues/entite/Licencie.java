package entite;
import java.sql.Date;
import java.util.ArrayList;
public class Licencie extends Participant
{
	private Date dateinscparticipant;
	private Date dateenrparticipant;
	private String clewifiparticipant;
	private String nolicencelicencie;
	private  int idqualitelicencie ;
	private ArrayList<Integer> listeAteliers;
	// constructeur
	public Licencie
	(Integer wnum, String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail, String wstatut, Date wdateins, Date wdateenr,String Wclewifi, String wnolicence,int widqualite, ArrayList<Integer> liste)
	{
		//appel du constructeur de la classe mère Participant
		super(wnum, wnom, wprenom, wadr1, wadr2, wcp, wville, wmail, wstatut);
		nolicencelicencie = wnolicence ;
		idqualitelicencie = widqualite ;
		dateinscparticipant=wdateins;
		dateenrparticipant=wdateenr;
		clewifiparticipant=Wclewifi;
		listeAteliers= new ArrayList<Integer>();
		listeAteliers = liste;
	}
	public Date getDateinscparticipant() {
		return dateinscparticipant;
	}
	public Date getDateenrparticipant() {
		return dateenrparticipant;
	}
	public String getClewifiparticipant() {
		return clewifiparticipant;
	}
	public String getNolicencelicencie() 
	{
		return nolicencelicencie;
	}
	public void setnolicencelicencie(String wnolicence) 
	{
		nolicencelicencie = wnolicence;
	}
	public int getIdqualitelicencie() 
	{
		return idqualitelicencie;
	}
	public void setIdqualitelicencie(int widqualite) 
	{
		idqualitelicencie = widqualite;
	}
	public String req_InsertParticipant() {
		return "insert into Participant (nomparticipant,prenomparticipant,"
			+ "adresse1participant, adresse2participant, cpparticipant, villeparticipant, "
			+ "mailparticipant, dateinscriptionparticipant, datearriveeparticipant, "
			+ "clewifiparticipant, statutparticipant, numerolicence, idqualitelicencie) "
			+ "values('"+super.getNomparticipant()+"','"+super.getPrenomparticipant()
			+"','"+super.getAdress1participant()+"','"+ super.getAdress2participant()+ "','"+super.getCpparticipant()
			+ "','"+ super.getVilleparticipant()+ "','"+super.getMailparticipant()+ "','"+this.getDateinscparticipant()
			+ "','"+this.getDateenrparticipant()+ "','"+ this.getClewifiparticipant()+"','"+super.getStatutparticipant()
			+"','"+this.getNolicencelicencie()+ "','"+ this.getIdqualitelicencie()+"');"; 
		}
	public String req_InsertInscrire(){
		String req="";
		for (int i=0; i<this.listeAteliers.size(); i++){	
			req+="insert into Inscrire (idatelier, idparticipant) values('"+this.listeAteliers.get(i)+"','"+super.getNumparticipant()+"') ";
		}
		return req;
	}
	
	public String getType() {
		return "Licencie";
	}
}
