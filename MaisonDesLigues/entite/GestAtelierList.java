package entite;

import java.util.ArrayList;
import java.util.List;

public class GestAtelierList {

	private List<Atelier> LesAteliers = new ArrayList<Atelier>();
	
	public int Nbelement(){
		return LesAteliers.size();
	}
	
	public void Ajouter (Atelier unAtelier)
	{
		LesAteliers.add(unAtelier);
	}
	
	public Atelier elt (int ind)
	{
		return LesAteliers.get(ind);
	}
	public int rechercher(Integer num)
	{
		int trouve = -1;
		int taille = LesAteliers.size();
		for (int ind = 0 ; ind < taille; ind ++ )
		{
			if (num.equals( LesAteliers.get(ind).getNoatelier()))
			{
				trouve = ind;
			}
			
		}
		return trouve;
		
	}
}