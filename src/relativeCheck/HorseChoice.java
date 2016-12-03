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

public class HorseChoice extends MDRFrame {
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

	boolean getFavs = true;
	RelativeCheckGUI rcgui;
	boolean workLeft;
	private JTextField search = new JTextField();
	private JCheckBox checkBoxShowMale = new JCheckBox("Hengste");
	private JCheckBox checkBoxShowFemale = new JCheckBox("Stuten");
	
	private JRadioButton radioButtonSortByName = new JRadioButton("Name");

	//
	// ============== Constructors ===============
	//
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
		final int columnWidth = 200;
		DatabaseManager.load();
		final Vector<RelativeHorse> horseList = DatabaseManager.getPopulation();
		
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
		
		int yCheckBox = yTop + 2*heightLabel + gridButtonGap;
		checkBoxShowMale.setBounds(gridButtonGap, yCheckBox, 75, heightLabel);
		checkBoxShowMale.setOpaque(false);
		checkBoxShowMale.setSelected(ShortTimeMemory.isSelectedMale());
		checkBoxShowMale.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				getFilterSettings();
			}
		});
		cp.add(checkBoxShowMale);
		checkBoxShowFemale.setBounds(gridButtonGap+columnWidth/2, yCheckBox, 65, heightLabel);
		checkBoxShowFemale.setOpaque(false);
		checkBoxShowFemale.setSelected(ShortTimeMemory.isSelectedFemale());
		checkBoxShowFemale.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				getFilterSettings();
			}
		});
		cp.add(checkBoxShowFemale);
		
		JLabel labelSearchKey = new JLabel("Suchen:");
		labelSearchKey.setBounds(gridButtonGap, yCheckBox+heightLabel+gridButtonGap/2, columnWidth, heightLabel);
		cp.add(labelSearchKey);
		textfieldSearchKey.setBounds(gridButtonGap, yCheckBox+2*heightLabel+gridButtonGap/2, columnWidth, heightLabel);
		textfieldSearchKey.setText(ShortTimeMemory.getSearchedString());
		textfieldSearchKey.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				getFilterSettings();
			}
		});
		cp.add(textfieldSearchKey);
		
		JLabel labelSortOrder = new JLabel("Sortieren nach");
		labelSortOrder.setBounds(gridButtonGap, yCheckBox+3*heightLabel+gridButtonGap, columnWidth, heightLabel);
		cp.add(labelSortOrder);
		int yRadioButtons = yCheckBox+4*heightLabel+gridButtonGap;
		radioButtonSortByName.setSelected(ShortTimeMemory.isSortByName());
		radioButtonSortByName.setBounds(gridButtonGap, yRadioButtons, 60, heightLabel);
		radioButtonSortByName.setOpaque(false);
		cp.add(radioButtonSortByName);
		JRadioButton radioButtonSortByCP = new JRadioButton("GP");
		radioButtonSortByCP.setBounds(gridButtonGap+columnWidth/2, yRadioButtons, 45, heightLabel);
		radioButtonSortByCP.setOpaque(false);
		cp.add(radioButtonSortByCP);
		ButtonGroup buttonGroupSortOrder = new ButtonGroup();
		buttonGroupSortOrder.add(radioButtonSortByName);
		buttonGroupSortOrder.add(radioButtonSortByCP);
		
		Vector<Object[]> rowData = new Vector<Object[]>();
		Iterator<RelativeHorse> it = horseList.iterator();
		while(it.hasNext()){
			Object[] temp = new Object[2];
			RelativeHorse rh = it.next();
			temp[0] = rh.getName();
			temp[1] = rh.getCompletePotential();
			rowData.add(temp);
		}
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("GP");
		
		listsOfHorses[0] = new JTable(rowData, columnNames);
		//listsOfHorses[0].setModel(listModelAllHorses);
		JScrollPane scrollPaneListAllHorses = new JScrollPane(listsOfHorses[0]);
		scrollPaneListAllHorses.setBounds(gridButtonGap, yRadioButtons+heightLabel+gridButtonGap/2, columnWidth, columnWidth);
		listsOfHorses[0].setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listsOfHorses[0].addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	indexSelected(listsOfHorses[0], evt);
		    }
		});
		cp.add(scrollPaneListAllHorses);
	
		JButton buttonAccept = new JButton("OK");
		buttonAccept.setBounds(gridButtonGap, yRadioButtons+heightLabel+gridButtonGap*3/2+columnWidth, columnWidth, buttonHeight);
		buttonAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				choose();
			}
		});
		cp.add(buttonAccept);
		
		int columnHeight = yRadioButtons+heightLabel+gridButtonGap/2+buttonHeight;
		final Vector<String> favouriteLists = DatabaseManager.getFavourites();
		/*for(int i=0; i<favouriteLists.size(); i++){
			JLabel labelTemp = new JLabel(favouriteLists.get(i));
			int x = (gridButtonGap+columnWidth)*(i+1);
			labelTemp.setBounds(x, yTop, columnWidth, heightLabel);
			
			listsOfHorses[i+1] = new JList<String>();
			DefaultListModel<String> horseListTempModel = new DefaultListModel<String>();
			JScrollPane horseListTempScrollPane = new JScrollPane(listsOfHorses[i+1]);
			
			listsOfHorses[i+1].setModel(horseListTempModel);
			horseListTempScrollPane.setBounds(x, yTop+heightLabel, columnWidth, columnHeight-heightLabel);
			listsOfHorses[i+1].setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			int temp = i+1;
			listsOfHorses[i+1].addMouseListener(new MouseAdapter() {
				@Override
			    public void mouseClicked(MouseEvent evt) {
			    	indexSelected(listsOfHorses[temp], evt);
			    }
			});
			cp.add(horseListTempScrollPane);
		}*/
		
		getFilterSettings();
		
		int frameWidth = (favouriteLists.size()+1)*(columnWidth+gridButtonGap)+gridButtonGap*3/2;
		int frameHeight = yRadioButtons+heightLabel+gridButtonGap*5+columnWidth+buttonHeight;
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
	
	private void filterVisible(String race, String searched, boolean showMale, boolean showFemale) {
		searched = searched.toLowerCase();
		Vector<RelativeHorse> horseList = DatabaseManager.getPopulation(); 
		final Vector<String> favourites = DatabaseManager.getFavourites();
		
		//prepare list by race, searched string and gender
		//must do this before sorting alphabetically since only strings can be sorted and here, horses are necessary
		for(int i = 0; i < horseList.size(); i++) {
			RelativeHorse current = horseList.get(i);
			boolean delete = current.getName().equals("Unbekannt") || !current.getRace().contains(race) || !current.getName().toLowerCase().contains(searched);
			delete = delete || (current.isMale() && !showMale) || (!current.isMale() && !showFemale);
			if (delete) {
				horseList.remove(i);
				i--;
			}
		}
		
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
	
	public void choose(){
		//save options
		ShortTimeMemory.setSelectedRace(raceFilter.getSelectedItem().toString());
		ShortTimeMemory.setSelectedMale(checkBoxShowMale.isSelected());
		ShortTimeMemory.setSelectedFemale(checkBoxShowFemale.isSelected());
		ShortTimeMemory.setSearchedString(search.getText());
		ShortTimeMemory.setSortByName(radioButtonSortByName.isSelected());
		
		//insert into other frame
		String name = null;
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
			new NameLoader(name, new DocumentManager(), rcgui.horseNamesL, rcgui.horseRacesL, null, rcgui.getRadioButtonIsMaleLeft(), rcgui.getRadioButtonIsFemaleLeft());
		} else {
			new NameLoader(name, new DocumentManager(), rcgui.horseNamesR, rcgui.horseRacesR, null, rcgui.getRadioButtonIsMaleRight(), rcgui.getRadioButtonIsFemaleRight());
		}
		this.dispose();
	}

	public void getFilterSettings() {
		String selectedRace = raceFilter.getSelectedItem().toString();
		if (selectedRace.equals("----------") || selectedRace.equals(" Alle")) {
			selectedRace = "";
		}
		filterVisible(selectedRace, search.getText(), checkBoxShowMale.isSelected(), checkBoxShowFemale.isSelected());
	}

	public static void main(String[] args) {
		new HorseChoice();
	}
}
