package verwandschaftscheck;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class HorseAndRaceField extends Container{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textfieldName = new JTextField();
	private JComboBox<String> comboboxRace = new JComboBox<String>();
	private String[] races = new String[] { " Unbekannt", "Achal-Tekkiner", "American Paint Horse", "American Quarter Horse", "Andalusier", "Appaloosa", "Berber", "Cape Boerperd", "Criollo",
			"Deutsches Reitpony", "Englisches Vollblut", "Hannoveraner", "Holsteiner", "Lipizzaner", "Lusitano", "Oldenburger", "Pinto", "Trakehner", "Vollblutaraber" };
	
	public HorseAndRaceField(){
		textfieldName.setPreferredSize(new Dimension(125, 25));
		textfieldName.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				if ((textfieldName.getText()).equals("nicht in DB"))
					textfieldName.setText("");
			}
		});
		comboboxRace.setPreferredSize(new Dimension(125, 15));
		comboboxRace.setFont(new Font("@Arial Unicode MS", Font.BOLD, 9));
		for (int j = 0; j < races.length; j++) {
			comboboxRace.addItem(races[j]);
		}
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(textfieldName);
		this.add(comboboxRace);
	}
}
