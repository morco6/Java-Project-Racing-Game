package game.arenas.air;

import game.arenas.Arena;
import game.arenas.iArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.IAerialRacer;
import utilities.EnumContainer;

public class AerialArena extends Arena implements iArena {

	private final static String cName = "AerialArena";
	private final static double DEFAULT_FRICTION = 0.4;
	private final static int DEFAULT_MAX_RACERS = 6;
	private final static int DEFAULT_LENGTH = 1500;

	EnumContainer.Vision vision;
	EnumContainer.Weather wather;
	EnumContainer.Height height;
	EnumContainer.Wind wind;

	public AerialArena() {
		this(DEFAULT_LENGTH, DEFAULT_MAX_RACERS);
	}

	/**
	 * @param length
	 *            The x value for the finish line
	 * @param maxRacers
	 *            Maximum number of racers
	 */
	public AerialArena(double length, int maxRacers) {
		super(length, maxRacers, DEFAULT_FRICTION);
		this.vision = EnumContainer.Vision.SUNNY;
		this.wather = EnumContainer.Weather.DRY;
		this.height = EnumContainer.Height.HIGH;
		this.wind = EnumContainer.Wind.HIGH;
	}

	@Override
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		if (newRacer instanceof IAerialRacer) {
			super.addRacer(newRacer);
		} else {
			throw new RacerTypeException(newRacer.className(), "Aerial");
		}
	}





	@Override
	public String arenaType() {
		// TODO Auto-generated method stub
		return this.cName;
	}
}
