package relativeCheck;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import general.MDRButton;
import general.MDRFrame;

public class RelativeChoiceGUI extends HorseChoiceGUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2881889443319788956L;

	private int position = -1;
	private RelativeHorse subject;

	public RelativeChoiceGUI(RelativeCheckGUI frameOwner, int pos) {
		super(frameOwner);
		this.position = pos;
		subject = frameOwner.getSubject(Math.abs(pos - 1));

		// set renderers for cell colors
		if (subject != null) {
			int frameHeightOld = this.getHeight();
			int frameHeight = frameHeightOld+buttonHeight + gridButtonGap;
			this.setSize(this.getWidth(), frameHeight);
			
			tableMale.getColumnModel().getColumn(0).setCellRenderer(new StatusColumnCellRenderer());
			tableMale.getColumnModel().getColumn(1).setCellRenderer(new StatusColumnCellRenderer());

			tableFemale.getColumnModel().getColumn(0).setCellRenderer(new StatusColumnCellRenderer());
			tableFemale.getColumnModel().getColumn(1).setCellRenderer(new StatusColumnCellRenderer());

			setTitle("Partner für " + subject.getName() + " auswählen");

			MDRButton buttonSortByColor = new MDRButton("nach Farbe sortieren");
			buttonSortByColor.setBounds(gridButtonGap, this.getHeight() - 40 - 2 * buttonHeight - gridButtonGap, columnWidth, buttonHeight);
			buttonSortByColor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					sortByColor();
				}
			});
			cp.add(buttonSortByColor);
		
			scrollPaneTableMale.setBounds(scrollPaneTableMale.getX(), scrollPaneTableMale.getY(), scrollPaneTableMale.getWidth(), scrollPaneTableMale.getHeight()-frameHeightOld+frameHeight);
			scrollPaneTableFemale.setBounds(scrollPaneTableFemale.getX(), scrollPaneTableFemale.getY(), scrollPaneTableFemale.getWidth(), scrollPaneTableFemale.getHeight()-frameHeightOld+frameHeight);
			buttonAccept.setBounds(buttonAccept.getX(), buttonAccept.getY()-frameHeightOld+frameHeight, buttonAccept.getWidth(), buttonAccept.getHeight());
		}
		finish2();
	}

	// filters which horses are visible
	@Override
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
				if (subject != null) {
					if (subject.isMale()) {
						temp.add("red");
					} else {
						switch (doSingleRelativeCheck(rh)) {
						case ERROR:
							temp.add("red");
							break;
						case WARNING:
							temp.add("orange");
							break;
						case MATCH:
							temp.add("green");
							break;
						default:
							break;
						}
					}
				} else{
					temp.add("null");
				}
				rowDataMale.add(temp);
			} else {
				if (subject != null) {
					if (!subject.isMale()) {
						temp.add("red");
					} else {
						switch (doSingleRelativeCheck(rh)) {
						case ERROR:
							temp.add("red");
							break;
						case WARNING:
							temp.add("orange");
							break;
						case MATCH:
							temp.add("green");
							break;
						default:
							break;
						}
					}
				} else{
					temp.add("null");
				}
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

	@Override
	protected void choose() {
		String name = "";
		if (tableMale.getSelectedRow() >= 0) {
			name = (String) tableMale.getValueAt(tableMale.getSelectedRow(), 0);
		} else if (tableFemale.getSelectedRow() >= 0) {
			name = (String) tableFemale.getValueAt(tableFemale.getSelectedRow(), 0);
		}
		((RelativeCheckGUI) this.getOwner()).setSubject(position, name);
		super.choose();
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
					// int rowSorted = table.getRowSorter().convertRowIndexToModel(row);
					switch ((String) table.getValueAt(row, 2)) {
					case "red":
						label.setBackground(MDRFrame.RED2);
						break;
					case "orange":
						label.setBackground(MDRFrame.ORANGE);
						break;
					case "green":
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
			
			//for subject
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

			//for rh
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
			return new RelativeCheckResult(relatives, racesOfRelatives, isMale).getResultType();
		}
		return ResultType.NEUTRAL;
	}

	private void sortByColor() {
		if (position == 0) {
			tableMale.getRowSorter().toggleSortOrder(2);
		} else {
			tableFemale.getRowSorter().toggleSortOrder(2);
		}
	}

	@Override
	protected Vector<String> getColumnNames() {
		Vector<String> columnNames = super.getColumnNames();
		columnNames.add("Color");
		return columnNames;
	}

	@Override
	protected void finish() {
	}

	protected void finish2(){
		super.finish();
	}

	public static void main(String[] args) {
		new RelativeChoiceGUI(new RelativeCheckGUI(), 0);
	}
}
