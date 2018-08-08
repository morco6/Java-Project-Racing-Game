package game.racers;

import java.util.Hashtable;
import utilities.EnumContainer;

public abstract interface iRacer{

	public void iRacer();
	public void addAttribute();
	public void setColor(EnumContainer.Color color);
	public Hashtable<String, Object> getHm();
	public void setNumOfWheels(int numOfWheels);
	public iRacer getDecoratedRacer();
	public void introduce();
	
	
}
