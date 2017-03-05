package relativeCheck;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import general.MDRButton;
import general.MDRFrame;
import general.MDRNumberField;
import general.MainMenu;

public abstract class ManageHorseGUI extends MDRFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected HorseAndRaceField[] horseNamesAndRaces = new HorseAndRaceField[15];
	protected MDRButton buttonAddOrSaveHorse = new MDRButton();
	protected MDRButton buttonChooseHorse = new MDRButton();
	protected MDRButton buttonResetOrDeleteHorse = new MDRButton();
	protected JTextArea textInput = new JTextArea();
	protected JRadioButton radioButtonIsMale = new JRadioButton("Hengst");
	protected JRadioButton radioButtonIsFemale = new JRadioButton("Stute");
	protected GPField[] fieldGP = new GPField[7];
	protected Vector<JCheckBox> checkboxFavourites = new Vector<JCheckBox>();
	private RelativeHorse subject;

	public ManageHorseGUI(String title) {
		super(title);
		int eastButtonWidth = 210;
		int frameWidth = (int) (7.5 * gridButtonGap + 4 * HorseAndRaceField.WIDTH + 2 * eastButtonWidth);
		int frameHeight = 11 * gridButtonGap + 9 * HorseAndRaceField.HEIGHT + buttonHeight + 20; // if too many favs changed in favs-section

		final Vector<String> vectorFavourites = DatabaseManager.getFavourites();

		// Komponenten
		for (int i = 0; i < horseNamesAndRaces.length; i++) {
			horseNamesAndRaces[i] = new HorseAndRaceField();
			cp.add(horseNamesAndRaces[i]);
		}
		for (int i = 0; i < fieldGP.length; i++) {
			fieldGP[i] = new GPField();
			cp.add(fieldGP[i]);
		}
		int yTop = 3 * gridButtonGap + 20;

		//
		// WEST
		//
		int y0 = (int) (yTop + 3.5 * HorseAndRaceField.HEIGHT + 3 * gridButtonGap);
		horseNamesAndRaces[0].setLocation(gridButtonGap, y0);

		int x2row = 2 * gridButtonGap + horseNamesAndRaces[0].getWidth();
		int y1 = (int) (yTop + HorseAndRaceField.HEIGHT * 1.5 + gridButtonGap);
		horseNamesAndRaces[1].setLocation(x2row, y1);
		fieldGP[1].setLocation(x2row, y1 + HorseAndRaceField.HEIGHT + gridButtonGap / 2);
		int y2 = (int) (yTop + HorseAndRaceField.HEIGHT * 5.5 + 5 * gridButtonGap);
		horseNamesAndRaces[2].setLocation(x2row, y2);
		fieldGP[2].setLocation(x2row, y2 + HorseAndRaceField.HEIGHT + gridButtonGap / 2);

		int x3row = 3 * gridButtonGap + 2 * horseNamesAndRaces[0].getWidth();
		for (int i = 3; i <= 6; i++) {
			int y = (int) (yTop + HorseAndRaceField.HEIGHT / 2 + (2 * gridButtonGap + 2 * HorseAndRaceField.HEIGHT) * (i - 3));
			horseNamesAndRaces[i].setLocation(x3row, y);
			fieldGP[i].setLocation(x3row, y + HorseAndRaceField.HEIGHT + gridButtonGap / 2);
		}

		int x4row = 4 * gridButtonGap + 3 * horseNamesAndRaces[0].getWidth();
		for (int i = 7; i <= 14; i++) {
			horseNamesAndRaces[i].setLocation(x4row, yTop + (gridButtonGap + HorseAndRaceField.HEIGHT) * (i - 7));
		}

		int y0GP = y0 + HorseAndRaceField.HEIGHT + gridButtonGap / 2;
		fieldGP[0].setLocation(gridButtonGap, y0GP);
		MDRNumberField numberField0 = fieldGP[0].getNumberField();
		numberField0.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				numberField0.setText("");
			}

			@Override
			public void focusLost(FocusEvent fe) {
				if (numberField0.getText().equals("")) {
					if (subject != null) {
						numberField0.setText(subject.getCompletePotential());
					} else {
						numberField0.setText("0");
					}
				}
			}
		});

		radioButtonIsMale.setBounds(gridButtonGap, (int) (y0GP + 2.5 * gridButtonGap), HorseAndRaceField.WIDTH, 2 * gridButtonGap);
		radioButtonIsMale.setOpaque(false);
		radioButtonIsMale.setSelected(true);
		radioButtonIsMale.setFocusPainted(false);
		cp.add(radioButtonIsMale);
		radioButtonIsFemale.setBounds(gridButtonGap, (int) (y0GP + 4 * gridButtonGap), HorseAndRaceField.WIDTH, 2 * gridButtonGap);
		radioButtonIsFemale.setOpaque(false);
		radioButtonIsFemale.setFocusPainted(false);
		cp.add(radioButtonIsFemale);
		ButtonGroup buttonGroupRadioButtons = new ButtonGroup();
		buttonGroupRadioButtons.add(radioButtonIsMale);
		buttonGroupRadioButtons.add(radioButtonIsFemale);

		int y0Favs = y0GP + 6 * gridButtonGap;
		for (int i = 0; i < vectorFavourites.size(); i++) {
			String name = vectorFavourites.get(i);
			JCheckBox temp = new JCheckBox(name);
			temp.setBounds(gridButtonGap, y0Favs + 2 * i * gridButtonGap, HorseAndRaceField.WIDTH, 2 * gridButtonGap);
			temp.setOpaque(false);
			temp.setFocusPainted(false);
			checkboxFavourites.add(temp);
			cp.add(temp);
		}
		int frameHeightFavs = y0Favs + 2 * gridButtonGap * (vectorFavourites.size() + 1) + buttonHeight + 20;
		if (frameHeightFavs > frameHeight) {
			frameHeight = frameHeightFavs;
		}

		int yBot = frameHeight - 20 - buttonHeight - 2 * gridButtonGap;// yTop + (gridButtonGap + HorseAndRaceField.HEIGHT)*8;
		buttonAddOrSaveHorse.setBounds(x2row, yBot, HorseAndRaceField.WIDTH, buttonHeight);
		buttonAddOrSaveHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				addOrSaveHorse();
			}
		});
		cp.add(buttonAddOrSaveHorse);

		buttonResetOrDeleteHorse.setBounds(x3row, yBot, HorseAndRaceField.WIDTH, buttonHeight);
		buttonResetOrDeleteHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				resetOrDelete();
			}
		});
		cp.add(buttonResetOrDeleteHorse);

		//
		// EAST
		//
		int xEast = x4row + HorseAndRaceField.WIDTH + gridButtonGap;
		buttonChooseHorse.setBounds(xEast, yTop, eastButtonWidth, buttonHeight);
		ManageHorseGUI temp = this;
		buttonChooseHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new HorseChoiceGUI(temp);
				if (subject != null) {
					new EditHorseGUI(getSubject());
					dispose();
				}
			}
		});
		cp.add(buttonChooseHorse);

		int xEastButton2 = xEast + gridButtonGap + eastButtonWidth;
		MDRButton buttonGoToRC = new MDRButton("Verwandtschaftscheck");
		buttonGoToRC.setBounds(xEastButton2, yTop, eastButtonWidth, buttonHeight);
		buttonGoToRC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new RelativeCheckGUI();
				dispose();
			}
		});
		cp.add(buttonGoToRC);

		int yButton2 = yTop + gridButtonGap + buttonHeight;
		MDRButton buttonGoToMenu = new MDRButton("Hauptmenü");
		buttonGoToMenu.setBounds(xEast, yButton2, eastButtonWidth, buttonHeight);
		buttonGoToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new MainMenu();
				dispose();
			}
		});
		cp.add(buttonGoToMenu);

		MDRButton buttonExit = new MDRButton("Schließen");
		buttonExit.setBounds(xEastButton2, yButton2, eastButtonWidth, buttonHeight);
		buttonExit.setMargin(new Insets(2, 2, 2, 2));
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		cp.add(buttonExit);

		JScrollPane textInputScrollPane = new JScrollPane(textInput);
		int textAreaHeight = 8 * HorseAndRaceField.HEIGHT + 5 * gridButtonGap - 2 * buttonHeight;
		textInputScrollPane.setBounds(xEast, yButton2 + buttonHeight + gridButtonGap, 2 * eastButtonWidth + gridButtonGap, textAreaHeight);
		cp.add(textInputScrollPane);

		int yButton3 = yButton2 + buttonHeight + 2 * gridButtonGap + textAreaHeight;
		MDRButton buttonAnalyzeText = new MDRButton("Text analysieren");
		buttonAnalyzeText.setBounds(xEast, yButton3, eastButtonWidth, buttonHeight);
		buttonAnalyzeText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				analyzeText();
			}
		});
		cp.add(buttonAnalyzeText);

		MDRButton buttonResetText = new MDRButton("Text löschen");
		buttonResetText.setBounds(xEast + gridButtonGap + eastButtonWidth, yButton3, eastButtonWidth, buttonHeight);
		buttonResetText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				textInput.setText("");
				textInput.requestFocus();
			}
		});
		cp.add(buttonResetText);

		if (frameHeightFavs > frameHeight) {
			frameHeight = frameHeightFavs;
		}
		setSize(frameWidth, frameHeight);
		super2();
		textInput.requestFocus();
	}

	//
	// Methods
	//

	public void analyzeText() {
		String[] lineSplit = textInput.getText().split("\n");
		int counter = 0;
		int start = 0;
		for (int i = 0; i < lineSplit.length; i++) {
			if (lineSplit[i].contains("Gesamtpotential") && !lineSplit[i].contains("Potential")) {
				start = i;
				break;
			}
		}

		for (int i = start; i < lineSplit.length && counter < horseNamesAndRaces.length; i++) {
			String current = lineSplit[i];
			if (current.contains("Unbekannt")) {
				horseNamesAndRaces[counter].setName(lineSplit[i]);
				counter++;
			} else {
				if (counter < 7 && current.contains("Gesamtpotential")) {
					horseNamesAndRaces[counter].setName(lineSplit[i - 3]);
					horseNamesAndRaces[counter].setCaretPosition(0);
					String racename = lineSplit[i - 2];
					for (int j = 0; j < horseNamesAndRaces.length; j++) {
						String currentRace = horseNamesAndRaces[j].getRace();
						if (racename.contains(currentRace)) {
							racename = currentRace;
						}
					}
					horseNamesAndRaces[counter].setSelectedItem(racename);
					if (horseNamesAndRaces[counter].equals("Unbekannt")) {
						fieldGP[counter].setGP(-1);
					} else {
						String[] wordSplit = lineSplit[i].split(" ");
						fieldGP[counter].setGP(Integer.valueOf(wordSplit[1].split("\t")[0]));
					}
					counter++;
				} else if (counter >= 7) {
					horseNamesAndRaces[counter].setName(lineSplit[i]);
					horseNamesAndRaces[counter].setCaretPosition(0);
					i++;
					String racename = lineSplit[i];
					for (int j = 0; j < horseNamesAndRaces.length; j++) {
						String currentRace = horseNamesAndRaces[j].getRace();
						if (racename.contains(currentRace)) {
							racename = currentRace;
						}
					}
					horseNamesAndRaces[counter].setSelectedItem(racename);
					counter++;
				}
			}
		}
		if (counter != horseNamesAndRaces.length)
			textInput.setText("Nicht alle Pferdenamen gefunden.");

		for (int i = 0; i < lineSplit.length; i++) {
			if (lineSplit[i].contains("Geschlecht")) {
				boolean isMale = lineSplit[i].contains("Hengst");
				radioButtonIsMale.setSelected(isMale);
				radioButtonIsFemale.setSelected(!isMale);
				break;
			}
		}

		/*
		 * int pos = 0; for (int i = start; (i < lineSplit.length) && (pos<fieldGP.length); i++) { if (lineSplit[i].contains("Gesamtpotential: ")) { String[] wordSplit = lineSplit[i].split(" "); //if(horseNamesAndRaces[i].getName().equals("Unbekannt) fieldGP[pos].setGP(Integer.valueOf(wordSplit[1].split("\t")[0])); pos++; } }
		 */
	}

	public int findNext(String[] words, int start) {
		while (start < words.length) {
			if (words[start].contains("Unbekannt"))
				return start;
			for (int i = 0; i < horseNamesAndRaces.length; i++) {
				if (words[start].contains(horseNamesAndRaces[i].getRace()))
					return start;
			}
		}
		return -1;
	}

	protected void setSubject(RelativeHorse subject) {
		this.subject = subject;
	}

	public void setSubject(String name) {
		RelativeHorse tempSubject = DatabaseManager.findHorse(name);
		if (tempSubject != null) {
			this.subject = tempSubject;
		}
	}

	protected RelativeHorse getSubject() {
		return subject;
	}

	void addOrSaveHorse() {
		if ((horseNamesAndRaces[0].getName()).equals("")) {
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
			horses[i] = new RelativeHorse(currentName, father, mother, currentRace, null, i % 2 == 1, -1);
			if (i < fieldGP.length) {
				horses[i].setCompletePotential(fieldGP[i].getGP());
			}
			horses[i] = DatabaseManager.addHorse(horses[i]);
		}
		name = horseNamesAndRaces[0].getName();
		if (!(name.equals("") || name.equals("nicht in DB"))) {
			father = horses[1];
			mother = horses[2];
			Vector<String> favourites = new Vector<String>();
			Iterator<JCheckBox> it = checkboxFavourites.iterator();
			while (it.hasNext()) {
				JCheckBox currentCheckbox = it.next();
				if (currentCheckbox.isSelected()) {
					favourites.add(currentCheckbox.getText());
				}
			}
			String race = horseNamesAndRaces[0].getRace();
			int GP = fieldGP[0].getGP();
			RelativeHorse newHorse = new RelativeHorse(name, father, mother, race, favourites, radioButtonIsMale.isSelected(), GP);
			DatabaseManager.addHorse(newHorse);

			if (subject != null && !subject.getName().equals(name)) {
				DatabaseManager.removeHorse(subject.getName());
			}
		}
	}

	protected abstract void resetOrDelete();

}
