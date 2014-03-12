package entite;
public class Atelier 
{
	private Integer noatelier;
	private Integer nointervenant;
	private String libelleatelier;
	private Integer maxplace;
	// constructeur
	public Atelier(Integer wnoat,Integer wnoint,String wlib, Integer wnbplace)	{
		noatelier = wnoat;
		nointervenant = wnoint;
		libelleatelier = wlib;
		maxplace = wnbplace;
	}
	public Integer getNoatelier() {
		return noatelier;
	}
	public String getLibelleatelier() {
		return libelleatelier;
	}
	public Integer getNointervenant() {
		return nointervenant;
	}
	public void SetNointervenant(int wnoint) {
		nointervenant=wnoint;
	}
	public Integer getMaxplace() {
		return maxplace;
	}
	

}


