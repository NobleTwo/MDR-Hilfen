package relativeCheck;

import java.util.Iterator;
import java.util.Vector;

public class HorseLoader {
	private static RelativeHorse[] horseArray = new RelativeHorse[16];
	private static final Vector<RelativeHorse> horses = DatabaseManager.getPopulation();

	private static RelativeHorse[] load(String name) {
		horseArray[0] = find(name);
		for (int i = 1; i < horseArray.length; i++) {
			load(i);
		}
		return horseArray;
	}

	private static RelativeHorse find(String name) {
		Iterator<RelativeHorse> it = horses.iterator();
		while (it.hasNext()) {
			RelativeHorse current = it.next();
			if (current.getName().equals(name)) {
				return current;
			}
		}
		System.out.println(horses.size());
		return null;
	}

	private static void load(int index) {
		if (index != 0) {
			int childIndex = (index - 1) / 2;
			RelativeHorse child = horseArray[childIndex];
			if (child == null)
				return;
			else if (index % 2 == 0) {
				horseArray[index] = child.getMother();
			} else if (index % 2 == 1) {
				horseArray[index] = child.getFather();
			}
		}
	}

	public static void assignNamesAndRaces(String name, HorseAndRaceField[] horseNamesAndRaces) {
		RelativeHorse[] population = load(name);
		System.out.println(population[0].getName());
		for (int i = 0; i < horseNamesAndRaces.length; i++) {
			if (population[i] != null) {
				horseNamesAndRaces[i].setName(population[i].getName());
				horseNamesAndRaces[i].setSelectedItem(population[i].getRace());
			} else {
				horseNamesAndRaces[i].setName("nicht in DB");
				horseNamesAndRaces[i].setSelectedItem(" Unbekannt");
			}
		}
	}
}