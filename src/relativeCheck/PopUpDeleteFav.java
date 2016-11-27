package relativeCheck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import general.MDRDialog;

public class PopUpDeleteFav extends MDRDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpDeleteFav(ManageFavouritesGUI manageFavsFrame, String name) {
		super(manageFavsFrame, "Liste "+name+" wirklich löschen?");

		JButton buttonAccept = new JButton("OK");
		buttonAccept.setBounds(gridButtonGap, yTop, (2*widthLabel-gridButtonGap)/2, buttonHeight);
		buttonAccept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DatabaseManager.deleteFavourite(name);
				dispose();
			}
		});
		cp.add(buttonAccept);
		
		JButton buttonCancel = new JButton("Abbrechen");
		buttonCancel.setBounds(gridButtonGap*2+(2*widthLabel-gridButtonGap)/2, yTop, (2*widthLabel-gridButtonGap)/2, buttonHeight);
		buttonCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cp.add(buttonCancel);
		
		setSize(gridButtonGap*5/2+2*widthLabel,yTop+gridButtonGap*7/2+buttonHeight);
		super2();
	}
	
}
