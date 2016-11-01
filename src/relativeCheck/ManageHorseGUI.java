package relativeCheck;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import general.MDRFrame;
import general.MDRNumberField;
import general.MainMenu;

public abstract class ManageHorseGUI extends MDRFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected HorseAndRaceField[] horseNamesAndRaces = new HorseAndRaceField[15];
	protected JButton buttonAddOrSaveHorse = new JButton();
	protected JButton buttonChooseHorse = new JButton();
	protected JButton buttonResetOrDeleteHorse = new JButton();
	protected JTextArea textInput = new JTextArea(); 
	private JRadioButton radioButtonIsMale = new JRadioButton("Hengst");
	private JRadioButton radioButtonIsFemale = new JRadioButton("Stute");
	private MDRNumberField numberfieldGP = new MDRNumberField(false);
	private Vector<JCheckBox> checkboxFavourites = new Vector<JCheckBox>();

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

	public ManageHorseGUI(String title) {
		super(title);
		int eastButtonWidth = 210;
		int frameWidth =(int)(7.5*gridButtonGap+4*HorseAndRaceField.WIDTH+2*eastButtonWidth);
		int frameHeight = 11*gridButtonGap+9*HorseAndRaceField.HEIGHT+buttonHeight+20;
		setSize(frameWidth, frameHeight);
		
		Vector<String> vectorFavourites = new Vector<String>();
		vectorFavourites.add("Favoriten1");
		vectorFavourites.add("Favoriten2");
		vectorFavourites.add("Favoriten2");
		vectorFavourites.add("Favoriten2");
		vectorFavourites.add("Favoriten2");
		vectorFavourites.add("Favoriten6");
		//max = 6
		
		//Komponenten
		Container cp = getContentPane();
		for(int i = 0; i<horseNamesAndRaces.length; i++){
			horseNamesAndRaces[i] = new HorseAndRaceField();
			cp.add(horseNamesAndRaces[i]);	
		}
		int yTop = 3*gridButtonGap + 20;
		
		//
		//WEST
		//
		int y0 = (int)(yTop + 3.5*HorseAndRaceField.HEIGHT + 3*gridButtonGap);
		horseNamesAndRaces[0].setLocation(gridButtonGap, y0);
		
		int x2row = 2*gridButtonGap + horseNamesAndRaces[0].getWidth();
		int y1 = (int)(yTop + HorseAndRaceField.HEIGHT*1.5 + gridButtonGap);
		horseNamesAndRaces[1].setLocation(x2row, y1);
		int y2 = (int)(yTop + HorseAndRaceField.HEIGHT*5.5 + 5*gridButtonGap);
		horseNamesAndRaces[2].setLocation(x2row, y2);
		
		int x3row = 3*gridButtonGap + 2*horseNamesAndRaces[0].getWidth();
		for(int i = 3; i<=6; i++){
			int y = (int)(yTop + HorseAndRaceField.HEIGHT/2 +(2*gridButtonGap + 2*HorseAndRaceField.HEIGHT)*(i-3));
			horseNamesAndRaces[i].setLocation(x3row, y);	
		}
		
		int x4row = 4*gridButtonGap + 3*horseNamesAndRaces[0].getWidth();
		for(int i = 7; i<=14; i++){
			horseNamesAndRaces[i].setLocation(x4row, yTop + (gridButtonGap + HorseAndRaceField.HEIGHT)*(i-7));
		}

		Container containerGP = new Container();
		containerGP.setLayout(new BorderLayout());
		int y0GP = y0 + HorseAndRaceField.HEIGHT + gridButtonGap/2;
		containerGP.setBounds(gridButtonGap, y0GP, HorseAndRaceField.WIDTH, 2*gridButtonGap);
		JLabel labelGP = new JLabel("GP: ");
		containerGP.add(labelGP, BorderLayout.WEST);
		containerGP.add(numberfieldGP, BorderLayout.CENTER);
		cp.add(containerGP);
		
		radioButtonIsMale.setBounds(gridButtonGap, (int)(y0GP+2.5*gridButtonGap), HorseAndRaceField.WIDTH, 2*gridButtonGap);
		radioButtonIsMale.setOpaque(false);
		radioButtonIsMale.setSelected(true);
		cp.add(radioButtonIsMale);
		radioButtonIsFemale.setBounds(gridButtonGap, (int)(y0GP+4*gridButtonGap), HorseAndRaceField.WIDTH, 2*gridButtonGap);
		radioButtonIsFemale.setOpaque(false);
		cp.add(radioButtonIsFemale);
		ButtonGroup buttonGroupRadioButtons = new ButtonGroup();
		buttonGroupRadioButtons.add(radioButtonIsMale);
		buttonGroupRadioButtons.add(radioButtonIsFemale);
		
		int y0Favs = y0GP + 6*gridButtonGap;
		for(int i = 0; i<vectorFavourites.size(); i++){
			String name = vectorFavourites.get(i);
			JCheckBox temp = new JCheckBox(name);
			temp.setBounds(gridButtonGap, y0Favs+2*i*gridButtonGap, HorseAndRaceField.WIDTH, 2*gridButtonGap);
			temp.setOpaque(false);
			checkboxFavourites.add(temp);
			cp.add(temp);
		}
		
		int yBot = yTop + (gridButtonGap + HorseAndRaceField.HEIGHT)*8;
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
		//EAST
		//		
		int xEast = x4row + HorseAndRaceField.WIDTH + gridButtonGap;
		buttonChooseHorse.setBounds(xEast, yTop, eastButtonWidth, buttonHeight);
		buttonChooseHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new HorseChoice();
				dispose();
			}
		});
		cp.add(buttonChooseHorse);
		
		int xEastButton2 = xEast + gridButtonGap + eastButtonWidth;
		JButton buttonGoToRC = new JButton("Verwandtschaftscheck");
		buttonGoToRC.setBounds(xEastButton2, yTop, eastButtonWidth, buttonHeight);
		buttonGoToRC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new RelativeCheckGUI();
			}
		});
		cp.add(buttonGoToRC);
		
		int yButton2 = yTop + gridButtonGap + buttonHeight;
		JButton buttonGoToMenu = new JButton("Hauptmenü");
		buttonGoToMenu.setBounds(xEast, yButton2, eastButtonWidth, buttonHeight);
		buttonGoToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new MainMenu();
				dispose();
			}
		});
		cp.add(buttonGoToMenu);
		
		JButton buttonExit = new JButton("Schließen");
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
		int textAreaHeight = 8*HorseAndRaceField.HEIGHT+5*gridButtonGap-2*buttonHeight;
		textInputScrollPane.setBounds(xEast, yButton2+buttonHeight+gridButtonGap, 2*eastButtonWidth+gridButtonGap, textAreaHeight);
		cp.add(textInputScrollPane);
		
		int yButton3 = yButton2 + buttonHeight + 2*gridButtonGap + textAreaHeight;
		JButton buttonAnalyzeText = new JButton("Text analysieren");
		buttonAnalyzeText.setBounds(xEast, yButton3, eastButtonWidth, buttonHeight);
		buttonAnalyzeText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				analyzeText();
			}
		});
		cp.add(buttonAnalyzeText);
		
		JButton buttonResetText = new JButton("Text löschen");
		buttonResetText.setBounds(xEast + gridButtonGap + eastButtonWidth, yButton3, eastButtonWidth, buttonHeight);
		buttonResetText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				textInput.setText("");
				textInput.requestFocus();
			}
		});
		cp.add(buttonResetText);
	}

	//
	//Methoden
	//

	public void analyzeText(){
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
			}
		}
		
		for(int i = 0; i<lineSplit.length; i++){
			if(lineSplit[i].contains("Gesamtpotential: ")){
				String[] wordSplit = lineSplit[i].split(" ");
				numberfieldGP.setText(wordSplit[1]);
			}
		}
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
	
	protected abstract void addOrSaveHorse();
	protected abstract void resetOrDelete();

}
