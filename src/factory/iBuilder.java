package factory;

import GUI.viewRace;
import game.arenas.Arena;
import game.racers.Racer;

public interface iBuilder {
	public Arena setRace(viewRace vRace, RaceBuilder builder);
}
