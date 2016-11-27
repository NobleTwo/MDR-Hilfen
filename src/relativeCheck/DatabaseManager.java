package relativeCheck;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import general.Warndialog;

public abstract class DatabaseManager {
	private static Vector<RelativeHorse> population = new Vector<RelativeHorse>();
	private static Vector<String> favourites = new Vector<String>();
	public static final String fileName = "MDR-Datenbank";
	
	public static Vector<RelativeHorse> getPopulation(){
		return population;
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
		save();
		return population.size() - 1;
	}
	
	private static int save() {
		RelativeHorse forFavs = new RelativeHorse("forFavs", null, null);
		forFavs.setFavourites(favourites);
		population.add(0, forFavs);
		OutputStream fos = null;
		try{
			fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for(RelativeHorse rh: population){
				oos.writeObject(rh);
			}
			oos.close();
			fos.close();
		} catch(Exception e){
			new Warndialog(new JFrame(), "Kann nicht auf Datei '"+fileName+"' zugreifen. Vielleicht ist schon ein andere Instanz von MDR-Hilfen geöffnet?");
			return -1;
		}
		population.remove(forFavs);
		return 0;
	}
	
	public static int load(){
		InputStream fis = null;
		try{
			fis = new FileInputStream(fileName);
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
			new Warndialog(new JFrame(), "Kann Datei '"+fileName+"' nicht laden.");
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
		save();
	}
	
	public static Vector<String> getFavourites(){
		return favourites;
	}
	
	public static boolean addFavourite(String name){
		if(favourites.contains(name)){
			return false;
		} else{
			favourites.add(name);
			save();
		}
		return true;
	}
	
	//gibt -1 zurück, wenn alter Name nicht vorhanden
	// gibt -2 zurück, wenn neuer Name bereits vorhanden
	//gibt sonst 0 zurück
	public static int editFavourite(String oldName, String newName){
		if(!favourites.contains(oldName)){
			return -1;
		} else if(favourites.contains(newName)){
			return -2;
		} else{
			favourites.remove(oldName);
			favourites.add(newName);
			Iterator<RelativeHorse> it = population.iterator();
			while(it.hasNext()){
				Vector<String> favTemp = it.next().getFavourites();
				if(favTemp.contains(oldName)){
					favTemp.remove(oldName);
					favTemp.add(newName);
				}
			}
			save();
			return 0;
		}
	}
	
	public static void deleteFavourite(String name){
		favourites.remove(name);
		Iterator<RelativeHorse> it = population.iterator();
		while(it.hasNext()){
			Vector<String> favTemp = it.next().getFavourites();
			if(favTemp.contains(name)){
				favTemp.remove(name);
			}
		}
		save();
	}
}
