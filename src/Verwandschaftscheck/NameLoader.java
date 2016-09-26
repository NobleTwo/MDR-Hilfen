package Verwandschaftscheck;
import javax.swing.*;

class NameLoader{
  private JTextField[] nameArray;
  private JComboBox[] raceArray;
  private DocumentManager dm;

  public NameLoader(String name, DocumentManager doMa, JTextField[] naAr, JComboBox[] raAr, JCheckBox isFavourite, JRadioButton isMale, JRadioButton isFemale){
    nameArray = naAr;
    raceArray = raAr;
    dm = doMa;
    
    nameArray[0].setText(name);
    if(name == null){
      raceArray[0].setSelectedIndex(0);
      isMale.setSelected(true);
      isFemale.setSelected(false);
      for(int i=1; i<nameArray.length; i++){
        nameArray[i].setText("");
        raceArray[i].setSelectedItem(0);
      }
    } else{
      nameArray[0].setCaretPosition(0);
      int popIndex = dm.find(name);
      if(popIndex<0){          
         return;
      }
      RelativeHorse rh = dm.population.get(popIndex);
      String raceName = rh.race;
      raceArray[0].setSelectedIndex(findRaceIndex(raceName));
      if(isFavourite!=null)
        isFavourite.setSelected(rh.isFavourite);
      isMale.setSelected(rh.isMale());
      isFemale.setSelected(!rh.isMale());
      for(int i=1; i<nameArray.length; i++){
        load(i);
      }
    }
  }

  private int findRaceIndex(String raceName){
    int length = raceArray[0].getItemCount();
    for(int i=1; i<length; i++){
       if(((raceArray[0].getItemAt(i)).toString()).equals(raceName))
          return i;
    }
    return 0;
  }
   
  private void load(int index){
    if(index!=0){
      String name = "nicht in DB";
      String race = (raceArray[0].getItemAt(0)).toString();
      int childIndex = (index-1)/2;
      String childName = nameArray[childIndex].getText();
      if(childName.equals("nicht in DB") || childName.equals(""))
        return;
      int populationIndexChild = dm.find(childName);
      if(!(populationIndexChild<0)){
        RelativeHorse child = dm.population.get(populationIndexChild);
        if(index%2==0 && child.mother!=null){
              name = child.mother.name;
              race = child.mother.race;
        }
        else if(index%2==1 && child.father!=null){
             name = child.father.name;
             race = child.father.race;
        } 
      }
      nameArray[index].setText(name);
      nameArray[index].setCaretPosition(0);
      raceArray[index].setSelectedItem(race);
    }
  }

}