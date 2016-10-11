package verwandschaftscheck;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import allgemein.MDRFrame;
import allgemein.MainMenu;

public abstract class ManageHorseGUI extends MDRFrame {

	private HorseAndRaceField[] horseNamesAndRaces = new HorseAndRaceField[15];
	protected JButton buttonAddOrSaveHorse = new JButton();
	protected JButton buttonChooseHorse = new JButton();
	protected JButton buttonResetOrDelete = new JButton();

	/*public ManageHorseGUI(String name) {
		super();
		start(false);
		new NameLoader(name, dm, horseNames, horseRaces, isFavourite, radioButtonIsMale, radioButtonIsFemale);
		int subjectPosition = dm.find(horseNames[0].getText());
		if(subjectPosition >=0){
			subject = dm.population.get(subjectPosition);
		} else{
			subject = null;
		}
	}*/

	@SuppressWarnings("unchecked")
	public ManageHorseGUI() {
		super();

		//Frame-Initialisierung
		int frameWidth = 913;
		int frameHeight = 575;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		Container cp = getContentPane();
		cp.setLayout(null);
		
		//Anfang Komponenten		
		for(int i = 0; i<horseNamesAndRaces.length; i++){
			horseNamesAndRaces[i] = new HorseAndRaceField();
			cp.add(horseNamesAndRaces[i]);	
		}
		horseNamesAndRaces[0].setLocation(16, 248);
		
		horseNamesAndRaces[1].setLocation(144, 136);
		horseNamesAndRaces[2].setLocation(144, 352);
		
		horseNamesAndRaces[3].setLocation(272, 80);
		horseNamesAndRaces[3].setLocation(272, 192);
		horseNamesAndRaces[5].setLocation(272, 296);
		horseNamesAndRaces[6].setLocation(272, 416);
		
		horseNamesAndRaces[7].setLocation(400, 48);
		horseNamesAndRaces[3].setLocation(400, 104);
		horseNamesAndRaces[9].setLocation(400, 160);
		horseNamesAndRaces[10].setLocation(400, 216);
		horseNamesAndRaces[11].setLocation(400, 272);
		horseNamesAndRaces[12].setLocation(400, 328);
		horseNamesAndRaces[13].setLocation(400, 384);
		horseNamesAndRaces[14].setLocation(400, 440);
		
		buttonAddOrSaveHorse.setBounds(272, 504, 123, 25);
		buttonAddOrSaveHorse.setMargin(new Insets(2, 2, 2, 2));
		buttonAddOrSaveHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addOrSaveHorse();
			}
		});
		cp.add(buttonAddOrSaveHorse);

		JLabel labelGP = new JLabel("GP: ");
		labelGP.setBounds(16, 304, 17, 17);
		cp.add(labelGP);

		buttonChooseHorse.setBounds(544, 48, 185, 25);
		buttonChooseHorse.setMargin(new Insets(2, 2, 2, 2));
		buttonChooseHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new HorseChoice(new DocumentManager());
				dispose();
			}
		});
		cp.add(buttonChooseHorse);
		
		buttonResetOrDelete.setBounds(400, 504, 121, 25);
		buttonResetOrDelete.setMargin(new Insets(2, 2, 2, 2));
		buttonResetOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetOrDelete();
			}
		});
		cp.add(buttonResetOrDelete);
		
		JButton buttonSave = new JButton("Datenbank speichern");
		buttonSave.setBounds(544, 112, 185, 25);
		buttonSave.setMargin(new Insets(2, 2, 2, 2));
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				save();
			}
		});
		cp.add(save);
		createNewHorse.setBounds(544, 80, 185, 25);
		createNewHorse.setText("Neues Pferd erstellen");
		createNewHorse.setMargin(new Insets(2, 2, 2, 2));
		if (newHorseMode)
			createNewHorse.setVisible(false);
		createNewHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				createNewHorse_ActionPerformed(evt);
			}
		});
		cp.add(createNewHorse);
		ButtonAnalyzeText.setBounds(720, 504, 169, 25);
		ButtonAnalyzeText.setText("Text analysieren");
		ButtonAnalyzeText.setMargin(new Insets(2, 2, 2, 2));
		ButtonAnalyzeText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonAnalyzeText_ActionPerformed(evt);
			}
		});
		cp.add(ButtonAnalyzeText);
		TextInputScrollPane.setBounds(544, 160, 345, 329);
		cp.add(TextInputScrollPane);
		jComboBox0.setBounds(16, 280, 121, 17);
		jComboBox0.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox0);
		jComboBox1.setBounds(144, 168, 121, 17);
		jComboBox1.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox1);
		jComboBox2.setBounds(144, 384, 121, 17);
		jComboBox2.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox2);
		jComboBox3.setBounds(272, 112, 121, 17);
		jComboBox3.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox3);
		jComboBox4.setBounds(272, 224, 121, 17);
		jComboBox4.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox4);
		jComboBox5.setBounds(272, 328, 121, 17);
		jComboBox5.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox5);
		jComboBox6.setBounds(272, 448, 121, 17);
		jComboBox6.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox6);
		jComboBox7.setBounds(400, 80, 121, 17);
		jComboBox7.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox7);
		jComboBox8.setBounds(400, 136, 121, 17);
		jComboBox8.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox8);
		jComboBox9.setBounds(400, 192, 121, 17);
		jComboBox9.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox9);
		jComboBox10.setBounds(400, 248, 121, 17);
		jComboBox10.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox10);
		jComboBox11.setBounds(400, 304, 121, 17);
		jComboBox11.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox11);
		jComboBox12.setBounds(400, 360, 121, 17);
		jComboBox12.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox12);
		jComboBox13.setBounds(400, 416, 121, 17);
		jComboBox13.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox13);
		jComboBox14.setBounds(400, 472, 121, 17);
		jComboBox14.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		cp.add(jComboBox14);
		buttonGoToRC.setBounds(736, 48, 153, 25);
		buttonGoToRC.setText("Verwandtschaftscheck");
		buttonGoToRC.setMargin(new Insets(2, 2, 2, 2));
		buttonGoToRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonGoToRC_ActionPerformed(evt);
			}
		});
		cp.add(buttonGoToRC);
		ButtonExit.setBounds(736, 112, 153, 25);
		ButtonExit.setText("Schließen");
		ButtonExit.setMargin(new Insets(2, 2, 2, 2));
		ButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonExit_ActionPerformed(evt);
			}
		});
		cp.add(ButtonExit);
		ButtonGoToMenu.setBounds(736, 80, 153, 25);
		ButtonGoToMenu.setText("Hauptmenü");
		ButtonGoToMenu.setMargin(new Insets(2, 2, 2, 2));
		ButtonGoToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonGoToMenu_ActionPerformed(evt);
			}
		});
		cp.add(ButtonGoToMenu);
		cp.setBackground(new Color(0xB8CFE5));
		resetText.setBounds(544, 504, 169, 25);
		resetText.setText("Text löschen");
		resetText.setMargin(new Insets(2, 2, 2, 2));
		resetText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetText_ActionPerformed(evt);
			}
		});
		cp.add(resetText);
		jLabel2.setBounds(376, 8, 211, 33);
		jLabel2.setText("Datenbankverwaltung");
		jLabel2.setFont(new Font("Dialog", Font.BOLD, 20));
		cp.add(jLabel2);
		radioButtonIsMale.setBounds(16, 323, 17, 17);
		radioButtonIsMale.setText("");
		radioButtonIsMale.setOpaque(false);
		radioButtonIsMale.setEnabled(true);
		radioButtonIsMale.setSelected(true);
		radioButtonIsMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				radioButtonIsMale_ActionPerformed(evt);
			}
		});
		cp.add(radioButtonIsMale);
		radioButtonIsFemale.setBounds(16, 340, 17, 17);
		radioButtonIsFemale.setText("");
		radioButtonIsFemale.setOpaque(false);
		radioButtonIsFemale.setEnabled(true);
		radioButtonIsFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				radioButtonIsFemale_ActionPerformed(evt);
			}
		});
		cp.add(radioButtonIsFemale);
		jLabel3.setBounds(40, 322, 59, 19);
		jLabel3.setText("Hengst");
		cp.add(jLabel3);
		jLabel4.setBounds(40, 342, 59, 11);
		jLabel4.setText("Stute");
		cp.add(jLabel4);
		// Ende Komponenten
		horseNames = new JTextField[15];
		horseNames[0] = jTextField0;
		horseNames[1] = jTextField1;
		horseNames[2] = jTextField2;
		horseNames[3] = jTextField3;
		horseNames[4] = jTextField4;
		horseNames[5] = jTextField5;
		horseNames[6] = jTextField6;
		horseNames[7] = jTextField7;
		horseNames[8] = jTextField8;
		horseNames[9] = jTextField9;
		horseNames[10] = jTextField10;
		horseNames[11] = jTextField11;
		horseNames[12] = jTextField12;
		horseNames[13] = jTextField13;
		horseNames[14] = jTextField14;

		horseRaces = new JComboBox[horseNames.length];
		horseRaces[0] = jComboBox0;
		horseRaces[1] = jComboBox1;
		horseRaces[2] = jComboBox2;
		horseRaces[3] = jComboBox3;
		horseRaces[4] = jComboBox4;
		horseRaces[5] = jComboBox5;
		horseRaces[6] = jComboBox6;
		horseRaces[7] = jComboBox7;
		horseRaces[8] = jComboBox8;
		horseRaces[9] = jComboBox9;
		horseRaces[10] = jComboBox10;
		horseRaces[11] = jComboBox11;
		horseRaces[12] = jComboBox12;
		horseRaces[13] = jComboBox13;
		horseRaces[14] = jComboBox14;

		for (int i = 0; i < horseRaces.length; i++) {
			for (int j = 0; j < races.length; j++) {
				horseRaces[i].addItem(races[j]);
			}
		}
		setVisible(true);
	} // end of public RCGUIdatabase

	// Anfang Methoden
	public void addHorse_ActionPerformed(ActionEvent evt) {
		if ((horseNames[0].getText()).equals("") && newHorseMode){
			return;
		}
		RelativeHorse father = null;
		RelativeHorse mother = null;
		String name;
		RelativeHorse[] horses = new RelativeHorse[horseNames.length];
		for (int i = horseNames.length - 1; i > 0; i--) {
			String currentName = horseNames[i].getText();
			if (currentName.equals("") || currentName.equals("nicht in DB"))
				continue;
			if ((2 * i + 1) < horseNames.length) {
				father = horses[2 * i + 1];
				mother = horses[2 * i + 2];
			}
			String currentRace = (String) horseRaces[i].getSelectedItem();
			horses[i] = new RelativeHorse(currentName, father, mother, currentRace, false, i % 2 == 1,-1);
			dm.addHorse(horses[i]);
		}

		name = horseNames[0].getText();
		if (!(name.equals("") || name.equals("nicht in DB"))) {
			father = horses[1];
			mother = horses[2];
			boolean isFav = isFavourite.isSelected();
			String race = String.valueOf(horseRaces[0].getSelectedItem());

			dm.addHorse(new RelativeHorse(name, father, mother, race, isFav, radioButtonIsMale.isSelected(),-1), true);
			if(!subject.getName().equals(name)){
				dm.removeHorse(subject.getName());
			}
		}
	}

	public void changeMode_ActionPerformed(ActionEvent evt) {
		new HorseChoice(dm);
		this.dispose();
	}

	public void reset_ActionPerformed(ActionEvent evt) {
		if (!newHorseMode)
			delete();
		for (int i = 0; i < horseNames.length; i++) {
			horseNames[i].setText("");
		}
		isFavourite.setSelected(false);
		for (int i = 0; i < horseRaces.length; i++) {
			horseRaces[i].setSelectedIndex(0);
		}
	}

	public void delete() {
		if ((horseNames[0].getText()).equals(""))
			return;
		dm.removeHorse(horseNames[0].getText());
	}

	public void save(){
		DocumentManager dm = new DocumentManager();
		dm.save();
		try {
			dm.analyzeFile();
		} catch (IllegalFileException e) {
			e.popUp(this);
		}
	}

	public void createNewHorse_ActionPerformed(ActionEvent evt) {
		new ManageHorseGUI();
		this.dispose();
	}

	public void ButtonAnalyzeText_ActionPerformed(ActionEvent evt) {
		String[] lineSplit = TextInput.getText().split("\n");
		int counter = 0;
		int start = 0;
		for (int i = 0; i < lineSplit.length; i++) {
			if (lineSplit[i].contains("Gesamtpotential") && !lineSplit[i].contains("Potential")) {
				start = i;
				break;
			}
		}

		for (int i = start; i < lineSplit.length && counter < horseNames.length; i++) {
			String current = lineSplit[i];
			if (current.contains("Unbekannt")) {
				horseNames[counter].setText(lineSplit[i]);
				counter++;
			} else {
				if (counter < 7 && current.contains("Gesamtpotential")) {
					horseNames[counter].setText(lineSplit[i - 3]);
					horseNames[counter].setCaretPosition(0);
					String racename = lineSplit[i - 2];
					for (int j = 0; j < races.length; j++) {
						if (racename.contains(races[j])) {
							racename = races[j];
						}
					}
					horseRaces[counter].setSelectedItem(racename);
					counter++;
				} else if (counter >= 7) {
					horseNames[counter].setText(lineSplit[i]);
					horseNames[counter].setCaretPosition(0);
					i++;
					String racename = lineSplit[i];
					for (int j = 0; j < races.length; j++) {
						if (racename.contains(races[j])) {
							racename = races[j];
						}
					}
					horseRaces[counter].setSelectedItem(racename);
					counter++;
				}
			}
		}
		if (counter != horseNames.length)
			TextInput.setText("Nicht alle Pferdenamen gefunden.");
		for (int i = 0; i < lineSplit.length; i++) {
			if (lineSplit[i].contains("Geschlecht")) {
				boolean isMale = lineSplit[i].contains("Hengst");
				radioButtonIsMale.setSelected(isMale);
				radioButtonIsFemale.setSelected(!isMale);
			}
		}
	}

	public int findNext(String[] words, int start) {
		while (start < words.length) {
			if (words[start].contains("Unbekannt"))
				return start;
			for (int i = 0; i < races.length; i++) {
				if (words[start].contains(races[i]))
					return start;
			}
		}
		return -1;
	}

	public void buttonGoToRC_ActionPerformed(ActionEvent evt) {
		new RelativeCheckGUI();
		this.dispose();
	}

	public void ButtonExit_ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	public void ButtonGoToMenu_ActionPerformed(ActionEvent evt) {
		new MainMenu();
		this.dispose();
	}
	
	public void resetText_ActionPerformed(ActionEvent evt) {
		TextInput.setText("");
		TextInput.requestFocus();
	}

	public void radioButtonIsMale_ActionPerformed(ActionEvent evt) {
		radioButtonIsMale.setSelected(true);
		radioButtonIsFemale.setSelected(false);
	}

	public void radioButtonIsFemale_ActionPerformed(ActionEvent evt) {
		radioButtonIsMale.setSelected(false);
		radioButtonIsFemale.setSelected(true);
	}
	
	protected abstract void addOrSaveHorse();
	protected abstract void resetOrDelete();
	// Ende Methoden

	public static void main(String[] args) {
		new ManageHorseGUI();
	} // end of main

} // end of class RCGUIdatabase
