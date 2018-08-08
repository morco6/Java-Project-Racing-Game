package game.racers.decorator;

import java.util.Hashtable;

import game.racers.iRacer;
import utilities.EnumContainer;
import utilities.EnumContainer.Color;

public class ColoredRacer extends RacerDecorator{
	
	private String attributeName = "color";
	private EnumContainer.Color color;
	
	public ColoredRacer(iRacer r, utilities.EnumContainer.Color color){
		super(r);
		this.color = color;
	}

	@Override
	public void iRacer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAttribute() {
		(decoratedRacer).setColor(this.color);
		(decoratedRacer).getHm().put(attributeName, this.color);
	}

	@Override
	public void introduce() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hashtable<String, Object> getHm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNumOfWheels(int numOfWheels) {
		// TODO Auto-generated method stub
		
	}

}
