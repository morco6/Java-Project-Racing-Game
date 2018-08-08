package game.racers.decorator;

import game.racers.iRacer;

public abstract class RacerDecorator implements iRacer {
	
	protected iRacer decoratedRacer;
	
	public RacerDecorator(iRacer r){
		this.decoratedRacer = r;
	}
	@Override
	public void addAttribute(){
		decoratedRacer.addAttribute();
	}
	@Override
	public iRacer getDecoratedRacer() {
		return decoratedRacer;
	}

}
