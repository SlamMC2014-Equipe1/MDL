package entite;

import java.util.ArrayList;
import java.util.List;

public class GestCategorieList {

	private List<Categorie> LesCategories = new ArrayList<Categorie>();
	
	public int Nbelement(){
		return LesCategories.size();
	}
	
	public void Ajouter (Categorie uneCategorie)
	{
		LesCategories.add(uneCategorie);
	}
	
	public Categorie elt (int ind)
	{
		return LesCategories.get(ind);
	}
	public int recherhcer(String num)
	{
		int trouve = -1;
		int taille = LesCategories.size();
		for (int ind = 0 ; ind < taille; ind ++ )
		{
			if (num.equals(LesCategories.get(ind).getIdcategorie()))
			{
				trouve = ind;
			}
			
		}
		return trouve;
		
	}
}