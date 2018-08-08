package factory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import GUI.viewRace;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.iRacer;
import game.racers.decorator.ColoredRacer;
import utilities.EnumContainer.Color;

public class CarRaceBuilder implements iBuilder{
	
	private static Arena arena;
	private int numOfRacers;
	private static ArrayList<Racer> racers;
	String cls = "game.racers.land.Car";
	String RacerName =  "Car";
	double RacerSpeed = Double.valueOf(120);
	double RacerAcceleration = Double.valueOf(12);
	Color color = Color.BLACK;
	
	public CarRaceBuilder(int N, Arena arena){
		this.arena = arena;
		this.numOfRacers = N;
	}
	
	public Arena setRace(viewRace vRace, RaceBuilder builder){
		racers = new ArrayList<>();	
		Racer.setLastSerialNumber(1);
		
		try {
			
			racers.add(builder.buildWheeledRacer(cls, RacerName, RacerSpeed, RacerAcceleration, color, 4));
			racers.get(racers.size()-1).setArena(arena);
			
			if (addRacerToArena(racers.get(racers.size()-1))){
				vRace.loadRacerBackground("Car","BLACK");
			}
			
			for(int i=1 ; i < numOfRacers ; i++){
				//Racer racer = (racers.get(i)).clone(builder, i);
				racers.add((racers.get(racers.size()-1)).clone(builder, i));
				racers.get(racers.size()-1).setArena(arena);
				if (addRacerToArena(racers.get(racers.size()-1))){
					vRace.loadRacerBackground("Car","BLACK");
				}
			}
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return arena;
	}
	
	
	private static boolean addRacerToArena(Racer newRcaer) {
		try {
			arena.addRacer(newRcaer);
			return true;
		} catch (RacerLimitException e) {
			JOptionPane.showMessageDialog(null, "[Error] " + e.getMessage());
			return false;
		} catch (RacerTypeException e) {
			JOptionPane.showMessageDialog(null, "[Error] " + e.getMessage());
			return false;
		}
	}
}
