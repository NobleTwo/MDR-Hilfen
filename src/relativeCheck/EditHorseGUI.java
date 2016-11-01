package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EditHorseGUI extends ManageHorseGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public EditHorseGUI(String name) {
		super("Verwandtschaftscheck: Bestehendes Pferd bearbeiten");
		buttonAddOrSaveHorse.setText("Pferd speichern");
		buttonResetOrDeleteHorse.setText("Pferd löschen");
		buttonChooseHorse.setText("Anderes Pferd bearbeiten");
		this.name = name;
		
		JButton buttonNewHorse = new JButton("Neues Pferd");
		int x4row = 4*gridButtonGap + 3*horseNamesAndRaces[0].getWidth();
		int yTop = 3*gridButtonGap + 20;
		int yBot = yTop + (gridButtonGap + HorseAndRaceField.HEIGHT)*8;
		buttonNewHorse.setBounds(x4row, yBot, HorseAndRaceField.WIDTH, buttonHeight);
		buttonNewHorse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new NewHorseGUI();
				dispose();
			}
		});
		cp.add(buttonNewHorse);
		
		super2();
		textInput.requestFocus();
	}

	@Override
	protected void addOrSaveHorse() {
		//save
		
	}

	@Override
	protected void resetOrDelete() {
		//delete
		DatabaseManager.removeHorse(name);
		new NewHorseGUI();
		this.dispose();
	}
	
	public static void main(String[] args){
		new EditHorseGUI("Test");
	}
}
