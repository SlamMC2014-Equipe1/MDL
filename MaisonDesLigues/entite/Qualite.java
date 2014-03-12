package entite;
public class Qualite 
{
	private Integer idqualite;
	private String libellequalite;
	// constructeur
	public Qualite(Integer widqual,String wlibqual)	{
		idqualite = widqual;
		libellequalite = wlibqual;
	}
	public Integer getIdqualite() {
		return idqualite;
	}
	public String getLibellequalite() {
		return libellequalite;
	}
}


