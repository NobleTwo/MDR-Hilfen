package relativeCheck;

import java.util.Iterator;

public class HorseLoader {
	private static RelativeHorse[] horseArray = new RelativeHorse[15];

	public static RelativeHorse[] load(String name) {
		horseArray[0] = find(name);
		for (int i = 1; i < horseArray.length; i++) {
			load(i);
			// correct wrong database entries
			if(horseArray[i] != null){
				if(horseArray[i].getName().equals("Unbekannt") && !horseArray[i].getRace().equals(" Unbekannt")){
					horseArray[i].setRace(" Unbekannt");
				}
			}
		}
		return horseArray;
	}

	private static RelativeHorse find(String name) {
		Iterator<RelativeHorse> it = DatabaseManager.getPopulation().iterator();
		while (it.hasNext()) {
			RelativeHorse current = it.next();
			if (current.getName().equals(name)) {
				return current;
			}
		}
		return null;
	}

	private static void load(int index) {
		if (index != 0) {
			int childIndex = (index - 1) / 2;
			RelativeHorse child = horseArray[childIndex];
			if (child == null)
				horseArray[index] = null;
			else if (index % 2 == 0) {
				horseArray[index] = child.getMother();
			} else if (index % 2 == 1) {
				horseArray[index] = child.getFather();
			}
		}
	}

	public static void assignNamesAndRaces(String name, HorseAndRaceField[] horseNamesAndRaces, GPField[] fieldGP) {
		RelativeHorse[] population = load(name);
		for (int i = 0; i < population.length; i++) {
			if (population[i] != null) {
				horseNamesAndRaces[i].setName(population[i].getName());
				horseNamesAndRaces[i].setCaretPosition(0);
				
				horseNamesAndRaces[i].setSelectedItem(population[i].getRace());
				if(fieldGP!=null && i<fieldGP.length){
					fieldGP[i].setGP(population[i].getCompletePotential());
				}
			} else {
				horseNamesAndRaces[i].setName("nicht in DB");
				horseNamesAndRaces[i].setSelectedItem(" Unbekannt");
				if(fieldGP!=null && i<fieldGP.length){
					fieldGP[i].setGP(-1);
				}
			}
		}
	}
}