package entite;
public abstract class Participant 
{
	private Integer numparticipant;
	private String nomparticipant;
	private String prenomparticipant;
	private String adresse1participant;
	private String adresse2participant;
	private String cpparticipant;
	private String villeparticipant;
	private String mailparticipant;
	private String statutparticipant;
	

	// constructeur
	public Participant(Integer wnum,String wnom,String wprenom, String wadr1, String wadr2, String wcp, String wville, String wmail, String wstatut)
	{
		numparticipant = wnum;
		nomparticipant = wnom;
		prenomparticipant = wprenom;
		adresse1participant = wadr1;
		adresse2participant = wadr2;
		cpparticipant=wcp;
		villeparticipant=wville;
		mailparticipant=wmail;
		statutparticipant=wstatut;
		
	}
	public Integer getNumparticipant(){
		return numparticipant;
	}
	public String getNomparticipant() {
		return nomparticipant;
	}
	public String getPrenomparticipant() {
		return prenomparticipant;
	}
	public String getAdress1participant() {
		return adresse1participant;
	}
	public String getAdress2participant() {
		return adresse2participant;
	}
	public String getCpparticipant() {
		return cpparticipant;
	}
	public String getVilleparticipant() {
		return villeparticipant;
	}
	public String getMailparticipant() {
		return mailparticipant;
	}
	
	public String getStatutparticipant(){
		return statutparticipant;
	}
	public abstract String req_InsertParticipant();
	
}
