package game.arenas;

import java.util.ArrayList;
import java.util.Observable;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;

public interface iArena {
	public String arenaType();
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException;
	
	public ArrayList<Racer> getActiveRacers();
	public ArrayList<Racer> getCompleatedRacers();
	public boolean hasActiveRacers();
	public void initRace();
	public void startRace();
	//public void update(Observable observable, Object arg);
	public void showResults();
	public ArrayList<Racer> getBrokenRacers();
	public ArrayList<Racer> getDisabledRacers();


}
