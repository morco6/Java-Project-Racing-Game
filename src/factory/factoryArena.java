package factory;

import java.lang.reflect.InvocationTargetException;

import game.arenas.iArena;

public class factoryArena {
	
	public iArena getArena(String a, int arenaLength, int amountOfRacers, RaceBuilder builder){
		iArena iarena = null;
		if(a.equals("Air"))
			try {
				iarena = (iArena)(builder.buildArena("game.arenas.air.AerialArena", arenaLength, amountOfRacers));
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(a.equals("Land"))
			try {
				iarena = (iArena)(builder.buildArena("game.arenas.land.LandArena", arenaLength, amountOfRacers));
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(a.equals("Navy"))
			try {
				iarena = (iArena)(builder.buildArena("game.arenas.sea.NavalArena", arenaLength, amountOfRacers));
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return iarena;
	}

}
