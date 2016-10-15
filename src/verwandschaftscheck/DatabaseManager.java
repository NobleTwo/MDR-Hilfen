package verwandschaftscheck;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Vector;

public abstract class DatabaseManager {
	private static Vector<RelativeHorse> population = new Vector<RelativeHorse>();
	private static Vector<String> favourites = new Vector<String>();
	
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
	}
	
	public static int find(String n) {
		int i = 0;
		while (i < population.size()) {
			if (population.get(i).getName().equals(n)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public static int addHorse(RelativeHorse rh) { // returns -1 if horse is already known and the index of insertion if horse is new
		if (rh.getName().equals("null"))
			return -2;
		int numberOfHorses = population.size();
		for (int i = 0; i < numberOfHorses; i++) {
			RelativeHorse h = population.elementAt(i);
			if (h.getName().equals(rh.getName())) {
				if (rh.getFather() != null) {
					h.setFather(rh.getFather());
				}
				if (rh.getMother() != null) {
					h.setMother(rh.getMother());
				}
				if (!rh.getRace().equals(" Unbekannt")) {
					h.setRace(rh.getRace());
				}
				return -1;
			}
		}
		population.add(rh);
		return population.size() - 1;
	}
	
	public static int save() {
		RelativeHorse forFavs = new RelativeHorse("forFavs", null, null);
		forFavs.setFavourites(favourites);
		population.add(0, forFavs);
		OutputStream fos = null;
		try{
			fos = new FileOutputStream("MDR-Datenbank");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for(RelativeHorse rh: population){
				oos.writeObject(rh);
			}
			oos.close();
			fos.close();
		} catch(Exception e){
			return -1;
		}
		population.remove(forFavs);
		return 0;
	}
	
	public static int load(){
		InputStream fis = null;
		try{
			fis = new FileInputStream("MDR-Datenbank");
			ObjectInputStream ois = new ObjectInputStream(fis);
			population = new Vector<RelativeHorse>();
			while(true){
				population.add((RelativeHorse)ois.readObject());
			}
		} catch(EOFException e){
			favourites = population.get(0).getFavourites();
			population.remove(0);
			return 0;
		} catch(Exception e){
			return -1;
		} finally{
			try{
				fis.close();
			} catch(IOException ioe){
				return -2;
			}
		}
	}
	
	public static void setPopulation(Vector<RelativeHorse> newPop){
		population = newPop;
	}
}
