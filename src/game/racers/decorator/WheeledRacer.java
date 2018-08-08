package game.racers.decorator;

import java.util.Hashtable;

import game.racers.iRacer;
import utilities.EnumContainer.Color;

public class WheeledRacer extends RacerDecorator{
	
	private String attributeName = "numOfWheels";
	private int nOw;

	public WheeledRacer(iRacer r, int nOw) {
		super(r);
		this.nOw = nOw;
	}

	public String describeSpecific() {
		return ", Number of Wheels: " + this.getNumOfWheels();
	}

	public int getNumOfWheels() {
		return nOw;

}

	public String getAttributeName() {
		return attributeName;
	}

	@Override
	public void iRacer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAttribute() {
		(decoratedRacer).setNumOfWheels(this.nOw);
		(decoratedRacer).getHm().put(attributeName, this.nOw);
		
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