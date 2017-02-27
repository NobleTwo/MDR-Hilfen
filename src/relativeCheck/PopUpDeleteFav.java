package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import general.MDRButton;
import general.MDRDialog;

public class PopUpDeleteFav extends MDRDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpDeleteFav(ManageFavouritesGUI manageFavsFrame, String name) {
		super(manageFavsFrame, "Liste '" + name + "' wirklich löschen?");

		MDRButton buttonAccept = new MDRButton("OK");
		buttonAccept.setBounds(gridButtonGap, yTop, (2 * widthLabel - gridButtonGap) / 2, buttonHeight);
		buttonAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DatabaseManager.deleteFavourite(name);
				dispose();
			}
		});
		cp.add(buttonAccept);

		MDRButton buttonCancel = new MDRButton("Abbrechen");
		buttonCancel.setBounds(gridButtonGap * 2 + (2 * widthLabel - gridButtonGap) / 2, yTop, (2 * widthLabel - gridButtonGap) / 2, buttonHeight);
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cp.add(buttonCancel);

		setSize(gridButtonGap * 5 / 2 + 2 * widthLabel, yTop + gridButtonGap * 7 / 2 + buttonHeight);
		super2();
	}

}
