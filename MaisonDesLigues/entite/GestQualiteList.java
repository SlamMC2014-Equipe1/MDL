package entite;

import java.util.ArrayList;
import java.util.List;

public class GestQualiteList {

	private List<Qualite> LesQualite= new ArrayList<Qualite>();
	
	public int Nbelement(){
		return LesQualite.size();
	}
	
	public void Ajouter (Qualite uneQualite)
	{
		LesQualite.add(uneQualite);
	}
	
	public Qualite elt (int ind)
	{
		return LesQualite.get(ind);
	}
	public int recherhcer(String num)
	{
		int trouve = -1;
		int taille = LesQualite.size();
		for (int ind = 0 ; ind < taille; ind ++ )
		{
			if (num.equals( LesQualite.get(ind).getIdqualite()))
			{
				trouve = ind;
			}
			
		}
		return trouve;
		
	}
}