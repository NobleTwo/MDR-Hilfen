package verwandschaftscheck;
public class RelativeHorse {
  private RelativeHorse father;
  private RelativeHorse mother;
  private String name;
  private String race = " Unbekannt";
  boolean isFavourite = false;
  private boolean isMale = true;
  private int completePotential = -1;
  
  public RelativeHorse(String n, RelativeHorse f, RelativeHorse m){
    this.setFather(f);
    this.setMother(m);
    this.setName(n);
  }
  
  public RelativeHorse(String name, RelativeHorse father, RelativeHorse mother, String race, boolean isFavourite, boolean isMale, int completePotential){
    setFather(father);
    setMother(mother);
    setName(name);
    setRace(race);
    this.isFavourite = isFavourite;
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
}
