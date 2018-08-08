package game.racers;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Observable;

import GUI.ToolBar;
import factory.RaceBuilder;
import factory.RacingClassesFinder;
import game.arenas.Arena;
import utilities.EnumContainer;
import utilities.EnumContainer.RacerEvent;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;

public abstract class Racer extends Observable implements Runnable, iRacer, Cloneable{
	protected static int lastSerialNumber = 1;

	private int serialNumber; // Each racer has an unique number, assigned by arena in addRacer() method
	private String name;
	private Point currentLocation;
	private Point finish;
	private Arena arena;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	@SuppressWarnings("unused")
	private double failureProbability; // Chance to break down
	private EnumContainer.Color color; // (RED,GREEN,BLUE,BLACK,YELLOW)
	private double fric;
	private Mishap mishap;
	private int numOfWheels;
	private Hashtable<String,Object> hm = new Hashtable<String,Object>();  

	public static int timer = 0;
	
	/**
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Racer(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		this.serialNumber = Racer.lastSerialNumber++;
		this.hm.put("SerialNumber",this.serialNumber); 
		this.name = name;
		this.hm.put("Name",this.name); 
		this.maxSpeed = maxSpeed;
		this.hm.put("MaxSpeed",this.maxSpeed); 
		this.acceleration = acceleration;
		this.hm.put("Acceleration",this.acceleration); 
		this.color = color;
		this.hm.put("Color",this.color); 
		 
	}

	public abstract String className();

	public String describeRacer() {
		String s = "";
		s += "name: " + this.name + ", ";
		s += "SerialNumber: " + this.serialNumber + ", ";
		s += "maxSpeed: " + this.maxSpeed + ", ";
		s += "acceleration: " + this.acceleration + ", ";
		s += "color: " + this.color + ", ";
		s = s.substring(0, s.length() - 2);
		// returns a string representation of the racer, including: general attributes
		// (color name, number) and specific ones (numberOfWheels, etc.)
		s += this.describeSpecific();
		return s;
	}

	public abstract String describeSpecific();

	public int getSerialNumber() {
		return serialNumber;
	}

	private boolean hasMishap() {
		if (this.mishap != null && this.mishap.getTurnsToFix() == 0)
			this.mishap = null;
		return this.mishap != null;
	}

	public void initRace(Arena arena, Point start, Point finish) {
		this.arena = arena;
		this.currentLocation = new Point(start);
		this.finish = new Point(finish);
	}

	public void introduce() {
		// Prints a line, obtained from describeRacer(), with its type
		System.out.println("[" + this.className() + "] " + this.describeRacer());
	}

	public synchronized Point move(double friction) {
		double reductionFactor = 1;
		if (!(this.hasMishap()) && Fate.breakDown(this.failureProbability)) {
			this.mishap = Fate.generateMishap();
			System.out.println(this.name + " Has a new mishap! (" + this.mishap + ")");
		}

		if (this.hasMishap()) {
			if (this.mishap.isFixable()==false){
				this.notifyObservers(EnumContainer.RacerEvent.DISABLED);
				Thread.currentThread().interrupt(); //stop thread
			}			
			else
				this.notifyObservers(EnumContainer.RacerEvent.BROKENDOWN);
			
			reductionFactor = mishap.getReductionFactor();
			this.mishap.nextTurn();
		}
		else
			this.notifyObservers(EnumContainer.RacerEvent.ACTIVE);

		if (this.currentSpeed < this.maxSpeed) {
			this.currentSpeed += this.acceleration * friction * reductionFactor;
		}
		if (this.currentSpeed > this.maxSpeed) {
			this.currentSpeed = this.maxSpeed;
		}
		double newX = (this.currentLocation.getX() + (this.currentSpeed));
		GUI.viewRace.setX(this.getSerialNumber(), newX);
		Point newLocation = new Point(newX, this.currentLocation.getY());
		this.currentLocation = newLocation;

		return this.currentLocation;
	}
	
	public void setfric(double f){
		this.fric = f;
	}
	
	private void notifyObservers(RacerEvent event) {
		switch (event){
		case FINISHED:
			ToolBar.update(this, RacerEvent.FINISHED);
			break;
		case BROKENDOWN:
			ToolBar.update(this, RacerEvent.BROKENDOWN);
			break;
		case DISABLED:
			ToolBar.update(this, RacerEvent.DISABLED);
			break;
		case ACTIVE:
			ToolBar.update(this, RacerEvent.ACTIVE);
			break;
		default:
			break;
		}
	}
	
	
	/*notification about the state of racer(thread)*/ 
	/*
	private void notifyObservers(RacerEvent event) {
		switch (event){
		case FINISHED:
			this.arena.update(this, RacerEvent.FINISHED);
			break;
		case BROKENDOWN:
			this.arena.update(this, RacerEvent.BROKENDOWN);
			break;
		case DISABLED:
			this.arena.update(this, RacerEvent.DISABLED);
			break;
		case REPAIRED:
			this.arena.update(this, RacerEvent.REPAIRED);
			break;
		default:
			break;
		}
	}*/
	
	@Override
	public void run(){
		while(this.currentLocation.getX() < this.finish.getX()){
			//System.out.println("Starting racer-move(thread)" + this.serialNumber);
			timer = timer+1;
			move(this.fric);
			Thread.currentThread();
			this.setChanged();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e){e.printStackTrace();}
			
			//System.out.println("Complete racer-move(thread)" + this.serialNumber);
		}	
		this.notifyObservers(utilities.EnumContainer.RacerEvent.FINISHED);
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
	
	public static void setLastSerialNumber(int lastSerialNumber) {
		Racer.lastSerialNumber = lastSerialNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Point getFinish() {
		return finish;
	}

	public void setFinish(Point finish) {
		this.finish = finish;
	}

	public Arena getArena() {
		return arena;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public double getFailureProbability() {
		return failureProbability;
	}

	public void setFailureProbability(double failureProbability) {
		this.failureProbability = failureProbability;
	}

	public EnumContainer.Color getColor() {
		return color;
	}

	public void setColor(EnumContainer.Color color) {
		this.color = color;
	}

	public Mishap getMishap() {
		return mishap;
	}

	public void setMishap(Mishap mishap) {
		this.mishap = mishap;
	}
	
	public void addAttribute(){
		System.out.println("runing decoration");
		//hm.put(nameOfAttribute, o);
	}

	public Hashtable<String, Object> getHm() {
		return hm;
	}

	public int getNumOfWheels() {
		return numOfWheels;
	}

	public void setNumOfWheels(int numOfWheels) {
		this.numOfWheels = numOfWheels;
	}
	
	public Racer clone(RaceBuilder builder, int cloneNum){
		Racer clonedRacer = null;
		String cN = (Integer.valueOf(cloneNum)).toString();
		String cls = className();
		for (String str : RacingClassesFinder.getInstance().getRacersList()){
			if (str.contains(cls)){
				if (cls.equals("Airplane") || cls.equals("Car")  || cls.equals("Bicycle")){
					try {
						clonedRacer = builder.buildWheeledRacer(str, (getName() + " c" + cN), getMaxSpeed(), getAcceleration(), getColor(), getNumOfWheels());
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
							| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if ( cls.equals("Helicopter")  || cls.equals("Horse") || cls.equals("RowBoat") || cls.equals("SpeedBoat") ){
					try {
						clonedRacer = builder.buildRacer(str, (getName() + " c" + cN), getMaxSpeed(), getAcceleration(), getColor());
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
							| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return clonedRacer;
	}

}
