package verwandschaftscheck;

import java.util.Vector;

public class NewHorseGUI extends ManageHorseGUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewHorseGUI(){
		super("Verwandtschaftscheck: Neues Pferd erstellen");
		buttonAddOrSaveHorse.setText("Pferd hinzufügen");
		buttonResetOrDeleteHorse.setText("Zurücksetzen");
		buttonChooseHorse.setText("Bestehendes Pferd bearbeiten");
		/*JButton buttonCreateNewHorse = new JButton();
		buttonCreateNewHorse.setBounds(544, 80, 185, 25);
		buttonCreateNewHorse.setText("Neues Pferd erstellen");
		buttonCreateNewHorse.setMargin(new Insets(2, 2, 2, 2));
		buttonCreateNewHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createNewHorse();
			}
		});
		Container cp = this.getContentPane();
		cp.add(buttonCreateNewHorse);*/
		super2();
		textInput.requestFocus();
	}
	
	@Override
	protected void addOrSaveHorse() {
		//add
		if ((horseNamesAndRaces[0].getName()).equals("")){
			return;
		}
		RelativeHorse father = null;
		RelativeHorse mother = null;
		String name;
		RelativeHorse[] horses = new RelativeHorse[horseNamesAndRaces.length];
		for (int i = horseNamesAndRaces.length - 1; i > 0; i--) {
			String currentName = horseNamesAndRaces[i].getName();
			if (currentName.equals("") || currentName.equals("nicht in DB"))
				continue;
			if ((2 * i + 1) < horseNamesAndRaces.length) {
				father = horses[2 * i + 1];
				mother = horses[2 * i + 2];
			}
			String currentRace = horseNamesAndRaces[i].getSelectedItem();
			horses[i] = new RelativeHorse(currentName, father, mother, currentRace, new Vector<String>(), i % 2 == 1,-1);
			//dm.addHorse(horses[i]);
		}
			name = horseNamesAndRaces[0].getName();
		if (!(name.equals("") || name.equals("nicht in DB"))) {
			father = horses[1];
			mother = horses[2];
			//boolean isFav = isFavourite.isSelected();
			//String race = String.valueOf(horseNamesAndRaces[0].getSelectedItem());
				//dm.addHorse(new RelativeHorse(name, father, mother, race, isFav, radioButtonIsMale.isSelected(),-1), true);
			/*if(!subject.getName().equals(name)){
				dm.removeHorse(subject.getName());
			}*/
		}
	}
	
	@Override
	protected void resetOrDelete() {
		//reset
		new NewHorseGUI();
		this.dispose();
	}
	
	public static void main(String[] args){
		new NewHorseGUI();
	}
}
