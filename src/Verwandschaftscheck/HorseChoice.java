package Verwandschaftscheck;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 15.02.2015
 * @author
 */

@SuppressWarnings("serial")
public class HorseChoice extends JFrame {
	String[] racesUpdate;
	DocumentManager dm;
	// Anfang Attribute
	private JButton accept = new JButton();
	private JList<String> horseListfavs = new JList<String>();
	private DefaultListModel<String> horseListfavsModel = new DefaultListModel<String>();
	private JScrollPane horseListfavsScrollPane = new JScrollPane(horseListfavs);
	private JLabel jLabel1 = new JLabel();
	private JList<String> horseList = new JList<String>();
	private DefaultListModel<String> horseListModel = new DefaultListModel<String>();
	private JScrollPane horseListScrollPane = new JScrollPane(horseList);
	private JComboBox raceFilter = new JComboBox();
	private JLabel jLabel2 = new JLabel();

	boolean getFavs;
	RelativeCheckGUI rcgui;
	boolean workLeft;
	private JLabel jLabel3 = new JLabel();
	private JTextField search = new JTextField();
	private JLabel jLabel4 = new JLabel();
	private JCheckBox checkBoxMale = new JCheckBox();
	private JCheckBox checkBoxFemale = new JCheckBox();
	private JLabel jLabel5 = new JLabel();
	private JLabel jLabel6 = new JLabel();

	// Ende Attribute

	public HorseChoice(DocumentManager doMa) {
		super("MDR-Hilfen");
		initiate(doMa);
	}

	public HorseChoice(DocumentManager doMa, RelativeCheckGUI rc, boolean workL) {
		super("MDR-Hilfen");
		rcgui = rc;
		workLeft = workL;
		initiate(doMa);
	}

	public void initiate(DocumentManager doMa) {
		dm = doMa;
		try {
			dm.analyzeFile();
		} catch (IllegalFileException e) {
			e.popUp(this);
		}
		getFavs = true;

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 471;
		int frameHeight = 431;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		// Anfang Komponenten

		accept.setBounds(24, 360, 193, 25);
		accept.setText("OK");
		accept.setMargin(new Insets(2, 2, 2, 2));
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				accept_ActionPerformed(evt);
			}
		});
		cp.add(accept);
		horseListfavs.setModel(horseListfavsModel);
		horseListfavsScrollPane.setBounds(24, 72, 193, 281);

		horseListfavs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		horseListfavs.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				horseListfavs_ValueChanged(evt);
			}
		});
		cp.add(horseListfavsScrollPane);
		jLabel1.setBounds(24, 48, 107, 19);
		jLabel1.setText("Favoriten");
		cp.add(jLabel1);
		horseList.setModel(horseListModel);
		horseListScrollPane.setBounds(240, 168, 193, 217);
		horseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		horseList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				horseList_ValueChanged(evt);
			}
		});
		cp.add(horseListScrollPane);
		raceFilter.setBounds(240, 64, 193, 25);
		racesUpdate = new String[dm.races.length + 2];
		racesUpdate[0] = " Alle";
		racesUpdate[1] = dm.races[0];
		racesUpdate[2] = "----------";
		for (int i = 3; i < racesUpdate.length; i++) {
			racesUpdate[i] = dm.races[i - 2];
		}
		raceFilter.setModel(new DefaultComboBoxModel(racesUpdate));
		raceFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				raceFilter_ItemStateChanged(evt);
			}
		});
		cp.add(raceFilter);
		jLabel2.setBounds(240, 48, 91, 19);
		jLabel2.setText("Rassefilter:");
		cp.add(jLabel2);
		jLabel3.setBounds(152, 8, 163, 28);
		jLabel3.setText("Pferd ausw�hlen");
		jLabel3.setFont(new Font("Dialog", Font.BOLD, 20));
		cp.add(jLabel3);
		search.setBounds(240, 136, 193, 25);
		search.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				search_KeyReleased(evt);
			}
		});
		cp.add(search);
		jLabel4.setBounds(240, 120, 51, 17);
		jLabel4.setText("Suchen:");
		cp.add(jLabel4);
		checkBoxMale.setBounds(240, 96, 17, 17);
		checkBoxMale.setText("");
		checkBoxMale.setOpaque(false);
		checkBoxMale.setSelected(true);
		checkBoxMale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				checkBoxMale_ItemStateChanged(evt);
			}
		});
		cp.add(checkBoxMale);
		checkBoxFemale.setBounds(370, 96, 17, 17);
		checkBoxFemale.setText("");
		checkBoxFemale.setOpaque(false);
		checkBoxFemale.setSelected(true);
		checkBoxFemale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				checkBoxFemale_ItemStateChanged(evt);
			}
		});
		cp.add(checkBoxFemale);
		jLabel5.setBounds(264, 94, 59, 19);
		jLabel5.setText("Hengste");
		cp.add(jLabel5);
		jLabel6.setBounds(392, 94, 43, 19);
		jLabel6.setText("Stuten");
		cp.add(jLabel6);
		// Ende Komponenten

		filterVisible("", "", true, true);
		setVisible(true);
	} // end of public HorseChoice

	// Anfang Methoden
	public void filterVisible(String race, String searched, boolean showMale, boolean showFemale) {
		Vector<RelativeHorse> horses = new Vector<RelativeHorse>();
		for (int i = 0; i < dm.population.size(); i++) {
			horses.add(dm.population.get(i));
		}
		searched = searched.toLowerCase();
		for (int i = 0; i < horses.size(); i++) {
			RelativeHorse current = horses.get(i);
			boolean delete = current.name.equals("Unbekannt") || !current.race.contains(race) || !current.name.toLowerCase().contains(searched);
			delete = delete || (current.isMale() && !showMale) || (!current.isMale() && !showFemale);
			if (delete) {
				horses.remove(i);
				i--;
			}
		}

		Vector<String> horseNamesAll = new Vector<String>();
		for (int i = 0; i < horses.size(); i++) {
			horseNamesAll.add(horses.get(i).name);
		}
		horseNamesAll = (new RadixSort()).sort(horseNamesAll);
		horseListModel.removeAllElements();
		for (int i = 0; i < horseNamesAll.size(); i++) {
			horseListModel.addElement(horseNamesAll.get(i));
		}

		Vector<String> horseNamesFavs = new Vector<String>();
		for (int i = 0; i < horseNamesAll.size(); i++) {
			RelativeHorse current = horses.get(i);
			if (!current.name.equals("Unbekannt") && current.isFavourite) {
				horseNamesFavs.add(current.name);
			}
		}
		horseNamesFavs = (new RadixSort()).sort(horseNamesFavs);
		horseListfavsModel.removeAllElements();
		for (int i = 0; i < horseNamesFavs.size(); i++) {
			horseListfavsModel.addElement(horseNamesFavs.get(i));
		}
	}

	public void accept_ActionPerformed(ActionEvent evt) {
		String name;
		if (getFavs)
			name = (String) horseListfavs.getSelectedValue();
		else {
			name = (String) horseList.getSelectedValue();
		}
		if (rcgui == null)
			new RCGUIdatabase(name);
		else if (workLeft) {
			new NameLoader(name, dm, rcgui.horseNamesL, rcgui.horseRacesL, null, rcgui.getRadioButtonIsMaleLeft(), rcgui.getRadioButtonIsFemaleLeft());
		} else {
			new NameLoader(name, dm, rcgui.horseNamesR, rcgui.horseRacesR, null, rcgui.getRadioButtonIsMaleRight(), rcgui.getRadioButtonIsFemaleRight());
		}
		this.dispose();
	}

	public void horseList_ValueChanged(ListSelectionEvent evt) {
		getFavs = false;
		horseListfavs.clearSelection();
	}

	public void horseListfavs_ValueChanged(ListSelectionEvent evt) {
		getFavs = true;
		horseList.clearSelection();
	}

	public void raceFilter_ItemStateChanged(ItemEvent evt) {
		getFilterSettings();
	}

	public void getFilterSettings() {
		String selectedRace = raceFilter.getSelectedItem().toString();
		if (selectedRace.equals("----------") || selectedRace.equals(" Alle")) {
			selectedRace = "";
		}
		filterVisible(selectedRace, search.getText(), checkBoxMale.isSelected(), checkBoxFemale.isSelected());
	}

	public void search_KeyReleased(KeyEvent evt) {
		getFilterSettings();
	}

	public void checkBoxMale_ItemStateChanged(ItemEvent evt) {
		getFilterSettings();
	}

	public void checkBoxFemale_ItemStateChanged(ItemEvent evt) {
		getFilterSettings();
	}

	// Ende Methoden

	public static void main(String[] args) {
		DocumentManager dm = new DocumentManager();
		try {
			dm.analyzeFile();
		} catch (Exception e) {
		}
		new HorseChoice(dm);
	} // end of main

} // end of class HorseChoice