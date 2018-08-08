package game.arenas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer.RacerEvent;
import utilities.Point;

import GUI.ToolBar;

public abstract class Arena {

	private final static int MIN_Y_GAP = 10;
	private ArrayList<Racer> activeRacers;
	private ArrayList<Racer> compleatedRacers;
	private ArrayList<Racer> brokenRacers;
	private ArrayList<Racer> disabledRacers;
	private  static volatile boolean activeRacersBoolean = false;


	private double length;
	private final int MAX_RACERS;
	private final double FRICTION;

	/**
	 * 
	 * @param length
	 *            the x value for the finish line
	 * @param maxRacers
	 *            Maximum number of racers
	 * @param friction
	 *            Coefficient of friction
	 * 
	 */
	protected Arena(double length, int maxRacers, double friction) {
		this.length = length;
		this.MAX_RACERS = maxRacers;
		this.FRICTION = friction;
		this.activeRacers = new ArrayList<Racer>();
		this.compleatedRacers = new ArrayList<Racer>();
	}

	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		if (this.activeRacers.size() == this.MAX_RACERS) {
			throw new RacerLimitException(this.MAX_RACERS, newRacer.getSerialNumber());
		}
		newRacer.setfric(this.FRICTION);
		this.activeRacers.add(newRacer);
		
	}

	public ArrayList<Racer> getActiveRacers() {
		return activeRacers;
	}

	public ArrayList<Racer> getCompleatedRacers() {
		return compleatedRacers;
	}

	public boolean hasActiveRacers() {
		return this.activeRacers.size() > 0;
	}

	public void initRace() {
		int y = 0;
		for (Racer racer : this.activeRacers) {
			Point s = new Point(0, y);
			Point f = new Point(this.length, y);
			racer.initRace(this, s, f);
			y += Arena.MIN_Y_GAP;
		}
	}

	/*start the race with using threadpool*/
	public void startRace() {
		
		ExecutorService pool = Executors.newFixedThreadPool(this.MAX_RACERS);
		
		for (Racer racer : this.activeRacers)
			pool.execute(racer);
		
		pool.shutdown();
		while(!pool.isTerminated()) { }

	}
	
	/*
	public void update(Observable observable, Object arg)
	{
		RacerEvent event = (RacerEvent) arg;
		switch (event)
		{
		case BROKENDOWN:
			this.brokenRacers.add((Racer)observable);
			this.activeRacers.remove((Racer)observable);
			break;
		case DISABLED:
			this.disabledRacers.add((Racer)observable);
			this.activeRacers.remove((Racer)observable);
			break;
		case FINISHED:
			this.compleatedRacers.add((Racer)observable);
			this.activeRacers.remove((Racer)observable);
			System.out.println("racer #" + ((Racer)observable).getSerialNumber() + " finished");
			
			activeRacersBoolean = this.hasActiveRacers();
			if (activeRacersBoolean == false){
				ToolBar.setInRace(false);
				ToolBar.setRaceEnded(true);
				System.out.println("race finished");
			}
			break;
		case REPAIRED:
			break;
		default:
			break;
		}
	}*/


	public void showResults() {
		for (Racer r : this.compleatedRacers) {
			String s = "#" + this.compleatedRacers.indexOf(r) + " -> ";
			s += r.describeRacer();
			System.out.println(s);
		}
	}

	public ArrayList<Racer> getBrokenRacers() {
		return brokenRacers;
	}
	
	public ArrayList<Racer> getDisabledRacers() {
		return disabledRacers;
	}

	public static void setActiveRacersBoolean(boolean activeRacersBoolean) {
		Arena.activeRacersBoolean = activeRacersBoolean;
	}
	
	public static boolean isActiveRacersBoolean() {
		return activeRacersBoolean;
	}
}
