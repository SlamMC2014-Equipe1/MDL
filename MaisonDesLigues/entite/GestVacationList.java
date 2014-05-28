package entite;

import java.util.ArrayList;
import java.util.List;

public class GestVacationList {

	private List<Vacation> LesVacations = new ArrayList<Vacation>();
	
	public int Nbelement(){
		return LesVacations.size();
	}
	
	public void Ajouter (Vacation uneVacation)
	{
		LesVacations.add(uneVacation);
	}
	
	public Vacation elt (int ind)
	{
		return LesVacations.get(ind);
	}
	public int rechercher(Integer num)
	{
		int trouve = -1;
		int taille = LesVacations.size();
		for (int ind = 0 ; ind < taille; ind ++ )
		{
			if (num.equals( LesVacations.get(ind).getNoatelier()))
			{
				trouve = ind;
			}
			
		}
		return trouve;
		
	}
}