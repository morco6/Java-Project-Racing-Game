/**
 * 
 */
package utilities;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.arenas.land.LandArena;
import game.arenas.sea.NavalArena;
import game.racers.Racer;
import game.racers.air.Airplane;
import game.racers.air.Helicopter;
import game.racers.land.Bicycle;
import game.racers.land.Car;
import game.racers.land.Horse;
import game.racers.sea.RowBoat;
import game.racers.sea.SpeedBoat;
import utilities.EnumContainer.Color;

/**
 * @author Mor Cohen
 *
 */
public class Program {

	private static Arena arena;
	private static RaceBuilder builder = RaceBuilder.getInstance();
	private static ArrayList<Racer> racers;

	private static void addRacersToArena() {
		for (Racer racer : racers) {
			try {
				arena.addRacer(racer);
			} catch (RacerLimitException e) {
				System.out.println("[Error] " + e.getMessage());
			} catch (RacerTypeException e) {
				System.out.println("[Error] " + e.getMessage());
			}
		}
	}

	private static void initAirRace() {
		try {
			arena = builder.buildArena("game.arenas.air.AerialArena", 1450, 4);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			System.out.println("Unable to build arena!");
			arena = new AerialArena();
		}
		racers = new ArrayList<>();
		try {
			racers.add(builder.buildWheeledRacer("game.racers.air.Airplane", "Bob", 220, 10, Color.BLUE, 3));
			racers.add(builder.buildWheeledRacer("game.racers.air.Airplane", "John", 175, 20, Color.BLUE, 3));
			racers.add(builder.buildWheeledRacer("game.racers.air.Airplane", "Frank", 180, 15, Color.BLUE, 3));
			racers.add(builder.buildRacer("game.racers.air.Helicopter", "Matt", 230, 8, Color.RED));
			racers.add(builder.buildWheeledRacer("game.racers.land.Car", "car", 15, 1, Color.GREEN, 3));
			racers.add(builder.buildRacer("game.racers.air.Helicopter", "Alby", 200, 8, Color.BLUE));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}

		addRacersToArena();
	}

	private static void initLandRace() {
		try {
			arena = builder.buildArena("game.arenas.land.LandArena", 1450, 8);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			System.out.println("Unable to build arena!");
			arena = new LandArena();
		}
		racers = new ArrayList<>();
		try {
			racers.add(builder.buildWheeledRacer("game.racers.land.Car", "Bob", 220, 10, Color.BLUE, 4));
			racers.add(builder.buildWheeledRacer("game.racers.land.Car", "John", 175, 20, Color.BLUE, 4));
			racers.add(builder.buildRacer("game.racers.land.Horse", "Frank", 180, 15, Color.BLUE));
			racers.add(builder.buildRacer("game.racers.land.Horse", "Matt", 230, 8, Color.RED));
			racers.add(builder.buildWheeledRacer("game.racers.land.Bicycle", "Timmy", 15, 1, Color.GREEN, 3));
			racers.add(builder.buildRacer("game.racers.air.Helicopter", "Alby", 200, 8, Color.BLUE));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}

		addRacersToArena();
	}

	private static void initNavalRace() {
		try {
			arena = builder.buildArena("game.arenas.sea.NavalArena", 1225, 2);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			System.out.println("Unable to build arena!");
			arena = new NavalArena();
		}
		racers = new ArrayList<>();
		try {
			racers.add(builder.buildRacer("game.racers.sea.RowBoat", "Bob", 220, 10, Color.BLUE));
			racers.add(builder.buildRacer("game.racers.sea.SpeedBoat", "John", 175, 20, Color.BLUE));
			racers.add(builder.buildRacer("game.racers.sea.RowBoat", "Matt", 230, 8, Color.RED));
			racers.add(builder.buildWheeledRacer("game.racers.land.Car", "car", 15, 1, Color.GREEN, 3));
			racers.add(builder.buildRacer("game.racers.land.Car", "car", 15, 1, Color.GREEN)); // intentional exception!
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		addRacersToArena();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fate.setSeed(477734503); // to get same "random" results every run;
		////////////////////////////////////////////
		testDefaults();
		System.out.println("----------");
		////////////////////////////////////////////
		System.out.println("New Air Race");
		initAirRace();
		arena.initRace();
		startRace();
		arena.showResults();
		////////////////////////////////////////////
		System.out.println("----------");
		System.out.println("New Land Race");
		initLandRace();
		arena.initRace();
		startRace();
		arena.showResults();
		////////////////////////////////////////////
		System.out.println("----------");
		System.out.println("New Naval Race");
		initNavalRace();
		arena.initRace();
		startRace();
		arena.showResults();

	}

	private static void startRace() {
		System.out.println("Introduction: ");
		for (Racer racer : arena.getActiveRacers())
			racer.introduce();
		System.out.println("Strat Race!");
		while (arena.hasActiveRacers()) {
			arena.startRace();
		}
		System.out.println("Race Compleated!");
	}

	private static void testDefaults() {
		System.out.println("Testing default valus and introduction.");
		(new Car()).introduce();
		(new Horse()).introduce();
		(new Bicycle()).introduce();
		(new Helicopter()).introduce();
		(new Airplane()).introduce();
		(new SpeedBoat()).introduce();
		(new RowBoat()).introduce();
		System.out.println("End of test.");
	}

}
