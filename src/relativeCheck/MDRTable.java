package relativeCheck;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import general.MDRFrame;

public class MDRTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDRTable(Vector<Vector<String>> rowData, Vector<String> columnNames) {
		this.setModel(new DefaultTableModel(rowData, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;// This causes all cells to be not editable
			}
		});

		setAutoCreateRowSorter(true);
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		adjustColumnSize();
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		UIManager.put("ScrollBar.background", MDRFrame.BROWN);
		updateUI();
		this.setSelectionBackground(MDRFrame.LIGHT_BROWN);

		this.getTableHeader().setBackground(MDRFrame.BROWN);
	}

	public void adjustColumnSize() {
		int widthFirstColumn;
		if (this.getRowCount() > 14) {
			widthFirstColumn = 144;
		} else {
			widthFirstColumn = 160;
		}
		this.getColumnModel().getColumn(0).setPreferredWidth(widthFirstColumn);
		this.getColumnModel().getColumn(1).setPreferredWidth(38);
	}
}
