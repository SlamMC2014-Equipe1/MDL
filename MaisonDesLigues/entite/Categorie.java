package entite;
public class Categorie 
{
	private Integer idcategorie;
	private String libellecategorie;
	// constructeur
	public Categorie(Integer wid,String wlib)	{
		idcategorie = wid;
		libellecategorie = wlib;
	}
	public Integer getIdcategorie() {
		return idcategorie;
	}
	public String getLibellecategorie() {
		return libellecategorie;
	}
}


