package verwandschaftscheck;

import java.io.Serializable;
import java.util.Vector;

public class RelativeHorse implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String[] races = new String[] { " Unbekannt", "Achal-Tekkiner", "American Paint Horse", "American Quarter Horse", "Andalusier", "Appaloosa", "Berber", "Cape Boerperd", "Criollo",
			"Deutsches Reitpony", "Englisches Vollblut", "Hannoveraner", "Holsteiner", "Lipizzaner", "Lusitano", "Oldenburger", "Pinto", "Trakehner", "Vollblutaraber" };
	
	private RelativeHorse father;
	private RelativeHorse mother;
	private String name;
	private String race = " Unbekannt";
	private boolean isMale = true;
	private Vector<String> favourites = new Vector<String>();
	private int completePotential = -1;
  
	public RelativeHorse(String n, RelativeHorse f, RelativeHorse m){
		this.setFather(f);
	    this.setMother(m);
	    this.setName(n);
	}
  
  public RelativeHorse(String name, RelativeHorse father, RelativeHorse mother, String race, Vector<String> favourites, boolean isMale, int completePotential){
    setFather(father);
    setMother(mother);
    setName(name);
    setRace(race);
    this.favourites = favourites;
    this.isMale = isMale;
    setCompletePotential(completePotential);
  }

  public boolean isMale(){
    return this.isMale;
  }

	public String getRace() {
		return race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public int getCompletePotential() {
		return completePotential;
	}
	
	public void setCompletePotential(int completePotential) {
		this.completePotential = completePotential;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public RelativeHorse getFather() {
		return father;
	}
	
	public void setFather(RelativeHorse father) {
		this.father = father;
	}
	
	public RelativeHorse getMother() {
		return mother;
	}
	
	public void setMother(RelativeHorse mother) {
		this.mother = mother;
	}
	
	public Vector<String> getFavourites(){
		return this.favourites;
	}
	
	public void addFavourite(String name){
		this.favourites.add(name);
	}
	
	public void removeFavourite(String name){
		this.favourites.remove(name);
	}
	
	public void setFavourites(Vector<String> favourites){
		this.favourites = favourites;
	}
	
	public static String[] getRaces(){
		return races;
	}
}
