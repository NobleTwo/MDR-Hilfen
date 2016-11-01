package relativeCheck;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Collator;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;

import general.MDRFrame;
import general.ShortTimeMemory;

public class HorseChoice extends MDRFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Anfang Attribute
	private JList<String> horseListfavs = new JList<String>();
	private DefaultListModel<String> horseListfavsModel = new DefaultListModel<String>();
	private JScrollPane horseListfavsScrollPane = new JScrollPane(horseListfavs);
	
	private JList<String> listAllHorses = new JList<String>();
	private DefaultListModel<String> listModelAllHorses = new DefaultListModel<String>();
	
	private JComboBox<String> raceFilter = new JComboBox<String>();
	private JTextField textfieldSearchKey = new JTextField();

	boolean getFavs = true;
	RelativeCheckGUI rcgui;
	boolean workLeft;
	private JTextField search = new JTextField();
	private JCheckBox checkBoxMale = new JCheckBox("Hengste");
	private JCheckBox checkBoxFemale = new JCheckBox("Stuten");

	private boolean showMale = ShortTimeMemory.isSelectedMale();
	private boolean showFemale = ShortTimeMemory.isSelectedFemale();
	// Ende Attribute

	public HorseChoice() {
		super("Pferd auswählen");
		initiate();
	}

	public HorseChoice(RelativeCheckGUI rc, boolean workL) {
		super("Pferd auswählen");
		rcgui = rc;
		workLeft = workL;
		initiate();
	}

	public void initiate() {
		JButton buttonAccept = new JButton("OK");
		buttonAccept.setBounds(24, 360, 193, 25);
		buttonAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				choose();
			}
		});
		cp.add(buttonAccept);
		
		horseListfavs.setModel(horseListfavsModel);
		horseListfavsScrollPane.setBounds(24, 72, 193, 281);

		/*horseListfavs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		horseListfavs.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	    		getFavs = true;
	    		horseList.clearSelection();
	    		horseListfavs.setSelectedIndex(horseListfavs.locationToIndex(evt.getPoint()));
		        if (evt.getClickCount() == 2) {
		    		choose();
		        }
		    }
		});
		cp.add(horseListfavsScrollPane);
		jLabel1.setBounds(24, 48, 107, 19);
		jLabel1.setText("Favoriten");
		cp.add(jLabel1);*/
		
		listAllHorses.setModel(listModelAllHorses);
		JScrollPane scrollPaneListAllHorses = new JScrollPane(listAllHorses);
		scrollPaneListAllHorses.setBounds(240, 168, 193, 217);
		listAllHorses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAllHorses.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
	    		getFavs = false;
	    		horseListfavs.clearSelection();
	    		listAllHorses.setSelectedIndex(listAllHorses.locationToIndex(evt.getPoint()));
		        if (evt.getClickCount() == 2) {
		    		choose();
		        }
		    }
		});
		/*horseList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				horseList_ValueChanged(evt);
			}
		});*/
		cp.add(scrollPaneListAllHorses);
		
		JLabel labelRaceFilter = new JLabel("Rassefilter:");
		labelRaceFilter.setBounds(240, 48, 91, 19);
		cp.add(labelRaceFilter);
		raceFilter.setBounds(240, 64, 193, 25);
		String[] listRaces = new String[RelativeHorse.getRaces().length + 2];
		listRaces[0] = " Alle";
		listRaces[1] = RelativeHorse.getRaces()[0];
		listRaces[2] = "----------";
		for (int i = 3; i < listRaces.length; i++) {
			listRaces[i] = RelativeHorse.getRaces()[i - 2];
		}
		raceFilter.setModel(new DefaultComboBoxModel<String>(listRaces));
		raceFilter.setSelectedItem(ShortTimeMemory.getSelectedRace());
		raceFilter.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				getFilterSettings();
			}
		});
		cp.add(raceFilter);
		
		JLabel labelSearchKey = new JLabel("Suchen:");
		labelSearchKey.setBounds(240, 120, 51, 17);
		cp.add(labelSearchKey);
		textfieldSearchKey.setBounds(240, 136, 193, 25);
		textfieldSearchKey.setText(ShortTimeMemory.getSearchedString());
		textfieldSearchKey.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				getFilterSettings();
			}
		});
		cp.add(textfieldSearchKey);
		
		checkBoxMale.setBounds(240, 96, 100, 20);
		checkBoxMale.setOpaque(false);
		checkBoxMale.setSelected(showMale);
		checkBoxMale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				checkBoxMale_ItemStateChanged(evt);
			}
		});
		cp.add(checkBoxMale);
		checkBoxFemale.setBounds(370, 96, 100, 17);
		checkBoxFemale.setOpaque(false);
		checkBoxFemale.setSelected(showFemale);
		checkBoxFemale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				checkBoxFemale_ItemStateChanged(evt);
			}
		});
		cp.add(checkBoxFemale);
		// Ende Komponenten

		getFilterSettings();
		
		int frameWidth = 471;
		int frameHeight = 431;
		setSize(frameWidth, frameHeight);
		super2();
	} // end of public HorseChoice

	// Anfang Methoden
	public void filterVisible(String race, String searched, boolean showMale, boolean showFemale) {
		Vector<RelativeHorse> horses = new Vector<RelativeHorse>();
		for (int i = 0; i < DatabaseManager.getPopulation().size(); i++) {
			horses.add(DatabaseManager.getPopulation().get(i));
		}
		searched = searched.toLowerCase();
		for (int i = 0; i < horses.size(); i++) {
			RelativeHorse current = horses.get(i);
			boolean delete = current.getName().equals("Unbekannt") || !current.getRace().contains(race) || !current.getName().toLowerCase().contains(searched);
			delete = delete || (current.isMale() && !showMale) || (!current.isMale() && !showFemale);
			if (delete) {
				horses.remove(i);
				i--;
			}
		}
		
		//for all horses
		Collection<String> horseNamesAll = new TreeSet<String>(Collator.getInstance());
		for (int i = 0; i < horses.size(); i++) {
			horseNamesAll.add(horses.get(i).getName());
		}
		listModelAllHorses.removeAllElements();
		Iterator<String> iterator = horseNamesAll.iterator();
		while(iterator.hasNext()) {
			listModelAllHorses.addElement(iterator.next());
		}

		//for favourites
		Collection<String> horseNamesFavs = new TreeSet<String>(Collator.getInstance());
		for (int i = 0; i < horseNamesAll.size(); i++) {
			RelativeHorse current = horses.get(i);
			if (!current.getName().equals("Unbekannt") && !current.getFavourites().isEmpty()) {
				horseNamesFavs.add(current.getName());
			}
		}
		horseListfavsModel.removeAllElements();
		iterator = horseNamesFavs.iterator();
		for (int i = 0; i < horseNamesFavs.size(); i++) {
			horseListfavsModel.addElement(iterator.next());
		}
	}

	public void choose(){
		//save options
		ShortTimeMemory.setSelectedRace(raceFilter.getSelectedItem().toString());
		ShortTimeMemory.setSelectedMale(checkBoxMale.isSelected());
		ShortTimeMemory.setSelectedFemale(checkBoxFemale.isSelected());
		ShortTimeMemory.setSearchedString(search.getText());
		
		//insert into other frame
		String name;
		if (getFavs)
			name = (String) horseListfavs.getSelectedValue();
		else {
			name = (String) listAllHorses.getSelectedValue();
		}
		if (rcgui == null)
			new EditHorseGUI(name);
		else if (workLeft) {
			new NameLoader(name, new DocumentManager(), rcgui.horseNamesL, rcgui.horseRacesL, null, rcgui.getRadioButtonIsMaleLeft(), rcgui.getRadioButtonIsFemaleLeft());
		} else {
			new NameLoader(name, new DocumentManager(), rcgui.horseNamesR, rcgui.horseRacesR, null, rcgui.getRadioButtonIsMaleRight(), rcgui.getRadioButtonIsFemaleRight());
		}
		this.dispose();
	}

	public void horseList_ValueChanged(ListSelectionEvent evt) {
		getFavs = false;
		horseListfavs.clearSelection();
	}

	public void horseListfavs_ValueChanged(ListSelectionEvent evt) {
		getFavs = true;
		listAllHorses.clearSelection();
	}

	public void getFilterSettings() {
		String selectedRace = raceFilter.getSelectedItem().toString();
		if (selectedRace.equals("----------") || selectedRace.equals(" Alle")) {
			selectedRace = "";
		}
		filterVisible(selectedRace, search.getText(), showMale, showFemale);
	}

	public void checkBoxMale_ItemStateChanged(ItemEvent evt) {
		showMale = checkBoxMale.isSelected();
		getFilterSettings();
	}

	public void checkBoxFemale_ItemStateChanged(ItemEvent evt) {
		showFemale = checkBoxFemale.isSelected();
		getFilterSettings();
	}

	// Ende Methoden

	public static void main(String[] args) {
		new HorseChoice();
	} // end of main

} // end of class HorseChoice
