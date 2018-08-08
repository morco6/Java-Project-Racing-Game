package game.arenas.exceptions;

@SuppressWarnings("serial")
public class RacerTypeException extends Exception {
	public RacerTypeException(String racerType, String arenaType) {
		super("Invalid Racer of type \"" + racerType + "\" for " + arenaType + " arena.");
	}

}
