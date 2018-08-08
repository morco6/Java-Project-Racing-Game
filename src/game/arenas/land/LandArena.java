package game.arenas.land;

import game.arenas.Arena;
import game.arenas.iArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.ILandRacer;
import utilities.EnumContainer;

public class LandArena extends Arena implements iArena{

	private final static String cName = "LandArena";
	private final static double DEFAULT_FRICTION = 0.5;
	private final static int DEFAULT_MAX_RACERS = 8;
	private final static int DEFAULT_LENGTH = 800;

	EnumContainer.Coverage coverage;
	EnumContainer.LandSurface surface;

	public LandArena() {
		this(DEFAULT_LENGTH, DEFAULT_MAX_RACERS);
	}

	public LandArena(double length, int maxRacers) {
		super(length, maxRacers, DEFAULT_FRICTION);
		this.coverage = EnumContainer.Coverage.GRASS;
		this.surface = EnumContainer.LandSurface.FLAT;
	}

	@Override
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		if (newRacer instanceof ILandRacer) {
			super.addRacer(newRacer);
		} else {
			throw new RacerTypeException(newRacer.className(), "Land");
		}
	}

	@Override
	public String arenaType() {
		// TODO Auto-generated method stub
		return this.cName;
	}
}
