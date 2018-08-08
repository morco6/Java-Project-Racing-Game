package game.arenas.sea;

import game.arenas.Arena;
import game.arenas.iArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.sea.INavalRacer;
import utilities.EnumContainer;

public class NavalArena extends Arena implements iArena{
	
	private final static String cName = "NavalArena";
	private final static double DEFAULT_FRICTION = 0.7;
	private final static int DEFAULT_MAX_RACERS = 5;
	private final static int DEFAULT_LENGTH = 1000;

	EnumContainer.Water water;
	EnumContainer.WaterSurface surface;
	EnumContainer.Body body;

	public NavalArena() {
		this(DEFAULT_LENGTH, DEFAULT_MAX_RACERS);
	}

	public NavalArena(double length, int maxRacers) {
		super(length, maxRacers, DEFAULT_FRICTION);
		this.water = EnumContainer.Water.SWEET;
		this.surface = EnumContainer.WaterSurface.FLAT;
		this.body = EnumContainer.Body.LAKE;
	}

	@Override
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		if (newRacer instanceof INavalRacer) {
			super.addRacer(newRacer);
		} else {
			throw new RacerTypeException(newRacer.className(), "Naval");
		}
	}

	@Override
	public String arenaType() {
		// TODO Auto-generated method stub
		return this.cName;
	}

}
