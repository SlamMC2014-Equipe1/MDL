package entite;

import java.util.ArrayList;
import java.util.List;

public class GestHotelList {

	private List<Hotel> LesHotels= new ArrayList<Hotel>();
	
	public int Nbelement(){
		return LesHotels.size();
	}
	
	public void Ajouter (Hotel unHotel)
	{
		LesHotels.add(unHotel);
	}
	
	public Hotel elt (int ind)
	{
		return LesHotels.get(ind);
	}
	public int recherhcer(String num)
	{
		int trouve = -1;
		int taille = LesHotels.size();
		for (int ind = 0 ; ind < taille; ind ++ )
		{
			if (num.equals(LesHotels.get(ind).getCodehotel()))
			{
				trouve = ind;
			}
			
		}
		return trouve;
		
	}
}