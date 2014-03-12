package entite;
public class Hotel 
{
	private Integer codehotel;
	private String nomhotel;
	private String adresse1hotel;
	private String adresse2hotel;
	private String cphotel;
	private String villehotel;
	private String telhotel;
	private String mailhotel;
	// constructeur
	public Hotel(Integer wmat,String wnom,String wadr1, String wadr2, String wcp, String wville, String wtel, String wmail)
	{
		codehotel = wmat;
		nomhotel = wnom;
		adresse1hotel = wadr1;
		adresse2hotel = wadr2;
		cphotel=wcp;
		villehotel=wville;
		telhotel=wtel;
		mailhotel=wmail;
	}
	public Integer getCodehotel() {
		return codehotel;
	}
	public String getNomhotel() {
		return nomhotel;
	}
	public String getAdress1hotel() {
		return adresse1hotel;
	}
	public String getAdress2hotel() {
		return adresse2hotel;
	}
	public String getCphotel() {
		return cphotel;
	}
	public String getVillehotel() {
		return villehotel;
	}
	public String getMailhotel() {
		return mailhotel;
	}
	public String getTelhotel() {
		return telhotel;
	}
	
}
