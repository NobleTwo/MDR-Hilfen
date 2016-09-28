package verwandschaftscheck;
public class RelativeHorse {
  RelativeHorse father;
  RelativeHorse mother;
  String name;
  String race;
  boolean isFavourite;
  private boolean isMale;
  
  public RelativeHorse(String n, RelativeHorse f, RelativeHorse m){
    this.father = f;
    this.mother = m;
    this.name = n;
    this.race = " Unbekannt";
    this.isFavourite = false;
    this.isMale = true;
  }
  
  public RelativeHorse(String name, RelativeHorse father, RelativeHorse mother, String race, boolean isFavourite, boolean isMale){
    this.father = father;
    this.mother = mother;
    this.name = name;
    this.race = race;
    this.isFavourite = isFavourite;
    this.isMale = isMale;
  }

  public boolean isMale(){
    return this.isMale;
  }
}
