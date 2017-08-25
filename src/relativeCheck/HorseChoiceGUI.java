package relativeCheck;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableModel;

import general.MDRButton;
import general.MDRDialog;
import general.MDRFrame;
import general.ShortTimeMemory;

public class HorseChoiceGUI extends MDRDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int columnWidth = 200;

	protected JComboBox<String> raceFilter = new JComboBox<String>();
	protected JTextField textfieldSearchKey = new JTextField();
	private JRadioButton[] radioButtonsSelectFavs;
	private boolean isMaleSelected = false;

	protected MDRTable tableMale;
	protected MDRTable tableFemale;
	protected JScrollPane scrollPaneTableMale;
	protected JScrollPane scrollPaneTableFemale;
	protected MDRButton buttonAccept;

	//
	// ============== Constructor ===============
	//
	public HorseChoiceGUI(MDRFrame frameOwner) {
		super(frameOwner, "Pferd auswählen");
		initiate();
	}

	protected void initiate() {
		DatabaseManager.load();
		final Vector<String> favList = DatabaseManager.getFavourites();
		int frameWidth = 3 * (columnWidth + gridButtonGap) + gridButtonGap * 3 / 2;
		
		JLabel labelRaceFilter = new JLabel("Rassefilter:");
		labelRaceFilter.setBounds(gridButtonGap, yTop, columnWidth, heightLabel);
		cp.add(labelRaceFilter);
		raceFilter.setBounds(gridButtonGap, yTop + heightLabel, columnWidth, heightLabel);
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
				filterVisible();
			}
		});
		// set color of raceFilter
		Object child = raceFilter.getAccessibleContext().getAccessibleChild(0);
		BasicComboPopup popup = (BasicComboPopup) child;
		@SuppressWarnings("unchecked")
		JList<String> list = popup.getList();
		list.setSelectionBackground(MDRFrame.LIGHT_BROWN);
		raceFilter.setBackground(MDRFrame.BROWN);
		cp.add(raceFilter);

		int ySearch = yTop + 2 * heightLabel + gridButtonGap;
		JLabel labelSearchKey = new JLabel("Suchen:");
		labelSearchKey.setBounds(gridButtonGap, ySearch, columnWidth, heightLabel);
		cp.add(labelSearchKey);
		textfieldSearchKey.setBounds(gridButtonGap, ySearch + heightLabel, columnWidth, heightLabel);
		textfieldSearchKey.setText(ShortTimeMemory.getSearchedString());
		textfieldSearchKey.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				filterVisible();
			}
		});
		cp.add(textfieldSearchKey);

		JLabel labelFavList = new JLabel("Anzeigen:");
		labelFavList.setBounds(gridButtonGap, ySearch + 2 * heightLabel + gridButtonGap, columnWidth, heightLabel);
		cp.add(labelFavList);
		int yRadioButtons = ySearch + 3 * heightLabel + gridButtonGap;
		radioButtonsSelectFavs = new JRadioButton[favList.size() + 1];
		ButtonGroup buttonGroupSelectFav = new ButtonGroup();
		for (int i = 0; i < radioButtonsSelectFavs.length; i++) {
			if (i == 0) {
				radioButtonsSelectFavs[i] = new JRadioButton("Alle ("+DatabaseManager.getPopulation().size()+")");
				radioButtonsSelectFavs[i].setSelected(true);
			} else {
				radioButtonsSelectFavs[i] = new JRadioButton(ManageFavouritesGUI.getNameWithSize(favList.get(i - 1)));
			}
			buttonGroupSelectFav.add(radioButtonsSelectFavs[i]);
			if (i != 0) {
				radioButtonsSelectFavs[i].setSelected(ShortTimeMemory.getNameFavListShown().equals(ManageFavouritesGUI.getNameWithoutSize(radioButtonsSelectFavs[i].getText())));
			}
			radioButtonsSelectFavs[i].setBounds(gridButtonGap, yRadioButtons + i * heightLabel, columnWidth, heightLabel);
			radioButtonsSelectFavs[i].setOpaque(false);
			radioButtonsSelectFavs[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterVisible();
				}

			});
			cp.add(radioButtonsSelectFavs[i]);
		}

		int frameHeight = getFrameHeight(ySearch, favList.size());

		// =====================================================
		// Start tables
		// =====================================================
		Vector<Vector<String>> rowDataMale = new Vector<Vector<String>>();
		Vector<Vector<String>> rowDataFemale = new Vector<Vector<String>>();
		Vector<String> columnNames = getColumnNames();

		JLabel labelMale = new JLabel("Hengste");
		labelMale.setBounds(gridButtonGap * 2 + columnWidth, yTop, widthLabel, heightLabel);
		cp.add(labelMale);
		tableMale = new MDRTable(rowDataMale, columnNames);
		scrollPaneTableMale = new JScrollPane(tableMale);
		scrollPaneTableMale.setBounds(2 * gridButtonGap + columnWidth, yTop + heightLabel, columnWidth, frameHeight - yTop - heightLabel - 40);
		scrollPaneTableMale.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		cp.add(scrollPaneTableMale);

		JLabel labelFemale = new JLabel("Stuten");
		labelFemale.setBounds(gridButtonGap * 3 + 2 * columnWidth, yTop, widthLabel, heightLabel);
		cp.add(labelFemale);
		tableFemale = new MDRTable(rowDataFemale, columnNames);
		scrollPaneTableFemale = new JScrollPane(tableFemale);
		scrollPaneTableFemale.setBounds(3 * gridButtonGap + 2 * columnWidth, yTop + heightLabel, columnWidth, frameHeight - yTop - heightLabel - 40);
		scrollPaneTableFemale.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		cp.add(scrollPaneTableFemale);

		tableMale.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!isMaleSelected) {
					tableFemale.clearSelection();
					isMaleSelected = true;
				}
			}

		});
		tableFemale.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (isMaleSelected) {
					tableMale.clearSelection();
					isMaleSelected = false;
				}
			}

		});

		tableMale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					choose();
				}
			}
		});
		tableFemale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					choose();
				}
			}
		});

		tableMale.getRowSorter().toggleSortOrder(0);
		tableFemale.getRowSorter().toggleSortOrder(0);

		// =====================================================
		// End tables
		// =====================================================
		
		buttonAccept = new MDRButton("OK");
		buttonAccept.setBounds(gridButtonGap, frameHeight - 40 - buttonHeight, columnWidth, buttonHeight);
		buttonAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				choose();
			}
		});
		cp.add(buttonAccept);


		setSize(frameWidth, frameHeight);
		finish();
	}

	//
	// ============ Methods =============
	//

	// filters which horses are visible
	protected void filterVisible() {
		String selectedRace = raceFilter.getSelectedItem().toString();
		if (selectedRace.equals("----------")) {
			raceFilter.setSelectedIndex(0);
			return;
		}
		if (selectedRace.equals(" Alle")) {
			selectedRace = "";
		}

		String searched = textfieldSearchKey.getText().toLowerCase();
		String nameSelectedFavList = getSelectedFavList();
		Vector<RelativeHorse> allHorses = filterAll(searched, selectedRace, nameSelectedFavList);
		Vector<Vector<String>> rowDataMale = new Vector<Vector<String>>();
		Vector<Vector<String>> rowDataFemale = new Vector<Vector<String>>();

		Iterator<RelativeHorse> it = allHorses.iterator();
		while (it.hasNext()) {
			Vector<String> temp = new Vector<String>();
			RelativeHorse rh = it.next();
			temp.add(rh.getName());
			temp.add(((Integer) (rh.getCompletePotential())).toString());
			if (rh.isMale()) {
				rowDataMale.add(temp);
			} else {
				rowDataFemale.add(temp);
			}
		}

		DefaultTableModel modelMale = (DefaultTableModel) tableMale.getModel();
		updateTable(modelMale, rowDataMale);
		DefaultTableModel modelFemale = (DefaultTableModel) tableFemale.getModel();
		updateTable(modelFemale, rowDataFemale);

		tableMale.adjustColumnSize();
		tableFemale.adjustColumnSize();

		tableMale.getRowSorter().setSortKeys(tableMale.getRowSorter().getSortKeys());
		tableFemale.getRowSorter().setSortKeys(tableFemale.getRowSorter().getSortKeys());
	}

	protected void updateTable(DefaultTableModel tableModel, Vector<Vector<String>> rowData) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		for (int i = 0; i < rowData.size(); i++) {
			tableModel.addRow(rowData.get(i));
		}
	}

	protected Vector<RelativeHorse> filterAll(String searched, String selectedRace, String nameSelectedFavList) {
		Vector<RelativeHorse> horseList = DatabaseManager.getPopulation();
		for (int i = 0; i < horseList.size(); i++) {
			RelativeHorse current = horseList.get(i);
			boolean delete = current.getName().equals("Unbekannt") || !current.getRace().contains(selectedRace) || !current.getName().toLowerCase().contains(searched);
			if (!nameSelectedFavList.equals("Alle")) {
				delete = delete || current.getFavourites() == null || !(current.getFavourites().contains(nameSelectedFavList));
			}
			if (delete) {
				horseList.remove(i);
				i--;
			}
		}
		return horseList;
	}

	protected String getSelectedFavList() {
		for (int i = 0; i < radioButtonsSelectFavs.length; i++) {
			if (radioButtonsSelectFavs[i].isSelected()) {
				String favName = radioButtonsSelectFavs[i].getText();
				return ManageFavouritesGUI.getNameWithoutSize(favName);
			}
		}
		return "Alle";
	}

	protected void choose() {
		// save options
		ShortTimeMemory.setSelectedRace(raceFilter.getSelectedItem().toString());
		ShortTimeMemory.setSearchedString(textfieldSearchKey.getText());
		ShortTimeMemory.setNameFavListShown(getSelectedFavList());

		// insert into other frame
		String name = "";
		if (tableMale.getSelectedRow() >= 0) {
			name = (String) tableMale.getValueAt(tableMale.getSelectedRow(), 0);
		} else if (tableFemale.getSelectedRow() >= 0) {
			name = (String) tableFemale.getValueAt(tableFemale.getSelectedRow(), 0);
		}
		Window owner = this.getOwner();
		if (owner instanceof ManageHorseGUI) {
			((ManageHorseGUI) owner).setSubject(name);
		}
		this.dispose();
	}
	
	private int getFrameHeight(int ySearch, int favListSize){
		int frameHeight = yTop + heightLabel + columnWidth + gridButtonGap * 4; // is changed by favList if too small
		int frameHeightFavs = ySearch + (3 + favListSize) * (heightLabel + gridButtonGap) *7/8 + buttonHeight + 2*gridButtonGap;
		if (frameHeight < frameHeightFavs) {
			frameHeight = frameHeightFavs;
		}
		return frameHeight;
	}
	
	protected Vector<String> getColumnNames(){
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("GP");		
		return columnNames;
	}
	
	protected void finish(){
		filterVisible();
		
		//needed for setting focus
		this.setModal(false);
		
		super2();
		
		//needed for setting focus
		textfieldSearchKey.requestFocusInWindow();
		this.setModal(true);
		this.setVisible(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new HorseChoiceGUI(new NewHorseGUI());
	}
}
