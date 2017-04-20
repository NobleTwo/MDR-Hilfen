package relativeCheck;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import general.WarningDialog;

public abstract class DatabaseManager {
	private static Vector<RelativeHorse> population = new Vector<RelativeHorse>();
	private static Vector<String> favourites = new Vector<String>();
	public static final String fileName = "Datenbank.mdr";// "MDR-Datenbank";

	@SuppressWarnings("unchecked")
	public static Vector<RelativeHorse> getPopulation() {
		return (Vector<RelativeHorse>) population.clone();
	}

	public static void removeHorse(String name) {
		for (int i = 0; i < population.size(); i++) {
			RelativeHorse current = population.get(i);
			if (current.getFather() != null) {
				if (current.getFather().getName().equals(name))
					current.setFather(null);
			}
			if (current.getMother() != null) {
				if (current.getMother().getName().equals(name))
					current.setMother(null);
			}
			if (current.getName().equals(name)) {
				population.remove(i);
				i--;
			}
		}
		save();
	}

	public static RelativeHorse findHorse(String name) {
		Iterator<RelativeHorse> it = population.iterator();
		while (it.hasNext()) {
			RelativeHorse current = it.next();
			if (current.getName().equals(name)) {
				return current;
			}
		}
		return null;
	}

	// returns -1 if horse is already known and the index of insertion if horse
	// is new
	public static RelativeHorse addHorse(RelativeHorse newHorse) {
		if (newHorse.getName().equals("null"))
			return null;
		Iterator<RelativeHorse> it = population.iterator();
		while (it.hasNext()) {
			RelativeHorse h = it.next();
			if (h.getName().equals(newHorse.getName())) {
				if (newHorse.getFather() != null) {
					h.setFather(newHorse.getFather());
				}
				if (newHorse.getMother() != null) {
					h.setMother(newHorse.getMother());
				}
				if (!newHorse.getRace().equals(" Unbekannt")) {
					h.setRace(newHorse.getRace());
				}
				if (newHorse.getFavourites() != null) {
					h.setFavourites(newHorse.getFavourites());
				}
				if (newHorse.getCompletePotential() != 0) {
					h.setCompletePotential(newHorse.getCompletePotential());
				}
				h.setMale(newHorse.isMale());
				save();
				return h;
			}
		}
		population.add(newHorse);
		save();
		return newHorse;
	}

	private static int save() {
		RelativeHorse forFavs = new RelativeHorse("forFavs", null, null);
		forFavs.setFavourites(favourites);
		population.add(0, forFavs);
		OutputStream fos = null;
		try {
			// unhide
			File file = new File(DatabaseManager.fileName);
			Path path = file.toPath();
			if (file.isFile()) {
				Files.setAttribute(path, "dos:hidden", false);
			}
			// save
			fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (RelativeHorse rh : population) {
				oos.writeObject(rh);
			}
			oos.close();
			fos.close();

			// hide
			if (file.isFile()) {
				Files.setAttribute(path, "dos:hidden", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			new WarningDialog(new JFrame(), "Kann nicht auf Datei '" + fileName + "' zugreifen. Vielleicht ist schon ein andere Instanz von MDR-Hilfen geöffnet?");
			return -1;
		}
		population.remove(forFavs);
		return 0;
	}

	public static int load() {
		InputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			population = new Vector<RelativeHorse>();
			while (true) {
				population.add((RelativeHorse) ois.readObject());
			}
		} catch (EOFException e) {
			favourites = population.get(0).getFavourites();
			population.remove(0);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			new WarningDialog(new JFrame(), "Kann Datei '" + fileName + "' nicht laden.");
			if (population.size() != 0) {
				population.remove(0);
			}
			return -1;
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				return -2;
			}
		}
	}

	public static void setPopulation(Vector<RelativeHorse> newPop) {
		population = newPop;
		save();
	}

	@SuppressWarnings("unchecked")
	public static Vector<String> getFavourites() {
		return (Vector<String>) favourites.clone();
	}

	public static boolean addFavourite(String name) {
		if (favourites.contains(name)) {
			return false;
		} else {
			favourites.add(name);
			save();
		}
		return true;
	}

	// gibt -1 zurück, wenn alter Name nicht vorhanden
	// gibt -2 zurück, wenn neuer Name bereits vorhanden
	// gibt sonst 0 zurück
	public static int editFavourite(String oldName, String newName) {
		if (!favourites.contains(oldName)) {
			return -1;
		} else if (favourites.contains(newName)) {
			return -2;
		} else {
			favourites.remove(oldName);
			favourites.add(newName);
			Iterator<RelativeHorse> it = population.iterator();
			while (it.hasNext()) {
				Vector<String> favTemp = it.next().getFavourites();
				if (favTemp.contains(oldName)) {
					favTemp.remove(oldName);
					favTemp.add(newName);
				}
			}
			save();
			return 0;
		}
	}

	public static void deleteFavourite(String name) {
		favourites.remove(name);
		Iterator<RelativeHorse> it = population.iterator();
		while (it.hasNext()) {
			Vector<String> favTemp = it.next().getFavourites();
			if (favTemp != null && favTemp.contains(name)) {
				favTemp.remove(name);
			}
		}
		save();
	}

	public static void addPopulation(Vector<RelativeHorse> secondPopulation) {
		for (RelativeHorse rh : secondPopulation) {
			addHorse(rh);
		}
		save();
	}
}
