package verwandschaftscheck;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import allgemein.MDRFrame;

public class HorseAndRaceField extends Container{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textfieldName = new JTextField();
	private JComboBox<String> comboboxRace = new JComboBox<String>();
	private final String[] races = new String[] { " Unbekannt", "Achal-Tekkiner", "American Paint Horse", "American Quarter Horse", "Andalusier", "Appaloosa", "Berber", "Cape Boerperd", "Criollo",
			"Deutsches Reitpony", "Englisches Vollblut", "Hannoveraner", "Holsteiner", "Lipizzaner", "Lusitano", "Oldenburger", "Pinto", "Trakehner", "Vollblutaraber" };
	public static final int WIDTH = 130;
	public static final int HEIGHT = 41;
	
	public HorseAndRaceField(){
		textfieldName.setPreferredSize(new Dimension(WIDTH, MDRFrame.buttonHeight));
		textfieldName.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				if ((textfieldName.getText()).equals("nicht in DB"))
					textfieldName.setText("");
			}
		});
		comboboxRace.setPreferredSize(new Dimension(125, HEIGHT - MDRFrame.buttonHeight));
		comboboxRace.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		for (int j = 0; j < races.length; j++) {
			comboboxRace.addItem(races[j]);
		}
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(textfieldName);
		this.add(comboboxRace);
	}
	
	public String getName(){
		return textfieldName.getText();
	}
	
	public String getSelectedItem(){
		return (String)comboboxRace.getSelectedItem();
	}
	
	public void setSelectedIndex(int index){
		comboboxRace.setSelectedIndex(index);
	}
	
	public void setName(String name){
		textfieldName.setText(name);
	}
	
	public void setCaretPosition(int position){
		textfieldName.setCaretPosition(position);
	}
	
	public String getRace(){
		return (String)comboboxRace.getSelectedItem();
	}
	
	public void setSelectedItem(String race){
		comboboxRace.setSelectedItem(race);
	}
	
	@Override
	public void setLocation(int x, int y){
		setBounds(new Rectangle(new Point(x,y), getPreferredSize()));
	}
}
