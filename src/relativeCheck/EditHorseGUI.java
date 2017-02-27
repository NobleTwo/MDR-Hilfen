package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import general.MDRButton;

public class EditHorseGUI extends ManageHorseGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditHorseGUI(RelativeHorse subject) {
		super("Verwandtschaftscheck: Bestehendes Pferd bearbeiten");
		buttonAddOrSaveHorse.setText("Pferd speichern");
		buttonResetOrDeleteHorse.setText("Pferd löschen");
		buttonChooseHorse.setText("Anderes Pferd bearbeiten");
		setSubject(subject);
		HorseLoader.assignNamesAndRaces(subject.getName(), horseNamesAndRaces);
		numberfieldGP.setText(subject.getCompletePotential());
		for (JCheckBox checkbox : checkboxFavourites) {
			if (subject.getFavourites() != null && subject.getFavourites().contains(checkbox.getText())) {
				checkbox.setSelected(true);
			}
		}
		if (subject.isMale()) {
			radioButtonIsMale.setSelected(true);
		} else {
			radioButtonIsFemale.setSelected(true);
		}

		MDRButton buttonNewHorse = new MDRButton("Neues Pferd");
		int x4row = 4 * gridButtonGap + 3 * horseNamesAndRaces[0].getWidth();
		int yTop = 3 * gridButtonGap + 20;
		int yBot = yTop + (gridButtonGap + HorseAndRaceField.HEIGHT) * 8;
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
		this.repaint();
	}

	@Override
	protected void resetOrDelete() {
		// delete
		DatabaseManager.removeHorse(getSubject().getName());
		new NewHorseGUI();
		this.dispose();
	}

	public static void main(String[] args) {
		DatabaseManager.load();
		new EditHorseGUI(DatabaseManager.getPopulation().get(0));
	}
}
