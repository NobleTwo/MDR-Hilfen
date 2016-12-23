package relativeCheck;

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

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import general.MDRFrame;
import general.ShortTimeMemory;

public class HorseChoiceGUI extends MDRFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 
	//============ Attributes ===============
	//
	@SuppressWarnings("unchecked")
	private JTable[] listsOfHorses = new JTable[DatabaseManager.getFavourites().size()+1];
	private DefaultListModel<String> listModelAllHorses = new DefaultListModel<String>();
	
	private JComboBox<String> raceFilter = new JComboBox<String>();
	private JTextField textfieldSearchKey = new JTextField();
	private JRadioButton[] radioButtonsSelectFavs;
	
	private boolean getFavs = true;
	private RelativeCheckGUI rcgui;
	
	private boolean workLeft;
	private JTextField search = new JTextField();

	//
	// ============== Constructors ===============
	//
	public HorseChoiceGUI() {
		super("Pferd auswählen");
		initiate();
	}

	public HorseChoiceGUI(RelativeCheckGUI rc, boolean workL) {
		super("Pferd auswählen");
		rcgui = rc;
		workLeft = workL;
		initiate();
	}

	public void initiate() {
		final int columnWidth = 200;
		DatabaseManager.load();
		final Vector<RelativeHorse> horseList = DatabaseManager.getPopulation();
		final Vector<String> favList = DatabaseManager.getFavourites();
		
		int frameWidth = 3*(columnWidth+gridButtonGap)+gridButtonGap*3/2;
		int frameHeight = yTop+heightLabel+columnWidth+gridButtonGap*4; //is changed by favList if to small
		
		JLabel labelRaceFilter = new JLabel("Rassefilter:");
		labelRaceFilter.setBounds(gridButtonGap, yTop, columnWidth, heightLabel);
		cp.add(labelRaceFilter);
		raceFilter.setBounds(gridButtonGap, yTop+heightLabel, columnWidth, heightLabel);
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
		
		int ySearch = yTop + 2*heightLabel + gridButtonGap;
		JLabel labelSearchKey = new JLabel("Suchen:");
		labelSearchKey.setBounds(gridButtonGap, ySearch, columnWidth, heightLabel);
		cp.add(labelSearchKey);
		textfieldSearchKey.setBounds(gridButtonGap, ySearch+heightLabel, columnWidth, heightLabel);
		textfieldSearchKey.setText(ShortTimeMemory.getSearchedString());
		textfieldSearchKey.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				getFilterSettings();
			}
		});
		cp.add(textfieldSearchKey);
		
		JLabel labelFavList = new JLabel("Anzeigen:");
		labelFavList.setBounds(gridButtonGap, ySearch+2*heightLabel+gridButtonGap, columnWidth, heightLabel);
		cp.add(labelFavList);
		int yRadioButtons = ySearch+3*heightLabel+gridButtonGap;
		radioButtonsSelectFavs = new JRadioButton[favList.size()+1];
		ButtonGroup buttonGroupSelectFav = new ButtonGroup();
		for(int i=0; i<radioButtonsSelectFavs.length; i++){
			if(i==0){
				radioButtonsSelectFavs[i] = new JRadioButton("Alle");
				radioButtonsSelectFavs[i].setSelected(true);
			} else{
				radioButtonsSelectFavs[i] = new JRadioButton(favList.get(i-1));
			}
			buttonGroupSelectFav.add(radioButtonsSelectFavs[i]);
			if(i!=0){
				radioButtonsSelectFavs[i].setSelected(ShortTimeMemory.getNameFavListShown().equals(radioButtonsSelectFavs[i].getText()));
			}
			radioButtonsSelectFavs[i].setBounds(gridButtonGap, yRadioButtons+i*heightLabel, widthLabel, heightLabel);
			radioButtonsSelectFavs[i].setOpaque(false);
			cp.add(radioButtonsSelectFavs[i]);
		}
		
		int frameHeightFavs = ySearch+(3+favList.size())*heightLabel+gridButtonGap;
		if(frameHeight<frameHeightFavs){
			frameHeight = frameHeightFavs;
		}		
		Vector<RelativeHorse> horses = getFilterSettings();
		Vector<Vector<String>> rowDataMale = new Vector<Vector<String>>();
		Vector<Vector<String>> rowDataFemale = new Vector<Vector<String>>();
		Iterator<RelativeHorse> it = horses.iterator();
		while(it.hasNext()){
			Vector<String> temp = new Vector<String>();
			RelativeHorse rh = it.next();
			temp.add(rh.getName());
			temp.add(((Integer)(rh.getCompletePotential())).toString());
			if(rh.isMale()){
				rowDataMale.add(temp);
			} else{
				rowDataFemale.add(temp);
			}
		}
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("GP");
		
		JLabel labelMale = new JLabel("Hengste");
		labelMale.setBounds(gridButtonGap*2+columnWidth, yTop, widthLabel, heightLabel);
		cp.add(labelMale);
		JTable tableMale = new JTable(rowDataMale, columnNames);
		tableMale.setAutoCreateRowSorter(true);
		tableMale.getColumnModel().getColumn(1).setPreferredWidth(0); //TODO
		JScrollPane scrollPaneTableMale = new JScrollPane(tableMale);
		scrollPaneTableMale.setBounds(2*gridButtonGap+columnWidth, yTop+heightLabel, columnWidth, frameHeight-yTop-heightLabel-40);
		cp.add(scrollPaneTableMale);
		
		JLabel labelFemale = new JLabel("Stuten");
		labelFemale.setBounds(gridButtonGap*3+2*columnWidth, yTop, widthLabel, heightLabel);
		cp.add(labelFemale);
		JTable tableFemale = new JTable(rowDataFemale, columnNames);
		tableFemale.setAutoCreateRowSorter(true);
		tableFemale.getColumnModel().getColumn(1).setPreferredWidth(0); //TODO
		JScrollPane scrollPaneTableFemale = new JScrollPane(tableFemale);
		scrollPaneTableFemale.setBounds(3*gridButtonGap+2*columnWidth, yTop+heightLabel, columnWidth, frameHeight-yTop-heightLabel-40);
		cp.add(scrollPaneTableFemale);
		
		JButton buttonAccept = new JButton("OK");
		buttonAccept.setBounds(gridButtonGap, frameHeight-40-buttonHeight, columnWidth, buttonHeight);
		buttonAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				choose();
			}
		});
		cp.add(buttonAccept);
		
		setSize(frameWidth, frameHeight);
		super2();
	}

	// 
	// ============ Methods =============
	//
	private void clearSelections(){
		for(int i=0; i<DatabaseManager.getFavourites().size()+1; i++){
			listsOfHorses[i].clearSelection();
		}
	}
	
	private Vector<RelativeHorse> filterVisible(String race, String searched, String nameSelectedFavList) {
		searched = searched.toLowerCase();
		Vector<RelativeHorse> horseList = DatabaseManager.getPopulation(); 
		final Vector<String> favourites = DatabaseManager.getFavourites();
		
		//prepare list by race, searched string and gender
		//must do this before sorting alphabetically since only strings can be sorted and here, horses are necessary
		for(int i = 0; i < horseList.size(); i++) {
			RelativeHorse current = horseList.get(i);
			boolean delete = current.getName().equals("Unbekannt") || !current.getRace().contains(race) || !current.getName().toLowerCase().contains(searched);
			if(!nameSelectedFavList.equals("Alle")){
					delete = delete || (current.getFavourites().contains(nameSelectedFavList));
			}
			if (delete) {
				horseList.remove(i);
				i--;
			}
		}
		
		return horseList;
		/*
		//create a TreeSet for each list of favourites
		@SuppressWarnings("unchecked")
		Collection<String>[] horseNames = new TreeSet[favourites.size()+1];
		for(int i=0; i<horseNames.length; i++){
			horseNames[i] = new TreeSet<String>(Collator.getInstance());
		}
		
		//move through all horses and insert into appropriate favourite-TreeSet
		//they are automatically sorted alphabetically
		for(RelativeHorse horse: horseList){
			horseNames[0].add(horse.getName());
			for(int i=0; i<horse.getFavourites().size(); i++){
				for(int j=0; j<favourites.size(); j++){
					if(horse.getFavourites().get(i).equals(favourites.get(j))){
						horseNames[j+1].add(horse.getName());
						break;
					}
				}
			}
		}
		
		//insert into ListModels
		/*for(int i=0; i<listsOfHorses.length; i++){
			DefaultListModel<String> listModel = (DefaultListModel<String>)(listsOfHorses[i].getModel());
			Iterator<String> iterator = horseNames[i].iterator();
			while(iterator.hasNext()){
				listModel.addElement(iterator.next());
			}
		}*/
	}

	private void indexSelected(JTable table, MouseEvent evt){
    	clearSelections();
		//table.setSelectedIndex(table.locationToIndex(evt.getPoint()));
        if (evt.getClickCount() == 2) {
    		choose();
        }
	}
	
	private String getSelectedFavList(){
		for(int i=0; i<radioButtonsSelectFavs.length; i++){
			if(radioButtonsSelectFavs[i].isSelected()){
				return radioButtonsSelectFavs[i].getText();
			}
		}
		return "Alle";
	}
	
	public void choose(){
		//save options
		ShortTimeMemory.setSelectedRace(raceFilter.getSelectedItem().toString());
		ShortTimeMemory.setSearchedString(search.getText());
		ShortTimeMemory.setNameFavListShown(getSelectedFavList());
		
		//insert into other frame
		String name = "";
		for(JTable table: listsOfHorses){
			/*if(!table.isSelectionEmpty()){
				name = table.getSelectedValue();
				break;
			}*/
		}
		if(name == null){
			return;
		}
		if (rcgui == null)
			new EditHorseGUI(name);
		else if (workLeft) {
			new HorseLoader(name, new DocumentManager(), rcgui.horseNamesL, rcgui.horseRacesL, null, rcgui.getRadioButtonIsMaleLeft(), rcgui.getRadioButtonIsFemaleLeft());
		} else {
			new HorseLoader(name, new DocumentManager(), rcgui.horseNamesR, rcgui.horseRacesR, null, rcgui.getRadioButtonIsMaleRight(), rcgui.getRadioButtonIsFemaleRight());
		}
		this.dispose();
	}

	public Vector<RelativeHorse> getFilterSettings() {
		String selectedRace = raceFilter.getSelectedItem().toString();
		if (selectedRace.equals("----------") || selectedRace.equals(" Alle")) {
			selectedRace = "";
		}
		return filterVisible(selectedRace, search.getText(), getSelectedFavList()); 
	}

	public static void main(String[] args) {
		new HorseChoiceGUI();
	}
}
