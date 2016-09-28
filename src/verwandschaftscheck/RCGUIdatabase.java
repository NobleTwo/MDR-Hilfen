package verwandschaftscheck;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import allgemein.Menue;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 15.02.2015
 * @author
 */

public class RCGUIdatabase extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DocumentManager dm;
	boolean newHorseMode;
	// Anfang Attribute
	private JTextField jTextField7 = new JTextField();
	private JTextField jTextField9 = new JTextField();
	private JTextField jTextField10 = new JTextField();
	private JTextField jTextField11 = new JTextField();
	private JTextField jTextField12 = new JTextField();
	private JTextField jTextField13 = new JTextField();
	private JTextField jTextField14 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JTextField jTextField6 = new JTextField();
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField0 = new JTextField();
	private JButton addHorse = new JButton();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField8 = new JTextField();
	private JCheckBox isFavourite = new JCheckBox();
	private JLabel jLabel1 = new JLabel();
	private JButton changeMode = new JButton();
	private JButton reset = new JButton();
	private JButton save = new JButton();
	private JButton createNewHorse = new JButton();
	private JButton ButtonAnalyzeText = new JButton();
	private JTextArea TextInput = new JTextArea("");
	private JScrollPane TextInputScrollPane = new JScrollPane(TextInput);
	private JComboBox<String> jComboBox0 = new JComboBox<String>();
	private JComboBox<String> jComboBox1 = new JComboBox<String>();
	private JComboBox<String> jComboBox2 = new JComboBox<String>();
	private JComboBox<String> jComboBox3 = new JComboBox<String>();
	private JComboBox<String> jComboBox4 = new JComboBox<String>();
	private JComboBox<String> jComboBox5 = new JComboBox<String>();
	private JComboBox<String> jComboBox6 = new JComboBox<String>();
	private JComboBox<String> jComboBox7 = new JComboBox<String>();
	private JComboBox<String> jComboBox8 = new JComboBox<String>();
	private JComboBox<String> jComboBox9 = new JComboBox<String>();
	private JComboBox<String> jComboBox10 = new JComboBox<String>();
	private JComboBox<String> jComboBox11 = new JComboBox<String>();
	private JComboBox<String> jComboBox12 = new JComboBox<String>();
	private JComboBox<String> jComboBox13 = new JComboBox<String>();
	private JComboBox<String> jComboBox14 = new JComboBox<String>();
	private JButton buttonGoToRC = new JButton();
	private JButton ButtonExit = new JButton();
	private JButton ButtonGoToMenu = new JButton();
	private JButton resetText = new JButton();
	private JLabel jLabel2 = new JLabel();
	private JRadioButton radioButtonIsMale = new JRadioButton();
	private JRadioButton radioButtonIsFemale = new JRadioButton();
	private JLabel jLabel3 = new JLabel();
	private JLabel jLabel4 = new JLabel();
	// Ende Attribute
	private JTextField[] horseNames;
	private JComboBox<String>[] horseRaces;
	String[] races;
	private RelativeHorse subject;

	public RCGUIdatabase() {
		super("MDR-Hilfen");
		start(true);
	}

	public RCGUIdatabase(String name) {
		super();
		start(false);
		new NameLoader(name, dm, horseNames, horseRaces, isFavourite, radioButtonIsMale, radioButtonIsFemale);
		int subjectPosition = dm.find(horseNames[0].getText());
		if(subjectPosition >=0){
			subject = dm.population.get(subjectPosition);
		} else{
			subject = null;
		}
	}

	@SuppressWarnings("unchecked")
	public void start(boolean mode) {

		// Frame-Initialisierung

		dm = new DocumentManager();
		newHorseMode = mode;
		try {
			dm.analyzeFile();
		} catch (IllegalFileException e) {
			e.popUp(this);
		}

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 913;
		int frameHeight = 575;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		// Anfang Komponenten

		jTextField7.setBounds(400, 48, 121, 33);
		jTextField7.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField7_FocusGained(evt);
			}
		});
		cp.add(jTextField7);
		jTextField9.setBounds(400, 160, 121, 33);
		jTextField9.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField9_FocusGained(evt);
			}
		});
		cp.add(jTextField9);
		jTextField10.setBounds(400, 216, 121, 33);
		jTextField10.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField10_FocusGained(evt);
			}
		});
		cp.add(jTextField10);
		jTextField11.setBounds(400, 272, 121, 33);
		jTextField11.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField11_FocusGained(evt);
			}
		});
		cp.add(jTextField11);
		jTextField12.setBounds(400, 328, 121, 33);
		jTextField12.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField12_FocusGained(evt);
			}
		});
		cp.add(jTextField12);
		jTextField13.setBounds(400, 384, 121, 33);
		jTextField13.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField13_FocusGained(evt);
			}
		});
		cp.add(jTextField13);
		jTextField14.setBounds(400, 440, 121, 33);
		jTextField14.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField14_FocusGained(evt);
			}
		});
		cp.add(jTextField14);
		jTextField3.setBounds(272, 80, 121, 33);
		jTextField3.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField3_FocusGained(evt);
			}
		});
		cp.add(jTextField3);
		jTextField5.setBounds(272, 296, 121, 33);
		jTextField5.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField5_FocusGained(evt);
			}
		});
		cp.add(jTextField5);
		jTextField6.setBounds(272, 416, 121, 33);
		jTextField6.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField6_FocusGained(evt);
			}
		});
		cp.add(jTextField6);
		jTextField1.setBounds(144, 136, 121, 33);
		jTextField1.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField1_FocusGained(evt);
			}
		});
		cp.add(jTextField1);
		jTextField2.setBounds(144, 352, 121, 33);
		jTextField2.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField2_FocusGained(evt);
			}
		});
		cp.add(jTextField2);
		jTextField0.setBounds(16, 248, 121, 33);
		jTextField0.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField0_FocusGained(evt);
			}
		});
		cp.add(jTextField0);
		addHorse.setBounds(272, 504, 123, 25);
		if (newHorseMode)
			addHorse.setText("Pferd hinzufügen");
		else {
			addHorse.setText("Pferd speichern");
		}
		addHorse.setMargin(new Insets(2, 2, 2, 2));
		addHorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addHorse_ActionPerformed(evt);
			}
		});
		cp.add(addHorse);
		jTextField4.setBounds(272, 192, 121, 33);
		jTextField4.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField4_FocusGained(evt);
			}
		});
		cp.add(jTextField4);
		jTextField8.setBounds(400, 104, 121, 33);
		jTextField8.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				jTextField8_FocusGained(evt);
			}
		});
		cp.add(jTextField8);
		isFavourite.setBounds(16, 304, 17, 17);
		isFavourite.setText("");
		isFavourite.setOpaque(false);
		cp.add(isFavourite);
		jLabel1.setBounds(40, 304, 103, 19);
		jLabel1.setText("als Favorit setzen");
		cp.add(jLabel1);

		races = dm.races;
		changeMode.setBounds(544, 48, 185, 25);
		if (newHorseMode)
			changeMode.setText("Bestehendes Pferd bearbeiten");
		else {
			changeMode.setText("Anderes Pferd bearbeiten");
		}
		changeMode.setMargin(new Insets(2, 2, 2, 2));
		changeMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				changeMode_ActionPerformed(evt);
			}
		});
		cp.add(changeMode);
		reset.setBounds(400, 504, 121, 25);
		if (newHorseMode)
			reset.setText("Zurücksetzen");
		else {
			reset.setText("Pferd löschen");
		}
		reset.setMargin(new Insets(2, 2, 2, 2));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				reset_ActionPerformed(evt);
			}
		});
		cp.add(reset);
		save.setBounds(544, 112, 185, 25);
		save.setText("Datenbank speichern");
		save.setMargin(new Insets(2, 2, 2, 2));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				save_ActionPerformed(evt);
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
			horses[i] = new RelativeHorse(currentName, father, mother, currentRace, false, i % 2 == 1);
			dm.addHorse(horses[i]);
		}

		name = horseNames[0].getText();
		if (!(name.equals("") || name.equals("nicht in DB"))) {
			father = horses[1];
			mother = horses[2];
			boolean isFav = isFavourite.isSelected();
			String race = String.valueOf(horseRaces[0].getSelectedItem());

			dm.addHorse(new RelativeHorse(name, father, mother, race, isFav, radioButtonIsMale.isSelected()), true);
			if(!subject.name.equals(name)){
				dm.removeHorse(subject.name);
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

	public void save_ActionPerformed(ActionEvent evt) {
		dm.save();
		try {
			dm.analyzeFile();
		} catch (IllegalFileException e) {
			e.popUp(this);
		}
	}

	public void createNewHorse_ActionPerformed(ActionEvent evt) {
		new RCGUIdatabase();
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
		new Menue();
		this.dispose();
	}

	public void jTextField0_FocusGained(FocusEvent evt) {
		if ((jTextField0.getText()).equals("nicht in DB"))
			jTextField0.setText("");
	}

	public void jTextField1_FocusGained(FocusEvent evt) {
		if ((jTextField1.getText()).equals("nicht in DB"))
			jTextField1.setText("");
	}

	public void jTextField2_FocusGained(FocusEvent evt) {
		if ((jTextField2.getText()).equals("nicht in DB"))
			jTextField2.setText("");
	}

	public void jTextField3_FocusGained(FocusEvent evt) {
		if ((jTextField3.getText()).equals("nicht in DB"))
			jTextField3.setText("");
	}

	public void jTextField4_FocusGained(FocusEvent evt) {
		if ((jTextField4.getText()).equals("nicht in DB"))
			jTextField4.setText("");
	}

	public void jTextField5_FocusGained(FocusEvent evt) {
		if ((jTextField5.getText()).equals("nicht in DB"))
			jTextField5.setText("");
	}

	public void jTextField6_FocusGained(FocusEvent evt) {
		if ((jTextField6.getText()).equals("nicht in DB"))
			jTextField6.setText("");
	}

	public void jTextField7_FocusGained(FocusEvent evt) {
		if ((jTextField7.getText()).equals("nicht in DB"))
			jTextField7.setText("");
	}

	public void jTextField8_FocusGained(FocusEvent evt) {
		if ((jTextField8.getText()).equals("nicht in DB"))
			jTextField8.setText("");
	}

	public void jTextField9_FocusGained(FocusEvent evt) {
		if ((jTextField9.getText()).equals("nicht in DB"))
			jTextField9.setText("");
	}

	public void jTextField10_FocusGained(FocusEvent evt) {
		if ((jTextField10.getText()).equals("nicht in DB"))
			jTextField10.setText("");
	}

	public void jTextField11_FocusGained(FocusEvent evt) {
		if ((jTextField11.getText()).equals("nicht in DB"))
			jTextField11.setText("");
	}

	public void jTextField12_FocusGained(FocusEvent evt) {
		if ((jTextField12.getText()).equals("nicht in DB"))
			jTextField12.setText("");
	}

	public void jTextField13_FocusGained(FocusEvent evt) {
		if ((jTextField13.getText()).equals("nicht in DB"))
			jTextField13.setText("");
	}

	public void jTextField14_FocusGained(FocusEvent evt) {
		if ((jTextField14.getText()).equals("nicht in DB"))
			jTextField14.setText("");
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

	// Ende Methoden

	public static void main(String[] args) {
		new RCGUIdatabase();
	} // end of main

} // end of class RCGUIdatabase
