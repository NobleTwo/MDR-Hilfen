package relativeCheck;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboPopup;

import general.MDRFrame;

public class HorseAndRaceField extends Container {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textfieldName = new JTextField();
	private JComboBox<String> comboBoxRace = new JComboBox<String>();
	private final String[] races = new String[] { " Unbekannt", "Achal-Tekkiner", "American Paint Horse", "American Quarter Horse", "Andalusier", "Appaloosa", "Berber", "Cape Boerperd", "Criollo", "Deutsches Reitpony", "Englisches Vollblut", "Hannoveraner", "Holsteiner", "Lipizzaner", "Lusitano", "Oldenburger", "Pinto", "Trakehner", "Vollblutaraber" };
	public static final int WIDTH = 130;
	public static final int HEIGHT = 41;

	public HorseAndRaceField() {
		textfieldName.setPreferredSize(new Dimension(WIDTH, MDRFrame.buttonHeight));
		// FocusListener needed for ManageHorseGUI
		textfieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				if ((textfieldName.getText()).equals("nicht in DB")) {
					textfieldName.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent evt) {
				if ((textfieldName.getText()).equals("")) {
					textfieldName.setText("nicht in DB");
				}
			}
		});
		comboBoxRace.setPreferredSize(new Dimension(125, HEIGHT - MDRFrame.buttonHeight));
		comboBoxRace.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		for (int j = 0; j < races.length; j++) {
			comboBoxRace.addItem(races[j]);
		}
		//set color of combobobox
		comboBoxRace.setBackground(MDRFrame.BROWN);
		UIManager.put("ScrollBar.background", MDRFrame.DARK_BROWN);
		comboBoxRace.updateUI();
		Object child = comboBoxRace.getAccessibleContext().getAccessibleChild(0);
		BasicComboPopup popup = (BasicComboPopup)child;
		@SuppressWarnings("unchecked")
		JList<String> list = popup.getList();
		list.setSelectionBackground(MDRFrame.LIGHT_BROWN);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(textfieldName);
		this.add(comboBoxRace);
	}

	public String getName() {
		return textfieldName.getText();
	}

	public String getSelectedItem() {
		return (String) comboBoxRace.getSelectedItem();
	}

	public void setSelectedIndex(int index) {
		comboBoxRace.setSelectedIndex(index);
	}

	public void setName(String name) {
		textfieldName.setText(name);
	}

	public void setCaretPosition(int position) {
		textfieldName.setCaretPosition(position);
	}

	public String getRace() {
		return (String) comboBoxRace.getSelectedItem();
	}

	public void setSelectedItem(String race) {
		comboBoxRace.setSelectedItem(race);
	}

	@Override
	public void setLocation(int x, int y) {
		setBounds(new Rectangle(new Point(x, y), getPreferredSize()));
	}

	public void setEditable(boolean editable) {
		textfieldName.setEditable(editable);
		textfieldName.setBackground(MDRFrame.BROWN);
		for (FocusListener fl : textfieldName.getFocusListeners()) {
			textfieldName.removeFocusListener(fl);
		}
		// removes Dropdown by click on race name
		for (MouseListener fl : comboBoxRace.getMouseListeners()) {
			comboBoxRace.removeMouseListener(fl);
		}
		// removes Dropdown by click on arrow
		for (MouseListener fl : comboBoxRace.getComponent(0).getMouseListeners()) {
			comboBoxRace.getComponent(0).removeMouseListener(fl);
		}
	}

	public void setColor(boolean forTextfield, ResultType type) {
		if (forTextfield) {
			switch (type) {
			case WARNING:
				textfieldName.setBackground(MDRFrame.ORANGE);
				break;
			case ERROR:
				textfieldName.setBackground(MDRFrame.RED2);
				break;
			case MATCH:
				textfieldName.setBackground(MDRFrame.GREEN2);
				break;
			case NEUTRAL:
				textfieldName.setBackground(MDRFrame.BROWN);
			}
		} else {
			switch (type) {
			case WARNING:
				comboBoxRace.setBackground(MDRFrame.ORANGE);
				break;
			case ERROR:
				comboBoxRace.setBackground(MDRFrame.RED2);
				break;
			case MATCH:
				comboBoxRace.setBackground(MDRFrame.GREEN2);
				break;
			case NEUTRAL:
				comboBoxRace.setBackground(MDRFrame.BROWN);
			}
		}
	}
}
