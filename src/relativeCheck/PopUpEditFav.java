package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import general.MDRButton;
import general.MDRDialog;

public class PopUpEditFav extends MDRDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpEditFav(ManageFavouritesGUI manageFavsFrame, String name) {
		super(manageFavsFrame, "Name der Favoriten-Liste '" + name + "' ändern");

		final String errorMessageAlreadyKnown = "   --- Name bereits vorhanden!";
		final String errorMessageNotKnown = "   --- Alter Name nicht vorhanden! Bitte abbrechen.";
		JTextField favName = new JTextField(name);
		favName.setBounds(gridButtonGap, yTop, widthLabel * 2, heightLabel);
		favName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s = favName.getText();
				if (s.contains(errorMessageAlreadyKnown)) {
					favName.setText(s.substring(0, s.indexOf(errorMessageAlreadyKnown)));
				}
			}
		});
		cp.add(favName);
		MDRButton buttonAccept = new MDRButton("OK");
		buttonAccept.setBounds(gridButtonGap, yTop + heightLabel + gridButtonGap, (2 * widthLabel - gridButtonGap) / 2, buttonHeight);
		buttonAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = favName.getText();
				if (s.equals(name)) {
					dispose();
				}
				if (s.contains(errorMessageAlreadyKnown)) {
					return;
				}

				int result = DatabaseManager.editFavourite(name, favName.getText());
				switch (result) {
				case -2:
					favName.setText(favName.getText() + errorMessageAlreadyKnown);
					break;
				case -1:
					favName.setText(favName.getText() + errorMessageNotKnown);
					break;
				case 0:
					dispose();
				}
			}
		});
		cp.add(buttonAccept);

		MDRButton buttonCancel = new MDRButton("Abbrechen");
		buttonCancel.setBounds(gridButtonGap * 2 + (2 * widthLabel - gridButtonGap) / 2, yTop + heightLabel + gridButtonGap, (2 * widthLabel - gridButtonGap) / 2, buttonHeight);
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cp.add(buttonCancel);

		setSize(gridButtonGap * 5 / 2 + 2 * widthLabel, yTop + heightLabel + gridButtonGap * 9 / 2 + buttonHeight);
		super2();
	}

}