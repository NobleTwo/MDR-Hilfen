package relativeCheck;

import java.awt.Component;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
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

	private JComboBox<String> raceFilter = new JComboBox<String>();
	private JTextField textfieldSearchKey = new JTextField();
	private JRadioButton[] radioButtonsSelectFavs;
	private JTextField search = new JTextField();
	private boolean isMaleSelected = false;

	private MDRTable tableMale;
	private MDRTable tableFemale;

	// needed for RelativeCheckGUI
	private int position;
	private RelativeHorse subject;

	//
	// ============== Constructors ===============
	//
	public HorseChoiceGUI(ManageHorseGUI frameOwner) {
		super(frameOwner, "Pferd auswählen");
		initiate();
	}

	public HorseChoiceGUI(RelativeCheckGUI frameOwner, int pos) {
		super(frameOwner, "Pferd auswählen");
		this.position = pos;
		initiate();
	}

	public void initiate() {
		DatabaseManager.load();
		final Vector<String> favList = DatabaseManager.getFavourites();

		int frameWidth = 3 * (columnWidth + gridButtonGap) + gridButtonGap * 3 / 2;
		int frameHeight = yTop + heightLabel + columnWidth + gridButtonGap * 4; // is changed by favList if too small

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
				radioButtonsSelectFavs[i] = new JRadioButton("Alle");
				radioButtonsSelectFavs[i].setSelected(true);
			} else {
				radioButtonsSelectFavs[i] = new JRadioButton(favList.get(i - 1));
			}
			buttonGroupSelectFav.add(radioButtonsSelectFavs[i]);
			if (i != 0) {
				radioButtonsSelectFavs[i].setSelected(ShortTimeMemory.getNameFavListShown().equals(radioButtonsSelectFavs[i].getText()));
			}
			radioButtonsSelectFavs[i].setBounds(gridButtonGap, yRadioButtons + i * heightLabel, widthLabel, heightLabel);
			radioButtonsSelectFavs[i].setOpaque(false);
			radioButtonsSelectFavs[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterVisible();
				}

			});
			cp.add(radioButtonsSelectFavs[i]);
		}

		int frameHeightFavs = ySearch + (3 + favList.size()) * (heightLabel + gridButtonGap) + buttonHeight;
		if (frameHeight < frameHeightFavs) {
			frameHeight = frameHeightFavs;
		}

		//
		// Start tables
		//
		Vector<Vector<String>> rowDataMale = new Vector<Vector<String>>();
		Vector<Vector<String>> rowDataFemale = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("GP");

		JLabel labelMale = new JLabel("Hengste");
		labelMale.setBounds(gridButtonGap * 2 + columnWidth, yTop, widthLabel, heightLabel);
		cp.add(labelMale);
		tableMale = new MDRTable(rowDataMale, columnNames);
		JScrollPane scrollPaneTableMale = new JScrollPane(tableMale);
		scrollPaneTableMale.setBounds(2 * gridButtonGap + columnWidth, yTop + heightLabel, columnWidth, frameHeight - yTop - heightLabel - 40);
		scrollPaneTableMale.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		cp.add(scrollPaneTableMale);

		JLabel labelFemale = new JLabel("Stuten");
		labelFemale.setBounds(gridButtonGap * 3 + 2 * columnWidth, yTop, widthLabel, heightLabel);
		cp.add(labelFemale);
		tableFemale = new MDRTable(rowDataFemale, columnNames);
		JScrollPane scrollPaneTableFemale = new JScrollPane(tableFemale);
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

		// set renderers for cell colors
		Window owner = this.getOwner();
		if (owner instanceof RelativeCheckGUI) {
			RelativeCheckGUI ownerRC = (RelativeCheckGUI) owner;
			subject = ownerRC.getSubject(Math.abs(1 - position));
			if (subject != null) {
				tableMale.getColumnModel().getColumn(0).setCellRenderer(new StatusColumnCellRenderer());
				tableMale.getColumnModel().getColumn(1).setCellRenderer(new StatusColumnCellRenderer());
				tableFemale.getColumnModel().getColumn(0).setCellRenderer(new StatusColumnCellRenderer());
				tableFemale.getColumnModel().getColumn(1).setCellRenderer(new StatusColumnCellRenderer());

				setTitle("Partner für " + subject.getName() + " auswählen");
			}
		}
		//
		// End tables
		//

		MDRButton buttonAccept = new MDRButton("OK");
		buttonAccept.setBounds(gridButtonGap, frameHeight - 40 - buttonHeight, columnWidth, buttonHeight);
		buttonAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				choose();
			}
		});
		cp.add(buttonAccept);

		filterVisible();
		setSize(frameWidth, frameHeight);
		super2();
	}

	//
	// ============ Methods =============
	//

	// filters which horses are visible
	private void filterVisible() {
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

		/*
		 * //create a TreeSet for each list of favourites
		 * 
		 * @SuppressWarnings("unchecked") Collection<String>[] horseNames = new TreeSet[favourites.size()+1]; for(int i=0; i<horseNames.length; i++){ horseNames[i] = new TreeSet<String>(Collator.getInstance()); }
		 * 
		 * //move through all horses and insert into appropriate favourite-TreeSet //they are automatically sorted alphabetically for(RelativeHorse horse: horseList){ horseNames[0].add(horse.getName()); for(int i=0; i<horse.getFavourites().size(); i++){ for(int j=0; j<favourites.size(); j++){ if(horse.getFavourites().get(i).equals(favourites.get(j))){ horseNames[j+1].add(horse.getName()); break; } } } }
		 * 
		 * //insert into ListModels /*for(int i=0; i<listsOfHorses.length; i++){ DefaultListModel<String> listModel = (DefaultListModel<String>)(listsOfHorses[i].getModel()); Iterator<String> iterator = horseNames[i].iterator(); while(iterator.hasNext()){ listModel.addElement(iterator.next()); } }
		 */
	}

	private void updateTable(DefaultTableModel tableModel, Vector<Vector<String>> rowData) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		for (int i = 0; i < rowData.size(); i++) {
			tableModel.addRow(rowData.get(i));
		}
	}

	private Vector<RelativeHorse> filterAll(String searched, String selectedRace, String nameSelectedFavList) {
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

	private String getSelectedFavList() {
		for (int i = 0; i < radioButtonsSelectFavs.length; i++) {
			if (radioButtonsSelectFavs[i].isSelected()) {
				return radioButtonsSelectFavs[i].getText();
			}
		}
		return "Alle";
	}

	public void choose() {
		// save options
		ShortTimeMemory.setSelectedRace(raceFilter.getSelectedItem().toString());
		ShortTimeMemory.setSearchedString(search.getText());
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
		} else if (owner instanceof RelativeCheckGUI) {
			RelativeCheckGUI ownerRC = (RelativeCheckGUI) owner;
			ownerRC.setSubject(position, name);
		}
		this.dispose();
	}

	// for coloring cells
	private class StatusColumnCellRenderer extends DefaultTableCellRenderer {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			// Cells are by default rendered as a JLabel.
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

			// Get the status for the current row.
			if (subject != null) {
				// if table is for wrong sex: set color to red
				if (table.equals(tableMale) && subject.isMale()) {
					label.setBackground(MDRFrame.RED2);
				} else if (table.equals(tableFemale) && !subject.isMale()) {
					label.setBackground(MDRFrame.RED2);
				} else {
					// else: set background to fitting color
					int rowSorted = table.getRowSorter().convertRowIndexToModel(row);
					String name;
					if (col == 0) {
						name = (String) table.getModel().getValueAt(rowSorted, col);
					} else {
						name = (String) table.getModel().getValueAt(rowSorted, 0);
					}
					RelativeHorse rh = DatabaseManager.findHorse(name);
					switch (doSingleRelativeCheck(rh)) {
					case ERROR:
						label.setBackground(MDRFrame.RED2);
						break;
					case WARNING:
						label.setBackground(MDRFrame.ORANGE);
						break;
					case MATCH:
						label.setBackground(MDRFrame.GREEN2);
						break;
					default:
						break;
					}
				}
			}
			if (col == 0) {
				setHorizontalAlignment(JLabel.LEFT);
			} else {
				setHorizontalAlignment(JLabel.RIGHT);
			}

			// Return the JLabel which renders the cell.
			return label;
		}
	}

	private ResultType doSingleRelativeCheck(RelativeHorse rh) {
		Window owner = this.getOwner();
		if (owner instanceof RelativeCheckGUI) {
			RelativeCheckGUI ownerRC = (RelativeCheckGUI) owner;
			subject = ownerRC.getSubject(Math.abs(1 - position));
			RelativeHorse[] horses = HorseLoader.load(subject.getName());
			String[][] relatives = new String[2][15];
			String[][] racesOfRelatives = new String[2][15];
			for (int i = 0; i < relatives[0].length; i++) {
				if (horses[i] != null) {
					relatives[0][i] = horses[i].getName();
					racesOfRelatives[0][i] = horses[i].getRace();
				} else {
					relatives[0][i] = "nicht in DB";
					racesOfRelatives[0][i] = " Unbekannt";
				}
			}

			boolean[] isMale = { true, false };

			RelativeHorse[] tempHorses = HorseLoader.load(rh.getName());

			for (int j = 0; j < relatives[1].length; j++) {
				if (tempHorses[j] != null) {
					relatives[1][j] = tempHorses[j].getName();
					racesOfRelatives[1][j] = tempHorses[j].getRace();
				} else {
					relatives[1][j] = "nicht in DB";
					racesOfRelatives[1][j] = " Unbekannt";
				}
			}
			return new RelativeCheckResult(relatives, racesOfRelatives, isMale).getResult();
		}
		return ResultType.NEUTRAL;
	}

	public static void main(String[] args) {
		new HorseChoiceGUI(new NewHorseGUI());
	}
}
